import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PanierAchat {
    private int total = 0;
    private int point = 0;

    List<String> panier = new ArrayList<String>();


    public void setTotal(int newTotal) {
        this.total = newTotal;
    }

    public int getTotal() {
        return total;
    }

    public void setPoint(int newPoint) {
        this.point = newPoint;
    }

    public int getPoint() {
        return point;
    }

    public void panierAchat() {

        boolean repeat = true;
        while (repeat) {

            System.out.println("Panier d'achat");
            System.out.println("Sélectionnez l'option désirée:");
            System.out.println("1. Ajouter produit(s)");
            System.out.println("2. Retirer produit(s)");
            System.out.println("3. Voir le panier");
            System.out.println("Vous avez un total de " + getTotal() + " à payer, ce qui vous donnera " + getPoint() + " point(s).");
            System.out.println("0. Retour");

            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();

            try {

                int option = Integer.parseInt(input);

                switch (option) {
                    case 1:
                        ajouterProduit();
                        repeat = false;
                    case 2:
                        retirerProduit();
                        repeat = false;
                    case 3:
                        voirPanier();
                        repeat = false;
                    case 0:/**accueil();*/
                        repeat = false;
                    default:
                        System.out.println("Option non comprise. Veuillez réessayer.");
                }

            } catch (NumberFormatException nfe) {
                System.out.println("Option non comprise. Veuillez réessayer.");
            }
        }
    }

    private void ajouterProduit() {
        System.out.println("Retrouvez le produit à rajouter");
        panier.add("tests1"); /** produits temporaires pour tester*/
        panier.add("tests2");
        panier.add("tests3");
        panier.add("tests4");
        panier.add("tests5");
        setTotal(500);
        setPoint(25);
        panierAchat();
        /** rechercheProduit() cas à implémenter */
    }

    private void retirerProduit() {
        System.out.println("Quel produit voulez-vous retirer?");
        if (panier.size() == 0) {
            System.out.println("Le panier est vide");
            panierAchat();
        } else {
            for (int i = 0; i < panier.size(); i++) {
                System.out.println(i + ". " + panier.get(i));
            }
            Scanner sc = new Scanner(System.in);
            int input = sc.nextInt();
            String retirer = panier.get(input);
            panier.remove(input);
            System.out.println("Vous avez retiré " + retirer + " .");
            panierAchat();
        }
    }

    private void voirPanier() {
        if (panier.size() == 0) {
            System.out.println("Le panier est vide");
            panierAchat();
        } else {
            System.out.println("Le panier contient:");
            for (int i = 0; i < panier.size(); i++) {
                System.out.println(panier.get(i));
            }
            System.out.println("Vous avez un total de " + getTotal() + " à payer, ce qui vous donnera " + getPoint() + " point(s).");
            panierAchat();
        }
    }
}
