package mainPack;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class SubFrame2 extends JFrame {
	
	boolean zamkniecie;
	public ArrayList<JPanel> panelList;
	public ArrayList<JPanel> mainpanelList;
	public ArrayList<JTextField> textFieldList;
	public ArrayList<JLabel> labelWspList;
	public ArrayList<JLabel> labelWykList;
	
	public SubFrame2(int l_czlonow, ArrayList<Body> constructor_list) {
		this.setSize(new Dimension(390, (l_czlonow+2)*35));
		this.setLayout(new GridLayout(l_czlonow+2, 1));
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("Wyra¿enie na si³ê");
		
		zamkniecie=false;
		panelList = new ArrayList<JPanel>();
		mainpanelList = new ArrayList<JPanel>();
		textFieldList = new ArrayList<JTextField>();
		labelWspList = new ArrayList<JLabel>();
		labelWykList = new ArrayList<JLabel>();
		
		JPanel podaj = new JPanel();
		JLabel podawanie = new JLabel("Podaj wspó³czynniki:", JLabel.LEFT);
		podaj.add(podawanie);
		this.add(podaj);
		
		for(int i=0;i<l_czlonow;i++) {
			mainpanelList.add(new JPanel());
			mainpanelList.get(i).setLayout(new GridLayout(1, 4));
		}
		
		for(int i=0; i<l_czlonow;i++) {
			labelWspList.add(new JLabel("Wspó³czynnik "+String.format("%d", i+1)+":", JLabel.CENTER));
		}
		
		for(int i=0; i<l_czlonow;i++) {
			labelWykList.add(new JLabel("Wyk³adnik "+String.format("%d", i+1)+":", JLabel.CENTER));
		}
		
		for(int i=0;i<l_czlonow*2;i++) {
			textFieldList.add(new JTextField());
			textFieldList.get(i).setPreferredSize(new Dimension(65, 25));
		}
		
		for(int i=0; i<l_czlonow*4; i++) {
			panelList.add(new JPanel());
			int k=i/4;
			
			if(i%4==0) {
				panelList.get(i).add(labelWspList.get(k));
			}else if(i%4==2) {
				panelList.get(i).add(labelWykList.get(k));
			}else {
				panelList.get(i).add(textFieldList.get(i/2));
			}
			
			mainpanelList.get(k).add(panelList.get(i));
		}
		
		for(int i=0; i<l_czlonow;i++) {
			this.add(mainpanelList.get(i));
		}
		
		JPanel guzik_panel = new JPanel();
		JButton dalej = new JButton("Dalej");
		dalej.setPreferredSize(new Dimension(90, 20));
		dalej.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				zamkniecie=true;
			}
		});
		guzik_panel.add(dalej);
		this.add(guzik_panel);
	}
	
}













