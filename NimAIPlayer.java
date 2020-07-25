/*
	NimAIPlayer.java
	
	This class is provided as a skeleton code for the tasks of 
	Sections 2.4, 2.5 and 2.6 in Project C. Add code (do NOT delete any) to it
	to finish the tasks. 
*/

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
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
		StringBuilder move = new StringBuilder();
		ArrayDeque<boolean[]> rounds = new ArrayDeque<>();
		
		rounds.add(available);

		return move.toString();
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
