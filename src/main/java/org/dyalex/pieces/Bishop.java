package org.dyalex.pieces;

import org.dyalex.ChessBoard;

public class Bishop extends ChessPiece {
    public Bishop(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return this.color;
    }

    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        int rowDiff = Math.abs(line - toLine);
        int colDiff = Math.abs(column - toColumn);

        if (rowDiff != colDiff) {
            return false; // Move is not diagonal
        }

        int rowStep = toLine > line ? 1 : -1;
        int colStep = toColumn > column ? 1 : -1;
        int steps = rowDiff - 1; // Number of squares to check for obstruction

        for (int i = 1; i <= steps; i++) {
            if (chessBoard.board[line + i * rowStep][column + i * colStep] != null) {
                return false; // There's a piece in the way
            }
        }

        ChessPiece target = chessBoard.board[toLine][toColumn];

        return target == null || !target.getColor().equals(this.color);
    }

    public String getSymbol() {
        return "B";
    }
}
