import java.util.*;

public class PasserCommande extends Authentification{
    Acheteur acheteur;
    Produit[] catalogue;
    String[] liensMedia;

    private int prixTotal;
    private int pointTotal;

    public void setTotal(int newTotal) {
        this.prixTotal = newTotal;
    }

    public int getTotal() {
        return prixTotal;
    }

    public void setPoint(int newPoint) {
        this.pointTotal = newPoint;
    }

    public int getPoint() {
        return pointTotal;
    }

    PasserCommande(Acheteur a){
        acheteur = a;

    }
    public void passerCommande(){
        boolean repeat = true;
        while (repeat) {

            System.out.println("Passer une commande");
            System.out.println("Sélectionnez l'option désirée:");
            System.out.println("1. Commander");
            System.out.println("2. Voir information du profil ");
            System.out.println("0. Retour");

            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();

            try {

                int option = Integer.parseInt(input);

                switch (option) {
                    case 1:
                        commande();break;
                    case 2:
                        infoProfil();break;
                    case 0:
                        connexionAcheteur(acheteur);break;
                    default:
                        System.out.println("!!!Option invalide. Veuillez entrer un nombre entier entre 0 et 2!!!");

                }

            } catch (NumberFormatException nfe) {
                System.out.println("!!!Entrée invalide. Veuillez entrer un nombre entier!!!");
            }
        }
    }
    private void commande(){


        boolean repeat = true;
        catalogue = fetchCatalogue();

        while (repeat) {
            System.out.println();
            System.out.println("Votre panier d'achat contient:");
            System.out.println("======================================");
            for (int i = 0; i < catalogue.length; i++) {
                System.out.println(i+1 + ". " + catalogue[i].nom + " (" + catalogue[i].description + ").");
                setTotal(getTotal() + catalogue[i].prix);
                setPoint(getPoint() + catalogue[i].pointsBonus);
            }
            System.out.println("======================================");
            System.out.println("Pour un total de " + getTotal()/100.0 + "$ et " + getPoint() + " points." );
            System.out.println("Livrer à: " + acheteur.adresse + " ?");
            System.out.println("1. Oui");
            System.out.println("2. Non");
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            try {

                int option = Integer.parseInt(input);

                switch (option) {
                    case 1:
                        payer();repeat=false;break;
                    case 2:
                        changerAdress();repeat=false;break;
                    default:
                        System.out.println("!!!Option invalide. Veuillez entrer 1 ou 2!!!");

                }

            } catch (NumberFormatException nfe) {
                System.out.println("!!!Entrée invalide. Veuillez entrer un nombre entier!!!");
            }
        }

    }
    private void infoProfil(){

        boolean repeat = true;

        while (repeat) {
            System.out.println();
            System.out.println("Vos informations personelles pour la commande");
            System.out.println("======================================");
            System.out.println("Prénom: " + acheteur.prenom + "     Nom: " + acheteur.nom);
            System.out.println("Adresse: " + acheteur.adresse + "       Téléphone: " + acheteur.telephone);
            System.out.println("======================================");
            System.out.println();
            System.out.println("Changer l'address de livraison?");
            System.out.println("1. Oui");
            System.out.println("2. Non ");
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();

            try {

                int option = Integer.parseInt(input);

                switch (option) {
                    case 1:
                        changerAdress();break;
                    case 2:
                        passerCommande();break;
                    default:
                        System.out.println("!!!Option invalide. Veuillez entrer 1 ou 2!!!");
                }

            } catch (NumberFormatException nfe) {
                System.out.println("!!!Entrée invalide. Veuillez entrer un nombre entier!!!");
            }
        }

    }

    private void payer(){

        boolean repeat = true;

        while (repeat) {
            Scanner sc = new Scanner(System.in);
            String numero = detailCarte();

            System.out.println("La livraison d'un total de " + getTotal()/100.0 + "$");
            System.out.println("Sera chargé à la carte commençant par " + numero);
            System.out.println("À l'adress: " + acheteur.adresse);
            System.out.println("1. Confirmer");
            System.out.println("2. Retour");

            String input = sc.nextLine();
            try {

                int option = Integer.parseInt(input);

                switch (option) {
                    case 1:
                        confirmation();repeat=false;;break;
                    case 2:
                        commande();repeat=false;break;
                    default:
                        System.out.println("!!!Option invalide. Veuillez entrer 1 ou 2!!!");
                }

            } catch (NumberFormatException nfe) {
                System.out.println("!!!Entrée invalide. Veuillez entrer un nombre entier!!!");
            }
        }
    }
    private String detailCarte(){
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println();
            System.out.println("Entrer les détails de votre carte");
            System.out.println("Numéro de la carte: ");
            String numero = sc.nextLine();

            System.out.println("Code de sécurité de la carte: ");
            int code = sc.nextInt();

            System.out.println("Date d'expiration de la carte: ");
            int date = sc.nextInt();

            return numero;
        }
        catch (InputMismatchException ime) {
            System.out.println("!!!Entrée invalide pour le code/date. Veuillez entrer des valeurs numériques valides!!!");
            sc.nextLine();
            return detailCarte();
        } catch (NoSuchElementException nsee) {
            System.out.println("!!!Impossible de lire les détails de la carte. Veuillez réessayer!!!");
            sc.nextLine();
            return detailCarte();
        }
    }
    private void changerAdress(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Quelle est l'address de livraison?");
        try {
            String address = sc.nextLine();
            System.out.println("======================================");
            System.out.println("L'address a été changé à: " + address);
            System.out.println("======================================");
            System.out.println();
            acheteur.adresse = address;
        }
        catch (InputMismatchException ime) {
            System.out.println("!!!Entrée invalide. Veuillez entrer une adresse valide!!!");
            sc.nextLine();
            changerAdress();
        }
    }
    private void confirmation(){
        UUID uuid = UUID.randomUUID();
        System.out.println("======================================");
        System.out.println("Votre commande a été confirmée,");
        System.out.println("Consultez l'état de votre commande avec le code suivant: " + uuid);
        System.out.println("======================================");
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
