import java.util.*;

public class CompleteSearch {

    // check board
    public static void printBoard(String[][] board) {
        for (String[] x : board) {
            for (String y : x) {
                System.out.print(y + " ");
            }
            System.out.println();
        }
        System.out.println("-----------------");
    }

    public static int countBalls(String[][] board){
        int numBalls = 0;
        int i, j;
        for (i = 0; i < 5; i++) { // for each row
            for (j = 0; j < 9; j++) { // for each col
                if (board[i][j].equals("o")) { // for each ball in the board
                    numBalls++;
                }
            }
        }
        return numBalls;
    }

    // checks if a move is valid
    public static boolean isValid(String[][] board, int[] pos, String dir) { //pos = [row,col] of ball
        try {
            if (dir.equals("up")) {
                if (board[pos[0] - 1][pos[1]].equals("o")) { // if there is a ball above
                    if (board[pos[0] - 2][pos[1]].equals(".")) { // if there is an empty space after the ball
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }

            if (dir.equals("down")) {
                if (board[pos[0] + 1][pos[1]].equals("o")) { // if there is a ball under
                    if (board[pos[0] + 2][pos[1]].equals(".")) { // if there is an empty space after the ball
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }

            if (dir.equals("right")) {
                if (board[pos[0]][pos[1] + 1].equals("o")) { // if there is a ball to the right
                    if (board[pos[0]][pos[1] + 2].equals(".")) { // if there is an empty space after the ball
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }

            if (dir.equals("left")) {
                if (board[pos[0]][pos[1] - 1].equals("o")) { // if there is a ball to the left
                    if (board[pos[0]][pos[1] - 2].equals(".")) { // if there is an empty space after the ball
                        return true;
                    }
                }
            }
            return false;
        } catch (Exception e) { // index out of bounds (index not on board)
            return false;
        }
    }

    // move the ball at position pos in direction dir
    public static String[][] move(String[][] board, int[] pos, String dir) {
        int x = pos[0]; // pos row
        int y = pos[1]; // pos col
        board[x][y] = "."; // remove ball from initial position
        if (dir.equals("up")) {
            board[x - 1][y] = "."; // remove ball jumped over
            board[x - 2][y] = "o"; // place ball at final position

        } else if (dir.equals("down")) {
            board[x + 1][y] = ".";
            board[x + 2][y] = "o";

        } else if (dir.equals("right")) {
            board[x][y + 1] = ".";
            board[x][y + 2] = "o";

        } else if (dir.equals("left")) {
            board[x][y - 1] = ".";
            board[x][y - 2] = "o";
        }
        return board;
    }

    public static String[][] undoMove(String[][] board, int[] pos, String dir) {
        int x = pos[0]; // pos row
        int y = pos[1]; // pos col
        board[x][y] = "o"; // replace ball at initial position
        if (dir.equals("up")) {
            board[x - 1][y] = "o"; // replace ball jumped over
            board[x - 2][y] = "."; // remove ball from final position

        } else if (dir.equals("down")) {
            board[x + 1][y] = "o";
            board[x + 2][y] = ".";

        } else if (dir.equals("right")) {
            board[x][y + 1] = "o";
            board[x][y + 2] = ".";

        } else if (dir.equals("left")) {
            board[x][y - 1] = "o";
            board[x][y - 2] = ".";
        }
        return board;
    }

    public static int solve(String[][] board, int minBalls) { // recursive function to solve the board
        String[] directions = {"up", "down", "right", "left"}; // keep array of possible moves

        int i, j; // counters
        for (i = 0; i < 5; i++) { // for each row
            for (j = 0; j < 9; j++) { // for each col
                if (board[i][j].equals("o")) { // for each ball in the board
                    int[] pos = {i, j};
                    for (String dir : directions) { // for each direction
                        if (isValid(board, pos, dir)) {
                            board = move(board, pos, dir);
                            int numBalls = countBalls(board);
                            if (numBalls < minBalls){
                                minBalls = numBalls;
                            }
                            minBalls = solve(board, minBalls);
                            board = undoMove(board, pos, dir);
                        }
                    }
                }
            }
        }
        return minBalls;
    }

    public static int[] game(String[][] board) {
        // compute initial # balls on board
        int initialBalls = countBalls(board);

        // compute final # balls on board
        int finalBalls = solve(board, initialBalls);

        int moves = initialBalls - finalBalls;

        int[] result = {finalBalls, moves};

        return result;
    }

    // testing
    public static void main(String[] args) {
        String[][] board1 = {
                {"#", "#", "#", ".", "o", ".", "#", "#", "#"},
                {".", ".", "o", ".", ".", ".", ".", "o", "."},
                {".", ".", ".", ".", ".", "o", ".", ".", "."},
                {".", ".", ".", ".", ".", ".", "o", ".", "."},
                {"#", "#", "#", ".", ".", ".", "#", "#", "#"},
        };

        String[][] board2 = {
                {"#", "#", "#", ".", "o", ".", "#", "#", "#"},
                {".", ".", ".", "o", "o", ".", "o", ".", "."},
                {".", ".", "o", ".", ".", ".", "o", ".", "."},
                {"o", ".", ".", "o", ".", ".", ".", ".", "."},
                {"#", "#", "#", ".", ".", ".", "#", "#", "#"},
        };

        String[][] board3 = {
                {"#", "#", "#", ".", ".", ".", "#", "#", "#"},
                {"o", ".", "o", ".", "o", ".", ".", ".", "o"},
                {".", ".", "o", ".", ".", ".", "o", ".", "."},
                {".", ".", ".", ".", ".", ".", "o", ".", "."},
                {"#", "#", "#", ".", ".", ".", "#", "#", "#"},
        };

        String[][] boardDefault = {
                {"#", "#", "#", ".", ".", ".", "#", "#", "#"},
                {".", ".", ".", ".", ".", ".", ".", ".", "."},
                {".", ".", ".", ".", ".", ".", ".", ".", "."},
                {".", ".", ".", ".", ".", ".", ".", ".", "."},
                {"#", "#", "#", ".", ".", ".", "#", "#", "#"},
        };

        int[] result = game(board3);
        for (int x: result){
            System.out.println(x);
        }


    }

}