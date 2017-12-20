import java.util.List;

/**
 * Extend this class to make a player that can compete in the tournament.
 * <p>
 * Rock-Paper-Scissors rules:
 * Rock beats Scissors, Scissors beats Paper, Paper beats Rock
 */
public class RockPaperScissorsPlayer {
    /**
     * Returns the next move this player will make based on its opponent's
     * previous moves.
     *
     * @param previousMoves A list of all of the opponent's previous moves, from
     *                      oldest to newest.
     * @return This player's next move.
     */
    Move makeMove(List<Move> previousMoves) {
        return Move.random();
    }

    /**
     * Called when a training session starts.
     *
     * @param data The data from the previous training session game.
     */
    void trainingInit(Object[] data) {

    }

    /**
     * Called when a training session ends.
     *
     * @return The data to send to the next training session game.
     */
    Object[] trainingEnd() {
        return null;
    }
}
