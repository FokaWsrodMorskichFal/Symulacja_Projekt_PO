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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;

@SuppressWarnings("serial")
public class MainFrame extends JFrame{

	JTextField answer;
	JTextField answer_equation;
	int numBodies;
	int numMembers;
	NumberFormatException exception;
	SubFrame sf;
	JPanel right;
	JFrame it;
	JPanel right1;
	ArrayList<Body> bodies = new ArrayList<Body>();
	ArrayList<Double> coefs = new ArrayList<Double>();
	
	ArrayList<Character> txt  = new ArrayList<Character>();
	ArrayList<Integer> tags = new ArrayList<Integer>();
	
	
	
	public MainFrame() {
		this.setSize(new Dimension(1724, 900));
		this.setLayout(new BorderLayout());
		this.setTitle("Symulacja ruchu cia³ pod wp³ywem si³y centralnej");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setMaximizedBounds(new Rectangle(new Dimension(1724, 900)));
		this.setMinimumSize(new Dimension(350, 900));
		this.setMaximumSize(new Dimension(3000, 950));
		it=this;
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
			public void windowClosing(WindowEvent e) {//zamkniêcie g³ównego okna zamyka ca³y program
				System.exit(0);
			}
			
			@Override
			public void windowClosed(WindowEvent e) {}
			
			@Override
			public void windowActivated(WindowEvent e) {}
		});
		
		exception = new NumberFormatException();
		
		/* PRAWY PANEL */
		
		right = new JPanel();
		right.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3, true));
		right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
		
		right1 = new JPanel();
		right1.setPreferredSize(new Dimension(330, 130));
		right1.setLayout(new BoxLayout(right1, BoxLayout.Y_AXIS));
		
		JPanel question = new JPanel();
		question.setSize(new Dimension(250, 25));
		JLabel que = new JLabel("Wpisz liczbê cia³:");
		question.add(que);
		
		right1.add(question);
		
		JPanel answearPanel = new JPanel();
		answer = new JTextField("3");
		answer.setPreferredSize(new Dimension(130, 20)); 
		answearPanel.add(answer);
		right1.add(answearPanel);
		
		JPanel equationPanel = new JPanel();
		JTextArea equationTextArea = new JTextArea("Wzór na si³ê centraln¹ jest konstruowany w oparciu o podane przez u¿ytkownika wspó³czynniki w postaci szeregu wyrazów, gdzie ka¿dy wyraz sk³ada siê ze wspó³czynnika i zmiennej r podniesionej do pewnej potêgi.");
		equationTextArea.setSize(new Dimension(330, 70));
		equationTextArea.setEditable(false);
		equationTextArea.setLineWrap(true);
		equationTextArea.setWrapStyleWord(true);
		equationTextArea.setBackground(right1.getBackground());
		equationTextArea.setBorder(BorderFactory.createLineBorder(Color.darkGray, 2, false));
		equationPanel.add(equationTextArea);
		
		right1.add(equationPanel);
		
		JPanel forceEquationPanel = new JPanel();
		forceEquationPanel.setSize(new Dimension(250, 25));
		JLabel forceEquationLabel = new JLabel("Wpisz liczbê cz³onów n:", JLabel.CENTER);
		forceEquationPanel.add(forceEquationLabel);
		
		right1.add(forceEquationPanel);
		
		JPanel answerForcePanel = new JPanel();
		answer_equation = new JTextField();
		answer_equation.setPreferredSize(new Dimension(130, 20)); 
		answerForcePanel.add(answer_equation);
		right1.add(answerForcePanel);
		
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setSize(new Dimension(330, 40));
		buttonsPanel.setLayout(new FlowLayout());
		
		JButton next = new JButton("Dalej");
		next.setPreferredSize(new Dimension(80, 25));
		
		JButton exit = new JButton("Zakoñcz");
		exit.setPreferredSize(new Dimension(100, 25));
		ActionListener exitListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				System.exit(0); 
			}
		};
		exit.addActionListener(exitListener);

		next.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
				     numBodies = Integer.parseInt(answer.getText());
				     if(numBodies<1 || numBodies>8) {
				    	 throw exception;
				     }
				     numMembers = Integer.parseInt(answer_equation.getText());
				     if(numMembers<1 || numMembers>4) {
				    	 throw exception;
				     }
				     sf = new SubFrame(0, numBodies,null);			//SubFrame czyli okno od dodawania cia³
				     sf.addWindowListener(new WindowListener() {	//ActionListener s³u¿y do blokowania guzików i pól
				    	 										//tekstowych w mainFramie gdy pojawiaj¹ siê SubFramy
						@Override
						public void windowOpened(WindowEvent e) {
							next.setEnabled(false);
						    exit.setEnabled(false);		
						    answer.setEnabled(false);
						    answer_equation.setEnabled(false);
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
						    if(sf.j!=numBodies) {
						    	next.setEnabled(true);		//sf.j!=l_cial to warunek sprawdzaj¹cy czy subFrame zamkn¹³ siê na skutek dodania wsyztskich zadeklarowanych przez u¿ytkownika cia³ czy na skutek wciœniêcia "X", zmienna j wewn¹trz subFrame'a liczy ile dodano cia³
							    exit.setEnabled(true);	//jeœli SubFrame zostanie wy³¹czony wracamy do mainFrame
							    answer.setEnabled(true);
							    answer_equation.setEnabled(true);
						    }else {
							    SubFrame2 sf2 = new SubFrame2(numMembers, sf.listBody);//subFrame2 s³u¿¹cy do zbierania od u¿ytkownika wspó³czynników do wzoru na si³ê
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
										next.setEnabled(true);
									    exit.setEnabled(true);		//jeœli okno to zostanie zamkniête to wracamy do aktywnego
									    answer.setEnabled(true);			//okna g³ównego
									    answer_equation.setEnabled(true);
									    
									    if(sf2.close==true) {		//zmienna Boolena "close" w subFrame2 monitoruje czy do zamknêcia dosz³o
									    	it.dispose();			//z powodu uzupe³nienia wsyztskich pól czy z powodu wciœniêcia "X"
									    	it = new SimFrame(sf.listBody, sf2.coefsList);	//zmienna "to" jest naszym oknem mainFrame, które przestajemy wyœwietlaæ a nastêpnie
									    	it.setVisible(true);		//zaczynamy ale zainicjalizowane nowym obiektem SimFrame z argumentami pozyskanymi z sybFrameów
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
		
		JLabel breakLabel = new JLabel("    ");
		
		buttonsPanel.add(next);
		buttonsPanel.add(breakLabel);
		buttonsPanel.add(exit);
		right1.add(buttonsPanel);
		
		right.add(right1);
		
		Backgroung equationForm = new Backgroung("src/gotowe2.png");		//klasa Background dziedziczaca po JPanel
		equationForm.setPreferredSize(new Dimension(330, 340));
		right.add(equationForm);
		
		JPanel fillingPanel = new JPanel();
		fillingPanel.setPreferredSize(new Dimension(330, 210));
		right.add(fillingPanel);
		
		this.add(right, BorderLayout.LINE_END);
		
		
		
		/* MENU */
		
		JMenuBar menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("Operacje na plikach");
		menuBar.add(menu);
		
		JMenuItem menuItem1 = new JMenuItem("Wczytaj");
		menuItem1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				bodies.clear();
				coefs.clear();
				
				//metoda wczytywania
                
                txt.removeAll(txt);
                JFileChooser fileChooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter(null, "txt");
                fileChooser.setFileFilter(filter);
                int returnVal = fileChooser.showOpenDialog(getParent());
                if(returnVal == JFileChooser.APPROVE_OPTION) {
                        System.out.println("Wybrano plik do otwarcia: "+fileChooser.getSelectedFile().getName());
                        if(fileChooser.getSelectedFile().canRead()) {
                            InputStream inputStream=null;
                            try {
                                inputStream = new FileInputStream(fileChooser.getSelectedFile().getPath());
                            } catch (FileNotFoundException e1) {
                                e1.printStackTrace();
                            }
                            //kodowanie UTF-8, polskie znaki
                            InputStreamReader inputStreamReader =new InputStreamReader(inputStream, Charset.forName("UTF-8"));
                            char tmp=' ';    //nieistotna inicjalizacja
                            int flag=0;        //flaga monitoruj¹ca czy tekst sie nie skonczy³
                            try {
                                flag = inputStreamReader.read();
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                            //wczytywanie znak po znaku
                            while(flag!=-1) {
                                try {
                                    tmp=(char) flag;
                                    flag=inputStreamReader.read();
                                    txt.add(tmp);		//w tablicy txt sk³adowany jest ca³y tekst
                                } catch (IOException e1) {
                                    e1.printStackTrace();
                                }
                            }
                            
                        }
                
                int bCounts = Integer.valueOf(String.valueOf(txt.get(0)));//pierwsze dwie dane, liczba cial i wspolczynnikow
                int cCounts = Integer.valueOf(String.valueOf(txt.get(2)));
          
                for( int i = 0; i < txt.size(); i ++)
                {
                	if(txt.get(i) == '"')
                		tags.add(i);
                }

               int counter = 0;
               for(int i = 0; i < bCounts; i++) {
            	   String name = "";
            	   double m = 0;
            	   double ch = 0;
            	   Color c;
            	   double x = 0.0;
            	   double y = 0.0;
            	   double vx = 0.0;
            	   double vy = 0.0;
                	
            	   for(int j = tags.get(0+counter) + 1; j < tags.get(1+counter) ; j++ ) {
            		   
            		   name += Character.toString(txt.get(j));
            	   
               		}
            	   
            	   String mS = "";
            	   
            	   for(int j = tags.get(2+counter) + 1; j < tags.get(3+counter) ; j++ ) {
            		   
            		   mS += Character.toString(txt.get(j));
            		   
            		   
            	   }
            	   m = Double.valueOf(mS);
            	   
            	   String chS ="";
            	   for( int j = tags.get(4+counter) + 1; j < tags.get(5+counter) ; j++ ) {
            		   chS += Character.toString(txt.get(j));  
            	   }
            	   ch = Double.valueOf(chS);
            	   
            	   // poczatek kolor
            	   int s1=0;
                   int s2=0;
                   int colSize=0;
                   String tmp2="";
                   for(int j=tags.get(6+counter)+1; j<tags.get(7+counter);j++) {
                       tmp2+=txt.get(j);
                       if(txt.get(j)==' ' && s1==0) {
                           s1=j;
                       }else if(txt.get(j)==' ' && s1!=0){
                           s2=j;
                       }
                   }
                   
                   colSize=tmp2.length();
                   int c1, c2, c3;

                   String tmpc="";
                   for(int k=tags.get(6+counter)+1; k<s1;k++) {
                       tmpc+=txt.get(k);
                   }
                   c1=Integer.parseInt(tmpc);
                   tmpc="";


                   for(int k=s1+1; k<s2;k++) {
                       tmpc+=txt.get(k);
                   }
                   c2=Integer.parseInt(tmpc);
                   tmpc="";
                   
                   for(int k=s2+1; k<colSize + tags.get(6+counter)+1;k++) {
                       tmpc+=txt.get(k);
                   }
                   c3=Integer.parseInt(tmpc);
                   tmpc="";
                   tmp2="";

                   c=new Color(c1, c2, c3);
            	   // koniec kolor
            	   
            	   
            	   String xS = "";
            	   for( int j = tags.get(8+counter) + 1; j < tags.get(9+counter); j++ ) {
            		   
            		   xS += Character.toString(txt.get(j)); 
            	   }
            	   x = Double.valueOf(xS);
            	   
            	   
            	   
            	   
            	   String yS = "";
            	   for( int j = tags.get(10+counter) + 1; j < tags.get(11+counter) ; j++ ) {
            		   
            		   yS += Character.toString(txt.get(j)); 
            	   }
            	   
            	   try {
            		   y = Double.valueOf(yS);
            	   }
            	   catch(NumberFormatException f) {
            		   f.printStackTrace();   
            	   }
            	   
            	   String vxS = "";
            	   for( int j = tags.get(12+counter) + 1; j < tags.get(13+counter) ; j++ ) {
            		   
            		   vxS += Character.toString(txt.get(j)); 
            	   }
            	   vx = Double.valueOf(vxS);
            	   
            	   String vyS = "";
            	   for( int j = tags.get(14+counter) + 1; j < tags.get(15+counter) ; j++ ) {
            		   
            		   vyS += Character.toString(txt.get(j)); 
            	   }
            	   vy = Double.valueOf(vyS); 
            	  
            	   bodies.add(new Body(name, m, ch, c ,x ,y, vx, vy, 0));
            	   
            	   
            	   counter += 16;
               }
               
               
               int counter2 = 0;
               
               for(int i = 0 ; i < cCounts; i++) {
            	   
            	   
            	   String a = ""; // wspolczynnik
            	   
            	   for(int j = tags.get(bCounts*16+counter2)+1; j < tags.get(bCounts*16+1+counter2); j++) {
            		   
            		   a += Character.toString(txt.get(j));
            		   
            	   }
            	   coefs.add(Double.valueOf(a));
            	   
            	   counter2 += 2;
               	}
              
               it.dispose();
               
               it = new SimFrame(bodies, coefs);
               
               it.setVisible(true);
               
                
			}
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
				it.dispose();
				
				bodies.add(new Body("Ciê¿ki James", 500, 100, Color.CYAN, 850, 550, 0, 30, 1));
				bodies.add(new Body("Lekka Alice", 500, 100, Color.GREEN, 700, 550, 0, -30, 3));
				
				coefs.add((double) 15000);
				coefs.add((double) -2);
				
				
		    	it = new SimFrame(bodies, coefs);
		    	it.setVisible(true);
			}
		});
		example.add(exampleItem1);
		
		
		JMenuItem exampleItem2 = new JMenuItem("uk³ad potrójny");
		exampleItem2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				it.dispose();
				
				bodies.add(new Body("A", 500, 100, Color.CYAN, 350, 250, -30, 40, 1));
				bodies.add(new Body("B", 500, 100, Color.GREEN, 500, 250, -30, -20, 3));
				bodies.add(new Body("C", 500, 100, Color.WHITE, 425, 350, 80, 0, 4));
				

				coefs.add((double) 100);
				coefs.add((double) -1);
				
		    	it = new SimFrame(bodies, coefs);
		    	it.setVisible(true);
			} 
		});
		example.add(exampleItem2);
		
		JMenuItem exampleItem3 = new JMenuItem("uk³ad poczwórny");
		exampleItem3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				it.dispose();
				
				bodies.add(new Body("A", 500, 30, Color.CYAN, 350, 250, -30, 40, 1));
				bodies.add(new Body("B", 500, 30, Color.GREEN, 500, 250, -30, -20, 3));
				bodies.add(new Body("OX", 50000, 100, Color.WHITE, 425, 350, 10, 0, 4));
				bodies.add(new Body("OY", 100000, 100, Color.YELLOW, 425, 300, 0, 0, 4));
			
				coefs.add((double) 40);
				coefs.add((double) -0.5);
				
		    	it = new SimFrame(bodies, coefs);
		    	it.setVisible(true);
			} 
		});
		example.add(exampleItem3);
		
		JMenuItem exampleItem5 = new JMenuItem("Uk³ad planetarny");
		exampleItem5.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				it.dispose();
				
				bodies.add(new Body("A1", 50, 50, Color.WHITE, 50, 50, -1, 1, 1));
				bodies.add(new Body("A2", 50, 50, Color.WHITE, 50, 100, 0, 2, 3));
				bodies.add(new Body("A3", 50, 50, Color.WHITE, 70, 130, 1, 1, 4));
				bodies.add(new Body("A4", 50, 50, Color.WHITE, 90, 160, 1.5, 0.3, 1));
				bodies.add(new Body("A5", 40, 40, Color.WHITE, 130, 130, 1, -1, 3));
				bodies.add(new Body("A6", 40, 40, Color.WHITE, 160, 100, -1.5, 0.5, 4));
				bodies.add(new Body("A7", 60, 60, Color.WHITE, 130, 70, -1, -1, 1));
				bodies.add(new Body("A8", 50, 50, Color.WHITE, 100, 50, -1, 0, 3));
				bodies.add(new Body("OA", 1000, 1000, Color.YELLOW, 90, 90, 5, 5, 4));
				
				coefs.add((double) 0.1);
				coefs.add((double) -1);
				
		    	it = new SimFrame(bodies, coefs);
		    	it.setVisible(true);
			} 
		});
		example.add(exampleItem5);
		
		JMenuItem exampleItem6 = new JMenuItem("Zderzenie 2 uk³adów planetarnych");
		exampleItem6.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				it.dispose();
				
				bodies.add(new Body("A1", 50, 50, Color.WHITE, 450, 250, -1, 1, 1));
				bodies.add(new Body("A2", 50, 50, Color.WHITE, 450, 300, 0, 2, 3));
				bodies.add(new Body("A3", 50, 50, Color.WHITE, 470, 330, 1, 1, 4));
				bodies.add(new Body("A4", 50, 50, Color.WHITE, 490, 360, 1.5, 0.3, 1));
				bodies.add(new Body("A5", 40, 40, Color.WHITE, 530, 230, 1, -1, 3));
				bodies.add(new Body("A6", 40, 40, Color.WHITE, 560, 300, -1.5, 0.5, 4));
				bodies.add(new Body("A7", 60, 60, Color.WHITE, 530, 270, -1, -1, 1));
				bodies.add(new Body("A8", 50, 50, Color.WHITE, 500, 250, -1, 0, 3));
				bodies.add(new Body("OA", 1000, 1000, Color.YELLOW, 490, 290, 0, 5, 4));
				
				bodies.add(new Body("B1", 10, 10, Color.RED, 750, 550, -1, 1, 1));
				bodies.add(new Body("B2", 10, 10, Color.RED, 750, 600, 0, 2, 3));
				bodies.add(new Body("B3", 10, 10, Color.RED, 770, 630, 1, 1, 4));
				bodies.add(new Body("B4", 10, 10, Color.RED, 790, 600, 1.5, 0.3, 1));
				bodies.add(new Body("B5", 10, 10, Color.RED, 830, 630, 1, -1, 3));
				bodies.add(new Body("B6", 10, 10, Color.RED, 860, 600, -1.5, 0.5, 4));
				bodies.add(new Body("B7", 20, 20, Color.RED, 830, 570, -1, -1, 1));
				bodies.add(new Body("B8", 50, 50, Color.RED, 800, 550, -1, 0, 3));
				bodies.add(new Body("OB", 1000, 1000, Color.magenta, 790, 590, 0, -3, 4));
				
				
				coefs.add((double) 0.1);
				coefs.add((double) -1);
				
		    	it = new SimFrame(bodies, coefs);
		    	it.setVisible(true);
			} 
		});
		example.add(exampleItem6);
		
		/* ŒRODKOWA GRAFIKA */
		
		Backgroung background = new Backgroung("src/bg.jpg");
		background.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3, true));
		this.add(background, BorderLayout.CENTER);
		
	}
}
