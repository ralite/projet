package controleur;



import java.util.ArrayList;
import java.util.List;

import com.sun.org.apache.xml.internal.resolver.Catalog;

import DAL.FactoryDAO;
import DAL.FactoryProduitDAO;
import DAL.I_CatalogueDAO;
import DAL.I_ProduitDAO;
import DAL.I_StockageFactory;
import DAL.ProduitDAO;
import metier.Catalogue;
import metier.I_Catalogue;
import metier.I_Produit;

public class ControleurCatalogue {
	private static I_CatalogueDAO catalogueDAO;
	private static I_ProduitDAO pdao;
	public static I_Catalogue catalogue;
	private List<I_Catalogue> lesCatalogues;
	private I_StockageFactory fabrique;
		

	public ControleurCatalogue() {
		fabrique=new FactoryDAO();
		lesCatalogues = new ArrayList<I_Catalogue>();
		catalogueDAO=fabrique.creerCatalogueDAO();
		pdao=fabrique.creerProduitDAO();
	}



	public static void initialiserCatalogue() {
		catalogue=new Catalogue("Catalogue1");
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



	public void ajouterCatalogue(String text) {
		lesCatalogues.add(new Catalogue(text));
		catalogueDAO.creerCatalogue(text);
	}



	public String[] getNomsCatalogues() {
		String[] nomsCatalogues=catalogueDAO.getNomsCatalogues();
		for (String nom : nomsCatalogues) {
			lesCatalogues.add(new Catalogue(nom));
		}
		return nomsCatalogues;
	}



	public void supprimerCatalogue(String texteSupprime) {
		catalogueDAO.supprimerCatalogue(texteSupprime);
		lesCatalogues.remove(new Catalogue(texteSupprime));
		
	}
	
}
   