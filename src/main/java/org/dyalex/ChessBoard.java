package org.dyalex;

import org.dyalex.pieces.ChessPiece;
import org.dyalex.pieces.King;
import org.dyalex.pieces.Rook;

public class ChessBoard {
    private static final String WHITE = "White";
    private static final String BLACK = "Black";
    public ChessPiece[][] board = new ChessPiece[8][8]; // creating a field for game
    String nowPlayer;

    public ChessBoard(String nowPlayer) {
        this.nowPlayer = nowPlayer;
    }

    public String nowPlayerColor() {
        return this.nowPlayer;
    }

    public boolean moveToPosition(int startLine, int startColumn, int endLine, int endColumn) {
        if (checkPos(startLine) && checkPos(startColumn)) {

            if (!nowPlayer.equals(board[startLine][startColumn].getColor())) return false;

            if (board[startLine][startColumn].canMoveToPosition(this, startLine, startColumn, endLine, endColumn)) {
                board[endLine][endColumn] = board[startLine][startColumn]; // if piece can move, we moved a piece
                if (board[endLine][endColumn].isCheck()
                        && (board[endLine][endColumn] instanceof King
                        || board[endLine][endColumn] instanceof Rook)) {
                    board[endLine][endColumn].unCheck();
                }
                board[startLine][startColumn] = null; // set null to previous cell
                this.nowPlayer = this.nowPlayerColor().equals(WHITE) ? BLACK : WHITE;

                return true;
            } else return false;
        } else return false;
    }

    public void printBoard() {  //print board in console
        System.out.println("Turn " + nowPlayer);
        System.out.println();
        System.out.println("Player 2(Black)");
        System.out.println();
        System.out.println("\t0\t1\t2\t3\t4\t5\t6\t7");

        for (int i = 7; i > -1; i--) {
            System.out.print(i + "\t");
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == null) {
                    System.out.print(".." + "\t");
                } else {
                    System.out.print(board[i][j].getSymbol() + board[i][j].getColor().substring(0, 1).toLowerCase() + "\t");
                }
            }
            System.out.println();
            System.out.println();
        }
        System.out.println("Player 1(White)");
    }

    public boolean checkPos(int pos) {
        return pos >= 0 && pos <= 7;
    }

    public boolean castling0() {
        return castling(0, WHITE, BLACK);
    }

    public boolean castling7() {
        return castling(7, BLACK, WHITE);
    }

    private boolean castling(int row, String color, String opponent) {
        ChessPiece king = board[row][4];
        ChessPiece rook = board[row][7];
        if (!this.nowPlayerColor().equals(color)
                || !king.isCheck()
                || !rook.isCheck()
                || !king.getColor().equals(color)
                || !rook.getColor().equals(color)
                || board[row][5] != null
                || board[row][6] != null
                || !(king instanceof King)
                || !(rook instanceof Rook)
                || ((King) king).isUnderAttack(this, row, 7)) {
            return false;
        } else {
            board[row][7] = king;
            board[row][4] = rook;
            this.nowPlayer = opponent;
            return true;
        }
    }
}
