import java.util.List;

/**
 * Implement this interface to make a bot that can compete in the tournament.
 * <p>
 * Rock-Paper-Scissors rules:
 * Rock beats Scissors, Scissors beats Paper, Paper beats Rock
 */
public interface RockPaperScissorsBot {
    /**
     * Returns the next move this bot will make based on its opponent's previous
     * moves.
     *
     * @param previousMoves A list of all of the opponent's previous moves, from
     *                      oldest to newest.
     * @return This bot's next move.
     */
    Move makeMove(List<Move> previousMoves);
}
