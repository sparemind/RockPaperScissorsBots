import java.util.List;
import java.util.Random;

/**
 * Chooses moves randomly, but with a bias towards moves chosen less frequently,
 * ultimately attempting to have a perfectly flat move frequency distribution.
 */
public class FlatBot extends RockPaperScissorsPlayer {
    private int[] counts; // Count of each move type made
    private int moves; // Total number of moves made
    private Random rand;

    public FlatBot() {
        this.counts = new int[Move.values().length];
        this.moves = -1; // First move will be move 0
        this.rand = new Random();
    }

    @Override
    public Move makeMove(List<Move> previousMoves) {
        this.moves++;
        Move move = Move.random();

        if (!previousMoves.isEmpty()) {
            // Calculate what the probability of selecting each move should be
            double[] probabilities = new double[this.counts.length];
            for (int i = 0; i < probabilities.length; i++) {
                probabilities[i] = (1 - (double) this.counts[i] / this.moves) / 2;
            }

            // Roulette wheel selection of the move based on the probabilities
            double r = this.rand.nextDouble();
            double sum = 0;
            for (int i = 0; i < probabilities.length; i++) {
                sum += probabilities[i];
                if (r <= sum) {
                    move = Move.values()[i];
                    break;
                }
            }
        }

        // Update count
        this.counts[move.val()]++;
        return move;
    }
}
