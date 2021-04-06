package projekt.symulacja;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
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


public class SimFrame extends JFrame {
	
	static final int SLIDER_MIN = 1; // ????
	static final int SLIDER_MAX = 4;
	static final int SLIDER_INIT = 1;

	JComboBox bodies;
	
	String [] bodyString;
	
	JLabel mass;
	JLabel xx;
	JLabel yy;
	JLabel vxx;
	JLabel vyy;
	JLabel sliderLabel;
	
	JButton addSatelite;
	JButton start;
	JButton exit;
	
	JSlider timeSlider;
	
	JCheckBox checkBox;
	
	SimulatePanel spacePanel;
	JPanel menuPanel;
	JPanel panelButtons;
	
	JMenuBar menuBar;
	JMenu menu;
	JMenuItem load;
	JMenuItem save;
	JMenuItem neww;
	
	GridBagConstraints glButtons = new GridBagConstraints();
	GridLayout glMain = new GridLayout(12,1);
	
	public SimFrame(ArrayList<Body> bodyList) throws HeadlessException {
		
		this.setSize(new Dimension(1024, 768));
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("Symulacja ruchu cia�� pod wp�ywem si�y centralnej");

		
		// 		MENU 		//
		
		menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
		
		menu = new JMenu("Menu");
		menuBar.add(menu);
		
		load = new JMenuItem("Wczytaj");
		load.setEnabled(false);
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
		// 			KONIEC MENU		 	//
		
		bodyString = new String[bodyList.size()];
		
		for(int i = 0; i < bodyList.size(); i++) {
			
			bodyString[i] = bodyList.get(i).getName();
		}
		
		
		spacePanel = new SimulatePanel(bodyList);
		
		
		menuPanel = new JPanel();
		menuPanel.setBorder(BorderFactory.createLineBorder(Color.white, 3, true));
		
		bodies = new JComboBox(bodyString);
		//bodies.setEditable(true);
		
	
		
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
		
		
		checkBox = new JCheckBox("Poka� drog�");
		
		menuPanel.add(checkBox);
		
		sliderLabel = new JLabel("Pr�dko�� symulacji:");
		sliderLabel.setSize(2, 50);
		menuPanel.add(sliderLabel);
		
		
		timeSlider = new JSlider(JSlider.HORIZONTAL, SLIDER_MIN, SLIDER_MAX, SLIDER_INIT);
		timeSlider.setMajorTickSpacing(1);
		timeSlider.setPaintTicks(true);
		timeSlider.setPaintLabels(true);
		
		
		menuPanel.add(timeSlider);
		
		addSatelite = new JButton("Dodaj satelit�");
		
		//menuPanel.add(addSatelite);
		
		start = new JButton("Start");
		ActionListener startListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				 
				int j = 0;
				
				if(start.getText() == "Start") {
					
					start.setText("Stop");
					j++;
				}
				
				if(start.getText() == "Stop" && j == 0) {
					
					start.setText("Start");
				}
				
			}
		};
		start.addActionListener(startListener);
		
		
		exit = new JButton("Zako�cz");
		ActionListener exitListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				System.exit(0); 
			}
		};
		exit.addActionListener(exitListener);
		
		panelButtons = new JPanel();
		panelButtons.setLayout(new GridBagLayout());
		
		glButtons.insets = new Insets(2,2,2,2);
		
		glButtons.gridx = 0;
		glButtons.gridy = 0;
		glButtons.gridwidth = 2;
		glButtons.fill = GridBagConstraints.HORIZONTAL;
		panelButtons.add(addSatelite, glButtons);
		
		glButtons.gridx = 0;
		glButtons.gridy = 1;
		glButtons.gridwidth = 1;
		panelButtons.add(start, glButtons);
		
		glButtons.gridx = 1;
		glButtons.gridy = 1;
		glButtons.gridwidth = 1;
		panelButtons.add(exit, glButtons);
		
		
	
		
		menuPanel.add(panelButtons);
		
		glMain.setHgap(1);
		glMain.setVgap(1);
		menuPanel.setLayout(glMain);
		
		
		
		this.add(spacePanel, BorderLayout.CENTER);
		this.add(menuPanel, BorderLayout.LINE_END);
		
	}
}