package org.dyalex.pieces;

import org.dyalex.ChessBoard;

public class Rook extends ChessPiece {
    public Rook(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return this.color;
    }

    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        ChessPiece target = chessBoard.board[toLine][toColumn];
        if (line == toLine) {
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
            return false; // Not a valid rook move (not straight line)
        }
    }

    public String getSymbol() {
        return "R";
    }
}
