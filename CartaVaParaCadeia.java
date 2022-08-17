/**
 * Subclasse de Carta. Classe sem métodos ou atributos prórpios, apenas para identificar uma instância específica
 */
public class CartaVaParaCadeia extends Carta{

    /**
     * @param descricao A descrição da carta. Deve ser uma das constantes definidas em EnumCarta
     */
    public CartaVaParaCadeia(EnumCarta descricao) {
        super(descricao);
    }
}
