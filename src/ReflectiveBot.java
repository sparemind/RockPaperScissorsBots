import java.util.ArrayList;
import java.util.List;

/**
 * Analyzes the opponents move history to find the longest move sequence that
 * matches the most recent move sequence. Then looks at what they played next
 * and plays the counter to it.
 * <p>
 * Does the same to own history to predict what an opponent would play if they
 * are doing the same strategy. Keeps track of which version would have
 * performed against what the opponent chose, and uses the best.
 */
public class ReflectiveBot extends RockPaperScissorsPlayer {
    private static final int SEARCH_DEPTH = 100;
    private static final double DECAY = 0.9;
    private static final double CHANGE = 0.1;

    private List<Move> moves;
    private Predictor opponentsPredictor;
    private Predictor myPredictor;

    public ReflectiveBot() {
        this.moves = new ArrayList<>();
        this.opponentsPredictor = new Predictor();
        this.myPredictor = new Predictor();
    }

    @Override
    public Move makeMove(List<Move> previousMoves) {
        Move nextMove = move(previousMoves);

        this.moves.add(nextMove);
        return nextMove;
    }

    private Move move(List<Move> previousMoves) {
        if (previousMoves.isEmpty()) {
            Move nextMove = Move.random();
            this.opponentsPredictor.predictedMove = nextMove;
            this.myPredictor.predictedMove = nextMove;
            return nextMove;
        }

        Move opponentsLast = previousMoves.get(previousMoves.size() - 1);
        this.opponentsPredictor.update(opponentsLast);
        this.myPredictor.update(opponentsLast);

        this.opponentsPredictor.predictedMove = predictMoveFromHistory(previousMoves);
        this.myPredictor.predictedMove = predictMoveFromHistory(this.moves);

        // double total = this.opponentsPredictor.score + this.myPredictor.score;
        // double r = Math.random() * total;
        // if (r < this.opponentsPredictor.score) {
        //     return this.opponentsPredictor.predictedMove.getCounter();
        // } else {
        //     return this.myPredictor.predictedMove.getCounter().getCounter();
        // }

        if (this.opponentsPredictor.score > this.myPredictor.score) {
            return this.opponentsPredictor.predictedMove.getCounter();
        } else {
            return this.myPredictor.predictedMove.getCounter().getCounter();
        }
    }

    private Move predictMoveFromHistory(List<Move> history) {
        // Most recent move
        int historyEnd = history.size() - 1;
        // How far back to search
        int searchDepth = Math.min(history.size(), SEARCH_DEPTH);
        // Length of the longest match sequence
        int longestMatch = 0;
        // Index of the next move played after the longest match sequence
        int indexOfNext = 0;

        // Search history for matches of increasing sequence length
        for (int i = 1; i < searchDepth; i++) {
            int high = historyEnd;
            int low = high - i;

            while (low > 0 && history.get(low) == history.get(high)) {
                high--;
                low--;
            }
            if (historyEnd - high > longestMatch) {
                longestMatch = historyEnd - high;
                indexOfNext = low + longestMatch + 1;
            }
        }

        return history.get(indexOfNext);
    }

    private static class Predictor {
        public Move predictedMove;
        public double score;

        public void update(Move opponentsLast) {
            this.score *= DECAY;
            if (this.predictedMove.beats(opponentsLast)) {
                // Won
                this.score += CHANGE;
            } else if (this.predictedMove != opponentsLast) {
                // Lost
                this.score -= CHANGE;
                this.score = 0;
            }
        }
    }
}
