/**
 * Stores and manages an Elo rating.
 */
public class Rating {
    public static final double INITIAL_RATING = 1000;
    public static final double K_FACTOR = 32;

    public static final double WIN_VALUE = 1.0;
    public static final double LOSS_VALUE = 0.0;
    public static final double DRAW_VALUE = 0.5;

    private double rating;

    /**
     * Create new rating with a default rating value.
     */
    public Rating() {
        this.rating = INITIAL_RATING;
    }

    /**
     * Updates the ratings for two given players based on the result of each.
     *
     * @param player1       The current rating of the first player.
     * @param player2       The current rating of the second player.
     * @param player1Result The numeric result of player 1, either 0, 0.5, or 1,
     *                      corresponding to a loss, win, and draw
     *                      respectively.
     * @param player2Result The numeric result of player 2, either 0, 0.5, or 1,
     *                      corresponding to a loss, draw, and win
     *                      respectively.
     */
    public static void updateRatings(Rating player1, Rating player2, double player1Result, double player2Result) {
        double player1Expected = player1.getExpected(player2);
        double player2Expected = player2.getExpected(player1);

        player1.update(player1Expected, player1Result);
        player2.update(player2Expected, player2Result);
    }

    /**
     * Returns the expected probability of a player with this rating winning
     * against an opponent with the given rating.
     *
     * @param other The rating to compare this rating against.
     * @return The probability of this player winning against the given
     * opponent.
     */
    private double getExpected(Rating other) {
        return 1.0 / (1 + Math.pow(10, (other.rating - this.rating) / 400.0));
    }

    /**
     * Updates this rating based on the expected and actual results of a game
     * played by a player with this rating.
     *
     * @param expected The expected probability of a player with this rating
     *                 winning the game.
     * @param actual   The game result, expressed numerically as either 0, 0.5,
     *                 or 1, corresponding to a loss, draw, and win
     *                 respectively.
     */
    private void update(double expected, double actual) {
        this.rating = this.rating + K_FACTOR * (actual - expected);
    }

    /**
     * Returns the numeric rating.
     *
     * @return The numeric rating.
     */
    public double value() {
        return this.rating;
    }

    /**
     * Returns the numeric rating, rounded down to the nearest integer.
     *
     * @return The string representation of the numeric rating, rounded down to
     * the nearest integer.
     */
    @Override
    public String toString() {
        return String.format("%.0f", this.rating);
    }
}
