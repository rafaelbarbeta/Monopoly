/* 
 * Classe responsável por controlar todas as operações monetárias do jogo.
 */
public class Banco {

    public Banco() {
    }

    /**
     * @param recebedor O jogador que irá receber o bônus monetário.
     * @param valor O valor a ser recebido.
     */
    public void bonusJogador(Jogador recebedor, int valor) { //bonus variados
        int valorAtual = recebedor.getSaldo();
        recebedor.setSaldo(valorAtual + valor);
    }
    
    /**
     * @return Verdadeiro quando o pagamento foi realizado, se não, o jogador pagador entrou em falência para o Banco.
     * @param pagador O jogador que irá realizar o pagamento ao Banco.
     * @param valor O valor a ser recebido pelo banco e descontado do saldo do Jogador.
     */
    public boolean pagarBanco(Jogador pagador, int valor) { //comprou propriedade, casa, pagou taxa, imposto
        int valorAtualPagador = pagador.getSaldo();
        if(valorAtualPagador < valor) {
            detectouFalencia(pagador); //troca propriedades
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
            detectouFalencia(pagador, recebedor); //troca propriedades
            return false;
        } else {
            pagador.setSaldo(valorAtualPagador - valor);
            recebedor.setSaldo(recebedor.getSaldo() + valor);
            return true;
        }
    }

    /**
     * @param pagador O jogador que entrou em falência devendo para o Banco.
     */
    public void detectouFalencia(Jogador pagador) {
        for(Espaco p: pagador.getConjunPropriedades()) { //teria que ser do tipo Propriedade??
            ((Propriedade) p).setDono(null); //propriedades disponiveis pra compra
        }
    }
    /**
     * @param pagador O jogador que entrou em falência e irá perder as propriedades.
     * @param recebedor O jogador que receberá as propriedades do jogador em falência.
     */
    public void detectouFalencia(Jogador pagador, Jogador Recebedor) {
        //A TROCA DE PROPIEDADES É FEITA PELO JOGO?
        //for(Propriedade p: pagador.conjuntoPropriedades) {
            //p.setDono(Recebedor); //passa para o Recebedor (adicionarPropriedade já faz isso?)
            //Recebedor.adicionarPropriedade(p);
        //}
    }

}
