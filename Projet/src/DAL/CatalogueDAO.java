package DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CatalogueDAO implements I_CatalogueDAO {


	Connexion connexion;
	PreparedStatement creerCatalogue=null;
	PreparedStatement recupererNomsCatalogues=null;
	PreparedStatement supprimerCatalogue=null;
	
	public CatalogueDAO(Connexion cn) {
		connexion=cn;
		try {
			creerCatalogue=connexion.prepareStatement("insert into Catalogue values(seqNumCatalogue.nextVal,?)",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			recupererNomsCatalogues=connexion.prepareStatement("select nomCatalogue from Catalogue",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			supprimerCatalogue=connexion.prepareStatement("delete from Catalogue where nomCatalogue=?",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Boolean creerCatalogue(String text) {
		try {
			creerCatalogue.setString(1, text);
			creerCatalogue.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public String[] getNomsCatalogues() {
		int i=0;
		ResultSet rs=null;
		try {
			rs=recupererNomsCatalogues.executeQuery();
			rs.last();
			String[] nomsCatalogues = new String[rs.getRow()];
			rs.beforeFirst();
			while(rs.next()){
				nomsCatalogues[i]=rs.getString("nomcatalogue");
				i++;
			}
			return nomsCatalogues;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new String[0];
		}
		
	}

	@Override
	public void supprimerCatalogue(String texteSupprime) {
		try {
			supprimerCatalogue.setString(1, texteSupprime);
			supprimerCatalogue.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

	
}
