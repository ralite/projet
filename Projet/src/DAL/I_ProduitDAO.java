package DAL;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import metier.I_Produit;
import metier.Produit;

public interface I_ProduitDAO {

	public boolean creerProduit(String nom, double prixHT, int qte, String nomCatalogue);
	
	public boolean supprimerProduit(String nom, String nomCatalogue);

	public String[] getNomProduits();

	public List<I_Produit> getProduits(String nomCatalogue);

	public boolean acheterProduit(String nom, int qte);

	public boolean vendreProduit(String nom, int qte);
	
	
}
