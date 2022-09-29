import java.util.Random;
/**
 * Representa um dado de 6 lados
 * MAXLADO é uma constante estática que representa o maior número presente no dado, contando a partir de 1
 * ultimoValor guarda o valor do último "rolamento" do dado
 * LADOS é um vetor constante que simula os lados do dado
 */
public class Dado {
    private final static int MAXLADO = 6;
    private final int[] LADOS;
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
     * @return valor aleatório do resulado do rolamento do dado
     */
    public int rolar() {
        Random lancamento = new Random();
        ultimoValor = LADOS[lancamento.nextInt(Dado.MAXLADO)];
        return ultimoValor;
    }
    /**
     * @return ultimo valor do lançamento do dado
     */
    public int getUltimoValor() {
        return ultimoValor;
    }
}
