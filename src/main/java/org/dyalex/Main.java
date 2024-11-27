package org.dyalex;

import org.dyalex.pieces.*;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    private static final String WHITE = "White";
    private static final String BLACK = "Black";

    public static ChessBoard buildBoard() {
        ChessBoard board = new ChessBoard(WHITE);

        board.board[0][0] = new Rook(WHITE);
        board.board[0][1] = new Horse(WHITE);
        board.board[0][2] = new Bishop(WHITE);
        board.board[0][3] = new Queen(WHITE);
        board.board[0][4] = new King(WHITE);
        board.board[0][5] = new Bishop(WHITE);
        board.board[0][6] = new Horse(WHITE);
        board.board[0][7] = new Rook(WHITE);
        board.board[1][0] = new Pawn(WHITE);
        board.board[1][1] = new Pawn(WHITE);
        board.board[1][2] = new Pawn(WHITE);
        board.board[1][3] = new Pawn(WHITE);
        board.board[1][4] = new Pawn(WHITE);
        board.board[1][5] = new Pawn(WHITE);
        board.board[1][6] = new Pawn(WHITE);
        board.board[1][7] = new Pawn(WHITE);

        board.board[7][0] = new Rook(BLACK);
        board.board[7][1] = new Horse(BLACK);
        board.board[7][2] = new Bishop(BLACK);
        board.board[7][3] = new Queen(BLACK);
        board.board[7][4] = new King(BLACK);
        board.board[7][5] = new Bishop(BLACK);
        board.board[7][6] = new Horse(BLACK);
        board.board[7][7] = new Rook(BLACK);
        board.board[6][0] = new Pawn(BLACK);
        board.board[6][1] = new Pawn(BLACK);
        board.board[6][2] = new Pawn(BLACK);
        board.board[6][3] = new Pawn(BLACK);
        board.board[6][4] = new Pawn(BLACK);
        board.board[6][5] = new Pawn(BLACK);
        board.board[6][6] = new Pawn(BLACK);
        board.board[6][7] = new Pawn(BLACK);
        return board;
    }
    public static void main(String[] args) {
        ChessBoard board = buildBoard();

        Scanner scanner = new Scanner(System.in);
        System.out.println("""
               Чтобы проверить игру надо вводить команды:
               'exit' - для выхода
               'replay' - для перезапуска игры
               'castling0' или 'castling7' - для рокировки по соответствующей линии
               'move 1 1 2 3' - для передвижения фигуры с позиции 1 1 на 2 3(поле это двумерный массив от 0 до 7)
               Проверьте могут ли фигуры ходить друг сквозь друга, корректно ли съедают друг друга, можно ли поставить шах и сделать рокировку?""");
        System.out.println();
        board.printBoard();
        while (true) {
            String s = scanner.nextLine();
            if (s.equals("exit")) break;
            else if (s.equals("replay")) {
                System.out.println("Заново");
                board = buildBoard();
                board.printBoard();
            } else {
                if (s.equals("castling0")) {
                    if (board.castling0()) {
                        System.out.println("Рокировка удалась");
                        board.printBoard();
                    } else {
                        System.out.println("Рокировка не удалась");
                    }
                } else if (s.equals("castling7")) {
                    if (board.castling7()) {
                        System.out.println("Рокировка удалась");
                        board.printBoard();
                    } else {
                        System.out.println("Рокировка не удалась");
                    }
                } else if (s.contains("move")) {
                    String[] a = s.split(" ");
                    System.out.println(Arrays.toString(a));
                    try {
                        int line = Integer.parseInt(a[1]);
                        int column = Integer.parseInt(a[2]);
                        int toLine = Integer.parseInt(a[3]);
                        int toColumn = Integer.parseInt(a[4]);

                        if (board.moveToPosition(line, column, toLine, toColumn)) {
                            System.out.println("Успешно передвинулись");
                            board.printBoard();
                        } else System.out.println("Передвижение не удалось");
                    } catch (Exception e) {
                        System.out.println("Вы что-то ввели не так, попробуйте ещё раз");
                    }

                }
            }
        }
    }
}