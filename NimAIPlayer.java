/*
	NimAIPlayer.java
	
	This class is provided as a skeleton code for the tasks of 
	Sections 2.4, 2.5 and 2.6 in Project C. Add code (do NOT delete any) to it
	to finish the tasks. 
*/

import java.io.Serializable;

public class NimAIPlayer extends NimPlayer implements Testable {
	// you may further extend a class or implement an interface
	// to accomplish the tasks.\
	private int move;

	public NimAIPlayer() {
		super();
		this.move = 0;
	}

	public NimAIPlayer(String username, String familyName, String givenName) {
		super(username,familyName,givenName);
		this.move = 0;
	}

	public NimAIPlayer (NimPlayer otherPlayer) {
		super(otherPlayer);
		this.move = 0;
	}

	public static int removeStone(int stoneCount, int upperBound) {
		if (stoneCount == upperBound+2 || stoneCount == 1) {
			return 1;
		}

		else if (stoneCount < upperBound+2) {
			if (stoneCount == upperBound +1) {
				return upperBound;
			}

			else
				return stoneCount-1;
		}

		else if (stoneCount > 2*upperBound+2) {
				return upperBound;
		}

		else {
			return stoneCount-upperBound-2;
		}
	}
	
	public String advancedMove(boolean[] available, String lastMove) {
		// the implementation of the victory
		// guaranteed strategy designed by you
		String move = "";
		
		return move;
	}
}
