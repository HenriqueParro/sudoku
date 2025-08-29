package br.com.dio.model;

/**
 * Enum que representa o estado atual do jogo de Sudoku.
 */
public enum GameStatusEnum {
    NOT_STARTED,   // Jogo ainda não iniciado
    IN_PROGRESS,   // Jogo em andamento (parcialmente preenchido)
    COMPLETE,      // Jogo completo e válido
    INVALID;       // Jogo preenchido mas com erros
}
