import java.util.Scanner;

public final class JogoDaVelhaJava {
    
    public static final char JOGADOR_X = 'X';
    public static final char JOGADOR_O = 'O';

    private final char[][] tabuleiro;
    private char jogadorAtual;

    public JogoDaVelhaJava() {
        tabuleiro = new char[3][3];
        jogadorAtual = JOGADOR_X;
        inicializarTabuleiro();
    }

    public void inicializarTabuleiro() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tabuleiro[i][j] = ' ';
            }
        }
    }

    public void mostrarTabuleiro() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(tabuleiro[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    public boolean jogadaValida(int linha, int coluna) {
        return linha >= 0 && linha < 3 && coluna >= 0 && coluna < 3 && tabuleiro[linha][coluna] == ' ';
    }

    public void fazerJogada(int linha, int coluna) {
        if (jogadaValida(linha, coluna)) {
            tabuleiro[linha][coluna] = jogadorAtual;
            jogadorAtual = (jogadorAtual == JOGADOR_X) ? JOGADOR_O : JOGADOR_X;
        } else {
            System.out.println("Jogada inválida. Tente novamente.");
        }
    }

    public boolean haVencedor() {
        return (verificarLinhas() || verificarColunas() || verificarDiagonais());
    }

    private boolean verificarLinhas() {
        for (int i = 0; i < 3; i++) {
            if (tabuleiro[i][0] == tabuleiro[i][1] && tabuleiro[i][0] == tabuleiro[i][2] && tabuleiro[i][0] != ' ') {
                return true;
            }
        }
        return false;
    }

    private boolean verificarColunas() {
        for (int j = 0; j < 3; j++) {
            if (tabuleiro[0][j] == tabuleiro[1][j] && tabuleiro[0][j] == tabuleiro[2][j] && tabuleiro[0][j] != ' ') {
                return true;
            }
        }
        return false;
    }

    private boolean verificarDiagonais() {
        return ((tabuleiro[0][0] == tabuleiro[1][1] && tabuleiro[0][0] == tabuleiro[2][2] && tabuleiro[0][0] != ' ')
                || (tabuleiro[0][2] == tabuleiro[1][1] && tabuleiro[0][2] == tabuleiro[2][0] && tabuleiro[0][2] != ' '));
    }

    public boolean haEmpate() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tabuleiro[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        JogoDaVelhaJava jogo = new JogoDaVelhaJava();
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Bem-vindo ao Jogo da Velha!");
            
            while (true) {
                jogo.mostrarTabuleiro();
                System.out.println("É a vez do jogador " + jogo.jogadorAtual + ". Digite a linha e coluna (0-2): ");
                int linha = scanner.nextInt();
                int coluna = scanner.nextInt();
                
                jogo.fazerJogada(linha, coluna);
                
                if (jogo.haVencedor()) {
                    jogo.mostrarTabuleiro();
                    System.out.println("                                E         ");
                    System.out.println("                               E E        ");
                    System.out.println("       V       V  OOO   CCCC  EEEEE       ");
                    System.out.println("        V     V  O   O C     E            ");
                    System.out.println("         V   V   O   O C      EEEEE       ");
                    System.out.println("          V V    O   O C     E            ");
                    System.out.println("           V      OOO   CCCC  EEEEE       ");
                    System.out.println(" GGGG      A     N   N H   H  OOOO  U    U");
                    System.out.println("G         A A    NN  N H   H O    O U    U");
                    System.out.println("GGGGG    AAAAA   N N N HHHHH O    O U    U");
                    System.out.println("G    G  A     A  N  NN H   H O    O U    U");
                    System.out.println(" GGGG  A       A N   N H   H  OOOO   UUUU ");
                    System.out.println("A CATEC agradece a sua jogatina!");
                    break;
                }
                
                if (jogo.haEmpate()) {
                    jogo.mostrarTabuleiro();
                    System.out.println("O jogo terminou em empate!");
                    System.out.println("A CATEC agradece a sua jogatina!");
                    break;
                }
            }
        }
    }
}