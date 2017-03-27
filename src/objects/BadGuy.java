package objects;

import java.util.List;
import java.util.Random;

import graphics.Sprite;
import javafx.geometry.BoundingBox;
import javafx.scene.canvas.GraphicsContext;
import math.Vector2;
import particles.Particle;
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
	private static final int particlesToCreateOnDeath=20;
	
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
		
		if (position.distance(player.getPosition())<110) {
			player.onHitWithEnemy();
			onDie();
			MainScene.getGameObjects().remove(this);
		}
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

	
	private void onDie() {
		List<GameObject> gameObjects=MainScene.getGameObjects();
		for (int i=0; i<particlesToCreateOnDeath; i++) {
			double angle=random.nextDouble()*360;
			double angularVelocity=random.nextDouble()*0.1;
			Vector2 offset=new Vector2(random.nextInt(70)-35, random.nextInt(70)-35);
			final double maxVelocity=25;
			Vector2 velocityOffset=new Vector2(random.nextDouble()*maxVelocity-maxVelocity/2, random.nextDouble()*maxVelocity-maxVelocity/2);
			Particle toAdd=new Particle(Sprite.getSprite("CookieSection.png"), position.add(offset), velocity.add(velocityOffset), 
					20, false, false, false, true, 40, 40, angle, angularVelocity);
			gameObjects.add(toAdd);
		}
	}

}
