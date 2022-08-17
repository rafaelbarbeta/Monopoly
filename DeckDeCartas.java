import java.util.ArrayList;

public class DeckDeCartas {
    public final static int DECKCOFRE = 1,DECKSORTE = 2;

    private ArrayList<Carta> deck;

    public DeckDeCartas(int tipoDeck) {
        EnumCarta[] descricoesCartas = EnumCarta.values();
        if (tipoDeck == DECKCOFRE) {
            deck.add(new CartaDeMovimento(descricoesCartas[0], CartaDeMovimento.ESPECIFICO, 14));
            deck.add(new CartaDeMovimento(descricoesCartas[1], CartaDeMovimento.ESPECIFICO, 40));
            deck.add(new CartaDeMovimento(descricoesCartas[2], CartaDeMovimento.ESPECIFICO, 7));
            deck.add(new CartaDeMovimento(descricoesCartas[3], CartaDeMovimento.ESPECIFICO, 24));
            deck.add(new CartaDeMovimento(descricoesCartas[4], CartaDeMovimento.PARAFRENTE, 2));
            deck.add(new CartaDeMovimento(descricoesCartas[5], CartaDeMovimento.PARATRAS, 4));
            //deck.add(new CartaDeMovimento(descricoesCartas[6], CartaDeMovimento.PARATRAS, 4));
            deck.add(new CartaDeDinheiro(descricoesCartas[7], 100));
            deck.add(new CartaDeDinheiro(descricoesCartas[8], 10));
            deck.add(new CartaDeDinheiro(descricoesCartas[9], 200));
            deck.add(new CartaDeDinheiro(descricoesCartas[10], 25));
            deck.add(new CartaDeDinheiro(descricoesCartas[11], -75));
            deck.add(new CartaDeDinheiro(descricoesCartas[12], -100));
            deck.add(new CartaDeDinheiro(descricoesCartas[13], 100));
            deck.add(new CartaDeDinheiro(descricoesCartas[14], -45));
            deck.add(new CartaVaParaCadeia(descricoesCartas[15]));
        }
        else if (tipoDeck == DECKSORTE) {
            deck.add(new CartaDeMovimento(descricoesCartas[15], CartaDeMovimento.ESPECIFICO, 1));
            deck.add(new CartaDeMovimento(descricoesCartas[16], CartaDeMovimento.ESPECIFICO, 33));
            deck.add(new CartaDeMovimento(descricoesCartas[17], CartaDeMovimento.ESPECIFICO, 17));
            deck.add(new CartaDeMovimento(descricoesCartas[18], CartaDeMovimento.ESPECIFICO, 4));
            deck.add(new CartaDeMovimento(descricoesCartas[19], CartaDeMovimento.PARAFRENTE, 5));
            deck.add(new CartaDeMovimento(descricoesCartas[20], CartaDeMovimento.PARATRAS, 3));
            //deck.add(new CartaDeMovimento(descricoesCartas[20], CartaDeMovimento.PARATRAS, 3)); mais proxima

            deck.add(new CartaDeDinheiro(descricoesCartas[21], -50));
            deck.add(new CartaDeDinheiro(descricoesCartas[22], 75));
            deck.add(new CartaDeDinheiro(descricoesCartas[23], -50));
            deck.add(new CartaDeDinheiro(descricoesCartas[24], 100));
            deck.add(new CartaDeDinheiro(descricoesCartas[25], 50));
            deck.add(new CartaDeDinheiro(descricoesCartas[26], 150));
            deck.add(new CartaDeDinheiro(descricoesCartas[27], -40));
            deck.add(new CartaDeDinheiro(descricoesCartas[28], 200));
            
            deck.add(new CartaVaParaCadeia(descricoesCartas[29]));
        }
        else {
            //
        }
    }
}
