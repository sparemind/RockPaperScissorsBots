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
     * Loads data saved from previous training session game. Called when a
     * training session starts.
     *
     * @param data The data from the previous training session game. Will be
     *             null if this game is the first being played by this player
     *             class in this tournament.
     * @return The data to send to this player's opponent, if the opponent is
     * another player of this player class.
     */
    Object[] trainingInit(Object[] data) {
        return data;
    }

    /**
     * Saves data to use in next training session game. Called when a training
     * session ends.
     *
     * @param record This player's win/loss/draw record for the training session
     *               game.
     * @return The data to send to the next training session game. This data is
     * shared between all players of this player class.
     */
    Object[] trainingEnd(PlayerData.Record record) {
        return null;
    }
}
