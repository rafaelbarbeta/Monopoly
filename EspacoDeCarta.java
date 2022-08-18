/**
 * Subclasse de Espaço. Representa o espaço que permite que um jogador retire uma carta de um dos deques
 */
public class EspacoDeCarta extends Espaco{
    /**
     * Constantes públicas que definem qual o tipo de espaço de Carta : COFRE ou SORTE.
     * Devem ser utilizadas pela classe que utiliza EspacoDeCarta
     */
    public final static int COFRE = 0, SORTE = 1;

    private final int tipoEspacoCarta;

    /**
     * @param nome O nome dado ao espaço. Só pode ser um dos nomes especificados em NomeDoEspaco
     * @param tipoEspacoCarta O tipo do Espaço de Carta. Deve ser passada uma das constantes definidas na classe: COFRE ou SORTE
     */
    public EspacoDeCarta(NomeDoEspaco nome,int tipoEspacoCarta) {
        super(nome);
        this.tipoEspacoCarta = tipoEspacoCarta;
    }

    public int getTipoEspacoCarta() {
        return tipoEspacoCarta;
    }
}
