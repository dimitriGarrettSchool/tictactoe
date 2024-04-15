import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Board board = new Board();
        board.clearBoard();

        Scanner scanner = new Scanner(System.in);

        while (true)
        {
            board.display();

            int x = SafeInput.getRangedInt(scanner, "What is your x value (1-3) > ", 1, 3);
            int y = SafeInput.getRangedInt(scanner, "What is your y value (1-3) > ", 1, 3);

            Board.wincondition move = board.next_move(x - 1, y - 1);

            if (move == Board.wincondition.again)
            {
                System.out.println("Invalid input!"); continue;
            }

            if (move != Board.wincondition.playing)
            {
                switch (move)
                {
                    case xwin:
                        System.out.println("X wins!"); break;
                    case owin:
                        System.out.println("O wins!"); break;
                    case tie:
                        System.out.println("It was a tie!"); break;
                }

                if (SafeInput.getYN(scanner, "Do you want to play again > ") == false)
                    return;

                board.clearBoard();
            }
        }
    }
}