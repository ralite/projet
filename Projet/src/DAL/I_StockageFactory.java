package DAL;

import metier.I_Catalogue;

public interface I_StockageFactory {

	I_CatalogueDAO creerCatalogueDAO();

	I_ProduitDAO creerProduitDAO();

	
}
