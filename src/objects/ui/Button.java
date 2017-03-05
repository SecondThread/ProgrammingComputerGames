package objects.ui;

import input.Mouse;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import math.GeneralMath;
import objects.GameObject;

public class Button extends GameObject {
	
	private Color startColor, pressedColor, textColor;
	private Rectangle boundingBox, expandedBox;
	private boolean pressed=false, clickedOn=false;
	private boolean mouseOver=false;
	private String text;
	private int fontSize;
	private double progress=0;
	
	public Button(Rectangle boundingbox, String text, int fontSize) {
		startColor=Color.AQUA;
		pressedColor=Color.AQUAMARINE;
		textColor=Color.BLACK;
		this.boundingBox=boundingbox;
		expandedBox=new Rectangle(boundingBox.getX()-boundingBox.getWidth()*0.2, boundingBox.getY()-boundingBox.getHeight()*0.2,
				boundingBox.getWidth()*1.4, boundingBox.getHeight()*1.4);
		this.text=text;
		this.fontSize=fontSize;
	}
	
	public void update() {
		//if I was pressed last frame, and this frame the mouse was released
		clickedOn=pressed&&Mouse.getMouseReleased();

		mouseOver=boundingBox.contains(new Point2D(Mouse.getMouseX(), Mouse.getMouseY()));
		if (mouseOver) {//if the mouse is over, fade toward expanded, otherwise fade toward not expanded.
			progress=GeneralMath.lerp(progress, 1, 0.3);
		}
		else {
			progress=GeneralMath.lerp(progress, 0, 0.3);
		}
		pressed=mouseOver&&Mouse.getMouseDown();
	}
	
	public void render(GraphicsContext gc) {
		if (pressed) {
			gc.setFill(startColor);
		}
		else {
			gc.setFill(pressedColor);
		}
		
		double x=GeneralMath.lerp(boundingBox.getX(), expandedBox.getX(), progress);
		double y=GeneralMath.lerp(boundingBox.getY(), expandedBox.getY(), progress);
		double width=GeneralMath.lerp(boundingBox.getWidth(), expandedBox.getWidth(), progress);;
		double height=GeneralMath.lerp(boundingBox.getHeight(), expandedBox.getHeight(), progress);;
		gc.fillRect(x, y, width, height);
		
		gc.setFill(textColor);
		gc.setFont(new Font("Arial", fontSize));
		gc.setTextAlign(TextAlignment.CENTER);
		gc.fillText(text, boundingBox.getX()+boundingBox.getWidth()/2, boundingBox.getY()+0.8*boundingBox.getHeight());
	}
	
	public boolean getClickedOn() {
		return clickedOn;
	}
}
