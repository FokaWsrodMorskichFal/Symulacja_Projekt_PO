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
	Calculus calc;
	ArrayList<Body> bodies = new ArrayList<Body>();
	ArrayList<Double> coefs = new ArrayList<Double>();
	
	public MainFrame() {
		this.setSize(new Dimension(1724, 900));
		this.setLayout(new BorderLayout());
		this.setTitle("Symulacja ruchu cia� pod wp�ywem si�y centralnej");
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
		//prawy.setLayout(new GridLayout(3, 1));
		prawy.setLayout(new BoxLayout(prawy, BoxLayout.Y_AXIS));
		
		prawy1 = new JPanel();
		prawy1.setPreferredSize(new Dimension(330, 130));
		prawy1.setLayout(new BoxLayout(prawy1, BoxLayout.Y_AXIS));
		
		JPanel pytanie = new JPanel();
		pytanie.setSize(new Dimension(250, 25));///////////
		JLabel pyt = new JLabel("Wpisz liczb� cia�:");
		pytanie.add(pyt);
		
		prawy1.add(pytanie);
		
		JPanel odpowiedz = new JPanel();
		odpow = new JTextField("3");
		odpow.setPreferredSize(new Dimension(130, 20)); ///////////
		odpowiedz.add(odpow);
		//odpowiedz.setPreferredSize(new Dimension(230, 10));
		prawy1.add(odpowiedz);
		
		JPanel wzor = new JPanel();
		JTextArea wzor_sily = new JTextArea("Wz�r na si�� centraln� jest konstruowany w oparciu o podane przez u�ytkownika wsp�czynniki w postaci szeregu wyraz�w, gdzie ka�dy wyraz sk�ada si� ze wsp�czynnika i zmiennej r podniesionej do pewnej pot�gi.");
		wzor_sily.setSize(new Dimension(330, 70));///////////
		wzor_sily.setEditable(false);
		wzor_sily.setLineWrap(true);
		wzor_sily.setWrapStyleWord(true);
		wzor_sily.setBackground(prawy1.getBackground());
		wzor_sily.setBorder(BorderFactory.createLineBorder(Color.darkGray, 2, false));
		wzor.add(wzor_sily);
		
		prawy1.add(wzor);
		
		JPanel wzor_sily2 = new JPanel();
		wzor_sily2.setSize(new Dimension(250, 25));///////////
		JLabel wzor_sily3 = new JLabel("Wpisz liczb� cz�on�w n:", JLabel.CENTER);
		//wzor_sily3.setSize(new Dimension(330, 10));
		wzor_sily2.add(wzor_sily3);
		
		prawy1.add(wzor_sily2);
		
		JPanel odpow_sily = new JPanel();
		odpow_wzor = new JTextField();
		odpow_wzor.setPreferredSize(new Dimension(130, 20)); ///////////
		odpow_sily.add(odpow_wzor);
		prawy1.add(odpow_sily);
		
		JPanel guziki = new JPanel();
		guziki.setSize(new Dimension(330, 40));
		guziki.setLayout(new FlowLayout());
		
		JButton dalej = new JButton("Dalej");
		dalej.setPreferredSize(new Dimension(80, 25));
		
		JButton koniec = new JButton("Zako�cz");
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
				     System.out.println("Niepoprawna liczba cia� lub cz�on�w. Liczba cia� musi by� z przedzia�u (1-8), a liczba cz�on�w z przedzia�u (1-4).");
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
		
		JMenu example = new JMenu("Przyk�adowe uk�ady");
		menuBar.add(example);
		
		JMenuItem exampleItem1 = new JMenuItem("Uk�ad podw�jny");
		exampleItem1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				to.dispose();
				
				bodies.add(new Body("100", 500, 100, Color.CYAN, 650, 550, 0, 30, 1));
				bodies.add(new Body("50", 500, 100, Color.GREEN, 800, 550, 0, -30, 3));
				//bodies.add(new Body("50", 500, 100, Color.WHITE, 500, 450, 5, -10, 4));
				//bodies.add(new Body("50", 50, 50, Color.WHITE, 260, 260, -5, -5, 3));

				coefs.add((double) 100);
				coefs.add((double) -1);
				
		    	to = new SimFrame(bodies, coefs);
		    	to.setVisible(true);
			}
		});
		example.add(exampleItem1);
		
		
		JMenuItem exampleItem2 = new JMenuItem("uk�ad potr�jny");
		exampleItem2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				to.dispose();
				
				bodies.add(new Body("100", 500, 100, Color.CYAN, 350, 250, -30, 40, 1));
				bodies.add(new Body("50", 500, 100, Color.GREEN, 500, 250, -30, -20, 3));
				bodies.add(new Body("50", 500, 100, Color.WHITE, 425, 350, 80, 0, 4));
				//bodies.add(new Body("50", 1000000000, 100, Color.YELLOW, 425, 300, 0, 0, 4));
				//bodies.add(new Body("50", 50, 50, Color.WHITE, 260, 260, -5, -5, 3));

				coefs.add((double) 40);
				coefs.add((double) -0.5);
				
		    	to = new SimFrame(bodies, coefs);
		    	to.setVisible(true);
			} 
		});
		example.add(exampleItem2);
		
		JMenuItem exampleItem3 = new JMenuItem("uk�ad poczw�rny");
		exampleItem3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				to.dispose();
				
				bodies.add(new Body("100", 500, 100, Color.CYAN, 350, 250, -30, 40, 1));
				bodies.add(new Body("50", 500, 100, Color.GREEN, 500, 250, -30, -20, 3));
				bodies.add(new Body("50", 500, 100, Color.WHITE, 425, 350, 80, 0, 4));
				bodies.add(new Body("50", 1000000000, 100, Color.YELLOW, 425, 300, 0, 0, 4));
			
				coefs.add((double) 40);
				coefs.add((double) -0.5);
				
		    	to = new SimFrame(bodies, coefs);
		    	to.setVisible(true);
			} 
		});
		example.add(exampleItem3);
		
		
		/* �RODKOWA GRAFIKA */
		
		Tlo tlo = new Tlo("src/bg.jpg");
		tlo.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3, true));
		this.add(tlo, BorderLayout.CENTER);
		
	}
}
