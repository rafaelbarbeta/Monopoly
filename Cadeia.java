/**
 * Subclasse de Espaco. Representa o espaço em que os jogadores estão presos, e onde devem pagar uma fiança
 */
public class Cadeia extends Espaco{
    private int fianca;

    /**
     * @param nome O nome dado ao espaço. Só pode ser um dos nomes especificados em NomeDoEspaco
     * @param posicao A posição que o espaço ocupa no tabuleiro. Todo espaço tem uma posição o qual ele ocupa.
     * @param fianca O valor de fiança para o jogador sair da cadeia
     */
    public Cadeia(NomeDoEspaco nome,int posicao,int fianca) {
        super(nome,posicao);
        this.fianca = fianca;
    }

    /**
     * Getter da Fiança
     * @return O valor a ser pago como fiança pelo jogador
     */
    public int getFianca() {
        return fianca;
    }
}