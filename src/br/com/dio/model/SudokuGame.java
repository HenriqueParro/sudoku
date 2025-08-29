package br.com.dio.game;

import br.com.dio.model.Board;
import br.com.dio.model.GameStatusEnum;
import br.com.dio.model.Space;

import java.util.List;

/**
 * Classe que encapsula toda a lógica do jogo de Sudoku.
 */
public class SudokuGame {

    private final Board board;

    public SudokuGame(Board board) {
        this.board = board;
    }

    /** Retorna o estado atual do jogo */
    public GameStatusEnum getStatus() {
        if (!board.isBoardValid()) {
            return GameStatusEnum.INVALID;
        }
        return board.getStatus();
    }

    /** Tenta inserir um valor em uma posição */
    public boolean placeNumber(int row, int col, int value) {
        if (row < 0 || row >= 9 || col < 0 || col >= 9 || value < 1 || value > 9) {
            System.out.println("⚠️ Movimento inválido: fora dos limites ou valor incorreto.");
            return false;
        }
        Space space = board.getSpaces().get(row).get(col);
        if (space.isFixed()) {
            System.out.println("⚠️ Não é possível alterar uma célula fixa.");
            return false;
        }
        space.setValue(value);

        if (!board.isBoardValid()) {
            System.out.println("⚠️ Movimento inválido: viola as regras do Sudoku.");
            space.setValue(0);
            return false;
        }
        return true;
    }

    /** Remove número de uma célula (se não for fixa) */
    public boolean removeNumber(int row, int col) {
        Space space = board.getSpaces().get(row).get(col);
        if (space.isFixed()) {
            System.out.println("⚠️ Não é possível remover de uma célula fixa.");
            return false;
        }
        space.setValue(0);
        return true;
    }

    /** Limpa todas as células editáveis */
    public void clearBoard() {
        board.clearBoard();
    }

    /** Imprime o tabuleiro formatado */
    public void printBoard() {
        board.printBoard();
    }

    /** Resolve automaticamente o Sudoku (backtracking) */
    public boolean solve() {
        return solveRecursive(board.getSpaces(), 0, 0);
    }

    /** Algoritmo de resolução recursiva */
    private boolean solveRecursive(List<List<Space>> spaces, int row, int col) {
        if (row == 9) return true; // terminou

        int nextRow = (col == 8) ? row + 1 : row;
        int nextCol = (col + 1) % 9;

        Space current = spaces.get(row).get(col);
        if (!current.isEmpty() && current.isFixed()) {
            return solveRecursive(spaces, nextRow, nextCol);
        }

        for (int num = 1; num <= 9; num++) {
            current.setValue(num);
            if (board.isBoardValid() && solveRecursive(spaces, nextRow, nextCol)) {
                return true;
            }
            current.setValue(0);
        }
        return false;
    }
}
