import java.util.List;

/**
 * Plays the counter to the opponent's most frequent move.
 */
public class FrequencyBot implements RockPaperScissorsPlayer {
    private int[] counts;

    public FrequencyBot() {
        this.counts = new int[Move.values().length];
    }

    @Override
    public Move makeMove(List<Move> previousMoves) {
        if (previousMoves.isEmpty()) {
            return Move.random();
        }

        // Record opponent's previous move
        int moveIndex = previousMoves.get(previousMoves.size() - 1).val();
        this.counts[moveIndex]++;

        // Find the opponent's move frequent move
        int indexOfMost = 0;
        for (int i = 1; i < this.counts.length; i++) {
            if (this.counts[i] > this.counts[indexOfMost]) {
                indexOfMost = i;
            }
        }

        // Play the counter to that move
        return Move.values()[indexOfMost].getCounter();
    }
}
