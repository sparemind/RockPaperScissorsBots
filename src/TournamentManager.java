import java.util.ArrayList;
import java.util.List;

/**
 * Manages a tournament between rock-paper-scissors players.
 */
public class TournamentManager {
    private List<PlayerData> players;

    /**
     * Initializes a new tournament with no players.
     */
    public TournamentManager() {
        this.players = new ArrayList<>();
    }

    /**
     * Adds a player to the tournament.
     *
     * @param newPlayer The player to add to the tournament.
     */
    public void add(Class<? extends RockPaperScissorsPlayer> newPlayer) {
        this.players.add(new PlayerData(newPlayer));
    }

    /**
     * Runs a round-robin tournament between all loaded player. Each player
     * plays a match against every other player. A match consists of the given
     * number of games, with each game lasting the given number of rounds.
     *
     * @param rounds The number of rounds to play per game.
     * @param games  The number of games to play per match between two players.
     * @throws IllegalStateException If fewer than 2 players have been added to
     *                               the tournament.
     */
    public void runTournament(int rounds, int games) {
        if (this.players.size() < 2) {
            throw new IllegalStateException("At least 2 players must be added to be able to run a tournament.");
        }

        resetPlayerData();

        // Have every player play a match against every other player
        for (int i = 0; i < this.players.size(); i++) {
            for (int j = i; j < this.players.size(); j++) {
                // Prevent a player from playing themselves
                if (i != j) {
                    // Play the given number of games
                    for (int game = 0; game < games; game++) {
                        playGame(this.players.get(i), this.players.get(j), rounds);
                    }
                }
            }
        }

        for (PlayerData playerData : this.players) {
            System.out.print(playerData.getName() + "\t");
            System.out.print(playerData.getGamesRecord() + "\t");
            System.out.println(playerData.getRoundsRecord());
        }
    }

    /**
     * Plays a single game consisting of the given number of rounds between the
     * two given players. Player data for both players is updated depending on
     * the results of the rounds and the game.
     *
     * @param player1Data The first player.
     * @param player2Data The second player.
     * @param rounds      The number of rounds the game should last.
     */
    private void playGame(PlayerData player1Data, PlayerData player2Data, int rounds) {
        RockPaperScissorsPlayer player1 = initializePlayer(player1Data);
        RockPaperScissorsPlayer player2 = initializePlayer(player2Data);

        // If a player could not be initialized, end the game
        if (player1 == null || player2 == null) {
            if (player1 == null) {
                player1Data.getGamesRecord().addLoss();
            } else {
                player1Data.getGamesRecord().addWin();
            }
            if (player2 == null) {
                player2Data.getGamesRecord().addLoss();
            } else {
                player2Data.getGamesRecord().addWin();
            }
            return;
        }

        List<Move> player1Moves = new ArrayList<>();
        List<Move> player2Moves = new ArrayList<>();
        int player1RoundWins = 0;
        int player2RoundWins = 0;

        // Play the given number of rounds
        for (int round = 0; round < rounds; round++) {
            Move player1Move = player1.makeMove(player2Moves);
            Move player2Move = player2.makeMove(player1Moves);

            player1Moves.add(player1Move);
            player2Moves.add(player2Move);

            int result = player1Move.versus(player2Move);
            if (result == 0) {
                // Draw
                player1Data.getRoundsRecord().addDraw();
                player2Data.getRoundsRecord().addDraw();
            } else if (result == 1) {
                // Player 1 wins
                player1RoundWins++;
                player1Data.getRoundsRecord().addWin();
                player2Data.getRoundsRecord().addLoss();
            } else { // result == -1
                // Player 2 wins
                player2RoundWins++;
                player1Data.getRoundsRecord().addLoss();
                player2Data.getRoundsRecord().addWin();
            }
        }

        // Update game records based on game results
        if (player1RoundWins > player2RoundWins) {
            player1Data.getGamesRecord().addWin();
            player2Data.getGamesRecord().addLoss();
        } else if (player1RoundWins < player2RoundWins) {
            player1Data.getGamesRecord().addLoss();
            player2Data.getGamesRecord().addWin();
        } else {
            player1Data.getGamesRecord().addDraw();
            player2Data.getGamesRecord().addDraw();
        }
    }

    /**
     * Attempts to initialize a new instance of a player. If the player cannot
     * be initialized, prints error message and stack trace.
     *
     * @param playerData The player to get a new instance of.
     * @return A new instance of the given player. Returns null if the player
     * cannot be initialized.
     */
    private RockPaperScissorsPlayer initializePlayer(PlayerData playerData) {
        RockPaperScissorsPlayer player = null;

        try {
            player = playerData.newInstance();
        } catch (Exception e) {
            System.err.println(playerData.getName() + " threw exception during initialization.");
            e.printStackTrace();
        }

        return player;
    }

    /**
     * Resets the win/loss/draw totals for all players.
     */
    public void resetPlayerData() {
        for (PlayerData player : this.players) {
            player.resetRecords();
        }
    }
}
