package controleur;



import java.util.List;

import com.sun.org.apache.xml.internal.resolver.Catalog;

import DAL.FactoryProduitDAO;
import DAL.I_ProduitDAO;
import DAL.ProduitDAO;
import metier.Catalogue;
import metier.I_Catalogue;
import metier.I_Produit;

public class ControleurCatalogue {
	private static I_ProduitDAO pdao;
	public static I_Catalogue catalogue;
	


	public static void initialiserCatalogue() {
		catalogue=new Catalogue();
		pdao = FactoryProduitDAO.getInstanceProduitDAO("xml");
		List<I_Produit> produit = pdao.getProduits();
		catalogue.addProduits(produit);
		
	}



	public static String getProduitsCatalogue() {
		return catalogue.toString();
	}



	public static String[] getNomsProduits(){
		return pdao.getNomProduits();
	}
	
}
   