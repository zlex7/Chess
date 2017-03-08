import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class SquarePanel extends JPanel{
	Color background;
	JButton square;
	BufferedImage piece;
	char pieceNameChar;
	char pieceColor;
	int row;
	int col;

	public SquarePanel(int r, int c){
		if((r+c)%2==0){
			background=new Color(245, 241, 222);
		}
		else{
			background=new Color(20, 16, 15);
		}
		row=r;
		col=c;
		pieceColor='X';
		pieceNameChar='X';
		square = new JButton();
		this.setBorder(BorderFactory.createLineBorder(Color.darkGray,1));
		this.setLayout(new GridBagLayout());
	}
	
	public SquarePanel(char color, int r, int c, BufferedImage newPiece, char pieceNameChar){
		if((r+c)%2==0){
			background=new Color(245, 241, 222);
		}
		else{background = new Color(20, 16, 15);
		}
		
		pieceColor = color;
		
		row=r;
		col=c;
		
		piece=newPiece;
		this.pieceNameChar = pieceNameChar;

		square = new JButton();
		this.setBorder(BorderFactory.createLineBorder(Color.darkGray,1));
		this.setLayout(new GridBagLayout());
		
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		int width = getWidth();
		int height = getHeight();
		g.setColor(background);
		//(x coordinate, y coordinate, width, height)
		square.setLocation(0,0);
		square.setSize(width,height);
		square.setOpaque(false);
		square.setContentAreaFilled(false);
		add(square);
		g.fillRect(0,0,width,height);
		//piece.setOpaque(true);
		//piece.setLocation(0,0);
		g.drawImage(piece,0,0,width,height,null);
		//pieceLabel.setLocation(0,0);
		//pieceLabel.setSize(width,height);
		//add(pieceLabel);
	}
	
	public BufferedImage getPiece(){
		return piece;
	}
	public char getPieceColor(){
		return pieceColor;
	}
	
	public void setPieceColor(char newPieceColor){
		pieceColor=newPieceColor;
	}
	
	public int getRow(){
		return row;	
	}
	
	public int getCol(){
		return col;
	}
	
	public void setBackground(Color color){
		background=color;
	}
	
	public Color getBackground(){
		return background;
	}
	
	public void resetBackground(){
		if((row+col)%2==0){
			background=new Color(245, 241, 222);
		}
		else{background = new Color(20, 16, 15);
		}
	}
	
	
	private static ImageIcon createImageIcon(String path, String description) {
		java.net.URL imgURL = TextChessGUI.class.getClassLoader().getResource(path);
		System.out.println(imgURL);
		if (imgURL != null) {
			return new ImageIcon(imgURL, description);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

}
