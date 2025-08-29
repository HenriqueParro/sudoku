package br.com.dio.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static br.com.dio.model.GameStatusEnum.*;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 * Representa o tabuleiro de Sudoku (9x9).
 * Contém células do tipo Space, que podem ser fixas ou editáveis.
 */
public class Board {

    private final List<List<Space>> spaces;

    public Board(final List<List<Space>> spaces) {
        this.spaces = spaces;
    }

    public List<List<Space>> getSpaces() {
        return spaces;
    }

    /** Retorna o status atual do jogo */
    public GameStatusEnum getStatus() {
        if (spaces.stream().flatMap(Collection::stream).noneMatch(s -> !s.isFixed() && nonNull(s.getValue()))) {
            return NON_STARTED;
        }
        return spaces.stream().flatMap(Collection::stream).anyMatch(Space::isEmpty) ? INCOMPLETE : COMPLETE;
    }

    /** Checa se há erros (duplicatas em linhas, colunas ou blocos) */
    public boolean hasErrors() {
        if (getStatus() == NON_STARTED) return false;
        return !isBoardValid();
    }

    /** Valida se o tabuleiro segue as regras do Sudoku */
    public boolean isBoardValid() {
        return validateRows() && validateColumns() && validateBlocks();
    }

    private boolean validateRows() {
        for (List<Space> row : spaces) {
            List<Integer> values = row.stream()
                    .map(Space::getValue)
                    .filter(v -> v != 0)
                    .collect(Collectors.toList());
            if (hasDuplicates(values)) return false;
        }
        return true;
    }

    private boolean validateColumns() {
        for (int col = 0; col < 9; col++) {
            List<Integer> values = new ArrayList<>();
            for (int row = 0; row < 9; row++) {
                int value = spaces.get(row).get(col).getValue();
                if (value != 0) values.add(value);
            }
            if (hasDuplicates(values)) return false;
        }
        return true;
    }

    private boolean validateBlocks() {
        for (int blockRow = 0; blockRow < 3; blockRow++) {
            for (int blockCol = 0; blockCol < 3; blockCol++) {
                List<Integer> values = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        int value = spaces.get(blockRow * 3 + i).get(blockCol * 3 + j).getValue();
                        if (value != 0) values.add(value);
                    }
                }
                if (hasDuplicates(values)) return false;
            }
        }
        return true;
    }

    private boolean hasDuplicates(List<Integer> values) {
        return values.size() != values.stream().distinct().count();
    }

    /** Limpa todas as células não fixas */
    public void clearBoard() {
        spaces.forEach(row -> row.forEach(space -> {
            if (!space.isFixed()) space.setValue(0);
        }));
    }

    /** Exibe o tabuleiro formatado em blocos 3x3 */
    public void printBoard() {
        System.out.println("=== TABULEIRO ATUAL ===");
        for (int i = 0; i < 9; i++) {
            if (i % 3 == 0) System.out.println("+-------+-------+-------+");
            for (int j = 0; j < 9; j++) {
                if (j % 3 == 0) System.out.print("| ");
                int val = spaces.get(i).get(j).getValue();
                System.out.print(val == 0 ? ". " : val + " ");
            }
            System.out.println("|");
        }
        System.out.println("+-------+-------+-------+");
    }
}
