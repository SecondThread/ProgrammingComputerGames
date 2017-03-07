package scenes;

import java.util.ArrayList;
import java.util.List;

import graphics.Camera;
import graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;
import math.Vector2;
import objects.GameObject;
import objects.Player;
import objects.tiles.GrassTile;

public class MainScene extends Scene {
	
	private static List<GameObject> gameObjects;
	private static Sprite sky;
	
	public MainScene() {
		gameObjects=new ArrayList<>();
		gameObjects.add(new GrassTile(new Vector2(100, 350), 400, 50));
		gameObjects.add(new Player(new Vector2(400, 0)));
		sky=Sprite.getSprite("sky.png");
	}
	
	public Scene update() {
		for (int i=0; i<gameObjects.size(); i++) {
			gameObjects.get(i).update();
		}
		return this;
	}

	public void render(GraphicsContext gc) {
		sky.draw(gc, Camera.cameraPosition);
		for (int i=0; i<gameObjects.size(); i++) {
			gameObjects.get(i).render(gc);;
		}
	}
	
	public static List<GameObject> getGameObjects() {
		return gameObjects;
	}
	
}
