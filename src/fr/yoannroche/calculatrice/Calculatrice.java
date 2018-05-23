package fr.yoannroche.calculatrice;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener; 
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Calculatrice extends JFrame{
	private JPanel container = new JPanel();
	private JLabel ecran = new JLabel();
	private double chiffre1 ;
	private boolean clicOperateur = false, update= true;
	private String operateur = "" ;
	

	String [] tab_string = {"1","2","3","4","5","6","7","8","9","0","=","+","-","/","*","C","."};
	JButton [] tab_button = new JButton[tab_string.length];

	public Calculatrice() {
		this.setSize(230,255);
		this.setTitle("Calculette");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		container.setBackground(Color.DARK_GRAY);
		Contenu();

		this.setContentPane(container);
		this.setVisible(true);




	}
	/**
	 * Géneration de la méthode qui gère le graphique de la Calculatrice
	 */
	private void Contenu() {
		Font police = new Font("Impact", Font.BOLD,40);
		ecran = new JLabel("0");
		ecran.setFont(police);
	    
	    
		JPanel operateur = new JPanel ();
		operateur.setPreferredSize(new Dimension(50,160));
		operateur.setBorder(BorderFactory.createLineBorder(Color.gray));
		operateur.setBackground(Color.orange);
		JPanel chiffre = new JPanel ();
		chiffre.setPreferredSize(new Dimension(150,130));
		chiffre.setBorder(BorderFactory.createLineBorder(Color.gray));
		chiffre.setBackground(Color.getHSBColor(189, 32, 54));
		JPanel ecranP = new JPanel ();
	    
		ecranP.setBackground(Color.getHSBColor(189,32,54));
		ecranP.setBorder(BorderFactory.createLineBorder(Color.gray));
		ecran.setHorizontalAlignment(JLabel.RIGHT);
		ecran.setPreferredSize(new Dimension(200,40));
		
		
		



		for(int i = 0; i < tab_string.length; i++){
			
			tab_button[i] = new JButton(tab_string[i]);
			switch(i) {
			
			case 10 :
				tab_button[i].addActionListener(new EgalListener());
				tab_button[i].setBackground(Color.DARK_GRAY);
				tab_button[i].setForeground(Color.orange);
				chiffre.add(tab_button[i]);
				break;

			case 11 :
				tab_button[i].addActionListener(new PlusListener());
				tab_button[i].setForeground(Color.getHSBColor(189, 32, 54));
				tab_button[i].setBackground(Color.DARK_GRAY);
				operateur.add(tab_button[i]);
				break;


			case 12 :
				tab_button[i].addActionListener(new MoinsListener());
				tab_button[i].setForeground(Color.getHSBColor(189, 32, 54));
				tab_button[i].setBackground(Color.DARK_GRAY);
				operateur.add(tab_button[i]);
				break;


			case 13 :
				tab_button[i].addActionListener(new DivListener());
				tab_button[i].setForeground(Color.getHSBColor(189, 32, 54)) ;
				tab_button[i].setBackground(Color.DARK_GRAY);
				operateur.add(tab_button[i]);
				break;


			case 14 :
				tab_button[i].addActionListener(new MultiListener());
				tab_button[i].setForeground(Color.getHSBColor(189, 32, 54));
				tab_button[i].setBackground(Color.DARK_GRAY);
				operateur.add(tab_button[i]);
				break;

			case 15 :
				tab_button[i].addActionListener(new ResetListener());
				tab_button[i].setForeground(Color.red);
				tab_button[i].setBackground(Color.DARK_GRAY);
				operateur.add(tab_button[i]);
				break ;

			default :
				chiffre.add(tab_button[i]);
				tab_button[i].addActionListener(new ChiffreListener());
				tab_button[i].setForeground(Color.DARK_GRAY);
				break;

			}
		}
			
		ecranP.add(ecran);
		container.add(ecranP, BorderLayout.NORTH);
		container.add(chiffre, BorderLayout.CENTER);
		container.add(operateur, BorderLayout.EAST);


	}

	private void calcul() {
		if(operateur.equals("+")){
			chiffre1 = chiffre1 + 
					Double.valueOf(ecran.getText()).doubleValue();
			ecran.setText(String.valueOf(chiffre1));
		}
		if(operateur.equals("-")){
			chiffre1 = chiffre1 - 
					Double.valueOf(ecran.getText()).doubleValue();
			ecran.setText(String.valueOf(chiffre1));
		}          
		if(operateur.equals("*")){
			chiffre1 = chiffre1 * 
					Double.valueOf(ecran.getText()).doubleValue();
			ecran.setText(String.valueOf(chiffre1));
		}     
		if(operateur.equals("/")){
			try{
				chiffre1 = chiffre1 / 
						Double.valueOf(ecran.getText()).doubleValue();
				ecran.setText(String.valueOf(chiffre1));
			} catch(ArithmeticException e) {
				ecran.setText("0");
			}
		}
		
			
			
		}
		

	/**
	 * Listener des chiffres
	 * @author Yoann
	 *
	 */
	class ChiffreListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String str = ((JButton)e.getSource()).getText();
			if(update){
				update = false;
			}
			else{
				if(!ecran.getText().equals("0"))
					str = ecran.getText() + str;
			}
			ecran.setText(str);
		}
	}
	/**
	 * Listener du bouton =
	 * @author Yoann
	 *
	 */
	class EgalListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0){
			calcul();
			update = true;
			clicOperateur = false;
		}
	}

	/**
	 * Listener du bouton +
	 * @author Yoann
	 *
	 */
	class PlusListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0){
			if(clicOperateur){
				calcul();
				ecran.setText(String.valueOf(chiffre1));
			}
			else{
				chiffre1 = Double.valueOf(ecran.getText()).doubleValue();
				clicOperateur = true;
			}
			operateur = "+";
			update = true;
		}
	}

	/**
	 * Listener du bouton -
	 * @author Yoann
	 *
	 */
	class MoinsListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0){
			if(clicOperateur){
				calcul();
				ecran.setText(String.valueOf(chiffre1));
			}
			else{
				chiffre1 = Double.valueOf(ecran.getText()).doubleValue();
				clicOperateur = true;
			}
			operateur = "-";
			update = true;
		}
	}

	/**
	 * Listener du bouton *
	 * @author Yoann
	 *
	 */
	class MultiListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0){
			if(clicOperateur){
				calcul();
				ecran.setText(String.valueOf(chiffre1));
			}
			else{
				chiffre1 = Double.valueOf(ecran.getText()).doubleValue();
				clicOperateur = true;
			}
			operateur = "*";
			update = true;
		}
	}

	/**
	 * Listener du bouton /
	 * @author Yoann
	 *
	 */
	class DivListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0){
			if(clicOperateur){
				calcul();
				ecran.setText(String.valueOf(chiffre1));
			}
			else{
				chiffre1 = Double.valueOf(ecran.getText()).doubleValue();
				clicOperateur = true;
			}
			operateur = "/";
			update = true;
		}
	}

	/**
	 * Listener du bouton C ( reset )
	 * @author Yoann
	 *
	 */
	class ResetListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0){
			clicOperateur = false;
			update = true;
			chiffre1 = 0;
			operateur = "";
			ecran.setText("0");
		}
	} 
	/**
	 * Listener de la virgule
	 * @author Yoann
	 *
	 */
	class PointListener implements ActionListener {
		public void actionPerformed(ActionEvent e){
			String str = ((JButton)e.getSource()).getText();
			if(update){
				update = false;
			}
			else{
				if(!ecran.getText().equals("0"))
					str = ecran.getText() + str;
			}
			ecran.setText(str);
		}
	}
}
