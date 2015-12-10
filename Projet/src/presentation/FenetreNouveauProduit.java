package presentation;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import controleur.ControleurGestionProduit;

public class FenetreNouveauProduit extends JFrame implements ActionListener {
	private ControleurGestionProduit gp = new ControleurGestionProduit();
	private JTextField txtPrixHT;
	private JTextField txtNom;
	private JTextField txtQte;
//	private JComboBox<String> combo;
	private JButton btValider;
	JLabel labErreur = new JLabel();

//	public FenetreNouveauProduit(String[] lesCategories) {
	public FenetreNouveauProduit() {	

		setTitle("Creation Produit");
		setBounds(500, 500, 200, 250);
		Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());

		JLabel labNom = new JLabel("Nom produit");
		JLabel labPrixHT = new JLabel("Prix Hors Taxe");
		JLabel labQte = new JLabel("Quantit� en stock");
//		JLabel labCategorie = new JLabel("Categorie");
		contentPane.add(labNom);
		txtNom = new JTextField(15);
		contentPane.add(txtNom);
		contentPane.add(labPrixHT);
		txtPrixHT = new JTextField(15);
		contentPane.add(txtPrixHT);
		contentPane.add(labQte);
		txtQte = new JTextField(15);
		contentPane.add(txtQte);
		contentPane.add(labErreur);

//		combo = new JComboBox<String>(lesCategories);
//		combo.setPreferredSize(new Dimension(100, 20));
//		contentPane.add(labCategorie);
//		contentPane.add(combo);

		
		btValider = new JButton("Valider");
		contentPane.add(btValider);

		btValider.addActionListener(this);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		Double prix ;
		int qte ;
		try{
			prix =  Double.valueOf(txtPrixHT.getText().toString());
			qte = Integer.parseInt(txtQte.getText().toString());
			if(!gp.addProduit(txtNom.getText().toString(),prix, qte)){
				labErreur.setText("Erreur saisie");
			}
			else{
				this.dispose();
			}
		} catch(Exception e1){
			labErreur.setText("Verifier prix & quantité");
		}
		
	}

}