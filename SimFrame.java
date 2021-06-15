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
import java.util.Objects;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
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
	
	static final int SLIDER_MIN = 1; // minimalna wartosc slider'a
	static final int SLIDER_MAX = 7; // maksymalna
	static final int SLIDER_INIT = 1; // poczatkowa ( przy wlaczeniu frame'a )
	
	
	int stan_slider; // do przechowywania wartosci slidera

	int counterSat=0;
	
	JComboBox bodies;
	
	String [] bodyString; // do JComboBox'a, przewchowywanie nazw cial
	
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
	
	JCheckBox checkBox; //smuga
	
	SimulatePanel spacePanel;
	JPanel menuPanel;
	JPanel panelButtons;
	
	JMenuBar menuBar;
	JMenu menu;
	JMenuItem load;
	JMenuItem save;
	JMenuItem neww;
	
	JFrame to; //znacznik na ten frame
	
	GridBagConstraints glButtons = new GridBagConstraints(); //layout do ustawienia guzikow
	GridLayout glMain = new GridLayout(12,1); // layout do ustawienia calego menu ( caly prawy panel )
	ArrayList<String> SatNames; // lista nazw satelit
	ArrayList<Double> coefList = new ArrayList<Double>(); // do kopii listy wspolczynnikow do zapisywania
	
	public SimFrame(ArrayList<Body> bodyList, ArrayList<Double> coefficientsList) throws HeadlessException {
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(new Dimension(1624, 1000));
		this.setTitle("Symulacja ruchu ciał‚ pod wpływem siły centralnej");
		this.setLocationRelativeTo ( null ); // ustawia okno na srodek ekranu
		
		stan_slider=2;
		to=new JFrame();
		to=this;
		
		
		coefList=coefficientsList;
        ArrayList<Body> initCond=new ArrayList<>(bodyList); // lista użyta do zapisywania
		
		SatNames = new ArrayList<String>();	// lista nazw satelit ( by nazwy się nie powtarzały )
		
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
                save+="Nazwa    masa    ladunek    color    x    y    vx     vy\n";
                
                //linijki z parametrami ciał
                for(int i=0; i<initCond.size(); i++) {
                    save+=String.format("\"%s\"\t\"%s\"\t\"%s\"\t\"%d %d %d\"\t\"%d\"\t\"%d\"\t\"%s\"\t\"%s\"\n", initCond.get(i).getName(), initCond.get(i).mass, initCond.get(i).charge, initCond.get(i).getColor().getRed(), initCond.get(i).getColor().getGreen(), initCond.get(i).getColor().getBlue(), (int) initCond.get(i).x, (int) initCond.get(i).y, initCond.get(i).vx, initCond.get(i).vy);                                                                        
                }
                //linijka ze współczynnikami
                for(int i=0;i<spacePanel.coefsList.size();i++) {
                    save+=String.format("\"%s\" ", coefList.get(i));
                }
                
                
                JFileChooser fileChooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter(null, "txt");
                fileChooser.setFileFilter(filter);
                int returnVal = fileChooser.showOpenDialog(getParent());
                if(returnVal == JFileChooser.APPROVE_OPTION) {
                        System.out.println("You chose to open this file: "+fileChooser.getSelectedFile().getName());
                }
                
                try {
                    OutputStream outputStream = new FileOutputStream(fileChooser.getSelectedFile().getAbsolutePath());
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
		
		
		// panel z paintcomponent i wątki:
		spacePanel = new SimulatePanel(bodyList, coefficientsList);
		Thread t = new Thread(spacePanel);
		t.start();
		t.suspend();
		
		Thread t2 = new Thread(this);
		t2.start();
		t2.suspend();
		
		Thread t3 = new Thread(spacePanel.taskTimer);
		t3.start();
		t3.suspend();
		
		// panel z guzikami i ze wszystkimi danymi
		menuPanel = new JPanel();
		menuPanel.setBorder(BorderFactory.createLineBorder(Color.white, 3, true));
		
		bodies = new JComboBox(bodyString);
		
		
		for(int i = 0; i < bodyList.size(); i++) {
			
			if(bodies.getSelectedItem() == bodyList.get(i).getName()) {
			
				// ustawiamy wszystkie dane w labelach ( gdy ten frame się włącza po raz piewrszy )
				mass = new JLabel("Masa: " + bodyList.get(i).getMass()+" [kg]");
				charge = new JLabel("Ładunek: " + String.format("%.0f", bodyList.get(i).getCharge())+" [--]");
				xx = new JLabel("X: " + String.format("%.0f", bodyList.get(i).getX())+" [m]");
				yy = new JLabel("Y: " + String.format("%.0f", bodyList.get(i).getY())+" [m]");
				vxx = new JLabel("Vx: " + String.format("%.2f", bodyList.get(i).getVx())+" [m/s]");
				vyy = new JLabel("Vy: " + String.format("%.2f", bodyList.get(i).getVy())+" [m/s]");
				
			}
		
		}
		
		ActionListener changelistener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				for(int i = 0; i < bodyList.size(); i++) {
					
					if( bodies.getSelectedItem() == spacePanel.listBody.get(i).getName()) {
					
						// ustawiamy wszystkie dane w labelach ( przy zmianie "itemu" w jcomboboxie )
						
						mass.setText("Masa: " + spacePanel.listBody.get(i).getMass()+" [kg]"); 
						charge.setText("Ładunek: " + spacePanel.listBody.get(i).getCharge()+" [--]");
						xx.setText("X: "+ String.format("%.0f", spacePanel.listBody.get(i).getX())+" [m]"); 
						yy.setText("Y: " + String.format("%.0f", spacePanel.listBody.get(i).getY())+" [m]");
						vxx.setText("Vy: " + String.format("%.2f", spacePanel.listBody.get(i).getVx())+" [m/s]"); 
						vyy.setText("Vx: " + String.format("%.2f", spacePanel.listBody.get(i).getVy())+" [m/s]");
						
					}
					
					
					
				}
				
				for(int i = 0; i < spacePanel.satList.size(); i++) {
					
					
					
					
					if( Objects.equals(bodies.getSelectedItem(), spacePanel.satList.get(i).getName()) ) {
						
						
						// to samo dla satelit
						
						mass.setText("Masa: " + spacePanel.satList.get(i).getMass()+" [kg]"); 
						charge.setText("Ładunek: " + spacePanel.satList.get(i).getCharge()+" [--]");
						xx.setText("X: "+ String.format("%.0f", spacePanel.satList.get(i).getX())+" [m]"); 
						yy.setText("Y: " + String.format("%.0f", spacePanel.satList.get(i).getY())+" [m]");
						vxx.setText("Vx: " + String.format("%.2f", spacePanel.satList.get(i).getVx())+" [m/s]"); 
						vyy.setText("Vy: " + String.format("%.2f", spacePanel.satList.get(i).getVy())+" [m/s]");
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

		// checkbox do "smugi" 
		checkBox = new JCheckBox("Pokaż drogę");
		checkBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(spacePanel.nebulaBoolean==true) {
					spacePanel.nebulaBoolean=false;
				}else {
					spacePanel.nebulaBoolean=true;
				}
				repaint();
			}
		});
		
		menuPanel.add(checkBox);
		
		sliderLabel = new JLabel("Prędkość symulacji (przyspieszenie x):");
		sliderLabel.setSize(2, 50);
		menuPanel.add(sliderLabel);
		
		
		timeSlider = new JSlider(JSlider.HORIZONTAL, SLIDER_MIN, SLIDER_MAX, SLIDER_INIT);
		timeSlider.setMajorTickSpacing(1); // majortick
		timeSlider.setPaintTicks(true);    // przedzialki
		timeSlider.setPaintLabels(true);
		timeSlider.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				stan_slider=timeSlider.getValue();
				spacePanel.actual(stan_slider);
			}
		});
		
		
		menuPanel.add(timeSlider);
		
		addSatelite = new JButton("Dodaj satelitę");
		addSatelite.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				 
				 t.suspend();
				 t3.suspend();
				 start.setText("Start");
				 
				 // "model" combobox'a: 
				 DefaultComboBoxModel model = (DefaultComboBoxModel) bodies.getModel();
				 
				 
				 
				 for(int i=0; i<spacePanel.satList.size(); i++) {
					 
					 SatNames.add(spacePanel.satList.get(i).getName());
					 
				 }
				 
				 SubFrame satframe = new SubFrame(0,1,SatNames);
				 satframe.nameField.setText("SAT"+ (spacePanel.satList.size()+1));
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
						if(counterSat==9) {
							addSatelite.setEnabled(false);
						}	
						counterSat++;
						
						// dodanie satelity za pomocą SubFrame'a
						spacePanel.satList.add(
								new	Body(satframe.nameField.getText(), Double.valueOf(satframe.massField.getText()), 
										Double.valueOf(satframe.chargeField.getText()), satframe.col, Integer.valueOf(satframe.xField.getText()),
										Integer.valueOf(satframe.yField.getText()) ,Double.valueOf(satframe.vxField.getText()), Double.valueOf(satframe.vyField.getText()), satframe.j)
								);
							
							model.addElement(satframe.nameField.getText()); // dodanie satelity to JComboBox'a
							
							repaint();
						}	
					}
					
					@Override
					public void windowActivated(WindowEvent e) {}
				});
			
				 
			}
		});
		
		start = new JButton("Start");
		ActionListener startListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				int j = 0; // licznik który to "opuszczania" drugiego warunku/pętli
				
				if(start.getText() == "Start") {
				
					// włączenie wszystkich wątków
					t.resume();
					t2.resume();
					t3.resume();
					start.setText("Stop");
					j++;
				}
				
				if(start.getText() == "Stop" && j == 0) {
					
					// wyłączenie wszystkich wątków
					
					t.suspend();
					t2.suspend();
					t3.suspend();
					start.setText("Start");
					
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
		
		
		
		//dodanie wszystkich guzików do panelu za pomocą layout "GridBagLayout" - glButtons
		
		panelButtons = new JPanel();
		panelButtons.setLayout(new GridBagLayout());
		
		glButtons.insets = new Insets(2,2,2,2); // odległości między obiektami ( guzikami ), z góry, z lewej, z dołu , z prawej
		
		
		// gridx to wspolrzedna x, a gridy to y, gridwidth to szerokosc
		glButtons.gridx = 0;
		glButtons.gridy = 0;
		glButtons.gridwidth = 2;
		glButtons.fill = GridBagConstraints.HORIZONTAL; // wypelnienie guzika "w szerokości"
		panelButtons.add(addSatelite, glButtons);
		
		glButtons.gridx = 0;
		glButtons.gridy = 1;
		glButtons.gridwidth = 1; // ustawienie szerokosci na 1
		panelButtons.add(start, glButtons);
		
		glButtons.gridx = 1;
		glButtons.gridy = 1;
		glButtons.gridwidth = 1;
		panelButtons.add(exit, glButtons);
		
		
		menuPanel.add(panelButtons);
		
		glMain.setHgap(1);
		glMain.setVgap(1);
		menuPanel.setLayout(glMain); // glMain to GridLayout(12,1) 
		
		this.add(spacePanel, BorderLayout.CENTER);
		this.add(menuPanel, BorderLayout.LINE_END);
		
	}

	// metoda run do zmieniania danych w labelach w trakcie trwania symulacji 
	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			for(int i = 0; i < spacePanel.listBody.size(); i++) {
				
				if(bodies.getSelectedItem() == spacePanel.listBody.get(i).getName()) {
					
					
					xx.setText("X: "+ String.format("%.0f", spacePanel.listBody.get(i).getX())+" [m]"); 
					yy.setText("Y: " + String.format("%.0f", spacePanel.listBody.get(i).getY())+" [m]");
					vxx.setText("Vy: " + String.format("%.2f", spacePanel.listBody.get(i).getVx())+" [m/s]"); 
					vyy.setText("Vx: " + String.format("%.2f", spacePanel.listBody.get(i).getVy())+" [m/s]");
					
				}
			}
			
			for(int i = 0; i < spacePanel.satList.size(); i++) {
				
				
				
				
				if(Objects.equals(bodies.getSelectedItem(), spacePanel.satList.get(i).getName())) {
					
					mass.setText("Masa: " + spacePanel.satList.get(i).getMass()+" [kg]"); 
					charge.setText("Ładunek: " + spacePanel.satList.get(i).getCharge()+" [--]");
					xx.setText("X: "+ String.format("%.0f", spacePanel.satList.get(i).getX())+" [m]"); 
					yy.setText("Y: " + String.format("%.0f", spacePanel.satList.get(i).getY())+" [m]");
					vxx.setText("Vx: " + String.format("%.2f", spacePanel.satList.get(i).getVx())+" [m/s]"); 
					vyy.setText("Vy: " + String.format("%.2f", spacePanel.satList.get(i).getVy())+" [m/s]");
				}
			}
			
			
		}
		
	}
}