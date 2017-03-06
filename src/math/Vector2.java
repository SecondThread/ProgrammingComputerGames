package math;

public class Vector2 {
	
	public static final Vector2 ZERO=new Vector2(0, 0);
	
	private double x, y;
	
	public Vector2(double x, double y) {
		this.x=x;
		this.y=y;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public double distance(Vector2 other) {
		return Math.hypot(x-other.getX(), y-other.getY());
	}
	
	public Vector2 scaleBy(double scalar) {
		return new Vector2(x*scalar, y*scalar);
	}
	
	public Vector2 add(Vector2 other) {
		return new Vector2(x+other.getX(), y+other.getY());
	}
	
	public Vector2 subtract(Vector2 other) {
		return this.add(other.scaleBy(-1));
	}
	
	public double dot(Vector2 other) {
		return this.x*other.getY()+this.y*other.getY();
	}
}
