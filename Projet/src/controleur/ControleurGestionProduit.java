package controleur;

import javax.jws.Oneway;

import DAL.ProduitDAO;
import metier.Produit;

public class ControleurGestionProduit {

	private ProduitDAO pdao;
	
	public ControleurGestionProduit() {
		pdao = new ProduitDAO();
	}
	
	public boolean addProduit(String nom, double prixHT,int quantite){
		pdao.creerProduit(nom, prixHT, quantite);
		return ControleurCatalogue.catalogue.addProduit(nom, prixHT, quantite);
	}
	
	public String[] getNomsProduits(){
		return pdao.getNomProduits();
	}

	public boolean remove(String nom) {
		return ControleurCatalogue.catalogue.removeProduit(nom);		
	}
}
