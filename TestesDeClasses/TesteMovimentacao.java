public class TesteMovimentacao {
    public static void main(String[] args) {
        Tabuleiro tabuleiro = new Tabuleiro();
        CartaDeMovimento cartaTeste1 =new CartaDeMovimento(EnumCarta.COFRE_07, CartaDeMovimento.MAISPROXIMO, CartaDeMovimento.UTILIDADE);
        CartaDeMovimento cartaTeste2 =new CartaDeMovimento(EnumCarta.SORTE_07, CartaDeMovimento.MAISPROXIMO, CartaDeMovimento.METRO);
        Jogador j1 = new Jogador("Rafael", tabuleiro.getEspaco(1));
        Jogador j2 = new Jogador("Beatriz", tabuleiro.getEspaco(1)); 
        Dado dado1 = new Dado();
        Dado dado2 = new Dado();
        int valDado1,valDado2;
        boolean passouPeloInicio;
        
        for (int i = 0; i < 10; i++) {
            System.out.println(j1.getNome() +" esta: " + j1.getLocalizacao().getNome());
            valDado1 = dado1.rolar();
            valDado2 = dado2.rolar();
            System.out.println(j1.getNome() + " tirou " + (valDado1 + valDado2));
            passouPeloInicio = tabuleiro.movimentarJogador(j1,valDado1+valDado2);
            if (passouPeloInicio) {
                System.out.println(j1.getNome() + " passou pelo inicio!");
            }
        }
        
        System.out.println();
        System.out.println(j2.getNome() + " esta: "+ j2.getLocalizacao().getNome());
        tabuleiro.movimentarJogador(j2, cartaTeste2);
        System.out.println(j2.getNome() +" foi pra: "+ j2.getLocalizacao().getNome());
    }
}
