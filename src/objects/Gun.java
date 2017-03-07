package objects;

import graphics.Sprite;
import input.Mouse;
import javafx.scene.canvas.GraphicsContext;
import math.Vector2;
import scenes.MainScene;

public class Gun {
	
	private Vector2 position;
	private static final double width=100, height=100;
	private Sprite gunSprite;
	private static final Vector2 OFFSET=new Vector2(0, 10), centerOfSprite=new Vector2(30, 42); 
	private static double angle=0, angleToMouse;
	private static boolean flipped=false;
	private static int shootingCooldown=0, maxShootingCooldown=10;
	
	
	public Gun(Vector2 position) {
		this.position=position;
		gunSprite=Sprite.getSprite("assaultrifle.png");
	}
	
	public void update(Vector2 playerPosition) {
		position=OFFSET.add(playerPosition);
		Vector2 mousePosition=new Vector2(Mouse.getMouseX(), Mouse.getMouseY());
		angleToMouse=position.angleTo(mousePosition);
		
		//check if I need to flip the sprite if I am facing the other way so that the gun isn't upside-down
		if (Math.abs(angleToMouse)>Math.PI/2) {
			flipped=true;
			angle=angleToMouse-Math.PI;
		}
		else {
			flipped=false;
			angle=angleToMouse;
		}
		
		tryToFire();
	}
	
	public void render(GraphicsContext gc) {
		gunSprite.draw(gc, position, width, height, angle, centerOfSprite, flipped);
	}
	
	
	private void tryToFire() {
		if (shootingCooldown>0) {
			shootingCooldown--;
			return;
		}
		
		if (Mouse.getMouseDown()) {
			shootingCooldown=maxShootingCooldown;
			final double distanceFromGun=50;
			Vector2 positionToCreate=position.add(Vector2.unitVector(angleToMouse).scaleBy(distanceFromGun));
			Bullet toCreate=new Bullet(positionToCreate, angleToMouse);
			MainScene.getGameObjects().add(toCreate);
		}
	}
	
}
