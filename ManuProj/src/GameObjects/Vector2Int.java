package GameObjects;
public class Vector2Int {
	private int x;
	private int y;

	public Vector2Int(int x, int y) {
		this.x = x;
		this.y = y;
		versorizar();
	}

	public static Vector2Int Implicit(Vector2 vec)
	{
		int x = Math.round(vec.getX());
		int y = Math.round(vec.getY());
		return new Vector2Int(x,y);
	}

	public int getX() {
		return x;
	}
	
	public int getY() { return y; }

	private void versorizar() {
		double norma = Math.sqrt((x*x) + (y*y));
		x /= norma;
		y /= norma;
	}

	public String toString() { return "("+x+","+y+")"; }

}