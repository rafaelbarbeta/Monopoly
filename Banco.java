/** 
 * Classe responsável por controlar todas as operações monetárias do jogo.
 */
public class Banco {
    /**
     * Construtor do Banco
     */
    public Banco() {
    }

    /**
     * Método responsável por dar um bônus financeiro ao jogador. Pode ser utlizado para valores negativos também
     * @param recebedor O jogador que irá receber o bônus monetário.
     * @param valor O valor a ser recebido.
     * @return true quando o bônus foi realizado com sucesso. false, se o bônus resultou em saldo negativo
     */
    public boolean bonusJogador(Jogador recebedor, int valor) { //bonus variados
        int valorAtual = recebedor.getSaldo();
        int saldoJogador = recebedor.getSaldo();
        if (saldoJogador+valor <= 0) {
            return false;
        }
        recebedor.setSaldo(valorAtual + valor);
        return true;
    }
    
    /**
     * Método que realiza um pagamento de um Jogador para o Banco
     * @return true quando o pagamento foi realizado, a falta do pagamento pode indicar falência se foi chamado para uma operação obrigatória
     * @param pagador O jogador que irá realizar o pagamento ao Banco.
     * @param valor O valor a ser recebido pelo banco é descontado do saldo do Jogador.
     */
    public boolean pagarBanco(Jogador pagador, int valor) { //comprou propriedade, casa, pagou taxa, imposto
        int valorAtualPagador = pagador.getSaldo();
        if(valorAtualPagador < valor) {
            //falência pro banco, todas as propriedades do pagador ficam disponíveis pra compra
            return false;
        } else {
            pagador.setSaldo(valorAtualPagador - valor);
            return true;      
        }
    }
    
    /**
     * Método que realiza a transação entre Jogadores
     * @return Verdadeiro quando o pagamento foi realizado, se não, o jogador pagador entrou em falência para o jogador recebedor.
     * @param pagador O jogador que irá realizar um pagamento a outro.
     * @param recebedor O jogador que irá receber um pagamento de outro.
     * @param valor O valor que será repassado do pagador para o recebedor.
     */
    public boolean pagamentoEntreJogadores(Jogador pagador, Jogador recebedor, int valor) {
        int valorAtualPagador = pagador.getSaldo();
        if(valorAtualPagador < valor) {
            //falência, no jogo o recebedor recebe as propriedades do pagador
            return false;
        } else {
            pagador.setSaldo(valorAtualPagador - valor);
            recebedor.setSaldo(recebedor.getSaldo() + valor);
            return true;
        }
    }
}
