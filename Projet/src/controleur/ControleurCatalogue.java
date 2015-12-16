package controleur;



import java.util.List;

import DAL.ProduitDAO;
import metier.I_Catalogue;
import metier.I_Produit;

public class ControleurCatalogue {
	private static ProduitDAO pdao;
	public static I_Catalogue catalogue;
	
	public static void setProduits(){
		List<I_Produit> prod = pdao.getProduits();
		catalogue.addProduits(prod);
	}
	
}
   