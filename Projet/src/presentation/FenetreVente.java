package presentation;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import controleur.ControleurTransaction;

public class FenetreVente extends JFrame implements ActionListener {

	private ControleurTransaction ct;
	private JButton btVente;
	private JTextField txtQuantite;
	private JComboBox<String> combo;
	private JLabel labErreur = new JLabel();

	public FenetreVente(String[] lesProduits, ControleurTransaction ctrlTransaction) {
		
		ct=ctrlTransaction;
		
		setTitle("Vente");
		setBounds(500, 500, 200, 125);
		Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());
		btVente = new JButton("Vente");
		txtQuantite = new JTextField(5);
		txtQuantite.setText("0");

		combo = new JComboBox<String>(lesProduits);
		combo.setPreferredSize(new Dimension(100, 20));
		contentPane.add(new JLabel("Produit"));
		contentPane.add(combo);
		contentPane.add(new JLabel("Quantit� vendue"));
		contentPane.add(txtQuantite);
		contentPane.add(btVente);
		contentPane.add(labErreur);
		
		btVente.addActionListener(this);
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		ct.vendreProduit(combo.getSelectedItem().toString(),txtQuantite.getText());
		this.dispose();
				
	}

}
