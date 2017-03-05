package objects.tiles;

import graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;
import math.Vector2;
import objects.GameObject;

public abstract class Tile extends GameObject {

	private Sprite spriteToDraw;
	private Vector2 position;
	private double width, height;
	
	public Tile(Sprite spriteToDraw, Vector2 position, double width, double height) {
		this.spriteToDraw=spriteToDraw;
		this.position=position;
		this.width=width;
		this.height=height;
	}
	
	public void render(GraphicsContext gc) {
		spriteToDraw.draw(gc, (int)position.getX(), (int)position.getY(), (int)width, (int)height);
	}
}
