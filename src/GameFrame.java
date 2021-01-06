import javax.swing.JFrame;

public class GameFrame extends JFrame {
	public GameFrame() {
		// TODO Auto-generated constructor stub
		this.add(new GamePanel());
		this.setTitle("Snake Game");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.pack();
		this.setVisible(true);
		
	}
}
