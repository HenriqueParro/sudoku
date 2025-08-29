package br.com.dio.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Classe utilitária para gerar tabuleiros iniciais de Sudoku.
 */
public class BoardTemplate {

    /**
     * Cria um tabuleiro inicial padrão (nível fácil).
     */
    public static List<List<Space>> createBoard() {
        int[][] puzzle = EASY; // aqui você pode trocar por MEDIUM ou HARD
        return convertToBoard(puzzle);
    }

    /**
     * Converte uma matriz de inteiros em uma matriz de Spaces.
     */
    private static List<List<Space>> convertToBoard(int[][] puzzle) {
        List<List<Space>> board = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            List<Space> row = new ArrayList<>();
            for (int j = 0; j < 9; j++) {
                int value = puzzle[i][j];
                boolean fixed = value != 0;
                row.add(new Space(value, fixed));
            }
            board.add(row);
        }
        return board;
    }

    // =============================
    // Templates de puzzles (0 = vazio)
    // =============================

    public static final int[][] EASY = {
            {5, 3, 0, 0, 7, 0, 0, 0, 0},
            {6, 0, 0, 1, 9, 5, 0, 0, 0},
            {0, 9, 8, 0, 0, 0, 0, 6, 0},
            {8, 0, 0, 0, 6, 0, 0, 0, 3},
            {4, 0, 0, 8, 0, 3, 0, 0, 1},
            {7, 0, 0, 0, 2, 0, 0, 0, 6},
            {0, 6, 0, 0, 0, 0, 2, 8, 0},
            {0, 0, 0, 4, 1, 9, 0, 0, 5},
            {0, 0, 0, 0, 8, 0, 0, 7, 9}
    };

    public static final int[][] MEDIUM = {
            {0, 0, 0, 2, 6, 0, 7, 0, 1},
            {6, 8, 0, 0, 7, 0, 0, 9, 0},
            {1, 9, 0, 0, 0, 4, 5, 0, 0},
            {8, 2, 0, 1, 0, 0, 0, 4, 0},
            {0, 0, 4, 6, 0, 2, 9, 0, 0},
            {0, 5, 0, 0, 0, 3, 0, 2, 8},
            {0, 0, 9, 3, 0, 0, 0, 7, 4},
            {0, 4, 0, 0, 5, 0, 0, 3, 6},
            {7, 0, 3, 0, 1, 8, 0, 0, 0}
    };

    public static final int[][] HARD = {
            {0, 0, 0, 0, 0, 0, 0, 0, 2},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 1, 0, 9, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 5, 0, 0},
            {0, 0, 0, 2, 0, 0, 0, 6, 0},
            {0, 0, 0, 0, 0, 7, 0, 0, 0},
            {0, 0, 6, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 4, 1, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0}
    };
}
