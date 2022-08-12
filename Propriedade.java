/**
 * Subclasse de Espaco. Define um espaco que pode ser comprado por jogadores
 */
public class Propriedade extends Espaco {
    private final int precoCompra;

    /**
     * Retorna um objeto representando uma propriedade no tabuleiro, podendo ser um Lote, Utilidade ou Estação de Metro
     * É necessário passar como argumentos o nome do espaço e o preço de compra do mesmo.
     * @param nome O nome dado ao espaço. Só pode ser um dos nomes especificados em NomeDoEspaco
     * @param precoCompra O valor do preco da compra dessa propriedade. É dado por um número inteiro
     */
    public Propriedade(NomeDoEspaco nome,int precoCompra) {
        super(nome);
        this.precoCompra = precoCompra;
    }

    /**
     * @return o valor do preco de compra do espaco
     */
    public int getPrecoCompra() {
        return precoCompra;
    }
}
