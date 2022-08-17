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

        }
        else {
            //
        }
    }
}
