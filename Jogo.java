import java.util.ArrayList;
/**
 * Classe principal, que contém todos os objetos necessários par simular um jogo de Monopoly
 * É a responsável pela interface com jogadores. Contém um conjunto de métodos privados para gerenciamento da Partida.
 */
public class Jogo {
    private final Tabuleiro tabuleiro;
    private final Dado dado1, dado2;
    private final DeckDeCartas deckSorte, deckCofre;
    //private final Banco banco;
    private ArrayList<Jogador> jogadores;
    private int jogadorAtual;

    /**
     * Inicializa todos os objetos necessários para o funcionamento do Jogo.
     * @param qtdJogadores A quantidade total de jogadores que o Jogo terá.
     * @param nomes Uma array de nomes dos jogadores
     * @throws IllegalArgumentException se quantidade de jogadores maior que 4 ou menor que 1
     */
    public Jogo(int qtdJogadores, String[] nomes) throws IllegalArgumentException {
        if (qtdJogadores > 4 || qtdJogadores < 1) {
            throw new IllegalArgumentException("Jogo não pode ter mais que 1 ou menos que 4 jogadores");
        }
        tabuleiro = new Tabuleiro();
        dado1 = new Dado();
        dado2 = new Dado();
        deckSorte = new DeckDeCartas(DeckDeCartas.DECKSORTE);
        deckCofre = new DeckDeCartas(DeckDeCartas.DECKCOFRE);
        jogadores = new ArrayList<Jogador>();
        for (int i = 0; i < qtdJogadores; i++) {
            jogadores.add(new Jogador(nomes[i],tabuleiro.getEspaco(1)));
        }
        jogadorAtual = 0;
    }

    /**
     * Método que executa a Partida de um jogo de Monopoly. Executa até que o Jogo acabe.
     * Responsável pela comunicação com o usuário
     */
    public void Partida() {
        System.out.println("Definindo ordem dos jogadores");
        
    }

}
