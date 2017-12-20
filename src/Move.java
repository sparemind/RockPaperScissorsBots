/**
 * Represents a move that a player can make, one of: Rock, Paper, Scissors.
 * Paper beats rock, scissors beats paper, and rock beats scissors.
 */
public enum Move {
    ROCK(0), PAPER(1), SCISSORS(2);

    private int value;

    Move(int value) {
        this.value = value;
    }

    /**
     * Returns a random move.
     *
     * @return A random move.
     */
    public static Move random() {
        return values()[(int) (Math.random() * values().length)];
    }

    /**
     * Returns a numeric representation of this move.
     *
     * @return Integer representation of this move. Higher values beat lower
     * values, except for the highest value, which is beaten by the lowest (0).
     */
    public int val() {
        return this.value;
    }

    /**
     * Returns whether this move beats another move.
     *
     * @param other The other move to check against.
     * @return Whether this move beats the other move.
     */
    public boolean beats(Move other) {
        return other.getCounter() == this;
    }

    /**
     * Returns an integer result representing whether this move wins/loses/draws
     * against the other move.
     *
     * @param other The other move to check against.
     * @return 0 if it's a draw, 1 if this move beats the other move, -1 if this
     * move loses.
     */
    public int versus(Move other) {
        if (this == other) {
            return 0;
        } else if (beats(other)) {
            return 1;
        } else {
            return -1;
        }
    }

    /**
     * Returns the move that beats this move.
     *
     * @return The move that beats this move.
     */
    public Move getCounter() {
        return values()[(this.value + 1) % values().length];
    }
}