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
		playButton=new Button(new Rectangle(Main.WIDTH/2-150, Main.HEIGHT-170, 300, 70), "PLAY");
		
	}
	
	public void render(GraphicsContext gc) {
		titleScreenSprite.draw(gc, 0, 0);
		playButton.render(gc);
	}
	
	public Scene update() {
		playButton.update();
		return this;
	}
	
}
