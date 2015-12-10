package DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProduitDAO {

	String url="jdbc:oracle:thin:@gloin:1521:iut";
	String driver = "oracle.jdbc.driver.OracleDriver";
	String login = "ralitej";
	String mdp="2205000408Z";
	Connection cn= null;
	PreparedStatement creerProd = null;
	PreparedStatement suppProd = null;
	ResultSet rs=null;
	
	public ProduitDAO() {
		try {
			Class.forName(driver);
			cn=DriverManager.getConnection(url,login,mdp);
			} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			creerProd=cn.prepareStatement("insert into Produit values (seqNumProduit.nextval,?,?,?)",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			suppProd=cn.prepareStatement("delete from Produit where nomProduit=?",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void deconnexion() {
		// TODO Auto-generated method stub
		try {
			cn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public boolean creerProduit(String nom, double prixHT, int qte){
		try {
			creerProd.setString(1,nom);
			creerProd.setDouble(2,prixHT);
			creerProd.setInt(3,qte);
			creerProd.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean supprimerProduit(String nom){
		try {
			suppProd.setString(1,nom);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public String[] getNomProduits() {
		return null;
	}
	
}
