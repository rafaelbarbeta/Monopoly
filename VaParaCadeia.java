/**
 * Subclasse de Espaço, representa o espaço que manda os jogadores diretamente para o espaço Cadeia
 */
public class VaParaCadeia extends Espaco{
    /**
     * @param nome O nome dado ao espaço. Só pode ser um dos nomes especificados em NomeDoEspaco
     */
    public VaParaCadeia(NomeDoEspaco nome) {
        super(nome);
    }
}
