package mainPack;

import java.util.ArrayList;

public class Calculus extends Thread {
	
	public ArrayList<Point> pointList;
	public ArrayList<Body> bodyList;
	
	public Calculus(ArrayList<Body> constructor_list) {
		pointList = new ArrayList<Point>(); 	//historia pozycji cia³
		bodyList = new ArrayList<Body>();		//lista ze stanem cia³
	}
}
