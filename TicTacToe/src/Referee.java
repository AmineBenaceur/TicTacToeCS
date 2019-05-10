import java.io.*;
/**
* Provides data fields and methods to create a Java data-type, representing a
* Referee of tic tac toe in a Java application. 
* The overall purpose of this class exercise is for completing lab #2 postlab.
*
* @author A. Benaceur
* @version 1.0
* @since Feb 1 , 2018
*/

public class Referee{
	/**
	* The player with X as their mark. 
	*/
	Player xplayer;
	/**
	* The player with O as their mark. 
	*/
	Player oPlayer;
	/**
	* The board the game is being played in. 
	*/
	Board board;

	/**
	* Assigns a board for the referee to manage. 
	*/
	public void setBoard(Board b ){
		board=b;
	}
	/**
	* Assigns a player to play with mark 0. 
	*/
	public void setoPlayer(Player o){
		oPlayer=o;
	}
	/**
	* Assigns a player to play with mark X. 
	*/
	public void setxPlayer(Player x){
		xplayer=x;
	}

	public void sendBoardToPlayers(){
			oPlayer.out.println(boardToDisplay());
			xplayer.out.println(boardToDisplay());
	
	}
	
	public String boardToDisplay() {
		String line = "";
		for(int i= 0 ; i < 3; i ++) 
			for (int j = 0 ; j < 3 ; j++)
				line += Character.toString(board.getMark(i, j) );
		line += "*";
		return line;
	}

	/**
	* function to pass turns between the xplayer and oplayer as long as the game is not over. 
	*/
	
	public void runTheGame(){
		
		xplayer.setOpponent(oPlayer);
		oPlayer.setOpponent(xplayer);
		board.display();
		sendBoardToPlayers();
		
		while(!board.xWins() && !board.oWins() && !board.isFull() ){
			xplayer.play();
			oPlayer.play();

		
			}
		}

}

