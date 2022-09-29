/**
 * Subclasse de Carta. É a classe que define a carta um movimento a ser realizado por um jogador.
 */
public class CartaDeMovimento extends Carta {
    /**
     * Constante estática que define o tipo de movimento.
     * 1 é para frente(PARAFRENTE), -1 para trás(PARATRAS), 0 é para um espaço em específico(ESPECIFICO) e 2 é para um ponto mais próximo do jogador (MAISPROXIMO)
     */
    public final static int PARAFRENTE = 1, PARATRAS = -1, ESPECIFICO = 0, MAISPROXIMO = 2;

    /**
     * Constantes estáticas que definem para qual espaço mias próximo o jogador deve avançar
     * 1 é para Utilidade mais próxima e 2 é para Metro mais próximo.
     */
    public final static int UTILIDADE = 1, METRO = 2;
    /**
     * Variável que representa o tipo do movimento (PARAFRENTE, PARATRAS ou ESPECIFICO)
     */
    private int tipoMovimento;
    /**
     * Variável que representa o valor do movimento (quantidade de movimento)
     */
    private int valorMovimento;

    /**
     * Cria um Carta de Movimento a partir de sua descrição, seu tipo de movimento (para frente, para trás, para um local específico ou para um local mais próximo)
     *  e o quanto será esse movimento (quantidade total ou posição específica).
     * @param descricao A descrição da carta. Deve ser uma das constantes definidas em EnumCarta
     * @param tipoMovimento O tipo de movimento a ser realizado. Só Pode ser uma das constantes da classe: PARAFRENTE,PARATRAS, ESPECIFICO ou MAISPROXIMO
     * @param valorMovimento A quantidade de casas "andadas" ou a posição no tabuleiro para um movimento específico ou uma das constantes UTILIDADE. METRO, para movimento para local mais próximo
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
     * @return O quanto o jogador deve "andar", no caso de movimento para frente ou para trás,
     * ou "ir", no caso de movimento ser para um espaço específico ou "avançar", no caso de ser um movimento para uma espaço mais próximo
     */
    public int getValorMovimento() {
        return this.valorMovimento;
    }
}