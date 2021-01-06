import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Snake {
	int bodyLength;
	Coordinates coordinates[];
	char direction = 'R';
	MyKeyAdapter keyEvents = new MyKeyAdapter();
	int applesEaten = 0;
	
	Snake(int bodyLength, int gameUnits) {
		this.bodyLength = bodyLength;
		coordinates = new Coordinates[gameUnits];
		for(int i = 0; i < gameUnits; i++) {
			coordinates[i] = new Coordinates(0, 0);
		}
	}
	
	public void move() {
		for(int i = bodyLength; i > 0; i--) {
			coordinates[i] = new Coordinates(coordinates[i - 1].x, coordinates[i - 1].y);
		}
		switch(direction) {
		case 'U':
			coordinates[0].y = coordinates[0].y - 25;
			break;
		case 'D':
			coordinates[0].y = coordinates[0].y + 25;
			break;
		case 'L':
			coordinates[0].x = coordinates[0].x - 25;
			break;
		case 'R':
			coordinates[0].x = coordinates[0].x + 25;
			break;
		}
	}
	
	public boolean snakeCollison() {
		for(int i = bodyLength; i > 0; i--) {
			if(coordinates[0].x == coordinates[i].x && coordinates[0].y == coordinates[i].y) {
				return true;
			}
		}
		
		return false;
	}
	
	public boolean eatApple(Apple apple) {
		if(this.coordinates[0].x == apple.coordinate.x && this.coordinates[0].y == apple.coordinate.y) {
			applesEaten++;
			bodyLength++;
			return true;
		}
		return false;
	}
	
	public class MyKeyAdapter extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()) {
			case KeyEvent.VK_UP:
				if(direction != 'D') {
					direction = 'U';
				}
				break;
			case KeyEvent.VK_DOWN:
				if(direction != 'U') {
					direction = 'D';
				}
				break;
			case KeyEvent.VK_LEFT:
				if(direction != 'R') {
					direction = 'L';
				}
				break;
			case KeyEvent.VK_RIGHT:
				if(direction != 'L') {
					direction = 'R';
				}
				break;
			}
		}
	}
}
