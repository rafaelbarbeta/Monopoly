import java.util.ArrayList;

public class Jogador {
    private String nome;
    private int saldo;
    private ArrayList<Propriedade> conjuntoPropriedades;
    private int[][] jogadasAnteriores;
    private Espaco localizacao;
    private boolean naCadeia;
    private int quantidadeMonopolios;
    
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
    }
    
    public String getNome() {
        return nome;
    }
    
    public int getSaldo() {
        return saldo;
    }

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
     * @return uma cópia do conjunto de propriedades do jogador
     */
    public ArrayList<Propriedade> getConjuntoPropriedades() {
        ArrayList<Propriedade> copiaConjuntoPropriedades = new ArrayList<Propriedade>(conjuntoPropriedades);
        return copiaConjuntoPropriedades;
    }

    /**
     * @param conjuntoPropriedades uma ArrayList contendo um conjunto de propriedades.
     */
    public void setConjuntoPropriedades(ArrayList<Propriedade> conjuntoPropriedades) {
        this.conjuntoPropriedades = new ArrayList<Propriedade>(conjuntoPropriedades);
        return;
    }


    public Espaco getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(Espaco novaLocalizacao) {
        this.localizacao = novaLocalizacao;
    }

    public boolean getNaCadeia() {
        return naCadeia;
    }

    public void setNaCadeia(boolean naCadeia) {
        this.naCadeia = naCadeia;
    }

    public int getQuantidadeMonopolios() {
        return quantidadeMonopolios;
    }

    public void setQuantidadeMonopolios(int quantidadeMonopolios) {
        this.quantidadeMonopolios = quantidadeMonopolios;
    }

    public int quantidadeEstacoesMetro() {
        int totalEstacoesMetro = 0;
        for (Propriedade propriedadeAtual : conjuntoPropriedades) {
            if (propriedadeAtual instanceof EstacaoDeMetro)
                totalEstacoesMetro++;
        }
        return totalEstacoesMetro;
    }

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
}
