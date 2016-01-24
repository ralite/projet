package DAL;

public interface I_CatalogueDAO {

	Boolean creerCatalogue(String text);

	String[] getNomsCatalogues();

	void supprimerCatalogue(String texteSupprime);

}
