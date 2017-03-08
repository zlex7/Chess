import java.util.Scanner;


public class TextChessRunner {

	public static void main(String[] args) {
		TextChess c = new TextChess();
		c.printboard();
		int i=0;
		Scanner scan = new Scanner(System.in);
		while(!c.checkmate){
			System.out.println("Please enter a move: ");
			String m = scan.next();
			c.move(m);
			i++;
		}
		String whowon="";
		if(c.color){
			whowon="white";
		}
		else{
			whowon="black";
		}
		System.out.println(i + " moves were played, " + i/2 + " for each side, " + "resulting in a win for " + whowon);
		scan.close();

	}

}
