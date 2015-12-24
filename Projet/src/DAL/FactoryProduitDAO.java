package DAL;

public class FactoryProduitDAO {
	
	private static I_ProduitDAO instance;

	public static I_ProduitDAO createProduitDAO(String nom) {
		if(instance==null)
			 switch (nom){
                     case "oracle":
                    	 instance = new ProduitDAO();
                         break;
                     case "xml":
                    	 instance = new AdapterProduitDAO_XML();
                         break;

             }
		return instance;
	}	
}
