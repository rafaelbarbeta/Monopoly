/**
 * Subclasse de Espaco. Representa o espaço que obrigada o jogador pagar um imposto sobre sua fortuna ou um valor fixo
 */
public class ImpostodeRenda extends Espaco{
    private int imposto;
    
    /**
     * @param nome O nome dado ao espaço. Só pode ser um dos nomes especificados em NomeDoEspaco
     * @param posicao A posição que o espaço ocupa no tabuleiro. Todo espaço tem uma posição o qual ele ocupa.
     * @param imposto O valor do imposto fixo quando um jogador estiver nesse espaço
     */
    public ImpostodeRenda(NomeDoEspaco nome,int posicao,int imposto) {
        super(nome,posicao);
        this.imposto = imposto;
    }

    /**
     * @return o valor do imposto fixo devido
     */
    public int getImposto() {
        return imposto;
    }
    /* Implementar */
    /*public int getImposto(Jogador jogador) {
    }*/
}
