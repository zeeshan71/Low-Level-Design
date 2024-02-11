package Models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Board {
    public int size;
    public PlayingPiece[][] board;

    public Board(int size) {
        this.size = size;
        this.board = new PlayingPiece[size][size];
    }

    public boolean addPiece(int row, int col, PlayingPiece playingPiece) {
        if (this.board[row][col] != null) {
            return false;
        } else {
            this.board[row][col] = playingPiece;
            return true;
        }
    }

    public List<HashMap<Integer, Integer>> getFreeCells() {
        List<HashMap<Integer, Integer>> freeCells = new ArrayList();

        for(int i = 0; i < this.board.length; ++i) {
            for(int j = 0; j < this.board[0].length; ++j) {
                if (this.board[i][j] == null) {
                    HashMap<Integer, Integer> map = new HashMap();
                    map.put(i, j);
                    freeCells.add(map);
                }
            }
        }

        return freeCells;
    }

    public void printBoard() {
        for(int i = 0; i < this.board.length; ++i) {
            for(int j = 0; j < this.board[0].length; ++j) {
                if (this.board[i][j] != null) {
                    PlayingPieceType playingpieceType = this.board[i][j].pieceType;
                    System.out.print(playingpieceType.name() + "   ");
                } else {
                    System.out.print("    ");
                }

                System.out.println(" | ");
            }

            System.out.println();
        }

    }
}
