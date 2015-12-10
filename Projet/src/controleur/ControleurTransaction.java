package controleur;

public class ControleurTransaction {

	public boolean acheterProduit(String nom, int qte) {
		return ControleurCatalogue.catalogue.acheterStock(nom, qte);
	}

	public boolean vendreProduit(String nom, int qte) {
		return ControleurCatalogue.catalogue.vendreStock(nom, qte);
	}

	
	
}
