package br.com.dio;

import br.com.dio.game.SudokuGame;
import br.com.dio.model.Board;
import br.com.dio.model.BoardTemplate;
import br.com.dio.model.GameStatusEnum;
import br.com.dio.model.Space;

import java.util.List;
import java.util.Scanner;

/**
 * Classe principal: responsável por interagir com o usuário via console.
 */
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Cria o tabuleiro inicial a partir do template
        List<List<Space>> initialBoard = BoardTemplate.createBoard();
        Board board = new Board(initialBoard);
        SudokuGame game = new SudokuGame(board);

        boolean running = true;

        System.out.println("=== Bem-vindo ao Sudoku ===");

        while (running) {
            System.out.println("\nMenu:");
            System.out.println("1. Ver tabuleiro");
            System.out.println("2. Inserir número");
            System.out.println("3. Remover número");
            System.out.println("4. Limpar tabuleiro");
            System.out.println("5. Verificar status");
            System.out.println("6. Resolver automaticamente");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            int option = sc.nextInt();

            switch (option) {
                case 1 -> game.printBoard();

                case 2 -> {
                    System.out.print("Linha (0-8): ");
                    int row = sc.nextInt();
                    System.out.print("Coluna (0-8): ");
                    int col = sc.nextInt();
                    System.out.print("Número (1-9): ");
                    int val = sc.nextInt();
                    game.placeNumber(row, col, val);
                }

                case 3 -> {
                    System.out.print("Linha (0-8): ");
                    int row = sc.nextInt();
                    System.out.print("Coluna (0-8): ");
                    int col = sc.nextInt();
                    game.removeNumber(row, col);
                }

                case 4 -> {
                    game.clearBoard();
                    System.out.println("✅ Tabuleiro limpo.");
                }

                case 5 -> {
                    GameStatusEnum status = game.getStatus();
                    System.out.println("📌 Status atual: " + status);
                }

                case 6 -> {
                    if (game.solve()) {
                        System.out.println("✅ Sudoku resolvido automaticamente!");
                        game.printBoard();
                    } else {
                        System.out.println("⚠️ Não foi possível resolver este Sudoku.");
                    }
                }

                case 0 -> {
                    running = false;
                    System.out.println("👋 Obrigado por jogar!");
                }

                default -> System.out.println("⚠️ Opção inválida!");
            }
        }

        sc.close();
    }
}
