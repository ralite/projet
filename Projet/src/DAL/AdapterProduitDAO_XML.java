package DAL;

import java.util.List;

import metier.I_Produit;
import metier.Produit;

public class AdapterProduitDAO_XML implements I_ProduitDAO{
	private ProduitDAO_XML daoxml;
	
	
	public AdapterProduitDAO_XML() {
		this.daoxml = new ProduitDAO_XML();
	}

	@Override
	public boolean creerProduit(String nom, double prixHT, int qte, String nomCatalogue) {
		boolean resultat = daoxml.creer(new Produit(nom, prixHT, qte));
		return resultat;
	}

	@Override
	public boolean supprimerProduit(String nom, String nomCatalogue) {
		boolean resultat;
		I_Produit produit;
		produit=daoxml.lire(nom);
		resultat=daoxml.supprimer(produit);
		return resultat;
	}

	@Override
	public String[] getNomProduits() {
		List<I_Produit> lesProduits = daoxml.lireTous();
		String[] noms= new String[lesProduits.size()];
		int i=0;
		for (I_Produit p : lesProduits) {
			noms[i]=p.getNom();
			i++;
		}
		return noms;
	}

	@Override
	public List<I_Produit> getProduits(String nomCatalogue) {
		return daoxml.lireTous();
	}

	@Override
	public boolean acheterProduit(String nom, int qte) {
		boolean resultat;
		I_Produit produit = daoxml.lire(nom);
		resultat=produit.ajouter(qte);
		resultat=daoxml.maj(produit);
		return resultat;
	}

	@Override
	public boolean vendreProduit(String nom, int qte) {
		boolean resultat;
		I_Produit produit = daoxml.lire(nom);
		resultat=produit.enlever(qte);
		resultat=daoxml.maj(produit);
		return resultat;
	}

}
