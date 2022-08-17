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

        }
        else {
            //
        }
    }
}
