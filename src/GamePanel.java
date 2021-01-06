import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener {
	static final int SCREEN_WIDTH = 600;
	static final int SCREEN_HEIGHT = 600;
	static final int UNIT_SIZE = 25;
	static final int DELAY = 75;
	Timer timer;
	Rules rules;
	Random random;

	public GamePanel() {
		// TODO Auto-generated constructor stub
		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.setBackground(Color.black);
		this.setFocusable(true);
		startGame();
	}
	public void startGame() {
		random = new Random();
		rules = new Rules(UNIT_SIZE, SCREEN_WIDTH, SCREEN_HEIGHT);
		this.addKeyListener(rules.getSnake().keyEvents);
		rules.setGameRunning(true);
		this.timer = new Timer(DELAY, this);
		timer.start();
	}
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		draw(g);
	}
	public void draw(Graphics g) {
		if(rules.getGameRunning()) {
			drawSnake(g);
			
			drawApple(g);
			drawScore(g);
		}
		
		else {
			timer.stop();
			drawScore(g);
			drawGameOverSign(g);	
		}
	}
	
	public void drawSnake(Graphics g) {
		for(int i = 0; i < rules.getSnake().bodyLength; i++) {
			if(i == 0) {
				g.setColor(Color.green);
			}
			else {
				g.setColor(new Color(random.nextInt(255),random.nextInt(255), random.nextInt(255)));
			}
			g.fillRect(rules.getSnake().coordinates[i].x, rules.getSnake().coordinates[i].y, UNIT_SIZE, UNIT_SIZE);
		}
	}
	
	public void drawApple(Graphics g) {
		g.setColor(Color.red);
		g.fillOval(rules.getApple().coordinate.x, rules.getApple().coordinate.y, UNIT_SIZE, UNIT_SIZE);
	}
	
	public void drawScore(Graphics g) {
		g.setColor(Color.red);
		g.setFont(new Font("Ink Free", Font.BOLD, 40));
		FontMetrics metrics = getFontMetrics(g.getFont());
		g.drawString("Score: " + rules.getSnake().applesEaten, (SCREEN_WIDTH - metrics.stringWidth("Score " + rules.getSnake().applesEaten))/2, g.getFont().getSize());
	}
	public void drawGameOverSign(Graphics g) {
		g.setColor(Color.red);
		g.setFont(new Font("Ink Free", Font.BOLD, 75));
		FontMetrics metrics = getFontMetrics(g.getFont());
		g.drawString("GAME OVER", (SCREEN_WIDTH - metrics.stringWidth("GAME OVER"))/2, SCREEN_HEIGHT/2);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(rules.getGameRunning()) {
			rules.getSnake().move();
			if(rules.wallCollison() || rules.getSnake().snakeCollison()) {
				rules.setGameRunning(false);
			}
			if(rules.getSnake().eatApple(rules.getApple())) {
//				rules.newApple();
				rules.setApple(rules.newApple());
			}
			repaint();
		}
		
	}

}
