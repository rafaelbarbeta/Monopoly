import java.util.ArrayList;

public class Tabuleiro {
    private Espaco[] tabuleiro;
    private static final int FIANCA = 50;
    private static final int TAXA = 200;

    public Tabuleiro() {
        tabuleiro = new Espaco[41];
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
    }

    public void movimentarJogador(Jogador jogador, int quantidadeMovimento) {
        //jogador.setEspacoTabuleiro();
        // dar a volta no tabueiro
    }
}
