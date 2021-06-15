package mainPack;

public class Point {
	int x;
	int y;
	
	public Point(int a, int b) {
		x=a;
		y=b;
	}
	public Point aktual(int a, int b) {
		Point tmp=new Point(a, b);
		return tmp;
	}
}
