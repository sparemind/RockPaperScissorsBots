import java.util.List;
import java.util.Scanner;

/**
 * User interface for a human player.
 * <p>
 * Moves are read in through the console. 'R' for rock, 'P' for paper, 'S' for
 * scissors. The current win/loss/draw record along with a list of the opponents
 * move recent moves is printed before each move prompt. This info line also
 * begins with a +, -, or 0, corresponding to having won, lost, or drawn the
 * previous round.
 */
public class HumanPlayer extends RockPaperScissorsPlayer {
    private static final int HISTORY_LENGTH = 20;

    private Scanner console;
    private Move move;
    private int wins;
    private int losses;
    private int draws;

    public HumanPlayer() {
        this.console = new Scanner(System.in);
    }

    @Override
    public Move makeMove(List<Move> previousMoves) {
        if (!previousMoves.isEmpty()) {
            // Update records
            Move opponentLast = previousMoves.get(previousMoves.size() - 1);
            if (this.move.beats(opponentLast)) {
                // Win
                this.wins++;
                System.out.print("+ ");
            } else if (this.move == opponentLast) {
                // Draw
                this.draws++;
                System.out.print("0 ");
            } else {
                // Loss
                this.losses++;
                System.out.print("- ");
            }

            System.out.printf("[%d/%d/%d] ", this.wins, this.losses, this.draws);
            System.out.print("Opponents last " + HISTORY_LENGTH + " moves: ");
            for (int i = Math.max(0, previousMoves.size() - HISTORY_LENGTH); i < previousMoves.size(); i++) {
                System.out.print(previousMoves.get(i).toString().charAt(0));
            }
            System.out.println();
        }

        this.move = null;
        while (this.move == null) {
            System.out.print("Next move (R/P/S): ");
            char input = this.console.next().toUpperCase().charAt(0);
            if (input == 'R') {
                this.move = Move.ROCK;
            } else if (input == 'P') {
                this.move = Move.PAPER;
            } else if (input == 'S') {
                this.move = Move.SCISSORS;
            }
        }
        return this.move;
    }
}
