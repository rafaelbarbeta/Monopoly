/**
 * Classe básica que define uma Carta genérica do jogo. 
 */
public class Carta {
    private String descricao;

    /**
     * @param descricao A descrição da carta. Só pode ser um dos valores definidos em EnumCarta
     */
    public Carta(EnumCarta descricao) {
        this.descricao = descricao.toString();
    }

    /**
     * @return A descrição da carta
     */
    public String getDescricao() {
        return descricao;
    }
}
