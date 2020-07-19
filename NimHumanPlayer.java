/**
 * A sub class of NimPlayer class. Includes humman players only.
 * @author Zhejun Lyu 1128727
 */
public class NimHumanPlayer extends NimPlayer {
    //Constructor.
    public NimHumanPlayer() {
        super();
    }

    //Constructor.
    public NimHumanPlayer(String username, String familyName, String givenName) {
        super(username,familyName,givenName);
    }

    //Constructor.
    public NimHumanPlayer (NimPlayer otherPlayer) {
        super(otherPlayer);
    }

    /**
     * Deciding a player's move manually.
     * @return A String of manually entered command to decide the move.
     */
    public int originalMove() {
        return Integer.parseInt(Nimsys.console.nextLine());
    }

    /**
     * Deciding a player's move manually.
     * @return A String of manually entered command to decide the move.
     */
    public String advancedMove() {
        return Nimsys.console.nextLine();
    }
}
