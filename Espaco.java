/**
 * Representa um espaço nomeado qualquer do tabuleiro.
 */
public class Espaco {
    /**
     * Nome do Espaço
     */
    private String nome;
    /**
     * Posição do espaço no Tabuleiro
     */
    private int posicao;

    /**
     * Retorna um objeto representando um espaço genérico no tabuleiro.
     * É necessário passar o nome desse espaço como argumento.
     * @param nome O nome dado ao espaço. Só pode ser um dos nomes especificados em NomeDoEspaco
     * @param posicao A posição que o espaço ocupa no tabuleiro. Todo espaço tem uma posição o qual ele ocupa.
     * @return Um objeto representando espaço genérico
     */
    public Espaco(NomeDoEspaco nome,int posicao) {
        this.nome = nome.toString();
        this.posicao = posicao;
    }

    /**
     * Getter do Nome do Espaço
     * @return O nome do Espaço
     */
    public String getNome() {
        return nome;
    }

    /**
     * Getter da Posição do espaço no Tabuleiro
     * @return A posição do espaço genérico no tabuleiro.
     */
    public int getPosicao() {
        return posicao;
    }
}
