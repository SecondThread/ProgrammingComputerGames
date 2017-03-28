package graphics;

import main.Main;
import math.GeneralMath;
import math.Vector2;

public class Camera {
	
	public static Vector2 cameraPosition=Vector2.ZERO;
	
	public static void resetCamera() {
		cameraPosition=Vector2.ZERO;
	}
	
	public static void update(Vector2 positionOfPlayer) {
		//it is more comfortable to look at slightly above the player than at him directly
		Vector2 targetPosition=positionOfPlayer.subtract(new Vector2(Main.WIDTH/2, Main.HEIGHT/2)).subtract(new Vector2(0, 60));
		double distanceBetween=targetPosition.distance(cameraPosition);
		//move the camera faster if it is farther away from the player
		double speed=distanceBetween>200?0.2:0.04;
		cameraPosition=GeneralMath.lerp(cameraPosition, targetPosition, speed);
	}
	
}
