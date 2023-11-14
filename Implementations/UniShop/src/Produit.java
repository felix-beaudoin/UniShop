import java.util.HashMap;
import java.util.UUID;


public class Produit {
    int id;
    TypeProduit type;
    int prix, nbLikes, quantite, pointsBonus; //prix en cents, pour éviter les nombres flottants
    String nom, categorie;
    String description;
    String[] liensMedia;
    Revendeur Revendeur;
    HashMap<Acheteur, String[]> commentaires;


    public Produit(TypeProduit type, int id, int prix, String nom, String description, Revendeur revendeur, int quantite, int pointsBonus, liensMedia) {
        this.id = id;
        this.type = type;
        this.prix = prix;
        this.nom = nom;
        this.description = description;
        this.Revendeur = revendeur;
        this.quantite = quantite;
        this.pointsBonus = pointsBonus;
        this.liensMedia = liensMedia;
    }

    public Produit(){}

    public void afficher(){
        System.out.println("ID: " + id);
        System.out.println("Type: " + type);
        System.out.println("Prix: " + prix);
        System.out.println("Nom: " + nom);
        System.out.println("Description: " + description);
        System.out.println("Revendeur: " + Revendeur.nom);
        System.out.println("Quantite: " + quantite);
        System.out.println("Points Bonus: " + pointsBonus);
    }
}
