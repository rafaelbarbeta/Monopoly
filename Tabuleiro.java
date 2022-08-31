
public class Tabuleiro {
    private final Espaco[] tabuleiro;
    private final GrupoDoLote[] grupos;
    private static final int FIANCA = 50;
    private static final int TAXA = 200;

    /**
     * Cria o Tabuleiro a partir do seu nome (Enum NomeDoEspaco), posição no tabuleiro, preço de compra, cor do Lote (EnumCorDoLote), preço do aluguel, preço de construção de casa/hotel e preço de aluguel com casa/hotel.
     * Inicializa os grupos de lote com seus respectivos lotes, separados pela cor
     */
    public Tabuleiro() {
        tabuleiro = new Espaco[41];
        tabuleiro[0] = null; // espaço não utilizado. A primeira posição irá começar com 1
        tabuleiro[1] = new PontoDePartida(NomeDoEspaco.PONTO_PARTIDA,1,200);
        tabuleiro[2] = new Lote(NomeDoEspaco.AV_SUMARE, 2,60, EnumCorDoLote.MARROM, 2, 50, 10);
        tabuleiro[3] = new EspacoDeCarta(NomeDoEspaco.COFRE, 3, EspacoDeCarta.COFRE);
        tabuleiro[4] = new Lote(NomeDoEspaco.PRACA_SE, 4, 60, EnumCorDoLote.MARROM, 4, 50, 20);
        tabuleiro[5] = new ImpostoDeRenda(NomeDoEspaco.IMPOSTO, 5, 200);
        tabuleiro[6] = new EstacaoDeMetro(NomeDoEspaco.METRO_MARACANA,6,200);
        tabuleiro[7] = new Lote(NomeDoEspaco.RUA_25_MARCO, 7, 100, EnumCorDoLote.AZUL_CLARO, 50, 6, 30);
        tabuleiro[8] = new EspacoDeCarta(NomeDoEspaco.SORTE, 8, EspacoDeCarta.SORTE);
        tabuleiro[9] = new Lote(NomeDoEspaco.AV_SAO_JOAO, 9, 100, EnumCorDoLote.AZUL_CLARO, 50, 6, 30);
        tabuleiro[10] = new Lote(NomeDoEspaco.AV_PAULISTA, 10, 120, EnumCorDoLote.AZUL_CLARO, 50, 8, 40);
        tabuleiro[11] = new Cadeia(NomeDoEspaco.CADEIA, 11, FIANCA );
        tabuleiro[12] = new Lote(NomeDoEspaco.AV_VIEIRA_SOUTO, 12, 140, EnumCorDoLote.ROSA, 100, 10, 50);
        tabuleiro[13] = new Utilidade(NomeDoEspaco.COMP_ELETRICA, 13, 150);
        tabuleiro[14] = new Lote(NomeDoEspaco.NITEROI, 14, 140, EnumCorDoLote.ROSA, 100, 10, 50);
        tabuleiro[15] = new Lote(NomeDoEspaco.AV_ATLANTICA, 15, 160, EnumCorDoLote.ROSA, 100, 12, 60);
        tabuleiro[16] = new EstacaoDeMetro(NomeDoEspaco.METRO_CARIOCA, 16, 200);
        tabuleiro[17] = new Lote(NomeDoEspaco.AV_PRES_JUSCELINO, 17, 180, EnumCorDoLote.LARANJA, 100, 14, 70);
        tabuleiro[18] = new EspacoDeCarta(NomeDoEspaco.COFRE, 18, EspacoDeCarta.COFRE);
        tabuleiro[19] = new Lote(NomeDoEspaco.AV_ENG_LUIS, 19, 180, EnumCorDoLote.LARANJA, 100, 14, 70);
        tabuleiro[20] = new Lote(NomeDoEspaco.AV_BRIGADEIRO, 20, 200, EnumCorDoLote.LARANJA, 100,  16, 80);
        tabuleiro[21] = new EstacionamentoGratis(NomeDoEspaco.ESTACIONAMENTO, 21);
        tabuleiro[22] = new Lote(NomeDoEspaco.IPANEMA, 22, 220, EnumCorDoLote.VERMELHO, 150, 18, 90);
        tabuleiro[23] = new EspacoDeCarta(NomeDoEspaco.SORTE, 23, EspacoDeCarta.SORTE);
        tabuleiro[24] = new Lote(NomeDoEspaco.LEBLON, 24, 220, EnumCorDoLote.VERMELHO, 150, 18, 90);
        tabuleiro[25] = new Lote(NomeDoEspaco.COPACABANA, 240, 25, EnumCorDoLote.VERMELHO, 150, 20, 100);
        tabuleiro[26] = new EstacaoDeMetro(NomeDoEspaco.METRO_CONSOLACAO, 26, 200);
        tabuleiro[27] = new Lote(NomeDoEspaco.AV_CIDADE_JARDIM, 27, 260, EnumCorDoLote.AMARELO, 150, 22, 110);
        tabuleiro[28] = new Lote(NomeDoEspaco.PACAEMBU, 28, 260, EnumCorDoLote.AMARELO, 150, 22, 110);
        tabuleiro[29] = new Utilidade(NomeDoEspaco.COMP_AGUA, 29, 150);
        tabuleiro[30] = new Lote(NomeDoEspaco.IBIRAPUERA, 30, 280, EnumCorDoLote.AMARELO, 150, 24, 120);
        tabuleiro[31] = new VaParaCadeia(NomeDoEspaco.VA_PARA_CADEIA, 31);
        tabuleiro[32] = new Lote(NomeDoEspaco.BARRA_DA_TIJUCA, 32, 300, EnumCorDoLote.VERDE, 200, 26, 130);
        tabuleiro[33] = new Lote(NomeDoEspaco.JARDIM_BOTANICO, 33, 300, EnumCorDoLote.VERDE, 200, 26, 130);
        tabuleiro[34] = new EspacoDeCarta(NomeDoEspaco.COFRE, 34, EspacoDeCarta.COFRE);
        tabuleiro[35] = new Lote(NomeDoEspaco.LAGOA_RODRIGO, 35, 320, EnumCorDoLote.VERDE, 200, 28, 150);
        tabuleiro[36] = new EstacaoDeMetro(NomeDoEspaco.METRO_REPUBLICA, 36, 200);
        tabuleiro[37] = new EspacoDeCarta(NomeDoEspaco.SORTE, 37, EspacoDeCarta.SORTE);
        tabuleiro[38] = new Lote(NomeDoEspaco.AV_MORUMBI, 38, 350, EnumCorDoLote.AZUL, 200, 35, 175);
        tabuleiro[39] = new TaxadeRiqueza(NomeDoEspaco.TAXA_RIQUEZA,39, TAXA);
        tabuleiro[40] = new Lote(NomeDoEspaco.RUA_OSCAR_FREIRE, 40, 400, EnumCorDoLote.AZUL, 200, 50, 200);

        grupos = new GrupoDoLote[8];
        for (int i = 0; i < 8; i++) {
            grupos[i] = new GrupoDoLote();
        }

        grupos[0].adicionarLote((Lote)tabuleiro[2]);
        grupos[0].adicionarLote((Lote)tabuleiro[4]);

        grupos[1].adicionarLote((Lote)tabuleiro[7]);
        grupos[1].adicionarLote((Lote)tabuleiro[9]);
        grupos[1].adicionarLote((Lote)tabuleiro[10]);

        grupos[2].adicionarLote((Lote)tabuleiro[12]);
        grupos[2].adicionarLote((Lote)tabuleiro[14]);
        grupos[2].adicionarLote((Lote)tabuleiro[15]);

        grupos[3].adicionarLote((Lote)tabuleiro[17]);
        grupos[3].adicionarLote((Lote)tabuleiro[19]);
        grupos[3].adicionarLote((Lote)tabuleiro[20]);

        grupos[4].adicionarLote((Lote)tabuleiro[22]);
        grupos[4].adicionarLote((Lote)tabuleiro[24]);
        grupos[4].adicionarLote((Lote)tabuleiro[25]);

        grupos[5].adicionarLote((Lote)tabuleiro[27]);
        grupos[5].adicionarLote((Lote)tabuleiro[28]);
        grupos[5].adicionarLote((Lote)tabuleiro[30]);

        grupos[6].adicionarLote((Lote)tabuleiro[32]);
        grupos[6].adicionarLote((Lote)tabuleiro[33]);
        grupos[6].adicionarLote((Lote)tabuleiro[35]);

        grupos[7].adicionarLote((Lote)tabuleiro[38]);
        grupos[7].adicionarLote((Lote)tabuleiro[40]);
    }

