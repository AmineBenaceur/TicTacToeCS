
/**
* Provides data fields and methods to create a Java data-type, representing a
* board of tic tac toe in a Java application.
* The overall purpose of this class exercise is for completing lab #2 postlab.
*
* @author M. Moussavi
* @version 1.0
* @since January 16, 2015
*/
public class Board implements Constants {
	/**
	* a double char array to hold the marks(moves) the players make. 
	*/
	private char theBoard[][];
	/**
	* The amount of marks in the board. 
	*a maximum of 9.
	*/
	private int markCount;

	public Board() {
		markCount = 0;
		theBoard = new char[3][];
		for (int i = 0; i < 3; i++) {
			theBoard[i] = new char[3];
			for (int j = 0; j < 3; j++)
				theBoard[i][j] = SPACE_CHAR;
		}
	}

	/**
	* gets the perticular mark from a coordinate on the board. Either X or O or a SPACE_CHAR. 
	* @param row: the row of intrest.
	* @param the column of intrest.  
	*/
	public char getMark(int row, int col) {
		return theBoard[row][col];
	}

	/**
	* Checks if the board is full of marks or in other words has become a tie. 
	*@return true if the board is full, false otherwise. 
	*/
	public boolean isFull() {
		return markCount == 9;
	}

	/**
	* Checks the board to see if player X has won the game.  
	*@return true if player x wins, false otherwsie.  
	*/
	public boolean xWins() {
		if (checkWinner(LETTER_X) == 1)
			return true;
		else
			return false;
	}
	/**
	* Checks the board to see if player O has won the game.  
	*@return true if player O wins, false otherwsie.  
	*/
	public boolean oWins() {
		if (checkWinner(LETTER_X) == 1)
			return true;
		else
			return false;
	}

	/**
	* prints the current board to the console. 
	*/
	public void display() {
		displayColumnHeaders();
		addHyphens();
		for (int row = 0; row < 3; row++) {
			addSpaces();
			System.out.print("    row " + row + ' ');
			for (int col = 0; col < 3; col++)
				System.out.print("|  " + getMark(row, col) + "  ");
			System.out.println("|");
			addSpaces();
			addHyphens();
		}
	}

	/**
	* places perticular mark from a coordinate on the board. Either X or O or a SPACE_CHAR. 
	* @param row: the row of intrest.
	* @param the column of intrest.  
	*/ 
	public void addMark(int row, int col, char mark) {
		
		theBoard[row][col] = mark;
		markCount++;
	}

	/**
	* Clears the board of any X or O marks replacing them with space chars.   
	*/ 
	public void clear() {
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				theBoard[i][j] = SPACE_CHAR;
		markCount = 0;
	}

	public String toString(){
		String s = "";
		// for (int j = 0; j < 3; j++)
		// 	s += "|col " + Integer.toString(j);
		// s += "\n";
		s = "\t|col 0|col 1|col 2 \n \t +-----+-----+-----+ \n \t |     |     |     | \n row 0   |  "+ Character.toString(theBoard[0][0]) +"  |   "+ Character.toString(theBoard[0][1]) +"  |   "+ Character.toString(theBoard[0][2]) +" | \n \t |     |     |     | \n \t +-----+-----+-----+ \n \t |     |     |     | \n row 1   |  "+ Character.toString(theBoard[1][0]) +"  |  "+ Character.toString(theBoard[1][1]) +"  |  "+ Character.toString(theBoard[1][2]) +"  | \n \t |     |     |     | \n \t +-----+-----+-----+ \n  \t |     |     |     | \n row 2   |  "+ Character.toString(theBoard[2][0]) +"  |  "+ Character.toString(theBoard[2][1]) +"  |  "+ Character.toString(theBoard[2][2]) +"  | \n \t |     |     |     | \n \t +-----+-----+-----+";
		return s;
	}

	/**
	* Checks to see if a player with a certain mark has won. 
	* @param mark: the mark of the player to be evaluated.
	*/ 
	int checkWinner(char mark) {
		int row, col;
		int result = 0;

		for (row = 0; result == 0 && row < 3; row++) {
			int row_result = 1;
			for (col = 0; row_result == 1 && col < 3; col++)
				if (theBoard[row][col] != mark)
					row_result = 0;
			if (row_result != 0)
				result = 1;
		}

		
		for (col = 0; result == 0 && col < 3; col++) {
			int col_result = 1;
			for (row = 0; col_result != 0 && row < 3; row++)
				if (theBoard[row][col] != mark)
					col_result = 0;
			if (col_result != 0)
				result = 1;
		}

		if (result == 0) {
			int diag1Result = 1;
			for (row = 0; diag1Result != 0 && row < 3; row++)
				if (theBoard[row][row] != mark)
					diag1Result = 0;
			if (diag1Result != 0)
				result = 1;
		}
		if (result == 0) {
			int diag2Result = 1;
			for (row = 0; diag2Result != 0 && row < 3; row++)
				if (theBoard[row][3 - 1 - row] != mark)
					diag2Result = 0;
			if (diag2Result != 0)
				result = 1;
		}
		return result;
	}

	/**
	* helper function to diplay the baord.   
	*/ 
	void displayColumnHeaders() {
		System.out.print("          ");
		for (int j = 0; j < 3; j++)
			System.out.print("|col " + j);
		System.out.println();
	}
	/**
	* helper function to diplay the baord.   
	*/ 

	void addHyphens() {
		System.out.print("          ");
		for (int j = 0; j < 3; j++)
			System.out.print("+-----");
		System.out.println("+");
	}
	/**
	* helper function to diplay the baord.   
	*/ 

	void addSpaces() {
		System.out.print("          ");
		for (int j = 0; j < 3; j++)
			System.out.print("|     ");
		System.out.println("|");
	}
}
