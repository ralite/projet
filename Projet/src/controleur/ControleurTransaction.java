package controleur;

import DAL.FactoryProduitDAO;
import DAL.I_ProduitDAO;
import DAL.ProduitDAO;

public class ControleurTransaction {
	
	private I_ProduitDAO pdao;
	
	public ControleurTransaction() {
		pdao = FactoryProduitDAO.createProduitDAO();
	}

	public boolean acheterProduit(String nom, int qte) {
		pdao.acheterProduit(nom,qte);
		return ControleurCatalogue.catalogue.acheterStock(nom, qte);
	}

	public boolean vendreProduit(String nom, int qte) {
		pdao.vendreProduit(nom,qte);
		return ControleurCatalogue.catalogue.vendreStock(nom, qte);
	}

	
	
}
