import java.util.Scanner;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * A class maintains a list of instances of NimPlayer with their attributes and variables.
 * Contains one instance of NimGame which instances of NimPlayer participates.
 * Can rank and sort instances of NimPlayer based on certain conditions.
 * @author Zhejun Lyu 1128727
 */
public class Nimsys {

    //Initials an array list that stores the instances of NimPlayer.
    private static ArrayList<NimPlayer> players = new ArrayList<NimPlayer>();

    //A boolean value to controls the input choices.
    private static boolean idle = true;

    //A scanner that receives command from the users.
    public static Scanner console = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.print("Welcome to Nim\n\n$");

        //Receives various command from the user multiple times.
        while (idle){

            String command = console.nextLine();

            if (command.equals("exit")) {
                System.out.println();
                System.exit(0);
            }

            else if (command.equals("rankings")||command.equals("rankings desc")||command.equals("rankings asc")) {
                rankings(command);
            }

            else if (command.equals("resetstats")) {
                resetAll();
            }

            else if (command.length()>10 && command.substring(0,10).equals("addplayer ")) {
                addPlayer(command);
            }

            else if (command.length()>11 && command.substring(0,11).equals("editplayer ")) {
                editPlayer(command);
            }

            else if (command.length()>11 && command.substring(0,11).equals("resetstats ")) {

                resetPlayer(command);
            }

            else if (command.equals("removeplayer")) {
                removePlayer(command);
            }

            else if (command.length()>13 && command.substring(0,13).equals("removeplayer ")){

                removePlayer(command);
            }

            else if (command.equals("displayplayer")) {
                displayAll();
            }

            else if (command.length()>14 && command.substring(0,14).equals("displayplayer ")) {
                displayPlayer(command);
            }

            else if (command.length()>10 && command.substring(0,10).equals("startgame ")) {
                idle = false;
                startGame(command);
            }

        }

    }

    /**
     * Gets the index of a player in the players array list.
     * @param enteredUsername the username of the player.
     * @return the index of a player in the players array list.
     */
    public static int indexOfPlayer(String enteredUsername) {

        for (int i=0; i<players.size(); i++) {
            if (players.get(i).getUsername().compareTo(enteredUsername)==0) {
                //return the index
                return i;
            }
        }
        return -1;

    }

    /**
     * Adds a player to the array list.
     * @param command includes the player's information.
     */
    public static void addPlayer(String command) {

        StringTokenizer tokenizer = new StringTokenizer(command.substring(10), ",");
        String username = tokenizer.nextToken();
        String familyName = tokenizer.nextToken();
        String givenName = tokenizer.nextToken();
        NimPlayer player = new NimPlayer(username, familyName, givenName);

        if (indexOfPlayer(username)<0) {
            players.add(player);
            System.out.print("\n$");
        }
        else {
            System.out.print("The player already exists.\n\n$");
        }

    }

    /**
     * Removes a player from the arraylist.
     * @param command includes the player's information.
     */
    public static void removePlayer(String command) {

        if (command.equals("removeplayer")) {
            System.out.println("Are you sure you want to remove all players? (y/n)");
            String confirm = console.nextLine();
            if (confirm.equals("y")) {
                players.clear();
            }
            System.out.print("\n$");
        }
        else {
            String username = command.substring(13);

            if (indexOfPlayer(username)<0) {
                System.out.print("The player does not exist.\n\n$");
            }
            else {
                players.remove(indexOfPlayer(username));
                System.out.print("\n$");
            }
        }

    }

    /**
     * Edits a player's information.
     * @param command includes first name and last name.
     */
    public static void editPlayer(String command) {

        StringTokenizer tokenizer = new StringTokenizer(command.substring(11), ",");

        String username = tokenizer.nextToken();
        String familyName = tokenizer.nextToken();
        String givenName = tokenizer.nextToken();

        if (indexOfPlayer(username)<0) {
            System.out.print("The player does not exist.\n\n$");
        }
        else {
            players.get(indexOfPlayer(username)).setFamilyName(familyName);
            players.get(indexOfPlayer(username)).setGivenName(givenName);
            System.out.print("\n$");
        }

    }

    /**
     * Resets a player's win rate and games played.
     * @param command includes which player to be reset.
     */
    public static void resetPlayer(String command) {

        String username = command.substring(11);

        if (indexOfPlayer(username)<0) {
            System.out.print("The player does not exist.\n\n$");
        }
        else {
            players.get(indexOfPlayer(username)).setGamesPlayed(0);
            players.get(indexOfPlayer(username)).setGamesWon(0);
            System.out.print("\n$");
        }

    }

    /**
     * Resets all the players' win rate and games played.
     */
    public static void resetAll() {

        System.out.println("Are you sure you want to reset all player statistics? (y/n)");

        String confirm = console.nextLine();

        if (confirm.equals("y")) {
            for (NimPlayer eachPlayer : players) {
                eachPlayer.setGamesWon(0);
                eachPlayer.setGamesPlayed(0);
            }
        }
        System.out.print("\n$");

    }

    /**
     * Displays player's information.
     * @param command instruct which player to display.
     */
    public static void displayPlayer(String command) {

        String username = command.substring(14);

        if (indexOfPlayer(username)<0) {
            System.out.print("The player does not exist.\n\n$");
        } else {
            players.get(indexOfPlayer(username)).display();
            System.out.print("\n$");
        }

    }

    /**
     * Displays all the player's information based on usernames alphabetically.
     */
    public static void displayAll() {

        sortPlayers();

        for (NimPlayer eachPlayer : players) {
            eachPlayer.display();
        }
        System.out.print("\n$");

    }

    /**
     * Displays a ranking list of all the players based on the win rate and games played.
     * @param command
     */
    public static void rankings(String command) {

        rankPlayer(players);
        if (command.equals("rankings")) {
            if (players.size()<10) {
                for (int i = 0; i < players.size(); i++) {
                    players.get(i).show();
                }
            }
            else {
                for (int i = 0; i < 10; i++) {
                    players.get(i).show();
                }
            }
            System.out.print("\n$");
        }

        else if (command.substring(9).equals("desc")) {
            if (players.size()<10) {
                for (int i = 0; i<players.size(); i++) {
                    players.get(i).show();
                }
            }
            else {
                for (int i = 0; i<10 ; i++) {
                    players.get(i).show();
                }
            }
            System.out.print("\n$");
        }

        else {
            if (players.size()<10) {
                for (int i = players.size()-1; i>-1 ; i--) {
                    players.get(i).show();
                }
            }
            else {
                for (int i = players.size()-1; i> players.size()-10 ; i--) {
                    players.get(i).show();
                }
            }
            System.out.print("\n$");
        }

    }

    /**
     * Sorts the players alphabetically.
     */
    public static void sortPlayers() {

        for (int i=0; i<players.size()-1; i++) {
            int precedent = i;
            for (int j=i+1; j< players.size(); j++) {
                if (players.get(j).getUsername().compareTo(players.get(precedent).getUsername())<0) {
                    precedent = j;
                }
            }
            interchangePlayer(players, precedent, i);
        }

    }

    /**
     * Ranks the player based on winrate.
     */
    public static void rankPlayer(ArrayList<NimPlayer> players) {

        for (int i=0; i<players.size()-1; i++) {
            int precedent = i;
            for (int j=i+1; j< players.size(); j++) {
                if (players.get(j).compareTo(players.get(precedent))<0) {
                    precedent = j;
                }
                interchangePlayer(players, precedent, i);
            }
        }

    }

    public static void interchangePlayer(ArrayList<NimPlayer> players, int precedent, int i) {
        NimPlayer temp = new NimPlayer(players.get(i));
        players.set(i, players.get(precedent));
        players.set(precedent, temp);
    }

    /**
     * Start the Nim game.
     * @param command includes the information needed to start a game.
     */
    public static void startGame(String command) {

        StringTokenizer tokenizer = new StringTokenizer(command.substring(10), ",");

        String stoneCountString= tokenizer.nextToken();
        int stoneCount = Integer.parseInt(stoneCountString);

        String upperBoundString = tokenizer.nextToken();
        int upperBound = Integer.parseInt(upperBoundString);

        String username1 = tokenizer.nextToken();
        String username2 = tokenizer.nextToken();

        if (indexOfPlayer(username1)<0||indexOfPlayer(username2)<0) {
            System.out.print("One of the players does not exist.\n\n$");
        }
        else {
            NimGame game = new NimGame(stoneCount, upperBound, players.get(indexOfPlayer(username1)),
                    players.get(indexOfPlayer(username2)));
            game.play();
        } idle = true;
    }

}
