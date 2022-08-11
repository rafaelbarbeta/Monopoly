/**
 * Representa um espaço nomeado qualquer do tabuleiro.
 */
public class Espaco {
    private final String nome;
    private String tipo;

    /**
     * Retorna um objeto representando um espaço genérico no tabuleiro.
     * É necessário passar o nome desse espaço como argumento.
     * O tipo é genericamente iniciado com o valor "Espaço"
     * @param nome O nome dado ao espaço. Só pode ser um dos nomes especificados em NomeDoEspaco
     * @return Um objeto representando espaço genérico
     */
    public Espaco(NomeDoEspaco nome) {
        this.nome = nome.toString();
        tipo = "Espaco";
    }

    /**
     * @return O nome do Espaço
     */
    public String getNome() {
        return nome;
    }
    /**
     * @return O tipo do Espaço. Para essa classe, retorna o valor padrão "Espaço"
     */
    public String getTipo() {
        return tipo;
    }

}
