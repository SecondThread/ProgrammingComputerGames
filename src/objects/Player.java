package objects;

import java.util.List;

import graphics.Sprite;
import javafx.geometry.BoundingBox;
import javafx.scene.canvas.GraphicsContext;
import math.Vector2;
import scenes.MainScene;

public class Player extends GameObject implements Collidable {
	
	private Vector2 position, velocity;
	private double width, height;
	private Sprite playerSprite;
	private static final Vector2 gravity=new Vector2(0, 0.2);
	private static final double friction=0.98;
	
	public Player(Vector2 position) {
		this.position=position;
		velocity=Vector2.ZERO;
		playerSprite=Sprite.getSprite("tomatoCharacter.png");
		width=100;
		height=100;
	}
	
	public void update() {
		velocity=velocity.add(gravity);
		velocity=velocity.scaleBy(friction);
		if (moveX(MainScene.getGameObjects())) {
			//move to touch
			velocity=new Vector2(0.01*Math.signum(velocity.getX()), velocity.getY());
			while (!moveX(MainScene.getGameObjects())&&velocity.getX()!=0) {
			}
			velocity=new Vector2(0, velocity.getY());
		}
		if (moveY(MainScene.getGameObjects())) {
			//move to touch
			velocity=new Vector2(velocity.getX(), 0.01*Math.signum(velocity.getY()));
			while (!moveY(MainScene.getGameObjects())&&velocity.getY()!=0) {
			}
			velocity=new Vector2(Math.signum(velocity.getX()), 0);
		}
	}
	
	public void render(GraphicsContext gc) {
		playerSprite.draw(gc, position.subtract(new Vector2(width/2, height/2)), width, height);
	}

	public boolean touching(Collidable other) {
		return other.getBoundingBox().intersects(getBoundingBox());
	}

	public BoundingBox getBoundingBox() {
		BoundingBox toReturn=new BoundingBox(position.getX()-width/2, position.getY()-height/2, width, height);
		return toReturn;
	}
	
	/**
	 * Returns whether or not there was a collision
	 * @param objects
	 * @return true if and only if there was a collision
	 */
	private boolean moveX(List<GameObject> objects) {
		double newXPosition=position.getX()+velocity.getX();
		Vector2 oldPosition=position;
		position=new Vector2(newXPosition, position.getY());
		for (GameObject o:objects) {
			if (o instanceof Collidable&&o!=this) {
				if (touching((Collidable)o)) {
					position=oldPosition;
					return true;
				}
			}
		}
		
		return false;
	}
	
	private boolean moveY(List<GameObject> objects) {
		Vector2 oldPosition=position;
		double newYPosition=position.getY()+velocity.getY();
		position=new Vector2(position.getX(), newYPosition);
		for (GameObject o:objects) {
			if (o instanceof Collidable&&o!=this) {
				if (touching((Collidable)o)) {
					position=oldPosition;
					return true;
				}
			}
		}
		return false;
	}
}
