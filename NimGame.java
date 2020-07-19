/**
 * A class of a round of Nim game.
 * @author Zhejun Lyu 1128727
 */
public abstract class NimGame {
    private int stoneCount;
    private final int upperBound;
    private final NimPlayer player1;
    private final NimPlayer player2;
    private int turnCount;

    //Constructor
    public NimGame() {
        stoneCount = 0;
        upperBound = 0;
        player1 = null;
        player2 = null;
        turnCount = 1;
    }
    //Constructor
    public NimGame(int stoneCount, int upperBound, NimPlayer player1, NimPlayer player2) {
        this.stoneCount = stoneCount;
        this.upperBound = upperBound;
        this.player1 = player1;
        this.player2 = player2;
        this.turnCount = 1;
    }

    /**
     * Gets current number of remaining stones.
     * @return the value of current number of remaining stones.
     */
    public int getStoneCount() {
        return stoneCount;
    }

    /**
     * Sets or changes the number of remaining stones.
     * @param stoneCount the desired number of remaining stones.
     */
    public void setStoneCount(int stoneCount) {
        this.stoneCount = stoneCount;
    }

    /**
     * Gets the current maximum number of stones to be removed in a round of Nim game.
     * @return the current maximum number of stones to be removed in a round of Nim game.
     */
    public int getUpperBound() {
        return upperBound;
    }

    /**
     * Gets the first player as an instance of class NimPlayer.
     * @return the player1 as an instance of class NimPlayer.
     */
    public NimPlayer getPlayer1() {
        return player1;
    }

    /**
     * Gets the second player as an instance of class NimPlayer.
     * @return second player as an instance of class NimPlayer.
     */
    public NimPlayer getPlayer2() {
        return player2;
    }

    public int getTurnCount() {
        return turnCount;
    }

    public void setTurnCount(int turnCount) {
        this.turnCount = turnCount;
    }

    /**
     * Plays a round of Nim game including two players from NimPlayer,
     * along with settled number of total stone and
     * the number of stones to be removed in a turn.
     */


    public void play() {

        //Prints the starting information.
        System.out.println("\nInitial stone count: " + getStoneCount()
                + "\nMaximum stone removal: " + getUpperBound()
                + "\nPlayer 1: " + getPlayer1().getGivenName() + " " + getPlayer1().getFamilyName()
                + "\nPlayer 2: " + getPlayer2().getGivenName() + " " + getPlayer2().getFamilyName());

        //Initials a boolean value to control the game status.
        boolean isPlaying = true;

        //Start the Nim game.
        while (isPlaying) {
            //Checks the game over conditions.
            if (getStoneCount() == 0) {
                isPlaying = gameOver(getPlayer1(),getPlayer2(),getTurnCount());
            }
            //Game is not over, resuming the current turn of the game.
            else {
                System.out.print("\n" + getStoneCount() + " stones left:");

                //Print out the initial asterisk.
                System.out.print(" *");

                //A for loop that produces asterisks (other than the first one) that represent remaining stones.
                for (int i = 0; i < getStoneCount() - 1; i++) {
                    System.out.print(" *");
                }

                //Decides the turn for the according player based on the turn count. For example,
                // if the turn count is odd, then it is player1's turn, and vice versa.
                if (getTurnCount() % 2 == 1) {
                    playerTurn(getPlayer1());
                }
                else {
                    playerTurn(getPlayer2());
                }
            }
        }
    }

    /**
     * Allows a player plays the current turn.
     * Updates the turn count and the stone count if the player finishes the turn.
     * @param player The player who should play this turn.
     */
    public void playerTurn(NimPlayer player) {
        System.out.println("\n" + player.getGivenName() + "'s turn - remove how many?");

        //Initialises an integer variable to stores the number of stones that the player
        // wants to remove in the according turn. The initial value only serves for the
        // AI players.
        int stoneNumber = getStoneCount()-1;

        //Asks the player to enter the number of stones to be removed.
        //AI player will decide the number stones to be removed of automatically.
        if (player instanceof NimHumanPlayer) {
            stoneNumber = ((NimHumanPlayer) player).originalMove();
        }
        else {
            stoneNumber = ((NimAIPlayer)player).originalMove(getStoneCount(),getUpperBound(),stoneNumber);
        }

        //Asks the player to enter the valid amount of stones to be removed if there is an invalid move.
        try {
            if (stoneNumber < 1 || stoneNumber > getUpperBound()||stoneNumber > getStoneCount()) {
                throw new InvalidMoveException();
            }

            //The remaining stones will decrease after a player determines the stone removal.
            setStoneCount(getStoneCount() - stoneNumber);
            //Updates the turn count.
            setTurnCount(getTurnCount()+1);
        }
        catch (InvalidMoveException e) {
            System.out.println("\nInvalid move. You must remove between 1 and "
                    + Math.min(getStoneCount(),getUpperBound()) + " stones.");
        }
    }

    /**
     * Ends the game.
     * @param player1 A player in the current game.
     * @param player2 Another player in the current game.
     * @param turnCount The turn count when the game is ending.
     * @return A boolean variable that passes to another boolean variable isPlaying
     * that continues/ends the game play.
     */
    public boolean gameOver(NimPlayer player1, NimPlayer player2, int turnCount) {
        System.out.println("\nGame Over");
        player1.setGamesPlayed(player1.getGamesPlayed() + 1);
        player2.setGamesPlayed(player2.getGamesPlayed() + 1);

        //Determines and announces the winner based on the turn count, if the turn
        // count is odd then player 1 wins, and vice versa.
        if (turnCount % 2 == 1) {
            announceWinner(player1);
        } else {
            announceWinner(player2);
        }
        return false;
    }

    /**
     * Announces the winner of the current game.
     * @param player the player who is the winner.
     */
    public void announceWinner(NimPlayer player) {
        player.setGamesWon(player.getGamesWon() + 1);
        System.out.print(player.getGivenName() + " "
                + player.getFamilyName() + " wins!\n\n$");
    }
}
