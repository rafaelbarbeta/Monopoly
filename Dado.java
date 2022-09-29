import java.util.Random;
/**
 * Representa um dado de 6 lados
 */
public class Dado {
    /**
     * Constante estática que define a quantidade de lados do dado
     */
    private final static int MAXLADO = 6;
    /**
     * Vetor que representa os lados do dado
     */
    private final int[] LADOS;
    /**
     * Variável que armazena o último valor retirado no dado
     *  */
    private int ultimoValor;

    /**
     * Inicializa cada lado com seu número correspondente
     */
    public Dado() {
        LADOS = new int[Dado.MAXLADO];
        for(int i = 0; i < Dado.MAXLADO; i++) {
            LADOS[i] = i+1;
        }
    }
    /**
     * Simula o rolamento do dado. Guarda o resultado em ultimoValor para posterior utilização
     * @return valor aleatório do resultado do rolamento do dado
     */
    public int rolar() {
        Random lancamento = new Random();
        ultimoValor = LADOS[lancamento.nextInt(Dado.MAXLADO)];
        return ultimoValor;
    }
    /**
     * Getter do último valor do lançamento do Dado
     * @return ultimo valor do lançamento do dado
     */
    public int getUltimoValor() {
        return ultimoValor;
    }
}
