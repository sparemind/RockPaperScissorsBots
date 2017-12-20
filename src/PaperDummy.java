import java.util.List;

/**
 * Always plays paper.
 */
public class PaperDummy extends RockPaperScissorsPlayer {
    @Override
    public Move makeMove(List<Move> previousMoves) {
        return Move.PAPER;
    }
}
