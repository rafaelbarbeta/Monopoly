/**
 * Subclasse de Espaco. Representa o espaço em que os jogadores estão presos, e onde devem pagar uma fiança
 */
public class Cadeia extends Espaco{
    private final int FIANCA;

    /**
     * @param nome O nome dado ao espaço. Só pode ser um dos nomes especificados em NomeDoEspaco
     * @param fianca O valor de fiança para o jogador sair da cadeia
     */
    public Cadeia(NomeDoEspaco nome,int fianca) {
        super(nome);
        this.FIANCA = fianca;
    }

    /**
     * @return O valor a ser pago como fiança pelo jogador
     */
    public int getFianca() {
        return FIANCA;
    }
}