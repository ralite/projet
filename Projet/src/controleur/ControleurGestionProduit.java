package controleur;

import javax.jws.Oneway;
import javax.swing.JOptionPane;

import DAL.FactoryProduitDAO;
import DAL.I_ProduitDAO;
import DAL.ProduitDAO;

public class ControleurGestionProduit {

	private I_ProduitDAO pdao;
	
	public ControleurGestionProduit() {
		pdao = FactoryProduitDAO.getInstanceProduitDAO("xml");
	}
	
	public void addProduit(String nom, String prix,String qte){
		boolean resultat;
		try{
			double prixHT =  Double.valueOf(prix);
			int quantite = Integer.parseInt(qte);
			resultat=pdao.creerProduit(nom, prixHT, quantite);
			resultat=ControleurCatalogue.catalogue.addProduit(nom, prixHT, quantite);
		}catch(Exception e){
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
		resultat=pdao.supprimerProduit(nom);
		resultat=ControleurCatalogue.catalogue.removeProduit(nom);	
		if(!resultat){
			JOptionPane.showMessageDialog(null,
				    "Produit non supprimé !\n",
				    "Erreur",
				    JOptionPane.ERROR_MESSAGE);
		}
	}
}
