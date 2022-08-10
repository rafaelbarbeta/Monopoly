/**
 * Representa um espaço nomeado qualquer do tabuleiro.
 */
public class Espaco {
    private final String nome;
    private final String tipo;

    /**
     * Retorna um objeto representando um espaço genérico no tabuleiro.
     * É necessário passar o nome desse espaço como argumento.
     * @param nome O nome dado ao espaço. Só pode ser um dos nomes especificados em NomeDoEspaco
     * @param tipo O tipo do espaço. Pode ser um dos definidos na enum TipoDoEspaco
     * @return Um objeto representando espaço genérico
     */
    public Espaco(NomeDoEspaco nome,TipoDoEspaco tipo) {
        this.nome = nome.toString();
        this.tipo = tipo.toString();
    }

    public String getNome() {
        return nome;
    }

    public String getTipo() {
        return tipo;
    }

}
