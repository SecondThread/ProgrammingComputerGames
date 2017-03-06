package objects;

import javafx.geometry.BoundingBox;

public interface Collidable {
	boolean touching(Collidable other);
	BoundingBox getBoundingBox();
}
