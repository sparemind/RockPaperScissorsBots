public class TournamentMain {
    public static final int NUM_ROUNDS = 1000;
    public static final int NUM_GAMES = 1;

    public static void main(String[] args) {
        TournamentManager manager = new TournamentManager();

        manager.add(RockBot.class);
        manager.add(PaperBot.class);
        manager.add(ScissorsBot.class);

        manager.runTournament(NUM_ROUNDS, NUM_GAMES);
    }
}
