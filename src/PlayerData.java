import java.lang.reflect.Constructor;

/**
 * Holds the data for a single player in the tournament.
 */
public class PlayerData implements Comparable<PlayerData> {
    private String name;
    private Constructor<?> constructor;
    private Record roundsRecord;
    private Record gamesRecord;

    /**
     * Initializes statistics tracking for a given player and links them to this
     * class for later recall.
     *
     * @param player The player whose data will be stored.
     */
    public PlayerData(Class<? extends RockPaperScissorsBot> player) {
        this.name = player.getName();
        this.constructor = player.getConstructors()[0];
        resetRoundsRecord();
        resetGamesRecord();
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
     * Returns this player's game win/loss/draw totals.
     *
     * @return The game win/loss/draw records for this player.
     */
    public Record getGamesRecord() {
        return this.gamesRecord;
    }

    /**
     * Resets this player's round win/loss/draw totals.
     */
    public void resetRoundsRecord() {
        this.roundsRecord = new Record();
    }

    /**
     * Resets this player's game win/loss/draw totals.
     */
    public void resetGamesRecord() {
        this.gamesRecord = new Record();
    }

    /**
     * Creates a new instance of the player whose data is being stored.
     *
     * @return A new instance of the player whose data is being stored.
     * @throws Exception If a new player instance cannot be created.
     */
    public RockPaperScissorsBot newInstance() throws Exception {
        return (RockPaperScissorsBot) this.constructor.newInstance();
    }

    /**
     * Returns a comparison value such that PlayerData will be sorted in order
     * of descending number of rounds won.
     *
     * @param other The data to compare this data to.
     * @return Negative if this should come before other, positive if this
     * should come after other, 0 if both should be ranked equally.
     */
    @Override
    public int compareTo(PlayerData other) {
        return other.roundsRecord.wins - this.roundsRecord.wins;
    }

    /**
     * Stores totals for win/loss/draw data.
     */
    private static class Record {
        public int wins;
        public int losses;
        public int draws;
        public int total;
    }
}
