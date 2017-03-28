package scenes;

import java.util.ArrayList;
import java.util.List;

import graphics.Camera;
import graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import math.Vector2;
import objects.BadGuySpawner;
import objects.GameObject;
import objects.Player;
import objects.Rocket;
import objects.tiles.DirtTile;
import objects.tiles.GrassTile;

public class MainScene extends Scene {
	
	private static MainScene mainScene;
	private static List<GameObject> gameObjects;
	private static Player player;
	private static Sprite sky;
	private int money=0;
	
	
	public MainScene() {
		mainScene=this;
		gameObjects=new ArrayList<>();
		gameObjects.add(new GrassTile(new Vector2(100, 350), 400, 50));
		gameObjects.add(new GrassTile(new Vector2(0, 300), 100, 100));
		gameObjects.add(new GrassTile(new Vector2(500, 250), 200, 200));
		gameObjects.add(new GrassTile(new Vector2(200, 140), 200, 60));
		gameObjects.add(new GrassTile(new Vector2(700, 300), 200, 100));
		gameObjects.add(new DirtTile(new Vector2(-500, 400), 1900, 60));
		gameObjects.add(new Rocket(new Vector2(100, 200)));
		
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
		if (player.isDead()) {
			return new YouLoseScene();
		}
		if (player.hasWon()) {
			return new YouWinScene();
		}
		return this;
	}

	public void render(GraphicsContext gc) {
		sky.draw(gc, Camera.cameraPosition);
		for (int i=0; i<gameObjects.size(); i++) {
			gameObjects.get(i).render(gc);;
		}
		player.postRender(gc);
		gc.save();
		gc.setFill(Color.YELLOW);
		gc.fillText("$"+money, 60, 60);
		gc.restore();
	}
	
	public static List<GameObject> getGameObjects() {
		return gameObjects;
	}
	
	public static MainScene getScene() {
		return mainScene;
	}
	
	public void addMoney(int amount) {
		money+=amount;
	}
	
	public int getMoney() {
		return money;
	}
}
