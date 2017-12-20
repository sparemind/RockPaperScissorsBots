import java.util.List;

/**
 * Always plays a random move.
 */
public class RandomBot implements RockPaperScissorsPlayer {
    @Override
    public Move makeMove(List<Move> previousMoves) {
        return Move.random();
    }
}
