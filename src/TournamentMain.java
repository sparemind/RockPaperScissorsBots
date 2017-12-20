public class TournamentMain {
    private static final int NUM_ROUNDS = 1000;
    private static final int NUM_GAMES = 10;

    public static void main(String[] args) {
        TournamentManager manager = new TournamentManager();

        //////// Load special players \\\\\\\\
        // manager.add(HumanPlayer.class);

        //////// Load dummy players \\\\\\\\
        // manager.add(RandomDummy.class);
        // manager.add(RockDummy.class);
        // manager.add(PaperDummy.class);
        // manager.add(ScissorsDummy.class);
        manager.add(PatternDummy.class);

        //////// Load strategic players \\\\\\\\
        manager.add(FrequencyBot.class);
        manager.add(DecayingFrequencyBot.class);
        manager.add(HistoryBot.class);

        manager.runTournament(NUM_ROUNDS, NUM_GAMES, true);
    }
}
