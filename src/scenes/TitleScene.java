package scenes;

import graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;
import main.Main;
import objects.ui.Button;

public class TitleScene extends Scene {
	
	private Sprite titleScreenSprite;
	private Button playButton;
	
	public TitleScene() {
		titleScreenSprite=new Sprite("TitleScreen.png");
		playButton=new Button(new Rectangle(Main.WIDTH/2-Main.WIDTH/10, Main.HEIGHT-Main.WIDTH/10, Main.WIDTH/5, Main.WIDTH/15), "PLAY", Main.WIDTH/20);
		
	}
	
	public void render(GraphicsContext gc) {
		titleScreenSprite.draw(gc, 0, 0, Main.WIDTH, Main.HEIGHT);
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
