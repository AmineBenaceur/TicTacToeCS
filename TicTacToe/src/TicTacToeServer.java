import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.concurrent.*;



public class TicTacToeServer implements Runnable {
	static Scanner keyboard;
	BufferedReader xIn,oIn;
	PrintWriter oOut,xOut;
	Socket sX,sO;
	ServerSocket ss;
	int portnum;
	String server = "localhost";
	Game theGame;

	public TicTacToeServer(int number){
		portnum = number;
		try{	
			ss = new ServerSocket(portnum);
			

		}catch(IOException e){
			System.out.println("ERROR");
		}

	}
	public void run(){
		try{
			System.out.println("SERVER RUNNNING...");
			//create game to play
			Game theGame = new Game();
			System.out.println("");
			//coonnect the x player
			sX = ss.accept();
			xIn= new BufferedReader(new InputStreamReader(sX.getInputStream()));
			xOut= new PrintWriter(sX.getOutputStream(),true);
			theGame.connectX(xIn,xOut);

			//connect the O player
			sO= ss.accept();
			oIn= new BufferedReader(new InputStreamReader(sO.getInputStream()));
			oOut= new PrintWriter(sO.getOutputStream(),true);
			theGame.connectO(oIn,oOut);

			//connect refree, tie up loose ends
			theGame.setOpponents();
			theGame.theRef = new Referee();
			theGame.theRef.setBoard(theGame.theBoard);
			theGame.theRef.setoPlayer(theGame.playerO);
			theGame.theRef.setxPlayer(theGame.playerX);
			//start Game
			theGame.appointReferee(theGame.theRef);
		}catch(IOException e ){
			e.printStackTrace();
		} 
	}

	public static void main(String[] args) {
		ExecutorService pool = Executors.newFixedThreadPool(10);
		TicTacToeServer server = new TicTacToeServer(9090);

		
			pool.submit(server);
			
		
		pool.shutdown();
	}

}