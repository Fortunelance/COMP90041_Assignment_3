import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * A sub class of Nim game.
 * @author Zhejun Lyu 1128727
 */
public class NimAdvancedGame extends NimGame {

    private String lastMove;
    private final boolean[] available;

    //Constructor.
    public NimAdvancedGame() {
        super();
        this.lastMove = "";
        this.available = new boolean[0];
    }

    //Constructor.
    public NimAdvancedGame (int stoneCount, NimPlayer player1, NimPlayer player2) {
        super(stoneCount,2,player1,player2);
        this.lastMove = "";
        //Sets up the Array of boolean variables to represents the remaining stones
        //All the stones are available for removing at first.
        available = new boolean[stoneCount];
        Arrays.fill(available,true);
    }

    public String getLastMove() {
        return lastMove;
    }

    public void setLastMove(String lastMove) {
        this.lastMove = lastMove;
    }

    public boolean[] getAvailable() {
        return available;
    }

    public void setAvailable(String lastMove) {
        int expectedNumberOfArguments = 2;
        StringTokenizer tokenizer = new StringTokenizer(lastMove);
        try {
            if (tokenizer.countTokens() < expectedNumberOfArguments) {
                throw new InvalidArgumentsNumberException();
            }

            int position = Integer.parseInt(tokenizer.nextToken());
            int number = Integer.parseInt(tokenizer.nextToken());

            if (!isMoveValid(position,number)) {
                throw new InvalidMoveException();
            }

            available[position-1] = false;
            if (number == 2) {
                available[position] = false;
            }

            setTurnCount(getTurnCount()+1);
            setStoneCount(getStoneCount()-number);
        }
        catch (InvalidArgumentsNumberException | InvalidMoveException
                | ArrayIndexOutOfBoundsException iane) {
            System.out.print("\nInvalid move.\n");
        }
    }

    @Override
    public void play() {
        //Prints information for starting to play the game.
        System.out.println("\nInitial stone count: " + getStoneCount());
        System.out.print("Stones display: ");
        stoneDisplay(getAvailable());
        System.out.println("Player 1: " + getPlayer1().getGivenName() +
                " " + getPlayer1().getFamilyName() +
                "\nPlayer 2: " + getPlayer2().getGivenName() +
                " " + getPlayer2().getFamilyName());

        boolean isPlaying = true;
        while (isPlaying) {
            //Checks the game over conditions.
            if (getStoneCount()==0) {
                isPlaying = gameOver(getPlayer1(),getPlayer2(),getTurnCount());
            }

            //Game is not over, resuming the current turn of the game.
            else {
                System.out.print("\n"+ getStoneCount() + " stones left: ");
                stoneDisplay(getAvailable());

                if (getTurnCount()%2==1) {
                    playerTurn(getPlayer1());
                }
                else {
                    playerTurn(getPlayer2());
                }
            }
        }
    }

    /**
     * Examines if the move made by a player is valid.
     * @param i The chosen position of the move.
     * @param j The chosen number of the move.
     * @return A boolean variable, which is true if the move is valid, false if it is not.
     */
    public boolean isMoveValid (int i, int j) {
        if (j == 2) {
            return getAvailable()[i - 1] && getAvailable()[i];
        }

        else if (j == 1) {
            return getAvailable()[i-1];
        }

        else
            return false;
    }

    public void playerTurn (NimPlayer player) {
        System.out.println(player.getGivenName() + "'s turn - which to remove?");

        if (player instanceof NimHumanPlayer) {
            setLastMove(((NimHumanPlayer)player).advancedMove());
        }

        else {
            setLastMove(((NimAIPlayer)player).advancedMove(getAvailable(),getLastMove()));
        }
        setAvailable(getLastMove());
    }

    /**
     * Display a array of stones.
     * @param available The array of stones in the current game.
     */
    public void stoneDisplay(boolean[] available) {
        System.out.print("<"+ 1 + stoneAvailability(available[0]) + ">");
        for (int i = 0; i< available.length - 1; i++) {
            System.out.print(" <"+ (i+2) + stoneAvailability(available[i+1]) + ">");
        }
        System.out.println();
    }

    /**
     * @param aStone A stone in the current game, can be true (available for removing)
     *               or false (unavailable for removing).
     * @return A boolean value represents the availability of a stone.
     */
    public String stoneAvailability(boolean aStone) {
        if (aStone) {
            return ",*";
        }
        else {
            return ",x";
        }
    }

    /**
     * Ends the game. Player who removes the last stone wins,
     * which is opposite to the rule of original Nim game.
     * @param player1 A player in the current game.
     * @param player2 Another player in the current game.
     * @param turnCount The turn count when the game is ending.
     * @return A boolean variable that passes to another boolean variable isPlaying
     * that continues/ends the game play.
     */
    @Override
    public boolean gameOver(NimPlayer player1, NimPlayer player2, int turnCount) {
        System.out.println("\nGame Over");
        player1.setGamesPlayed(player1.getGamesPlayed() + 1);
        player2.setGamesPlayed(player2.getGamesPlayed() + 1);

        //Determines and announces the winner based on the turn count, if the turn
        // count is odd then player 2 wins, and vice versa.
        if (turnCount % 2 == 1) {
            announceWinner(player2);
        } else {
            announceWinner(player1);
        }
        return false;
    }
}
