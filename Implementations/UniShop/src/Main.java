import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Bienvenue sur UniShop, votre plateforme d'achats et de reventes en ligne pour étudiants.");

        Authentification auth = new Authentification();
        Acheteur a = auth.accueil();

        System.out.println("Que voulez-vous faire?");
        System.out.println("1- Afficher le catalogue des produits");
        System.out.println("2- Rechercher un produit particulier");
        System.out.println("0- Quitter");

        Scanner in = new Scanner(System.in);
        String inp = in.nextLine();

        boolean accueilLoop = true;
        while (accueilLoop) {
            try {
                int n = Integer.parseInt(inp);

                switch (n) {
                    case 1: AfficherCatalogue afficherCatalogue = new AfficherCatalogue(a);
                    case 2: RechercheProduits rechercheProduits = new RechercheProduits(a);
                }

            } catch (NumberFormatException nfe){
                System.out.println("Choix non comprix, que voulez-vous faire?");
            }
        }




    }


}