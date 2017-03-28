package scenes;

import graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;
import main.Main;
import math.Vector2;
import objects.ui.Button;

public class TitleScene extends Scene {
	
	private Sprite titleScreenSprite;
	private Button playButton;
	
	public TitleScene() {
		titleScreenSprite=Sprite.getSprite("TitleScreen.png");
		playButton=new Button(new Rectangle(Main.WIDTH/2-Main.WIDTH/10, Main.HEIGHT-Main.WIDTH/10, Main.WIDTH/5, Main.WIDTH/15), "NEXT", Main.WIDTH/20);
		
	}
	
	public void render(GraphicsContext gc) {
		titleScreenSprite.draw(gc, Vector2.ZERO, Main.WIDTH, Main.HEIGHT);
		playButton.render(gc);
	}
	
	public Scene update() {
		playButton.update();
		if (playButton.getClickedOn()) {
			return new InstructionsScene();
		}
		return this;
	}
	
}
