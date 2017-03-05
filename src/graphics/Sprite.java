package graphics;

import java.util.HashMap;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Sprite {
	
	private static HashMap<String, Sprite> sprites=new HashMap<>();
	
	private Image image;
	
	public static Sprite getSprite(String spriteName) {
		if (sprites.containsKey(spriteName)) {
			return sprites.get(spriteName);
		}
		Sprite toAdd=new Sprite(spriteName);
		sprites.put(spriteName, toAdd);
		return toAdd;
	}
	
	private Sprite(String spriteName) {
		image=new Image(spriteName);
	}
	
	public void draw(GraphicsContext gc, int x, int y) {
		gc.drawImage(image, x, y);
	}
	
	public void draw(GraphicsContext gc, int x, int y, int width, int height) {
		gc.drawImage(image, x, y, width, height);
	}
}
