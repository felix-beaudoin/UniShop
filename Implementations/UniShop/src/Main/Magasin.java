package Main;

import java.util.*;

public class Magasin extends Authentification {
    Acheteur acheteur;
    Produit[] catalogue;
    private List<Produit> panier = new ArrayList<>();
   private HashMap<String,List<Produit>> commande = new HashMap();
    String[] liensMedia;

    Magasin(Acheteur a){
        acheteur = a;
    }
    private int total;
    private int point;

    public HashMap<String,List<Produit>> getCommande() {
        return commande;
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

    public void menuMagasin() {
        Scanner sc = new Scanner(System.in);
        boolean repeat = true;
        while (repeat) {

            System.out.println("Panier d'achat");
            System.out.println("Sélectionnez l'option désirée:");
            System.out.println("1. Ajouter produit(s)");
            System.out.println("2. Retirer produit(s)");
            System.out.println("3. Voir le panier");
            System.out.println("4. Passer une commande");
            System.out.println("5. Gérer les commandes");
            System.out.println("Vous avez un total de " + getTotal()/100.0 + "$ à payer, ce qui vous donnera " + getPoint() + " point(s).");
            System.out.println("0. Retour");

            String input = sc.nextLine();

            try {

                int option = Integer.parseInt(input);

                switch (option) {
                    case 1:
                        ajouterProduit(); repeat = false; break;
                    case 2:
                        retirerProduit(); repeat = false; break;
                    case 3:
                        voirPanier(); repeat = false; break;
                    case 4:
                        if(getTotal() == 0){
                            System.out.println("!!!Le panier est vide, ajouter des produits avant de commander!!!");
                            System.out.println();
                            menuMagasin();
                        }
                        passerCommande(); repeat = false; break;
                    case 5:
                        menuGestion(); repeat = false; break;
                    case 0:
                        connexionAcheteur(acheteur);repeat = false;  break;
                    default:
                        System.out.println("!!!Option invalide. Veuillez entrer un nombre entier entre 0 et 3!!!");
                }

            } catch (NumberFormatException nfe) {
                System.out.println("!!!Entrée invalide. Veuillez entrer un nombre entier!!!");
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
        try {
            int input = sc.nextInt();
            Produit p = catalogue[input];
            setTotal(getTotal() + p.prix);
            setPoint(getPoint() + p.pointsBonus);
            System.out.println("=============================================================");
            System.out.println("Vous avez ajouté: " + p.nom + " (" + p.description + ")");
            System.out.println("=============================================================");
            panier.add(p);
            System.out.println();
            menuMagasin();
        }
        catch (InputMismatchException ime) {
            System.out.println("!!!Entrée invalide. Veuillez entrer un nombre entier!!!");
            sc.nextLine();
            ajouterProduit();
        } catch (ArrayIndexOutOfBoundsException aioobe) {
            System.out.println("!!!Indice invalide. Veuillez choisir un indice valide!!!");
            ajouterProduit();
        }

    }

    private void retirerProduit() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Quel produit voulez-vous retirer?");
        if (panier.size() == 0) {
            System.out.println("Le panier est vide");
            menuMagasin();
        } else {
            for (int i = 0; i < panier.size(); i++) {
                System.out.println(i + ". " + panier.get(i).nom);
            }
            try {
                int input = sc.nextInt();
                Produit p = panier.get(input);
                setTotal(getTotal() - p.prix);
                setPoint(getPoint() - p.pointsBonus);
                panier.remove(input);
                System.out.println("======================================");
                System.out.println("Vous avez retiré: " + p.nom);
                System.out.println("======================================");
                System.out.println();
                menuMagasin();
            }
            catch (InputMismatchException ime) {
                System.out.println("!!!Entrée invalide. Veuillez entrer un nombre entier!!!");
                sc.nextLine();
                retirerProduit();
            } catch (IndexOutOfBoundsException ioobe) {
                System.out.println("!!!Indice invalide. Veuillez choisir un indice valide!!!");
                retirerProduit();
            }

        }
    }

    private void voirPanier() {
        if (panier.size() == 0) {
            System.out.println("Le panier est vide");
        } else {
            System.out.println("Le panier contient:");
            System.out.println("===================================================================");
            for (int i = 0; i < panier.size(); i++) {
                Produit produit = panier.get(i);
                System.out.println(i+1 + ". " + produit.nom + " (" + produit.description + "), " + produit.prix/100.0 + "$");
            }
            System.out.println("===================================================================");
        }
        System.out.println();
        menuMagasin();
    }
    private void passerCommande(){

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
                            menuMagasin();break;
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
            for (int i = 0; i < panier.size(); i++) {
                System.out.println(i+1 + ". " + panier.get(i).nom + " (" +panier.get(i).description + ").");
                setTotal(getTotal() + panier.get(i).prix);
                setPoint(getPoint() + panier.get(i).pointsBonus);
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
        String code = generateRandomString();
        System.out.println("======================================");
        System.out.println("Votre commande a été confirmée,");
        System.out.println("Consultez l'état de votre commande avec le code suivant: " + code);
        System.out.println("======================================");
        List<Produit> panierActuel = panier;
        System.out.println(panierActuel);
        commande.put(code,panierActuel);
        panier.clear();
        setPoint(0);
        setTotal(0);


        acheteur.Notifications.push("Votre commande a été confirmée, consultez l'état de votre commande avec le code suivant: " + code);
    }
    public void menuGestion(){
        Scanner sc = new Scanner(System.in);
        catalogue = fetchCatalogue();

        boolean repeat = true;
        while (repeat) {

            System.out.println("Gestion des commandes");
            System.out.println("Présentez le code unique associé à la commande.");
            String code = sc.nextLine();
            if(!commande.containsKey(code)){
             System.out.println("!!!Le code saisi n'est pas associé à une commande!!!");
             menuGestion();
             }
            List<Produit> commandeActuelle = commande.get(code);
            System.out.println();
            System.out.println("Sélectionnez l'option désirée pour la commande " + code + ":");
            System.out.println("1. Vérifier l'état de la commande");
            System.out.println("2. Effectuer un échange");
            System.out.println("3. Effectuer un retour");
            System.out.println("4. Annuler la commande");
            System.out.println("5. Confirmer la réception de la commande");
            System.out.println("6. Signaler une commande");
            System.out.println("0. Retour");

            String input = sc.nextLine();

            try {

                int option = Integer.parseInt(input);

                switch (option) {
                    case 1:
                        repeat=false;  etatCommande(code);break;
                    case 2:
                        repeat=false; echangeCommande(code,commandeActuelle);break;
                    case 3:
                        repeat=false;retourCommande(code,commandeActuelle);break;
                    case 4:
                        repeat=false;annulerCommande(code,commandeActuelle);break;
                    case 5:
                        repeat=false;confirmerReception(code);break;
                    case 6:
                        repeat = false;
                        signalerCommande(code);
                    case 0:
                        repeat=false; menuMagasin();break;
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
        menuGestion();

    }
    public void echangeCommande(String code,List<Produit> commande){
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("Quel produit(s) voulez-vous échanger?");
            System.out.println("======================================");
            for (int i = 0; i < commande.size(); i++) {
                System.out.println(i + ". " + commande.get(i).nom + " (" + commande.get(i).description + "), " + commande.get(i).prix / 100.0 + "$.");
            }
            System.out.println("======================================");
            int input = sc.nextInt();
            Produit produitE = commande.get(input);
            UUID uuid = UUID.randomUUID();
            System.out.println("Quel produit(s) voulez-vous recevoir en échange?");
            System.out.println("======================================");
            for (int i = 0; i < commande.size(); i++) {
                System.out.println(i + ". " + commande.get(i).nom + " (" + commande.get(i).description + "), " + commande.get(i).prix / 100.0 + "$.");
            }
            System.out.println("======================================");
            int in = sc.nextInt();
            Produit produitR = commande.get(in);
            int difference = produitE.prix - produitR.prix;
            if (difference < 0) {
                System.out.println("======================================");
                System.out.println("Vous devez une différence de " + (-difference / 100.0) + "$");
                System.out.println("Suivez l'état de la livraison avec le code suivant: " + code);
                System.out.println("Retournez le produit à échanger à l'address suivante: 3200, rue Jean-Brillant ");
                System.out.println("La demande sera annulée après 30 jours si le produit n'est pas reçu, merci.");
                System.out.println("======================================");
                acheteur.Notifications.push("Le produit " + produitE.nom + " est en cours d'échange.");
                menuGestion();
            } else if (difference > 0) {
                System.out.println("======================================");
                System.out.println("Un montant de " + "100$ need to change" + " a été remboursé à votre carte!");
                System.out.println("Suivez l'état de la livraison avec le code suivant: " + code);
                System.out.println("Retournez le produit à échanger à l'address suivante: 3200, rue Jean-Brillant ");
                System.out.println("La demande sera annulée après 30 jours si le produit n'est pas reçu, merci.");
                System.out.println("======================================");
                acheteur.Notifications.push("Le produit " + produitE.nom + " est en cours d'échange.");

                menuGestion();
            } else {
                System.out.println("======================================");
                System.out.println("Vous n'avez pas de différence à payer.");
                System.out.println("Suivez l'état de la livraison avec le code suivant: " + code);
                System.out.println("Retournez le produit à échanger à l'address suivante: 3200, rue Jean-Brillant ");
                System.out.println("La demande sera annulée après 30 jours si le produit n'est pas reçu, merci.");
                System.out.println("======================================");
                acheteur.Notifications.push("Le produit " + produitE.nom + " est en cours d'échange.");

                menuGestion();
            }
        }
        catch (InputMismatchException ime) {
            System.out.println("!!!Entrée invalide. Veuillez entrer un nombre entier!!!");
            sc.nextLine();
            echangeCommande(code, commande);
        }catch (ArrayIndexOutOfBoundsException aioobe) {
            System.out.println("!!!Indice invalide. Veuillez choisir un indice valide!!!");
            echangeCommande(code, commande);
        }
    }

    public void retourCommande(String code,List<Produit> commande) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("Quel produit(s) voulez-vous retourner??");
            System.out.println("======================================");
            for (int i = 0; i < commande.size(); i++) {
                System.out.println(i + ". " + commande.get(i).nom + " (" + commande.get(i).description + "), " + commande.get(i).prix / 100.0 + "$.");
            }
            System.out.println("======================================");
            int input = sc.nextInt();
            Produit produitE = commande.get(input);


            UUID uuid = UUID.randomUUID();
            System.out.println("======================================");
            System.out.println("Un montant de " + produitE.prix / 100.0 + "$ sera remboursé à votre carte.");
            System.out.println("Suivez l'état du retour avec le code suivant: " + code);
            System.out.println("Retournez le produit à l'address suivante: 3200, rue Jean-Brillant ");
            System.out.println("La demande sera annulée après 30 jours si le produit n'est pas reçu, merci.");
            System.out.println("======================================");

            acheteur.Notifications.push("Le produit " + produitE.nom + " a été retourné.");

            // misAjourInventaireR(produitE); mis à jour de l'inventaire du revendeur
            menuGestion();
        }
        catch (InputMismatchException ime) {
            System.out.println("!!!Entrée invalide. Veuillez entrer un nombre entier!!!");
            sc.nextLine();
            retourCommande(code, commande);
        }catch (ArrayIndexOutOfBoundsException aioobe) {
            System.out.println("!!!Indice invalide. Veuillez choisir un indice valide!!!");
            retourCommande(code, commande);
        }
    }
    public void annulerCommande(String code,List<Produit> commandeActuelle){
        Scanner sc = new Scanner(System.in);

        System.out.println("Annuler la commande contennant?:");
        System.out.println("======================================");
        for (int i = 0; i < commandeActuelle.size(); i++) {
            System.out.println(i+1 + ". " + commandeActuelle.get(i).nom + " (" +commandeActuelle.get(i).description + ").");
        }
        System.out.println("======================================");
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
                        System.out.println("Un montant de " + "fix price" + " a été remboursé à votre carte!");
                        System.out.println("======================================");
                        acheteur.Notifications.push("La commande " + code + " a été annulée.");

                        menuGestion();break;
                    case 2:
                        menuGestion();break;
                    default:
                        System.out.println("!!!Option invalide. Veuillez entrer 1 ou 2!!!");
                }

            } catch (NumberFormatException nfe) {
                System.out.println("!!!Entrée invalide. Veuillez entrer un nombre entier!!!");
            }
        }
    }
    public void confirmerReception(String code){
        Scanner sc = new Scanner(System.in);
        System.out.println("Avez-vous reçu la commande: " + code + "?");
        boolean repeat = true;
        while(repeat){

            System.out.println("1. Oui (Confirmer)");
            System.out.println("2. Non");

            String input = sc.nextLine();
            try {

                int option = Integer.parseInt(input);

                switch (option) {
                    case 1:
                        System.out.println("La commande a été livré");
                        System.out.println();
                        //commande.etat = "livré";
                        acheteur.Notifications.push("L'état de la commande a été changé à --> livré");
                        menuGestion();break;
                    case 2:
                        menuGestion();break;
                    default:
                        System.out.println("!!!Option invalide. Veuillez entrer 1 ou 2!!!");
                }

            } catch (NumberFormatException nfe) {
                System.out.println("!!!Entrée invalide. Veuillez entrer un nombre entier!!!");
            }
        }
    }
    public static String generateRandomString() {

        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        Random random = new Random();

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            int randomIndex = random.nextInt(characters.length());

            stringBuilder.append(characters.charAt(randomIndex));
        }

        return stringBuilder.toString();
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
    public void signalerCommande(String code){
        Billet signalement = new Billet(acheteur,"", "", code, "");
        System.out.println("Veuillez décrire votre problème:");
        Scanner sc = new Scanner(System.in);
        String probleme = sc.nextLine();
        signalement.setProbleme(probleme);
        System.out.println("Merci d'avoir entré votre problème, nous vous contacterons sous peu.");

    }

}   