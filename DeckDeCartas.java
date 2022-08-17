import java.util.ArrayList;

public class DeckDeCartas {
    public final static int DECKCOFRE = 1,DECKSORTE = 2;

    private ArrayList<Carta> deck;

    public DeckDeCartas(int tipoDeck) {
        EnumCarta[] descricoesCartas = EnumCarta.values();
        if (tipoDeck == DECKCOFRE) {
            deck.add(new CartaDeMovimento(descricoesCartas[0], CartaDeMovimento.ESPECIFICO, 14));
            deck.add(new CartaDeMovimento(descricoesCartas[1], CartaDeMovimento.ESPECIFICO, 14));
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
