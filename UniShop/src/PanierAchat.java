import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class PanierAchat {
    Acheteur acheteur;
    Produit[] catalogue;
    private int total;
    private int point;

    List<Produit> panier = new ArrayList<Produit>();

    PanierAchat(Acheteur a){
        acheteur = a;
    }
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
        Scanner sc = new Scanner(System.in);
        boolean repeat = true;
        while (repeat) {

            System.out.println("Panier d'achat");
            System.out.println("Sélectionnez l'option désirée:");
            System.out.println("1. Ajouter produit(s)");
            System.out.println("2. Retirer produit(s)");
            System.out.println("3. Voir le panier");
            System.out.println("Vous avez un total de " + getTotal()/100 + "$ à payer, ce qui vous donnera " + getPoint() + " point(s).");
            System.out.println("0. Retour");

            String input = sc.nextLine();

            try {

                int option = Integer.parseInt(input);

                switch (option) {
                    case 1:
                        ajouterProduit();break;
                    case 2:
                        retirerProduit();break;
                    case 3:
                        voirPanier();break;
                    case 0:
                        repeat = false;break;
                    default:
                        System.out.println("Option non comprise. Veuillez réessayer.");
                }

            } catch (NumberFormatException nfe) {
                System.out.println("Option non comprise. Veuillez réessayer.");
            }
        }
    }

    private void ajouterProduit() {
        Scanner sc = new Scanner(System.in);
        catalogue = fetchCatalogue();
        System.out.println("Quel produit voulez vous ajouter au panier?");
        for(int i = 0; i < catalogue.length; i++){
            Produit p = catalogue[i];
            System.out.println(i + ". " + p.nom + " (" + p.description + ")" + " ,prix: " + p.prix/100.0 + "$ ,point(s): " + p.pointsBonus );
        }
        int input = sc.nextInt();
        Produit p = catalogue[input];
        setTotal(getTotal() + p.prix);
        setPoint(getPoint() + p.pointsBonus);
        System.out.println("Vous avez ajouté " + p.nom  + " .");
        panier.add(p);
        panierAchat();

    }

    private void retirerProduit() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Quel produit voulez-vous retirer?");
        if (panier.size() == 0) {
            System.out.println("Le panier est vide");
            panierAchat();
        } else {
            for (int i = 0; i < panier.size(); i++) {
                System.out.println(i + ". " + panier.get(i).nom);
            }

            int input = sc.nextInt();
            Produit p = panier.get(input);
            setTotal(getTotal() - p.prix);
            setPoint(getPoint() - p.pointsBonus);
            panier.remove(input);
            System.out.println("Vous avez retiré " + p.nom + " .");
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
                System.out.println(i+1 + ". " + panier.get(i));
            }
            System.out.println("Vous avez un total de " + getTotal()/100.0 + "$ à payer, ce qui vous donnera " + getPoint() + " point(s).");
            panierAchat();
        }
    }
    private Produit[] fetchCatalogue(){ //pris de AfficherCatalogue pour tester méthode d'ajout.
        //verifier la base de donnees, mais en attendant on en invente

        Revendeur revendeur = new Revendeur();
        Produit p1 = new Produit(TypeProduit.LIVRES_ET_MANUELS, 1, 5800, "Calculus 1", "Livre de calcul intégral Stewart 2019", revendeur, 10, 5);
        Produit p2 = new Produit(TypeProduit.RESSOURCES_APPRENTISSAGE, 2, 3000, "Physique 3 cheneliere", "abonnement à la plateforme en ligne de cheneliere pour le cours de physique moderne", revendeur, 10, 5);
        Produit p3 = new Produit(TypeProduit.PAPETERIES, 8, 3, "feuille a4", "feuille de papier seule, de format a4.", revendeur, 10, 5);
        Produit p4 = new Produit(TypeProduit.MATERIEL_INFORMATIQUE, 4, 20000, "Razer Death Adder", "Souris de bureau pour étudier", revendeur, 10, 5);

        return new Produit[]{p1, p2, p3, p4};
    }
}
