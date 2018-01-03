import java.util.ArrayList;
import java.util.List;

/**
 * Creates a random pattern of moves before the game begins, then plays it
 * repeatably for the entirety of the game.
 */
public class PatternDummy extends RockPaperScissorsPlayer {
    private static final int MIN_LENGTH = 5;
    private static final int MAX_LENGTH = 15;

    private List<Move> pattern;
    private int index;

    public PatternDummy() {
        this.pattern = new ArrayList<>();
        this.index = 0;

        int length = MIN_LENGTH + (int) (Math.random() * (MAX_LENGTH - MIN_LENGTH));
        for (int i = 0; i < length; i++) {
            this.pattern.add(Move.random());
        }
    }

    @Override
    public Move makeMove(List<Move> previousMoves) {
        return this.pattern.get(this.index++ % this.pattern.size());
    }
}
