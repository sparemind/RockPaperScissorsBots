import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Analyzes the opponents move history to find the longest move sequence that
 * matches the most recent move sequence. Then looks at what they played next
 * and plays the counter to it.
 * <p>
 * Keeps track of how different "shifts" to its chosen move would have performed
 * against what the opponent chose, and applies the best.
 */
public class MetaBot extends RockPaperScissorsPlayer {
    private static final int SEARCH_DEPTH = 100;
    private static final double DECAY = 0.9;
    private static final double CHANGE = 0.1;

    private Move move;
    private Map<Integer, Double> shifts;

    public MetaBot() {
        this.move = Move.random();
        this.shifts = new HashMap<>();
        for (int i = 0; i < Move.values().length; i++) {
            this.shifts.put(i, 0.0);
        }
    }

    @Override
    public Move makeMove(List<Move> previousMoves) {
        if (previousMoves.isEmpty()) {
            return this.move;
        }
        updateStrategy(previousMoves);

        // Most recent move
        int historyEnd = previousMoves.size() - 1;
        // How far back to search
        int searchDepth = Math.min(previousMoves.size(), SEARCH_DEPTH);
        // Length of the longest match sequence
        int longestMatch = 0;
        // Index of the next move played after the longest match sequence
        int indexOfNext = 0;

        // Search history for matches of increasing sequence length
        for (int i = 1; i < searchDepth; i++) {
            int high = historyEnd;
            int low = high - i;

            while (low > 0 && previousMoves.get(low) == previousMoves.get(high)) {
                high--;
                low--;
            }
            if (historyEnd - high > longestMatch) {
                longestMatch = historyEnd - high;
                indexOfNext = low + longestMatch + 1;
            }
        }

        this.move = previousMoves.get(indexOfNext).getCounter();
        int bestShift = 0;
        for (int shift : this.shifts.keySet()) {
            if (this.shifts.get(shift) > this.shifts.get(bestShift)) {
                bestShift = shift;
            }
        }
        return this.move.shift(bestShift);
    }

    private void updateStrategy(List<Move> previousMoves) {
        Move opponentsLast = previousMoves.get(previousMoves.size() - 1);
        for (int shift : this.shifts.keySet()) {
            this.shifts.put(shift, this.shifts.get(shift) * DECAY);
            Move shiftedMove = this.move.shift(shift);
            if (shiftedMove.beats(opponentsLast)) {
                // Win
                this.shifts.put(shift, this.shifts.get(shift) + CHANGE);
            } else if (shiftedMove != opponentsLast) {
                // Lose
                this.shifts.put(shift, this.shifts.get(shift) - CHANGE);
            }
        }
    }
}
