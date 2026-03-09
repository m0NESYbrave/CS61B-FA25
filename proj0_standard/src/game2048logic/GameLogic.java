package game2048logic;

import game2048rendering.Side;
import static game2048logic.MatrixUtils.rotateLeft;
import static game2048logic.MatrixUtils.rotateRight;

/**
 * @author  Josh Hug
 */
public class GameLogic {
    /** Moves the given tile up as far as possible, subject to the minR constraint.
     *
     * @param board the current state of the board
     * @param r     the row number of the tile to move up
     * @param c -   the column number of the tile to move up
     * @param minR  the minimum row number that the tile can land in, e.g.
     *              if minR is 2, the moving tile should move no higher than row 2.
     * @return      if there is a merge, returns the 1 + the row number where the merge occurred.
     *              if no merge occurs, then return 0.
     */
    public static int moveTileUpAsFarAsPossible(int[][] board, int r, int c, int minR) {
        int temp = board[r][c];
        int cnt = 0; // number of tiles between the top row (including it) and row r
        for (int row = 0; row < r; row++) {
            if (board[row][c] != 0) {
                cnt += 1;
            }
        }
        // without temp
        //board[cnt][c] = board[r][c];
        //if (cnt != r) {
        //    board[r][c] = 0;
        //}

        // Move first
        // I assume that when we move a whole column, we move the first tile in that direction first
        board[r][c] = 0;
        if (cnt >= minR) {
            board[cnt][c] = temp;
        } else {
            board[minR][c] = temp;
        }

        // Merge
        // With the condition of minR, we don't need cnt != 0 to discard top tile now
        if (cnt - 1 >= minR && board[cnt - 1][c] == temp) {
            board[cnt - 1][c] += temp;
            board[cnt][c] = 0;
            return cnt;
        }
        return 0;
    }

    /**
     * Modifies the board to simulate the process of tilting column c
     * upwards.
     *
     * @param board     the current state of the board
     * @param c         the column to tilt up.
     */
    public static void tiltColumn(int[][] board, int c) {
        // Indicate the row right after the merge happens, which represents minR of the current tile.
        // 0 means no merge.
        // Like 2, 2, 4, 4: When two 2s merge, afterMerge is 1, so the first 4 can only get to row 1.
        // Then the other 4 comes, afterMerge is 0 so no merge, so merge two 4s.
        int afterMerge = 0;
        for (int r = 0; r < board.length; r++) {
            if (board[r][c] != 0) {
                afterMerge = moveTileUpAsFarAsPossible(board, r, c, afterMerge);
            }
        }
    }

    /**
     * Modifies the board to simulate tilting all columns upwards.
     *
     * @param board     the current state of the board.
     */
    public static void tiltUp(int[][] board) {
        for (int c = 0; c < board.length; c++) {
            tiltColumn(board, c);
        }
    }

    /**
     * Modifies the board to simulate tilting the entire board to
     * the given side.
     *
     * @param board the current state of the board
     * @param side  the direction to tilt
     */
    public static void tilt(int[][] board, Side side) {
        if (side == Side.EAST) {
            rotateLeft(board);
            tiltUp(board);
            rotateRight(board);
        } else if (side == Side.WEST) {
            rotateRight(board);
            tiltUp(board);
            rotateLeft(board);
        } else if (side == Side.SOUTH) {
            rotateLeft(board);
            rotateLeft(board);
            tiltUp(board);
            rotateRight(board);
            rotateRight(board);
        } else {
            tiltUp(board);
        }
    }
}
