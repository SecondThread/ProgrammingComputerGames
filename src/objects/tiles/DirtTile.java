package objects.tiles;

import graphics.Sprite;
import math.Vector2;

public class DirtTile extends Tile {

	public DirtTile(Vector2 position, double width, double height) {
		super(Sprite.getSprite("dirt.png"), position, width, height);
	}

}
