
import java.io.*;

/**
* Provides data fields and methods to create a Java data-type, representing a
* Game of tic tac toe in a Java application.
* The overall purpose of this class exercise is for completing lab #2 postlab.
*
* @author M. Moussavi
* @version 1.0
* @since January 16, 2015
*/

public class Game implements Constants {
	int id;
	/**
	* The board the game is being played in. 
	*/
	public Board theBoard;
	/**
	* The referee class in charge of managing the game. 
	*/
	public Referee theRef;
	
	/**
	* Constructs a game between 2 players.  
	*/

	public Player playerX;

	public Player playerO;

    public Game( ) {
        theBoard  = new Board();
        ++id;
	}
    /**
	* Assigns a referee to the current game.  
	*@param Referee r: the referee to run the game.  
	*/
    public void appointReferee(Referee r) throws IOException {
        theRef = r;
    	theRef.runTheGame();
    }

    public void connectX(BufferedReader i, PrintWriter o){
    	String line = "";
	    try{
	    	o.println("	Welcome to Tic Tac Toe, You are the first player to connect and will be player X");
	    	o.println("What is Your name, player X?");
	    	line = i.readLine();
	    	o.println("Great! " + line + " has successfully connected " );
	    	playerX = new Player(line,Constants.LETTER_X,i,o);
	    	playerX.setBoard(this.theBoard);

	    }catch(IOException e ){
	    	System.out.println("Error in connectX " + e.getMessage());
	    }
	}

	    public void connectO(BufferedReader i,PrintWriter o){
    	String line = "";
	    try{
	    	o.println("	Welcome to Tic Tac Toe, You are the second player to connect and will be player O ");
	    	o.println("What is Your name, player O?");
	    	line = i.readLine();
	    	o.println("Great! " + line + " has successfully connected " );
	    	playerO = new Player(line,Constants.LETTER_O,i,o);
	    	playerO.setBoard(this.theBoard);

	    }catch(IOException e ){
	    	System.out.println("Error in connectO " + e.getMessage());
	    }
	}

	public void setOpponents(){
		
		playerX.setOpponent(this.playerO);
		playerO.setOpponent(this.playerX);
	}

	
	/**
	* A main function to Test run all the classes. 
	* IGNORE ALL THIS CODE FOR LAB ASSIGNMENT 6
	*/
			// public void main(String[] args ) throws IOException {
			// 	System.out.println("------------------------------------------------------------");
			// 	Referee theRef;
			// 	Player xPlayer, oPlayer;
			// 	BufferedReader stdin;	
			// 	Game theGame = new Game();
			// 	stdin = new BufferedReader(new InputStreamReader(System.in));
			// 	System.out.print("\nPlease enter the name of the \'X\' player: ");
			// 	String name= stdin.readLine();
			// 	while (name == null) {
			// 		System.out.print("Please try again: ");
			// 		name = stdin.readLine();
			// 	}

			// 	xPlayer = new Player(name, LETTER_X);
			// 	xPlayer.setBoard(theGame.theBoard);
				
			// 	System.out.print("\nPlease enter the name of the \'O\' player: ");
			// 	name = stdin.readLine();
			// 	while (name == null) {
			// 		System.out.print("Please try again: ");
			// 		name = stdin.readLine();
			// 	}
				
			// 	oPlayer = new Player(name, LETTER_O);
			// 	oPlayer.setBoard(theGame.theBoard);
				
			// 	theRef = new Referee();
			// 	theRef.setBoard(theGame.theBoard);
			// 	theRef.setoPlayer(oPlayer);
			// 	theRef.setxPlayer(xPlayer);
		        
		 //        theGame.appointReferee(theRef);
			// }
	

}
