package input;

import graphics.Camera;

public class Mouse {

	private static int mouseX, mouseY;
	private static boolean mouseDownLastFrame;
	private static boolean mouseDownThisFrame;
	private static boolean mousePressed, mouseReleased;

	public static void init() {

	}

	// All these methods have to be synchronized in case the interrupt happens
	// to run exactly between update. This way we can be sure that we don't
	// register the mouse as being pressed half-way between when we check if it
	// was last frame and this frame
	public synchronized static void onMouseMove(int newX, int newY) {
		mouseX=newX;
		mouseY=newY;
	}

	public synchronized static void onMousePressed() {
		mouseDownThisFrame=true;
	}

	public synchronized static void onMouseReleased() {
		mouseDownThisFrame=false;
	}

	public synchronized static void update() {
		mousePressed=mouseDownThisFrame&&!mouseDownLastFrame;
		mouseReleased=!mouseDownThisFrame&&mouseDownLastFrame;
		mouseDownLastFrame=mouseDownThisFrame;
	}

	public static int getMouseX() {
		return (int) (mouseX+Camera.cameraPosition.getX());
	}

	public static int getMouseY() {
		return (int) (mouseY+Camera.cameraPosition.getY());
	}

	public static boolean getMousePressed() {
		return mousePressed;
	}

	public static boolean getMouseReleased() {
		return mouseReleased;
	}

	public static boolean getMouseDown() {
		return mouseDownThisFrame;
	}
}