    /**
     * Obtém a referência do vetor que guarda o tabuleiro.
     * @return vetor de Espaco representando o tabuleiro completo.
     */
    public Espaco[] getTabuleiro() {
        return tabuleiro;
    }
    
    /**
     * Obtém uma referência ao GrupoDoLote da cor desejada
     * @param cor uma enum EnumCorDoLote representando a cor desejada para obter o GrupoDoLote correspondente
     * @return GrupoDoLote selecionado através da cor
     */
    public GrupoDoLote getGrupo(EnumCorDoLote cor) {
        return grupos[cor.numCor()];
    }

    /**
     * Movimenta o jogador pelo tabuleiro de acordo com a Carta de Movimento retirada.
     * @return true se o jogador passou pelo Ponto de Partida.
     * @param jogador O jogador que será movimentado.
     * @param carta A Carta de Movimento retirada pelo jogador.
     */
    public boolean movimentarJogador(Jogador jogador, CartaDeMovimento carta) {
        int posicaoAtual = jogador.getLocalizacao().getPosicao();
        int novaPosicao, valorMovimento = carta.getValorMovimento();

        switch(carta.getTipoMovimento()) {
            case CartaDeMovimento.PARAFRENTE: 
                return movimentarJogador(jogador, valorMovimento); 

            case CartaDeMovimento.PARATRAS:
                if(posicaoAtual - valorMovimento < 1) {
                    novaPosicao = 40 + (posicaoAtual - valorMovimento);
                    jogador.setLocalizacao(tabuleiro[novaPosicao]);
                }
                else {
                    novaPosicao = posicaoAtual - valorMovimento;
                    jogador.setLocalizacao(tabuleiro[novaPosicao]);
                }
                return false;

            case CartaDeMovimento.ESPECIFICO:
                novaPosicao = valorMovimento;
                jogador.setLocalizacao(tabuleiro[valorMovimento]);
                return false;

            case CartaDeMovimento.MAISPROXIMO:
                switch(carta.getValorMovimento()) {
                    case CartaDeMovimento.UTILIDADE:
                        
                    case CartaDeMovimento.METRO:
                }
        }
        return false;
    }
    
    /**
     * Movimenta o jogador pelo tabuleiro de acordo com a quantidade retirada nos Dados.
     * @return True se o jogador passou pelo Ponto de Partida.
     * @param jogador O jogador que será movimentado.
     * @param quantidadeMovimento A quantidade de movimento definida ao jogar os Dados.
     */
    public boolean movimentarJogador(Jogador jogador, int quantidadeMovimento) {
        int posicaoAtual = jogador.getLocalizacao().getPosicao();
        int novaPosicao;
        if(posicaoAtual + quantidadeMovimento > 40) {
            novaPosicao = quantidadeMovimento - (40 - posicaoAtual);
            jogador.setLocalizacao(tabuleiro[novaPosicao]);
            return true;
        }
        else {
            novaPosicao = posicaoAtual + quantidadeMovimento;
            jogador.setLocalizacao(tabuleiro[novaPosicao]);
            return false;
        }
    }
}