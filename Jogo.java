import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * Classe principal, que contém todos os objetos necessários par simular um jogo de Monopoly
 * É a responsável pela interface com jogadores. Contém um conjunto de métodos privados para gerenciamento da Partida.
 */
public class Jogo {
    private final Tabuleiro tabuleiro;
    private final Dado dado1, dado2;
    private final DeckDeCartas deckSorte, deckCofre;
    private final Banco banco;
    private ArrayList<Jogador> jogadores;
    private int jogadorAtual;

    /**
     * Inicializa todos os objetos necessários para o funcionamento do Jogo.
     * @param qtdJogadores A quantidade total de jogadores que o Jogo terá.
     * @param nomes Uma array de nomes dos jogadores
     * @throws IllegalArgumentException se quantidade de jogadores maior que 4 ou menor que 1
     */
    public Jogo(int qtdJogadores, String[] nomes) throws IllegalArgumentException {
        if (qtdJogadores > 4 || qtdJogadores < 1) {
            throw new IllegalArgumentException("Jogo não pode ter mais que 1 ou menos que 4 jogadores");
        }
        tabuleiro = new Tabuleiro();
        dado1 = new Dado();
        dado2 = new Dado();
        deckSorte = new DeckDeCartas(DeckDeCartas.DECKSORTE);
        deckCofre = new DeckDeCartas(DeckDeCartas.DECKCOFRE);
        banco = new Banco();
        jogadores = new ArrayList<Jogador>();
        for (int i = 0; i < qtdJogadores; i++) {
            jogadores.add(new Jogador(nomes[i],tabuleiro.getEspaco(1)));
        }
        jogadorAtual = 0;
    }

    /**
     * Método que executa a Partida de um jogo de Monopoly. Executa até que o Jogo acabe.
     * @param scan um objeto scanner para fazer as leituras do jogo. O scanner deve ser fechado pelo método que o passou
     * Responsável pela comunicação com o usuário
     */
    public void partida(Scanner scan) {
        // Definição inicial da ordem de jogadas  
        System.out.println("Definindo ordem dos jogadores");
        Collections.shuffle(jogadores);
        System.out.println("Ordem de Jogadas:");
        for (int i = 0; i < jogadores.size(); i++) {
            System.out.println((i+1) + ": " + jogadores.get(i).getNome());
        }

        // Loop principal, ações que o joagdor pode tomar na partida e seus efeitos no jogo
        while (!vencedor()) {
            Jogador jogador = jogadores.get(jogadorAtual);
            System.out.println("Turno de " + jogador.getNome());
            System.out.println("Digite uma opção:");
            System.out.println("1) Jogar dados");
            System.out.println("2) Negociar Propriedade");
            System.out.println("3) Checar Dados");
            System.out.println("4) Construir Casa");
            System.out.println("5) Construir Hotel");

            // validação da opção
            int op = 0;
            while (op != 1 && op != 2 && op != 3 && op != 4 && op != 5) {
                try {
                    op = scan.nextInt();
                }
                catch (InputMismatchException e) {
                    System.out.println("Por favor, digite um número inteiro");
                    scan.nextLine();
                }
                finally {
                    if (op != 1 && op != 2 && op != 3 && op != 4 && op != 5) {
                        System.out.println("Opção inválida!");
                    }
                }
            }

            boolean jogadorRemovido = false;
            boolean jogaNovamente = false;
            switch (op) {
                // joga os dados, e executa a ação do espaço que o joagdor cair (além de tratar quando ele passa pelo início)
                case 1:
                    int valDados = jogador.lancarDado(dado1, dado2);
                    System.out.println("Tirou " + valDados + " nos dados");
                    // Verifica se resultou em uma dupla. Se sim, então aplica as ações necessárias para essa situação
                    // (cadeia, se três seguidas, ou apenas o jogador joga novamente)
                    if (jogador.dadosResultaramEmDupla(1)) {
                        System.out.println("Conseguiu uma dupla!");
                        if (jogador.dadosResultaramEmDupla(2) && jogador.dadosResultaramEmDupla(3)) {
                            System.out.println("Três duplas seguidas! " + jogador.getNome() + " está preso!");
                            jogador.setNaCadeia(true);
                            break;
                        }
                        else {
                            jogaNovamente = true;
                        }
                    }

                    // Verifica se o jogador passou pelo início, se sim, dá para ele um bônus
                    boolean passouInicio = tabuleiro.movimentarJogador(jogador,valDados);
                    System.out.println(jogador.getNome() + " caiu na casa " + jogador.getLocalizacao().getNome());
                    if (passouInicio) {
                        System.out.println(jogador.getNome() + " passou pelo início (ganhou 200R$)");
                        banco.bonusJogador(jogador, ((PontoDePartida)tabuleiro.getEspaco(1)).getBonus());
                    }

                    // "Executa" o espaço que o jogador cair, dependendo o que ele seja
                    // Também é capaz de retirar um jogador do jogo caso alguma das opções desencadem isso 
                    if (jogador.getLocalizacao() instanceof VaParaCadeia) {
                        executarVaParaCadeia(jogador);
                    }
                    else if (jogador.getLocalizacao() instanceof EspacoDeCarta) {
                        executarEspacoDeCarta(jogador);
                    }
                    else if (jogador.getLocalizacao() instanceof TaxadeRiqueza) {
                        executarTaxadeRiqueza(jogador);
                    }
                    else if (jogador.getLocalizacao() instanceof ImpostoDeRenda) {
                        executarImpostoDeRenda(jogador);
                    }
                    else if (jogador.getLocalizacao() instanceof Utilidade) {
                        executarUtilidade(jogador);
                    }
                    else if (jogador.getLocalizacao() instanceof EstacaoDeMetro) {
                        executarEstacaoDeMetro(jogador);
                    }
                    else if (jogador.getLocalizacao() instanceof Lote) {
                        executarLote(jogador);
                    }
                    
                    break;

                // opção de negociação de propriedades entre jogadores
                case 2:
                    executarNegociarPropriedades(jogador);
                    jogaNovamente = true;
                    break;
                
                // opção de
                case 3:
                    executarChecarDados(jogador);
                    jogaNovamente = true;
                    break;
                case 4:
                    executarConstruirCasa(jogador);
                    jogaNovamente = true;
                    break;
                case 5:
                    executarConstruirHotel(jogador);
                    jogaNovamente = true;
                    break;
            }

            // Escolhe o próximo jogador caso o atual tenha falido e permanece no mesmo jogador caso
            // ele tire dois valores iguais nos dados ou caso tenha escolhido uma opção que não
            // termina o turno (todas, menos a 1)
            if (jogadorRemovido || jogaNovamente) {
                jogadorAtual = jogadorAtual % jogadores.size();
                jogadorRemovido = false;
                jogaNovamente = false;
            }
            else {
                jogadorAtual = ((jogadorAtual + 1) % jogadores.size());
                jogadorRemovido = false;
                jogaNovamente = false;
            }
        }
    }

