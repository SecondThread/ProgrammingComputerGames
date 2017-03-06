package scenes;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import math.Vector2;
import objects.GameObject;
import objects.tiles.GrassTile;

public class MainScene extends Scene {
	
	private List<GameObject> gameObjects=new ArrayList<>();
	
	public MainScene() {
		gameObjects.add(new GrassTile(new Vector2(100, 350), 400, 50));
	}
	
	public Scene update() {
		for (int i=0; i<gameObjects.size(); i++) {
			gameObjects.get(i).update();
		}
		return this;
	}

	public void render(GraphicsContext gc) {
		for (int i=0; i<gameObjects.size(); i++) {
			gameObjects.get(i).render(gc);;
		}
	}
}
