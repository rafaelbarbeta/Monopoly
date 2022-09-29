import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
/**
 * Classe principal que contém todos os objetos necessários par simular um jogo de Monopoly
 * É a responsável pela interface com jogadores. Contém um conjunto de métodos privados para gerenciamento da Partida.
 */
public class Jogo {
    private final Tabuleiro tabuleiro;
    private final Dado dado1, dado2;
    private final DeckDeCartas deckSorte, deckCofre;
    private final Banco banco;
    private ArrayList<Jogador> jogadores;
    private int jogadorAtual;
    private Scanner scan;

    private boolean jaConstruiu;
    
    /**
     * Inicializa todos os objetos necessários para o funcionamento do Jogo.
     * @param qtdJogadores A quantidade total de jogadores que o Jogo terá.
     * @param nomes Uma array de nomes dos jogadores
     * @throws IllegalArgumentException se quantidade de jogadores for maior que 4 ou menor que 2
     */
    public Jogo(int qtdJogadores, String[] nomes, Scanner scan) throws IllegalArgumentException {
        if (qtdJogadores > 4 || qtdJogadores < 2) {
            throw new IllegalArgumentException("Jogo não pode ter menos que 1 ou mais que 4 jogadores");
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
        this.scan = scan;
    }

    /**
     * Método que executa a Partida de um jogo de Monopoly. Executa até que o Jogo acabe.
     */
    public void partida() {
        // Definição inicial da ordem de jogadas  
        System.out.println("\nDefinindo ordem dos jogadores");
        Collections.shuffle(jogadores);
        System.out.println("Ordem de Jogadas:");
        for (int i = 0; i < jogadores.size(); i++) {
            System.out.println((i+1) + ": " + jogadores.get(i).getNome());
        }

        // Loop principal, ações que o jogador pode tomar na partida e seus efeitos no jogo
        while (!vencedor()) {
            Jogador jogador = jogadores.get(jogadorAtual);
            boolean jogaNovamente;
            if (!jogador.getNaCadeia()) {
                jogaNovamente = executarFluxoNormal(jogador);
                proxJogador(jogador,jogaNovamente);
                continue;
            }
            else {
                jogaNovamente = executarJogadorPreso(jogador);
                proxJogador(jogador, jogaNovamente);
                continue;
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
     * @return true se resta somente um jogador
     */
    private boolean ultimoJogador() {
        if (jogadores.size() == 1) {
            System.out.println(jogadores.get(0).getNome() + " VENCEU O JOGO! (último jogador restante)");
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
                System.out.println(jogador.getNome() + " VENCEU O JOGO! (alcançou dois monopólios)");
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
                            System.out.println(jogador.getNome() + " VENCEU O JOGO! (formou monopólio com hotel)");
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * Fluxo normal de decisões, jogar dados, negociar propriedades, construir casa ou hotel e checar dados
     * @param jogador o jogador atual
     * @return se o jogador irá jogarNovamente. Também indica o próximo jogador caso algum deles tenha sido removido
     */
    private boolean executarFluxoNormal(Jogador jogador) {
        boolean jogaNovamente = false;
        System.out.println("\nTurno de " + jogador.getNome());
        System.out.println("Digite uma opção:");
        System.out.println("1) Jogar dados");
        System.out.println("2) Negociar Propriedade");
        System.out.println("3) Checar Dados");
        System.out.println("4) Construir Casa");
        System.out.println("5) Construir Hotel\n");

        switch (obterOpcaoSeguro(5)) {
            // joga os dados, e executa a ação do espaço que o jogador cair (além de tratar quando ele passa pelo início
            // e de quando ele está na cadeia)
            case 1:
                int valDados = jogador.lancarDado(dado1, dado2);
                System.out.println("Tirou " + valDados + " nos dados");
                // Verifica se resultou em uma dupla. Se sim, então aplica as ações necessárias para essa situação
                // (cadeia, se três seguidas, ou apenas o jogador joga novamente)
                if (jogador.dadosResultaramEmDupla(1)) {
                    System.out.println("Conseguiu uma dupla! Jogue novamente");
                    if (jogador.dadosResultaramEmDupla(2) && jogador.dadosResultaramEmDupla(3)) {
                        System.out.println("Três duplas seguidas! " + jogador.getNome() + " está preso!");
                        jogador.setNaCadeia(true);
                        jogador.setLocalizacao(tabuleiro.getEspaco(11));
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

                executarCasa(jogador);
                jaConstruiu = false;
                break;

            // opção de negociação de propriedades entre jogadores
            case 2:
                executarNegociarPropriedades(jogador);
                jogaNovamente = true;
                break;
            
            // opção de checar as informações atuais do jogador
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
        return jogaNovamente;
    }

    /**
     * Executa a vez do jogador que está preso, com a interface de opções específicas para quem está na Cadeia
     * @param jogador Jogador que está preso
     * @return true, se jogará novamente
     */
    private boolean executarJogadorPreso(Jogador jogador) {
        boolean jogaNovamente = false;
        boolean jogadorSemSaldo = false;
        boolean jogadorNaoEscapou3Vezes = false;
        boolean jogadorRecemLiberto = false;

        //seta as variáveis booleanas para indicar um estado do jogador na cadeia
        if (jogador.getSaldo() < ((Cadeia)jogador.getLocalizacao()).getFianca()) {
            jogadorSemSaldo = true;
        }
        if (jogador.getRodadasPreso() == 3) {
            jogadorNaoEscapou3Vezes = true;
        }

        if (jogadorSemSaldo && jogadorNaoEscapou3Vezes) {
            removerJogador(jogador);
            // jogar denovo para avançar para o próximo jogador
            return true;
        }

        System.out.println("\n"+jogador.getNome() + " está preso!");
        System.out.println("Digite uma opção:");
        System.out.println("1) Pagar fiança ($50, lança os dados)");
        System.out.println("2) Jogar dados (tire uma dupla para fugir)");
        System.out.println("3) Negociar Propriedade");
        System.out.println("4) Checar Dados");
        System.out.println("5) Construir Casa");
        System.out.println("6) Construir Hotel\n");

        switch (obterOpcaoSeguro(6)) {
            case 1:
                if (jogadorSemSaldo) {
                    System.out.println("Não há saldo disponível para essa opção!");
                    jogaNovamente = true;
                    break;
                }
                banco.pagarBanco(jogador, ((Cadeia)jogador.getLocalizacao()).getFianca());
                jogador.setNaCadeia(false);
                System.out.println("Jogador pagou a fiança! Jogando os dados...");
                jogadorRecemLiberto = true;
            case 2:
                if (jogadorNaoEscapou3Vezes && !jogadorRecemLiberto) {
                    System.out.println("Não pode tentar escapar! (já tentou 3 vezes)");
                    jogaNovamente = true;
                    break;
                }
                int valDados = jogador.lancarDado(dado1, dado2);
                System.out.println("Tirou " + valDados + " nos dados");
                // Verifica se resultou em uma dupla. Se sim, então aplica as ações necessárias para essa situação
                // (cadeia, se três seguidas, ou apenas o jogador joga novamente)
                if (jogador.dadosResultaramEmDupla(1)) {
                    if (!jogadorRecemLiberto) {
                        System.out.println("Conseguiu uma dupla! Saiu da Cadeia!");
                        jogador.setNaCadeia(false);
                        jogador.setRodadasPreso(0);
                    }
                    jogaNovamente = true;
                }
                else {
                    if (!jogadorRecemLiberto) {
                        System.out.println("Que pena! não resultou em dupla...");
                        jogador.setRodadasPreso(jogador.getRodadasPreso() + 1);
                        //termina o turno, já que ele não conseguiu tirar uma dupla
                        return false;
                    }
                }

                // Verifica se o jogador passou pelo início, se sim, dá para ele um bônus
                boolean passouInicio = tabuleiro.movimentarJogador(jogador,valDados);
                System.out.println(jogador.getNome() + " caiu na casa " + jogador.getLocalizacao().getNome());
                if (passouInicio) {
                    System.out.println(jogador.getNome() + " passou pelo início (ganhou 200R$)");
                    banco.bonusJogador(jogador, ((PontoDePartida)tabuleiro.getEspaco(1)).getBonus());
                }

                executarCasa(jogador);
                jaConstruiu = false;
                break;

            case 3:
                executarNegociarPropriedades(jogador);
                jogaNovamente = true;
                break;
            case 4:
                executarChecarDados(jogador);
                jogaNovamente = true;
                break;
            case 5:
                executarConstruirCasa(jogador);
                jogaNovamente = true;
                break;
            case 6:
                executarConstruirHotel(jogador);
                jogaNovamente = true;
                break;
        }
        return jogaNovamente; 
    }
    
    /**
     * "Executa" o espaço que o jogador cair, dependendo o que ele seja
     *  Também é capaz de retirar um jogador do jogo caso alguma das opções desencadem isso 
     * @param jogador o jogador atual
     */
    private void executarCasa(Jogador jogador) {
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
        else if (jogador.getLocalizacao() instanceof Propriedade) {
            executarPropriedade(jogador);
        }
    }

    /**
     * Executa a negociação de propriedades entre o Jogador e quem ele escolher
     * @param jogador Jogador que quer negociar
     */
    private void executarNegociarPropriedades(Jogador jogador) {
        int indice = 1;
        System.out.println("Com qual jogador quer negociar?");
        for(Jogador j: jogadores) {
            System.out.println(indice +" - "+ j.getNome());
            indice++;
        }
        Integer x = obterOpcaoSeguro(indice-1);
        Jogador jogEscolhido = jogadores.get(x-1);
        if(jogador.equals(jogEscolhido)) {
            System.out.println("Você não pode negociar com você mesmo!");
            return;
        }
        if(jogEscolhido.getConjuntoPropriedades().isEmpty()) {
            System.out.println(jogEscolhido.getNome()+ " não possui propriedades!");
            return;
        }
        System.out.println("Qual propriedade quer negociar?");
        indice = 1;
        for(Propriedade p : jogEscolhido.getConjuntoPropriedades()) {
            System.out.println(indice +" - "+ p.getNome());
            indice++;
        }
        x = obterOpcaoSeguro(indice-1);
        Propriedade propEscolhida = jogEscolhido.getConjuntoPropriedades().get(x-1);
        System.out.println("Qual a sua proposta de valor para a propriedade "+ propEscolhida.getNome() +"?");
        
        Integer valorProposta = obterOpcaoSeguro(jogador.getSaldo());
        
        while (valorProposta > jogador.getSaldo()) {
            System.out.println("Seu saldo é insuficiente para essa proposta, digite outro valor:");
            valorProposta = obterOpcaoSeguro(jogador.getSaldo());
        }
        System.out.println(jogEscolhido.getNome()+ " aceita a proposta? Y/N");
            while(true) {
                char resposta = scan.next().charAt(0);
                if(resposta == 'Y' || resposta == 'y') {
                    System.out.println("Négócio fechado entre "+jogador.getNome()+" e "+jogEscolhido.getNome());
                    jogador.setSaldo(jogador.getSaldo() - valorProposta);
                    jogEscolhido.setSaldo(jogEscolhido.getSaldo() + valorProposta);
                    propEscolhida.setDono(jogador);
                    ArrayList<Propriedade> novoConjuntoAdd = jogador.getConjuntoPropriedades();
                    ArrayList<Propriedade> novoConjuntoRemove = jogEscolhido.getConjuntoPropriedades();
                    novoConjuntoAdd.add(propEscolhida);
                    novoConjuntoRemove.remove(propEscolhida);
                    jogador.setConjuntoPropriedades(novoConjuntoAdd);
                    jogEscolhido.setConjuntoPropriedades(novoConjuntoRemove);
                    System.out.println("Propriedade transferida");
                    atualizarMonopolio(jogEscolhido);
                    detectaMonopolio(jogador, propEscolhida);
                    scan.nextLine();
                    break;
                } else if (resposta == 'N' || resposta == 'n') {
                    System.out.println("Proposta não aceita!");
                    scan.nextLine();
                    break;
                }
                else {
                    System.out.println("Digite uma resposta válida: Y/N");
                }
            }
        return;
    }

    /**
     * Método responsável por fornecer a interface de compra de casas para as propriedades no monopólio
     * de um jogador. Checa se já foram construidas propriedades anteriormente ou não e se o jogador
     * tem monopólio para a construção das mesmas.
     * @param jogador o jogador atual jogando
     */
    private void executarConstruirCasa(Jogador jogador) {
        if (jaConstruiu == true) {
            System.out.println("Máximo de melhorias já atingido! Não pode construir casa");
            return;
        }
        else if (jogador.getQuantidadeMonopolios() == 0) {
            System.out.println("Jogador não tem nenhum monopólio para construir casa!");
            return;
        }

        System.out.println("Escolha em que propriedade quer construir uma casa: ");
        int numOpcao = 1;
        ArrayList<Lote> propriedadesMonopolizadas = new ArrayList<>();
        for (Propriedade it : jogador.getConjuntoPropriedades()) {
            if (it instanceof Lote) {
                if (((Lote)it).getMonopolizado() && !((Lote)it).getTemCasa()) {
                    System.out.println(numOpcao +") "+ it.getNome() + " (" + ((Lote)it).getPrecoConstrucaoCasaHotel() + "$)");
                    propriedadesMonopolizadas.add((Lote)it);
                    numOpcao++;
                } 
            }
        }

        if (propriedadesMonopolizadas.size() == 0) {
            System.out.println("Erro: Todas as propriedades monopolizadas já tem uma casa!");
            return;
        } 
        int numEspacoConstruirCasa = obterOpcaoSeguro(propriedadesMonopolizadas.size()) - 1;
        Lote loteParaConstruirCasa = propriedadesMonopolizadas.get(numEspacoConstruirCasa);
        if (banco.pagarBanco(jogador, loteParaConstruirCasa.getPrecoConstrucaoCasaHotel())) {
            loteParaConstruirCasa.setTemCasa(true);
            System.out.println("Casa construida com sucesso!");
            System.out.println(loteParaConstruirCasa.getNome() + " agora tem uma casa");
            jaConstruiu = true;
        }
        else {
            System.out.println("Sem saldo suficiente para construir casa nessa propriedade!");
        }
        return;
    }

    /**
     * Método responsável por fornecer a interface de compra de hoteis para as propriedades no monopólio
     * de um jogador. Checa se todas as prorpriedades do monopólio já tem casa ou não e se o jogador
     * tem monopólio para a construção de hoteis.
     * @param jogador o jogador atual jogando
     */
    private void executarConstruirHotel(Jogador jogador) {
        if (jaConstruiu == true) {
            System.out.println("Máximo de melhorias já atingido! Não pode construir hotel");
            return;
        }
        else if (jogador.getQuantidadeMonopolios() == 0) {
            System.out.println("Jogador não tem nenhum monopólio para construir hotel!");
            return;
        }

        System.out.println("Escolha em que propriedade quer construir um hotel: ");
        int numOpcao = 1;
        ArrayList<GrupoDoLote> gruposMonopolizados = new ArrayList<>();
        ArrayList<Lote> propriedadesMonopolizadas = new ArrayList<>();
        for (GrupoDoLote grupo : tabuleiro.getGrupos()) {
            boolean pertencemAoMesmoJogador = true;
            for (Lote loteDoGrupo : grupo.membrosDoGrupo()) {
                if (!jogadorContemPropriedade(loteDoGrupo, jogador)) {
                    pertencemAoMesmoJogador = false;
                    break;
                }
            }
            if (pertencemAoMesmoJogador) {
                gruposMonopolizados.add(grupo);
            }
        }
        for(GrupoDoLote monopolizados : gruposMonopolizados) {
            boolean podeConstruir = true;
            for(Lote lote : monopolizados.membrosDoGrupo()) {
                if(!lote.getTemCasa() && !lote.getTemHotel()) { //é necessário ter uma casa em cada um / hotel
                    podeConstruir = false;
                }
            }
            if(podeConstruir) {
                for(Lote lote : monopolizados.membrosDoGrupo()) {
                    System.out.println(numOpcao +") "+ lote.getNome() + " (" + ((Lote)lote).getPrecoConstrucaoCasaHotel() + "$)");
                    propriedadesMonopolizadas.add((Lote)lote);
                    numOpcao++;
                }
            }
        }
        if (propriedadesMonopolizadas.size() == 0) {
            System.out.println("Erro: Todas as propriedades monopolizadas já possuem um hotel");
            return;
        } 
        int numEspacoConstruirHotel = obterOpcaoSeguro(propriedadesMonopolizadas.size()) - 1;
        Lote loteParaConstruirHotel = propriedadesMonopolizadas.get(numEspacoConstruirHotel);
        if (banco.pagarBanco(jogador, loteParaConstruirHotel.getPrecoConstrucaoCasaHotel())) {
            loteParaConstruirHotel.setTemCasa(false);
            loteParaConstruirHotel.setTemHotel(true);
            System.out.println("Hotel construido com sucesso!");
            System.out.println(loteParaConstruirHotel.getNome() + " agora tem um hotel");
            jaConstruiu = true;
        }
        else {
            System.out.println("Sem saldo suficiente para construir hotel nessa propriedade!");
        }
        return;
    }

    /**
     * Método que coloca um Jogador na Cadeia
     * @param jogador Jogador que irá pra Cadeia
     */
    private void executarVaParaCadeia(Jogador jogador) {
        System.out.println(jogador.getNome() + " foi mandando para a cadeia!");
        jogador.setNaCadeia(true);
        jogador.setLocalizacao(tabuleiro.getEspaco(11));
    }

    /**
     * Executa as ações de quando um jogador "cai" em um EspacoDeCarta.
     * Isso engloba o processo de retirar a carta pelo jogador do deck correto e
     * aplicar os efeitos da mesma, seja ela uma CartaDeDinheiro, de movimento ou uma VaParaCadeia
     * Detecta caso alguma condição leve o jogador a falência (CartaDeDinheiro)
     * @param jogador o jogador atual o qual será aplicado os efeitos da carta
     */
    private void executarEspacoDeCarta(Jogador jogador) {
        DeckDeCartas deckDoEspaco;
        if (((EspacoDeCarta)jogador.getLocalizacao()).getTipoEspacoCarta() == EspacoDeCarta.COFRE) {
            deckDoEspaco = deckCofre;
        }
        else {
            deckDoEspaco = deckSorte;
        }

        Carta cartaRetirada = jogador.retiraCarta(deckDoEspaco);
        System.out.println(jogador.getNome() + " retirou a carta: " + cartaRetirada.getDescricao());

        if (cartaRetirada instanceof CartaDeMovimento) {
            boolean passouInicio = tabuleiro.movimentarJogador(jogador, (CartaDeMovimento)cartaRetirada);
            System.out.println(jogador.getNome() + " está no espaço " + jogador.getLocalizacao().getNome() +
            " na posição [" + jogador.getLocalizacao().getPosicao() + "] do tabuleiro");
            if (passouInicio) {
                System.out.println(jogador.getNome() + " passou pelo início (ganhou 200R$)");
                banco.bonusJogador(jogador, ((PontoDePartida)tabuleiro.getEspaco(1)).getBonus());
            }
        }
        else if (cartaRetirada instanceof CartaDeDinheiro) {
            int valor = ((CartaDeDinheiro)cartaRetirada).getValor();
            if (valor < 0) {
                if (banco.bonusJogador(jogador, valor)) {
                    System.out.println(jogador.getNome() + " pagou $" + valor);
                }
                else {
                    System.out.println(jogador.getNome() + " não pode pagar!");
                    System.out.println("\n*** "+jogador.getNome() + " entrou em falência! ***");
                    removerJogador(jogador);
                }
            }
            else {
                banco.bonusJogador(jogador, valor);
                System.out.println(jogador.getNome() + " recebeu $" + valor);
            }
        }
        else if (cartaRetirada instanceof CartaVaParaCadeia) {
            jogador.setNaCadeia(true);
            jogador.setLocalizacao(tabuleiro.getEspaco(11));
            System.out.println(jogador.getNome() + " foi mandado para a cadeia!");
        }
    }

    /**
     * Método que executa o pagamento que jogador atual deve efetuar caso “caia” no espaço TaxadeRiqueza
     * @param jogador Jogador que deve pagar a Taxa de Riqueza
     */
    private void executarTaxadeRiqueza(Jogador jogador) {
        int taxa = ((TaxadeRiqueza)jogador.getLocalizacao()).getTaxa();
        System.out.println(jogador.getNome() + " terá que pagar $" + taxa);
        if (banco.pagarBanco(jogador, taxa)) {
            System.out.println(jogador.getNome() + " pagou $" + taxa);
        }
        else {
            System.out.println(jogador.getNome() + " não pode pagar a taxa!");
            System.out.println("\n*** "+jogador.getNome() + " entrou em falência! ***");
            removerJogador(jogador);
        }
    }

    /**
     * Método responsável por estabelecer o pagamento do jogador atual de imposto fixo ou variável (10% de sua fortuna) 
     * @param jogador Jogador que deve pagar o Imposto de Renda
     */
    private void executarImpostoDeRenda(Jogador jogador) {
        int impostoFixo = ((ImpostoDeRenda)jogador.getLocalizacao()).getImposto();
        int impostoVariavel = ((ImpostoDeRenda)jogador.getLocalizacao()).getImposto(jogador);
        System.out.println(jogador.getNome() + " terá que pagar $" + impostoFixo + " de imposto fixo ou " +
                            "10% da sua fortuna");
        int impostoMinimo;
        if (impostoFixo <= impostoVariavel) {
            impostoMinimo = impostoFixo;
        }
        else {
            impostoMinimo = impostoVariavel;
        }

        if (impostoMinimo >= jogador.getSaldo()) {
            System.out.println("Nada a se fazer! Jogador não tem saldo suficiente para pagar imposto");
            System.out.println("\n*** "+jogador.getNome() + " entrou em falência! ***");
            removerJogador(jogador);
        }
        else {
            System.out.println("Digite uma opção: ");
            System.out.println("1) Pagar imposto fixo de $" + impostoFixo);
            System.out.println("2) Pagar imposto sob fortuna total $" + impostoVariavel);
            boolean possivel = false;
            while (!possivel) {
                switch (obterOpcaoSeguro(2)) {
                    case 1:
                        possivel = banco.pagarBanco(jogador, impostoFixo);
                        if (!possivel) {
                            System.out.println("Sem saldo para essa operação! Tenta a outra...");
                            break;
                        }
                        System.out.println(jogador.getNome() + " pagou $" + impostoFixo + " de imposto");
                        break;
                    case 2:
                        possivel = banco.pagarBanco(jogador, impostoVariavel);
                        if (!possivel) {
                            System.out.println("Sem saldo para essa operação! Tenta a outra...");
                            break;
                        }
                        System.out.println(jogador.getNome() + " pagou $" + impostoVariavel + " de imposto");
                        break;
                }
            }
        }
    }

    /**
     * Apresenta a interface de compra de uma Propriedade para o jogador
     * Utilza o poliformismo para realiazar a operção genericamente
     * Alternativamente, realiza a ação de trânsferência de saldo do jogador atual para o dono
     * para o proprietário efetivo do lote
     * @param jogador o jogador atual que caiu nesse lote
     */
    private void executarPropriedade(Jogador jogador) {
        Propriedade propriedadeAtual = (Propriedade)jogador.getLocalizacao();
        if (propriedadeAtual.getDono() == null) {
            System.out.println("Propriedade sem dono! Deseja comprar?");
            System.out.println("Preço : $" + propriedadeAtual.getPrecoCompra());
            System.out.println("1) sim");
            System.out.println("2) não");
            switch(obterOpcaoSeguro(2)) {
                case 1: 
                    if (banco.pagarBanco(jogador, propriedadeAtual.getPrecoCompra())) {
                        propriedadeAtual.setDono(jogador);
                        ArrayList<Propriedade> propriedadesAtuais = jogador.getConjuntoPropriedades();
                        propriedadesAtuais.add(propriedadeAtual);
                        jogador.setConjuntoPropriedades(propriedadesAtuais);
                        System.out.println("Compra realizada com sucesso!");
                        detectaMonopolio(jogador, propriedadeAtual);
                    }
                    else {
                        System.out.println("Não há saldo sulficiente para comprar a propriedade!");
                        break;
                    }
                    
                case 2: break;
            }
        }
        else if (propriedadeAtual.getDono().getNome() == jogador.getNome()) {
            System.out.println("Nada a se fazer! A propriedade é do próprio jogador atual");
        }
        else {
            String nomeDono = propriedadeAtual.getDono().getNome();
            int custoAluguel = propriedadeAtual.calcularAluguel(jogador.getJogadaAnterior(1));
            System.out.println(nomeDono + " é o dono da propriedade");
                                    
            if (
                banco.pagamentoEntreJogadores(
                    jogador, 
                    propriedadeAtual.getDono(), 
                    custoAluguel
                    )
                ) {
                System.out.println(jogador.getNome() + " pagou $" + custoAluguel + " de aluguel para " + nomeDono);
            }
            else {
                System.out.println(jogador.getNome() + " não pode pagar o aluguel de " + custoAluguel);
                System.out.println("\n*** " +jogador.getNome() + " entrou em falência com " + nomeDono + "! ***");
                removerJogador(jogador, propriedadeAtual.getDono());
            }
        }
    }

    /**
     * Método que checa os dados do jogador, apresentando suas informações como o nome, saldo, propriedades, localização, monopólios e se o jogador encontra-se na cadeia ou não.
     * @param jogador Jogador que terá os dados exibidos
     */
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

    /**
     * Método que trata da falência de um jogador e da sua remoção da partida
     * Além disso, trata da transferência das propriedades.
     * @param jogador o jogador que será removido
     */
    private void removerJogador(Jogador jogador) {
        for(Jogador j: jogadores) {
            if(j.getNome() == jogador.getNome()) {
                for(Propriedade p: j.getConjuntoPropriedades()) { 
                    p.setDono(null); //propriedades disponiveis pra compra
                    if (p instanceof Lote) {
                        ((Lote)p).setTemCasa(false);
                        ((Lote)p).setTemHotel(false);
                    }
                }
                jogadores.remove(j);
                break;
            }
        }
        System.out.println("Jogador "+jogador.getNome()+" foi removido!");
        return;
    }

    /**
     * Método sobrecarregado que trata da falência de um jogador com outro e da sua remoção da partida
     * Além disso, trata da transferência das propriedades.
     * @param endividado o jogador que será removido
     * @param recebedor o jogador que receberá as propriedades
     */
    private void removerJogador(Jogador endividado,Jogador recebedor) {
        for(Jogador j: jogadores) {
            if(j.getNome() == endividado.getNome()) {
                ArrayList<Propriedade> conjunto = recebedor.getConjuntoPropriedades(); //pega o array de propriedades
                for(Propriedade p: j.getConjuntoPropriedades()) { 
                    p.setDono(recebedor); //propriedades agr são do recebedor
                    conjunto.add(p); //adiciona propriedades para recebedor
                }
                recebedor.setConjuntoPropriedades(conjunto); //set conjunto
                jogadores.remove(j);
                int qtdMonopoliosAnterior = recebedor.getQuantidadeMonopolios();
                atualizarMonopolio(recebedor);
                if(qtdMonopoliosAnterior < recebedor.getQuantidadeMonopolios()) {
                    System.out.println(recebedor.getNome() + " formou um ou mais monopólios após a transferência");
                }
                break;
            }
        }
        System.out.println("Jogador "+endividado.getNome()+" foi removido!");
        return;
    }

    /**
     * Método que detecta formação de um novo monopólio
     * @param jogador Jogador que teve uma propriedade incluida
     * @param propriedadeAlterada A nova propriedade adquirida pelo Jogador
     */
    private void detectaMonopolio(Jogador jogador, Propriedade propriedadeAlterada) {
        int qtdMonopoliosAnterior = jogador.getQuantidadeMonopolios();
        atualizarMonopolio(jogador);
        if (qtdMonopoliosAnterior != jogador.getQuantidadeMonopolios() && propriedadeAlterada instanceof Lote) {
            System.out.println(jogador.getNome() + " formou um monopólio! Cor: " + ((Lote)propriedadeAlterada).getCor());
        }
    }

    /**
     * Função responsável por obter uma opção do jogador. 
     * Trata possíveis excessões de leitura além de validá-la
     * @param qtdOpcoes Quantas opções serão disponibilizadas
     * @return O número da opção obtida
     */
    private int obterOpcaoSeguro(int qtdOpcoes) {
        while (true) {
            try {
                String opraw = scan.nextLine();
                String[] oprawparsed = opraw.split(" ");
                if (oprawparsed.length > 1 || oprawparsed.length == 0) {
                    System.out.println("Digite um, e apenas um número inteiro");
                    continue;
                }
                int op = Integer.parseInt(oprawparsed[0]);
                if (op < 1 || op > qtdOpcoes) {
                    System.out.println("Digite uma opção entre 1 e " + qtdOpcoes);
                    continue;
                }
                return op;
            }
            catch (NumberFormatException e) {
                System.out.println("Digite apenas um número inteiro");
            }
        }
    }

    /**
     * Escolhe o próximo jogador caso o atual tenha falido e permanece no mesmo jogador caso
     * ele tire dois valores iguais nos dados ou caso tenha escolhido uma opção que não
     * termina o turno (todas, menos a 1)
     * @param atual o jogador que está jogando atualmente
     * @param jogaNovamente booleano que indica se o jogador pode selecionar mais uma opção ou não
     */
    private void proxJogador(Jogador atual,boolean jogaNovamente) {
        if (jogaNovamente) {
            jogadorAtual = jogadorAtual % jogadores.size();
        }
        else {
            jogadorAtual = ((jogadorAtual + 1) % jogadores.size());
        }
    }

    /**
     * Função executada sempre que um jogador adquire, vende ou perde um lote.
     * Verifica as propriedades atuais do jogador e as compara com
     * os grupos definidos em GrupoDoLote e EnumCorDoLote.
     * Atualiza os dados do jogador com base em suas propriedades
     * Atualiza também as propriedades que fazem parte do monopólio
     * @param jogador o jogador atual
     */
    private void atualizarMonopolio(Jogador jogador) {
        int qtdMonopolios = 0;
        for (GrupoDoLote grupo : tabuleiro.getGrupos()) {
            boolean pertencemAoMesmoJogador = true;
            for (Lote loteDoGrupo : grupo.membrosDoGrupo()) {
                if (!jogadorContemPropriedade(loteDoGrupo, jogador)) {
                    pertencemAoMesmoJogador = false;
                    break;
                }
            }

            if (pertencemAoMesmoJogador) {
                qtdMonopolios++;
                jogador.setQuantidadeMonopolios(qtdMonopolios);
                for (Lote lotesMonopolizados : grupo.membrosDoGrupo()) {
                    lotesMonopolizados.setMonopolizado(true);
                }
            }
            else {
                for (Lote loteDoGrupo : grupo.membrosDoGrupo()) {
                    if (jogadorContemPropriedade(loteDoGrupo, jogador)) {
                        loteDoGrupo.setMonopolizado(false);
                    }
                }
            }
        }
  
        jogador.setQuantidadeMonopolios(qtdMonopolios);
        return;
    }

    /**
     * Método auxiliar para a função atualizarMonopolio. Identifica se uma propriedade faz parte
     * de um "grupo" ou não
     * @param p A propriedade a ser verificada
     * @param jogador O jogador que será conferido
     * @return true, se a propriedade está no grupo, ou false, se não estiver
     */
    private boolean jogadorContemPropriedade(Propriedade p, Jogador jogador) {
        for (Propriedade it : jogador.getConjuntoPropriedades()) {
            if (it.equals(p)) {
                return true;
            }
        }
        return false;
    }

}
