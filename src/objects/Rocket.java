package objects;

import graphics.Sprite;
import javafx.geometry.BoundingBox;
import javafx.scene.canvas.GraphicsContext;
import math.Vector2;

public class Rocket extends GameObject implements Collidable, HittableWithBullet {
	
	private Vector2 position;
	private Sprite rocketSprite;
	private int width=300, height=300;
	
	public Rocket(Vector2 position) {
		this.position=position;
		rocketSprite=Sprite.getSprite("rocket.png");
	}

	public boolean touching(Collidable other) {
		return getBoundingBox().intersects(other.getBoundingBox());
	}

	public BoundingBox getBoundingBox() {
		final double boundingBoxScalar=0.5;
		BoundingBox toReturn=new BoundingBox(position.getX()-width/2*boundingBoxScalar, position.getY()-height/2*boundingBoxScalar, width*boundingBoxScalar, height*boundingBoxScalar);
		return toReturn;
	}
	
	public void update() {
		
	}
	
	public void render(GraphicsContext gc) {
		rocketSprite.draw(gc, position.subtract(new Vector2(width/2, height/2)), width, height);
	}

	public void onHit(Bullet hitWith) {
		
	}
	
	public Vector2 getPosition() {
		return position;
	}
}
