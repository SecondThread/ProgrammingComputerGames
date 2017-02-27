package main;

import java.awt.event.KeyEvent;

import input.Keyboard;
import input.Mouse;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
//import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import scenes.Scene;

/**
 * Simple mouse event demo (using lambdas)
 * 
 * @author mike slattery
 * 
 * Modified By:	David Harmeyer 
 *         		Rade Latinovich
 */
public class Main extends Application {
	
	private final String appName="Candy Defense";
	private final int WIDTH=600;
	private final int HEIGHT=500;
	private final int FPS=60;

	private GraphicsContext gc;
	private Scene currentScene;

	private void setHandlers(javafx.scene.Scene scene) {
		//set keyboard callbacks
		scene.setOnKeyPressed(e-> {
			Keyboard.onKeyPressed(""+e.getCode().getName());
		});
		scene.setOnKeyReleased(e-> {
			Keyboard.onKeyReleased(""+e.getCode().getName());
		});
		
		//set mouse callbacks
		scene.setOnMousePressed(e -> {
		});
		scene.setOnMouseReleased(e -> {
		});
	}

	private void render(GraphicsContext gc) {
		// Clear background
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, WIDTH, HEIGHT);
		if (currentScene!=null) {
			currentScene.render(gc);
		}
	}
	
	private void update() {
		Keyboard.update();
		if (currentScene!=null) {
			currentScene=currentScene.update();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage theStage) {
		Keyboard.init();
		Mouse.init();
		theStage.setTitle(appName);

		Group root=new Group();
		javafx.scene.Scene theScene=new javafx.scene.Scene(root);
		theStage.setScene(theScene);

		Canvas canvas=new Canvas(WIDTH, HEIGHT);
		root.getChildren().add(canvas);

		gc=canvas.getGraphicsContext2D();

		KeyFrame kf=new KeyFrame(Duration.millis(1000/FPS), e -> {
			// update position
			update();
			// draw frame
			render(gc);
		});
		Timeline mainLoop=new Timeline(kf);
		mainLoop.setCycleCount(Animation.INDEFINITE);
		mainLoop.play();
		
		setHandlers(theScene);
		theStage.show();
	}
}
