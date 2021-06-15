package mainPack;

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
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

@SuppressWarnings("serial")
public class SimFrame extends JFrame implements Runnable {
	
	static final int SLIDER_MIN = 1;
	static final int SLIDER_MAX = 5;
	static final int SLIDER_INIT = 3;
	
	double secFrame = 0.0;
	int minFrame = 0;
	
	int stan_slider;

	JComboBox bodies;
	
	String [] bodyString;
	
	JLabel mass;
	JLabel charge;
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
	
	JFrame to;
	
	Thread t;
	Thread t2;
	Thread t3;
	
	GridBagConstraints glButtons = new GridBagConstraints();
	GridLayout glMain = new GridLayout(12,1);
	ArrayList<String> SatNames;
	ArrayList<Double> coefList;
	
	public SimFrame(ArrayList<Body> bodyList, ArrayList<Double> coefficientsList) throws HeadlessException {
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(new Dimension(1624, 1000));
		this.setTitle("Symulacja ruchu ciał‚ pod wpływem siły centralnej");
		stan_slider=2;
		to=new JFrame();
		to=this;
		
		coefList=coefficientsList;
		ArrayList<Body> initCond=new ArrayList<>(bodyList);
		SatNames = new ArrayList<String>();
		
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
				String save="";
				
				//pierwsza linijka
				save+=initCond.size();
				save+=" ";
				save+=coefList.size();
				save+='\n';
				
				//linijka druga
				save+="Nazwa	masa	ladunek	color	x	y	vx 	vy\n";
				
				//linijki z parametrami ciał
				for(int i=0; i<initCond.size(); i++) {
					save+=String.format("\"%s\"\t\"%s\"\t\"%s\"\t\"%d %d %d\"\t\"%d\"\t\"%d\"\t\"%s\"\t\"%s\"\n", initCond.get(i).getName(), initCond.get(i).mass, initCond.get(i).charge, initCond.get(i).getColor().getRed(), initCond.get(i).getColor().getGreen(), initCond.get(i).getColor().getBlue(), (int) initCond.get(i).x, (int) initCond.get(i).y, initCond.get(i).vx, initCond.get(i).vy);																		
				}
				//linijka ze współczynnikami
				for(int i=0;i<spacePanel.coefsList.size();i++) {
					save+=String.format("\"%s\" ", coefList.get(i));
				}
				System.out.println(save);
				
				JFileChooser fileChooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter(null, "txt");
				fileChooser.setFileFilter(filter);
				int returnVal = fileChooser.showOpenDialog(getParent());
				if(returnVal == JFileChooser.APPROVE_OPTION) {
				    	System.out.println("You chose to open this file: "+fileChooser.getSelectedFile().getName());
				}
				
				try {
					OutputStream outputStream = new FileOutputStream(fileChooser.getSelectedFile().getAbsolutePath());//fileChooser.getSelectedFile().getAbsolutePath()
					OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
					outputStreamWriter.write(save);
					outputStreamWriter.close();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
			} 
		});
		menu.add(save);
		
		neww = new JMenuItem("Nowe");
		neww.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				t.stop();
				t2.stop();
				t3.stop();
				to.dispose();
				to=new MainFrame();
				to.setVisible(true);
			} 
		});
		menu.add(neww);
		// 			KONIEC MENU		 	//
		
		bodyString = new String[bodyList.size()];
		
		for(int i = 0; i < bodyList.size(); i++) {
			
			bodyString[i] = bodyList.get(i).getName();
		}
		
		
		
		spacePanel = new SimulatePanel(bodyList, coefficientsList);
		t = new Thread(spacePanel);
		t.start();
		t.suspend();
		
		t2 = new Thread(this);
		t2.start();
		t2.suspend();
		
		t3 = new Thread(spacePanel.taskTimer);
		t3.start();
		t3.suspend();
		
		menuPanel = new JPanel();
		menuPanel.setBorder(BorderFactory.createLineBorder(Color.white, 3, true));
		
		bodies = new JComboBox(bodyString);
		
		
		for(int i = 0; i < bodyList.size(); i++) {
			
			if(bodies.getSelectedItem() == bodyList.get(i).getName()) {
			
				mass = new JLabel("Masa: " + bodyList.get(i).getMass());
				charge = new JLabel("Ładunek: " + String.format("%.0f", bodyList.get(i).getCharge()));
				xx = new JLabel("X: " + String.format("%.0f", bodyList.get(i).getX()));
				yy = new JLabel("Y: " + String.format("%.0f", bodyList.get(i).getY()));
				vxx = new JLabel("Vx: " + String.format("%.2f", bodyList.get(i).getVx()));
				vyy = new JLabel("Vy: " + String.format("%.2f", bodyList.get(i).getVy()));
				
			}
		
		}
		
		ActionListener changelistener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				for(int i = 0; i < bodyList.size(); i++) {
					
					if(bodies.getSelectedItem() == spacePanel.listBody.get(i).getName()) {
					
						mass.setText("Masa: " + spacePanel.listBody.get(i).getMass()); 
						charge.setText("Ładunek: " + spacePanel.listBody.get(i).getCharge());
						xx.setText("X: "+ String.format("%.0f", spacePanel.listBody.get(i).getX())); 
						yy.setText("Y: " + String.format("%.0f", spacePanel.listBody.get(i).getY()));
						vxx.setText("Vy: " + String.format("%.2f", spacePanel.listBody.get(i).getVx())); 
						vyy.setText("Vx: " + String.format("%.2f", spacePanel.listBody.get(i).getVy()));
						
					}
				}
			}
		};
		bodies.addActionListener(changelistener);
		
		
		menuPanel.add(bodies);
		
	
		menuPanel.add(mass);
		
		
		menuPanel.add(charge);
		
	
		menuPanel.add(xx);
		
		
		menuPanel.add(yy);
		
		
		menuPanel.add(vxx);
		
		
		menuPanel.add(vyy);

		
		checkBox = new JCheckBox("Pokaż drogę");
		checkBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(spacePanel.smuga_bool==true) {
					spacePanel.smuga_bool=false;
				}else {
					spacePanel.smuga_bool=true;
				}
				repaint();
			}
		});
		
		menuPanel.add(checkBox);
		
		sliderLabel = new JLabel("Prędkość symulacji:");
		sliderLabel.setSize(2, 50);
		menuPanel.add(sliderLabel);
		
		
		timeSlider = new JSlider(JSlider.HORIZONTAL, SLIDER_MIN, SLIDER_MAX, SLIDER_INIT);
		timeSlider.setMajorTickSpacing(1);
		timeSlider.setPaintTicks(true);
		timeSlider.setPaintLabels(true);
		timeSlider.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				stan_slider=timeSlider.getValue();
				spacePanel.aktual(stan_slider);
			}
		});
		
		
		menuPanel.add(timeSlider);
		
		addSatelite = new JButton("Dodaj satelitę");
		addSatelite.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				 
				 t.suspend();
				 start.setText("Start");
				 
				 //lista nazw satelit
				 for(int i=0; i<spacePanel.satList.size(); i++) {
					 SatNames.add(spacePanel.satList.get(i).getName());
				 }
				 SubFrame satframe = new SubFrame(0,1,SatNames);
				 satframe.pole_nazwy.setText("SAT"+ (spacePanel.satList.size()+1));
				 satframe.setVisible(true);
				 
				 satframe.addWindowListener(new WindowListener() {
					
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
						if(satframe.j == 1) {
							
						spacePanel.satList.add(
								new	Body(satframe.pole_nazwy.getText(), Double.valueOf(satframe.pole_wagi.getText()), 
										Double.valueOf(satframe.pole_ladunku.getText()), satframe.kol, Integer.valueOf(satframe.pole_wsp_X.getText()),
										Integer.valueOf(satframe.pole_wsp_Y.getText()) ,Double.valueOf(satframe.pole_pred_X.getText()), Double.valueOf(satframe.pole_pred_Y.getText()), satframe.j)
								);
							
							repaint();
						}	
					}
					
					@Override
					public void windowActivated(WindowEvent e) {}
				});
				
			}
		});
		
		//menuPanel.add(addSatelite);
		
		start = new JButton("Start");
		ActionListener startListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				int j = 0;
				
				if(start.getText() == "Start") {
					
					/*
					try {
						//spacePanel.setSeconds(secFrame);
						//spacePanel.setMinutes(minFrame);
						
						
						//spacePanel.timer.scheduleAtFixedRate(spacePanel.taskTimer, 10, 10);
					}
					catch(IllegalStateException e) {
						e.printStackTrace();
					}
					*/
					
					t.resume();
					t2.resume();
					t3.resume();
					start.setText("Stop");
					j++;
				}
				
				if(start.getText() == "Stop" && j == 0) {
					
					//secFrame = spacePanel.getSeconds();
					//minFrame = spacePanel.getMinutes();
					
					t.suspend();
					t2.suspend();
					t3.suspend();
					start.setText("Start");
					/*
					for(int i=0; i<spacePanel.smuga; i++) {
						System.out.println("pozycja "+i);
						System.out.println(spacePanel.pointBodyMatrix.get(1).get(i).x);
						System.out.println(spacePanel.pointBodyMatrix.get(1).get(i).y);
					}
					System.out.println(spacePanel.counter);
					*/
				}
				
			}
		};
		start.addActionListener(startListener);
		
		
		exit = new JButton("Zakończ");
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

	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(18/timeSlider.getValue());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			for(int i = 0; i < spacePanel.listBody.size(); i++) {
				
				if(bodies.getSelectedItem() == spacePanel.listBody.get(i).getName()) {
				
					//mass.setText("Masa: " + spacePanel.listBody.get(i).getMass()); 
					xx.setText("X: "+ String.format("%.0f", spacePanel.listBody.get(i).getX())); 
					yy.setText("Y: " + String.format("%.0f", spacePanel.listBody.get(i).getY()));
					vxx.setText("Vy: " + String.format("%.2f", spacePanel.listBody.get(i).getVx())); 
					vyy.setText("Vx: " + String.format("%.2f", spacePanel.listBody.get(i).getVy()));
					
					
				}
			}
			
			
		}
		
	}
}