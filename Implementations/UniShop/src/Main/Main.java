package Main;

public class Main {
    public static void main(String[] args) {
        System.out.println("Bienvenue sur UniShop, votre plateforme d'achats et de reventes en ligne pour étudiants.");

        Authentification auth = new Authentification();
        auth.accueil();
    }
}