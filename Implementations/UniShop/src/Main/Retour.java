package Main;

import java.util.UUID;

public class Retour {
    public int id;
    String status;
    Produit produit;

    public Retour(Produit produit, int id ) {
        this.id = id;
        this.status = "non livré";
        this.produit = produit;
    }
    public Retour(int id){
        this.produit = null;
        this.status = "non livré";
        this.id=id;
    }
    public void changerStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }
}
