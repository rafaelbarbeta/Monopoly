/**
 * Representa o espaço onde o jogador deve pagar uma taxa ao banco de acordo com as regras do Jogo
 */
public class TaxadeRiqueza extends Espaco{
    private final int TAXA;
    
    /**
     * Construtor do Espaço Taxa de Riqueza
     * @param nome O nome dado ao espaço. Só pode ser um dos nomes especificados em NomeDoEspaco
     * @param posicao A posição que o espaço ocupa no tabuleiro. Todo espaço tem uma posição o qual ele ocupa.
     * @param taxa O valor da taxa a ser paga pelos jogadores
     */
    public TaxadeRiqueza(NomeDoEspaco nome,int posicao,int taxa) {
        super(nome,posicao);
        this.TAXA = taxa;
    }

    /**
     * Getter do valor da Taxa de Riqueza
     * @return o valor da taxa 
     */
    public int getTaxa() {
        return TAXA;
    }
}
