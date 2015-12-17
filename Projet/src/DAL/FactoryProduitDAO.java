package DAL;

public class FactoryProduitDAO {
	
	private static I_ProduitDAO instance;

	public static I_ProduitDAO createProduitDAO() {
		if(instance==null)
			instance = new ProduitDAO();
		return instance;
	}	
}
