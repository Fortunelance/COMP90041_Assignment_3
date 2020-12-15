# COMP90041_Assignment_3
Implements the Nim game with Java

Nim is a two-player game, and the rules of the version used
here are as follows:
• The game begins with a number of objects (e.g., stones placed on a table).
• Each player takes turns removing stones from the table.
• On each turn, a player must remove at least one stone. In addition, there is an upper bound on the
number of stones that can be removed in a single turn. For example, if this upper bound is 3, a
player has the choice of removing 1, 2 or 3 stones on each turn.
• The game ends when there are no more stones remaining. The player who removes the last stone,
loses. The remaining player wins.
• Both the initial number of stones, and the upper bound on the number that can be removed, can be
varied from game to game, and must be chosen before a game commences.

How to play:
• addplayer - Allows new players to be added to the game. 
Syntax: addplayer username,family_name,given_name
• addaiplayer - Add an AI player that would take the best strategy to win.
Syntax: addaiplayer username,family_name,given_name
• removeplayer - Allows players to be removed from the game. The username of the player to be
removed is given as an argument to the command. If no username is given, the command would
remove all players.
Syntax: removeplayer [username]
• editplayer - Allows player details to be edited. 
Syntax: editplayer username,new_family_name,new_given_name
• resetstats - Allows player statistics to be reset. 
Syntax: resetstats [username]
• displayplayer - Displays player information. 
Syntax: displayplayer [username]
• rankings - Outputs a list of player rankings.
Syntax: rankings [asc|desc]
• startgame - Creates and commences a game of Nim
Syntax: startgame initialstones,upperbound,username1,username2
• exit - Exits the Nimsys program.
Syntax: exit
