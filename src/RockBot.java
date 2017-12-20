import java.util.List;

/**
 * Always plays rock.
 */
public class RockBot extends RockPaperScissorsPlayer {
    @Override
    public Move makeMove(List<Move> previousMoves) {
        return Move.ROCK;
    }
}
