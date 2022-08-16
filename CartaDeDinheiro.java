/**
 * Subclasse de Carta. Define tipo de carta bônus ou perda de dinheiro para quem a receba
 */
public class CartaDeDinheiro extends Carta {
    private int valorCarta;

    public class CartaDeDinheiro() {

    }
    /**
     * @return o valor da carta
     */
    public int getValorCarta() {
        return this.valorCarta;
    }
}