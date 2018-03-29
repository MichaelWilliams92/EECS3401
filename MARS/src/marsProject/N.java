package marsProject;

public class N { // provides the X and Y coordinates for a node
	int x;
	int y;

	public N(int a, int b) { // constructor class
		x = a;
		y = b;
	}

	public int getX() { // return X
		return x;
	}

	public int getY() { // returns Y
		return y;
	}

	@Override
	public String toString() {
		return ("(" + y + "," + x + ")");
	}// new toString method
}// class N