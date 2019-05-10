import java.io.*;
/**
* Provides data fields and methods to create a Java data-type, representing a
* Player of tic tac toe in a Java application.
* The overall purpose of this class exercise is for completing lab #2 postlab.
*
* @author A. Benaceur
* @version 1.0
* @since Feb 1 , 2018
*/


public class Player{
	/**
	* The name of a Person playing. 
	*/
	String name;
	/**
	* mark (X OR O) the player is using.  
	*/
	char mark;
	/**
	* The board the game is being played in. 
	*/
	Board board;
	/**
	* The opponent of type player this player is playing against. 
	*/
	Player opponent;

	/**
	* Printwriter to communicate 
	*/
	PrintWriter out;

		/**
	* Printwriter to read input
	*/
	BufferedReader in;

	/**
	* Constructs a Person object with the specified values for name and mark(either X or O is acceptable).  
	*@param name the Player object's name
	*@param mark that the player has. Either X or O. 
	*/
	public Player(String name, char mark, BufferedReader i, PrintWriter o){
		this.name = name;
		this.mark=mark;
		this.in=i;
		this.out = o;
		
	}


	/**
	* Assigns a board to be played on to a player. 
	* @param board to be played on of type Board.
	*/
	public void setBoard(Board b){
		this.board = b;
	}

	/**
	* Assigns an opponent board to be played on to a player. 
	*@param board to be played on of type Board.
	*/
	public void setOpponent(Player p ){
		this.opponent=p;
	}
	/**
	* returns the mark of a plyer. 
	*@return a char: either X, O. 
	*/
	public char getMark(){
		return this.mark;
	}
	/**
	* returns the name of a plyer. 
	*@return name of the player in a String;
	*/
	public String getName(){
		return this.name;
	}

	/**
	* takes a plyers input and make sure its a valid move, then makes the move.  
	*/
	public void makeMove(){
		try{
			int row=-1;
			int col=-1;
			
			boolean valid =false;

			while(!valid){
				out.println( name + ": Enter which Row you would like to place a mark?");
				while(true){
					row = Integer.parseInt(in.readLine());
					if(row > 2 || row < 0){
						this.out.println("Choose either 0, 1 or 2, try again.");
						continue;
					}
					else 
						break;
				}

				out.println("Which Column you would like to place a mark?");
				while(true){
					col = Integer.parseInt(in.readLine());
					if(col >2 || col < 0){
						out.println("Choose either 0, 1 or 2, try again.");
						continue;
					}
					else 
						break;
				}
				if (board.getMark(row,col) == 	Constants.SPACE_CHAR){
					valid=true;
				}
				else {
					out.println("Invalid move: choose an empty space please, try again. ");
					continue;
				}
			}

			board.addMark(row,col,this.mark);
			out.println(boardToDisplay());
			opponent.out.println(boardToDisplay());

		}catch(IOException e){
			e.printStackTrace();
		}
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
	* makes sure the game is not over before asking a player to make a move, in case it is over it announces a winner or a tie.  
	*/
	public void play(){
			
		if( !board.xWins() && !board.oWins() && !board.isFull() ){
			makeMove();
			board.display();
		}

		if( board.xWins() ){
			if(mark == Constants.LETTER_X){
				out.println(name + " HAS WON !");
				return;
			}
			if (opponent.getMark() == Constants.LETTER_X){
				out.println(opponent.getName() + "HAS WON !");
			return;
		}
		if( board.oWins()){
			if(mark == Constants.LETTER_O){
				out.println(name + " HAS WON ! ");
				return;
			}
			if (opponent.getMark() == Constants.LETTER_O){
			out.println(opponent.getName() + " HAS WON !");
			return;
			}
		}
		if(board.isFull()){
			out.println("IT'S A TIE! ");
			return;
		}


	}

	}
}