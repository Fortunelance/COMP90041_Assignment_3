/*
	NimAIPlayer.java
	
	This class is provided as a skeleton code for the tasks of 
	Sections 2.4, 2.5 and 2.6 in Project C. Add code (do NOT delete any) to it
	to finish the tasks. 
*/

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.StringTokenizer;

public class NimAIPlayer extends NimPlayer implements Testable {
	// you may further extend a class or implement an interface
	// to accomplish the tasks.\

	private String lastAIMove;

	//Constructor.
	public NimAIPlayer() {
		super();
	}

	//Constructor.
	public NimAIPlayer(String username, String familyName, String givenName) {
		super(username,familyName,givenName);
	}

	/**
	 * Gets the last move of this player.
	 * @return The last move of this player.
	 */
	public String getLastAIMove() {
		return lastAIMove;
	}

	/**
	 * Sets the last move of this player.
	 * @param LastAIMove the last move of this player.
	 */
	public void setLastAIMove(String LastAIMove) {
		this.lastAIMove = LastAIMove;
	}

	public NimAIPlayer (NimPlayer otherPlayer) {
		super(otherPlayer);
	}

	/**
	 * AI players automatically decide the number of stones to be removed.
	 * Using a recursive algorithm. Let integer M be the upper bound of stones to be removed).
	 * Initial value of move is (current stone count - 1), and n is a very large integer.
	 * The algorithm examines if the current stone count is in section [2,M+1],
	 * [(M+1)+2,2(M+1)], [2(M+1)+2,3(M+1)], ......, [n(M+1)+2,(n+1)(M+1)], and so on......
	 * If the stone count is in any one of the sections, then there is guaranteed chance to win.
	 * if the stone count is in section [n(M+1)+2,(n+1)(M+1)], move = stone count - n(M+1) - 1.
	 * @param stoneCount Remaining stones count in the current game.
	 * @param upperBound Upper bound of the number of stones to be removed in the current game.
	 * @return Number of stones to be removed decided by an AI player.
	 */
	public int originalMove(int stoneCount, int upperBound, int	move) {
		if (stoneCount == 1) {
			return 1;
		}

		if (stoneCount > 1 && stoneCount < (upperBound+2)){
			return move;
		}

		else {
			move = move - (upperBound+1);
			stoneCount = stoneCount - (upperBound+1);
			return originalMove(stoneCount, upperBound, move);
		}
	}

	/**
	 * A method to decide how AI player to move in advanced mode of Nim game.
	 * @param available The Array represents the current stone pools.
	 * @param lastMove The String represents the last opponent's move.
	 * @return The String represents the move decided by AI player.
	 */
	public String advancedMove(boolean[] available, String lastMove) {
		// the implementation of the victory
		// guaranteed strategy designed by you

		//Index of the middle point of the Array.
		int mid = (available.length-1) / 2;

		//Going first without last move.
		if (lastMove.equals("")) {
			if (availableCount(available) == available.length) {
				if (available.length%2==1) {
					return (mid+1) + " 1";
				}

				else
					return (mid+1) + " 2";
			}
		}

		String move = "";
		//Processes the opponents's last move.
		StringTokenizer tokenizer = new StringTokenizer(lastMove);
		//Index of the least
		int lastIndex = Integer.parseInt(tokenizer.nextToken())-1;
		int lastNumber = Integer.parseInt(tokenizer.nextToken());

		//Processes this AI player's last move,
		//that is, the move before the opponent's last move.
		int lastAIIndex;
		//Index of the symmetry of the opponent's last move.
		int symLastIndex = (available.length-1) - lastIndex;

		int symLastAIIndex = 0;
		if (getLastAIMove()!=null) {
			StringTokenizer tokenizer1 = new StringTokenizer(getLastAIMove());
			lastAIIndex = Integer.parseInt(tokenizer1.nextToken())-1;
			symLastAIIndex = (available.length-1) - lastAIIndex;
		}

		//Index of a available stone.
		//Only useful for special cases, such as when 1 or 2 stones remain.
		int indexOfAStone = indexOfAStone(available);



		//If there is 1 stone left, wins the game.
		if (availableCount(available) == 1) {
			move = (indexOfAStone+1) + " 1";
		}

		//If there are two stones left, wins the game.
		else if (availableCount(available) == 2) {
			if (indexOfAStone > 0 && available[indexOfAStone-1]) {
				move = (indexOfAStone) + " 2";
			}

			else if ((indexOfAStone < (available.length - 1)) && available[indexOfAStone+1]) {
				move = (indexOfAStone+1) + " 2";
			}

			else
				move = (indexOfAStone+1) + " 1";
		}

		//Checks the middle point.
		else if (available[mid]) {
			if (availableCount(available) == available.length) {
				if (available.length%2==1) {
					move = (mid+1) + " 1";
				}

				else
					move = (mid+1) + " 2";
			}

			else {
				if (available[mid-1]) {
					move = mid + " 2";
				}

				else if (available[mid+1]) {
					move = (mid+1) + " 2";
				}

				else {
					move = (mid+1) + " 1";
				}
			}
		}

		//Tries the symmetrical strategy on the opponent.
		else if (symLastIndex > 0 && symLastIndex < (available.length -1)
				&& available[symLastIndex]) {
			if (lastNumber ==1) {
				move = (symLastIndex+1) + " 1";
			}

			else{
				if (available[symLastIndex+1]) {
					move = (symLastIndex+1) + " 2";
				}

				else if (available[symLastIndex-1]) {
					move = symLastIndex + " 2";
				}

				else
					move = symLastIndex + " 1";
			}
		}

		//Tries the symmetrical strategy on this AI player itself.
		else if (symLastAIIndex>-1 && symLastAIIndex < available.length && available[symLastAIIndex]) {
			if (symLastAIIndex > 0 && available[symLastAIIndex-1]) {
				move = symLastAIIndex +" 2";
			}

			else if (symLastAIIndex < (available.length-1) && available[symLastAIIndex+1]) {
				move = (symLastAIIndex+1) + " 2";
			}

			else
				move = (symLastAIIndex+1) + " 1";
		}

		//No strategy, pick a random available stone.
		else {
			move = (indexOfAStone + 1) + " 1";
		}

		setLastAIMove(move);
		return move;
	}

	/**
	 * Counts the current number of stones that are available to be removed.
	 * @param available The Array represents the current stones.
	 * @return An integer equals to the number of stones that are available to be removed.
	 */
	public int availableCount(boolean[] available) {
		int counter = 0;
		for (boolean x : available) {
			if (x) {
				counter ++;
			}
		}
		return counter;
	}

	/**
	 * Finds an index of a random available stone.
	 * @param available The Array represents the current stones.
	 * @return An integer equals to an index of a random available stone.
	 */
	public int indexOfAStone(boolean[] available) {
		for (int i = 0; i < available.length; i++) {
			if (available[i]) {
				return i;
			}
		}
		return 0;
	}
}
