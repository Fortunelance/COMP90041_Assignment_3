public class NimHumanPlayer extends NimPlayer{
    public NimHumanPlayer() {
        super();
    }

    public NimHumanPlayer(String username, String familyName, String givenName) {
        super(username,familyName,givenName);
    }

    public NimHumanPlayer (NimPlayer otherPlayer) {
        super(otherPlayer);
    }

    public int removeStone() {
        return Integer.parseInt(Nimsys.console.nextLine());
    }

    public String advancedMove(boolean[] available, String lastMove) {
        // the implementation of the victory
        // guaranteed strategy designed by you
        String move = "";

        return move;
    }
}
