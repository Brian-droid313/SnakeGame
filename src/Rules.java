import java.util.Random;

public class Rules {
	int UNIT_SIZE;
	int GAME_UNITS;
	static int SCREEN_WIDTH;
	static int SCREEN_HEIGHT;
	private Apple apple;
	private Snake snake;
	private Random random;
	private boolean isGameRunning;
	
	Rules(int unit_size, int screen_width, int screen_height) {
		UNIT_SIZE = unit_size;
		SCREEN_WIDTH = screen_width;
		SCREEN_HEIGHT = screen_height;
		GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE;
		random = new Random();
		snake = new Snake(6, GAME_UNITS);
		apple = newApple();
		
	}
	
	public void setGameRunning(boolean value) {
		isGameRunning = value;
	}
	
	public boolean getGameRunning() {
		return isGameRunning;
	}
	
	public Apple getApple() {
		return apple;
	}
	
	public void setApple(Apple value) {
		apple = value;
	}
	
	public Apple newApple() {
		Apple apple = null;
		boolean acceptableApple = false;
		while(!acceptableApple) {
			acceptableApple = true;
			apple = new Apple(random.nextInt(SCREEN_WIDTH/UNIT_SIZE)*UNIT_SIZE, random.nextInt(SCREEN_HEIGHT/UNIT_SIZE)*UNIT_SIZE);
			for(int i = 0; i < snake.bodyLength; i++) {
				if(snake.coordinates[i].x == apple.coordinate.x && snake.coordinates[i].y == apple.coordinate.y) {
					acceptableApple = false;
				}
			}
		}
		return apple;
	}
	
	
	public Snake getSnake() {
		return snake;
	}
	
	public boolean wallCollison() {
		if(snake.coordinates[0].x < 0) {
			return true;
		}
		if(snake.coordinates[0].x > SCREEN_WIDTH - UNIT_SIZE) {
			return true;
		}
		if(snake.coordinates[0].y < 0) {
			return true;
		}
		if(snake.coordinates[0].y > SCREEN_HEIGHT - UNIT_SIZE) {
			return true;
		}
		return false;
	}
	
	
}
