package objects;

import graphics.Sprite;
import input.Keyboard;
import javafx.geometry.BoundingBox;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import math.Vector2;
import scenes.MainScene;

public class Rocket extends GameObject implements Collidable, HittableWithBullet {
	
	private Vector2 position;
	private Sprite rocketSprite;
	private int width=300, height=300;
	private boolean showText=false;
	
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
		double distanceFromPlayer=position.distance(MainScene.getPlayer().getPosition());
		if (distanceFromPlayer<200) {
			showText=true;
		}
		else {
			showText=false;
		}
	}
	
	public void render(GraphicsContext gc) {
		rocketSprite.draw(gc, position.subtract(new Vector2(width/2, height/2)), width, height);
		if (showText) {
			gc.save();
			gc.setFont(new Font("Arial", 24));
			gc.setFill(Color.YELLOW);
			double x=Sprite.convertToScreenPosition(position).getX();
			double y=Sprite.convertToScreenPosition(position).getY();
			gc.fillText("Hold 'E' to Escape", x, y);
			gc.restore();
		}
	}

	public void onHit(Bullet hitWith) {
		
	}
	
	public Vector2 getPosition() {
		return position;
	}
}
