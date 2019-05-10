import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToeClient extends JFrame{
	
	Scanner keyboard;
	BufferedReader in;
	PrintWriter out;
	Socket s;
	int portnum;
	String server = "localhost";
	
	public static Board board;
	public static JButton b0 ;
	public static JButton b1 ;
	public static JButton b2 ;
	public static JButton b3 ;
	public static JButton b4 ;
	public static JButton b5 ;
	public static JButton b6 ;
	public static JButton b7 ;
	public static JButton b8 ;
	

	public static JTextArea screen;
	TicTacToeClient(int number){
		portnum = number;
		JFrame frame;
		JPanel screenPanel;
		JPanel buttonPanel;
		JPanel inputPanel;
		
		Dimension dim;
		JButton ok = new JButton("OK");
		// frame stuff
		frame = new JFrame("TIC TAC TOE");
		dim = Toolkit.getDefaultToolkit().getScreenSize();
		// frame.setVisible(true);
		frame.setSize(600, 400);
		frame.setLocation((dim.width / 2 - frame.getWidth() / 2), (dim.height - frame.getHeight()) / 2);
		// frame.add(rp = new RenderPanel() );
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);

		Container pane = frame.getContentPane();
		b0 = new JButton("");
		b1 = new JButton("");
		b2 = new JButton("");
		b3 = new JButton("");
		b4 = new JButton("");
		b5 = new JButton("");
		b6 = new JButton("");
		b7 = new JButton("");
		b8 = new JButton("");
		
		b0.setFont(new Font("Arial", Font.PLAIN, 40));
		b1.setFont(new Font("Arial", Font.PLAIN, 40));
		b2.setFont(new Font("Arial", Font.PLAIN, 40));
		b3.setFont(new Font("Arial", Font.PLAIN, 40));
		b4.setFont(new Font("Arial", Font.PLAIN, 40));
		b5.setFont(new Font("Arial", Font.PLAIN, 40));
		b6.setFont(new Font("Arial", Font.PLAIN, 40));
		b7.setFont(new Font("Arial", Font.PLAIN, 40));
		b8.setFont(new Font("Arial", Font.PLAIN, 40));
		
		
		// button panel stuff
		buttonPanel = new JPanel(new GridLayout(3,3));
		buttonPanel.add(b0);
		buttonPanel.add(b1);
		buttonPanel.add(b2);
		buttonPanel.add(b3);
		buttonPanel.add(b4);
		buttonPanel.add(b5);
		buttonPanel.add(b6);
		buttonPanel.add(b7);
		buttonPanel.add(b8);
		
		screen = new JTextArea("");
		screen.setColumns(20);
		screen.setRows(3);
		screen.setFont(screen.getFont().deriveFont(16f));
		screen.setEditable(false);
		screenPanel = new JPanel();
		screenPanel.add(screen);
		
		inputPanel = new JPanel();
		JTextField inputField = new JTextField();
		inputField.setColumns(20);
		inputPanel.add(inputField);
		inputPanel.add(ok);
		
		

		
		try{
			keyboard = new Scanner(System.in);
			s = new Socket( server, portnum);
			out = new PrintWriter(s.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader( s.getInputStream()));
		} catch (IOException e){
			System.out.println("Error initializing client " + e.getMessage());
			System.exit(1);
		}
		
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
					out.println(inputField.getText().trim());
				
			}
		});
		
		b0.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				out.println("0\n0");
//				
			}
		});
		b1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
					out.println("0\n1");
//		
//				
			}
		});
		b2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				out.println("0\n2");
				
			}
		});
		b3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				out.println("1\n0");
				
			}
		});
		b4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				out.println("1\n1");
				
			}
		});
		b5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				out.println("1\n2");
				
			}
		});
		b6.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				out.println("2\n0");
				
				
			}
		});
		b7.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				out.println("2\n1");
				
			}
		});
		b8.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				out.println("2\n2");
				
			}
		});
		frame.setLayout(new BorderLayout(1, 3));

		pane.add(screenPanel, BorderLayout.PAGE_START);
		pane.add(buttonPanel, BorderLayout.CENTER);
		pane.add(inputPanel, BorderLayout.PAGE_END);

		frame.setVisible(true);


	}

	public void communicate() throws InterruptedException{
		String message = "";
		String response = "";

		try{
		while(true){

				message = in.readLine();
				System.out.println(message);
				if (message != null) {
					if(message.charAt( message.length() -1 ) == '*') {
						updateBoard(message);
					}else {
						screen.setText(message);
					}
				
	
					
					if(message.charAt( message.length() -1 ) == '!')
						break;
				
				}
				else {
					Thread.sleep(1000);
				}
			
				
		}
			
				in.close();
				out.close();
				keyboard.close();
		} catch ( IOException e ){
			System.out.println("ERROR during cleanup");
		}
	
	}
	public void updateBoard(String line ) {
		char[] arr = line.toCharArray();
		b0.setText(Character.toString(arr[0]));
		b1.setText(Character.toString(arr[1]));
		b2.setText(Character.toString(arr[2]));
		b2.setText(Character.toString(arr[3]));
		b2.setText(Character.toString(arr[4]));
		b2.setText(Character.toString(arr[5]));
		b2.setText(Character.toString(arr[6]));
		b2.setText(Character.toString(arr[7]));
		b2.setText(Character.toString(arr[8]));
	}
	public static void main(String[] args) {
		TicTacToeClient tttc = new TicTacToeClient(9090);
		try {
		tttc.communicate();
		}catch(InterruptedException exc) {
			exc.printStackTrace();
		}
		}


	}




