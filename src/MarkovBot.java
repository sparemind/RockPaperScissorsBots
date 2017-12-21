import java.util.List;

/**
 * Tracks the opponent's previous moves as a Markov model, predicting what their
 * most likely next move is based on what patterns they've played most
 * frequently in the past and what moves they've most recently played.
 */
public class MarkovBot extends RockPaperScissorsPlayer {
    private static final int ORDER = 10;

    private TrieNode root;

    public MarkovBot() {
        this.root = new TrieNode();
    }

    @Override
    public Move makeMove(List<Move> previousMoves) {
        if (previousMoves.size() < ORDER + 1) {
            return Move.random();
        }

        // Update markov chain values
        TrieNode current = this.root;
        for (int i = previousMoves.size() - 1 - ORDER; i < previousMoves.size(); i++) {
            Move move = previousMoves.get(i);
            if (current.children[move.val()] == null) {
                current.children[move.val()] = new TrieNode();
            }
            current = current.children[move.val()];
        }
        current.value++;

        // Navigate the trie using the recent moves
        current = this.root;
        for (int i = previousMoves.size() - ORDER; i < previousMoves.size(); i++) {
            Move move = previousMoves.get(i);
            current = current.children[move.val()];
            if (current == null) {
                return Move.random();
            }
        }

        // Find the most likely next move that will be played
        int nextMove = 0;
        double value = 0;
        for (int i = 0; i < current.children.length; i++) {
            TrieNode node = current.children[i];
            if (node != null) {
                if (node.value > value) {
                    value = node.value;
                    nextMove = i;
                }
            }
        }

        // Play the counter to that move
        return Move.values()[nextMove].getCounter();
    }

    /**
     * Represents a single move among a series of moves
     */
    private static class TrieNode {
        public double value;
        public TrieNode[] children;

        public TrieNode() {
            this.value = 0;
            this.children = new TrieNode[Move.values().length];
        }
    }
}
