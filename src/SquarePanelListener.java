import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;


public class SquarePanelListener implements ActionListener{
	public SquarePanel firstSquare;
	public SquarePanel secondSquare;
	public Chess chessBoard;
	private int numClicks=0;
	public int row;
	public int col;
	public int row2;
	public int col2;
	public char pieceName;
	public Object[] options = {"Restart", "Main Menu"};
	
	public SquarePanelListener(){
	}
	
	public SquarePanelListener(Chess chessBoard){
		this.chessBoard = chessBoard;
	}
	
	public void actionPerformed(ActionEvent e){
		JButton clickedButton = (JButton)e.getSource();
		SquarePanel clickedSquare = (SquarePanel)clickedButton.getParent();
		System.out.println(clickedSquare.row);
		if(numClicks==0){
			firstSquare=clickedSquare;
			numClicks++;
			row = clickedSquare.row;
			col = clickedSquare.col;
			pieceName = clickedSquare.pieceNameChar;
			clickedSquare.setBackground(Color.GREEN);
			clickedSquare.validate();
			clickedSquare.repaint();
		}
		else{
			secondSquare=clickedSquare;
			numClicks=0;
			firstSquare.resetBackground();
			row2 = clickedSquare.row;
			col2 = clickedSquare.col;
			System.out.println("row: " + row);
			System.out.println(col);
			System.out.println(row2);
			System.out.println(col2);
			System.out.println(firstSquare.pieceNameChar);
			if(chessBoard.move(7-row, col, 7-row2, col2, firstSquare.pieceNameChar)){
			
			secondSquare.piece = firstSquare.piece;
			firstSquare.piece=null;
			
			char temp = firstSquare.pieceNameChar;
			firstSquare.pieceNameChar = secondSquare.pieceNameChar;
			secondSquare.pieceNameChar = temp;
			}
			else{
				System.out.println("invalid move");
			}
			firstSquare.validate();
			firstSquare.repaint();
			clickedSquare.validate();
			clickedSquare.repaint();
			if(chessBoard.checkmate){
				int endGame = JOptionPane.showOptionDialog(clickedSquare.getParent(), "What would you like to do?", "Game Over", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, null);
				if(options[endGame].equals("Restart")){
					chessBoard.restart();
					System.out.println("hefelfhelflef");
					System.out.println(clickedSquare.getParent());
					try{ChessBoardGUI.restartGame();}
					catch(InterruptedException e2){
						e2.printStackTrace();
					}
				}
				else{
					
				}
			}
			//if(chessBoard.checkmate){
				//JLabel endGameLabel = new JLabel("Game Over");
				//JButton
			//}
		}
	}
	
	public void modifyAndRepaint(){
		System.out.println("completing modify and repaint");
		secondSquare.piece = firstSquare.piece;
		firstSquare.piece=null;
		firstSquare.validate();
		firstSquare.repaint();
		secondSquare.validate();
		secondSquare.repaint();
	}
	
	public int[] returnSquarePanelInfo(){
		int[] info = new int[4];
		info[0]=row;
		info[1]=col;
		info[2]=row2;
		info[3]=col2;
		return info;
	}
}

