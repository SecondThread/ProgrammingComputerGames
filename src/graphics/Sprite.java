package graphics;

import java.util.HashMap;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.transform.Affine;
import math.Vector2;

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

	public void draw(GraphicsContext gc, Vector2 worldPosition) {
		double x=worldPosition.getX();
		double y=worldPosition.getY();

		gc.drawImage(image, x, y);
	}

	public void draw(GraphicsContext gc, Vector2 worldPosition, double width, double height) {
		double x=worldPosition.getX();
		double y=worldPosition.getY();

		gc.drawImage(image, x, y, width, height);
	}

	// angle is in radians
	public void draw(GraphicsContext gc, Vector2 worldPosition, double width, double height, double angle, Vector2 centerOfRotation, boolean flipped) {
		gc.save();

		double x=worldPosition.getX();
		double y=worldPosition.getY();
		
		//<copied from spin demo. Original code was written by Dr. Slattery>
		Affine move=new Affine();
		// These next three lines modify the image
		// in reverse order (because of the way transforms
		// compose). So, we take our image and translate
		// it 20 units up and left so that the center of
		// the image is at (0,0). Then we rotate (so the
		// image rotates about its center).
		// Then we move the rotated image to where we really
		// want it.
		move.appendTranslation(x, y);
		move.appendRotation(Math.toDegrees(angle));
		if (flipped) {
			move.appendScale(-1, 1);
		}
		move.appendTranslation(-centerOfRotation.getX(), -centerOfRotation.getY());
		
		// draw the image using the specified transform
		gc.setTransform(move);
		//</copied part>
		
		gc.drawImage(image, 0, 0, width, height);
		gc.restore();
	}
}
