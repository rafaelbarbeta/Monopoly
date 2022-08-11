/**
 * Classe básica que define uma Carta genérica do jogo. 
 */
public class Carta {
    private final String descricao;
    private String tipo;

    /**
     * Retorna um objeto representando uma carta genérica do jogo
     * 
     * @param descricao A descrição da carta. Só pode ser um dos valores definidos em EnumCarta
     * @return Um objeto que representa uma carta genérica
     */
    public Carta(EnumCarta descricao) {
        this.descricao = descricao.toString();
        this.tipo = "Carta";
    }

    /**
     * @return A descrição da carta
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @return O tipo da carta. Para essa classe, retorna o valor padrão "Carta"
     */
    public String getTipo() {
        return tipo;
    }
}
