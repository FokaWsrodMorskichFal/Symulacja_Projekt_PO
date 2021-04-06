package mainPack;

import java.util.ArrayList;

public class Calculus extends Thread {
	
	public ArrayList<Point> pointList;
	public ArrayList<Body> bodyList;
	
	public Calculus(ArrayList<Body> constructor_list) {
		pointList = new ArrayList<Point>();
		bodyList = new ArrayList<Body>();
		
		bodyList=constructor_list;
	}
	
	public static void main(String[] args) {
		System.out.println("ELO");
	}
}
