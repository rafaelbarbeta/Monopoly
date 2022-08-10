import java.util.Random;

public class Dado {
    private final static int MAXLADO;
    private final int[] LADOS;
    private int ultimoValor;

    static {
        MAXLADO = 6;
    };

    public Dado() {
        LADOS = new int[Dado.MAXLADO];
        for(int i = 1; i <= Dado.MAXLADO; i++) {
            LADOS[i] = i;
        }
    }

    public int rolar() {
        Random lancamento = new Random();
        ultimoValor = LADOS[lancamento.nextInt(6)];
        return ultimoValor;
    }

    public int getUltimoValor() {
        return ultimoValor;
    }
}
