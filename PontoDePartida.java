/**
 * Representa o espaço inicial do jogo para os jogadores.
 */
public class PontoDePartida extends Espaco{
    private int bonus;

    /**
     * Construtor do ESpaço Ponto de Partida
     * @param nome O nome dado ao espaço. Só pode ser um dos nomes especificados em NomeDoEspaco
     * @param posicao A posição que o espaço ocupa no tabuleiro. Todo espaço tem uma posição o qual ele ocupa.
     * @param bonus O bonus correspondente à quantia recebida pelos jogadores ao voltarem para o Ponto de Partida
     */
    public PontoDePartida(NomeDoEspaco nome,int posicao,int bonus) {
        super(nome,posicao);
        this.bonus = bonus;
    }

    /**
     * Getter do Bonus de Ponto de Partida
     * @return O valor de bonus quando um dos jogadores passa pelo PontoDePartida
     */
    public int getBonus() {
        return bonus;
    }
}
