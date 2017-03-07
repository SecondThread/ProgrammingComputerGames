package scenes;

import java.util.ArrayList;
import java.util.List;

import graphics.Camera;
import graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;
import math.Vector2;
import objects.BadGuy;
import objects.BadGuySpawner;
import objects.GameObject;
import objects.Player;
import objects.tiles.DirtTile;
import objects.tiles.GrassTile;

public class MainScene extends Scene {
	
	private static List<GameObject> gameObjects;
	private static Player player;
	private static Sprite sky;
	
	public MainScene() {
		gameObjects=new ArrayList<>();
		gameObjects.add(new GrassTile(new Vector2(100, 350), 400, 50));
		gameObjects.add(new GrassTile(new Vector2(0, 300), 100, 100));
		gameObjects.add(new GrassTile(new Vector2(500, 250), 200, 200));
		gameObjects.add(new GrassTile(new Vector2(200, 140), 200, 60));
		gameObjects.add(new GrassTile(new Vector2(700, 300), 200, 100));
		gameObjects.add(new DirtTile(new Vector2(-500, 400), 1900, 60));
		
		player=new Player(new Vector2(400, 100));
		gameObjects.add(new BadGuySpawner(player));
		gameObjects.add(player);
		sky=Sprite.getSprite("sky.png");
	}
	
	public Scene update() {
		Camera.update(player.getPosition());
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
