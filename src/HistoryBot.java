import java.util.List;

/**
 * Analyzes the opponents move history to find the longest move sequence that
 * matches the most recent move sequence. Then looks at what they played next
 * and plays the counter to it.
 */
public class HistoryBot extends RockPaperScissorsPlayer {
    private static final int SEARCH_DEPTH = 1000;

    @Override
    public Move makeMove(List<Move> previousMoves) {
        if (previousMoves.isEmpty()) {
            return Move.random();
        }

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

        return previousMoves.get(indexOfNext).getCounter();
    }
}
