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
    private Scanner scan;

    /**
     * Inicializa todos os objetos necessários para o funcionamento do Jogo.
     * @param qtdJogadores A quantidade total de jogadores que o Jogo terá.
     * @param nomes Uma array de nomes dos jogadores
     * @throws IllegalArgumentException se quantidade de jogadores maior que 4 ou menor que 1
     */
    public Jogo(int qtdJogadores, String[] nomes, Scanner scan) throws IllegalArgumentException {
        if (qtdJogadores > 4 || qtdJogadores < 1) {
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
     * @param scan um objeto scanner para fazer as leituras do jogo. O scanner deve ser fechado pelo método que o passou
     * Responsável pela comunicação com o usuário
     */
    public void partida() {
        // Definição inicial da ordem de jogadas  
        System.out.println("Definindo ordem dos jogadores");
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
    /**
     * Fluxo normal de decisões, jogar dados, negociar propriedades, construir casa ou hotel e checar dados
     * @param jogador o jogador atual
     * @return se o jogador irá jogarNovamente. Também indica o próximo jogador caso algum deles tenha sido removido
     */
    private boolean executarFluxoNormal(Jogador jogador) {
        boolean jogaNovamente = false;
        System.out.println("Turno de " + jogador.getNome());
        System.out.println("Digite uma opção:");
        System.out.println("1) Jogar dados");
        System.out.println("2) Negociar Propriedade");
        System.out.println("3) Checar Dados");
        System.out.println("4) Construir Casa");
        System.out.println("5) Construir Hotel");

        switch (obterOpcaoSeguro(5)) {
            // joga os dados, e executa a ação do espaço que o jogador cair (além de tratar quando ele passa pelo início
            // e de quando ele está na cadeia)
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
        return jogaNovamente;
    }

    private boolean executarJogadorPreso(Jogador jogador) {
        boolean jogaNovamente = false;
        boolean jogadorSemSaldo = false;
        boolean jogadorNaoEscapou3Vezes = false;
        boolean jogadorRecemLiberto = false;

        //seta as variáveis booleanas para indicar um estado do jogador na cadeia
        if (jogador.getSaldo() < ((Cadeia)jogador.getLocalizacao()).getFianca()) {
            jogadorSemSaldo = true;
        }
        if (false) {
            jogadorNaoEscapou3Vezes = true;
        }

        if (jogadorSemSaldo && jogadorNaoEscapou3Vezes) {
            removerJogador(jogador);
            // jogar denovo para avançar para o próximo jogador
            return true;
        }

        System.out.println(jogador.getNome() + " está preso!");
        System.out.println("Digite uma opção:");
        System.out.println("1) Pagar fiança ($50, lança os dados)");
        System.out.println("2) Jogar dados (tire uma dupla para fugir)");
        System.out.println("3) Negociar Propriedade");
        System.out.println("4) Checar Dados");
        System.out.println("5) Construir Casa");
        System.out.println("6) Construir Hotel");

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
                    }
                    jogaNovamente = true;
                }
                else {
                    if (!jogadorRecemLiberto) {
                        System.out.println("Que pena! não resultou em dupla...");
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

    private void executarNegociarPropriedades(Jogador jogador) {
        int indice = 1;
        System.out.println("Com qual jogador quer negociar?");
        for(Jogador j: jogadores) {
            System.out.println(indice +" - "+ j.getNome());
            indice++;
        }
        Scanner entrada = new Scanner(System.in);
        Integer x = entrada.nextInt();
        Jogador jogEscolhido = jogadores.get(x-1);
        System.out.println("Qual propriedade quer negociar?");
        indice = 1;
        for(Propriedade p : jogEscolhido.getConjuntoPropriedades()) {
            System.out.println(indice +" - "+ p.getNome());
            indice++;
        }
        x = entrada.nextInt();
        Propriedade propEscolhida = jogEscolhido.getConjuntoPropriedades().get(x-1);
        System.out.println("Qual a sua proposta de valor para a propriedade "+ propEscolhida.getNome() +"?");
        Integer valorProposta = entrada.nextInt();
        while (valorProposta > jogador.getSaldo()) {
            System.out.println("Seu saldo é insuficiente para essa proposta, digite outro valor:");
            valorProposta = entrada.nextInt();
        }
        System.out.println(jogEscolhido.getNome()+ " aceita a proposta? Y/N");
            String resposta = entrada.nextLine();
            if(resposta == "Y" || resposta == "y") {
                System.out.println("Négócio fechado entre "+jogador.getNome()+" e "+jogEscolhido.getNome());
                propEscolhida.setDono(jogador);
                jogador.getConjuntoPropriedades().add(propEscolhida);
                jogEscolhido.getConjuntoPropriedades().remove(propEscolhida);
                System.out.println("Propriedade transferida");
            } else {
                System.out.println("Proposta não aceita!");
            }
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
                    System.out.println(jogador.getNome() + " entrou em falência!");
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

    private void executarTaxadeRiqueza(Jogador jogador) {
        int taxa = ((TaxadeRiqueza)jogador.getLocalizacao()).getTaxa();
        System.out.println(jogador.getNome() + " terá que pagar $" + taxa);
        if (banco.pagarBanco(jogador, taxa)) {
            System.out.println(jogador.getNome() + " pagou $" + taxa);
        }
        else {
            System.out.println(jogador.getNome() + " não pode pagar a taxa!");
            System.out.println(jogador.getNome() + " entrou em falência");
            removerJogador(jogador);
        }
    }

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
            System.out.println(jogador.getNome() + " entrou em falência!");
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
                        int qtdMonopoliosAnterior = jogador.getQuantidadeMonopolios();
                        atualizarMonopolio(jogador);
                        if (qtdMonopoliosAnterior != jogador.getQuantidadeMonopolios() && propriedadeAtual instanceof Lote) {
                            System.out.println(jogador.getNome() + " formou um monopólio! Cor: " + ((Lote)propriedadeAtual).getCor());
                        }
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
                System.out.println(jogador.getNome() + " entrou em falência com " + nomeDono + "!");
                removerJogador(jogador, propriedadeAtual.getDono());
            }
        }
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
                for(Propriedade p: j.getConjuntoPropriedades()) { 
                    p.setDono(recebedor); //propriedades agr são do recebedor
                    recebedor.getConjuntoPropriedades().add(p); //adiciona propriedades para recebedor
                }
                jogadores.remove(j);
                break;
            }
        }
        return;
    }

    /**
     * Função responsável por obter uma opção do jogador. 
     * Trata possíveis excessões de leitura além de validá-la
     * @param scan objeto para leitura dos dados
     * @param qtdOpcoes quantas opoções serão disponibilizadas
     * @return o número da opção obtida
     */
    private int obterOpcaoSeguro(int qtdOpcoes) {
        while (true) {
            try {
                String opraw = scan.nextLine();
                String[] oprawparsed = opraw.split(" ");
                if (oprawparsed.length > 1) {
                    System.out.println("Digite apenas um único número");
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
     * método auxiliar para a função atualizarMonopolio. Identifica se uma propriedade faz parte
     * de um "grupo" ou não
     * @param p A propriedade a ser verificada
     * @param grupo O grupo no qual a propriedade é buscada
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
