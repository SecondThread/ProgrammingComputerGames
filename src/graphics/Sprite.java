package graphics;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Sprite {
	
	private Image image;
	
	public Sprite(String spriteName) {
		image=new Image(spriteName);
	}
	
	public void draw(GraphicsContext gc, int x, int y) {
		gc.drawImage(image, x, y);
	}
}
