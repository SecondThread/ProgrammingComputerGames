package objects;

import graphics.Sprite;
import input.Keyboard;
import javafx.geometry.BoundingBox;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import math.Vector2;
import scenes.MainScene;

public class Obstacle extends GameObject implements Collidable, HittableWithBullet {

	private Vector2 position;
	private int width, height, cost;
	private boolean showText=false;
	private Sprite obstacleSprite;
	
	public Obstacle(Vector2 position, int width, int height, int cost) {
		this.position=position;
		this.width=width;
		this.height=height;
		this.cost=cost;
		obstacleSprite=Sprite.getSprite("rockPile.png");
	}
	
	
	public void onHit(Bullet hitWith) {
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
		if (distanceFromPlayer<300) {
			showText=true;
			if (MainScene.getScene().getMoney()>=cost&&Keyboard.getKeyDown('E')) {
				MainScene.getScene().addMoney(-cost);
				MainScene.getGameObjects().remove(this);
			}
		}
		else {
			showText=false;
		}
	}
	
	public void render(GraphicsContext gc) {
		obstacleSprite.draw(gc, position.subtract(new Vector2(width/2, height/2)), width, height);
		
		if (showText) {
			gc.save();
			gc.setFont(new Font("Arial", 24));
			gc.setFill(Color.YELLOW);
			double x=Sprite.convertToScreenPosition(position).getX();
			double y=Sprite.convertToScreenPosition(position).getY();
			gc.fillText("Hold 'E' to remove for $"+cost, x, y);
			gc.restore();
		}
	}

}