    /**
     * Função privada que checa se existe um vencedor no jogo
     * imprime um mensagem de vitória para o jogador vencedor
     * @return true, se há um vencedor
     */
    private boolean vencedor() {
        if (ultimoJogador()) {
            return true;
        }
        else if (jogadorComDoisMonopolios()) {
            return true;
        }
        else if (jogadorComMonopolioEHotel()) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Função privada que checa se há apenas um jogador no jogo
     * imprime um mensagem de vitória para o jogador vencedor
     * @return
     */
    private boolean ultimoJogador() {
        if (jogadores.size() == 1) {
            System.out.println(jogadores.get(0).getNome() + " venceu o jogo! (último jogador restante)");
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Função privada que checa se existe um jogador com dois monopólios
     * imprime um mensagem de vitória para o jogador vencedor
     * @return true, se existe um jogador com dois monopólios
     */
    private boolean jogadorComDoisMonopolios() {
        for (Jogador jogador : jogadores) {
            if (jogador.getQuantidadeMonopolios() >= 2) {
                System.out.println(jogador.getNome() + " venceu o jogo! (alcançou dois monopólios)");
                return true;
            }
        }
        return false;
    }
    /**
     * Função privada que checa se existe um jogador com um monopólio e um hotel
     * imprime um mensagem de vitória para o jogador vencedor
     * @return true, se existir
     */
    private boolean jogadorComMonopolioEHotel() {
        for (Jogador jogador : jogadores) {
            if (jogador.getQuantidadeMonopolios() == 1) {
                for (Propriedade propriedade : jogador.getConjuntoPropriedades()) {
                    if (propriedade instanceof Lote) {
                        if (((Lote)propriedade).getTemHotel()) {
                            System.out.println(jogador.getNome() + " venceu o jogo! (formou monopólio com hotel)");
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }


    private void executarNegociarPropriedades(Jogador jogador) {
        // IMPLEMENTAR
        System.out.println("Função não implementada!!!");
        return;
    }

    private void executarConstruirCasa(Jogador jogador) {
        // IMPLEMENTAR
        System.out.println("Função não implementada!!!");
        return;
    }

    private void executarConstruirHotel(Jogador jogador) {
        // IMPLEMENTAR
        System.out.println("Função não implmentada");
        return;
    }

    private void executarVaParaCadeia(Jogador jogador) {
        // IMPLEMENTAR
        System.out.println("Função não implementada!!!");
    }

    private void executarEspacoDeCarta(Jogador jogador) {
        // IMPLEMENTAR
        System.out.println("Função não implementada!!!");
    }

    private void executarTaxadeRiqueza(Jogador jogador) {
        // IMPLEMENTAR
        System.out.println("Função não implementada!!!");
    }

    private void executarImpostoDeRenda(Jogador jogador) {
        // IMPLEMENTAR
        System.out.println("Função não implementada!!!");
    }

    private void executarUtilidade(Jogador jogador) {
        // IMPLEMENTAR
        System.out.println("Função não implementada!!!");
    }

    private void executarEstacaoDeMetro(Jogador jogador) {
        // IMPLEMENTAR
        System.out.println("Função não implementada!!!");
    }

    private void executarLote(Jogador jogador) {
        // IMPLEMENTAR
        System.out.println("Função não implementada!!!");
    }

    private void executarChecarDados(Jogador jogador) {
        System.out.println("Jogador : " + jogador.getNome());
        System.out.println("Saldo : " + jogador.getSaldo());
        System.out.printf("Propriedades do Jogador : ");
        if (jogador.getConjuntoPropriedades().size() == 0) {
            System.out.println("Nenhuma");
        }
        else {
            for (Propriedade it : jogador.getConjuntoPropriedades()) {
                System.out.println(it.getNome());
            }
        }
        System.out.println("Localização : " + jogador.getLocalizacao().getNome());
        System.out.printf("Preso ? : ");
        if (jogador.getNaCadeia()) {
            System.out.println("sim");
        }
        else {
            System.out.println("nao");
        }
        System.out.println("Número Monopólios : " + jogador.getQuantidadeMonopolios());
        return;
    }

    private boolean executarJogadorPreso(Jogador jogador) {
        // IMPLEMENTAR
        System.out.println("Função não implementada!!!");
        return true;
    }

    private void atualizarMonopolio(Jogador jogador) {
        // IMPLEMENTAR
        System.out.println("Função não implementada!!!");
        return;
    }

}
