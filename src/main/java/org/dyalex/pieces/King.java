package org.dyalex.pieces;

import org.dyalex.ChessBoard;

public class King extends ChessPiece {
    public King(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return this.color;
    }

    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        int rowDiff = Math.abs(line - toLine);
        int colDiff = Math.abs(column - toColumn);

        boolean isOneSquareMove = rowDiff <= 1 && colDiff <= 1 && !(rowDiff == 0 && colDiff == 0);

        if (!isOneSquareMove) {
            return false; // Move is not within one square.
        }

        ChessPiece target = chessBoard.board[toLine][toColumn];

        return !isUnderAttack(chessBoard, toLine, toColumn) && (target == null || !target.getColor().equals(this.color));
    }

    public String getSymbol() {
        return "K";
    }

    public boolean isUnderAttack(ChessBoard board, int line, int column) {
        for (int row = 0; row < board.board.length; row++) {
            for (int col = 0; col < board.board[row].length; col++) {
                ChessPiece piece = board.board[row][col];
                if (piece != null && !piece.getColor().equals(this.color)) {
                    if (piece.canMoveToPosition(board, row, col, line, column)) {
                        return true; // An opposing piece can capture the king
                    }
                }
            }
        }
        return false;
    }
}
