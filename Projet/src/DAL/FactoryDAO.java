package DAL;

import metier.I_Catalogue;

public class FactoryDAO implements I_StockageFactory{
	

	
	@Override
	public I_CatalogueDAO creerCatalogueDAO() {
		return new CatalogueDAO(Connexion.getInstance());
	}

	@Override
	public I_ProduitDAO creerProduitDAO() {
		//Connexion 
		return new ProduitDAO();
	}

}
