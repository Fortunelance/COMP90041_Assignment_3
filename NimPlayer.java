import java.io.Serializable;

/**
 * A class of players of Nim game.
 * @author ZHejun Lyu 1128727
 */
public abstract class NimPlayer implements Comparable<NimPlayer>, Serializable {

    private String username;
    private String familyName;
    private String givenName;
    private int gamesPlayed;
    private int gamesWon;

    //Constructor.
    public NimPlayer(){

        username = null;
        familyName = null;
        givenName = null;
        gamesPlayed = 0;
        gamesWon = 0;

    }
    //Constructor.
    public NimPlayer (String username, String familyName, String givenName) {

        this.username = username;
        this.familyName = familyName;
        this.givenName = givenName;

    }

    public NimPlayer (NimPlayer otherPlayer) {

        username = otherPlayer.username;
        familyName = otherPlayer.familyName;
        givenName = otherPlayer.givenName;
        gamesPlayed = otherPlayer.gamesPlayed;
        gamesWon = otherPlayer.gamesWon;

    }

    /**
     * Gets the username of player.
     * @return the username of player.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets the given name of player.
     * @return Gets the given name of player.
     */
    public String getGivenName() {
        return givenName;
    }

    /**
     * Sets the given name of player.
     * @param givenName the given name of player to be used.
     */
    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    /**
     * Gets the given name of player.
     * @return the given name of player.
     */
    public String getFamilyName() {
        return familyName;
    }

    /**
     * Sets the family name of player.
     * @param familyName the family name of player to be used.
     */
    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    /**
     * Gets the number of games played.
     * @return the number of games played.
     */
    public int getGamesPlayed() {
        return gamesPlayed;
    }

    /**
     * Sets the number of games played.
     * @param gamesPlayed the number of games played.
     */
    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    /**
     * Gets the games won numbers.
     * @return Gets the games won numbers.
     */
    public int getGamesWon() {
        return gamesWon;
    }

    /**
     * Sets the number of games won.
     * @param gamesWon the number of games won to be set.
     */
    public void setGamesWon(int gamesWon) {
        this.gamesWon = gamesWon;
    }

    /**
     * Display the information of a player.
     */
    public void display() {
        System.out.println(getUsername()+","+getGivenName()+","+getFamilyName()+","+getGamesPlayed()+" games,"
                +getGamesWon()+" wins");
    }

    /**
     * Gets the win rate of a player.
     * @return the win rate of a player.
     */
    public int getWinRate () {
        if (getGamesPlayed()==0) {
            return 0;
        }
        else {
            return (int) Math.round(getGamesWon() * (100.0)/ getGamesPlayed());
        }
    }

    /**
     * Show a player's information that focuses on the win rate and games played.
     */
    public void show() {
        if (getGamesPlayed()<10) {
            System.out.printf("%-4s | 0%d games | %s %s\n", getWinRate()+"%", getGamesPlayed(),
                    getGivenName(), getFamilyName());
        }
        else {
            System.out.printf("%-4s | %d games | %s %s\n", getWinRate()+"%", getGamesPlayed(),
                    getGivenName(), getFamilyName());
        }
    }

    @Override
    public int compareTo(NimPlayer otherPlayer) {
        if (otherPlayer == null)
            return -1;

        if (getWinRate() < otherPlayer.getWinRate()) {
            return 1;
        }

        else if (getWinRate() > otherPlayer.getWinRate()) {
            return -1;
        }

        else if (getWinRate() == otherPlayer.getWinRate()) {
            return getUsername().compareTo(otherPlayer.getUsername());
        }

        else
            return 0;
    }

}
