package objects.ui;

import input.Mouse;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import objects.GameObject;

public class Button extends GameObject {
	
	private Color startColor, pressedColor, textColor;
	private Rectangle boundingBox;
	private boolean pressed=false, clickedOn=false;
	private String text;
	
	public Button(Rectangle boundingbox, String text) {
		startColor=Color.AQUA;
		pressedColor=Color.AQUAMARINE;
		textColor=Color.BLACK;
		this.boundingBox=boundingbox;
		this.text=text;
	}
	
	public void update() {
		clickedOn=pressed&&Mouse.getMouseReleased();
		System.out.println(Mouse.getMouseDown());
		pressed=boundingBox.contains(new Point2D(Mouse.getMouseX(), Mouse.getMouseY()))&&Mouse.getMouseDown();
	}
	
	public void render(GraphicsContext gc) {
		if (pressed) {
			gc.setFill(startColor);
		}
		else {
			gc.setFill(pressedColor);
		}
		gc.fillRect(boundingBox.getX(), boundingBox.getY(), boundingBox.getWidth(), boundingBox.getHeight());
		
		gc.setFill(textColor);
		gc.setFont(new Font("Arial", 48));
		gc.setTextAlign(TextAlignment.CENTER);
		gc.fillText(text, boundingBox.getX()+boundingBox.getWidth()/2, boundingBox.getY()+0.8*boundingBox.getHeight());
	}
	
	public boolean getClickedOn() {
		return clickedOn;
	}
}
