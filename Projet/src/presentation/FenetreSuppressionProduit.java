package presentation;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import controleur.ControleurGestionProduit;

public class FenetreSuppressionProduit extends JFrame implements ActionListener {
	private ControleurGestionProduit gp = new ControleurGestionProduit();
	private JButton btSupprimer;
	private JComboBox<String> combo;
	
	public FenetreSuppressionProduit(String lesProduits[]) {
		
		setTitle("Suppression produit");
		setBounds(500, 500, 200, 105);
		Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());
		btSupprimer = new JButton("Supprimer");
		System.out.println(lesProduits);
		combo = new JComboBox<String>(lesProduits);
		combo.setPreferredSize(new Dimension(100, 20));
		contentPane.add(new JLabel("Produit"));
		contentPane.add(combo);
		contentPane.add(btSupprimer);

		btSupprimer.addActionListener(this);

		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		gp.remove(combo.getSelectedItem().toString());
		this.dispose();
	}

}
