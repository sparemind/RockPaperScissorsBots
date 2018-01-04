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
    private Map<String, Integer> nemeses;
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
        this.nemeses = new HashMap<>();
        this.nemesis = null;
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
     */
    public void updateNemesis(String playerName, int losses) {
        if (!this.nemeses.containsKey(playerName)) {
            this.nemeses.put(playerName, 0);
        }
        this.nemeses.put(playerName, this.nemeses.get(playerName) + losses);
        if (this.nemesis == null || this.nemeses.get(playerName) > this.nemeses.get(this.nemesis)) {
            this.nemesis = playerName;
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
     * Resets all of this player's win/loss/draw totals for rounds and games
     */
    public void resetRecords() {
        this.roundsRecord = new Record();
        this.gamesRecord = new Record();
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
            double percent = 100.0 * this.wins / this.total;
            percent = (int) (10.0 * percent) / 10.0;

            int length = ("" + this.total).length();
            String wins = "" + this.wins;
            while (wins.length() < length) {
                wins = " " + wins;
            }
            String fraction = wins + "/" + this.total;

            return fraction + " (" + percent + "%)";
        }
    }
}
