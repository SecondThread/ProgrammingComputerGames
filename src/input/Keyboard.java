package input;

public class Keyboard {

	private static final int maxKeyCode=400;
	private static boolean[] keysDownThisFrame;
	private static boolean[] keysDownLastFrame;
	private static boolean[] pressed, released;

	public static void init() {
		keysDownThisFrame=new boolean[maxKeyCode];
		keysDownLastFrame=new boolean[maxKeyCode];
		pressed=new boolean[maxKeyCode];
		released=new boolean[maxKeyCode];
		for (int i=0; i<maxKeyCode; i++) {
			keysDownLastFrame[i]=keysDownThisFrame[i]=pressed[i]=released[i]=false;
		}
	}

	// These all have to be synchronized to avoid race cases. Because the key
	// interrupts are called in a seperate thread, we can't let onKeyPressed
	// happen as the same time update is being called. Making both methods
	// synchronized should protect against this
	public synchronized static void update() {
		for (int i=0; i<maxKeyCode; i++) {
			pressed[i]=!keysDownLastFrame[i]&&keysDownThisFrame[i];
			released[i]=!keysDownThisFrame[i]&&keysDownLastFrame[i];

			keysDownLastFrame[i]=keysDownThisFrame[i];
		}
	}

	public synchronized static void onKeyPressed(String characterPressed) {
		if (!keysDownThisFrame[characterPressed.charAt(0)]) {
			// System.out.println("Key Pressed: "+characterPressed);
			keysDownThisFrame[characterPressed.charAt(0)]=true;
		}
	}

	public synchronized static void onKeyReleased(String characterReleased) {
		keysDownThisFrame[characterReleased.charAt(0)]=false;
	}

	/**
	 * Checks if the key currently being held
	 * 
	 * @param key
	 *            A character representing the key to be checked. **MUST BE
	 *            UPERCASE**
	 * @return True if and only if the key is being held
	 */
	public static boolean getKeyDown(char key) {
		return keysDownThisFrame[key];
	}

	/**
	 * Checks if the key was pressed between this frame and last
	 * 
	 * @param key
	 *            A character representing the key to be checked. **MUST BE
	 *            UPERCASE**
	 * @return True if and only if the key was pressed just now
	 */
	public static boolean getKeyPressed(char key) {
		return pressed[key];
	}

	/**
	 * Checks if the key was released between this frame and last
	 * 
	 * @param key
	 *            A character representing the key to be checked. **MUST BE
	 *            UPERCASE**
	 * @return True if and only if the key was released just now
	 */
	public static boolean getKeyReleased(int key) {
		return released[key];
	}
}
