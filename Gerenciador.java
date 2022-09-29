import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Classe que gerencia o jogo. Pergunta quantos jogadores irão jogar e se gostaria de se reiniciar uma partida
 */
public class Gerenciador {
    static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {
        Jogo monopoly;
        System.out.println("Digite a quantidade de jogadores");
        int numJogadores = 0;
        while (numJogadores <= 1 || numJogadores >= 5) {
            try {
                numJogadores = scan.nextInt();
            }
            catch (InputMismatchException e) {
                System.out.println("Por favor, digite um número inteiro");
                scan.nextLine();
            }
            finally {
                if (numJogadores <= 1 || numJogadores >= 5) {
                    System.out.println("Por favor, digite um número entre 2 e 4 de jogadores");
                }
            }
        }
        scan.nextLine();
        String[] nomes = new String[numJogadores];
        String nome;
        System.out.println("Digite os nomes dos " + numJogadores + " jogadores");
        for (int i = 0; i < numJogadores; i++) {
            nome = recebeNome();
            for(int y = 0; y < numJogadores; y++) {
                if(nome.equals(nomes[y])) {
                    System.out.println("O nome "+ nome + " já foi adicionado, digite um nome diferente:");
                    nome = recebeNome();
                    y = -1; //volta o for do inicio
                }
            }
            nomes[i] = nome;
        }
        int maisUmaVez = 0;
        do {
            monopoly = new Jogo(numJogadores,nomes,scan);
            monopoly.partida();
            System.out.println("Partida encerrada! Gostaria de jogar mais uma vez com a configuração atual? (1 para sim)");
            try {
                maisUmaVez = scan.nextInt();
            }
            catch (InputMismatchException e) {
                System.out.println("Opção inválida! Encerrando o programa...");
                maisUmaVez = 0;
            }
        } while (maisUmaVez == 1);
        scan.close();
    }
    
    /**
     * Função que recebe o nome de um jogador, garantindo que não seja um espaço em branco ou tenha menos ue 3 caracteres
     * @return Um nome válido inserido pelo jogador
     */
    private static String recebeNome() {
        String nome;
        while(true) {
            nome = scan.nextLine();
            int tamanhoNome = (int)nome.chars().filter(ch -> ch != ' ').count();
            if(tamanhoNome > 2)
                break;
            System.out.println("Digite um nome válido (no mínimo 3 caracteres)");
        }            
        return nome;
    }
    
}
