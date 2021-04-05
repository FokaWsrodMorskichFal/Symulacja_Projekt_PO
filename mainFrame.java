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
		this.setTitle("Symulacja ruchu cia³ pod wp³ywem si³y centralnej");
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
		JLabel pyt = new JLabel("Wpisz liczbê cia³:");
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
		JButton koniec = new JButton("Zakoñcz");
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
		
		/* ŒRODKOWA GRAFIKA */
		
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
