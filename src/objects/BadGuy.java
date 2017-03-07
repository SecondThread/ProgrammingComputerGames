package objects;

import java.util.List;
import java.util.Random;

import graphics.Sprite;
import javafx.geometry.BoundingBox;
import javafx.scene.canvas.GraphicsContext;
import math.Vector2;
import scenes.MainScene;

public class BadGuy extends GameObject implements HittableWithBullet, Collidable {

	private Vector2 position, velocity=Vector2.ZERO;
	private Sprite sprite;
	private double width=100, height=100, angle=0;
	private Vector2 centerOfRotation=new Vector2(width/2, height/2);
	private boolean grounded=false;
	private static final Vector2 gravity=new Vector2(0, 0.4);
	private static final double friction=0.95, speed=.2, jumpPower=10;
	private Random random;
	
	private Player player;
	
	public BadGuy(Vector2 position, Player player) {
		this.position=position;
		this.player=player;
		sprite=Sprite.getSprite("tempEnemy.png");
		random=new Random();
	}
	
	public void update() {
		if (player.getPosition().getX()<position.getX()) {			
			velocity=new Vector2(velocity.getX()-speed, velocity.getY());
		}
		if (player.getPosition().getX()>position.getX()) {
			velocity=new Vector2(velocity.getX()+speed, velocity.getY());
		}
		if (random.nextInt(120)==0&&grounded) {
			velocity=new Vector2(velocity.getX(), -jumpPower);
		}
		
		velocity=velocity.add(gravity);
		angle+=velocity.getX()/(30*Math.PI);
		velocity=new Vector2(velocity.getX()*friction, velocity.getY());
		move();
	}
	
	public void render(GraphicsContext gc) {
		sprite.draw(gc, position, width, height, angle, centerOfRotation, false);
	}
	
	public boolean touching(Collidable other) {
		return other.getBoundingBox().intersects(getBoundingBox());
	}

	public BoundingBox getBoundingBox() {
		final double boundingBoxScalar=0.6;
		BoundingBox toReturn=new BoundingBox(position.getX()-width/2*(boundingBoxScalar), position.getY()-height/2*boundingBoxScalar, width*boundingBoxScalar, height*boundingBoxScalar);
		return toReturn;
	}

	public void onHit(Bullet hitWith) {
		//kill myself
		MainScene.getGameObjects().remove(this);
	}

	
	private void move() {
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
			velocity=new Vector2(velocity.getX(), 0);
			grounded=true;
		}
		else {
			grounded=false;
		}
	}
	
	/**
	 * Returns whether or not there was a collision
	 * @param objects a list of objects that it is possible to collide with
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
	
	/**
	 * Returns whether or not there was a collision
	 * @param objects a list of objects that it is possible to collide with
	 * @return true if and only if there was a collision
	 */
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