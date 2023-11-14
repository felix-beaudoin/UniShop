import java.util.UUID;

public class Retour {
    int id;
    String status;
    Produit produit;

    public Retour(Produit produit, int id) {
        this.id = id;
        this.status = "non livré";
        this.produit = produit;
    }

    public void changerStatus(String status) {
        this.status = status;
    }
}