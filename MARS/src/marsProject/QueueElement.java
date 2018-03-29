package marsProject;

import java.util.ArrayList;

public class QueueElement {// will be the element of the priority queue
	N n;
	static ArrayList p = new ArrayList();
	double function;

	public QueueElement(N z, ArrayList path, double f) {
		n = z;
		p = path;
		function = f;
	}// constructor

	public N getN() { // return n
		return n;
	}

	public ArrayList getPath() {
		return p;
	}

	public double getF() {
		return function;
	}

}// QueueElement Class