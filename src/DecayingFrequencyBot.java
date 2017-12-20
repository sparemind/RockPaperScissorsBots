import java.util.List;

/**
 * Plays the counter to the opponent's most frequent move. Move frequencies
 * decay over time so more recent moves are weighted higher.
 */
public class DecayingFrequencyBot extends RockPaperScissorsPlayer {
    private static final double DECAY = 0.9;
    private static final double CHANGE = 0.1;

    private double[] scores;
    private TrainingData[] trainingData;
    private int trainingID;

    public DecayingFrequencyBot() {
        this.scores = new double[Move.values().length];
        this.trainingID = -1;
    }

    @Override
    public Move makeMove(List<Move> previousMoves) {
        if (previousMoves.isEmpty()) {
            return Move.random();
        }

        // Apply decay to all scores
        for (int i = 0; i < this.scores.length; i++) {
            this.scores[i] *= DECAY;
        }

        // Update scores based on opponent's move
        Move opponentsLast = previousMoves.get(previousMoves.size() - 1);
        this.scores[opponentsLast.getCounter().val()] += CHANGE;
        this.scores[opponentsLast.getDefeated().val()] -= CHANGE;

        // Find the opponent's move frequent move
        int indexOfMost = 0;
        for (int i = 1; i < this.scores.length; i++) {
            if (this.scores[i] > this.scores[indexOfMost]) {
                indexOfMost = i;
            }
        }

        // Play the counter to that move
        return Move.values()[indexOfMost];
    }

    @Override
    Object[] trainingInit(Object[] data) {
        // Initialize shared data array if it isn't already
        if (data == null) {
            data = new TrainingData[2];

            for (int i = 0; i < data.length; i++) {
                data[i] = new TrainingData();
            }
        }
        this.trainingData = (TrainingData[]) data;

        // Get the ID of this bot instance and load data
        for (int i = 0; i < this.trainingData.length; i++) {
            if (!this.trainingData[i].taken) {
                this.trainingData[i].taken = true;
                this.trainingID = i;
                break;
            }
        }

        return this.trainingData;
    }

    @Override
    Object[] trainingEnd(PlayerData.Record record) {
        // Save results of this bot instance
        this.trainingData[this.trainingID] = new TrainingData();

        return this.trainingData;
    }

    private static class TrainingData {
        public boolean taken;
        public double wins;
        public double decayValue;

        public TrainingData() {
            this.taken = false;
            this.wins = 0;
            this.decayValue = 0.9;
        }
    }
}
