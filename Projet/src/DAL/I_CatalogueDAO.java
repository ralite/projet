package DAL;

import java.util.List;

import metier.I_Catalogue;

public interface I_CatalogueDAO {

	Boolean creerCatalogue(String text);

	void supprimerCatalogue(String texteSupprime);

	List<I_Catalogue> getCatalogues();

}
