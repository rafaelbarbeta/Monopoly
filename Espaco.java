/**
 * Representa um espaço nomeado qualquer do tabuleiro.
 */
public class Espaco {
    private final String nome;

    /**
     * Retorna um objeto representando um espaço genérico no tabuleiro.
     * É necessário passar o nome desse espaço como argumento.
     * @param nome O nome dado ao espaço. Só pode ser um dos nomes especificados em NomeDoEspaco
     * @return Um objeto representando espaço genérico
     */
    public Espaco(NomeDoEspaco nome) {
        this.nome = nome.toString();
    }

    /**
     * @return O nome do Espaço
     */
    public String getNome() {
        return nome;
    }
}
