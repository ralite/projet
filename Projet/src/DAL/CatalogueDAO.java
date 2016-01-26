package DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import metier.Catalogue;
import metier.I_Catalogue;

public class CatalogueDAO implements I_CatalogueDAO {


	Connexion connexion;
	PreparedStatement creerCatalogue=null;
	PreparedStatement recupererNomsCatalogues=null;
	PreparedStatement supprimerCatalogue=null;
	
	public CatalogueDAO(Connexion cn) {
		connexion=cn;
		try {
			creerCatalogue=connexion.prepareStatement("insert into Catalogue values(seqNumCatalogue.nextVal,?,?)",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			recupererNomsCatalogues=connexion.prepareStatement("select nomCatalogue, nombreProduit from Catalogue",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
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
			creerCatalogue.setInt(2, 0);
			creerCatalogue.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public List<I_Catalogue> getCatalogues() {
		List<I_Catalogue> catalogues = new ArrayList<I_Catalogue>();
		int i=0;
		ResultSet rs=null;
		try {
			int nombreProduits=0;
			rs=recupererNomsCatalogues.executeQuery();
			String nomsCatalogues = new String();
			rs.beforeFirst();
			while(rs.next()){
				nomsCatalogues=rs.getString("nomcatalogue");
				nombreProduits=rs.getInt("nombreProduit");
				catalogues.add(new Catalogue(nomsCatalogues,nombreProduits));
			}
			return catalogues;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
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
