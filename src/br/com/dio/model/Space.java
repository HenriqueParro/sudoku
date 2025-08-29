package br.com.dio.model;

/**
 * Representa uma célula do tabuleiro de Sudoku.
 * Pode ser fixa (número inicial) ou editável pelo jogador.
 */
public class Space {
    private int value;         // valor da célula (0 = vazio)
    private final boolean fixed; // true se for número inicial do tabuleiro

    public Space(int value, boolean fixed) {
        this.value = value;
        this.fixed = fixed;
    }

    /** Retorna o valor da célula (0 significa vazio) */
    public int getValue() {
        return value;
    }

    /** Define um valor na célula (não altera se for fixa) */
    public void setValue(int value) {
        if (!fixed) {
            this.value = value;
        }
    }

    /** Retorna se a célula é fixa (não pode ser alterada pelo jogador) */
    public boolean isFixed() {
        return fixed;
    }

    /** Verifica se a célula está vazia */
    public boolean isEmpty() {
        return value == 0;
    }

    @Override
    public String toString() {
        return isEmpty() ? "." : String.valueOf(value);
    }
}
