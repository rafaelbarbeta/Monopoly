/* 
 * Classe responsável por controlar todas as operações monetárias do jogo.
 */
public class Banco {

    public Banco() {
    }

    /**
     * método responsável por dar um bônus financeiro ao jogador. Pode ser utlizado para valores negativos também
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
     * 
     * @param pagador O jogador que irá realizar o pagamento ao Banco.
     * @param valor O valor a ser recebido pelo banco é descontado do saldo do Jogador.
     * @return true quando o pagamento foi realizado, a falat do pagamento pode indicar falência se foi chamado para uma operação obrigatória
     */
    public boolean pagarBanco(Jogador pagador, int valor) { //comprou propriedade, casa, pagou taxa, imposto
        int valorAtualPagador = pagador.getSaldo();
        if(valorAtualPagador < valor) {
            falenciaComOBanco(pagador); //troca propriedades
            return false;
        } else {
            pagador.setSaldo(valorAtualPagador - valor);
            return true;      
        }
    }
    
    /**
     * @return Verdadeiro quando o pagamento foi realizado, se não, o jogador pagador entrou em falência para o jogador recebedor.
     * @param pagador O jogador que irá realizar um pagamento a outro.
     * @param recebedor O jogador que irá receber um pagamento de outro.
     * @param valor O valor que será repassado do pagador para o recebedor.
     */
    public boolean pagamentoEntreJogadores(Jogador pagador, Jogador recebedor, int valor) {
        int valorAtualPagador = pagador.getSaldo();
        if(valorAtualPagador < valor) {
            falenciaComJogador(pagador, recebedor); //troca propriedades
            return false;
        } else {
            pagador.setSaldo(valorAtualPagador - valor);
            recebedor.setSaldo(recebedor.getSaldo() + valor);
            return true;
        }
    }

    /**
     * Método que realiza o processo de falência de um jogador com o Banco
     * @param pagador O jogador que entrou em falência devendo para o Banco.
     */
    public void falenciaComOBanco(Jogador pagador) {
        for(Propriedade p: pagador.getConjuntoPropriedades()) { //teria que ser do tipo Propriedade??
            p.setDono(null); //propriedades disponiveis pra compra
            if (p instanceof Lote) {
                ((Lote)p).setTemCasa(false);
                ((Lote)p).setTemHotel(false);
            }
        }
    }
    /**
     * Método que realiza o processo de falência de um jogador com outro
     * @param pagador O jogador que entrou em falência e irá perder as propriedades.
     * @param recebedor O jogador que receberá as propriedades do jogador em falência.
     */
    public void falenciaComJogador(Jogador pagador, Jogador recebedor) {
        for(Propriedade p: pagador.getConjuntoPropriedades()) {
            p.setDono(recebedor);
            if (p instanceof Lote) {
                ((Lote)p).setTemCasa(false);
                ((Lote)p).setTemHotel(false);
            }
        }
    }

}
