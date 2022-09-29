import java.util.ArrayList;
/**
 * Representa o espaço que obrigada o jogador pagar um imposto sobre sua fortuna ou um valor fixo
 */
public class ImpostoDeRenda extends Espaco{
    private int imposto;
    
    /**
     * Construtor do espaço Imposto de Renda
     * @param nome O nome dado ao espaço. Só pode ser um dos nomes especificados em NomeDoEspaco
     * @param posicao A posição que o espaço ocupa no tabuleiro. Todo espaço tem uma posição o qual ele ocupa.
     * @param imposto O valor do imposto fixo quando um jogador estiver nesse espaço
     */
    public ImpostoDeRenda(NomeDoEspaco nome,int posicao,int imposto) {
        super(nome,posicao);
        this.imposto = imposto;
    }

    /**
     * Getter do valor do Imposto
     * @return o valor do imposto fixo devido
     */
    public int getImposto() {
        return imposto;
    }
    
    /**
     * Método sobrecarregado que retorna o imposto variável, dependendo da fortuna do jogador
     * @param jogador o jogador que será utilizado como base para cálculo do imposto
     * @return O valor do imposto a ser pago
     */
    public int getImposto(Jogador jogador) {
        ArrayList<Propriedade> propriedadesDoJogador = jogador.getConjuntoPropriedades();
        int fortunaTotal = jogador.getSaldo();
        for (Propriedade it : propriedadesDoJogador) {
            fortunaTotal += it.getPrecoCompra();
            if (it instanceof Lote) {
                if (((Lote)it).getTemCasa() || ((Lote)it).getTemHotel()) {
                    fortunaTotal += ((Lote)it).getPrecoConstrucaoCasaHotel();
                }
            }
        }
        return (fortunaTotal/10);
    }
}
