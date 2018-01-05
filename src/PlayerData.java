import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

/**
 * Holds the data for a single player in the tournament.
 */
public class PlayerData implements Comparable<PlayerData> {
    private String name;
    private Constructor<?> constructor;
    private Record roundsRecord;
    private Record gamesRecord;
    private Rating rating;
    private Map<String, Record> nemeses;
    private String nemesis;

    /**
     * Initializes statistics tracking for a given player and links them to this
     * class for later recall.
     *
     * @param player The player whose data will be stored.
     */
    public PlayerData(Class<? extends RockPaperScissorsPlayer> player) {
        this.name = player.getName();
        this.constructor = player.getConstructors()[0];
        resetRecords();
        this.rating = new Rating();
    }

    /**
     * Returns this player's round win/loss/draw totals.
     *
     * @return The round win/loss/draw records for this player.
     */
    public Record getRoundsRecord() {
        return this.roundsRecord;
    }

    /**
     * Returns the name of this data's player.
     *
     * @return The name of this data's player.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns this player's game win/loss/draw totals.
     *
     * @return The game win/loss/draw records for this player.
     */
    public Record getGamesRecord() {
        return this.gamesRecord;
    }

    /**
     * Returns the overall rating of this player.
     *
     * @return The overall rating of this player.
     */
    public Rating getRating() {
        return PlayerData.this.rating;
    }

    /**
     * Updates the nemesis tracker of this player. A player's nemesis is the
     * player who they've lost the most rounds to.
     *
     * @param playerName The name of the player.
     * @param losses     The number of rounds lost to the given player.
     * @param total      The total number of rounds played played against the
     *                   given player.
     */
    public void updateNemesis(String playerName, int losses, int total) {
        if (!this.nemeses.containsKey(playerName)) {
            this.nemeses.put(playerName, new Record());
        }
        // Add wins for the nemesis
        Record opponentRecord = this.nemeses.get(playerName);
        opponentRecord.wins += losses;
        opponentRecord.total += total;
        if (this.nemesis == null) {
            this.nemesis = playerName;
        } else {
            Record nemesisRecord = this.nemeses.get(this.nemesis);
            double opponentWins = (double) opponentRecord.wins / opponentRecord.total;
            double nemesisWins = (double) nemesisRecord.wins / nemesisRecord.total;
            if (opponentWins > nemesisWins) {
                this.nemesis = playerName;
            }
        }
    }

    /**
     * Returns the nemesis of this player. A player's nemesis is the player who
     * they've lost the most rounds to.
     */
    public String getNemesis() {
        return this.nemesis;
    }

    /**
     * Returns the round win/total records of this player's nemesis.
     *
     * @return The round win/total records for this player's nemesis.
     */
    public Record getNemesisRecord() {
        if (this.nemesis == null) {
            return null;
        }
        return this.nemeses.get(this.nemesis);
    }

    /**
     * Resets all of this player's win/loss/draw totals for rounds and games, as
     * well as the player's nemesis tracker.
     */
    public void resetRecords() {
        this.roundsRecord = new Record();
        this.gamesRecord = new Record();
        this.nemeses = new HashMap<>();
        this.nemesis = null;
    }

    /**
     * Creates a new instance of the player whose data is being stored.
     *
     * @return A new instance of the player whose data is being stored.
     * @throws Exception If a new player instance cannot be created.
     */
    public RockPaperScissorsPlayer newInstance() throws Exception {
        return (RockPaperScissorsPlayer) this.constructor.newInstance();
    }

    /**
     * Returns a comparison value such that PlayerData will be sorted in order
     * of descending number of games won first, number of rounds won second, and
     * alphabetically by name third.
     *
     * @param other The data to compare this data to.
     * @return Negative if this should come before other, positive if this
     * should come after other, 0 if both should be ranked equally.
     */
    @Override
    public int compareTo(PlayerData other) {
        int result = other.gamesRecord.wins - this.gamesRecord.wins;
        if (result == 0) {
            result = other.roundsRecord.wins - this.roundsRecord.wins;
        }
        if (result == 0) {
            result = this.name.compareTo(other.name);
        }
        return result;
    }

    /**
     * Stores totals for win/loss/draw data.
     */
    public static class Record {
        private int wins;
        private int losses;
        private int draws;
        private int total;

        Record() {
            this.wins = 0;
            this.losses = 0;
            this.draws = 0;
            this.total = 0;
        }

        Record(Record other) {
            this.wins = other.wins;
            this.losses = other.losses;
            this.draws = other.draws;
            this.total = other.total;
        }

        public void addWin() {
            this.wins++;
            this.total++;
        }

        public void addLoss() {
            this.losses++;
            this.total++;
        }

        public void addDraw() {
            this.draws++;
            this.total++;
        }

        public int getWins() {
            return this.wins;
        }

        public int getDraws() {
            return this.draws;
        }

        public int getLosses() {
            return this.losses;
        }

        public int getTotal() {
            return this.total;
        }

        /**
         * Returns a string containing the fractions of wins and percent of wins
         * to 1 decimal place.
         *
         * @return A string of the fraction of wins and the percent of wins (to
         * 1 decimal place).
         */
        @Override
        public String toString() {
            int length = ("" + this.total).length();
            String wins = "" + this.wins;
            while (wins.length() < length) {
                wins = " " + wins;
            }
            String fraction = wins + "/" + this.total;

            return fraction + " (" + percent() + "%)";
        }

        /**
         * Returns the percent of wins to 1 decimal place.
         *
         * @return A string of the percent of wins (to 1 decimal place).
         */
        public String percent() {
            double percent = 100.0 * this.wins / this.total;

            return String.format("%.1f", percent);
        }
    }
}
