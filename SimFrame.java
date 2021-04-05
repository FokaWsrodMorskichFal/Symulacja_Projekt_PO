package projekt.symulacja;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;

public class SimFrame extends JFrame {
	
	static final int SLIDER_MIN = 3; // ????
	static final int SLIDER_MAX = 33;
	static final int SLIDER_INIT = 3;

	JComboBox bodies;
	
	String [] bodyString;
	
	JLabel mass;
	JLabel xx;
	JLabel yy;
	JLabel vxx;
	JLabel vyy;
	
	JButton addSatelite;
	JButton start;
	JButton exit;
	
	JSlider timeSlider;
	
	JCheckBox checkBox;
	
	JPanel spacePanel;
	JPanel menuPanel;
	
	JMenuBar menuBar;
	JMenu menu;
	JMenuItem load;
	JMenuItem save;
	JMenuItem neww;
	
	
	
	public SimFrame(ArrayList<Body> bodyList) throws HeadlessException {
		
		this.setSize(new Dimension(1000, 700));
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("Symulacja ruchu cia³‚ pod wp³ywem si³y centralnej");
		
		
		System.out.println("Siema");
		
		// 		MENU 		//
		
		menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
		
		menu = new JMenu("Menu");
		menuBar.add(menu);
		
		load = new JMenuItem("Wczytaj");
		load.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		menu.add(load);
		
		save = new JMenuItem("Zapisz");
		save.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			} 
		});
		menu.add(save);
		
		neww = new JMenuItem("Nowe");
		neww.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			} 
		});
		menu.add(neww);
		
		
		bodyString = new String[bodyList.size()];
		
		for(int i = 0; i < bodyList.size(); i++) {
			
			bodyString[i] = bodyList.get(i).getName();
		}
		
		
		spacePanel = new JPanel();
		spacePanel.setBackground(Color.white);
		
		menuPanel = new JPanel();
		
		bodies = new JComboBox(bodyString);
		bodies.setEditable(true);
		
		for(int i = 0; i < bodyList.size(); i++) {
			
			if(bodies.getSelectedItem() == bodyList.get(i).getName()) {
			
				mass = new JLabel("Waga: " + bodyList.get(i).getMass());
				xx = new JLabel("X: "+ bodyList.get(i).getX());
				yy = new JLabel("Y: " + bodyList.get(i).getY());
				vxx = new JLabel("Vy: " + bodyList.get(i).getVx());
				vyy = new JLabel("Vx: " + bodyList.get(i).getVy());
				
			}
		
		}
		
		ActionListener changelistener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				for(int i = 0; i < bodyList.size(); i++) {
					
					if(bodies.getSelectedItem() == bodyList.get(i).getName()) {
					
						mass.setText("Waga: " + bodyList.get(i).getMass()); 
						xx.setText("X: "+ bodyList.get(i).getX()); 
						yy.setText("Y: " + bodyList.get(i).getY());
						vxx.setText("Vy: " + bodyList.get(i).getVx()); 
						vyy.setText("Vx: " + bodyList.get(i).getVy());
						
					}
				}
			}
		};
		bodies.addActionListener(changelistener);
		menuPanel.add(bodies);
		
		menuPanel.add(mass);
		menuPanel.add(xx);
		menuPanel.add(yy);
		menuPanel.add(vxx);
		menuPanel.add(vyy);
		
		checkBox = new JCheckBox("Poka¿ drogê");
		menuPanel.add(checkBox);
		
		timeSlider = new JSlider(JSlider.HORIZONTAL, SLIDER_MIN, SLIDER_MAX, SLIDER_INIT);
		menuPanel.add(timeSlider);
		
		addSatelite = new JButton("Dodaj satelitê");
		menuPanel.add(addSatelite);
		
		start = new JButton("Start");
		menuPanel.add(start);
		
		exit = new JButton("Zakoñcz");
		menuPanel.add(exit);
		
		menuPanel.setLayout(new GridLayout(11,1));
		
		
		this.add(spacePanel, BorderLayout.CENTER);
		this.add(menuPanel, BorderLayout.LINE_END);
		
	}
}
