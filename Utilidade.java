/**
 * Representa os espaços da Companhia Elétrica e Companhia de distribuição de Água
 */
public class Utilidade extends Propriedade {

    /**
     * Construtor do Espaço de Utilidade
     * @param nome O nome dado ao espaço. Só pode ser um dos nomes especificados em NomeDoEspaco
     * @param posicao A posição que o espaço ocupa no tabuleiro. Todo espaço tem uma posição o qual ele ocupa.
     * @param precoCompra Um valor inteiro que é o preço de compra dessa propriedade
     */
    public Utilidade(NomeDoEspaco nome,int posicao,int precoCompra) {
        super(nome,posicao,precoCompra);
    }
    
    /**
     * Calcula o valor do aluguel com base na quantidade de utilidades que o dono possui, 
     * e o valor que o jogador que "caiu" no espaço tirou nos dados
     * @param valorDados A soma do resultado tirado nos dois dados pelo Jogador que entrou no Espaço
     * @return O valor devido do aluguel. Pode ser 4 ou 10 vezes o valor tirado nos dados
     */
    public int calcularAluguel(int valorDados) {
        switch(getDono().quantidadeUtilidade()) {
            case 1:
            return valorDados * 4;
            case 2:
            return valorDados * 10;
            default:
            return 0;
        }
    }
}
