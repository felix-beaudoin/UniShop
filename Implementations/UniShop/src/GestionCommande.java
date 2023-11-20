import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.UUID;

public class GestionCommande extends Authentification {
    Acheteur acheteur;
    Produit[] catalogue;
    String[] liensMedia;
    private int prixTotal;

    GestionCommande(Acheteur a){
        acheteur = a;
    }
    public void setTotal(int newTotal) {
        this.prixTotal = newTotal;
    }

    public int getTotal() {
        return prixTotal;
    }

    public void menu(){
        Scanner sc = new Scanner(System.in);
        catalogue = fetchCatalogue();
        boolean repeat = true;
        while (repeat) {

            System.out.println("Gestion des commandes");
            System.out.println("Présentez le code unique associé à la commande.");
            String code = sc.nextLine();

            System.out.println();
            System.out.println("Sélectionnez l'option désirée pour la commande " + code + ":");
            System.out.println("1. Vérifier l'état de la commande");
            System.out.println("2. Effectuer un échange");
            System.out.println("3. Effectuer un retour");
            System.out.println("4. Annuler la commande");
            System.out.println("0. Retour");

            String input = sc.nextLine();

            try {

                int option = Integer.parseInt(input);

                switch (option) {
                    case 1:
                        repeat=false;  etatCommande(code);break;
                    case 2:
                        repeat=false; echangeCommande(code,catalogue);break;
                    case 3:
                        repeat=false;retourCommande(code,catalogue);break;
                    case 4:
                        repeat=false;annulerCommande(code);break;
                    case 0:
                        repeat=false;   connexionAcheteur(acheteur);break;
                    default:
                        System.out.println("!!!Option invalide. Veuillez entrer un nombre entier entre 0 et 4!!!");
                }

            } catch (NumberFormatException nfe) {
                System.out.println("!!!Entrée invalide. Veuillez entrer un nombre entier!!!");
            }
        }
    }
    public void etatCommande(String code){

        System.out.println("======================================");
        System.out.println("État de la commande (" + code + "): En production");
        System.out.println("Date de livraison estimée: 18/12/23");
        System.out.println("======================================");
        menu();

    }
    public void echangeCommande(String code,Produit[] panier){
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("Quel produit(s) voulez-vous échanger?");
            System.out.println("======================================");
            for (int i = 0; i < panier.length; i++) {
                System.out.println(i + ". " + panier[i].nom + " (" + panier[i].description + "), " + panier[i].prix / 100.0 + "$.");
            }
            System.out.println("======================================");
            int input = sc.nextInt();
            Produit produitE = panier[input];
            UUID uuid = UUID.randomUUID();
            System.out.println("Quel produit(s) voulez-vous recevoir en échange?");
            System.out.println("======================================");
            for (int i = 0; i < panier.length; i++) {
                System.out.println(i + ". " + panier[i].nom + " (" + panier[i].description + "), " + panier[i].prix / 100.0 + "$.");
            }
            System.out.println("======================================");
            int in = sc.nextInt();
            Produit produitR = panier[in];
            int difference = produitE.prix - produitR.prix;
            if (difference < 0) {
                System.out.println("======================================");
                System.out.println("Vous devez une différence de " + (-difference / 100.0) + "$");
                System.out.println("Suivez l'état de la livraison avec le code suivant: " + uuid);
                System.out.println("Retournez le produit à échanger à l'address suivante: 3200, rue Jean-Brillant ");
                System.out.println("La demande sera annulée après 30 jours si le produit n'est pas reçu, merci.");
                System.out.println("======================================");
                menu();
            } else if (difference > 0) {
                System.out.println("======================================");
                System.out.println("Un montant de " + prixTotal + " a été remboursé à votre carte!");
                System.out.println("Suivez l'état de la livraison avec le code suivant: " + uuid);
                System.out.println("Retournez le produit à échanger à l'address suivante: 3200, rue Jean-Brillant ");
                System.out.println("La demande sera annulée après 30 jours si le produit n'est pas reçu, merci.");
                System.out.println("======================================");
                menu();
            } else {
                System.out.println("======================================");
                System.out.println("Vous n'avez pas de différence à payer.");
                System.out.println("Suivez l'état de la livraison avec le code suivant: " + uuid);
                System.out.println("Retournez le produit à échanger à l'address suivante: 3200, rue Jean-Brillant ");
                System.out.println("La demande sera annulée après 30 jours si le produit n'est pas reçu, merci.");
                System.out.println("======================================");
                menu();
            }
        }
        catch (InputMismatchException ime) {
            System.out.println("!!!Entrée invalide. Veuillez entrer un nombre entier!!!");
            sc.nextLine();
            echangeCommande(code, panier);
        }catch (ArrayIndexOutOfBoundsException aioobe) {
            System.out.println("!!!Indice invalide. Veuillez choisir un indice valide!!!");
            echangeCommande(code, panier);
        }
    }

    public void retourCommande(String code,Produit[] panier) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("Quel produit(s) voulez-vous retourner??");
            System.out.println("======================================");
            for (int i = 0; i < panier.length; i++) {
                System.out.println(i + ". " + panier[i].nom + " (" + panier[i].description + "), " + panier[i].prix / 100.0 + "$.");
            }
            System.out.println("======================================");
            int input = sc.nextInt();
            Produit produitE = panier[input];


            UUID uuid = UUID.randomUUID();
            System.out.println("======================================");
            System.out.println("Un montant de " + produitE.prix / 100.0 + "$ sera remboursé à votre carte.");
            System.out.println("Suivez l'état du retour avec le code suivant: " + uuid);
            System.out.println("Retournez le produit à l'address suivante: 3200, rue Jean-Brillant ");
            System.out.println("La demande sera annulée après 30 jours si le produit n'est pas reçu, merci.");
            System.out.println("======================================");
            // misAjourInventaireR(produitE); mis à jour de l'inventaire du revendeur
            menu();
        }
        catch (InputMismatchException ime) {
            System.out.println("!!!Entrée invalide. Veuillez entrer un nombre entier!!!");
            sc.nextLine();
            retourCommande(code, panier);
        }catch (ArrayIndexOutOfBoundsException aioobe) {
            System.out.println("!!!Indice invalide. Veuillez choisir un indice valide!!!");
            retourCommande(code, panier);
        }
    }
    public void annulerCommande(String code){
        Scanner sc = new Scanner(System.in);
        System.out.println("Annuler la commande pour: "+ catalogue[0].nom + " ("+ catalogue[0].description + ")"+ "?");
        boolean repeat = true;
        while(repeat){

            System.out.println("1. Confirmer");
            System.out.println("2. Retour");

            String input = sc.nextLine();
            try {

                int option = Integer.parseInt(input);

                switch (option) {
                    case 1:
                        System.out.println("======================================");
                        System.out.println("La commande " + code + " a été annulée!");
                        System.out.println("Un montant de " + catalogue[0].prix/100.0 + " a été remboursé à votre carte!");
                        System.out.println("======================================");
                        menu();break;
                    case 2:
                        menu();break;
                    default:
                        System.out.println("!!!Option invalide. Veuillez entrer 1 ou 2!!!");
                }

            } catch (NumberFormatException nfe) {
                System.out.println("!!!Entrée invalide. Veuillez entrer un nombre entier!!!");
            }
        }
    }
    private Produit[] fetchCatalogue(){ //pris de AfficherCatalogue pour tester méthode d'ajout.
        //verifier la base de donnees, mais en attendant on en invente

        Revendeur revendeur = new Revendeur();
        Produit p1 = new Produit(TypeProduit.LIVRES_ET_MANUELS, 1, 5800, "Calculus 1", "Livre de calcul intégral Stewart 2019", revendeur, 10, 5,liensMedia);
        Produit p2 = new Produit(TypeProduit.RESSOURCES_APPRENTISSAGE, 2, 3000, "Physique 3 cheneliere", "abonnement à la plateforme en ligne de cheneliere pour le cours de physique moderne", revendeur, 10, 5,liensMedia);
        Produit p3 = new Produit(TypeProduit.PAPETERIES, 8, 3, "feuille a4", "feuille de papier seule, de format a4.", revendeur, 10, 5,liensMedia);
        Produit p4 = new Produit(TypeProduit.MATERIEL_INFORMATIQUE, 4, 20000, "Razer Death Adder", "Souris de bureau pour étudier", revendeur, 10, 5,liensMedia);

        return new Produit[]{p1, p2, p3, p4};
    }
}
