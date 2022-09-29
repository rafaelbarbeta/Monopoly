/**
 * Subclasse de Espaço que representa o espaço inicial do jogo para os jogadores.
 */
public class PontoDePartida extends Espaco{
    private int bonus;

    /**
     * @param nome O nome dado ao espaço. Só pode ser um dos nomes especificados em NomeDoEspaco
     * @param posicao A posição que o espaço ocupa no tabuleiro. Todo espaço tem uma posição o qual ele ocupa.
     * @param bonus O bonus correspondente à quantia recebida pelos jogadores ao voltarem para o Ponto de Partida
     */
    public PontoDePartida(NomeDoEspaco nome,int posicao,int bonus) {
        super(nome,posicao);
        this.bonus = bonus;
    }

    /**
     * @return O valor de bonus quando um dos jogadores passa pelo PontoDePartida
     */
    public int getBonus() {
        return bonus;
    }
}
