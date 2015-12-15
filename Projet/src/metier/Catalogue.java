package metier;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Catalogue implements I_Catalogue{

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
		if( p==null || estPresent(p.getNom()) || p.getQuantite()<0 || p.getPrixUnitaireHT()<=0){
			return false;
		}
		else{
			lesProduits.add(p);
			return true;
		}
	}
	
	public boolean addProduit(String nom, double prixHT,int quantite){
		if(prixHT >= 0 && quantite >=0){
			return addProduit(new Produit(nom, prixHT,quantite));
		}
		else{
			return false;
		}
	}
	
	public int addProduits(List<I_Produit> produits){
		boolean res=true;
		int i=0;
		if(produits != null){
			for (I_Produit r : produits) {
				res=addProduit(r);
				if(res){
					i++;
				}
			}
		}
		return i;
	}
	
	private I_Produit chercherProduit(String nom){
		if(nom!=null){
			for (I_Produit r : lesProduits) {
				if(r.getNom().equalsIgnoreCase(nom)){
					return r;
				}
			}
		}
		return null;
	}
	
	public boolean removeProduit(String nom){
		I_Produit p=chercherProduit(nom);
		System.out.println(p);
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
	
	@Override
	public String[] getNomProduits(){
		String [] noms = new String[lesProduits.size()];
		ArrayList<String> trie = new ArrayList<String>();
		for (I_Produit p : lesProduits) {
			trie.add(p.getNom());
		}
		Collections.sort(trie);
		for (int i = 0; i < lesProduits.size() ; i++) {
			noms[i]=trie.get(i);
		}
		return noms; 
	}

	
	public double  getMontantTotalTTC(){
		double montant=0;
		for (I_Produit r : lesProduits) {
			montant+=r.getPrixStockTTC();
		}
		return arrondirPrix(montant);
	}

	private double arrondirPrix(double d){
		return (double)Math.round(d * 100) / 100;
	}
	
	@Override
	public void clear() {
		lesProduits.clear();
		
	}
	
	@Override
	public String toString() {
		String s = "";
		DecimalFormat df = new DecimalFormat("#0.00");
		for (I_Produit r : lesProduits) {
			s+=r.getNom()+" - prix HT : "+df.format(r.getPrixUnitaireHT())+" € - prix TTC : "+df.format(r.getPrixUnitaireTTC())+" € - quantité en stock : "+r.getQuantite() + "\n";
		}
		s+="\nMontant total TTC du stock : "+df.format(getMontantTotalTTC())+" €";
		s=s.replace(".", ",");
		return s;
	}
	
}
