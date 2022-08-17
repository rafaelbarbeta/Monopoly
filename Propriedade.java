/**
 * Subclasse de Espaco. Define um espaco que pode ser comprado por jogadores
 */
public abstract class Propriedade extends Espaco {
    private int precoCompra;
    protected int aluguel;
    protected Jogador dono;

    /**
     * Retorna um objeto representando uma propriedade no tabuleiro, podendo ser um Lote, Utilidade ou Estação de Metro
     * É necessário passar como argumentos o nome do espaço e o preço de compra do mesmo.
     * @param nome O nome dado ao espaço. Só pode ser um dos nomes especificados em NomeDoEspaco
     * @param precoCompra O valor do preco da compra dessa propriedade. É dado por um número inteiro
     */
    public Propriedade(NomeDoEspaco nome,int precoCompra) {
        super(nome);
        this.precoCompra = precoCompra;
        dono = null;
    }

    /**
     * @return o valor do preco de compra do espaco
     */
    public int getPrecoCompra() {
        return precoCompra;
    }

    /**
     * Configura o novo dono da propriedade
     * @param dono A referencia de um objeto do tipo Jogador. É o dono da propriedade
     */
    public void setDono(Jogador dono) {
        this.dono = dono;
    }

    /**
     * Calcula o preço de alguel de uma propriedade dependendo do seu tipo: Lote, Utilidade ou Estação de Metro
     */
    public abstract void calcularAluguel();
}
