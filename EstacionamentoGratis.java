/**
 * Subclasse de Espaco. É o espaço que não gera nenhum evento quando os jogadores "caem" nele
 */
public class EstacionamentoGratis extends Espaco{

    /**
     * @param nome O nome dado ao espaço. Só pode ser um dos nomes especificados em NomeDoEspaco
     */
    public EstacionamentoGratis(NomeDoEspaco nome) {
        super(nome);
    }
}