/**
 * Representa o espaço que manda os jogadores diretamente para o espaço Cadeia
 */
public class VaParaCadeia extends Espaco{
    /**
     * @param nome O nome dado ao espaço. Só pode ser um dos nomes especificados em NomeDoEspaco
     * @param posicao A posição que o espaço ocupa no tabuleiro. Todo espaço tem uma posição o qual ele ocupa.
     */
    public VaParaCadeia(NomeDoEspaco nome,int posicao) {
        super(nome,posicao);
    }
}
