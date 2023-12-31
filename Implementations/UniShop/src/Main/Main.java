package Main;

public class Main {
    public static void main(String[] args) {

        //var a = new RevendeurRepo();

        //a.put(new Revendeur("Michel", "Maison", "mi@ch.el", "514-514-5145", null));
        System.out.println("Bienvenue sur UniShop, votre plateforme d'achats et de reventes en ligne pour étudiants.");

        Authentification auth = new Authentification();
        auth.accueil();
    }
}