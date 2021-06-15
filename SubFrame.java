package mainPack;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class SubFrame extends JFrame {

	public ArrayList<JPanel> panelList;
	public ArrayList<String> numerals;	// lista numerowania
	public ArrayList<String> SatNameList;
	public ArrayList<Body> listBody;	//lista cial
	public ArrayList<Color> colList; // lista kolorow
	int j;
	int cBody; // counter body - licznik cial
	Color col;
	Boolean checkName;
	
	JTextField nameField;
	JTextField massField;
	JTextField chargeField;
	JTextField xField;
	JTextField yField;
	JTextField vxField; // predkosci X
	JTextField vyField; // predkosci Y
	
	public SubFrame(int order, int cBody, ArrayList<String> SatNames) {
		this.setSize(new Dimension(300, 450));
		this.setLayout(new GridLayout(10 ,1));
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("Parametry");
		this.setLocationRelativeTo ( null );
		
		SatNameList = new ArrayList<String>(); //nazwy satelit
		panelList = new ArrayList<JPanel>();	//lista paneli	
		numerals = new ArrayList<String>();	//  lista numerowania ( do labela - estetyczne )
		listBody = new ArrayList<Body>(); // lista cial
		colList = new ArrayList<Color>(); // lista kolorow
		SatNameList = SatNames; // do zmiany miedzy obiektami, a satelitami, dla obiektow dajemy "null" w konstruktorze
		
		checkName=false;
		
		j=order;
		this.cBody=cBody;
		
		for(int i=0; i<9; i++) {
			panelList.add(new JPanel());
			panelList.get(i).setLayout(new GridLayout(1, 3));
		}
		
		numerals.add("pierwszego");
		numerals.add("drugiego");
		numerals.add("trzeciego");
		numerals.add("czwartego");
		numerals.add("piątego");
		numerals.add("szóstego");
		numerals.add("siudmego");
		numerals.add("ósmego");
		
		JLabel parameters;
		if(SatNameList==null) {
			parameters = new JLabel("Podaj parametry "+numerals.get(j)+" ciała:", JLabel.CENTER);
		}else {
			parameters = new JLabel("Podaj parametry satelity", JLabel.CENTER);
		}
		panelList.get(0).add(parameters);
		
		
		// Labele nazwy do frame'a:
		JLabel nazwa = new JLabel("Nazwa:", JLabel.CENTER);
		nameField = new JTextField("Obiekt "+String.format("%d", j+1));
		nameField.setFont(new Font("DialogInput", 2, 17));
		JLabel emptyName = new JLabel(" ");
		panelList.get(1).add(nazwa);
		panelList.get(1).add(nameField);
		panelList.get(1).add(emptyName);
		
		JPanel buttonPanel = new JPanel();
		JLabel emptyColor1 = new JLabel(" ");
		
		colList.add(Color.blue);
		colList.add(Color.cyan);
		colList.add(Color.DARK_GRAY);
		colList.add(Color.green);
		colList.add(Color.magenta);
		colList.add(Color.orange);
		colList.add(Color.RED);
		colList.add(Color.YELLOW);
		
		col=colList.get(j);
		
		// wybor koloru za pomoca JColorChooser
		JButton color = new JButton("Kolor");
		buttonPanel.add(color);
		color.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				col=JColorChooser.showDialog(color, "Choose color", Color.WHITE);
				if(col==null) {
					col=colList.get(j);
				}
			}
		});
		
		// kolejne labele
		JLabel emptyColor2 = new JLabel(" ");
		panelList.get(2).add(emptyColor1);	
		panelList.get(2).add(buttonPanel);
		panelList.get(2).add(emptyColor2);

		JLabel mass = new JLabel("Masa [kg]:", JLabel.CENTER); //masa
		massField = new JTextField();
		massField.setFont(new Font("DialogInput", 2, 17));
		JLabel emptyMass = new JLabel(" ");
		panelList.get(3).add(mass);
		panelList.get(3).add(massField);
		panelList.get(3).add(emptyMass);
		
		//charge
		
		JLabel charge = new JLabel("Ładunek [--]:", JLabel.CENTER); //masa
		chargeField = new JTextField();
		chargeField.setFont(new Font("DialogInput", 2, 17));
		JLabel emptyCharge = new JLabel(" ");
		panelList.get(4).add(charge);
		panelList.get(4).add(chargeField);
		panelList.get(4).add(emptyCharge);
		
		JLabel x = new JLabel("X [m]:", JLabel.CENTER); // wsp x
		xField = new JTextField();
		xField.setFont(new Font("DialogInput", 2, 17));
		JLabel emptyX = new JLabel(" ");
		panelList.get(5).add(x);
		panelList.get(5).add(xField);
		panelList.get(5).add(emptyX);
		
		JLabel y = new JLabel("Y [m]:", JLabel.CENTER); // wsp y
		yField = new JTextField();
		yField.setFont(new Font("DialogInput", 2, 17));
		JLabel emptyY = new JLabel(" ");
		panelList.get(6).add(y);
		panelList.get(6).add(yField);
		panelList.get(6).add(emptyY);
		
		JLabel vx = new JLabel("V_x [m/s]:", JLabel.CENTER); // predkosc x
		vxField = new JTextField();
		vxField.setFont(new Font("DialogInput", 2, 17));
		JLabel emptyVX = new JLabel(" ");
		panelList.get(7).add(vx);
		panelList.get(7).add(vxField);
		panelList.get(7).add(emptyVX);
		
		JLabel vy = new JLabel("V_y [m/s]:", JLabel.CENTER); // predkosc y
		vyField = new JTextField();
		vyField.setFont(new Font("DialogInput", 2, 17));
		JLabel emptyVY = new JLabel(" ");
		panelList.get(8).add(vy);
		panelList.get(8).add(vyField);
		panelList.get(8).add(emptyVY);
			
		JPanel buttons = new JPanel();  // panel guzikow
		buttons.setLayout(new FlowLayout());
		
		// przycisk dalej, dodaje wpisane cialo do listy oraz na koncu zamyka SubFrame'a i włącza SimFrame'a
		JButton next = new JButton("Dalej");
		ActionListener nextListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try{	
					if(SatNameList==null) { // jesli satelity sa "wylaczone"
					for(int i=0; i<j; i++) {
						
						// sprawdzanie czy nazwy sie powtarzaja:
						if(Objects.equals(nameField.getText(), listBody.get(i).name)) {
							checkName=true;
							break;
						}else {
							checkName=false;
						}
					}
					}else {
						for(int i=0; i<SatNameList.size(); i++) {
							if(Objects.equals(nameField.getText(), SatNameList.get(i))) { // sprawdzanie nazw dla satelit
								checkName=true;
								break;
							}else {
								checkName=false;
							}
						}
					}
					if(checkName==false) { // jesli nie powtarza sie nazwa to dane sa przekazane do listy body
						listBody.add(
								new	Body(nameField.getText(), Double.valueOf(massField.getText()), Double.valueOf(chargeField.getText()), col, Integer.valueOf(xField.getText()),
									Integer.valueOf(yField.getText()) ,Double.valueOf(vxField.getText()), Double.valueOf(vyField.getText()), j)
							);
						if(j < cBody - 1) { // j to counter ktory pilnuje liczbe zapisanych obektow
							j++;
							nameField.setText("Obiekt "+String.format("%d", j+1));
							massField.setText(null);
							chargeField.setText(null);
							xField.setText(null);
							yField.setText(null);
							vxField.setText(null);
							vyField.setText(null);
							parameters.setText("Podaj parametry "+numerals.get(j)+" ciała:");
							col=colList.get(j);
						}else {
							j++; // jesli wpiszemy juz wszystkie obiekty to SubFrame sie wylacza i zapisuje dane
							dispose();	// JESLI WYLACZYMY SUBFRAME ZA POMOCA "X" TO DANE SIE NIE ZAPISZA
						}
					}else {
						JOptionPane.showMessageDialog(null, "Ta nazwa obiektu została już użyta","Błąd!",JOptionPane.ERROR_MESSAGE);
					}	// okno dialogowe jesli nazwa sie powtorzy
				}
				
				catch(NumberFormatException exception){
				     JOptionPane.showMessageDialog(null, "Zły format parametrów!","Błąd!",JOptionPane.ERROR_MESSAGE);
				}	// okngo dialogowe jesli wpiszemy nie poprawnie parametry
			}
		};
		next.addActionListener(nextListener);
		buttons.add(next);
		
		for(int i=0; i<9;i++) {
			this.add(panelList.get(i));
		}
		this.add(buttons);
	}
}
