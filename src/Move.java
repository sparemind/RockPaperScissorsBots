public enum Move {
    ROCK(0), PAPER(1), SCISSORS(2);

    private int value;

    Move(int value) {
        this.value = value;
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
     * Returns the move that beats this move.
     *
     * @return The move that beats this move.
     */
    public Move getCounter() {
        return this.values()[(this.value + 1) % this.values().length];
    }

    /**
     * Returns a random move.
     *
     * @return A random move.
     */
    public static Move random() {
        return values()[(int) (Math.random() * values().length)];
    }
}