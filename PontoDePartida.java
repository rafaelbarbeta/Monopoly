/**
 * Subclasse de Espa�o que representa o espa�o inicial do jogo.
 */
public class PontoDePartida extends Espaco{
    private int valor;

    /**
     * @param nome O nome dado ao espa�o. S� pode ser um dos nomes especificados em NomeDoEspaco
     * @param valor O valor correspondente � quantia recebida pelos jogadores ao voltarem para o Ponto de Partida
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
