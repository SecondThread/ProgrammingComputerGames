package objects.tiles;

import graphics.Sprite;
import math.Vector2;
import objects.Collidable;

public class DirtTile extends Tile implements Collidable {

	public DirtTile(Vector2 position, double width, double height) {
		super(Sprite.getSprite("dirt.png"), position, width, height, true);
	}

}
