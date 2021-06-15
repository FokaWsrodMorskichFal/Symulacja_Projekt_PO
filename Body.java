package mainPack;

import java.awt.Color;
import java.util.ArrayList;

public class Body {
	
	String name;
	double mass;
	double charge;
	Color color;
	double x;
	double y;
	double vx;
	double vy;
	double dt=0.02; //sekundach
	int j;
	
	
	public Body(String n, double m, double charge, Color c, double xx, double yy, double vxx, double vyy, int j) {
		
		this.j=j;
		this.charge=charge;
		if(n == null) {
			name = "Obiekt" + Integer.toString(j+1);
			
		}else{	
			name = n;
		}
		mass = m;
		
		if(c == null) {
			color = new Color((j+1)*20, (j+1)*20, (j+1)*20);
		}else {
			color =  c;
		}
		
		x = xx;
		y = yy;
		vx = vxx;
		vy = vyy;
		
	}

	
	//metoda liczaca nastepna pozycje obiektu
	public Body next(ArrayList<Body> listBody, ArrayList<Double> coefsList, int to) {

		int dl=listBody.size();
		int czlony=coefsList.size();
		
		
		double x=0;
		double y=0;
		double vx=0;
		double vy=0;
		double F=0;
		double Fx=0;
		double Fy=0;
		for(int i=0; i<dl; i++) {//liczymy sume oddzia³ywan obiektu ze wszystkimi innymi
			if(i!=to) {	//ale pomijamy sam obiekt zeby nie policzyc jego oddzia³ywana z samym sob¹
				//odleglosc
				Double r=Math.pow(Math.pow(listBody.get(i).getX()-listBody.get(to).getX(), 2)+Math.pow(listBody.get(i).getY()-listBody.get(to).getY(), 2), 0.5);
				//suma po wszytskich czlonach, skok iteracji co dwa, poniewa¿ kazdy czlon ma pare wspolczynnikow
				for(int j=0;j<czlony;j=j+2) {
					//sumujemy sile ze wszytskich czlonow
					F=F+listBody.get(i).charge*listBody.get(to).charge*coefsList.get(j)*Math.pow(r, coefsList.get(j+1));
				}
				//liczymy skladowe liczac odpowiednie funkcje trygonometryczne
				Fy=Fy+F*((listBody.get(i).getY()-listBody.get(to).getY())/r);
				Fx=Fx+F*((listBody.get(i).getX()-listBody.get(to).getX())/r);
				//zerujemy sile by uzyæ tej zmiennej do liczenia sily wypadkowej na inne cialo
				F=0;
			}
		}
		
		
		
		double ax=Fx/listBody.get(to).mass;		//przyspiesznia
		double ay=Fy/listBody.get(to).mass;
		
		x=listBody.get(to).x+listBody.get(to).vx*dt+0.5*ax*dt*dt;		//dt to nasz krok czasowy
		
		y=listBody.get(to).y+listBody.get(to).vy*dt+0.5*ay*dt*dt;		//liczymy nastepne polo¿enia x_0+v*t+0.5*at^2
		
		vx=listBody.get(to).vx+ax*dt;			//liczymy nowe predkoœci
		
		vy=listBody.get(to).vy+ay*dt;
	
		
		
		
		//tworzymy nowy obiekt Body, ktory pola zwiazane z jego wlasciwosciami ma takei same jak poprzednik, ale ma nowe polozenie i predkosci, ostatni 
		//argument konstruktora to informacja o tym ktore to cialo, obiekt Body wie jako który zosta³ dodany, co jest przydatne przy 
		//liczeniu sily, by miec je ponumerowane i ³atwo rozró¿nialne
		Body tmp=new Body(listBody.get(to).name, listBody.get(to).mass, listBody.get(to).charge, listBody.get(to).color, x, y, vx, vy, listBody.get(to).j);
		
		return tmp;
	}
	
	//metoda identyczna ale dla satelity, który zgodnie z za³o¿eniem nie oddzia³uje z innymi satelitami
	public Body nextSat(ArrayList<Body> listBody, ArrayList<Double> coefsList, ArrayList<Body> satlist, int to) {

		int dl=listBody.size();
		int czlony=coefsList.size()/2;
		
		
		double x=0;
		double y=0;
		double vx=0;
		double vy=0;
		double F=0;
		double Fx=0;
		double Fy=0;
		for(int i=0; i<dl; i++) {
		
			Double r=Math.pow(Math.pow(listBody.get(i).getX()-satlist.get(to).getX(), 2)+Math.pow(listBody.get(i).getY()-satlist.get(to).getY(), 2), 0.5);
			for(int j=0;j<czlony;j++) {
				F=+listBody.get(i).charge*satlist.get(to).charge*coefsList.get(j)*Math.pow(r, coefsList.get(j+1));
					
			}
			Fy=Fy+F*((listBody.get(i).getY()-satlist.get(to).getY())/r);
			Fx=Fx+F*((listBody.get(i).getX()-satlist.get(to).getX())/r);
				
			F=0;
			
		}
		
		
		
		double ax=Fx/satlist.get(to).mass;
		double ay=Fy/satlist.get(to).mass;
		
		x=satlist.get(to).x+satlist.get(to).vx*dt+0.5*ax*dt*dt;
		
		y=satlist.get(to).y+satlist.get(to).vy*dt+0.5*ay*dt*dt;
		
		vx=satlist.get(to).vx+ax*dt;
		
		vy=satlist.get(to).vy+ay*dt;
	
		
		
		
		
		Body tmp=new Body(satlist.get(to).name, satlist.get(to).mass, satlist.get(to).charge, satlist.get(to).color, x, y, vx, vy, satlist.get(to).j);
		
		return tmp;
	}
	
	

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public double getMass() {
		return mass;
	}


	public void setMass(double mass) {
		this.mass = mass;
	}


	public Color getColor() {
		return color;
	}


	public void setColor(Color color) {
		this.color = color;
	}


	public double getX() {
		return x;
	}


	public void setX(int x) {
		this.x = x;
	}


	public double getY() {
		return y;
	}


	public void setY(int y) {
		this.y = y;
	}


	public double getVx() {
		return vx;
	}


	public void setVx(double vx) {
		this.vx = vx;
	}


	public double getVy() {
		return vy;
	}


	public void setVy(double vy) {
		this.vy = vy;
	}



	public double getCharge() {
		return charge;
	}



	public void setCharge(double charge) {
		this.charge = charge;
	}
};