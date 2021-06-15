package mainPack;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class MainFrame extends JFrame{

	JTextField odpow;
	JTextField odpow_wzor;
	int l_cial;
	int l_czlonow;
	NumberFormatException exception;
	SubFrame sf;
	JPanel prawy;
	JFrame to;
	JPanel prawy1;
	ArrayList<Body> bodies = new ArrayList<Body>();
	ArrayList<Double> coefs = new ArrayList<Double>();
	
	public MainFrame() {
		this.setSize(new Dimension(1724, 900));
		this.setLayout(new BorderLayout());
		this.setTitle("Symulacja ruchu cia³ pod wp³ywem si³y centralnej");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setMaximizedBounds(new Rectangle(new Dimension(1724, 900)));
		this.setMinimumSize(new Dimension(350, 900));
		this.setMaximumSize(new Dimension(3000, 950));
		to=this;
		this.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {}
			
			@Override
			public void windowIconified(WindowEvent e) {}
			
			@Override
			public void windowDeiconified(WindowEvent e) {}
			
			@Override
			public void windowDeactivated(WindowEvent e) {}
			
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
			
			@Override
			public void windowClosed(WindowEvent e) {}
			
			@Override
			public void windowActivated(WindowEvent e) {}
		});
		
		exception = new NumberFormatException();
		
		/* PRAWY PANEL */
		
		prawy = new JPanel();
		prawy.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3, true));
		prawy.setLayout(new BoxLayout(prawy, BoxLayout.Y_AXIS));
		
		prawy1 = new JPanel();
		prawy1.setPreferredSize(new Dimension(330, 130));
		prawy1.setLayout(new BoxLayout(prawy1, BoxLayout.Y_AXIS));
		
		JPanel pytanie = new JPanel();
		pytanie.setSize(new Dimension(250, 25));
		JLabel pyt = new JLabel("Wpisz liczbê cia³:");
		pytanie.add(pyt);
		
		prawy1.add(pytanie);
		
		JPanel odpowiedz = new JPanel();
		odpow = new JTextField("3");
		odpow.setPreferredSize(new Dimension(130, 20)); 
		odpowiedz.add(odpow);
		prawy1.add(odpowiedz);
		
		JPanel wzor = new JPanel();
		JTextArea wzor_sily = new JTextArea("Wzór na si³ê centraln¹ jest konstruowany w oparciu o podane przez u¿ytkownika wspó³czynniki w postaci szeregu wyrazów, gdzie ka¿dy wyraz sk³ada siê ze wspó³czynnika i zmiennej r podniesionej do pewnej potêgi.");
		wzor_sily.setSize(new Dimension(330, 70));
		wzor_sily.setEditable(false);
		wzor_sily.setLineWrap(true);
		wzor_sily.setWrapStyleWord(true);
		wzor_sily.setBackground(prawy1.getBackground());
		wzor_sily.setBorder(BorderFactory.createLineBorder(Color.darkGray, 2, false));
		wzor.add(wzor_sily);
		
		prawy1.add(wzor);
		
		JPanel wzor_sily2 = new JPanel();
		wzor_sily2.setSize(new Dimension(250, 25));
		JLabel wzor_sily3 = new JLabel("Wpisz liczbê cz³onów n:", JLabel.CENTER);
		wzor_sily2.add(wzor_sily3);
		
		prawy1.add(wzor_sily2);
		
		JPanel odpow_sily = new JPanel();
		odpow_wzor = new JTextField();
		odpow_wzor.setPreferredSize(new Dimension(130, 20)); 
		odpow_sily.add(odpow_wzor);
		prawy1.add(odpow_sily);
		
		JPanel guziki = new JPanel();
		guziki.setSize(new Dimension(330, 40));
		guziki.setLayout(new FlowLayout());
		
		JButton dalej = new JButton("Dalej");
		dalej.setPreferredSize(new Dimension(80, 25));
		
		JButton koniec = new JButton("Zakoñcz");
		koniec.setPreferredSize(new Dimension(100, 25));
		ActionListener exitListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				System.exit(0); 
			}
		};
		koniec.addActionListener(exitListener);

		dalej.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
				     l_cial = Integer.parseInt(odpow.getText());
				     if(l_cial<1 || l_cial>8) {
				    	 throw exception;
				     }
				     l_czlonow = Integer.parseInt(odpow_wzor.getText());
				     if(l_czlonow<1 || l_czlonow>4) {
				    	 throw exception;
				     }
				     sf = new SubFrame(0, l_cial);
				     sf.addWindowListener(new WindowListener() {
						
						@Override
						public void windowOpened(WindowEvent e) {
							dalej.setEnabled(false);
						    koniec.setEnabled(false);
						    odpow.setEnabled(false);
						    odpow_wzor.setEnabled(false);
						}
						
						@Override
						public void windowIconified(WindowEvent e) {}
						
						@Override
						public void windowDeiconified(WindowEvent e) {}
						
						@Override
						public void windowDeactivated(WindowEvent e) {}
						
						@Override
						public void windowClosing(WindowEvent e) {}
						
						@Override
						public void windowClosed(WindowEvent e) {
						    if(sf.j!=l_cial) {
						    	dalej.setEnabled(true);
							    koniec.setEnabled(true);
							    odpow.setEnabled(true);
							    odpow_wzor.setEnabled(true);
						    }else {
							    SubFrame2 sf2 = new SubFrame2(l_czlonow, sf.listBody);
							    sf2.setVisible(true);
								sf2.addWindowListener(new WindowListener() {
									
									@Override
									public void windowOpened(WindowEvent e) {}
									
									@Override
									public void windowIconified(WindowEvent e) {}
									
									@Override
									public void windowDeiconified(WindowEvent e) {}
									
									@Override
									public void windowDeactivated(WindowEvent e) {}
									
									@Override
									public void windowClosing(WindowEvent e) {}
									
									@Override
									public void windowClosed(WindowEvent e) {
										dalej.setEnabled(true);
									    koniec.setEnabled(true);
									    odpow.setEnabled(true);
									    odpow_wzor.setEnabled(true);
									    
									    if(sf2.zamkniecie==true) {
									    	to.dispose();
									    	to = new SimFrame(sf.listBody, sf2.coefsList);
									    	to.setVisible(true);
									    }
									}
									
									@Override
									public void windowActivated(WindowEvent e) {}
								});
						    }
						}
						
						@Override
						public void windowActivated(WindowEvent e) {}
					});
				     sf.setVisible(true);
				}
				catch(NumberFormatException exception){
				     System.out.println("Niepoprawna liczba cia³ lub cz³onów. Liczba cia³ musi byæ z przedzia³u (1-8), a liczba cz³onów z przedzia³u (1-4).");
				}
			}
		});
		
		JLabel przerwa = new JLabel("    ");
		
		guziki.add(dalej);
		guziki.add(przerwa);
		guziki.add(koniec);
		prawy1.add(guziki);
		
		prawy.add(prawy1);
		
		Tlo postac_wzor = new Tlo("src/gotowe2.png");
		postac_wzor.setPreferredSize(new Dimension(330, 340));
		prawy.add(postac_wzor);
		
		JPanel wyp = new JPanel();
		wyp.setPreferredSize(new Dimension(330, 210));
		prawy.add(wyp);
		
		this.add(prawy, BorderLayout.LINE_END);
		
		
		
		/* MENU */
		
		JMenuBar menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("Operacje na plikach");
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
		menuItem2.setEnabled(false);
		menu.add(menuItem2);
		
		JMenuItem menuItem3 = new JMenuItem("Nowe");
		menuItem3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			} 
		});
		menuItem3.setEnabled(false);
		menu.add(menuItem3);
		
		JMenu example = new JMenu("Przyk³adowe uk³ady");
		menuBar.add(example);
		
		JMenuItem exampleItem1 = new JMenuItem("Uk³ad podwójny");
		exampleItem1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				to.dispose();
				
				bodies.add(new Body("Ciê¿ki James", 500, 100, Color.CYAN, 850, 550, 0, 30, 1));
				bodies.add(new Body("Lekka Alice", 500, 100, Color.GREEN, 700, 550, 0, -30, 3));

				coefs.add((double) 15000);
				coefs.add((double) -2);
				
		    	to = new SimFrame(bodies, coefs);
		    	to.setVisible(true);
			}
		});
		example.add(exampleItem1);
		
		
		JMenuItem exampleItem2 = new JMenuItem("uk³ad potrójny");
		exampleItem2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				to.dispose();
				
				bodies.add(new Body("A", 500, 100, Color.CYAN, 350, 250, -30, 40, 1));
				bodies.add(new Body("B", 500, 100, Color.GREEN, 500, 250, -30, -20, 3));
				bodies.add(new Body("C", 500, 100, Color.WHITE, 425, 350, 80, 0, 4));
				

				coefs.add((double) 100);
				coefs.add((double) -1);
				
		    	to = new SimFrame(bodies, coefs);
		    	to.setVisible(true);
			} 
		});
		example.add(exampleItem2);
		
		JMenuItem exampleItem3 = new JMenuItem("uk³ad poczwórny");
		exampleItem3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				to.dispose();
				
				bodies.add(new Body("A", 500, 30, Color.CYAN, 350, 250, -30, 40, 1));
				bodies.add(new Body("B", 500, 30, Color.GREEN, 500, 250, -30, -20, 3));
				bodies.add(new Body("OX", 50000, 100, Color.WHITE, 425, 350, 10, 0, 4));
				bodies.add(new Body("OY", 100000, 100, Color.YELLOW, 425, 300, 0, 0, 4));
			
				coefs.add((double) 40);
				coefs.add((double) -0.5);
				
		    	to = new SimFrame(bodies, coefs);
		    	to.setVisible(true);
			} 
		});
		example.add(exampleItem3);
		
		JMenuItem exampleItem5 = new JMenuItem("Uk³ad planetarny");
		exampleItem5.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				to.dispose();
				
				bodies.add(new Body("A", 50, 50, Color.WHITE, 50, 50, -1, 1, 1));
				bodies.add(new Body("A", 50, 50, Color.WHITE, 50, 100, 0, 2, 3));
				bodies.add(new Body("A", 50, 50, Color.WHITE, 70, 130, 1, 1, 4));
				bodies.add(new Body("A", 50, 50, Color.WHITE, 90, 160, 1.5, 0.3, 1));
				bodies.add(new Body("A", 40, 40, Color.WHITE, 130, 130, 1, -1, 3));
				bodies.add(new Body("A", 40, 40, Color.WHITE, 160, 100, -1.5, 0.5, 4));
				bodies.add(new Body("A", 60, 60, Color.WHITE, 130, 70, -1, -1, 1));
				bodies.add(new Body("A", 50, 50, Color.WHITE, 100, 50, -1, 0, 3));
				bodies.add(new Body("OA", 1000, 1000, Color.YELLOW, 90, 90, 5, 5, 4));
				
				coefs.add((double) 0.1);
				coefs.add((double) -1);
				
		    	to = new SimFrame(bodies, coefs);
		    	to.setVisible(true);
			} 
		});
		example.add(exampleItem5);
		
		JMenuItem exampleItem6 = new JMenuItem("Zderzenie 2 uk³adów planetarnych");
		exampleItem6.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				to.dispose();
				
				bodies.add(new Body("A", 50, 50, Color.WHITE, 450, 250, -1, 1, 1));
				bodies.add(new Body("A", 50, 50, Color.WHITE, 450, 300, 0, 2, 3));
				bodies.add(new Body("A", 50, 50, Color.WHITE, 470, 330, 1, 1, 4));
				bodies.add(new Body("A", 50, 50, Color.WHITE, 490, 360, 1.5, 0.3, 1));
				bodies.add(new Body("A", 40, 40, Color.WHITE, 530, 230, 1, -1, 3));
				bodies.add(new Body("A", 40, 40, Color.WHITE, 560, 300, -1.5, 0.5, 4));
				bodies.add(new Body("A", 60, 60, Color.WHITE, 530, 270, -1, -1, 1));
				bodies.add(new Body("A", 50, 50, Color.WHITE, 500, 250, -1, 0, 3));
				bodies.add(new Body("OA", 1000, 1000, Color.YELLOW, 490, 290, 0, 5, 4));
				
				bodies.add(new Body("B", 10, 10, Color.RED, 750, 550, -1, 1, 1));
				bodies.add(new Body("B", 10, 10, Color.RED, 750, 600, 0, 2, 3));
				bodies.add(new Body("B", 10, 10, Color.RED, 770, 630, 1, 1, 4));
				bodies.add(new Body("B", 10, 10, Color.RED, 790, 600, 1.5, 0.3, 1));
				bodies.add(new Body("B", 10, 10, Color.RED, 830, 630, 1, -1, 3));
				bodies.add(new Body("B", 10, 10, Color.RED, 860, 600, -1.5, 0.5, 4));
				bodies.add(new Body("B", 20, 20, Color.RED, 830, 570, -1, -1, 1));
				bodies.add(new Body("B", 50, 50, Color.RED, 800, 550, -1, 0, 3));
				bodies.add(new Body("OB", 1000, 1000, Color.magenta, 790, 590, 0, -3, 4));
				
				
				coefs.add((double) 0.1);
				coefs.add((double) -1);
				
		    	to = new SimFrame(bodies, coefs);
		    	to.setVisible(true);
			} 
		});
		example.add(exampleItem6);
		
		/* ŒRODKOWA GRAFIKA */
		
		Tlo tlo = new Tlo("src/bg.jpg");
		tlo.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3, true));
		this.add(tlo, BorderLayout.CENTER);
		
	}
}
