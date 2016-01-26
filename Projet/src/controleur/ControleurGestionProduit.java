package controleur;

import javax.jws.Oneway;
import javax.swing.JOptionPane;

import metier.I_Catalogue;
import DAL.I_ProduitDAO;
import DAL.ProduitDAO;

public class ControleurGestionProduit {

	private I_ProduitDAO pdao;
	private I_Catalogue catalogue;
	
	public ControleurGestionProduit(I_ProduitDAO pdao,I_Catalogue catalogue) {
		this.pdao=pdao;
		this.catalogue=catalogue;
	}
	
	public void addProduit(String nom, String prix,String qte){
		boolean resultat=false;
		try{
			System.out.println(catalogue);
			double prixHT =  Double.parseDouble(prix);
			int quantite = Integer.parseInt(qte);
			resultat=pdao.creerProduit(nom, prixHT, quantite, catalogue.getNom());
			resultat=catalogue.addProduit(nom, prixHT, quantite);
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("icivraiment");
			resultat=false;
		}
		if(!resultat){
			JOptionPane.showMessageDialog(null,
				    "Produit non enregistré !\n Verifiez le prix est la quantité.",
				    "Erreur",
				    JOptionPane.ERROR_MESSAGE);
		}
	}
	

	public void remove(String nom) {
		boolean resultat;
		resultat=pdao.supprimerProduit(nom,catalogue.getNom());
		resultat=catalogue.removeProduit(nom);	
		if(!resultat){
			JOptionPane.showMessageDialog(null,
				    "Produit non supprimé !\n",
				    "Erreur",
				    JOptionPane.ERROR_MESSAGE);
		}
	}
}
