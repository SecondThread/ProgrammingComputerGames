package particles;

import graphics.Sprite;
import javafx.scene.canvas.GraphicsContext;
import math.Vector2;
import objects.GameObject;
import scenes.MainScene;

public class Particle extends GameObject {

	private boolean hasFriction, hasRotationalFriction, hasGravity, fadesOut;
	private int maxLifetime;
	private int lifetimeCounter=0;
	private Sprite toDraw;
	private Vector2 position, velocity;
	private int width, height;
	private double angle=0, angularVelocity;
	
	public Particle(Sprite toDraw, Vector2 position, Vector2 velocity, int maxLifetime, boolean hasFriction, boolean hasRotationalFriction, 
			boolean hasGravity, boolean fadesOut, int width, int height, double angle, double angularVelocity) {
		this.maxLifetime=maxLifetime;
		this.hasFriction=hasFriction;
		this.hasGravity=hasGravity;
		this.fadesOut=fadesOut;
		this.toDraw=toDraw;
		this.position=position;
		this.velocity=velocity;
		this.width=width;
		this.height=height;
		this.hasRotationalFriction=hasRotationalFriction;
		this.angle=angle;
		this.angularVelocity=angularVelocity;
		System.out.println("particle created");
	}
	
	public void update() {
		position=position.add(velocity);
		angle+=angularVelocity;
		lifetimeCounter++;
		if (lifetimeCounter>maxLifetime) {
			MainScene.getGameObjects().remove(this);
		}
		
	}
	
	public void render(GraphicsContext gc) {
		double alpha=1;
		if (fadesOut) {
			alpha=1-lifetimeCounter/(double)maxLifetime;
		}
		toDraw.setAlpha(alpha);
		toDraw.draw(gc, position, width, height, angle, new Vector2(width/2, height/2), false);
	}
	
}
