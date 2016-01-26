package DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import metier.I_Produit;
import metier.Produit;

public class ProduitDAO implements I_ProduitDAO {

	Connexion cn;

	PreparedStatement creerProd = null;
	PreparedStatement suppProd = null;
	PreparedStatement getProd = null;
	PreparedStatement gererStock = null;
	PreparedStatement incrementerNbProduits=null;
	PreparedStatement decrementerNbProduits=null;
	ResultSet rs=null;

	
	public ProduitDAO(Connexion connexion) {
		cn=connexion;
		try {
			creerProd=cn.prepareStatement("insert into Produit values (seqNumProduit.nextval,?,?,?,?)",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			suppProd=cn.prepareStatement("delete from Produit where nomProduit=?",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			getProd=cn.prepareStatement("select * from Produit where nomCatalogue=?",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			gererStock=cn.prepareStatement("update Produit set quantite=quantite+? where nomProduit=?",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			incrementerNbProduits=cn.prepareStatement("update Catalogue set nombreProduit=nombreProduit+1",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			decrementerNbProduits=cn.prepareStatement("update Catalogue set nombreProduit=nombreProduit-1",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	public void deconnexion() {
	
		cn.deconnexion();

	}
	
	public boolean creerProduit(String nom, double prixHT, int qte, String nomCatalogue){
		try {
			creerProd.setString(1,nom);
			creerProd.setDouble(2,prixHT);
			creerProd.setInt(3,qte);
			creerProd.setString(4, nomCatalogue);
			creerProd.executeQuery();
			incrementerNbProduits.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean supprimerProduit(String nom, String nomCatalogue){
		try {
			suppProd.setString(1,nom);
			suppProd.setString(2,nomCatalogue);
			suppProd.executeQuery();
			decrementerNbProduits.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public String[] getNomProduits() {
		String noms[];
		int i=0;
		try {
			
			rs=getProd.executeQuery();
			rs.last();
			noms = new String[rs.getRow()];
			rs.beforeFirst();
			while(rs.next()){
				noms[i]=rs.getString("nomproduit");
				i++;
			}
			return noms;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new String[0];
		}
	}

	public List<I_Produit> getProduits(String nomCatalogue) {
		try {
			double prixUnitaireHT;
			int quantiteStock;
			List<I_Produit> prod = new ArrayList<I_Produit>();
			getProd.setString(1, nomCatalogue);
			rs=getProd.executeQuery();
			rs.beforeFirst();
			while(rs.next()){
				prixUnitaireHT= rs.getDouble("prixht");
				if(rs.wasNull())
					prixUnitaireHT=0;
				quantiteStock=rs.getInt("quantite");
				if(rs.wasNull())
					quantiteStock=0;
				prod.add(new Produit(rs.getString("nomproduit"), prixUnitaireHT, quantiteStock));
			}
			return prod;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return null;
	}

	public boolean acheterProduit(String nom, int qte) {
		try {
			gererStock.setInt(1,qte);
			gererStock.setString(2,nom);
			gererStock.executeQuery();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		
	}

	public boolean vendreProduit(String nom, int qte) {
		try {
			int diminuerQuantite=qte*-1;
			gererStock.setInt(1,diminuerQuantite);
			gererStock.setString(2,nom);
			gererStock.executeQuery();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
	}

	
}
