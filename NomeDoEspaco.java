/* Utilize a função toString() desse enum como nome para seus espaços  */

public enum NomeDoEspaco {
    
    /* LOTES */
    AV_SUMARE("Avenida Sumaré"),
    PRACA_SE("Praça da Sé"),
    RUA_25_MARCO("Rua 25 de Março"),
    AV_SAO_JOAO("Avenida São João"),
    AV_PAULISTA("Avenida Paulista"),
    AV_VIEIRA_SOUTO("Avenida Vieira Souto"),
    NITEROI("Niterói"),
    AV_ATLANTICA("Avenida Atlântica"),
    AV_PRES_JUSCELINO("Avenida Presidente Juscelino Kubitschek"),
    AV_ENG_LUIS("Avenida Engenheiro Luis Carlos Berrini"),
    AV_BRIGADEIRO("Avenida Brigadeiro Faria Lima"),
    IPANEMA("Ipanema"),
    LEBLON("Leblon"),
    COPACABANA("Copacabana"),
    AV_CIDADE_JARDIM("Avenida Cidade Jardim"),
    PACAEMBU("Pacaembu"),
    IBIRAPUERA("Ibirapuera"),
    BARRA_DA_TIJUCA("Barra da Tijuca"),
    JARDIM_BOTANICO("Jardim Botânico"),
    LAGOA_RODRIGO("Lagoa Rodrigo de Freitas"),
    AV_MORUMBI("Avenida Morumbi"),
    RUA_OSCAR_FREIRE("Rua Oscar Freire"),
    
    /* ESTAÇÕES DE METRÔ */
    METRO_MARACANA("Estação de Metrô Maracanã"),
    METRO_CARIOCA("Estação de Metrô Carioca"),
    METRO_CONSOLACAO("Estação de Metrô Consolação"),
    METRO_REPUBLICA("Estação de Metrô República"),
    
    /* UTILIDADES */
    COMP_ELETRICA("Companhia Elétrica"),
    COMP_AGUA("Companhia de distribuição de água"),
    
    /* CARTAS */
    COFRE("Cofre"),
    SORTE("Sorte"),
    
    /* CANTOS */
    PONTO_PARTIDA("Ponto de Partida"),
    CADEIA("Na cadeia"),
    ESTACIONAMENTO("Estacionamento grátis"),
    VA_PARA_CADEIA("Vá para a cadeia"),
    
    /* IMPOSTO DE RENDA */
    IMPOSTO("Imposto de Renda"),
    
    /* TAXA DE RIQUEZA */
    TAXA_RIQUEZA("Taxa de Riqueza"),
    ;
    
    private final String nome;

    NomeDoEspaco(final String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return nome;
    }
}