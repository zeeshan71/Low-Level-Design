
import Models.Board;
import Models.Player;
import Models.PlayingPieceO;
import Models.PlayingPieceType;
import Models.PlayingPieceX;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Game {
    Deque<Player> players;
    Board gameboard;

    public Game() {
    }

    void initalizeGame(int n) {
        this.players = new LinkedList();
        PlayingPieceX crossType = new PlayingPieceX();
        Player player1 = new Player("Player1", crossType);
        PlayingPieceO zeroType = new PlayingPieceO();
        Player player2 = new Player("Player2", zeroType);
        this.players.add(player1);
        this.players.add(player2);
        this.gameboard = new Board(n);
    }

    public String startGame() {
        boolean noWinner = true;

        while(noWinner) {
            Player playerTurn = (Player)this.players.removeFirst();
            this.gameboard.printBoard();
            List<HashMap<Integer, Integer>> freeCells = this.gameboard.getFreeCells();
            if (freeCells.isEmpty()) {
                noWinner = false;
            } else {
                System.out.print("Player:" + playerTurn.name + " Enter row,column: ");
                Scanner inputScanner = new Scanner(System.in);
                String s = inputScanner.nextLine();
                String[] values = s.split(",");
                int inputRow = Integer.parseInt(values[0]);
                int inputCol = Integer.parseInt(values[1]);
                boolean pieceAdded = this.gameboard.addPiece(inputRow, inputCol, playerTurn.playingPiece);
                if (!pieceAdded) {
                    System.out.println("Invalid Selection, please try again: ");
                    this.players.addFirst(playerTurn);
                } else {
                    this.players.addLast(playerTurn);
                    boolean isWinner = this.isWinner(inputRow, inputCol, playerTurn.playingPiece.pieceType);
                    if (isWinner) {
                        return playerTurn.name;
                    }
                }
            }
        }

        return "It's a Tie";
    }

    public boolean isWinner(int row, int col, PlayingPieceType pieceType) {
        boolean rowMatch = true;
        boolean colMatch = true;
        boolean diagMatch = true;
        boolean antiDiagMatch = true;

        int i;
        for(i = 0; i < this.gameboard.size; ++i) {
            if (this.gameboard.board[row][i] == null || this.gameboard.board[row][i].pieceType != pieceType) {
                rowMatch = false;
                break;
            }
        }

        for(i = 0; i < this.gameboard.size; ++i) {
            if (this.gameboard.board[col][i] == null || this.gameboard.board[col][i].pieceType != pieceType) {
                colMatch = false;
                break;
            }
        }

        i = 0;

        int j;
        for(j = 0; i < this.gameboard.size; ++j) {
            if (this.gameboard.board[i][j] == null || this.gameboard.board[i][j].pieceType != pieceType) {
                diagMatch = false;
                break;
            }

            ++i;
        }

        i = 0;

        for(j = this.gameboard.size - 1; i < this.gameboard.size; --j) {
            if (this.gameboard.board[i][j] == null || this.gameboard.board[i][j].pieceType != pieceType) {
                antiDiagMatch = false;
                break;
            }

            ++i;
        }

        return rowMatch || colMatch || diagMatch || antiDiagMatch;
    }
}
