package marsProject;

import java.util.PriorityQueue;

import java.util.Comparator;

import java.util.Queue;

import java.util.ArrayList;

import java.math.*;

public class MarsPlanner {
	static ArrayList list = new ArrayList();
	static int[][] grid = new int[5][5];
	static boolean remove = false;

	public static void main(String[] args) {
		//obstacles are represented as the number 1 on the grid
		//points A B and C are nodes created below
		grid[0][1] = 1;
		grid[0][3] = 1;
		grid[0][4] = 1;
		grid[1][1] = 1;
		grid[1][3] = 1;
		grid[3][2] = 1;
		grid[4][3] = 1;
		grid[4][4] = 1;

		N a = new N(0, 0);
		N b = new N(4, 2);
		N c = new N(2, 4);

	//	PriorityQueue<QueueElement> MarsQueues = new PriorityQueue<>(10,comparator); 
	//	MarsQueues.add(a, list, nonAdmissableFunction(0,4,0,2);
		//System.out.println
		//Below i create an ArrayList to store the answer and call the A* function 
		//using the non-admissable function
		//Afterwards, its contents (ie the path) is printed out
		ArrayList answer = new ArrayList(); 
		answer = A_star_nonAdmissable(a, b);
		for (int i = 0; i <= answer.size() - 1; i++) {
			System.out.println(answer.get(i).toString());
		}// for loop to print out path
		
		//Below i create an ArrayList to store the answer and call the A* function 
		//using the admissable function
		//Afterwards, its contents (ie the path) is printed out
		System.out.println();
		answer = new ArrayList(); 
		answer = A_star_Admissable(a, b);
		for (int i = 0; i <= answer.size() - 1; i++) {
			System.out.println(answer.get(i).toString());
		}// for loop to print out path

	}

