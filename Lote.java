public class Lote extends Propriedade {
    private String cor;
    private int precoAluguel;
    private int precoConstrucaoCasaHotel;
    private int precoAluguelComCasa;
    private boolean temCasaHotel;
    
    public Lote(NomeDoEspaco nome,int posicao,int precoCompra,EnumCorDoLote cor,
                       int precoAluguel,int precoConstrucaoCasaHotel,int precoAluguelComCasa) {

        super(nome,posicao,precoCompra);
        this.cor = cor.toString();
        this.precoAluguel = precoAluguel;
        this.precoConstrucaoCasaHotel = precoConstrucaoCasaHotel;
        this.precoAluguelComCasa = precoAluguelComCasa;
        this.temCasaHotel = false;
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

    public boolean getTemCasaHotel() {
        return temCasaHotel;
    }

    public void setTemCasaHotel(boolean temCasaHotel) {
        this.temCasaHotel = temCasaHotel;
    }

    public int calcularAluguel(int valorDados) {
        if (temCasaHotel) {
            return precoAluguelComCasa;
        }
        else {
            return precoAluguel;
        }
    }
}
