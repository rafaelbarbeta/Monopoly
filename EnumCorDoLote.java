public enum EnumCorDoLote {
    
    /**
     * Define as constantes da cor do lote. também contém um número de ordem dos lotes
     * A ordem é marrom, azul claro, rosa, laranja, vermelho, amarelo, verde, azul
     */
    MARROM("Marrom",0),
    AZUL_CLARO("Azul Claro",1),
    ROSA("Rosa",2),
    LARANJA("Laranja",3),
    VERMELHO("Vermelho",4),
    AMARELO("Amarelo",5),
    VERDE("Verde",6),
    AZUL("Azul",7);

    private final String cor;
    private final int numCor;

    /**
     * Constroi a enum das cores do lote
     * @param cor Uma string que representa a cor do lote
     * @param numCor numero relativo a ordem dos grupos do lote
     */
    EnumCorDoLote(final String cor,final int numCor) {
        this.cor = cor;
        this.numCor = numCor;
    }

    /**
     * @return a representacao em string da cor do lote
     */
    @Override
    public String toString() {
        return cor;
    }

    /**
     * @return o numero relativo a ordem dos grupos do lote
     */
    public int numCor() {
        return numCor;
    }

}
