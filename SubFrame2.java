package mainPack;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class SubFrame2 extends JFrame {
	
	boolean close;
	public ArrayList<JPanel> panelList;
	public ArrayList<JPanel> mainpanelList;
	public ArrayList<JTextField> textFieldList;
	public ArrayList<JLabel> labelCoeffList;
	public ArrayList<JLabel> labelExpList;
	public ArrayList<Double> coefsList;
	
	public SubFrame2(int numMembers, ArrayList<Body> constructor_list) {
		this.setSize(new Dimension(390, (numMembers+2)*35+20));
		this.setLayout(new GridLayout(numMembers+2, 1));
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("Wyra¿enie na si³ê");
		setLocationRelativeTo ( null );
		
		close=false;
		panelList = new ArrayList<JPanel>();
		mainpanelList = new ArrayList<JPanel>();
		textFieldList = new ArrayList<JTextField>();
		labelCoeffList = new ArrayList<JLabel>();
		labelExpList = new ArrayList<JLabel>();
		coefsList = new ArrayList<Double>();
		
		JPanel ansPanel = new JPanel();
		JLabel ansLabel = new JLabel("Podaj wspó³czynniki:", JLabel.LEFT);
		ansPanel.add(ansLabel);
		this.add(ansPanel);
		
		for(int i=0;i<numMembers;i++) {
			mainpanelList.add(new JPanel());
			mainpanelList.get(i).setLayout(new GridLayout(1, 4));
		}
		
		for(int i=0; i<numMembers;i++) {
			labelCoeffList.add(new JLabel("Wspó³czynnik "+String.format("%d", i+1)+":", JLabel.CENTER));
		}
		
		for(int i=0; i<numMembers;i++) {
			labelExpList.add(new JLabel("Wyk³adnik "+String.format("%d", i+1)+":", JLabel.CENTER));
		}
		
		for(int i=0;i<numMembers*2;i++) {
			textFieldList.add(new JTextField());
			textFieldList.get(i).setPreferredSize(new Dimension(65, 25));
		}
		
		for(int i=0; i<numMembers*4; i++) {
			panelList.add(new JPanel());
			int k=i/4;
			
			if(i%4==0) {
				panelList.get(i).add(labelCoeffList.get(k));
			}else if(i%4==2) {
				panelList.get(i).add(labelExpList.get(k));
			}else {
				panelList.get(i).add(textFieldList.get(i/2));
			}
			
			mainpanelList.get(k).add(panelList.get(i));
		}
		
		for(int i=0; i<numMembers;i++) {
			this.add(mainpanelList.get(i));
		}
		
		JPanel buttonPanel = new JPanel();
		JButton next = new JButton("Dalej");
		next.setPreferredSize(new Dimension(90, 20));
		next.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try{	
					for(int i=0; i<2*numMembers; i++) {
						coefsList.add(Double.valueOf(textFieldList.get(i).getText()));
					}
					dispose();
					close=true;
					
				}
				catch(NumberFormatException exception){
				     JOptionPane.showMessageDialog(null, "Z³y format parametrów!","B³¹d!",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		buttonPanel.add(next);
		this.add(buttonPanel);
	}
	
}













