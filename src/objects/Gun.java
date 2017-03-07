package objects;

import graphics.Sprite;
import input.Mouse;
import javafx.scene.canvas.GraphicsContext;
import math.Vector2;

public class Gun {
	
	private Vector2 position;
	private static final double width=100, height=100;
	private Sprite gunSprite;
	private static final Vector2 OFFSET=new Vector2(0, 10), centerOfSprite=new Vector2(30, 50); 
	private static double angle=0;
	private static boolean flipped=false;
	
	public Gun(Vector2 position) {
		this.position=position;
		gunSprite=Sprite.getSprite("assaultrifle.png");
	}
	
	public void update(Vector2 playerPosition) {
		position=OFFSET.add(playerPosition);
		Vector2 mousePosition=new Vector2(Mouse.getMouseX(), Mouse.getMouseY());
		angle=position.angleTo(mousePosition);
		
		//check if I need to flip the sprite if I am facing the other way so that the gun isn't upsidedown
		if (Math.abs(angle)>Math.PI/2) {
			flipped=true;
			angle-=Math.PI;
		}
		else {
			flipped=false;
		}
	}
	
	public void render(GraphicsContext gc) {
		gunSprite.draw(gc, position, width, height, angle, centerOfSprite, flipped);
	}
	
	
}
