import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Classe que gerencia o jogo. Pergunat quantos jogadores irão jogar e se gostaria de se reiniciar uma partida
 */
public class Gerenciador {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
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
        System.out.println("Digite os nomes dos " + numJogadores + " jogadores");
        for (int i = 0; i < numJogadores; i++) {
            nomes[i] = scan.nextLine();
        }
        monopoly = new Jogo(numJogadores,nomes,scan);
        int maisUmaVez = 0;
        do {
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
}
