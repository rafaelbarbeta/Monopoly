/**
 * Classe básica que define uma Carta genérica do jogo. 
 */
public class Carta {
    private final String DESCRICAO;

    /**
     * Retorna um objeto representando uma carta genérica do jogo
     * 
     * @param descricao A descrição da carta. Só pode ser um dos valores definidos em EnumCarta
     * @return Um objeto que representa uma carta genérica
     */
    public Carta(EnumCarta descricao) {
        this.DESCRICAO = descricao.toString();
    }

    /**
     * @return A descrição da carta
     */
    public String getDescricao() {
        return DESCRICAO;
    }
}
