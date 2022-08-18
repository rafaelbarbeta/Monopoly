/**
 * Subclasse de Espaço que representa o espaço inicial do jogo.
 */
public class PontoDePartida extends Espaco{
    private int valor;

    /**
     * @param nome O nome dado ao espaço. Só pode ser um dos nomes especificados em NomeDoEspaco
     * @param valor O valor correspondente à quantia recebida pelos jogadores ao voltarem para o Ponto de Partida
     */
    public PontoDePartida(NomeDoEspaco nome, int valor) {
        super(nome);
        this.valor = valor;
    }

    /**
     * @return
     */
    public int getValor() {
        return valor;
    }
}
