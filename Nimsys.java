import java.io.*;
import java.util.*;

/**
 * A class maintains a list of instances of NimPlayer with their attributes and variables.
 * Contains one instance of NimGame which instances of NimPlayer participates.
 * Can rank and sort instances of NimPlayer based on certain conditions.
 * @author Zhejun Lyu 1128727
 */
public class Nimsys {
    //Initials an array list that stores the instances of NimPlayer.
    private final static ArrayList<NimPlayer> players = new ArrayList<>();

    //A scanner that receives input from the users.
    static Scanner console = new Scanner(System.in);

    //An enumeration contains all the values of the valid commands or the valid initials of the commands.
    public enum Command {ADDPLAYER, ADDAIPLAYER, EDITPALYER, REMOVEPLAYER, DISPLAYPLAYER,
        RESETSTATS, RANKINGS, STARTGAME, STARTADVANCEDGAME, EXIT}

    public static void main(String[] args) {
        //The welcome message.
        System.out.print("Welcome to Nim\n\n$");
        //Initializes a Command variable.
        Command command;
        //Receives various inputs from the user for one or multiple time(s).
        readStatsFile();
        while (true){
            //Scans the input String.
            String input = console.nextLine();
            //Breaks the input String into tokens.
            StringTokenizer tokenizer = new StringTokenizer(input);
            //Initialises a String variable to store the initial token of the input String.
            String commandInitials = "";
            try {
                //Picks the initial token of the input String.
                commandInitials = tokenizer.nextToken();
                //Compares the initial token of the input String with the valid commands.
                command = Command.valueOf(commandInitials.toUpperCase());
                //Takes according actions based on the command
                switch (command) {
                    case ADDPLAYER:
                        addPlayer(input);
                        break;
                    case ADDAIPLAYER:
                        addAIPlayer(input);
                        break;
                    case EDITPALYER:
                        editPlayer(input);
                        break;
                    case REMOVEPLAYER:
                        removePlayer(input);
                        break;
                    case DISPLAYPLAYER:
                        displayPlayer(input);
                        break;
                    case RESETSTATS:
                        resetStats(input);
                        break;
                    case RANKINGS:
                        rankings(input);
                        break;
                    case STARTGAME:
                        startGame(input);
                        break;
                    case STARTADVANCEDGAME:
                        startAdvancedGame(input);
                        break;
                    case EXIT:
                        writeStatsFile();
                        System.out.println();
                        System.exit(0);
                        break;
                }
            }
            catch (IllegalArgumentException ilae) {
                System.out.print("'"+ commandInitials + "' is not a valid command.\n\n$");
            }
            catch (NoSuchElementException nse) {
                System.out.print("'"+ input + "' is not a valid command.\n\n$");
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
     * @param input includes the player's information.
     */
    public static void addPlayer(String input) {
        try {
            if (input.length()<11) {
                throw new InvalidArgumentsNumberException();
            }

            StringTokenizer tokenizer = new StringTokenizer(input.substring(10), ",");

            if (tokenizer.countTokens()<3) {
                throw new InvalidArgumentsNumberException();
            }

            String username = tokenizer.nextToken();
            String familyName = tokenizer.nextToken();
            String givenName = tokenizer.nextToken();
            NimHumanPlayer player = new NimHumanPlayer(username, familyName, givenName);

            if (indexOfPlayer(username)<0) {
                players.add(player);
                System.out.print("\n$");
            }
            else {
                System.out.print("The player already exists.\n\n$");
            }
        }
        catch (InvalidArgumentsNumberException e) {
            System.out.print(e.getMessage());
        }
    }

    /**
     * Adds an AI player which is an instance of the sub class of NimPlayer class.
     * @param input The String that specify the attributes of the AI player.
     */
    public static void addAIPlayer(String input) {
        try {
            if (input.length()<13) {
                throw new InvalidArgumentsNumberException();
            }

            StringTokenizer tokenizer = new StringTokenizer(input.substring(12), ",");

            if (tokenizer.countTokens()<3) {
                throw new InvalidArgumentsNumberException();
            }

            String username = tokenizer.nextToken();
            String familyName = tokenizer.nextToken();
            String givenName = tokenizer.nextToken();
            NimAIPlayer player = new NimAIPlayer(username, familyName, givenName);

            if (indexOfPlayer(username)<0) {
                players.add(player);
                System.out.print("\n$");
            }
            else {
                System.out.print("The player already exists.\n\n$");
            }
        }
        catch (InvalidArgumentsNumberException e) {
            System.out.print(e.getMessage());
        }
    }

    /**
     * Edits a player's information.
     * @param input includes first name and last name.
     */
    public static void editPlayer(String input) {
        try {
            if (input.length()<12) {
                throw new InvalidArgumentsNumberException();
            }

            StringTokenizer tokenizer = new StringTokenizer(input.substring(11), ",");

            if (tokenizer.countTokens()<3) {
                throw new InvalidArgumentsNumberException();
            }

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
        catch (InvalidArgumentsNumberException e) {
            System.out.print(e.getMessage());
        }
    }

    /**
     * Removes a player from the arraylist.
     * @param input includes the player's information.
     */
    public static void removePlayer(String input) {
        if (input.equals("removeplayer")) {
            System.out.println("Are you sure you want to remove all players? (y/n)");
            String confirm = console.nextLine();
            if (confirm.equals("y")) {
                players.clear();
            }
            System.out.print("\n$");
        }
        else {
            try {
                if (input.length()<13) {
                    throw new InvalidArgumentsNumberException();
                }

                String username = input.substring(13);
                if (indexOfPlayer(username)<0) {
                    System.out.print("The player does not exist.\n\n$");
                }
                else {
                    players.remove(indexOfPlayer(username));
                    System.out.print("\n$");
                }
            }
            catch (InvalidArgumentsNumberException e) {
                System.out.print(e.getMessage());
            }
        }
    }

    /**
     * Displays player's information.
     * @param input instruct which player to display or all the players will be displayed.
     */
    public static void displayPlayer(String input) {
        //Displays all the players based on the according command.
        if (input.equals("displayplayer")) {
            displayAll();
        }
        else {
            try {
                if (input.length()<13) {
                    throw new InvalidArgumentsNumberException();
                }

                String username = input.substring(14);
                if (indexOfPlayer(username)<0) {
                    System.out.print("The player does not exist.\n\n$");
                } else {
                    players.get(indexOfPlayer(username)).display();
                    System.out.print("\n$");
                }
            }
            catch (InvalidArgumentsNumberException e) {
                System.out.print(e.getMessage());
            }
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
     * Resets a player's win rate and games played.
     * @param input includes which player to be reset.
     */
    public static void resetStats(String input) {
        if (input.equals("resetstats")) {
            resetAllStats();
        }
        else {

            try {
                if (input.length()<12) {
                    throw new InvalidArgumentsNumberException();
                }

                String username = input.substring(11);
                if (indexOfPlayer(username)<0) {
                    System.out.print("The player does not exist.\n\n$");
                }
                else {
                    players.get(indexOfPlayer(username)).setGamesPlayed(0);
                    players.get(indexOfPlayer(username)).setGamesWon(0);
                    System.out.print("\n$");
                }
            }
            catch (InvalidArgumentsNumberException e) {
                System.out.print(e.getMessage());
            }
        }
    }

    /**
     * Resets all the players' win rate and games played.
     */
    public static void resetAllStats() {
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
     * Displays a ranking list of all the players based on the win rate and games played.
     * @param input determines the ranking order, which can be either ascending or descending.
     */
    public static void rankings(String input) {
        rankPlayer();

        if (input.equals("rankings")) {
            if (players.size()<10) {
                for (NimPlayer player : players) {
                    player.show();
                }
            }
            else {
                for (int i = 0; i < 10; i++) {
                    players.get(i).show();
                }
            }
            System.out.print("\n$");
        }

        else if (input.substring(9).equals("desc")) {
            if (players.size()<10) {
                for (NimPlayer player : players) {
                    player.show();
                }
            }
            else {
                for (int i = 0; i<10 ; i++) {
                    players.get(i).show();
                }
            }
            System.out.print("\n$");
        }

        else  if (input.substring(9).equals("asc")) {
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

        else if (input.equals("rankings ")) {
            //Throws and catch an exception when there is no valid number of arguments.
            try {
                throw new InvalidArgumentsNumberException();
            }
            catch (InvalidArgumentsNumberException e) {
                System.out.print(e.getMessage());
            }
        }

        else {
            //Throws and catch an exception when the user enters an invalid command.
            try {
                throw new InvalidCommandException("InvalidCommandException", input);
            }
            catch (InvalidCommandException e) {
                System.out.print(e.getInvalidCommand()+ " is not a valid command.\n\n$");
            }
        }
    }

    /**
     * Start the Nim game.
     * @param input includes the information needed to start a game.
     */
    public static void startGame(String input) {

        StringTokenizer tokenizer = new StringTokenizer(input.substring(10), ",");

        int expectedNumberOfArguments = 4;

        try{
            if (tokenizer.countTokens() < expectedNumberOfArguments) {
                throw new InvalidArgumentsNumberException();
            }
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
                NimOriginalGame game = new NimOriginalGame(stoneCount,
                        upperBound,
                        players.get(indexOfPlayer(username1)),
                        players.get(indexOfPlayer(username2)));
                game.play();
            }
        }
        catch (InvalidArgumentsNumberException iae) {
            System.out.print(iae.getMessage());
        }
        catch (NumberFormatException ne) {
            System.out.print("'" + input + "' is not a valid command.\n\n$");
        }
    }

    /**
     * Start an advanced version of Nim game
     * @param input The input that has the information to start the game
     */
    public static void startAdvancedGame(String input) {
        StringTokenizer tokenizer = new StringTokenizer(input.substring(18), ",");

        int expectedNumberOfArguments = 3;

        try{
            if (tokenizer.countTokens() < expectedNumberOfArguments) {
                throw new InvalidArgumentsNumberException();
            }
            String stoneCountString= tokenizer.nextToken();
            int stoneCount = Integer.parseInt(stoneCountString);

            String username1 = tokenizer.nextToken();
            String username2 = tokenizer.nextToken();

            if (indexOfPlayer(username1)<0||indexOfPlayer(username2)<0) {
                System.out.print("One of the players does not exist.\n\n$");
            }
            else {
                NimAdvancedGame game = new NimAdvancedGame(stoneCount,
                        players.get(indexOfPlayer(username1)),
                        players.get(indexOfPlayer(username2)));
                game.play();
            }
        }
        catch (InvalidArgumentsNumberException iae) {
            System.out.print(iae.getMessage());
        }
        catch (NumberFormatException ne) {
            System.out.print("'" + input + "' is not a valid command.\n\n$");
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
     * Ranks the player based on win rate.
     */
    public static void rankPlayer() {

        for (int i=0; i<players.size()-1; i++) {
            int precedent = i;
            for (int j=i+1; j < players.size(); j++) {
                if (players.get(j).compareTo(players.get(precedent))<0) {
                    precedent = j;
                }
            }
            interchangePlayer(players, precedent, i);
        }

    }

    /**
     * Interchanges two elements' indexes in an array list that contains NimPlayer's instances.
     * @param players Array list which contains NimPlayer's instances.
     * @param precedent One of the array list index.
     * @param i Another array list index.
     */
    public static void interchangePlayer(ArrayList<NimPlayer> players, int precedent, int i) {
        if (players.get(i) instanceof NimHumanPlayer) {
            NimHumanPlayer temp = new NimHumanPlayer(players.get(i));
            players.set(i, players.get(precedent));
            players.set(precedent, temp);
        }
        else {
            NimAIPlayer temp = new NimAIPlayer(players.get(i));
            players.set(i, players.get(precedent));
            players.set(precedent, temp);
        }
    }

    /**
     * Reads the files that stores an ArrayList that contains instances of NimPlayer.
     * Adds all the instances of NimPlayer to the initialized empty ArrayList called players.
     * The file's name should be "players.dat".
     * Does nothing if there is no such file.
     */
    public static void readStatsFile() {
        String filename = "players.dat";
        File fileObject = new File(filename);

        if (fileObject.exists()) {
            ObjectInputStream inputStream;

            try {
                inputStream = new ObjectInputStream(new FileInputStream(filename));
                Object savedPlayers = inputStream.readObject();
                players.addAll((ArrayList<NimPlayer>) savedPlayers);
            }
            catch (IOException | ClassNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Stores the ArrayList players to a file.
     * The file's name should be "players.dat".
     * A file with the same name will be created if there is no such file exits.
     */
    public static void writeStatsFile() {
        String filename = "players.dat";
        ObjectOutputStream outputStream;

        try {
            outputStream = new ObjectOutputStream(new FileOutputStream(filename));
            outputStream.writeObject(players);
        }
        catch (java.io.IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
