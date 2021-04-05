package mainPack;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class subFrame extends JFrame {

	public ArrayList<JPanel> panelList;
	public ArrayList<String> liczebniki;
	int j;
	int l_cial;
	Color kol;
	
	public subFrame(int kolej, int l_cial) {
		this.setSize(new Dimension(300, 600));
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		
		panelList = new ArrayList<JPanel>();
		liczebniki = new ArrayList<String>();
		
		j=kolej;
		this.l_cial=l_cial;
		
		for(int i=0; i<8; i++) {
			panelList.add(new JPanel());
			panelList.get(i).setLayout(new GridLayout(1, 3));
		}
		
		liczebniki.add("pierwszego");
		liczebniki.add("drugiego");
		liczebniki.add("trzeciego");
		liczebniki.add("czwartego");
		liczebniki.add("pi¹tego");
		liczebniki.add("szóstego");
		liczebniki.add("siudmego");
		liczebniki.add("ósmego");
		
		JLabel parametry = new JLabel("Podaj parametry "+liczebniki.get(j)+" cia³a:");
		panelList.get(0).add(parametry);
		
		JLabel nazwa = new JLabel("Nazwa:");
		JTextField pole_nazwy = new JTextField();
		JLabel puste_nazwa = new JLabel(" ");
		panelList.get(1).add(nazwa);
		panelList.get(1).add(pole_nazwy);
		panelList.get(1).add(puste_nazwa);
		
		JLabel puste_kolor1 = new JLabel(" ");
		JButton kolor = new JButton("Kolor");
		kolor.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				kol=JColorChooser.showDialog(kolor, "Choose color", Color.WHITE);
				if(kol==null) {
					kol=Color.BLACK;
				}
			}
		});
		JLabel puste_kolor2 = new JLabel(" ");
		panelList.get(2).add(puste_kolor1);
		panelList.get(2).add(kolor);
		panelList.get(2).add(puste_kolor2);

		JLabel waga = new JLabel("Waga:");
		JTextField pole_wagi = new JTextField();
		JLabel puste_waga = new JLabel(" ");
		panelList.get(3).add(waga);
		panelList.get(3).add(pole_wagi);
		panelList.get(3).add(puste_waga);
		
		JLabel wsp_X = new JLabel("X:");
		JTextField pole_wsp_X = new JTextField();
		JLabel puste_wsp_X = new JLabel(" ");
		panelList.get(4).add(wsp_X);
		panelList.get(4).add(pole_wsp_X);
		panelList.get(4).add(puste_wsp_X);
		
		JLabel wsp_Y = new JLabel("Y:");
		JTextField pole_wsp_Y = new JTextField();
		JLabel puste_wsp_Y = new JLabel(" ");
		panelList.get(5).add(wsp_Y);
		panelList.get(5).add(pole_wsp_Y);
		panelList.get(5).add(puste_wsp_Y);
		
		JLabel pred_X = new JLabel("V_x:");
		JTextField pole_pred_X = new JTextField();
		JLabel puste_pred_X = new JLabel(" ");
		panelList.get(6).add(pred_X);
		panelList.get(6).add(pole_pred_X);
		panelList.get(6).add(puste_pred_X);
		
		JLabel pred_Y = new JLabel("V_y:");
		JTextField pole_pred_Y = new JTextField();
		JLabel puste_pred_Y = new JLabel(" ");
		panelList.get(7).add(pred_Y);
		panelList.get(7).add(pole_pred_Y);
		panelList.get(7).add(puste_pred_Y);
		
		JPanel guziki = new JPanel();
		guziki.setLayout(new FlowLayout());
		JButton dalej = new JButton("Dalej");
		//zgarnij dane
		//sprawdz ich poprawnosc
		//stwórz cia³o na podstawie tych danych (konstruktor cia³a)
		//daj go do listy cia³
		//aktual() wyczyœæ okno i zaktualizuj
		JLabel przerwa = new JLabel(" ");
		JButton anuluj = new JButton("Anuluj");
		guziki.add(dalej);
		guziki.add(przerwa);
		guziki.add(anuluj);
		
		for(int i=0; i<8;i++) {
			this.add(panelList.get(i));
		}
		this.add(guziki);
	}
	
	/* TO NIE JEST POTRZEBNE CHYBA
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				subFrame sf = new subFrame(0);
				sf.setVisible(true);
			}
		});
	}
	*/
}
























