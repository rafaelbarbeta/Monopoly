public enum EnumCarta {
    
    
    /* CARTAS COFRE */
    
    // Cartas de movimento
    COFRE_01("Avance para Niterói"),
    COFRE_02("Avance para a Rua Oscar Freire"),
    COFRE_03("Avance para a Rua 25 de Março"),
    COFRE_04("Avance para o Leblon"),
    COFRE_05("Avance 2 espaços"),
    COFRE_06("Volte 4 espaços"),
    COFRE_07("Avance para a Utilidade mais próxima"),
    
    // Cartas de dinheiro
    COFRE_08("Seu seguro de vida alcança seu estágio mais avançado (colete $100)"),
    COFRE_09("Você ficou em 2º lugar em um concurso de beleza (colete $10)"),
    COFRE_10("Erro bancário a seu favor (colete $200)"),
    COFRE_11("Receba $25 por seus serviços"),
    COFRE_12("Pague $75 para auxiliar na reconstrução da cidade"),
    COFRE_13("Pague $100 ao hospital"),
    COFRE_14("Você ganhou uma competição de cruzadinha (colete $100)"),
    COFRE_15("Multa por alta velocidade (pague $45)"),
    
    // Carta "Vá para a cadeia"
    COFRE_16("Vá diretamente para a cadeia"),
    
    
    /* CARTAS SORTE */
    
    // Cartas de movimento
    SORTE_01("Avance para o Ponto de Partida"),
    SORTE_02("Avance para o Jardim Botânico"),
    SORTE_03("Avance para a Avenida Presidente Juscelino Kubitschek"),
    SORTE_04("Avance para a Praça da Sé"),
    SORTE_05("Avance 5 espaços"),
    SORTE_06("Volte 3 espaços"),
    SORTE_07("Avance para a Estação de Metrô mais próxima"),
    
    
    // Cartas de dinheiro
    SORTE_08("Você foi eleito presidente do Conselho. Pague $50."),
    SORTE_09("Erro bancário a seu favor (colete $75)"),
    SORTE_10("Taxa de atendimento médico (pague $50)"),
    SORTE_11("Seu fundo bancário de Natal cresce (colete $100)"),
    SORTE_12("O Banco te paga um dividendo de $50"),
    SORTE_13("Sua sociedade de crédito imobiliário cresce (colete $150)"),
    SORTE_14("Você sofreu um acidente de trânsito (pague $40)"),
    SORTE_15("É seu aniversáio! (colete $200)"),
    
    // Carta "Vá para a cadeia"
    SORTE_16("Vá diretamente para a cadeia"),
    ;
    
    private final String descricao;

    EnumCarta(final String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}