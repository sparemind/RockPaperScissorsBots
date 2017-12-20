public class TournamentMain {
    public static final int NUM_ROUNDS = 1000;
    public static final int NUM_GAMES = 3;

    public static void main(String[] args) {
        TournamentManager manager = new TournamentManager();

        manager.add(RandomBot.class);
        manager.add(RockBot.class);
        manager.add(PaperBot.class);
        manager.add(ScissorsBot.class);
        manager.add(FrequencyBot.class);

        manager.runTournament(NUM_ROUNDS, NUM_GAMES);
    }
}
