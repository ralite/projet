package metier;

public class Produit implements I_Produit {

	private int quantiteStock;
	private String nom;
	private double prixUnitaireHT;
	private static double tauxTVA=0.2;
	
	public Produit(String nom,int quantiteStock, double prixUnitaireHT) {
		super();
		this.quantiteStock = quantiteStock;
		this.nom = nom;
		this.prixUnitaireHT = prixUnitaireHT;
	}

	@Override
	public boolean ajouter(int qteAchetee) {
		if(qteAchetee<0){
			return false;
		}
		else {
			quantiteStock+=qteAchetee;
			return true;
		}
	}

	@Override
	public boolean enlever(int qteVendue) {
		if(qteVendue<0 || quantiteStock<qteVendue){
			return false;
		}
		else {
			quantiteStock-=qteVendue;
			return true;
		}
	}

	@Override
	public String getNom() {
		return nom;
	}

	@Override
	public int getQuantite() {
		return quantiteStock;
	}

	@Override
	public double getPrixUnitaireHT() {
		return prixUnitaireHT;
	}

	@Override
	public double getPrixUnitaireTTC() {
		return prixUnitaireHT+(prixUnitaireHT*tauxTVA);
	}

	@Override
	public double getPrixStockTTC() {
		return (prixUnitaireHT+(prixUnitaireHT*tauxTVA))*quantiteStock;
	}

	public String toString(){
		return nom;
	}
	
}
