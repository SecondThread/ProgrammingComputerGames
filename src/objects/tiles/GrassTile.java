package objects.tiles;

import graphics.Sprite;
import math.Vector2;
import objects.Collidable;

public class GrassTile extends Tile implements Collidable {

	public GrassTile(Vector2 position, double width, double height) {
		super(Sprite.getSprite("grass.png"), position, width, height, true);
	}


}
