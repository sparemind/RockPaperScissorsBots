import java.util.List;
import java.util.Random;

/**
 * Plays the counter to a random move previously made by the opponent.
 */
public class BiasBot extends RockPaperScissorsPlayer {
    private Random rand;

    public BiasBot() {
        this.rand = new Random();
    }

    @Override
    public Move makeMove(List<Move> previousMoves) {
        if (previousMoves.isEmpty()) {
            return Move.random();
        }

        int moveIndex = this.rand.nextInt(previousMoves.size());
        return previousMoves.get(moveIndex).getCounter();
    }
}
