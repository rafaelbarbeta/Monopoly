/**
 * É a classe que define a carta de acrésimo ou decrésimo de saldo de um jogador
 */
public class CartaDeDinheiro extends Carta{
    private int valor;
    
    /**
     * Construtor de uma Carta de Dinheiro
     * @param descricao A descrição da carta. Deve ser uma das constantes definidas em EnumCarta
     * @param valor Valor acrésimo ou decrésimo. Valores positivos são acrésimos e negativos decrésimos
     */
    public CartaDeDinheiro(EnumCarta descricao,int valor) {
        super(descricao);
        this.valor = valor;
    }

    /**
     * Getter do Valor da Carta
     * @return O valor de acrésimo/decrésimo definido para a Carta
     */
    public int getValor() {
        return valor;
    }
}