import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Authentification {
    private final Scanner in = new Scanner(System.in);
    private boolean running = true;
    private int produitId = 0;

    public void accueil() {
        Acheteur a = new Acheteur();
        Revendeur r = new Revendeur();

        System.out.println("Sélectionnez l'option désirée:");
        System.out.println("1. S'inscrire en tant qu'acheteur");
        System.out.println("2. S'inscrire en tant que revendeur");
        System.out.println("3. Se connecter en tant qu'acheteur");
        System.out.println("4. Se connecter en tant que revendeur");
        System.out.println("0. Quitter");

        while (running) {

            String inp = in.nextLine();

            try {

                int option = Integer.parseInt(inp);

                switch (option) {
                    case 1: connexionAcheteur(inscriptionAcheteur()); break;
                    case 2: connexionRevendeur(inscriptionRevendeur()); break;
                    case 3: connexionAcheteur(a); break;
                    case 4: connexionRevendeur(r); break;
                    case 0: running = false; break;
                    default: System.out.println("Option invalide. Veuillez réessayer.");
                }

            } catch (NumberFormatException nfe) {
                System.out.println("Option invalide. Veuillez réessayer.");
            }
        }
    }

    public void connexionAcheteur(Acheteur acheteur) {
        if (acheteur.pseudo == null){
            acheteur = new Acheteur("Doe", "John","John123","Johndoe@email.com",
                    "3150 rue Jean-Brillant", "514-123-1234");
        }
        System.out.println("Vous êtes connecté en tant qu'acheteur: " + acheteur.pseudo);
        System.out.println("Sélectionnez l'option désirée:");
        System.out.println("1. Afficher le catalogue");
        System.out.println("2. Afficher le panier d'achat");
        System.out.println("3. Passer une commande");
        System.out.println("4. Gestion de commandes");
        System.out.println("0. Quitter");

        while (running) {

            String inp = in.nextLine();
            try {

                int option = Integer.parseInt(inp);

                switch (option) {
                    case 1:
                        Catalogue(acheteur); break;
                    case 2:
                        Panier(acheteur); break;
                    case 3:
                        Commande(acheteur); break;
                    case 4:
                        Gestion(acheteur); break;
                    case 0:
                        running = false; break;
                    default:
                        System.out.println("Option invalide. Veuillez entrer un nombre entier entre 0 et 4.");
                }
            } catch (NumberFormatException nfe) {
                System.out.println("Entrée invalide. Veuillez entrer un nombre entier.");
            }
        }
    }

    private void connexionRevendeur(Revendeur revendeur) {
        if (revendeur.nom == null){
            List<Produit> listeProduit = new ArrayList<>();
            revendeur = new Revendeur("JohnDoe", "3150 rue Jean-Brillant","Johndoe@email.com",
                    "514-123-1234", listeProduit);
        }

        System.out.println("Vous êtes connecté en tant que revendeur: " + revendeur.nom);
        System.out.println("Sélectionnez l'option désirée:");
        System.out.println("1. Offrir un produit");
        System.out.println("2. Afficher les metriques");
        System.out.println("3. Confirmer la reception d'un retour");
        System.out.println("0. Quitter");

        while (running) {
            try {
                String input = in.nextLine();
                int option = Integer.parseInt(input);

                switch (option) {
                    case 1:
                        OffrirProduit(revendeur);
                        break;
                    case 2:
                        AfficherMetriques(revendeur);
                        break;
                    case 3:
                        ConfirmerReception(revendeur);
                        break;
                    case 0:
                        running = false;
                        break;
                    default:
                        System.out.println("Option invalide. Veuillez entrer un nombre entre 0 et 3.");
                }
            } catch (NumberFormatException nfe) {
                System.out.println("Entrée invalide. Veuillez entrer un nombre entier.");
            }
        }
    }

    private Acheteur inscriptionAcheteur() {
        System.out.println("======================================");
        System.out.println("Inscription Acheteur");
        System.out.println("======================================");
        System.out.println();
        System.out.println("Quel est votre nom?");
        String nom = in.nextLine();

        System.out.println("Quel est votre prénom?");
        String prenom = in.nextLine();

        System.out.println("Quel est votre pseudo?");
        String pseudo = in.nextLine();

        while (!pseudoDisponible(pseudo)) {
            System.out.println("Pseudo non disponible, veuillez en choisir un autre.");
            pseudo = in.nextLine();
        }

        System.out.println("Quel est votre email?");
        String email = in.nextLine();

        while (!emailPatternCorrect(email)) {
            System.out.println("Email incorrect, veuillez réessayez.");
            email = in.nextLine();
        }

        System.out.println("Quel est votre adresse de livraison?");
        String adresse = in.nextLine();

        System.out.println("Quel est votre numéro de téléphone? (514-123-1234)");
        String telephone = in.nextLine();
        boolean telephoneLoop = true;
        while(telephoneLoop) {
            if (telephone.matches("\\d{3}-\\d{3}-\\d{4}")) {
                telephoneLoop = false;
            } else {
                System.out.println("Votre numéro doit être dans le format 514-123-1234, veuillez réessayez.");
                telephone = in.nextLine();
            }
        }
        return new Acheteur(nom, prenom, pseudo, email, adresse, telephone);
        //return creerNouveauAcheteur(new Acheteur(nom, prenom, pseudo, email, adresse, telephone));
    }

    private Revendeur inscriptionRevendeur() {
        System.out.println("======================================");
        System.out.println("Inscription Revendeur");
        System.out.println("======================================");
        System.out.println();

        System.out.println("Quel est votre nom?");
        String nom = in.nextLine();

        while (!pseudoDisponible(nom)) {
            System.out.println("Nom non disponible, veuillez en choisir un autre.");
            nom = in.nextLine();
        }

        System.out.println("Quel est votre adresse?");
        String adresse = in.nextLine();

        System.out.println("Quel est votre email?");
        String email = in.nextLine();

        while (!emailPatternCorrect(email)) {
            System.out.println("Email incorrect, veuillez réessayez.");
            email = in.nextLine();
        }

        System.out.println("Quel est votre numéro de téléphone? (514-123-1234)");
        String telephone = in.nextLine();
        boolean telephoneLoop = true;
        while(telephoneLoop) {
            if (telephone.matches("\\d{3}-\\d{3}-\\d{4}")) {
                telephoneLoop = false;
            } else {
                System.out.println("Votre numéro doit être dans le format 514-123-1234, veuillez réessayez.");
                telephone = in.nextLine();
            }
        }
        List<Produit> listeProduit = new ArrayList<>();
        return new Revendeur(nom, adresse, email, telephone, listeProduit);
        //return creerNouveauRevendeur(new Revendeur(nom, adresse, email, telephone, listeProduit));
    }

    private void Catalogue(Acheteur a) {
        AfficherCatalogue afficherCatalogue = new AfficherCatalogue(a);
    }
    private void Panier(Acheteur a){
        PanierAchat panier = new PanierAchat(a);
        panier.panierAchat();
    }
    private void Commande(Acheteur a){
        PasserCommande commande = new PasserCommande(a);
        commande.passerCommande();
    }
    private void Gestion(Acheteur a){
        GestionCommande gestion = new GestionCommande(a);
        gestion.menu();
    }

    private void OffrirProduit(Revendeur revendeur){
        System.out.println("======================================");
        System.out.println("Offrir un produit");
        System.out.println("======================================");
        System.out.println();

        System.out.println("Entrez le titre du produit:");
        String titre = in.nextLine();

        System.out.println("Entrez la description du produit:");
        String description = in.nextLine();

        int quantite = 0;
        while (true) {
            System.out.println("Entrez la quantité initiale du produit:");
            try {
                quantite = Integer.parseInt(in.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Entrée invalide. Veuillez entrer un nombre entier.");
            }
        }

        int prix = 0;
        while (true) {
            System.out.println("Entrez le prix du produit:");
            try {
                prix = Integer.parseInt(in.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Entrée invalide. Veuillez entrer un nombre entier.");
            }
        }

        int pointsBonus = 0;
        while (true) {
            System.out.println("Entrez le nombre de points bonus (optionnel) :");
            try {
                pointsBonus = Integer.parseInt(in.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Entrée invalide. Veuillez entrer un nombre entier.");
            }
        }

        int pointsBase = prix;

        // Le nombre total de points (base + bonus) ne peut pas dépasser 20 points par dollar
        while (pointsBase + pointsBonus > 20 * prix) {
            System.out.println("Le nombre total de points (base + bonus) ne peut pas dépasser 20 points par dollar." +
                    "Veuillez entrer à nouveau le nombre de points bonus.");
            pointsBonus = Integer.parseInt(in.nextLine());
        }

        System.out.println("Voulez-vous ajouter des images et des vidéos ? (O/N) :");
        String reponse = in.nextLine();
        String[] liensMedia = null;
        if (reponse.equalsIgnoreCase("O")) {
            System.out.println("Entrez les liens vers les images et les vidéos (séparés par des virgules) :");
            String mediaLinks = in.nextLine();
            liensMedia = mediaLinks.split(", ");
        }

        int typeInput = 0;
        while (true) {
            System.out.println("Entrez le type du produit:");
            System.out.println("1. LIVRES_ET_MANUELS");
            System.out.println("2. RESSOURCES_APPRENTISSAGE");
            System.out.println("3. PAPETERIES");
            System.out.println("4. MATERIEL_INFORMATIQUE");
            System.out.println("5. EQUIPEMENT_BUREAU");
            try {
                typeInput = Integer.parseInt(in.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Entrée invalide. Veuillez entrer un nombre entier.");
            }
        }

        TypeProduit type = null;
        while (type == null) {
            switch(typeInput) {
                case 1: type = TypeProduit.LIVRES_ET_MANUELS; break;
                case 2: type = TypeProduit.RESSOURCES_APPRENTISSAGE; break;
                case 3: type = TypeProduit.PAPETERIES; break;
                case 4: type = TypeProduit.MATERIEL_INFORMATIQUE; break;
                case 5: type = TypeProduit.EQUIPEMENT_BUREAU; break;
                default:
                    System.out.println("Type non existant. Veuillez réessayer.");
                    typeInput = Integer.parseInt(in.nextLine());
            }
        }

        produitId = produitId+1;
        Produit produit = new Produit(type, produitId, prix, titre, description, revendeur, quantite, pointsBonus, liensMedia);

        revendeur.listeProduit.add(produit);

        System.out.println("Produit offert avec succès! L'identifiant unique du produit est " + produitId);

        System.out.println();
        System.out.println("Appuyez sur n'importe quelle touche pour revenir au menu revendeur.");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        connexionRevendeur(revendeur);
    }

    private void AfficherMetriques(Revendeur revendeur){
        int totalProduits = revendeur.listeProduit.size();
        int totalVendus = 0;
        int totalRevenu = 0;

        System.out.println("======================================");
        System.out.println("Métriques de vos activités:");
        System.out.println("Nombre total de produits mis en vente: " + totalProduits);
        System.out.println("Nombre total de produits vendus: " + totalVendus);
        System.out.println("Revenu total: " + totalRevenu + "$");
        System.out.println("======================================");

        System.out.println();
        System.out.println("Appuyez sur n'importe quelle touche pour revenir au menu revendeur.");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        connexionRevendeur(revendeur);
    }

    private void ConfirmerReception(Revendeur revendeur){
        System.out.println("======================================");
        System.out.println("Confirmer la reception d'un retour");
        System.out.println("======================================");
        System.out.println("Prototype: (Entrez 1 pour tester ID valide.)");
        String[] liensMedia = null;
        Produit produit = new Produit(TypeProduit.LIVRES_ET_MANUELS, 1, 1000, "Livre",
                "Un livre intéressant", revendeur, 10, 5, liensMedia);
        Retour retourMock = new Retour(produit, 1);

        System.out.println("Entrez l'ID du retour:");
        String retourIdStr = in.nextLine();
        int retourId = Integer.parseInt(retourIdStr);
        if (retourId == retourMock.id) {
            retourMock.changerStatus("livré");
            System.out.println("La réception du retour a été confirmée.");
        } else {
            System.out.println("Aucun retour trouvé avec cet ID.");
        }

        System.out.println();
        System.out.println("Appuyez sur n'importe quelle touche pour revenir au menu revendeur.");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        connexionRevendeur(revendeur);
    }

    private boolean emailPatternCorrect(String email) {
        return Pattern.compile("^(.+)@(\\S+)$").matcher(email).matches();
    }

    private boolean pseudoDisponible(String pseudo) {
        return true;
    }

    private Acheteur creerNouveauAcheteur(Acheteur a) {
        return new Acheteur(); //  quand nous aurons une base de données
    }

    private Revendeur creerNouveauRevendeur(Revendeur r) {
        return new Revendeur(); //  quand nous aurons une base de données
    }
}
