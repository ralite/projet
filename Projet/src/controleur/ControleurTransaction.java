package controleur;

import javax.swing.JOptionPane;

import metier.I_Catalogue;
import DAL.I_ProduitDAO;
import DAL.ProduitDAO;

public class ControleurTransaction {
	
	private I_ProduitDAO pdao;
	private I_Catalogue catalogue;
	
	public ControleurTransaction(I_ProduitDAO pdao,I_Catalogue catalogue) {
		this.pdao=pdao;
		this.catalogue=catalogue;
	}

	public void acheterProduit(String nom, String qte) {
		boolean resultat;
		try{
			int quantite = Integer.parseInt(qte);
			resultat=pdao.acheterProduit(nom,quantite);
			resultat=catalogue.acheterStock(nom, quantite);
		}catch(Exception e){
			resultat=false;
		}
		if(!resultat){
			JOptionPane.showMessageDialog(null,
				    "Achat non enregistré !\n",
				    "Erreur",
				    JOptionPane.ERROR_MESSAGE);
		}
	}

	public void vendreProduit(String nom, String qte) {
		boolean resultat ;
		try{
			int quantite = Integer.parseInt(qte);
			resultat= pdao.vendreProduit(nom,quantite);
			resultat=catalogue.vendreStock(nom, quantite);
		}catch(Exception e){
			resultat=false;
		}
		if(!resultat){
			JOptionPane.showMessageDialog(null,
				    "Vente non enregistrée !\n",
				    "Erreur",
				    JOptionPane.ERROR_MESSAGE);
		}
	}

	
	
}
