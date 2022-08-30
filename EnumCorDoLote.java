public enum EnumCorDoLote {
    
    /*Cores do Lote*/
    MARROM("Marrom"),
    AZUL_CLARO("Azul Claro"),
    ROSA("Rosa"),
    LARANJA("Laranja"),
    VERMELHO("Vermelho"),
    AMARELO("Amarelo"),
    VERDE("Verde"),
    AZUL("Azul");

    private final String cor;

    EnumCorDoLote(final String cor) {
        this.cor = cor;
    }

    @Override
    public String toString() {
        return cor;
    }

}
