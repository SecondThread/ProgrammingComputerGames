package scenes;

import graphics.Camera;
import graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;
import main.Main;
import math.Vector2;
import objects.ui.Button;

public class YouLoseScene extends Scene {

	private Sprite youLoseScreenSprite;
	private Button playButton;
	
	public YouLoseScene() {
		Camera.resetCamera();
		youLoseScreenSprite=Sprite.getSprite("youDied.png");
		playButton=new Button(new Rectangle(Main.WIDTH/2-Main.WIDTH/6, Main.HEIGHT-Main.WIDTH/10, Main.WIDTH/3, Main.WIDTH/15), "PLAY AGAIN", Main.WIDTH/20);
		
	}
	
	public void render(GraphicsContext gc) {
		youLoseScreenSprite.draw(gc, Vector2.ZERO, Main.WIDTH, Main.HEIGHT);
		playButton.render(gc);
	}
	
	public Scene update() {
		playButton.update();
		if (playButton.getClickedOn()) {
			return new MainScene();
		}
		return this;
	}
}
