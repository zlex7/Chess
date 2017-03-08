import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TextChessGUI {
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

	public static void createGUI() {
		JFrame frame = new JFrame("Chess");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel contentPane = new JPanel(new BorderLayout());
		ImageIcon labelicon = createImageIcon("images/chesspiece.jpeg", "a chess piece");
		JLabel title = new JLabel("Chess Game", labelicon, JLabel.CENTER);
		JLabel icononly = new JLabel(labelicon);
		JButton startgamebutton = new JButton("New Game");
		contentPane.add(title, BorderLayout.NORTH);
		contentPane.add(startgamebutton, BorderLayout.CENTER);
		contentPane.add(icononly);
		frame.setContentPane(contentPane);
		frame.pack();
		frame.setVisible(true);
	}
	public static void createChessBoard(){
		JFrame frame = new JFrame("Chess Board");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel contentPane = new JPanel(new BorderLayout());
		
	}

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createGUI();
			}
		});

	}

}