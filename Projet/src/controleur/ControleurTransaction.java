package controleur;

import javax.swing.JOptionPane;

import DAL.FactoryProduitDAO;
import DAL.I_ProduitDAO;
import DAL.ProduitDAO;

public class ControleurTransaction {
	
	private I_ProduitDAO pdao;
	
	public ControleurTransaction() {
		pdao = FactoryProduitDAO.getInstanceProduitDAO("xml");
	}

	public void acheterProduit(String nom, String qte) {
		boolean resultat;
		try{
			int quantite = Integer.parseInt(qte);
			resultat=pdao.acheterProduit(nom,quantite);
			resultat=ControleurCatalogue.catalogue.acheterStock(nom, quantite);
		}catch(Exception e){
			resultat=false;
		}
		if(!resultat){
			JOptionPane.showMessageDialog(null,
				    "Achat non enregistré !\n",
				    "Erreur",
				    JOptionPane.ERROR_MESSAGE);
		}
	}

	public void vendreProduit(String nom, String qte) {
		boolean resultat ;
		try{
			int quantite = Integer.parseInt(qte);
			resultat= pdao.vendreProduit(nom,quantite);
			resultat=ControleurCatalogue.catalogue.vendreStock(nom, quantite);
		}catch(Exception e){
			resultat=false;
		}
		if(!resultat){
			JOptionPane.showMessageDialog(null,
				    "Vente non enregistrée !\n",
				    "Erreur",
				    JOptionPane.ERROR_MESSAGE);
		}
	}

	
	
}
