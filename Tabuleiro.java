import java.util.ArrayList;

public class Tabuleiro {
    private ArrayList<Espaco> tabuleiro;
    public static final int MARROM=1, AZULCLARO=2, ROSA=3, LARANJA=4, VERMELHO=5, AMARELO=6, VERDE=7, AZULESCURO=8; //?
   
    public Tabuleiro() {
        tabuleiro = new ArrayList();
        tabuleiro.add(1, new PontoDePartida(NomeDoEspaco.PONTO_PARTIDA, 200));
        tabuleiro.add(2, new Lote(NomeDoEspaco.AV_SUMARE, 60, 50, 2, 10, MARROM));
        //tabuleiro.add(3, new COFRE);
        tabuleiro.add(4, new Lote(NomeDoEspaco.PRACA_SE, 60, 50, 4, 20, MARROM));
        //tabuleiro.add(5, new ImpostodeRenda(NomeDoEspaco.IMPOSTO));
        tabuleiro.add(6, new EstacaoDeMetro(NomeDoEspaco.METRO_MARACANA, 200));
        tabuleiro.add(7, new Lote(NomeDoEspaco.RUA_25_MARCO, 100, 50, 6, 30, AZULCLARO));
        //tabuleiro.add(8, new Carta());
        tabuleiro.add(9, new Lote(NomeDoEspaco.AV_SAO_JOAO, 100, 50, 6, 30, AZULCLARO));
        tabuleiro.add(10, new Lote(NomeDoEspaco.AV_PAULISTA, 120, 50, 8, 40, AZULCLARO));
        //tabuleiro.add(11, new Cadeia());
        tabuleiro.add(12, new Lote(NomeDoEspaco.AV_VIEIRA_SOUTO, 140, 100, 10, 50, ROSA));
        tabuleiro.add(13, new Utilidade(NomeDoEspaco.COMP_ELETRICA, 150));
        tabuleiro.add(14, new Lote(NomeDoEspaco.NITEROI, 140, 100, 10, 50, ROSA));
        tabuleiro.add(15, new Lote(NomeDoEspaco.AV_ATLANTICA, 160, 100, 12, 60, ROSA));
        tabuleiro.add(16, new EstacaoDeMetro(NomeDoEspaco.METRO_CARIOCA, 200));
        tabuleiro.add(17, new Lote(NomeDoEspaco.AV_PRES_JUSCELINO, 180, 100, 14, 70, LARANJA));
        //tabuleiro.add(18, new COFRE);
        tabuleiro.add(19, new Lote(NomeDoEspaco.AV_ENG_LUIS, 180, 100, 14, 70, LARANJA));
        tabuleiro.add(20, new Lote(NomeDoEspaco.AV_BRIGADEIRO, 200, 100, 16, 80, LARANJA));
        tabuleiro.add(21, new EstacionamentoGratis(NomeDoEspaco.ESTACIONAMENTO));
        tabuleiro.add(22, new Lote(NomeDoEspaco.IPANEMA, 220, 150, 18, 90, VERMELHO));
        //tabuleiro.add(23, new Carta());
        tabuleiro.add(24, new Lote(NomeDoEspaco.LEBLON, 220, 150, 18, 90, VERMELHO));
        tabuleiro.add(25, new Lote(NomeDoEspaco.COPACABANA, 240, 150, 20, 100, VERMELHO));
        tabuleiro.add(26, new EstacaoDeMetro(NomeDoEspaco.METRO_CONSOLACAO, 200));
        tabuleiro.add(27, new Lote(NomeDoEspaco.AV_CIDADE_JARDIM, 260, 150, 22, 110, AMARELO));
        tabuleiro.add(28, new Lote(NomeDoEspaco.PACAEMBU, 260, 150, 22, 110, AMARELO));
        tabuleiro.add(29, new Utilidade(NomeDoEspaco.COMP_AGUA, 150));
        tabuleiro.add(30, new Lote(NomeDoEspaco.IBIRAPUERA, 280, 150, 24, 120, AMARELO));
        tabuleiro.add(31, new VaParaCadeia(NomeDoEspaco.VA_PARA_CADEIA));
        tabuleiro.add(32, new Lote(NomeDoEspaco.BARRA_DA_TIJUCA, 300, 200, 26, 130, VERDE));
        tabuleiro.add(33, new Lote(NomeDoEspaco.JARDIM_BOTANICO, 300, 200, 26, 130, VERDE));
        //tabuleiro.add(34, new COFRE);
        tabuleiro.add(35, new Lote(NomeDoEspaco.LAGOA_RODRIGO, 320, 200, 28, 150, VERDE));
        tabuleiro.add(36, new EstacaoDeMetro(NomeDoEspaco.METRO_REPUBLICA, 200));
        //tabuleiro.add(37, new Carta());
        tabuleiro.add(38, new Lote(NomeDoEspaco.AV_MORUMBI, 350, 200, 35, 175, AZULESCURO));
        //tabuleiro.add(39, new TaxadeRiqueza(NomeDoEspaco.TAXA_RIQUEZA));
        tabuleiro.add(40, new Lote(NomeDoEspaco.RUA_OSCAR_FREIRE, 400, 200, 50, 200, AZULESCURO));
    }

    public void movimentarJogador(Jogador jogador, int quantidadeMovimento) {
        //jogador.setEspacoTabuleiro();
        // dar a volta no tabueiro
    }
}
