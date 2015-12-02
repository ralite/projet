package metier;

import java.util.ArrayList;
import java.util.List;


public class Catalogue {

	private List<I_Produit> lesProduits ;

	public Catalogue() {
		lesProduits = new ArrayList<I_Produit>();
	}
	
	private boolean estPresent(String nom){
		for (I_Produit r : lesProduits) {
			if(r.getNom().compareTo(nom)==0){
				return true;
			}
		}
		return false;
	}
	
	public boolean addProduit(I_Produit p){
		if(estPresent(p.getNom())){
			return false;
		}
		else{
			lesProduits.add(p);
			return true;
		}
	}
	
	public boolean addProduit(String nom, double prixHT,int quantite){
		if(prixHT > 0 && quantite >0){
			return addProduit(new Produit(nom,quantite, prixHT));
		}
		else{
			return false;
		}
	}
	
	public int addProduits(List<I_Produit> produits){
		boolean res=true;
		int i=0;
		for (I_Produit r : produits) {
			res=addProduit(r);
			if(!res){
				i++;
			}
		}
		return i;
	}
	
	private I_Produit chercherProduit(String nom){
		for (I_Produit r : lesProduits) {
			if(r.getNom().compareTo(nom)==0){
				return r;
			}
		}
		return null;
	}
	
	public boolean removeProduit(String nom){
		I_Produit p=chercherProduit(nom);
		if(p!=null){
			lesProduits.remove(p);
			return true;
		}
		else{
			return false;
		}
		
	}
	
	public boolean acheterStock(String nom, int qte){
		I_Produit p=chercherProduit(nom);
		if(p!=null){
			return p.ajouter(qte);
		}
		else {
			return false;
		}
	}
	
	public boolean vendreStock(String nom, int qte){
		I_Produit p=chercherProduit(nom);
		if(p!=null){
			return p.enlever(qte);
		}
		else {
			return false;
		}
	}
	
	public String[] getNomProduit(){
		String [] noms = new String[lesProduits.size()];
		for (int i = 0; i < lesProduits.size() ; i++) {
			noms[i]=lesProduits.get(i).getNom();
		}
		return noms; 
	}
	
	public double  getMontantTotalTTC(){
		double montant=0;
		for (I_Produit r : lesProduits) {
			montant+=r.getPrixStockTTC();
		}
		return montant;
	}

	@Override
	public String toString() {
		String s = "";
		for (I_Produit r : lesProduits) {
			s+=r.getNom()+"\n";
		}
		return s;
	}
	
}
