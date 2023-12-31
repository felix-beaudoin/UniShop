package Main;

import java.util.List;

public class Commande {

    String code;
    String etat;
    int totalPrixCommande;
    int totalPointsCommande;
    Acheteur acheteur;
    private List<Produit> produitCommande;

    public Commande(Acheteur acheteur,String code, String etat,List<Produit> produitCommande, int totalPrixCommande, int totalPointsCommande){
        this.code = code;
        this.etat = etat;
        this.produitCommande = produitCommande;
        this.totalPrixCommande = totalPrixCommande;
        this.acheteur = acheteur;
        this.totalPointsCommande = totalPointsCommande;
    }



    public String getCode(){
        return code;
    }
    public String getEtat(){
        return etat;
    }
    public List<Produit>  getProduitCommande(){
        return produitCommande;
    }
    public void setProduitCommande(List<Produit> newProduitCommande){
        this.produitCommande = newProduitCommande;
    }

}
