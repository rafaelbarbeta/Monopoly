import java.util.ArrayList;
/**
 * Classe correspondente a um jogador de uma partida
 */
public class Jogador {
    private String nome;
    private int saldo;
    private ArrayList<Propriedade> conjuntoPropriedades;
    private int[][] jogadasAnteriores;
    private int rodadasPreso;
    private Espaco localizacao;
    private boolean naCadeia;
    private int quantidadeMonopolios;

    /**
     * Constrói um Jogador com saldo inicial de 1500 e atributos zerados.
     * @param nome Nome do Jogador
     * @param localizacao Localização Inicial
     */
    public Jogador(String nome,Espaco localizacao) {
        this.nome = nome;
        this.saldo = 1500;
        conjuntoPropriedades = new ArrayList<Propriedade>();
        this.jogadasAnteriores = new int[3][2];

        this.jogadasAnteriores[0][0] = 0;
        this.jogadasAnteriores[0][1] = 1;
        this.jogadasAnteriores[1][0] = 0;
        this.jogadasAnteriores[1][1] = 1;
        this.jogadasAnteriores[2][0] = 0;
        this.jogadasAnteriores[2][1] = 1;

        this.localizacao = localizacao;
        this.naCadeia = false;
        this.quantidadeMonopolios = 0;
        this.rodadasPreso = 0;
    }
    /**
     * Getter do nome do Jogador
     * @return O nome do jogador
     */
    public String getNome() {
        return nome;
    }
    
    /**
     * Getter do Saldo do Jogador
     * @return O saldo atual do Jogador
     */
    public int getSaldo() {
        return saldo;
    }

    /**
     * Atualiza o saldo do Jogador
     * @param saldo Valor do novo saldo
     */
    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    /**
     * Retorna verdadeiro se uma jogada anterior definida pelo parâmetro qualJogada resultou em dupla
     * @param qualJogada qual jogada deseja-se saber se resultou em dupla, pode ser "1", "2", "3" para 1ª, 2ª ou 3ª
     * @return true, se resultou em dupla
     */
    public boolean dadosResultaramEmDupla(int qualJogada) {
        if (jogadasAnteriores[qualJogada-1][0] == jogadasAnteriores[qualJogada-1][1]) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Getter para o valor nos dados de uma jogada anterior
     * @param qualJogada qual das três jogadas anteriores
     * @return A soma dos dados de uma jogada anterior
     */
    public int getJogadaAnterior(int qualJogada) {
        return jogadasAnteriores[qualJogada-1][0] + jogadasAnteriores[qualJogada-1][1];
    }

    /**
     * Getter do Conjunto de Propriedades do Jogador
     * @return uma cópia do conjunto de Propriedades do Jogador
     */
    public ArrayList<Propriedade> getConjuntoPropriedades() {
        ArrayList<Propriedade> copiaConjuntoPropriedades = new ArrayList<Propriedade>(conjuntoPropriedades);
        return copiaConjuntoPropriedades;
    }

    /**
     * Setter do novo Conjunto de Propriedade do Jogador
     * @param conjuntoPropriedades uma ArrayList contendo um conjunto de propriedades.
     */
    public void setConjuntoPropriedades(ArrayList<Propriedade> conjuntoPropriedades) {
        this.conjuntoPropriedades = new ArrayList<Propriedade>(conjuntoPropriedades);
        return;
    }

    /**
     * Getter da localização atual do Jogador
     * @return Localização atual do Jogador (Espaço)
     */
    public Espaco getLocalizacao() {
        return localizacao;
    }

    /**
     * Setter da localização atual do Jogador
     * @param novaLocalizacao A nova localização do Jogador
     */
    public void setLocalizacao(Espaco novaLocalizacao) {
        this.localizacao = novaLocalizacao;
    }

    /**
     * Getter da situação do Jogador (Se está na cadeia ou não)
     * @return Se o jogador está na Cadeia "true" ou não "false".
     */
    public boolean getNaCadeia() {
        return naCadeia;
    }

    /**
     * Setter da situação do JOgador (Se vai para a Cadeia ou não)
     * @param naCadeia Se o Jogador vai para a Cadeia "true" ou sai "false"
     */
    public void setNaCadeia(boolean naCadeia) {
        this.naCadeia = naCadeia;
    }

    /**
     * Getter da quantidade de Monopólios atual do Jogador
     * @return Quantidade atual de monopólios do Jogador
     */
    public int getQuantidadeMonopolios() {
        return quantidadeMonopolios;
    }

    /**
     * Setter da quantidade nova de monopólios do Jogador
     * @param quantidadeMonopolios Nova quantidade de monopólios do Jogador
     */
    public void setQuantidadeMonopolios(int quantidadeMonopolios) {
        this.quantidadeMonopolios = quantidadeMonopolios;
    }

    /**
     * Getter do número de rodadas que o jogador passou preso
     * @return Valor inteiro que indica quantas rodadas o jogador passou preso
     */
    public int getRodadasPreso() {
        return rodadasPreso;
    }

    /**
     * Setter do número atualizado de rodadas que o JOgador passou preso
     * @param rodadasPreso valor inteiro que indica quantas rodadas o jogador passou preso
     */
    public void setRodadasPreso(int rodadasPreso) {
        this.rodadasPreso = rodadasPreso;
    }

    /**
     * Calcula quantas Estações de Metrô o Jogador possui dentre suas Propriedades
     * @return Quantidade de Estações de Metrô que o Jogador possui
     */
    public int quantidadeEstacoesMetro() {
        int totalEstacoesMetro = 0;
        for (Propriedade propriedadeAtual : conjuntoPropriedades) {
            if (propriedadeAtual instanceof EstacaoDeMetro)
                totalEstacoesMetro++;
        }
        return totalEstacoesMetro;
    }

    /**
     * Calcula quantas Utilidades o Jogador possui dentre suas Propriedades
     * @return Quantidade de Utilidades que o Jogador possui
     */
    public int quantidadeUtilidade() {
        int totalEstacoesMetro = 0;
        for (Propriedade propriedadeAtual : conjuntoPropriedades) {
            if (propriedadeAtual instanceof Utilidade)
                totalEstacoesMetro++;
        }
        return totalEstacoesMetro;      
    }

    /**
     * Ação de lançar um dado do Jogador.
     * Atualiza o campo jogadasAnteriores
     * @param dado1 objeto que referencia o primeiro dado do tabuleiro
     * @param dado2 objeto que referencia o segundo dado do tabuleiro
     * @return a soma dos dados
     */
    public int lancarDado(Dado dado1, Dado dado2) {
        int valDado1 = dado1.rolar();
        int valDado2 = dado2.rolar();

        jogadasAnteriores[2][0] = jogadasAnteriores[1][0];
        jogadasAnteriores[2][1] = jogadasAnteriores[1][1];
        jogadasAnteriores[1][0] = jogadasAnteriores[0][0];
        jogadasAnteriores[1][1] = jogadasAnteriores[0][1];
        jogadasAnteriores[0][0] = valDado1;
        jogadasAnteriores[0][1] = valDado2;

        return (valDado1 + valDado2);
    }

    /**
     * Ação de retirar uma carta pelo jogador
     * @param deck o deck do qual o jogador deve retirar uma carta
     * @return A carta retirada.
     */
    public Carta retiraCarta(DeckDeCartas deck) {
        return deck.pegarCarta();
    }
}
