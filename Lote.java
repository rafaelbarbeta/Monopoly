/**
 * Subclasse de Propriedade. Corresponde aos 22 lotes adquiríveis pelos jogadores.
 */
public class Lote extends Propriedade {
    private String cor;
    private int precoAluguel;
    private int precoConstrucaoCasaHotel;
    private int precoAluguelComCasa;
    private boolean temCasa;
    private boolean temHotel;
    private boolean monopolizado;
    
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

    public String getCor() {
        return cor;
    }

    public int getPrecoConstrucaoCasaHotel() {
        return precoConstrucaoCasaHotel;
    }

    public int getPrecoAluguelComCasa() {
        return precoAluguelComCasa;
    }

    public boolean getTemCasa() {
        return temCasa;
    }

    public void setTemCasa(boolean temCasa) {
        this.temCasa = temCasa;
    }

    public boolean getTemHotel() {
        return temHotel;
    }

    public void setTemHotel(boolean temHotel) {
        if (temHotel)
            this.temCasa = false;
        this.temHotel = temHotel;
    }

    public boolean getMonopolizado() {
        return monopolizado;
    }

    public void setMonopolizado(boolean monopolizado) {
        monopolizado = this.monopolizado;
    }

    /**
     * Calcula o preço do aluguel com base na existência ou não de casa e monopólio
     * @param valorDados parâmetro não utilizado para uso do valor que "caiu" nos dados.
     */
    public int calcularAluguel(int valorDados) {
        if (temCasa || temHotel) {
            if (monopolizado) {
                return 2*precoAluguelComCasa;
            }
            else {
                return precoAluguelComCasa;
            }
        }
        else {
            if (monopolizado) {
                return 2*precoAluguel;
            }
            else {
                return precoAluguel;
            }
        }
    }
}
