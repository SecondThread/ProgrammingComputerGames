package objects.tiles;

import graphics.Sprite;
import javafx.geometry.BoundingBox;
import javafx.scene.canvas.GraphicsContext;
import math.Vector2;
import objects.Collidable;
import objects.GameObject;

public abstract class Tile extends GameObject implements Collidable{

	private Sprite spriteToDraw;
	private Vector2 position;
	private double width, height;
	private boolean collidable;
	
	public Tile(Sprite spriteToDraw, Vector2 position, double width, double height, boolean collidable) {
		this.spriteToDraw=spriteToDraw;
		this.position=position;
		this.width=width;
		this.height=height;
		this.collidable=collidable;
	}
	
	public void render(GraphicsContext gc) {
		spriteToDraw.draw(gc, position, width, height);
	}
	
	public boolean touching(Collidable other) {
		return collidable;
		
	}
	
	public BoundingBox getBoundingBox() {
		return new BoundingBox(position.getX(), position.getY(), width, height);
	}
}
