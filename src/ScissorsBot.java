import java.util.List;

/**
 * Always plays scissors.
 */
public class ScissorsBot implements RockPaperScissorsPlayer {
    @Override
    public Move makeMove(List<Move> previousMoves) {
        return Move.SCISSORS;
    }
}