	//A* function.  Starts by creating and placing the starting point into the priority Queue
	//Afterwards, based on its position, appropriate adjacent points are checked and info is added to the queue
	//This is done until a path is made from the starting to ending point
	//if no path is found, null is returned
	public static ArrayList A_star_nonAdmissable(N a, N b) {
		N tempN;
		ArrayList tempL;
		double tempF;
		
		//First, we instantiate and create the priority queue with its first value
		PriorityQueue<QueueElement> MarsQueue = new PriorityQueue<>(10,comparator); 
		list = new ArrayList();
		list.add(a);
		QueueElement e = new QueueElement(a, list, nonAdmissableFunction(a.getX(), b.getX(), a.getY(), b.getY()));
		//System.out.println(e.function + " " + e.p + " " + e.n);
		MarsQueue.add(e);

		while (MarsQueue.isEmpty() == false) {
			QueueElement C = new QueueElement( MarsQueue.peek().getN(), MarsQueue.peek().getPath(),MarsQueue.peek().getF() );
			MarsQueue.poll();

			// check each cell adjacent to C. If adjacent Node = B, return path
			// else insert new queue element into the queue
			// if cell contains '1' value, do not check (ie a blockade)
			// broken into 9 cases

			// case 1: C is in the top left corner of the grid
			// Cell c1
			if (C.n.getX() == 0 && C.n.getY() == 0) {
				if (b.getY() == 0 && b.getX() == 1) {
					C.p.add(b);
					return C.p;
				}
				else if (grid[0][1] != 1) {
					tempN = new N(1, 0);
					tempL = new ArrayList();
					
					tempL.addAll(C.p);
					tempL.add(tempN);
					e = new QueueElement(tempN, tempL, nonAdmissableFunction(tempN.getX(), b.getX(), tempN.getY(), b.getY()));
					MarsQueue.add(e);
				//	C.p.remove(C.p.size() - 1);
				//	remove = true;
					System.out.println(e.function +" " + e.getPath() + " " + e.getN().toString());

				}

				// Cell c2
				if (b.getY() == 1 && b.getX() == 1) {
					C.p.add(b);
					return C.p;
				}

				else if (grid[1][1] != 1) {
					tempN = new N(1, 1); 
					tempL = new ArrayList();
					tempL.addAll(C.p);
					tempL.add(tempN); 
					e = new QueueElement(tempN, tempL, nonAdmissableFunction(tempN.getX(), b.getX(), tempN.getY(), b.getY()));
					MarsQueue.add(e);
					System.out.println(e.function +" " + e.getPath() + " " + e.getN().toString());
				//	C.p.remove(C.p.size() - 1);

				}

				// Cell c3
				if (b.getY() == 1 && b.getX() == 0) {
					C.p.add(b);
					return C.p;
				}

				else if (grid[1][0] != 1) {
					tempN = new N(0, 1);
					tempL = new ArrayList();
					for(int i = 0; i <= C.getPath().size() - 1; i++)
					tempL.add(C.getPath().get(i));
							
					//tempL.addAll(C.getPath());
					tempL.add(tempN); 
					e = new QueueElement(tempN, tempL, nonAdmissableFunction(
							tempN.getX(), b.getX(), tempN.getY(), b.getY()));
					MarsQueue.add(e);
				//	C.p.remove(C.p.size() - 1);
					System.out.println(e.function +" " + e.getPath() + " " + e.getN().toString());
					

				}
				
				

			}// case 1
// ///////////////////////////////////////////////////////////////////////////////////////////////////////////////

			// case 2: C is in the top right corner of the grid
			else if (C.n.getX() == grid[0].length - 1 && C.n.getY() == 0) {

				// Cell c1
				if (b.getY() == 0 && b.getX() == (grid[0].length - 2)) {
					C.p.add(b);
					return C.p;
				}
				else if (grid[0][grid[0].length - 2] != 1) {
					tempN = new N(grid[0].length - 2, 0);
					tempL = new ArrayList();
					tempL.addAll(C.p);
					tempL.add(tempN); 
					e = new QueueElement(tempN, tempL, nonAdmissableFunction(
							tempN.getX(), b.getX(), tempN.getY(), b.getY()));
					MarsQueue.add(e);

				}

				// Cell c2
				if (b.getY() == 1 && b.getX() == grid[0].length - 2) {
					C.p.add(b);
					return C.p;
				}

				else if (grid[1][grid[0].length - 2] != 1) {
					tempN = new N(grid[0].length - 2, 1); 
					tempL = new ArrayList();
					tempL.addAll(C.p);
					tempL.add(tempN);
					e = new QueueElement(tempN, tempL, nonAdmissableFunction(
							tempN.getX(), b.getX(), tempN.getY(), b.getY()));
					MarsQueue.add(e);

				}

				// Cell c3
				if (b.getY() == 1 && b.getX() == grid[0].length - 1) {
					C.p.add(b);
					return C.p;
				}

				else if (grid[1][grid[0].length - 1] != 1) {
					tempN = new N(grid[0].length - 1, 1); 
					tempL = new ArrayList();
					tempL.addAll(C.p);
					tempL.add(tempN); 
					e = new QueueElement(tempN, tempL, nonAdmissableFunction(
							tempN.getX(), b.getX(), tempN.getY(), b.getY()));
					MarsQueue.add(e);

				}

			}// case 2

// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

			// case 3: C is in the bottom left corner of the grid
			else if (C.n.getX() == 0 && C.n.getY() == grid.length - 1) {

				// Cell c1
				if (b.getY() == grid.length - 2 && b.getX() == 0) {
					C.p.add(b);
					return C.p;
				}
				else if (grid[grid.length - 2][0] != 1) {
					tempN = new N(0, grid.length - 2); 
					tempL = new ArrayList();
					tempL.addAll(C.p);
					tempL.add(tempN); 
					e = new QueueElement(tempN, tempL, nonAdmissableFunction(
							tempN.getX(), b.getX(), tempN.getY(), b.getY()));
					MarsQueue.add(e);

				}

				// Cell c2
				if (b.getY() == grid.length - 2 && b.getX() == 1) {
					C.p.add(b);
					return C.p;
				}

				else if (grid[grid.length - 2][1] != 1) {
					tempN = new N(1, grid.length - 2); 
					tempL = new ArrayList();
					tempL.addAll(C.p);
					tempL.add(tempN); 
					e = new QueueElement(tempN, tempL, nonAdmissableFunction(
							tempN.getX(), b.getX(), tempN.getY(), b.getY()));
					MarsQueue.add(e);

				}

				// Cell c3
				if (b.getY() == grid.length - 1 && b.getX() == 1) {
					C.p.add(b);
					return C.p;
				}

				else if (grid[grid.length - 1][1] != 1) {
					tempN = new N(1, grid.length - 1); 
					tempL = new ArrayList();
					tempL.addAll(C.p);
					tempL.add(tempN); 
					e = new QueueElement(tempN, tempL, nonAdmissableFunction(
							tempN.getX(), b.getX(), tempN.getY(), b.getY()));
					MarsQueue.add(e);

				}

			}// case 3
// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

			// case 4: C is in the bottom right corner of the grid
			else if (C.n.getX() == grid[grid.length - 1].length - 1
					&& C.n.getY() == grid.length - 1) {

				// Cell c1
				if (b.getY() == grid.length - 1
						&& b.getX() == grid[grid.length - 1].length - 2) {
					C.p.add(b);
					return C.p;
				}
				else if (grid[grid.length - 1][grid[grid.length - 1].length - 2] != 1) {
					tempN = new N(grid[grid.length - 1].length - 2,
							grid.length - 1); 
					tempL = new ArrayList();
					tempL.addAll(C.p);
					tempL.add(tempN); 
					e = new QueueElement(tempN, tempL, nonAdmissableFunction(
							tempN.getX(), b.getX(), tempN.getY(), b.getY()));
					MarsQueue.add(e);

				}

				// Cell c2
				if (b.getY() == grid.length - 2 && b.getX() == grid.length - 2) {
					C.p.add(b);
					return C.p;
				}

				else if (grid[grid.length - 2][grid[grid.length - 1].length - 2] != 1) {
					tempN = new N(grid[grid.length - 1].length - 2,
							grid.length - 2); 
					tempL = new ArrayList();
					tempL.addAll(C.p);
					tempL.add(tempN); 
					e = new QueueElement(tempN, tempL, nonAdmissableFunction(
							tempN.getX(), b.getX(), tempN.getY(), b.getY()));
					MarsQueue.add(e);

				}

				// Cell c3
				if (b.getY() == grid.length - 2
						&& b.getX() == grid[grid.length - 1].length - 1) {
					C.p.add(b);
					return C.p;
				}

				else if (grid[grid.length - 2][grid[grid.length - 1].length - 1] != 1) {
					tempN = new N(grid[grid.length - 1].length - 1,
							grid.length - 2); 
					tempL = new ArrayList();
					tempL.addAll(C.p);
					tempL.add(tempN); 
					e = new QueueElement(tempN, tempL, nonAdmissableFunction(
							tempN.getX(), b.getX(), tempN.getY(), b.getY()));
					MarsQueue.add(e);

				}

			}// case 4
// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////

			// case 5: C is in the bottom of the grid
			else if (C.n.getY() == grid.length - 1) {

				// Cell c1
				if (b.getY() == grid.length - 1 && b.getX() == C.n.getX() - 1) {
					C.p.add(b);
					return C.p;
				}
				else if (grid[grid.length - 1][C.n.getX() - 1] != 1) {
					tempN = new N(C.n.getX() - 1, grid.length - 1);
					tempL = new ArrayList();
					tempL.addAll(C.p);
					tempL.add(tempN); 
					e = new QueueElement(tempN, tempL, nonAdmissableFunction(
							tempN.getX(), b.getX(), tempN.getY(), b.getY()));
					MarsQueue.add(e);

				}

				// Cell c2
				if (b.getY() == grid.length - 2 && b.getX() == C.n.getX() - 1) {
					C.p.add(b);
					return C.p;
				}

				else if (grid[grid.length - 2][C.n.getX() - 1] != 1) {
					tempN = new N(C.n.getX() - 1, grid.length - 2);
					tempL = new ArrayList();
					tempL.addAll(C.p);
					tempL.add(tempN); 
					e = new QueueElement(tempN, tempL, nonAdmissableFunction(
							tempN.getX(), b.getX(), tempN.getY(), b.getY()));
					MarsQueue.add(e);

				}

				// Cell c3
				if (b.getY() == grid.length - 2 && b.getX() == C.n.getX()) {
					C.p.add(b);
					return C.p;
				}

				else if (grid[grid.length - 2][C.n.getX()] != 1) {
					tempN = new N(C.n.getX(), grid.length - 2); 
					tempL = new ArrayList();
					tempL.addAll(C.p);
					tempL.add(tempN); 
					e = new QueueElement(tempN, tempL, nonAdmissableFunction(
							tempN.getX(), b.getX(), tempN.getY(), b.getY()));
					MarsQueue.add(e);

				}

				// Cell c4
				if (b.getY() == grid.length - 2 && b.getX() == C.n.getX() + 1) {
					C.p.add(b);
					return C.p;
				}

				else if (grid[grid.length - 2][C.n.getX() + 1] != 1) {
					tempN = new N(C.n.getX() + 1, grid.length - 2);
					tempL = new ArrayList();
					tempL.addAll(C.p);
					tempL.add(tempN);
					e = new QueueElement(tempN, tempL, nonAdmissableFunction(
							tempN.getX(), b.getX(), tempN.getY(), b.getY()));
					MarsQueue.add(e);

				}

				// Cell c5
				if (b.getY() == grid.length - 1 && b.getX() == C.n.getX() + 1) {
					C.p.add(b);
					return C.p;
				}

				else if (grid[grid.length - 1][C.n.getX() + 1] != 1) {
					tempN = new N(C.n.getX() + 1, grid.length - 1); 
					tempL = new ArrayList();
					tempL.addAll(C.p);
					tempL.add(tempN); 
					e = new QueueElement(tempN, tempL, nonAdmissableFunction(
							tempN.getX(), b.getX(), tempN.getY(), b.getY()));
					MarsQueue.add(e);

				}

			}// case 5
// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

			// case 6: C is in the left of the grid
			else if (C.n.getX() == 0) {

				// Cell c1
				if (b.getY() == C.n.getY() - 1 && b.getX() == 0) {
					C.p.add(b);
					return C.p;
				}
				else if (grid[C.n.getY() - 1][0] != 1) {
					tempN = new N(0, C.n.getY() - 1); 
					tempL = new ArrayList();
					// for( item: C.getPath()) tempL.add(item);
					tempL.addAll(C.getPath());
					tempL.add(tempN); 
					e = new QueueElement(tempN, tempL, nonAdmissableFunction(
							tempN.getX(), b.getX(), tempN.getY(), b.getY()));
					MarsQueue.add(e);
					//C.p.remove(C.p.size() - 1);
												
					System.out.println(e.function +" " + e.getPath() + " " + e.getN().toString());

				}

				// Cell c2
				if (b.getY() == C.n.getY() - 1 && b.getX() == C.n.getX() + 1) {
					C.p.add(b);
					return C.p;
				}

				else if (grid[C.n.getY() - 1][C.n.getX() + 1] != 1) {
					tempN = new N(C.n.getX() + 1, C.n.getY() - 1); 
					tempL = new ArrayList();
					tempL.addAll(C.p);
					tempL.add(tempN);
					e = new QueueElement(tempN, tempL, nonAdmissableFunction(
							tempN.getX(), b.getX(), tempN.getY(), b.getY()));
					MarsQueue.add(e);
					System.out.println(e.function +" " + e.getPath() + " " + e.getN().toString());

				}

				// Cell c3
				if (b.getY() == C.n.getY() && b.getX() == C.n.getX() + 1) {
					C.p.add(b);
					return C.p;
				}

				else if (grid[C.n.getY()][C.n.getX() + 1] != 1) {
					tempN = new N(C.n.getX() + 1, C.n.getY()); 
					tempL = new ArrayList();
					tempL.addAll(C.p);
					tempL.add(tempN); 
					e = new QueueElement(tempN, tempL, nonAdmissableFunction(
							tempN.getX(), b.getX(), tempN.getY(), b.getY()));
					MarsQueue.add(e);
					System.out.println(e.function +" " + e.getPath() + " " + e.getN().toString());

				}

				// Cell c4
				if (b.getY() == C.n.getY() + 1 && b.getX() == C.n.getX() + 1) {
					C.p.add(b);
					return C.p;
				}

				else if (grid[C.n.getY() + 1][C.n.getX() + 1] != 1) {
					tempN = new N(C.n.getX() + 1, C.n.getY() + 1); 
					tempL = new ArrayList();
					tempL.addAll(C.p);
					tempL.add(tempN); 
					e = new QueueElement(tempN, tempL, nonAdmissableFunction(
							tempN.getX(), b.getX(), tempN.getY(), b.getY()));
					MarsQueue.add(e);
					System.out.println(e.function +" " + e.getPath() + " " + e.getN().toString());
				

				}

				// Cell c5
				if (b.getY() == C.n.getY() + 1 && b.getX() == C.n.getX()) {
					C.p.add(b);
					return C.p;
				}

				else if (grid[C.n.getY() + 1][C.n.getX()] != 1) {
					tempN = new N(C.n.getX(), C.n.getY() + 1); 
					tempL = new ArrayList();
					tempL.addAll(C.p);
					tempL.add(tempN); 
					e = new QueueElement(tempN, tempL, nonAdmissableFunction(
							tempN.getX(), b.getX(), tempN.getY(), b.getY()));
					MarsQueue.add(e);
					System.out.println(e.function +" " + e.getPath() + " " + e.getN().toString());
					

				}

			}// case 6

// ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

			// case 7: C is in the top of the grid
			else if (C.n.getY() == 0) {

				// Cell c1
				if (b.getY() == 0 && b.getX() == C.n.getX() - 1) {
					C.p.add(b);
					return C.p;
				}
				else if (grid[0][C.n.getX() - 1] != 1) {
					tempN = new N(C.n.getX() - 1, 0); 
					tempL = new ArrayList();
					tempL.addAll(C.p);
					tempL.add(tempN); 
					e = new QueueElement(tempN, tempL, nonAdmissableFunction(
							tempN.getX(), b.getX(), tempN.getY(), b.getY()));
					MarsQueue.add(e);

				}

				// Cell c2
				if (b.getY() == C.n.getY() + 1 && b.getX() == C.n.getX() - 1) {
					C.p.add(b);
					return C.p;
				}

				else if (grid[C.n.getY() + 1][C.n.getX() - 1] != 1) {
					tempN = new N(C.n.getX() - 1, C.n.getY() + 1); 
					tempL = new ArrayList();
					tempL.addAll(C.p);
					tempL.add(tempN); 
					e = new QueueElement(tempN, tempL, nonAdmissableFunction(
							tempN.getX(), b.getX(), tempN.getY(), b.getY()));
					MarsQueue.add(e);

				}

				// Cell c3
				if (b.getY() == C.n.getY() + 1 && b.getX() == C.n.getX()) {
					C.p.add(b);
					return C.p;
				}

				else if (grid[C.n.getY() + 1][C.n.getX()] != 1) {
					tempN = new N(C.n.getX(), C.n.getY() + 1); 
					tempL = new ArrayList();
					tempL.addAll(C.p);
					tempL.add(tempN); 
					e = new QueueElement(tempN, tempL, nonAdmissableFunction(
							tempN.getX(), b.getX(), tempN.getY(), b.getY()));
					MarsQueue.add(e);

				}

				// Cell c4
				if (b.getY() == C.n.getY() + 1 && b.getX() == C.n.getX() + 1) {
					C.p.add(b);
					return C.p;
				}

				else if (grid[C.n.getY() + 1][C.n.getX() + 1] != 1) {
					tempN = new N(C.n.getX() + 1, C.n.getY() + 1); 
					tempL = new ArrayList();
					tempL.addAll(C.p);
					tempL.add(tempN); 
					e = new QueueElement(tempN, tempL, nonAdmissableFunction(
							tempN.getX(), b.getX(), tempN.getY(), b.getY()));
					MarsQueue.add(e);

				}

				// Cell c5
				if (b.getY() == C.n.getY() && b.getX() == C.n.getX() + 1) {
					C.p.add(b);
					return C.p;
				}

				else if (grid[C.n.getY()][C.n.getX() + 1] != 1) {
					tempN = new N(C.n.getX() + 1, C.n.getY()); 
					tempL = new ArrayList();
					tempL.addAll(C.p);
					tempL.add(tempN); 
					e = new QueueElement(tempN, tempL, nonAdmissableFunction(
							tempN.getX(), b.getX(), tempN.getY(), b.getY()));
					// make new element
					MarsQueue.add(e);

				}

			}// case 7
	// ///////////////////////////////////////////////////////////////////////////////////////////////////////////////

			// case 8: C is in the right of the grid
			else if (C.n.getX() == grid[grid.length - 1].length - 1) {

				// Cell c1
				if (b.getY() == C.n.getY() - 1 && b.getX() == C.n.getX()) {
					C.p.add(b);
					return C.p;
				}
				else if (grid[C.n.getY() - 1][C.n.getX()] != 1) {
					tempN = new N(C.n.getX(), C.n.getY() - 1); 
					tempL = new ArrayList();
					tempL.addAll(C.p);
					tempL.add(tempN); 
					e = new QueueElement(tempN, tempL, nonAdmissableFunction(
							tempN.getX(), b.getX(), tempN.getY(), b.getY()));
					MarsQueue.add(e);

				}

				// Cell c2
				if (b.getY() == C.n.getY() - 1 && b.getX() == C.n.getX() - 1) {
					C.p.add(b);
					return C.p;
				}

				else if (grid[C.n.getY() - 1][C.n.getX() - 1] != 1) {
					tempN = new N(C.n.getX() - 1, C.n.getY() - 1); 
					tempL = new ArrayList();
					tempL.addAll(C.p);
					tempL.add(tempN); 
					e = new QueueElement(tempN, tempL, nonAdmissableFunction(
							tempN.getX(), b.getX(), tempN.getY(), b.getY()));
					MarsQueue.add(e);

				}

				// Cell c3
				if (b.getY() == C.n.getY() && b.getX() == C.n.getX() - 1) {
					C.p.add(b);
					return C.p;
				}

				else if (grid[C.n.getY()][C.n.getX() - 1] != 1) {
					tempN = new N(C.n.getX() - 1, C.n.getY()); 
					tempL = new ArrayList();
					tempL.addAll(C.p);
					tempL.add(tempN); 
					e = new QueueElement(tempN, tempL, nonAdmissableFunction(
							tempN.getX(), b.getX(), tempN.getY(), b.getY()));
					MarsQueue.add(e);

				}

				// Cell c4
				if (b.getY() == C.n.getY() + 1 && b.getX() == C.n.getX() - 1) {
					C.p.add(b);
					return C.p;
				}

				else if (grid[C.n.getY() + 1][C.n.getX() - 1] != 1) {
					tempN = new N(C.n.getX() - 1, C.n.getY() + 1);
					tempL = new ArrayList();
					tempL.addAll(C.p);
					tempL.add(tempN); 
					e = new QueueElement(tempN, tempL, nonAdmissableFunction(
							tempN.getX(), b.getX(), tempN.getY(), b.getY()));
					MarsQueue.add(e);

				}

				// Cell c5
				if (b.getY() == C.n.getY() + 1 && b.getX() == C.n.getX()) {
					C.p.add(b);
					return C.p;
				}

				else if (grid[C.n.getY() + 1][C.n.getX()] != 1) {
					tempN = new N(C.n.getX(), C.n.getY() + 1); 
					tempL = new ArrayList();
					tempL.addAll(C.p);
					tempL.add(tempN); 
					e = new QueueElement(tempN, tempL, nonAdmissableFunction(
							tempN.getX(), b.getX(), tempN.getY(), b.getY()));
					MarsQueue.add(e);

				}

			}// case 8

	// ////////////////////////////////////////////////////////////////////////////////////////////////////////////

			// case 9: C isn't at any border on the grid
			else if (C.n.getX() != grid[grid.length - 1].length - 1
					&& C.n.getY() != grid.length - 1 && C.n.getX() != 0
					&& C.n.getY() != 0) {

				// Cell c1
				// if Ci == B return path
				if (b.getY() == C.n.getY() - 1 && b.getX() == C.n.getX() - 1) {
					C.p.add(b);
					return C.p;
				}
				// else if Ci != blockade insert new element into queue
				else if (grid[C.n.getY() - 1][C.n.getX() - 1] != 1) {
					tempN = new N(C.n.getX() - 1, C.n.getY() - 1);
					tempL = new ArrayList();
					tempL.addAll(C.p);
					tempL.add(tempN); // make new list containing new path info
					e = new QueueElement(tempN, tempL, nonAdmissableFunction(
							tempN.getX(), b.getX(), tempN.getY(), b.getY()));
					MarsQueue.add(e);

				}

				// Cell c2
				if (b.getY() == C.n.getY() - 1 && b.getX() == C.n.getX()) {
					C.p.add(b);
					return C.p;
				}

				else if (grid[C.n.getY() - 1][C.n.getX()] != 1) {
					tempN = new N(C.n.getX(), C.n.getY() - 1); 
					tempL = new ArrayList();
					tempL.addAll(C.p);
					tempL.add(tempN); // make new list containing new path info
					e = new QueueElement(tempN, tempL, nonAdmissableFunction(
							tempN.getX(), b.getX(), tempN.getY(), b.getY()));
					MarsQueue.add(e);

				}

				// Cell c3
				if (b.getY() == C.n.getY() - 1 && b.getX() == C.n.getX() + 1) {
					C.p.add(b);
					return C.p;
				}

				else if (grid[C.n.getY() - 1][C.n.getX() + 1] != 1) {
					tempN = new N(C.n.getX() + 1, C.n.getY() - 1); 
					tempL = new ArrayList();
					tempL.addAll(C.p);
					tempL.add(tempN); // make new list containing new path info
					e = new QueueElement(tempN, tempL, nonAdmissableFunction(
							tempN.getX(), b.getX(), tempN.getY(), b.getY()));
					MarsQueue.add(e);

				}

				// Cell c4
				if (b.getY() == C.n.getY() && b.getX() == C.n.getX() + 1) {
					C.p.add(b);
					return C.p;
				}

				else if (grid[C.n.getY()][C.n.getX() + 1] != 1) {
					tempN = new N(C.n.getX() + 1, C.n.getY()); 
					tempL = new ArrayList();
					tempL.addAll(C.p);
					tempL.add(tempN); // make new list containing new path info
					e = new QueueElement(tempN, tempL, nonAdmissableFunction(
							tempN.getX(), b.getX(), tempN.getY(), b.getY()));
					MarsQueue.add(e);

				}

				// Cell c5
				if (b.getY() == C.n.getY() + 1 && b.getX() == C.n.getX() + 1) {
					C.p.add(b);
					return C.p;
				}

				else if (grid[C.n.getY() + 1][C.n.getX() + 1] != 1) {
					tempN = new N(C.n.getX() + 1, C.n.getY() + 1); 
					tempL = new ArrayList();
					tempL.addAll(C.p);
					tempL.add(tempN); // make new list containing new path info
					e = new QueueElement(tempN, tempL, nonAdmissableFunction(
							tempN.getX(), b.getX(), tempN.getY(), b.getY()));
					MarsQueue.add(e);

				}

				// Cell c6
				if (b.getY() == C.n.getY() + 1 && b.getX() == C.n.getX()) {
					C.p.add(b);
					return C.p;
				}

				else if (grid[C.n.getY() + 1][C.n.getX()] != 1) {
					tempN = new N(C.n.getX(), C.n.getY() + 1); // make new node
					tempL = new ArrayList();
					tempL.addAll(C.p);
					tempL.add(tempN); // make new list containing new path info
					e = new QueueElement(tempN, tempL, nonAdmissableFunction(
							tempN.getX(), b.getX(), tempN.getY(), b.getY()));
					// make new element
					MarsQueue.add(e);

				}

				// Cell c7
				if (b.getY() == C.n.getY() + 1 && b.getX() == C.n.getX() - 1) {
					C.p.add(b);
					return C.p;
				}

				else if (grid[C.n.getY() + 1][C.n.getX() - 1] != 1) {
					tempN = new N(C.n.getX() - 1, C.n.getY() + 1); 
					tempL = new ArrayList();
					tempL.addAll(C.p);
					tempL.add(tempN); // make new list containing new path info
					e = new QueueElement(tempN, tempL, nonAdmissableFunction(
							tempN.getX(), b.getX(), tempN.getY(), b.getY()));
					MarsQueue.add(e);

				}

				// Cell c8
				if (b.getY() == C.n.getY() && b.getX() == C.n.getX() - 1) {
					C.p.add(b);
					return C.p;
				}

				else if (grid[C.n.getY()][C.n.getX() - 1] != 1) {
					tempN = new N(C.n.getX() - 1, C.n.getY()); // make new node
					tempL = new ArrayList();
					tempL.addAll(C.p);
					tempL.add(tempN); // make new list containing new path info
					e = new QueueElement(tempN, tempL, nonAdmissableFunction(
							tempN.getX(), b.getX(), tempN.getY(), b.getY()));
					// make new element
					MarsQueue.add(e);

				}

			}// case 8

		}// while loop

		// if Queue is empty and a path has not been found, there is no path
		return null; 
	}// A* search algorithm with non-admissable function
///////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	//The following is the same function as above, only calling the admissable function this time
	//A* function.  Starts by creating and placing the starting point into the priority Queue
		//Afterwards, based on its position, appropriate adjacent points are checked and info is added to the queue
		//This is done until a path is made from the starting to ending point
		//if no path is found, null is returned
		public static ArrayList A_star_Admissable(N a, N b) {
			N tempN;
			ArrayList tempL;
			double tempF;
			
			PriorityQueue<QueueElement> MarsQueue = new PriorityQueue<>(10,comparator); 
			list = new ArrayList();
			list.add(a);
			QueueElement e = new QueueElement(a, list, admissableFunction(a.getX(), b.getX(), a.getY(), b.getY()));
			MarsQueue.add(e);

			while (MarsQueue.isEmpty() == false) {
				QueueElement C = MarsQueue.peek();
				MarsQueue.poll();

				// check each cell adjacent to C. If adjacent Node = B, return path
				// else insert new queue element into the queue
				// if cell contains '1' value, do not check (ie a blockade)
				// broken into 9 cases

				// case 1: C is in the top left corner of the grid
				// Cell c1
				if (C.n.getX() == 0 && C.n.getY() == 0) {
					if (b.getY() == 0 && b.getX() == 1) {
						C.p.add(b);
						return C.p;
					}
					else if (grid[0][1] != 1) {
						tempN = new N(1, 0);
						tempL = new ArrayList();
						tempL.addAll(C.p);
						tempL.add(tempN);
						e = new QueueElement(tempN, tempL, admissableFunction(tempN.getX(), b.getX(), tempN.getY(), b.getY()));
						MarsQueue.add(e);

					}

					// Cell c2
					if (b.getY() == 1 && b.getX() == 1) {
						C.p.add(b);
						return C.p;
					}

					else if (grid[1][1] != 1) {
						tempN = new N(1, 1); 
						tempL = new ArrayList();
						tempL.addAll(C.p);
						tempL.add(tempN); 
						e = new QueueElement(tempN, tempL, admissableFunction(tempN.getX(), b.getX(), tempN.getY(), b.getY()));
						MarsQueue.add(e);

					}

					// Cell c3
					if (b.getY() == 1 && b.getX() == 0) {
						C.p.add(b);
						return C.p;
					}

					else if (grid[1][0] != 1) {
						tempN = new N(0, 1);
						tempL = new ArrayList();
						tempL.addAll(C.p);
						tempL.add(tempN); 
						e = new QueueElement(tempN, tempL, admissableFunction(
								tempN.getX(), b.getX(), tempN.getY(), b.getY()));
						MarsQueue.add(e);

					}

				}// case 1
	// ///////////////////////////////////////////////////////////////////////////////////////////////////////////////

				// case 2: C is in the top right corner of the grid
				else if (C.n.getX() == grid[0].length - 1 && C.n.getY() == 0) {

					// Cell c1
					if (b.getY() == 0 && b.getX() == (grid[0].length - 2)) {
						C.p.add(b);
						return C.p;
					}
					else if (grid[0][grid[0].length - 2] != 1) {
						tempN = new N(grid[0].length - 2, 0);
						tempL = new ArrayList();
						tempL.addAll(C.p);
						tempL.add(tempN); 
						e = new QueueElement(tempN, tempL, admissableFunction(
								tempN.getX(), b.getX(), tempN.getY(), b.getY()));
						MarsQueue.add(e);

					}

					// Cell c2
					if (b.getY() == 1 && b.getX() == grid[0].length - 2) {
						C.p.add(b);
						return C.p;
					}

					else if (grid[1][grid[0].length - 2] != 1) {
						tempN = new N(grid[0].length - 2, 1); 
						tempL = new ArrayList();
						tempL.addAll(C.p);
						tempL.add(tempN);
						e = new QueueElement(tempN, tempL, admissableFunction(
								tempN.getX(), b.getX(), tempN.getY(), b.getY()));
						MarsQueue.add(e);

					}

					// Cell c3
					if (b.getY() == 1 && b.getX() == grid[0].length - 1) {
						C.p.add(b);
						return C.p;
					}

					else if (grid[1][grid[0].length - 1] != 1) {
						tempN = new N(grid[0].length - 1, 1); 
						tempL = new ArrayList();
						tempL.addAll(C.p);
						tempL.add(tempN); 
						e = new QueueElement(tempN, tempL, admissableFunction(
								tempN.getX(), b.getX(), tempN.getY(), b.getY()));
						MarsQueue.add(e);

					}

				}// case 2

	// ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

				// case 3: C is in the bottom left corner of the grid
				else if (C.n.getX() == 0 && C.n.getY() == grid.length - 1) {

					// Cell c1
					if (b.getY() == grid.length - 2 && b.getX() == 0) {
						C.p.add(b);
						return C.p;
					}
					else if (grid[grid.length - 2][0] != 1) {
						tempN = new N(0, grid.length - 2); 
						tempL = new ArrayList();
						tempL.addAll(C.p);
						tempL.add(tempN); 
						e = new QueueElement(tempN, tempL, admissableFunction(
								tempN.getX(), b.getX(), tempN.getY(), b.getY()));
						MarsQueue.add(e);

					}

					// Cell c2
					if (b.getY() == grid.length - 2 && b.getX() == 1) {
						C.p.add(b);
						return C.p;
					}

					else if (grid[grid.length - 2][1] != 1) {
						tempN = new N(1, grid.length - 2); 
						tempL = new ArrayList();
						tempL.addAll(C.p);
						tempL.add(tempN); 
						e = new QueueElement(tempN, tempL, admissableFunction(
								tempN.getX(), b.getX(), tempN.getY(), b.getY()));
						MarsQueue.add(e);

					}

					// Cell c3
					if (b.getY() == grid.length - 1 && b.getX() == 1) {
						C.p.add(b);
						return C.p;
					}

					else if (grid[grid.length - 1][1] != 1) {
						tempN = new N(1, grid.length - 1); 
						tempL = new ArrayList();
						tempL.addAll(C.p);
						tempL.add(tempN); 
						e = new QueueElement(tempN, tempL, admissableFunction(
								tempN.getX(), b.getX(), tempN.getY(), b.getY()));
						MarsQueue.add(e);

					}

				}// case 3
	// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

				// case 4: C is in the bottom right corner of the grid
				else if (C.n.getX() == grid[grid.length - 1].length - 1
						&& C.n.getY() == grid.length - 1) {

					// Cell c1
					if (b.getY() == grid.length - 1
							&& b.getX() == grid[grid.length - 1].length - 2) {
						C.p.add(b);
						return C.p;
					}
					else if (grid[grid.length - 1][grid[grid.length - 1].length - 2] != 1) {
						tempN = new N(grid[grid.length - 1].length - 2,
								grid.length - 1); 
						tempL = new ArrayList();
						tempL.addAll(C.p);
						tempL.add(tempN); 
						e = new QueueElement(tempN, tempL, admissableFunction(
								tempN.getX(), b.getX(), tempN.getY(), b.getY()));
						MarsQueue.add(e);

					}

					// Cell c2
					if (b.getY() == grid.length - 2 && b.getX() == grid.length - 2) {
						C.p.add(b);
						return C.p;
					}

					else if (grid[grid.length - 2][grid[grid.length - 1].length - 2] != 1) {
						tempN = new N(grid[grid.length - 1].length - 2,
								grid.length - 2); 
						tempL = new ArrayList();
						tempL.addAll(C.p);
						tempL.add(tempN); 
						e = new QueueElement(tempN, tempL, admissableFunction(
								tempN.getX(), b.getX(), tempN.getY(), b.getY()));
						MarsQueue.add(e);

					}

					// Cell c3
					if (b.getY() == grid.length - 2
							&& b.getX() == grid[grid.length - 1].length - 1) {
						C.p.add(b);
						return C.p;
					}

					else if (grid[grid.length - 2][grid[grid.length - 1].length - 1] != 1) {
						tempN = new N(grid[grid.length - 1].length - 1,
								grid.length - 2); 
						tempL = new ArrayList();
						tempL.addAll(C.p);
						tempL.add(tempN); 
						e = new QueueElement(tempN, tempL, admissableFunction(
								tempN.getX(), b.getX(), tempN.getY(), b.getY()));
						MarsQueue.add(e);

					}

				}// case 4
	// //////////////////////////////////////////////////////////////////////////////////////////////////////////////////

				// case 5: C is in the bottom of the grid
				else if (C.n.getY() == grid.length - 1) {

					// Cell c1
					if (b.getY() == grid.length - 1 && b.getX() == C.n.getX() - 1) {
						C.p.add(b);
						return C.p;
					}
					else if (grid[grid.length - 1][C.n.getX() - 1] != 1) {
						tempN = new N(C.n.getX() - 1, grid.length - 1);
						tempL = new ArrayList();
						tempL.addAll(C.p);
						tempL.add(tempN); 
						e = new QueueElement(tempN, tempL, admissableFunction(
								tempN.getX(), b.getX(), tempN.getY(), b.getY()));
						MarsQueue.add(e);

					}

					// Cell c2
					if (b.getY() == grid.length - 2 && b.getX() == C.n.getX() - 1) {
						C.p.add(b);
						return C.p;
					}

					else if (grid[grid.length - 2][C.n.getX() - 1] != 1) {
						tempN = new N(C.n.getX() - 1, grid.length - 2);
						tempL = new ArrayList();
						tempL.addAll(C.p);
						tempL.add(tempN); 
						e = new QueueElement(tempN, tempL, admissableFunction(
								tempN.getX(), b.getX(), tempN.getY(), b.getY()));
						MarsQueue.add(e);

					}

					// Cell c3
					if (b.getY() == grid.length - 2 && b.getX() == C.n.getX()) {
						C.p.add(b);
						return C.p;
					}

					else if (grid[grid.length - 2][C.n.getX()] != 1) {
						tempN = new N(C.n.getX(), grid.length - 2); 
						tempL = new ArrayList();
						tempL.addAll(C.p);
						tempL.add(tempN); 
						e = new QueueElement(tempN, tempL, admissableFunction(
								tempN.getX(), b.getX(), tempN.getY(), b.getY()));
						MarsQueue.add(e);

					}

					// Cell c4
					if (b.getY() == grid.length - 2 && b.getX() == C.n.getX() + 1) {
						C.p.add(b);
						return C.p;
					}

					else if (grid[grid.length - 2][C.n.getX() + 1] != 1) {
						tempN = new N(C.n.getX() + 1, grid.length - 2);
						tempL = new ArrayList();
						tempL.addAll(C.p);
						tempL.add(tempN);
						e = new QueueElement(tempN, tempL, admissableFunction(
								tempN.getX(), b.getX(), tempN.getY(), b.getY()));
						MarsQueue.add(e);

					}

					// Cell c5
					if (b.getY() == grid.length - 1 && b.getX() == C.n.getX() + 1) {
						C.p.add(b);
						return C.p;
					}

					else if (grid[grid.length - 1][C.n.getX() + 1] != 1) {
						tempN = new N(C.n.getX() + 1, grid.length - 1); 
						tempL = new ArrayList();
						tempL.addAll(C.p);
						tempL.add(tempN); 
						e = new QueueElement(tempN, tempL, admissableFunction(
								tempN.getX(), b.getX(), tempN.getY(), b.getY()));
						MarsQueue.add(e);

					}

				}// case 5
	// /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

				// case 6: C is in the left of the grid
				else if (C.n.getX() == 0) {

					// Cell c1
					if (b.getY() == C.n.getY() - 1 && b.getX() == 0) {
						C.p.add(b);
						return C.p;
					}
					else if (grid[C.n.getY() - 1][0] != 1) {
						tempN = new N(0, C.n.getY() - 1); 
						tempL = new ArrayList();
						tempL.addAll(C.p);
						tempL.add(tempN); 
						e = new QueueElement(tempN, tempL, admissableFunction(
								tempN.getX(), b.getX(), tempN.getY(), b.getY()));
						MarsQueue.add(e);

					}

					// Cell c2
					if (b.getY() == C.n.getY() - 1 && b.getX() == C.n.getX() + 1) {
						C.p.add(b);
						return C.p;
					}

					else if (grid[C.n.getY() - 1][C.n.getX() + 1] != 1) {
						tempN = new N(C.n.getX() + 1, C.n.getY() - 1); 
						tempL = new ArrayList();
						tempL.addAll(C.p);
						tempL.add(tempN);
						e = new QueueElement(tempN, tempL, admissableFunction(
								tempN.getX(), b.getX(), tempN.getY(), b.getY()));
						MarsQueue.add(e);

					}

					// Cell c3
					if (b.getY() == C.n.getY() && b.getX() == C.n.getX() + 1) {
						C.p.add(b);
						return C.p;
					}

					else if (grid[C.n.getY()][C.n.getX() + 1] != 1) {
						tempN = new N(C.n.getX() + 1, C.n.getY()); 
						tempL = new ArrayList();
						tempL.addAll(C.p);
						tempL.add(tempN); 
						e = new QueueElement(tempN, tempL, admissableFunction(
								tempN.getX(), b.getX(), tempN.getY(), b.getY()));
						MarsQueue.add(e);

					}

					// Cell c4
					if (b.getY() == C.n.getY() + 1 && b.getX() == C.n.getX() + 1) {
						C.p.add(b);
						return C.p;
					}

					else if (grid[C.n.getY() + 1][C.n.getX() + 1] != 1) {
						tempN = new N(C.n.getX() + 1, C.n.getY() + 1); 
						tempL = new ArrayList();
						tempL.addAll(C.p);
						tempL.add(tempN); 
						e = new QueueElement(tempN, tempL, admissableFunction(
								tempN.getX(), b.getX(), tempN.getY(), b.getY()));
						MarsQueue.add(e);

					}

					// Cell c5
					if (b.getY() == C.n.getY() + 1 && b.getX() == C.n.getX()) {
						C.p.add(b);
						return C.p;
					}

					else if (grid[C.n.getY() + 1][C.n.getX()] != 1) {
						tempN = new N(C.n.getX(), C.n.getY() + 1); 
						tempL = new ArrayList();
						tempL.addAll(C.p);
						tempL.add(tempN); 
						e = new QueueElement(tempN, tempL, admissableFunction(
								tempN.getX(), b.getX(), tempN.getY(), b.getY()));
						MarsQueue.add(e);

					}

				}// case 6

	// ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

				// case 7: C is in the top of the grid
				else if (C.n.getY() == 0) {

					// Cell c1
					if (b.getY() == 0 && b.getX() == C.n.getX() - 1) {
						C.p.add(b);
						return C.p;
					}
					else if (grid[0][C.n.getX() - 1] != 1) {
						tempN = new N(C.n.getX() - 1, 0); 
						tempL = new ArrayList();
						tempL.addAll(C.p);
						tempL.add(tempN); 
						e = new QueueElement(tempN, tempL, admissableFunction(
								tempN.getX(), b.getX(), tempN.getY(), b.getY()));
						MarsQueue.add(e);

					}

					// Cell c2
					if (b.getY() == C.n.getY() + 1 && b.getX() == C.n.getX() - 1) {
						C.p.add(b);
						return C.p;
					}

					else if (grid[C.n.getY() + 1][C.n.getX() - 1] != 1) {
						tempN = new N(C.n.getX() - 1, C.n.getY() + 1); 
						tempL = new ArrayList();
						tempL.addAll(C.p);
						tempL.add(tempN); 
						e = new QueueElement(tempN, tempL, admissableFunction(
								tempN.getX(), b.getX(), tempN.getY(), b.getY()));
						MarsQueue.add(e);

					}

					// Cell c3
					if (b.getY() == C.n.getY() + 1 && b.getX() == C.n.getX()) {
						C.p.add(b);
						return C.p;
					}

					else if (grid[C.n.getY() + 1][C.n.getX()] != 1) {
						tempN = new N(C.n.getX(), C.n.getY() + 1); 
						tempL = new ArrayList();
						tempL.addAll(C.p);
						tempL.add(tempN); 
						e = new QueueElement(tempN, tempL, admissableFunction(
								tempN.getX(), b.getX(), tempN.getY(), b.getY()));
						MarsQueue.add(e);

					}

					// Cell c4
					if (b.getY() == C.n.getY() + 1 && b.getX() == C.n.getX() + 1) {
						C.p.add(b);
						return C.p;
					}

					else if (grid[C.n.getY() + 1][C.n.getX() + 1] != 1) {
						tempN = new N(C.n.getX() + 1, C.n.getY() + 1); 
						tempL = new ArrayList();
						tempL.addAll(C.p);
						tempL.add(tempN); 
						e = new QueueElement(tempN, tempL, admissableFunction(
								tempN.getX(), b.getX(), tempN.getY(), b.getY()));
						MarsQueue.add(e);

					}

					// Cell c5
					if (b.getY() == C.n.getY() && b.getX() == C.n.getX() + 1) {
						C.p.add(b);
						return C.p;
					}

					else if (grid[C.n.getY()][C.n.getX() + 1] != 1) {
						tempN = new N(C.n.getX() + 1, C.n.getY()); 
						tempL = new ArrayList();
						tempL.addAll(C.p);
						tempL.add(tempN); 
						e = new QueueElement(tempN, tempL, admissableFunction(
								tempN.getX(), b.getX(), tempN.getY(), b.getY()));
						// make new element
						MarsQueue.add(e);

					}

				}// case 7
		// ///////////////////////////////////////////////////////////////////////////////////////////////////////////////

				// case 8: C is in the right of the grid
				else if (C.n.getX() == grid[grid.length - 1].length - 1) {

					// Cell c1
					if (b.getY() == C.n.getY() - 1 && b.getX() == C.n.getX()) {
						C.p.add(b);
						return C.p;
					}
					else if (grid[C.n.getY() - 1][C.n.getX()] != 1) {
						tempN = new N(C.n.getX(), C.n.getY() - 1); 
						tempL = new ArrayList();
						tempL.addAll(C.p);
						tempL.add(tempN); 
						e = new QueueElement(tempN, tempL, admissableFunction(
								tempN.getX(), b.getX(), tempN.getY(), b.getY()));
						MarsQueue.add(e);

					}

					// Cell c2
					if (b.getY() == C.n.getY() - 1 && b.getX() == C.n.getX() - 1) {
						C.p.add(b);
						return C.p;
					}

					else if (grid[C.n.getY() - 1][C.n.getX() - 1] != 1) {
						tempN = new N(C.n.getX() - 1, C.n.getY() - 1); 
						tempL = new ArrayList();
						tempL.addAll(C.p);
						tempL.add(tempN); 
						e = new QueueElement(tempN, tempL, admissableFunction(
								tempN.getX(), b.getX(), tempN.getY(), b.getY()));
						MarsQueue.add(e);

					}

					// Cell c3
					if (b.getY() == C.n.getY() && b.getX() == C.n.getX() - 1) {
						C.p.add(b);
						return C.p;
					}

					else if (grid[C.n.getY()][C.n.getX() - 1] != 1) {
						tempN = new N(C.n.getX() - 1, C.n.getY()); 
						tempL = new ArrayList();
						tempL.addAll(C.p);
						tempL.add(tempN); 
						e = new QueueElement(tempN, tempL, admissableFunction(
								tempN.getX(), b.getX(), tempN.getY(), b.getY()));
						MarsQueue.add(e);

					}

					// Cell c4
					if (b.getY() == C.n.getY() + 1 && b.getX() == C.n.getX() - 1) {
						C.p.add(b);
						return C.p;
					}

					else if (grid[C.n.getY() + 1][C.n.getX() - 1] != 1) {
						tempN = new N(C.n.getX() - 1, C.n.getY() + 1);
						tempL = new ArrayList();
						tempL.addAll(C.p);
						tempL.add(tempN); 
						e = new QueueElement(tempN, tempL, admissableFunction(
								tempN.getX(), b.getX(), tempN.getY(), b.getY()));
						MarsQueue.add(e);

					}

					// Cell c5
					if (b.getY() == C.n.getY() + 1 && b.getX() == C.n.getX()) {
						C.p.add(b);
						return C.p;
					}

					else if (grid[C.n.getY() + 1][C.n.getX()] != 1) {
						tempN = new N(C.n.getX(), C.n.getY() + 1); 
						tempL = new ArrayList();
						tempL.addAll(C.p);
						tempL.add(tempN); 
						e = new QueueElement(tempN, tempL, admissableFunction(
								tempN.getX(), b.getX(), tempN.getY(), b.getY()));
						MarsQueue.add(e);

					}

				}// case 8

		// ////////////////////////////////////////////////////////////////////////////////////////////////////////////

				// case 9: C isn't at any border on the grid
				else if (C.n.getX() != grid[grid.length - 1].length - 1
						&& C.n.getY() != grid.length - 1 && C.n.getX() != 0
						&& C.n.getY() != 0) {

					// Cell c1
					// if Ci == B return path
					if (b.getY() == C.n.getY() - 1 && b.getX() == C.n.getX() - 1) {
						C.p.add(b);
						return C.p;
					}
					// else if Ci != blockade insert new element into queue
					else if (grid[C.n.getY() - 1][C.n.getX() - 1] != 1) {
						tempN = new N(C.n.getX() - 1, C.n.getY() - 1);
						tempL = new ArrayList();
						tempL.addAll(C.p);
						tempL.add(tempN); // make new list containing new path info
						e = new QueueElement(tempN, tempL, admissableFunction(
								tempN.getX(), b.getX(), tempN.getY(), b.getY()));
						MarsQueue.add(e);

					}

					// Cell c2
					if (b.getY() == C.n.getY() - 1 && b.getX() == C.n.getX()) {
						C.p.add(b);
						return C.p;
					}

					else if (grid[C.n.getY() - 1][C.n.getX()] != 1) {
						tempN = new N(C.n.getX(), C.n.getY() - 1); 
						tempL = new ArrayList();
						tempL.addAll(C.p);
						tempL.add(tempN); // make new list containing new path info
						e = new QueueElement(tempN, tempL, admissableFunction(
								tempN.getX(), b.getX(), tempN.getY(), b.getY()));
						MarsQueue.add(e);

					}

					// Cell c3
					if (b.getY() == C.n.getY() - 1 && b.getX() == C.n.getX() + 1) {
						C.p.add(b);
						return C.p;
					}

					else if (grid[C.n.getY() - 1][C.n.getX() + 1] != 1) {
						tempN = new N(C.n.getX() + 1, C.n.getY() - 1); 
						tempL = new ArrayList();
						tempL.addAll(C.p);
						tempL.add(tempN); // make new list containing new path info
						e = new QueueElement(tempN, tempL, admissableFunction(
								tempN.getX(), b.getX(), tempN.getY(), b.getY()));
						MarsQueue.add(e);

					}

					// Cell c4
					if (b.getY() == C.n.getY() && b.getX() == C.n.getX() + 1) {
						C.p.add(b);
						return C.p;
					}

					else if (grid[C.n.getY()][C.n.getX() + 1] != 1) {
						tempN = new N(C.n.getX() + 1, C.n.getY()); 
						tempL = new ArrayList();
						tempL.addAll(C.p);
						tempL.add(tempN); // make new list containing new path info
						e = new QueueElement(tempN, tempL, admissableFunction(
								tempN.getX(), b.getX(), tempN.getY(), b.getY()));
						MarsQueue.add(e);

					}

					// Cell c5
					if (b.getY() == C.n.getY() + 1 && b.getX() == C.n.getX() + 1) {
						C.p.add(b);
						return C.p;
					}

					else if (grid[C.n.getY() + 1][C.n.getX() + 1] != 1) {
						tempN = new N(C.n.getX() + 1, C.n.getY() + 1); 
						tempL = new ArrayList();
						tempL.addAll(C.p);
						tempL.add(tempN); // make new list containing new path info
						e = new QueueElement(tempN, tempL, admissableFunction(
								tempN.getX(), b.getX(), tempN.getY(), b.getY()));
						MarsQueue.add(e);

					}

					// Cell c6
					if (b.getY() == C.n.getY() + 1 && b.getX() == C.n.getX()) {
						C.p.add(b);
						return C.p;
					}

					else if (grid[C.n.getY() + 1][C.n.getX()] != 1) {
						tempN = new N(C.n.getX(), C.n.getY() + 1); // make new node
						tempL = new ArrayList();
						tempL.addAll(C.p);
						tempL.add(tempN); // make new list containing new path info
						e = new QueueElement(tempN, tempL, admissableFunction(
								tempN.getX(), b.getX(), tempN.getY(), b.getY()));
						// make new element
						MarsQueue.add(e);

					}

					// Cell c7
					if (b.getY() == C.n.getY() + 1 && b.getX() == C.n.getX() - 1) {
						C.p.add(b);
						return C.p;
					}

					else if (grid[C.n.getY() + 1][C.n.getX() - 1] != 1) {
						tempN = new N(C.n.getX() - 1, C.n.getY() + 1); 
						tempL = new ArrayList();
						tempL.addAll(C.p);
						tempL.add(tempN); // make new list containing new path info
						e = new QueueElement(tempN, tempL, admissableFunction(
								tempN.getX(), b.getX(), tempN.getY(), b.getY()));
						MarsQueue.add(e);

					}

					// Cell c8
					if (b.getY() == C.n.getY() && b.getX() == C.n.getX() - 1) {
						C.p.add(b);
						return C.p;
					}

					else if (grid[C.n.getY()][C.n.getX() - 1] != 1) {
						tempN = new N(C.n.getX() - 1, C.n.getY()); // make new node
						tempL = new ArrayList();
						tempL.addAll(C.p);
						tempL.add(tempN); // make new list containing new path info
						e = new QueueElement(tempN, tempL, admissableFunction(
								tempN.getX(), b.getX(), tempN.getY(), b.getY()));
						// make new element
						MarsQueue.add(e);

					}

				}// case 8

			}// while loop

			// if Queue is empty and a path has not been found, there is no path
			return null; 
		}// A* search algorithm woth admissable function

	public static Comparator<QueueElement> comparator = new Comparator<QueueElement>() {

		@Override
		public int compare(QueueElement q1, QueueElement q2) {

			return Double.compare(q1.getF(), q2.getF());

		}

	};// implements the compare method for priority queue

	public static double nonAdmissableFunction(double Xn, double Xb, double Yn,
			double Yb) {

		return Math.abs(Xn - Xb) + Math.abs(Yn - Yb);

	}// returns admissable function result

	public static double admissableFunction(double Xn, double Xb, double Yn,
			double Yb) {

		double a = Math.pow(Math.abs(Xn - Xb), 2);

		double b = Math.pow(Math.abs(Yn - Yb), 2);

		return Math.sqrt((a + b) / 2);

	}// returns nonAdmissable function result

}
