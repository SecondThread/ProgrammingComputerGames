package objects;

import java.util.List;

import graphics.Sprite;
import javafx.geometry.BoundingBox;
import javafx.scene.canvas.GraphicsContext;
import math.Vector2;
import scenes.MainScene;

public class Bullet extends GameObject implements Collidable {

	private Vector2 position;
	private double direction;
	private final static double speed=40;
	private final static double width=64, height=64;
	private final static int numberOfHitscans=20;
	private int lifeCounter=400;
	private Sprite bulletSprite;
	private Vector2 centerOfSprite=new Vector2(32, 32);
	
	public Bullet(Vector2 position, double direction) {
		this.position=position;
		this.direction=direction;
		bulletSprite=Sprite.getSprite("movingBullet.png");
	}
	
	public void update() {
		List<GameObject> gameObjects=MainScene.getGameObjects();
		Vector2 velocity=new Vector2(Math.cos(direction), Math.sin(direction)).scaleBy(speed);
		for (int hitScanNumber=0; hitScanNumber<numberOfHitscans; hitScanNumber++) {
			position=position.add(velocity.scaleBy(1.0/numberOfHitscans));
			for (int i=0; i<gameObjects.size(); i++) {
				GameObject o=gameObjects.get(i);
				
				//If I hit the other thing, tell it, and then destroy myself
				if (o instanceof Collidable&&touching((Collidable)o)&&o instanceof HittableWithBullet&&o!=this) {
					((HittableWithBullet)o).onHit(this);
					gameObjects.remove(this);
				}
			}
		}
		if (--lifeCounter<0) {
			gameObjects.remove(this);
		}
	}
	
	public void render(GraphicsContext gc) {
		if (bulletSprite!=null) {			
			bulletSprite.draw(gc, position, width, height, direction, centerOfSprite, false);
		}
	}

	public boolean touching(Collidable other) {
		return other.getBoundingBox().intersects(getBoundingBox());
	}

	public BoundingBox getBoundingBox() {
		final double boundingBoxScalar=0.1; 
		BoundingBox toReturn=new BoundingBox(position.getX()-width/2*boundingBoxScalar, position.getY()-height/2*boundingBoxScalar, width*boundingBoxScalar, height*boundingBoxScalar);
		return toReturn;
	}
	
}
