import java.util.ArrayList;

public class Jogador {
    private String nome;
    private int saldo;
    private ArrayList<Propriedade> conjuntoPropriedades;
    private int duplasConsecutivas;
    private Espaco localizacao;
    private boolean naCadeia;
    private int quantidadeMonopolios;
    
    public Jogador(String nome,Espaco localizacao) {
        this.nome = nome;
        conjuntoPropriedades = new ArrayList<Propriedade>();
        this.duplasConsecutivas = 0;
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

    public ArrayList<Propriedade> getConjunPropriedades() {
        return conjuntoPropriedades;
    }

    public int getDuplasConsecutivas() {
        int duplasConsecutivasSalvo = duplasConsecutivas;
        if (duplasConsecutivas == 3) {
            this.duplasConsecutivas = 0;
        }
        return duplasConsecutivasSalvo;
    }

    public void duplaNosDados(boolean dupla) {
        if (dupla) {
            this.duplasConsecutivas++;
        }
        else {
            this.duplasConsecutivas = 0;
        }
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
}
