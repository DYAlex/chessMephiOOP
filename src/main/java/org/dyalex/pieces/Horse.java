package org.dyalex.pieces;

import org.dyalex.ChessBoard;

public class Horse extends ChessPiece {

    public Horse(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return this.color;
    }

    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        int rowDiff = Math.abs(line - toLine);
        int colDiff = Math.abs(column - toColumn);
        ChessPiece target = chessBoard.board[toLine][toColumn];
        // Check for the 'L' shaped move pattern
        boolean isValidLMove = (rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2);

        return isValidLMove && (target == null || !target.getColor().equals(this.color));
    }

    public String getSymbol() {
        return "H";
    }
}
