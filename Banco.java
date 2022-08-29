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
     * @param pagador O jogador que irá realizar o pagamento ao Banco.
     * @param valor O valor a ser recebido pelo banco e descontado do saldo do Jogador.
     */
    public void pagarBanco(Jogador pagador, int valor) { //comprou propriedade, casa, pagou taxa, imposto
        int valorAtual = pagador.getSaldo();
        if(valorAtual < valor)
            detectouFalencia(pagador);
        else {
            pagador.setSaldo(valorAtual - valor);      
        }
    }
    
    /**
     * @param pagador O jogador que irá realizar um pagamento a outro.
     * @param recebedor O jogador que irá receber um pagamento de outro.
     * @param valor O valor que será repassado do pagador para o recebedor.
     */
    public void pagamentoEntreJogadores(Jogador pagador, Jogador recebedor, int valor) {
        int valorAtual = pagador.getSaldo();
        pagador.setSaldo(valorAtual - valor);
    
        valorAtual = recebedor.getSaldo();
        recebedor.setSaldo(valorAtual + valor);
    }
    /**
     * @param pagador O jogador que entrou em falência devendo para o Banco.
     */
    public boolean detectouFalencia(Jogador pagador) {
        saldoBanco = saldoBanco + pagador.getSaldo();
        for(Espaco p: pagador.conjuntoPropriedades) { //teria que ser do tipo Propriedade??
            p.setDono(null); //propriedades disponiveis pra compra
        }
    }
    /**
     * @param pagador O jogador que entrou em falência e irá perder as propriedades.
     * @param recebedor O jogador que receberá as propriedades do jogador em falência.
     */
    public boolean detectouFalencia(Jogador pagador, Jogador Recebedor) {
        for(Propriedade p: pagador.conjuntoPropriedades) {
            p.setDono(Recebedor); //passa para o Recebedor (adicionarPropriedade já faz isso?)
            Recebedor.adicionarPropriedade(p);
        }
    }
    /*fazer um if antes de todas as operações de pagamento pra detectar falência. se entrar, chamar um método do jogo que "desconecta" jogador?
    ou fazer um retorno, se retornar 1 no jogo realizou normal, se não, o jogador n tinha saldo e declara em falencia
    */
}
