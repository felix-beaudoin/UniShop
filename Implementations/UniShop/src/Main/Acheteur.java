package Main;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class Acheteur {

    String nom, prenom, pseudo, email, adresse, role, telephone;
    int points;

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
     public void getListeAcheteur(Map<String, Acheteur> ListeAcheteur){
        if(!ListeAcheteur.isEmpty()){
            System.out.println("Voici la liste des acheteurs");
            for (Map.Entry<String, Acheteur> entry : ListeAcheteur.entrySet()) {
                Acheteur acheteur = entry.getValue();
                System.out.println(acheteur.getPrenom() + " " + acheteur.getNom());
            }
        } else {
            System.out.println("La liste des acheteurs est vide.");
        }
    
        
    }

    public void getProfilAcheteur(Map<String, Acheteur> ListeAcheteur){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Entrez le pseudonyme : ");
        String userName = scanner.nextLine();
        if(ListeAcheteur.containsKey(userName)){
            Acheteur acheteur = ListeAcheteur.get(userName);
            System.out.println("Nom: " + acheteur.getNom());
            System.out.println("Prenom: " + acheteur.getPrenom());
            System.out.println("Adresse: " + acheteur.getAdresse());
            System.out.println("Courriel: " + acheteur.getEmail());
            System.out.println("Telephone: " + acheteur.getTelephone());
            System.out.println("Role: " + acheteur.getRole());
            System.out.println("Points: " + acheteur.getPoints());
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
