import java.util.List;

/**
 * Always plays scissors.
 */
public class ScissorsBot extends RockPaperScissorsPlayer {
    @Override
    public Move makeMove(List<Move> previousMoves) {
        return Move.SCISSORS;
    }
}
