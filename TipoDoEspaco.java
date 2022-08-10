/**
 * Enumeração dos tipos possíveis de espaços. 
 * Um espaço pode ser
 * <ol>
 *  <li> Ponto de Partida </li>
 *  <li> Va para cadeia </li>
 *  <li> Estacionamento Gratis </li>
 *  <li> Cadeia </li>
 *  <li> Imposto de Renda </li>
 *  <li> Taxa de riqueza </li>
 *  <li> Espaco De Carta </li>
 *  <li> Propriedade </li>
 * </ol>
 */

public enum TipoDoEspaco {
    PONTO_PARTIDA("Ponto de Partida"),
    VA_PARA_CADEIA("Vá para cadeia"),
    ESTACIONAMENTO_GRATIS("Estacionamento Grátis"),
    CADEIA("Cadeia"),
    IMPOSTO_RENDA("Imposto de Renda"),
    TAXA_RIQUEZA("Taxa de riqueza"),
    ESPACO_CARTA("Espaço de Carta"),
    PROPRIEDADE("Propriedade");

    private final String tipo;

    TipoDoEspaco(final String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return tipo;
    }
     
}