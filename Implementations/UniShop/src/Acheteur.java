public class Acheteur {

    String nom, prenom, pseudo, email, adresse, role;
    int telephone, points;

    public Acheteur(String nom, String prenom, String pseudo, String email, String adresse, int telephone) {
        this.nom = nom;
        this.prenom = prenom;
        this.pseudo = pseudo;
        this.email = email;
        this.adresse = adresse;
        this.telephone = telephone;
        this.role = "Acheteur";
        points = 0;

    }

    public Acheteur(){
        this.role = "Acheteur";
    }
}
