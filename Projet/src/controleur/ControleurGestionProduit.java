package controleur;

import javax.jws.Oneway;

import DAL.ProduitDAO;

public class ControleurGestionProduit {

	private ProduitDAO pdao;
	
	public ControleurGestionProduit() {
		pdao = new ProduitDAO();
	}
	
	public boolean addProduit(String nom, double prixHT,int quantite){
		boolean res;
		res=pdao.creerProduit(nom, prixHT, quantite);
		res=ControleurCatalogue.catalogue.addProduit(nom, prixHT, quantite);
		return res;
	}
	
	public String[] getNomsProduits(){
		return pdao.getNomProduits();
	}

	public boolean remove(String nom) {
		pdao.supprimerProduit(nom);
		//return ControleurCatalogue.catalogue.removeProduit(nom);	
		return true;
	}
}
