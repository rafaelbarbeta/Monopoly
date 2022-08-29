/**
 * Subclasse de Espaco. Define um espaco que pode ser comprado por jogadores
 */
public abstract class Propriedade extends Espaco {
    private int precoCompra; 
    private Jogador dono;

    /**
     * Retorna um objeto representando uma propriedade no tabuleiro, podendo ser um Lote, Utilidade ou Estação de Metro
     * É necessário passar como argumentos o nome do espaço e o preço de compra do mesmo.
     * @param nome O nome dado ao espaço. Só pode ser um dos nomes especificados em NomeDoEspaco
     * @param posicao A posição que o espaço ocupa no tabuleiro. Todo espaço tem uma posição o qual ele ocupa.
     * @param precoCompra O valor do preco da compra dessa propriedade. É dado por um número inteiro
     */
    public Propriedade(NomeDoEspaco nome,int posicao,int precoCompra) {
        super(nome,posicao);
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
     * Obtém uam referência ao dono da propriedade
     * @return Uma referência ao dono da propriedade do tipo Jogador
     */
    public Jogador getDono() {
        return dono;
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
    public abstract int calcularAluguel(int valorDados);
}
