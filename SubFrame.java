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
	public ArrayList<String> liczebniki;
	public ArrayList<String> nameList;
	public ArrayList<Body> listBody;
	public ArrayList<Color> colList;
	int j;
	int l_cial;
	Color kol;
	Boolean CheckNazwy;
	
	JTextField pole_nazwy;
	JTextField pole_wagi;
	JTextField pole_ladunku;
	JTextField pole_wsp_X;
	JTextField pole_wsp_Y;
	JTextField pole_pred_X;
	JTextField pole_pred_Y;
	
	public SubFrame(int kolej, int l_cial, ArrayList<String> names) {
		this.setSize(new Dimension(300, 450));
		this.setLayout(new GridLayout(10 ,1));
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("Parametry");
		
		nameList = new ArrayList<String>();
		panelList = new ArrayList<JPanel>();
		liczebniki = new ArrayList<String>();
		listBody = new ArrayList<Body>();
		colList = new ArrayList<Color>();
		
		CheckNazwy=false;
		
		j=kolej;
		this.l_cial=l_cial;
		
		for(int i=0; i<9; i++) {
			panelList.add(new JPanel());
			panelList.get(i).setLayout(new GridLayout(1, 3));
		}
		
		liczebniki.add("pierwszego");
		liczebniki.add("drugiego");
		liczebniki.add("trzeciego");
		liczebniki.add("czwartego");
		liczebniki.add("piątego");
		liczebniki.add("szóstego");
		liczebniki.add("siudmego");
		liczebniki.add("ósmego");
		
		JLabel parametry = new JLabel("Podaj parametry "+liczebniki.get(j)+" ciała:", JLabel.CENTER);
		panelList.get(0).add(parametry);
		
		JLabel nazwa = new JLabel("Nazwa:", JLabel.CENTER);
		pole_nazwy = new JTextField("Obiekt "+String.format("%d", j+1));
		pole_nazwy.setFont(new Font("DialogInput", 2, 17));
		JLabel puste_nazwa = new JLabel(" ");
		panelList.get(1).add(nazwa);
		panelList.get(1).add(pole_nazwy);
		panelList.get(1).add(puste_nazwa);
		
		JPanel buttonPanel = new JPanel();
		JLabel puste_kolor1 = new JLabel(" ");
		
		colList.add(Color.blue);
		colList.add(Color.cyan);
		colList.add(Color.DARK_GRAY);
		colList.add(Color.green);
		colList.add(Color.magenta);
		colList.add(Color.orange);
		colList.add(Color.RED);
		colList.add(Color.YELLOW);
		
		kol=colList.get(j);
		
		JButton kolor = new JButton("Kolor");
		buttonPanel.add(kolor);
		kolor.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				kol=JColorChooser.showDialog(kolor, "Choose color", Color.WHITE);
				if(kol==null) {
					kol=colList.get(j);
				}
			}
		});
		JLabel puste_kolor2 = new JLabel(" ");
		panelList.get(2).add(puste_kolor1);
		panelList.get(2).add(buttonPanel);
		panelList.get(2).add(puste_kolor2);

		JLabel waga = new JLabel("Masa:", JLabel.CENTER); //masa
		pole_wagi = new JTextField();
		pole_wagi.setFont(new Font("DialogInput", 2, 17));
		JLabel puste_waga = new JLabel(" ");
		panelList.get(3).add(waga);
		panelList.get(3).add(pole_wagi);
		panelList.get(3).add(puste_waga);
		
		//charge
		
		JLabel ladunek = new JLabel("Ładunek:", JLabel.CENTER); //masa
		pole_ladunku = new JTextField();
		pole_ladunku.setFont(new Font("DialogInput", 2, 17));
		JLabel puste_ladunek = new JLabel(" ");
		panelList.get(4).add(ladunek);
		panelList.get(4).add(pole_ladunku);
		panelList.get(4).add(puste_ladunek);
		
		JLabel wsp_X = new JLabel("X:", JLabel.CENTER);
		pole_wsp_X = new JTextField();
		pole_wsp_X.setFont(new Font("DialogInput", 2, 17));
		JLabel puste_wsp_X = new JLabel(" ");
		panelList.get(5).add(wsp_X);
		panelList.get(5).add(pole_wsp_X);
		panelList.get(5).add(puste_wsp_X);
		
		JLabel wsp_Y = new JLabel("Y:", JLabel.CENTER);
		pole_wsp_Y = new JTextField();
		pole_wsp_Y.setFont(new Font("DialogInput", 2, 17));
		JLabel puste_wsp_Y = new JLabel(" ");
		panelList.get(6).add(wsp_Y);
		panelList.get(6).add(pole_wsp_Y);
		panelList.get(6).add(puste_wsp_Y);
		
		JLabel pred_X = new JLabel("V_x:", JLabel.CENTER);
		pole_pred_X = new JTextField();
		pole_pred_X.setFont(new Font("DialogInput", 2, 17));
		JLabel puste_pred_X = new JLabel(" ");
		panelList.get(7).add(pred_X);
		panelList.get(7).add(pole_pred_X);
		panelList.get(7).add(puste_pred_X);
		
		JLabel pred_Y = new JLabel("V_y:", JLabel.CENTER);
		pole_pred_Y = new JTextField();
		pole_pred_Y.setFont(new Font("DialogInput", 2, 17));
		JLabel puste_pred_Y = new JLabel(" ");
		panelList.get(8).add(pred_Y);
		panelList.get(8).add(pole_pred_Y);
		panelList.get(8).add(puste_pred_Y);
			
		JPanel guziki = new JPanel();
		guziki.setLayout(new FlowLayout());
		JButton dalej = new JButton("Dalej");
		ActionListener nextListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try{	
					for(int i=0; i<j; i++) {
						System.out.println(listBody.get(i).name);
						System.out.println(pole_nazwy.getText());
						System.out.println(Objects.equals(pole_nazwy.getText(), listBody.get(i).name));
						if(Objects.equals(pole_nazwy.getText(), listBody.get(i).name)) {
							CheckNazwy=true;
							break;
						}else {
							CheckNazwy=false;
						}
					}
					if(CheckNazwy==false) {
						listBody.add(
								new	Body(pole_nazwy.getText(), Double.valueOf(pole_wagi.getText()), Double.valueOf(pole_ladunku.getText()), kol, Integer.valueOf(pole_wsp_X.getText()),
									Integer.valueOf(pole_wsp_Y.getText()) ,Double.valueOf(pole_pred_X.getText()), Double.valueOf(pole_pred_Y.getText()), j)
							);
						if(j < l_cial - 1) {
							j++;
							pole_nazwy.setText("Obiekt "+String.format("%d", j+1));
							pole_wagi.setText(null);
							pole_ladunku.setText(null);
							pole_wsp_X.setText(null);
							pole_wsp_Y.setText(null);
							pole_pred_X.setText(null);
							pole_pred_Y.setText(null);
							parametry.setText("Podaj parametry "+liczebniki.get(j)+" ciała:");
							kol=colList.get(j);
						}else {
							j++;
							dispose();
						}
					}else {
						JOptionPane.showMessageDialog(null, "Ta nazwa obiektu została już użyta","Błąd!",JOptionPane.ERROR_MESSAGE);
					}
				}
				
				catch(NumberFormatException exception){
				     JOptionPane.showMessageDialog(null, "Zły format parametrów!","Błąd!",JOptionPane.ERROR_MESSAGE);
				}
			}
		};
		dalej.addActionListener(nextListener);
		guziki.add(dalej);
		
		for(int i=0; i<9;i++) {
			this.add(panelList.get(i));
		}
		this.add(guziki);
	}
}
