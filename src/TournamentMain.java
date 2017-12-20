public class TournamentMain {
    private static final int NUM_ROUNDS = 1000;
    private static final int NUM_GAMES = 3;

    public static void main(String[] args) {
        TournamentManager manager = new TournamentManager();

        // manager.add(HumanPlayer.class);

        manager.add(RandomBot.class);
        manager.add(RockBot.class);
        manager.add(PaperBot.class);
        manager.add(ScissorsBot.class);
        manager.add(FrequencyBot.class);
        manager.add(DecayingFrequencyBot.class);

        manager.runTournament(NUM_ROUNDS, NUM_GAMES, true);
    }
}
