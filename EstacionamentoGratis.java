/**
 * É o espaço que não gera nenhum evento quando os jogadores "caem" nele
 */
public class EstacionamentoGratis extends Espaco{

    /**
     * Construtor de estacionamento Grátis
     * @param nome O nome dado ao espaço. Só pode ser um dos nomes especificados em NomeDoEspaco
     * @param posicao A posição que o espaço ocupa no tabuleiro. Todo espaço tem uma posição o qual ele ocupa.
     */
    public EstacionamentoGratis(NomeDoEspaco nome,int posicao) {
        super(nome,posicao);
    }
}