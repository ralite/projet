package controleur;

import javax.jws.Oneway;

import DAL.FactoryProduitDAO;
import DAL.I_ProduitDAO;
import DAL.ProduitDAO;

public class ControleurGestionProduit {

	private I_ProduitDAO pdao;
	
	public ControleurGestionProduit() {
		pdao = FactoryProduitDAO.createProduitDAO("xml");
	}
	
	public boolean addProduit(String nom, double prixHT,int quantite){
		boolean resultat;
		resultat=pdao.creerProduit(nom, prixHT, quantite);
		resultat=ControleurCatalogue.catalogue.addProduit(nom, prixHT, quantite);
		return resultat;
	}
	
	public String[] getNomsProduits(){
		return pdao.getNomProduits();
	}

	public boolean remove(String nom) {
		boolean resultat;
		resultat=pdao.supprimerProduit(nom);
		resultat=ControleurCatalogue.catalogue.removeProduit(nom);	
		return resultat;
	}
}
