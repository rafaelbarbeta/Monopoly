import java.util.ArrayList;

public class Jogador {
    private final static int SALDOINICIAL = 1500;
    private final String nome;
    private int saldo;
    ArrayList<Espaco> conjuntoPropriedades;
    private int espacoTabuleiro;
    private int numeroDuplasConsecutivas;
    private int numeroDeEstacoesMetro;
    private int numeroDeUtilidades;
    private boolean naCadeia;
    private int quantidadeMonopolios;


    public Jogador(String nome) {
        this.nome = nome;
        this.saldo = SALDOINICIAL;
        this.conjuntoPropriedades = new ArrayList<Espaco>();
        this.espacoTabuleiro = 0;
        this.numeroDuplasConsecutivas = 0;
        this.numeroDeEstacoesMetro = 0;
        this.numeroDeUtilidades = 0;
        this.naCadeia = false;
        this.quantidadeMonopolios = 0;
    }

    public void adicionarPropriedade(Propriedade novaPropriedade) {
        if (novaPropriedade instanceof Utilidade) {
            numeroDeUtilidades++;
        }
        else if (novaPropriedade instanceof EstacoMetro) {
            numeroDeEstacoesMetro++;
        }
        conjuntoPropriedades.add(novaPropriedade);
        // complementar...
    }

    public int getNumeroDeUtilidades() {
        return this.numeroDeUtilidades;
    }

    public int getNumeroEstacoesMetro() {
        return this.numeroDeEstacoesMetro;
    }

}
