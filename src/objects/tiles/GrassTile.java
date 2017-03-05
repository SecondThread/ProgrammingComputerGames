package objects.tiles;

import graphics.Sprite;
import math.Vector2;

public class GrassTile extends Tile {

	public GrassTile(Vector2 position, double width, double height) {
		super(Sprite.getSprite("grass.png"), position, width, height);
	}

}
