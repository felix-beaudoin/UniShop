public class Acheteur {

    String nom, prenom, pseudo, email, adresse, role;
    int telephone;

    public Acheteur(String nom, String prenom, String pseudo, String email, String adresse, int telephone) {
        this.nom = nom;
        this.prenom = prenom;
        this.pseudo = pseudo;
        this.email = email;
        this.adresse = adresse;
        this.telephone = telephone;
        this.role = "Acheteur";
    }

    public Acheteur(){
        this.role = "Acheteur";
    }
}
