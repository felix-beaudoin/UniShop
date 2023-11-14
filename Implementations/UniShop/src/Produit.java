import java.util.HashMap;

public class Produit {
    TypeProduit type;
    int prix, nbLikes; //prix en cents, pour éviter les nombres flottants
    String nom;
    String description;
    Vendeur vendeur;

    HashMap<Acheteur, String[]> commentaires;

    public Produit(TypeProduit type, int prix, String nom, String description, Vendeur vendeur) {
        this.type = type;
        this.prix = prix;
        this.nom = nom;
        this.description = description;
        this.vendeur = vendeur;
    }

    public Produit(){};

    public void afficher(){
        return;
    }
}
