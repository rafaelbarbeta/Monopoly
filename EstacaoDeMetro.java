/**
 * Subclasse de Propriedade. Corresponde as 4 Estações de Metrô do jogo.
 */
public class EstacaoDeMetro extends Propriedade {
    
    /**
     * @param nome O nome dado ao espaço. Só pode ser um dos nomes especificados em NomeDoEspaco
     * @param posicao A posição que o espaço ocupa no tabuleiro. Todo espaço tem uma posição o qual ele ocupa.
     * @param precoCompra Um valor inteiro que é o preço de compra dessa propriedade
     */
    public EstacaoDeMetro(NomeDoEspaco nome,int posicao,int precoCompra) {
        super(nome,posicao,precoCompra);
    }

    /**
     * Calcula o valor do aluguel com base na qauntidade de Estações de Metrô que o dono possui
     * @param valorDados A soma do resultado tirado nos dois dados pelo Jogador que entrou no Espaço. Não utilizado nessa classe
     * @return O valor devido de aluguel. Inicialmente, é 25 e dobra para cada propriedade o dono possuir
     */
    public int calcularAluguel(int valorDados) {
        switch(getDono().quantidadeEstacoesMetro()) {
            case 1: return 25;
            case 2: return 50;
            case 3: return 100;
            case 4: return 200;
            default:
            return 0;
        }
    }
}
