package org.dyalex.pieces;

import org.dyalex.ChessBoard;

public class Pawn extends ChessPiece {

    public Pawn(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return this.color;
    }

    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        int forwardDirection = color.equals("White") ? 1 : -1;
        int rowDiff = (toLine - line) * forwardDirection;
        int colDiff = column - toColumn;

        // Initial two square movement
        if (colDiff == 0 && rowDiff == 2 && isCheck()
                && chessBoard.board[toLine][toColumn] == null) {
            int middleRow = line + forwardDirection;
            if (chessBoard.board[middleRow][column] == null) {
                this.unCheck();
                return true;
            }
        }

        if (canAttack(chessBoard, rowDiff, colDiff, toLine, toColumn)
                || colDiff == 0 && rowDiff == 1 && chessBoard.board[toLine][toColumn] == null) {
            this.unCheck();
            return true;
        }
        return false;
    }

    public String getSymbol() {
        return "P";
    }

    private boolean canAttack(ChessBoard chessBoard, int rowDiff, int colDiff, int toLine, int toColumn) {
        return Math.abs(colDiff) == 1 && rowDiff == 1 && chessBoard.board[toLine][toColumn] != null &&
                !chessBoard.board[toLine][toColumn].getColor().equals(this.color);
    }
}
