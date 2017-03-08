import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public class ChessBoardGUI {
	
	public static Chess chessBoard = new Chess();
	
	public static void createChessBoard() throws InterruptedException{
		JFrame board = new JFrame("Chess Board");
		board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel contentPane = new JPanel(new GridLayout(8,8));
		
		BufferedImage chessPieces = getSpriteSheet("images/ChessPieces2.png");
		int imageHeight = chessPieces.getHeight()/2;
		int imageWidth = chessPieces.getWidth()/6;
		BufferedImage BlackKing = chessPieces.getSubimage(0,0,imageWidth,imageHeight);
		BufferedImage BlackQueen = chessPieces.getSubimage(imageWidth*1,0,imageWidth,imageHeight);
		BufferedImage BlackRook = chessPieces.getSubimage(imageWidth*4,0,imageWidth,imageHeight);
		BufferedImage BlackBishop = chessPieces.getSubimage(imageWidth*2,0,imageWidth,imageHeight);
		BufferedImage BlackKnight = chessPieces.getSubimage(imageWidth*3,0,imageWidth,imageHeight);
		BufferedImage BlackPawn = chessPieces.getSubimage(imageWidth*5,0,imageWidth,imageHeight);
		
		SquarePanelListener listener = new SquarePanelListener(chessBoard);
		SquarePanel[][] squares = new SquarePanel[8][8];
		
		squares[0][0] = new SquarePanel('b',0,0,BlackRook,'R');
		squares[0][1] = new SquarePanel('b',0,1,BlackKnight,'N');
		squares[0][2] = new SquarePanel('b',0,2,BlackBishop,'B');
		squares[0][3] = new SquarePanel('b',0,3,BlackQueen,'Q');
		squares[0][4] = new SquarePanel('b',0,4,BlackKing,'K');
		squares[0][5] = new SquarePanel('b',0,5,BlackBishop,'B');
		squares[0][6] = new SquarePanel('b',0,6,BlackKnight,'N');
		squares[0][7] = new SquarePanel('b',0,7,BlackRook,'N');
		
		for(int col=0;col<8;col++){
			squares[1][col] = new SquarePanel('b',1,col,BlackPawn,'P');
		}
		for(int i=2;i<6;i++){
			for(int j=0;j<8;j++){
			if((i+j)%2==0){
			squares[i][j] = new SquarePanel(i,j);
			System.out.println(i);
			}
			else{
				squares[i][j] = new SquarePanel(i,j);
			}
			}
		}
		
		BufferedImage WhiteKing = chessPieces.getSubimage(0,imageHeight,imageWidth,imageHeight);
		BufferedImage WhiteQueen = chessPieces.getSubimage(imageWidth*1,imageHeight,imageWidth,imageHeight);
		BufferedImage WhiteRook = chessPieces.getSubimage(imageWidth*4,imageHeight,imageWidth,imageHeight);
		BufferedImage WhiteBishop = chessPieces.getSubimage(imageWidth*2,imageHeight,imageWidth,imageHeight);
		BufferedImage WhiteKnight = chessPieces.getSubimage(imageWidth*3,imageHeight,imageWidth,imageHeight);
		BufferedImage WhitePawn = chessPieces.getSubimage(imageWidth*5,imageHeight,imageWidth,imageHeight);
		
		for(int col=0;col<8;col++){
			squares[6][col] = new SquarePanel('w',6,col,WhitePawn,'P');
		}
		
		squares[7][0] = new SquarePanel('w',7,0,WhiteRook,'R');
		squares[7][1] = new SquarePanel('w',7,1,WhiteKnight,'N');
		squares[7][2] = new SquarePanel('w',7,2,WhiteBishop,'B');
		squares[7][3] = new SquarePanel('w',7,3,WhiteQueen,'Q');
		squares[7][4] = new SquarePanel('w',7,4,WhiteKing,'K');
		squares[7][5] = new SquarePanel('w',7,5,WhiteBishop,'B');
		squares[7][6] = new SquarePanel('w',7,6,WhiteKnight,'N');
		squares[7][7] = new SquarePanel('w',7,7,WhiteRook,'R');
		
		
		for(int i=0;i<squares.length;i++){
			for(int j=0;j<squares.length;j++){
				squares[i][j].square.addActionListener(listener);
			}
		}
		for(int i=0;i<squares.length;i++){
			for(int j=0;j<squares.length;j++){
				contentPane.add(squares[i][j]);
			}
		}
		
		board.setContentPane(contentPane);
		board.setSize(800,800);
		board.setMinimumSize(new Dimension(500,500));
		board.setResizable(true);
		board.setVisible(true);
		
		//if(chessBoard.move(row,col,row2,col2,piece)){
			//listener.modifyAndRepaint();
		//}
		//}
		/*chessBoard.printboard();
		int i=0;
		while(!chessBoard.checkmate){
			chessBoard.move(m);
			i++;
		}
		String whowon="";
		if(chessBoard.color){
			whowon="white";
		}
		else{
			whowon="black";
		}
		System.out.println(i + " moves were played, " + i/2 + " for each side, " + "resulting in a win for " + whowon);
		*/
		
	}

	public static void main(String[] args) throws InterruptedException{
		SwingUtilities.invokeLater( new Runnable(){
			public void run() {
				try{
				createChessBoard();
				}
				catch(InterruptedException e){
					e.printStackTrace();
				}
			}
		});
		System.out.println("hello");

	}
	
	public static void restartGame() throws InterruptedException{
		SwingUtilities.invokeLater( new Runnable(){
			public void run() {
				try{
				createChessBoard();
				}
				catch(InterruptedException e){
					e.printStackTrace();
				}
			}
		});
	}
	public static BufferedImage getSpriteSheet(String path){
		try{
			return ImageIO.read(new File(path));
		}
		catch(IOException e){
			System.out.println("Failed to find file at: " + path);
		}
		return null;
	}

}
