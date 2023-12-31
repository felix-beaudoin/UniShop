package Main;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class Acheteur {

    String nom, prenom, pseudo, email, adresse, role, telephone;
    int points;
    ArrayList<String> RevendeurLike = new ArrayList<String>();
    Stack<String> Notifications = new Stack<String>();
    ArrayList<String> UtilisateurSuivi = new ArrayList<String>();
    ArrayList<String> SuiviPar = new ArrayList<String>();

    public Acheteur(String nom, String prenom, String pseudo, String email, String adresse, String telephone) {
        this.nom = nom;
        this.prenom = prenom;
        this.pseudo = pseudo;
        this.email = email;
        this.adresse = adresse;
        this.telephone = telephone;
        this.role = "Acheteur";
        points = 0;

    }
    
     public void getListeAcheteur(LinkedList<Acheteur> listeAcheteur) {
        if (!listeAcheteur.isEmpty()) {
            System.out.println("Voici la liste des acheteurs");
            for (Acheteur acheteur : listeAcheteur) {
                System.out.println(acheteur.getPrenom() + " " + acheteur.getNom());
            }
        } else {
            System.out.println("La liste des acheteurs est vide.");
        }
    }
    

    public void getProfilAcheteur(LinkedList<Acheteur> listeAcheteur){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Entrez le pseudonyme : ");
        String userName = scanner.nextLine();
        boolean found = false;
        for (Acheteur acheteur : listeAcheteur) {
            if (acheteur.getPseudo().equals(userName)) {
                System.out.println("Nom: " + acheteur.getNom());
                System.out.println("Prenom: " + acheteur.getPrenom());
                System.out.println("Adresse: " + acheteur.getAdresse());
                System.out.println("Courriel: " + acheteur.getEmail());
                System.out.println("Telephone: " + acheteur.getTelephone());
                System.out.println("Role: " + acheteur.getRole());
                System.out.println("Points: " + acheteur.getPoints());
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Acheteur non trouvé.");
        }
    }
    public void afficherNotifications(Acheteur a){
        if(!a.Notifications.isEmpty()){
            System.out.println("Voici vos notifications : ");
            for (String notification : a.Notifications) {
                System.out.println(notification);
            }
        } else {
            System.out.println("Vous n'avez pas de notifications.");
        }
    }
    public void gererSuiveur(){
        System.out.println("Voici la liste des utilisateurs qui vous suivent:");
        if(SuiviPar.size() == 0){
            System.out.println("Aucun utilisateur vous suit");
        }
        else {
            for (int i = 0; i < SuiviPar.size(); i++){
                System.out.println(i+1 + ". " + SuiviPar.get(i));
            }
        }
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getPseudo() {
        return pseudo;
    }

    public String getEmail() {
        return email;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getRole() {
        return role;
    }

    public String getTelephone() {
        return telephone;
    }

    public int getPoints() {
        return points;
    }

    public Acheteur(){
        this.role = "Acheteur";
    }

    @Override
        public String toString() {
            return "Acheteur{" +
                    "nom='" + nom + '\'' +
                    ", prenom='" + prenom + '\'' +
                    ", pseudo='" + pseudo + '\'' +
                    ", email='" + email + '\'' +
                    ", adresse='" + adresse + '\'' +
                    ", role='" + role + '\'' +
                    ", telephone='" + telephone + '\'' +
                    ", points=" + points +
                    '}';
        }

}
