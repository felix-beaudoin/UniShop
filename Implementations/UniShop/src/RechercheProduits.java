import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Scanner;

public class RechercheProduits {
    Acheteur a;

    RechercheProduits(Acheteur a){
       this.a = a;

       System.out.println("Mots clés à rechercher:");
       Scanner in = new Scanner(System.in);
       DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");

       Produit[] produits = recherche(in.nextLine());
       HashMap<Integer, Produit> produitHashMap = new HashMap<>();

       System.out.println("Résultat de la recherche:");
       for (int i = 0; i < produits.length; i++) {
           Produit p = produits[i];
           produitHashMap.put((i+1), p);

           System.out.println("Produit " + (i+1) + ":");
           System.out.println(p.nom);
           System.out.println(p.description);
           System.out.println(decimalFormat.format(p.prix/100.0) + "$");

        }



    }

    private Produit[] recherche(String motcle) {
        //verifier la base de donnees, mais en attendant on en invente

        /*
        Produit p1 = new Produit(TypeProduit.LIVRES_ET_MANUELS, 5800, "Calculus 1", "Livre de calcul intégral Stewart 2019", new Revendeur());
        Produit p2 = new Produit(TypeProduit.RESSOURCES_APPRENTISSAGE, 3000, "Physique 3 cheneliere", "abonnement à la plateforme en ligne de cheneliere pour le cours de physique moderne", new Revendeur());
        Produit p3 = new Produit(TypeProduit.PAPETERIES, 8, "feuille a4", "feuille de papier seule, de format a4.", new Revendeur());
        Produit p4 = new Produit(TypeProduit.MATERIEL_INFORMATIQUE, 0, 20000, "Razer Death Adder", "Souris de bureau pour étudier", new Revendeur(), 1, 0, "");

        return new Produit[]{p1, p2, p3, p4};*/
        return null;
    }

}
