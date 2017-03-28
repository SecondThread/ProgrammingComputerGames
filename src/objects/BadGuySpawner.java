package objects;

import java.util.List;

import math.Vector2;
import scenes.MainScene;

public class BadGuySpawner extends GameObject {
	private int spawnCounter=100, maxSpawnCounter=180;
	private Player player;
	private int enemiesSpawned=0;
	
	public BadGuySpawner(Player player) {
		this.player=player;
	}
	
	public void update() {
		if (--spawnCounter>0) {
			return;
		}
		spawnCounter=maxSpawnCounter;
		int numberOfEnemies=0;
		for (GameObject o:MainScene.getGameObjects()) {
			if (o instanceof BadGuy) {
				numberOfEnemies++;
			}
		}
		
		if (numberOfEnemies<3) {
			List<GameObject> objects=MainScene.getGameObjects();
			int health=(int) Math.sqrt(enemiesSpawned);
			health=Math.max(health, 1);
			objects.add(new BadGuy(new Vector2(Math.random()*1000, 0), player, health));
			enemiesSpawned++;
			numberOfEnemies++;
		}
	}
	
}
