/**
 * A class of a round of Nim game.
 * @author Zhejun Lyu 1128727
 */
public class NimGame {

    private int stoneCount;
    private int upperBound;
    private NimPlayer player1;
    private NimPlayer player2;

    //Constructor
    public NimGame() {
        stoneCount = 0;
        upperBound = 0;
        player1 = null;
        player2 = null;
    }
    //Constructor
    public NimGame(int stoneCount, int upperBound, NimPlayer player1, NimPlayer player2) {
        this.stoneCount = stoneCount;
        this.upperBound = upperBound;
        this.player1 = player1;
        this.player2 = player2;
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

    /**
     * Plays a round of Nim game including two players from NimPlayer, along with settled number of total stone and
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

        //Initials an integer value as the count of turns.
        int turnCount = 1;

        //Start the Nim game.
        while (isPlaying) {

            //Checks the game over conditions.
            if (getStoneCount() == 0) {

                System.out.println("\nGame Over");
                getPlayer1().setGamesPlayed(getPlayer1().getGamesPlayed() + 1);
                getPlayer2().setGamesPlayed(getPlayer2().getGamesPlayed() + 1);

                //Determines and announces the winner based on the turn count, if the turn
                // count is odd then player 1 wins, and vice versa.
                if (turnCount % 2 == 1) {
                    getPlayer1().setGamesWon(getPlayer1().getGamesWon() + 1);
                    System.out.print(getPlayer1().getGivenName() + " "
                            + getPlayer1().getFamilyName() + " wins!\n\n$");
                } else {
                    getPlayer2().setGamesWon(getPlayer2().getGamesWon() + 1);
                    System.out.print(getPlayer2().getGivenName() + " "
                            + getPlayer2().getFamilyName() + " wins!\n\n$");
                }
                isPlaying = false;
            }
            //Game is not over, resuming the current turn.
            else {

                System.out.print("\n" + getStoneCount() + " stones left:");

                //Print out the initial asterisk.
                System.out.print(" *");

                //A for loop that produces asterisks (other than the first one) that represent remaining stones.
                for (int i = 0; i < getStoneCount() - 1; i++) {
                    System.out.print(" *");
                }

                //Decides the turn for the according player based on the turn count. For example,
                // if the turn count is odd, then it is player1's turn.
                if (turnCount % 2 == 1) {

                    System.out.println("\n" + getPlayer1().getGivenName() + "'s turn - remove how many?");

                    //Initialises an integer variable to stores the number of stones that the player
                    // wants to remove in the according turn.
                    int stoneNumber = 0;

                    //Asks the player to enter the number of stones to be removed.
                    //AI player will decide the number stones to be removed of automatically.
                    if (player1 instanceof NimHumanPlayer) {
                        stoneNumber = ((NimHumanPlayer) player1).removeStone();
                    }
                    else {
                        stoneNumber = ((NimAIPlayer)player1).removeStone(getStoneCount(),getUpperBound());
                    }
                    //Asks the player to enter the valid amount of stones to be removed if there is an invalid move.
                    try {
                        if (stoneNumber < 1 || stoneNumber > getUpperBound()||stoneNumber > getStoneCount()) {
                            throw new InvalidMoveException();
                        }

                        //The remaining stones will decrease after a player determines the stone removal.
                        setStoneCount(getStoneCount() - stoneNumber);
                        //Updates the turn count.
                        turnCount++;
                    }
                    catch (InvalidMoveException e) {
                        System.out.println("\nInvalid move. You must remove between 1 and "
                                + Math.min(getStoneCount(),getUpperBound()) + " stones.");
                    }
                }
                else {
                    System.out.println("\n" + getPlayer2().getGivenName() + "'s turn - remove how many?");

                    //Initialises an integer variable to stores the number of stones that the player
                    // wants to remove in the according turn.
                    int stoneNumber = 0;

                    //Asks the player to enter the number of stones to be removed.
                    //AI player will decide the number stones to be removed of automatically.
                    if (player1 instanceof NimHumanPlayer) {
                        stoneNumber = ((NimHumanPlayer) player2).removeStone();
                    }
                    else {
                        stoneNumber = ((NimAIPlayer)player2).removeStone(getStoneCount(),getUpperBound());
                    }
                    //Asks the player to enter the valid amount of stones to be removed if there is an invalid move.
                    try {
                        if (stoneNumber < 1 || stoneNumber > getUpperBound()||stoneNumber > getStoneCount()) {
                            throw new InvalidMoveException();
                        }

                        //The remaining stones will decrease after a player determines the stone removal.
                        setStoneCount(getStoneCount() - stoneNumber);
                        //Updates the turn count.
                        turnCount++;
                    }
                    catch (InvalidMoveException e) {
                        System.out.println("\nInvalid move. You must remove between 1 and "
                                + Math.min(getStoneCount(),getUpperBound()) + " stones.");
                    }
                }
            }
        }
    }
}
