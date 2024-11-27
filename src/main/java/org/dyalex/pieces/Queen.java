package org.dyalex.pieces;

import org.dyalex.ChessBoard;

public class Queen extends ChessPiece {
    public Queen(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return this.color;
    }

    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        ChessPiece target = chessBoard.board[toLine][toColumn];
        int rowDiff = Math.abs(line - toLine);
        int colDiff = Math.abs(column - toColumn);

        if (rowDiff == colDiff) {
            int rowStep = toLine > line ? 1 : -1;
            int colStep = toColumn > column ? 1 : -1;
            int steps = rowDiff - 1; // Number of squares to check for obstruction

            for (int i = 1; i <= steps; i++) {
                if (chessBoard.board[line + i * rowStep][column + i * colStep] != null) {
                    return false; // There's a piece in the way
                }
            }
            return target == null || !target.getColor().equals(this.color);
        } else if (line == toLine) {
            int columnStart = Math.min(column, toColumn) + 1;
            int columnEnd = Math.max(column, toColumn);
            for (int col = columnStart; col < columnEnd; col++) {
                if (chessBoard.board[line][col] != null) {
                    return false; // There's a piece in the way
                }
            }
            return target == null || !target.getColor().equals(this.color);
        } else if (column == toColumn) {
            int rowStart = Math.min(line, toLine) + 1;
            int rowEnd = Math.max(line, toLine);
            for (int row = rowStart; row < rowEnd; row++) {
                if (chessBoard.board[row][column] != null) {
                    return false; // There's a piece in the way
                }
            }
            return target == null || !target.getColor().equals(this.color);
        } else {
            return false;
        }
    }

    public String getSymbol() {
        return "Q";
    }
}
