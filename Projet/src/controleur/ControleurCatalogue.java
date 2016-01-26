package controleur;



import java.util.ArrayList;
import java.util.List;

import presentation.FenetreAchat;
import presentation.FenetreNouveauProduit;
import presentation.FenetrePrincipale;
import presentation.FenetreSuppressionProduit;
import presentation.FenetreVente;

import com.sun.org.apache.xml.internal.resolver.Catalog;

import DAL.FactoryDAO;
import DAL.I_CatalogueDAO;
import DAL.I_ProduitDAO;
import DAL.I_StockageFactory;
import DAL.ProduitDAO;
import metier.Catalogue;
import metier.I_Catalogue;
import metier.I_Produit;

public class ControleurCatalogue {
	private static I_CatalogueDAO catalogueDAO;
	private I_ProduitDAO pdao;
	private I_Catalogue catalogue;
	private List<I_Catalogue> lesCatalogues;
	private I_StockageFactory fabrique;
	private ControleurGestionProduit ctrlGestionProduit;
	private ControleurTransaction ctrlTransaction;

	public ControleurCatalogue() {
		fabrique=new FactoryDAO();
		lesCatalogues = new ArrayList<I_Catalogue>();
		catalogueDAO=fabrique.creerCatalogueDAO();
		pdao=fabrique.creerProduitDAO();
	}


	public String getProduitsCatalogue() {
		return catalogue.toString();
	}



	public String[] getNomsProduits(){
		return pdao.getNomProduits();
	}



	public void ajouterCatalogue(String text) {
		lesCatalogues.add(new Catalogue(text,0));
		catalogueDAO.creerCatalogue(text);
	}



	public String[] getNomsCatalogues() {
		List<I_Catalogue> catalogues=catalogueDAO.getCatalogues();
		if(catalogues!=null){
			String[] nomsCatalogues= new String[(catalogues.size())];
			
			int i =0;
			for (I_Catalogue catalogue : catalogues) {
				nomsCatalogues[i]=catalogue.getNom();
				i++;
			}
			lesCatalogues=catalogues;
			return nomsCatalogues;
		}
		else return new String[0];
	}



	public void supprimerCatalogue(String texteSupprime) {
		catalogueDAO.supprimerCatalogue(texteSupprime);
		lesCatalogues.remove(new Catalogue(texteSupprime));
		
	}



	public String[] getDetailsCatalogues() {
		int i=0;
		String[] detailsCatalogues=new String[lesCatalogues.size()];
		for (I_Catalogue catalogue : lesCatalogues) {
			detailsCatalogues[i]=catalogue.getNom()+" : "+ String.valueOf(catalogue.getNombreProduits())+" produits";
			i++;
		}
		return detailsCatalogues;
	}
	
	public I_Catalogue rechercherCatalogue(String nomCatalogue){
		for (I_Catalogue catalogue : lesCatalogues) {
			if(catalogue.getNom().equalsIgnoreCase(nomCatalogue)){
				return catalogue;
			}
		}
		return null;
	}



	public void setCatalogue(String texteSelection) {
		I_Catalogue catalogue=rechercherCatalogue(texteSelection);
		if(catalogue!=null){
			this.catalogue=catalogue;
			ctrlGestionProduit=new ControleurGestionProduit(pdao,catalogue);
			ctrlTransaction=new ControleurTransaction(pdao,catalogue);
			catalogue.addProduits(pdao.getProduits(catalogue.getNom()));
			
			new FenetrePrincipale(this);
		}
		
		
	}



	public void nouveauProduit() {
		new FenetreNouveauProduit(ctrlGestionProduit);
		
	}



	public void supprimerProduit(String[] tabProduits) {
		new FenetreSuppressionProduit(tabProduits,ctrlGestionProduit);
		
	}



	public void acheterProduit(String[] tabProduits) {
		new FenetreAchat(tabProduits,ctrlTransaction);
		
	}



	public void vendreProduit(String[] tabProduits) {
		new FenetreVente(tabProduits,ctrlTransaction);
	}
	
}
   