package mainPack;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
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
	
	public MainFrame() {
		this.setSize(new Dimension(1024, 600));
		this.setLayout(new BorderLayout());
		this.setTitle("Symulacja ruchu cia³ pod wp³ywem si³y centralnej");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
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
		prawy.setLayout(new GridLayout(2, 1));
		
		prawy1 = new JPanel();
		prawy1.setLayout(new BoxLayout(prawy1, BoxLayout.Y_AXIS));
		
		JPanel pytanie = new JPanel();
		pytanie.setPreferredSize(new Dimension(250, 20));
		JLabel pyt = new JLabel("Wpisz liczbê cia³:");
		pytanie.add(pyt);
		
		prawy1.add(pytanie);
		
		JPanel odpowiedz = new JPanel();
		odpow = new JTextField("3");
		odpow.setPreferredSize(new Dimension(230, 25));
		odpowiedz.add(odpow);
		prawy1.add(odpowiedz);
		
		JPanel wzor = new JPanel();
		JTextArea wzor_sily = new JTextArea("Wzór na si³ê centraln¹ jest konstruowany w oparciu o podane przez u¿ytkownika wspó³czynniki w postaci szeregu wyrazów, gdzie ka¿dy wyraz sk³ada siê ze wspó³czynnika i zmiennej r podniesionej do pewnej potêgi.");
		wzor_sily.setPreferredSize(new Dimension(230, 100));
		wzor_sily.setEditable(false);
		wzor_sily.setLineWrap(true);
		wzor_sily.setWrapStyleWord(true);
		wzor_sily.setBackground(prawy1.getBackground());
		wzor_sily.setBorder(BorderFactory.createLineBorder(Color.darkGray, 2, false));
		wzor.add(wzor_sily);
		
		prawy1.add(wzor);
		
		JPanel wzor_sily2 = new JPanel();
		JLabel wzor_sily3 = new JLabel("Wpisz liczbê cz³onów:", JLabel.CENTER);
		wzor_sily2.add(wzor_sily3);
		
		prawy1.add(wzor_sily2);
		
		JPanel odpow_sily = new JPanel();
		odpow_wzor = new JTextField();
		odpow_wzor.setPreferredSize(new Dimension(230, 25));
		odpow_sily.add(odpow_wzor);
		prawy1.add(odpow_sily);
		
		JPanel guziki = new JPanel();
		guziki.setLayout(new FlowLayout());
		
		JButton dalej = new JButton("Dalej");
		dalej.setPreferredSize(new Dimension(80, 25));
		JButton koniec = new JButton("Zakoñcz");
		koniec.setPreferredSize(new Dimension(100, 25));
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
									    	to = new SimFrame(sf.listBody);
									    	to.setVisible(true);
									    	calc = new Calculus(sf.listBody);
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
		
		
		/* ŒRODKOWA GRAFIKA */
		
		Tlo tlo = new Tlo();
		tlo.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3, true));
		this.add(tlo, BorderLayout.CENTER);
		
	}
}
