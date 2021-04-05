**************************************************************************** KLASA mainFrame*************************************************************************************

package mainPack;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class mainFrame extends JFrame implements ActionListener{

	JTextField odpow;
	int l_cial;
	int ktory_subFrame;
	ArrayList<subFrame> subFrameList;
	NumberFormatException exception;
	
	public mainFrame() {
		this.setSize(new Dimension(1000, 700));
		this.setLayout(new BorderLayout());
		this.setTitle("Symulacja ruchu ciał pod wpływem siły centralnej");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.subFrameList = new ArrayList<subFrame>();
		exception = new NumberFormatException();
		
		/* PRAWY PANEL */
		
		JPanel prawy = new JPanel();
		prawy.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3, true));
		prawy.setLayout(new GridLayout(5, 1));
		
		JPanel prawy1 = new JPanel();
		prawy1.setLayout(new BoxLayout(prawy1, BoxLayout.Y_AXIS));
		
		JPanel pytanie = new JPanel();
		pytanie.setPreferredSize(new Dimension(250, 20));
		JLabel pyt = new JLabel("Wpisz liczbę ciał:");
		pytanie.add(pyt);
		
		prawy1.add(pytanie);
		
		JPanel odpowiedz = new JPanel();
		odpow = new JTextField("3");
		odpow.setPreferredSize(new Dimension(230, 25));
		odpowiedz.add(odpow);
		prawy1.add(odpowiedz);
		
		JPanel guziki = new JPanel();
		guziki.setLayout(new FlowLayout());
		
		JButton dalej = new JButton("Dalej");
		dalej.setPreferredSize(new Dimension(80, 25));
		JButton koniec = new JButton("Zakończ");
		koniec.setPreferredSize(new Dimension(100, 25));
		dalej.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
				     l_cial = Integer.parseInt(odpow.getText());
				     if(l_cial==0) {
				    	 throw exception;
				     }
				     subFrame sf = new subFrame(0, l_cial);
				     sf.setVisible(true);
				     dalej.setEnabled(false);
				     koniec.setEnabled(false);
				     odpow.setEnabled(false);
				     
				}
				catch(NumberFormatException exception){
				     System.out.println("Wrong number format.");
				}
			}
		});
		
		JLabel przerwa = new JLabel("    ");
		
		guziki.add(dalej);
		guziki.add(przerwa);
		guziki.add(koniec);
		prawy1.add(guziki);
		
		JPanel prawy2 = new JPanel();
		JPanel prawy3 = new JPanel();
		
		prawy.add(prawy1);
		prawy.add(prawy2);
		prawy.add(prawy3);
		
		this.add(prawy, BorderLayout.LINE_END);
		
		/* MENU */
		
		JMenuBar menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("Menu");
		menuBar.add(menu);
		
		JMenuItem menuItem1 = new JMenuItem("Wczytaj");
		menuItem1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		menu.add(menuItem1);
		
		JMenuItem menuItem2 = new JMenuItem("Zapisz");
		menuItem2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			} 
		});
		menu.add(menuItem2);
		
		JMenuItem menuItem3 = new JMenuItem("Nowe");
		menuItem2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			} 
		});
		menu.add(menuItem3);
		
		/* ŚRODKOWA GRAFIKA */
		
		tlo tlo = new tlo();
		tlo.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3, true));
		this.add(tlo, BorderLayout.CENTER);
		
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				mainFrame mf = new mainFrame();
				mf.setVisible(true);
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}

**************************************************************************** KLASA subFrame*************************************************************************************

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
		liczebniki.add("piątego");
		liczebniki.add("szóstego");
		liczebniki.add("siudmego");
		liczebniki.add("ósmego");
		
		JLabel parametry = new JLabel("Podaj parametry "+liczebniki.get(j)+" ciała:");
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
		//stwórz ciało na podstawie tych danych (konstruktor ciała)
		//daj go do listy ciał
		//aktual() wyczyść okno i zaktualizuj
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

**************************************************************************** KLASA tlo*************************************************************************************

package mainPack;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class tlo extends JPanel {
	
	Image bgImage;
	BufferedImage bImage;
	
	public tlo() {
		File inputFile = new File("src/bg.jpg");
		try {
			bImage = ImageIO.read(inputFile);
		} catch(IOException ex) {
			System.out.println(ex.getMessage());
		}
		bgImage = (Image) bImage;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	        g.drawImage(bgImage, 0, 0, null);
	}
}

