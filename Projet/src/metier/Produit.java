package metier;

public class Produit implements I_Produit {

	private int quantiteStock;
	private String nom;
	private double prixUnitaireHT;
	private static double tauxTVA=0.2;
	
	public Produit(String nom, double prixUnitaireHT,int quantiteStock) {
		super();
		this.quantiteStock = quantiteStock;
		this.nom = nom;
		this.prixUnitaireHT = prixUnitaireHT;
	}

	@Override
	public boolean ajouter(int qteAchetee) {
		if(qteAchetee<=0){
			return false;
		}
		else {
			quantiteStock+=qteAchetee;
			return true;
		}
	}

	@Override
	public boolean enlever(int qteVendue) {
		if(qteVendue<=0 || quantiteStock<qteVendue){
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
		return arrondirPrix(prixUnitaireHT);
	}

	@Override
	public double getPrixUnitaireTTC() {
		return arrondirPrix(prixUnitaireHT+(prixUnitaireHT*tauxTVA));
	}

	@Override
	public double getPrixStockTTC() {
		return ((prixUnitaireHT+(prixUnitaireHT*tauxTVA))*quantiteStock);
	}
	
	private double arrondirPrix(double d){
		//return (((double)(d*100))/100.);
		return (double)Math.round(d * 100) / 100;
	}

	@Override
	public String toString(){
		return nom;
	}
	
}
