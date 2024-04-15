public class Board {
    public enum wincondition {
        playing, again, xwin, owin, tie
    }

    private static int ROW = 3;
    private static int COL = 3;

    private char[][] board = new char[ROW][COL];

    public void clearBoard() {
        for (int i = 0; i < ROW; ++i) {
            for (int j = 0; j < COL; ++j) {
                board[i][j] = ' ';
            }
        }
        turn = x;
        win = false;
    }

    public void display() {
        for (int j = 0; j < ROW; ++j)
        {
            System.out.printf("| ");
            for (int i = 0; i < COL; ++i)
            {
                System.out.printf(String.valueOf(board[j][i]));
                System.out.printf(" | ");
            }
            System.out.println("");
            System.out.println("-------------");
        }

        if (win) return;

        if (turn == x)
        {
            System.out.println("It is Xs turn!");
        }

        else {
            System.out.println("It is Os turn!");
        }
    }

    private static int x = 0;
    private static int o = 1;

    private int turn = x;
    private boolean win = false;

    private boolean isValidMove(int px, int py) {
        return board[py][px] == ' ';
    }

    private boolean isColWin() {
        for (int x = 0; x < COL; ++x) {
            char first = board[0][x];
            if (first == ' ') continue;

            boolean equal = true;
            for (int y = 1; y < ROW; ++y) {
                equal &= board[y][x] == first;
            }

            if (equal) return true;
        }

        return false;
    }

    private boolean isRowWin() {
        for (int y = 0; y < ROW; ++y) {
            char first = board[y][0];
            if (first == ' ') continue;

            boolean equal = true;
            for (int x = 1; x < COL; ++x) {
                equal &= board[y][x] == first;
            }

            if (equal) return true;
        }

        return false;
    }

    private boolean isDiagWin() {
        if (board[1][1] == ' ') return false;

        char diagOne = board[0][0];
        char diagTwo = board[0][ROW - 1];

        if (board[1][1] == diagOne && board[2][2] == diagOne) return true;
        if (board[1][1] == diagTwo && board[2][0] == diagTwo) return true;

        return false;
    }

    private boolean isWin() {
        return isColWin() || isRowWin() || isDiagWin();
    }

    private boolean isTie() {
        for (int x = 0; x < ROW; ++x) {
            for (int y = 0; y < COL; ++y) {
                if (board[y][x] == ' ') return false;
            }
        }

        return true;
    }

    public wincondition next_move(int px, int py)
    {
        if (!isValidMove(px, py)) return wincondition.again;

        board[py][px] = turn == x ? 'X' : 'O';

        wincondition thingy = wincondition.playing;
        if (isWin())
        {
            if (turn == x) thingy = wincondition.xwin;
            else thingy = wincondition.owin;

            win = true;
            display();
        }

        else if (isTie())
        {
            thingy = wincondition.tie;
        }

        turn = turn == x ? o : x;

        return thingy;
    }
}
