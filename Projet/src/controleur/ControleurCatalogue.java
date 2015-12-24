package controleur;



import java.util.List;

import DAL.FactoryProduitDAO;
import DAL.I_ProduitDAO;
import DAL.ProduitDAO;
import metier.I_Catalogue;
import metier.I_Produit;

public class ControleurCatalogue {
	private static I_ProduitDAO pdao;
	public static I_Catalogue catalogue;
	
	public static void setProduits(){
		pdao = FactoryProduitDAO.createProduitDAO("xml");
		List<I_Produit> prod = pdao.getProduits();
		catalogue.addProduits(prod);
	}
	
}
   