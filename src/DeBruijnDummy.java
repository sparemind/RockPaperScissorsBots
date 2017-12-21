import java.util.List;

/**
 * Plays moves according to a fixed De Bruijn sequence consisting of 13
 * difference sequences 81 moves long (De Bruijn sequences of length 4).
 */
public class DeBruijnDummy extends RockPaperScissorsPlayer {
    private static final String SEQUENCE = "PRSRRSRSRPPRRSSPRRPPSSRRPSPRSSSSRPSRSSRSPPSPPRPPPSRRRRSPRPRPSSPSRPRRRPRSPSPSSSPPPRRPPPPRPRSSSRRSSRSRPRPPRSPPSSSSPPPSRPSSPSRRRPRRRRSRSSPRRPSPSSRPPSPPRRSPRPSRSPSPRSPPSRRPRPSSRPRRSRPSRPPSPPPPRSRSPRSSRSSSSRRRPSPSSSPPRPPRRRRSPSRSRRSSPRRPPPSSPSPRPRSPRPRSRSRRPSSSRSPRRPPPSSPPRSSRRRSSSSPSSRPSRRSRPPSPSPPPPRRSPSRPRRRRPRPPRPSPRSPPSRSSSSPPPPRRSRSSSPSPRSPRRRRSPPSSPRPRRPPPSPPRPSSSSRRPSRSRPSPSRPRPPSRRRPRSSRSPSSRPPRSRRRRRRPRRRSPPRRPPPPRSRSPSRSSPSSSPPPSPSPRRSRPPRPRSPRSSSSRSRRSSRRPSSPRPPSRPSPPSSRPRPSSSRSRRSRSPSSSSRRRRSSPRRRPSRPSPSRRPRSRPRRSPRPSSPPSRSSSPSPRSSRPPRSPPRRPPSPPPPRPRPPPPPPSPRRRRPPRSPSPSSSRRPSRPRPSPPSSRSRSPPRRPRSRRSRPPSRSSPPPPRPRRSSSSPSRRRSPRSSRPSSPRSPRPRPPPPSPPRPSPSSSSPSRRRPPSRSRSPRRRRSRRPRRSSSRRSPPSSRPSRPPRRPSSPPPRSRPRSSRSSPRSPSSSPRPRSSPSRSPRSRRRRPSPRRSRSSRPRPPSPPRRPRRRSPPSRRSSSSRRPPPRSPSPSSPPPPSSRSRPSRPPRPPRRPPRPSRPSPSSPRRSRSPRPRSSRPPSPRSRRPRPPPSSSSPSRSSPPSRRSPSPPPPRSPPRRRRSSSRRRPSSRSRSSRSPPSRSRRPPPRRPSPPRPPRSSRRSSPPPPSPSPRSRSSSSPSRRRPRRSPRRRRSRPPSSSRPSSPRPRPSRPRSPRSRSPPPRRSSSRPPSSPSRRRPRPSPR";

    private int index;

    @Override
    public Move makeMove(List<Move> previousMoves) {
        char next = SEQUENCE.charAt(this.index);
        this.index++;

        if (next == 'R') {
            return Move.ROCK;
        } else if (next == 'P') {
            return Move.PAPER;
        } else { // next == 'S'
            return Move.SCISSORS;
        }
    }
}
