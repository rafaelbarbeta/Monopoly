/**
 * Subclasse de Carta. É a classe que define a carta um movimento a ser realizado por um jogador.
 */
public class CartaDeMovimento extends Carta {
    /**
     * Constante estática que define o tipo de movimento.
     * 1 é para frente(PARAFRENTE), -1 para trás(PARATRAS) e 0 é para um espaço em específico(ESPECIFICO)
     */
    public final static int PARAFRENTE, PARATRAS, ESPECIFICO;

    static {
        PARAFRENTE = 1;
        PARATRAS = -1;
        ESPECIFICO = 0;
    }

    private int tipoMovimento;
    private int valorMovimento;

    /**
     * 
     * @param descricao A descrição da carta. Deve ser uma das constantes definidas em EnumCarta
     * @param tipoMovimento O tipo de movimento a ser realizado. Só Pode ser uma das constantes da classe: PARAFRENTE,PARATRAS ou ESPECIFICO
     * @param valorMovimento A quantidade de casas "andadas" ou a posição no tabuleiro para um movimento específico
     */
    public CartaDeMovimento(EnumCarta descricao,int tipoMovimento,int valorMovimento) {
        super(descricao);
        this.tipoMovimento = tipoMovimento;
        this.valorMovimento = valorMovimento;
    }

    /**
     * @return O tipo de movimento, PARAFRENTE, PARATRAS ou ESPECIFICO
     */
    public int getTipoMovimento() {
        return this.tipoMovimento;
    }

    /**
     * @return O quanto o jogador deve "andar" ou "ir", no caso de movimento ser para um espaço específico 
     */
    public int getValorMovimento() {
        return this.valorMovimento;
    }
}