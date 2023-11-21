import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Scanner;

public class AfficherCatalogue {
    Produit[] catalogue;
    Acheteur acheteur;// on prend l'acheteur qui regarde les produits pour vérifier ce qu'il a like

    AfficherCatalogue(Acheteur a) {
        acheteur = a;
        catalogue = fetchCatalogue();
        HashMap<Integer, Produit> produitHashMap = new HashMap<>();
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
        System.out.println("Liste de produits disponibles à l'achat:");
        for (int i = 0; i < catalogue.length; i++) {
            Produit p = catalogue[i];
            produitHashMap.put((i+1), p);

            System.out.println("Produit " + (i+1) + ":");
            System.out.println(p.nom);
            System.out.println(p.description);
            System.out.println(decimalFormat.format(p.prix/100.0) + "$");

        }

        System.out.println("Quel produit voulez-vous regarder?");
        System.out.println("Faites 0 pour quitter.");

        Scanner in = new Scanner(System.in);

        String inp = in.nextLine();

        while(true) {
            try {
                int num = Integer.parseInt(inp);

                if (num == 0) {
                    in.close();
                    break;// quitter le menu
                } else {
                    Produit p = produitHashMap.get(num);
                    if (p != null) {
                        p.afficher();
                    } else {
                        System.out.println("Choix non comprix, quel produit voulez-vous regarder?");
                    }
                }

            } catch (NumberFormatException nfe) {
                System.out.println("Choix non comprix, quel produit voulez-vous regarder?");
            }
        }
    }

    private Produit[] fetchCatalogue(){
        //verifier la base de donnees, mais en attendant on en invente

        Revendeur revendeur = new Revendeur();
        String[] liensMedia = null;
        Produit p1 = new Produit(TypeProduit.LIVRES_ET_MANUELS, 1, 5800, "Calculus 1", "Livre de calcul intégral Stewart 2019", revendeur, 10, 5, liensMedia);
        Produit p2 = new Produit(TypeProduit.RESSOURCES_APPRENTISSAGE, 2, 3000, "Physique 3 cheneliere", "abonnement à la plateforme en ligne de cheneliere pour le cours de physique moderne", revendeur, 10, 5, liensMedia);
        Produit p3 = new Produit(TypeProduit.PAPETERIES, 8, 3, "feuille a4", "feuille de papier seule, de format a4.", revendeur, 10, 5, liensMedia);
        Produit p4 = new Produit(TypeProduit.MATERIEL_INFORMATIQUE, 4, 20000, "Razer Death Adder", "Souris de bureau pour étudier", revendeur, 10, 5, liensMedia);

        return new Produit[]{p1, p2, p3, p4};
    }
}