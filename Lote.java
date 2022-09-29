/**
 * Corresponde aos 22 lotes adquiríveis pelos jogadores.
 */
public class Lote extends Propriedade {
    private String cor;
    private int precoAluguel;
    private int precoConstrucaoCasaHotel;
    private int precoAluguelComCasa;
    private boolean temCasa;
    private boolean temHotel;
    private boolean monopolizado;
    
    /**
     * Constrói um Lote 
     * @param nome Nome do Espaço correspondente ao Lote
     * @param posicao Posição do Lote no Tabuleiro
     * @param precoCompra Preço de compra do Lote
     * @param cor Cor do Lote correpondente ao Grupo a qual pertence
     * @param precoAluguel Valor do aluguel do Lote sem construções
     * @param precoConstrucaoCasaHotel Preço de construção de uma casa ou hotel no Lote
     * @param precoAluguelComCasa Valor do aluguel do Lote com uma Casa
     */
    public Lote(NomeDoEspaco nome,int posicao,int precoCompra,EnumCorDoLote cor,
                       int precoAluguel,int precoConstrucaoCasaHotel,int precoAluguelComCasa) {

        super(nome,posicao,precoCompra);
        this.cor = cor.toString();
        this.precoAluguel = precoAluguel;
        this.precoConstrucaoCasaHotel = precoConstrucaoCasaHotel;
        this.precoAluguelComCasa = precoAluguelComCasa;
        this.temCasa = false;
        this.temHotel = false;
        this.monopolizado = false;
    }

    /**
     * @return Cor correspondente ao Grupo que o Lote pertence
     */
    public String getCor() {
        return cor;
    }

    /**
     * @return O preço de construção da casa ou do hotel
     */
    public int getPrecoConstrucaoCasaHotel() {
        return precoConstrucaoCasaHotel;
    }

    /**
     * @return O preço do aluguel com casa construída
     */
    public int getPrecoAluguelComCasa() {
        return precoAluguelComCasa;
    }

    /**
     * @return Se o Lote tem uma casa "true"  ou não "false"
     */
    public boolean getTemCasa() {
        return temCasa;
    }

    /**
     * @param temCasa Se o Lote tem uma casa "true"  ou não "false"
     */
    public void setTemCasa(boolean temCasa) {
        this.temCasa = temCasa;
    }

    /**
     * @return Se o Lote tem Hotel "true" ou não "false"
     */
    public boolean getTemHotel() {
        return temHotel;
    }

    /**
     * @param temHotel Se o Lote tem Hotel "true"  ou não "false"
     */
    public void setTemHotel(boolean temHotel) {
        if (temHotel)
            this.temCasa = false;
        this.temHotel = temHotel;
    }

    /**
     * @return Se o Lote está monopolizado por algum Jogador "true" ou não "false"
     */
    public boolean getMonopolizado() {
        return monopolizado;
    }

    /**
     * @param monopolizado Se o Lote está monopolizado por algum Jogador "1" ou não "0"
     */
    public void setMonopolizado(boolean monopolizado) {
        this.monopolizado = monopolizado;
    }

    /**
     * Calcula o preço do aluguel com base na existência ou não de casa e monopólio
     * @param valorDados parâmetro não utilizado para uso do valor que "caiu" nos dados.
     * @return Preço do Aluguel a ser cobrado
     */
    public int calcularAluguel(int valorDados) {
        if (monopolizado) {
            if (temCasa || temHotel) {
                return 2*precoAluguelComCasa;
            }
            else {
                return 2*precoAluguel;
            }
        }
        else {
            return precoAluguel;
        }
    }
}
