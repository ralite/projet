package controleur;

import DAL.FactoryProduitDAO;
import DAL.I_ProduitDAO;
import DAL.ProduitDAO;

public class ControleurTransaction {
	
	private I_ProduitDAO pdao;
	
	public ControleurTransaction() {
		pdao = FactoryProduitDAO.createProduitDAO("xml");
	}

	public boolean acheterProduit(String nom, int qte) {
		boolean resultat;
		resultat=pdao.acheterProduit(nom,qte);
		resultat=ControleurCatalogue.catalogue.acheterStock(nom, qte);
		return resultat;
	}

	public boolean vendreProduit(String nom, int qte) {
		boolean resultat ;
		resultat= pdao.vendreProduit(nom,qte);
		resultat=ControleurCatalogue.catalogue.vendreStock(nom, qte);
		return resultat;
	}

	
	
}
