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

    private void connexionAcheteur(Acheteur acheteur) {
        if (acheteur.pseudo == null){
            acheteur = new Acheteur("Doe", "John","John123","Johndoe@email.com",
                    "3150 rue Jean-Brillant", 1234567890);
        }
        System.out.println("Vous êtes connecté en tant qu'acheteur: " + acheteur.pseudo);
        System.out.println("Sélectionnez l'option désirée:");
        System.out.println("1. Afficher le catalogue");
        System.out.println("0. Quitter");

        while (running) {

            String inp = in.nextLine();
            try {

                int option = Integer.parseInt(inp);

                switch (option) {
                    case 1:
                        Catalogue(acheteur); break;
                    case 0:
                        running = false; break;
                    default:
                        System.out.println("Option invalide. Veuillez entrer un nombre entier entre 0 et 1.");
                }
            } catch (NumberFormatException nfe) {
                System.out.println("Entrée invalide. Veuillez entrer un nombre entier.");
            }
        }
    }

    private void connexionRevendeur(Revendeur revendeur) {
        if (revendeur.nom == null){
            List<Produit> listeProduit = new ArrayList<>();
            revendeur = new Revendeur("John", "3150 rue Jean-Brillant","Johndoe@email.com",
                    1234567890, listeProduit);
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

        while (emailPatternIncorrect(email)) {
            System.out.println("Email incorrect, veuillez réessayez.");
            email = in.nextLine();
        }

        System.out.println("Quel est votre adresse de livraison?");
        String adresse = in.nextLine();

        System.out.println("Quel est votre numéro de téléphone?");
        String telephoneString = in.nextLine();
        int telephone = 0;
        boolean telephoneLoop = true;
        while(telephoneLoop) {
            try {
                telephone = Integer.parseInt(telephoneString);
                telephoneLoop = false;
            } catch (NumberFormatException nfe) {
                System.out.println("Votre numéro ne peut contenir que des nombres, veuillez réessayez.");
            }
        }

        return creerNouveauAcheteur(new Acheteur(nom, prenom, pseudo, email, adresse, telephone));
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

        while (emailPatternIncorrect(email)) {
            System.out.println("Email incorrect, veuillez réessayez.");
            email = in.nextLine();
        }

        System.out.println("Quel est votre numéro de téléphone?");
        String telephoneString = in.nextLine();
        int telephone = 0;
        boolean telephoneLoop = true;
        while(telephoneLoop) {
            try {
                telephone = Integer.parseInt(telephoneString);
                telephoneLoop = false;
            } catch (NumberFormatException nfe) {
                System.out.println("Votre numéro ne peut contenir que des nombres, veuillez réessayez.");
            }
        }
        List<Produit> listeProduit = new ArrayList<>();
        return creerNouveauRevendeur(new Revendeur(nom, adresse, email, telephone, listeProduit));
    }

    private void Catalogue(Acheteur a) {
        AfficherCatalogue afficherCatalogue = new AfficherCatalogue(a);
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

        System.out.println("Entrez la quantité initiale du produit:");
        int quantite = in.nextInt();

        System.out.println("Entrez le prix du produit:");
        int prix = in.nextInt();

        boolean pointsBonusValides = false;
        int pointsBonus = 0;

        while (!pointsBonusValides) {
            System.out.println("Entrez le nombre de points bonus (optionnel) :");
            pointsBonus = in.nextInt();

            int pointsBase = prix;

            // Arrondir au dollar inférieur si le prix n'est pas un nombre entier
            if (prix % 1 != 0) {
                pointsBase = (int) Math.floor(prix);
            }

            // Le nombre total de points (base + bonus) ne peut pas dépasser 20 points par dollar
            if (pointsBase + pointsBonus > 20 * prix) {
                System.out.println("Le nombre total de points (base + bonus) ne peut pas dépasser 20 points par dollar." +
                    "Veuillez entrer à nouveau le nombre de points bonus.");
            } else {
                pointsBonusValides = true;
            }
        }

        System.out.println("Voulez-vous ajouter des images et des vidéos ? (O/N) :");
        String reponse = in.nextLine();
        String[] liensMedia = null;
        if (reponse.equalsIgnoreCase("O")) {
            System.out.println("Entrez les liens vers les images et les vidéos (séparés par des virgules) :");
            String mediaLinks = in.nextLine();
            liensMedia = mediaLinks.split(",");
        }

        System.out.println("Entrez le type du produit:");
        System.out.println("1. LIVRES_ET_MANUELS");
        System.out.println("2. RESSOURCES_APPRENTISSAGE");
        System.out.println("3. PAPETERIES");
        System.out.println("4. MATERIEL_INFORMATIQUE");
        System.out.println("5. EQUIPEMENT_BUREAU");
        int typeInput = in.nextInt();

        TypeProduit type = null;
        switch(typeInput) {
            case 1: type = TypeProduit.LIVRES_ET_MANUELS; break;
            case 2: type = TypeProduit.RESSOURCES_APPRENTISSAGE; break;
            case 3: type = TypeProduit.PAPETERIES; break;
            case 4: type = TypeProduit.MATERIEL_INFORMATIQUE; break;
            case 5: type = TypeProduit.EQUIPEMENT_BUREAU; break;
            default: System.out.println("Type non existant. Veuillez réessayer.");
                return;
        }
        in.nextLine();

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

        for (Produit produit : revendeur.listeProduit) {
            totalVendus += produit.quantite;
            totalRevenu += produit.prix * produit.quantite;
        }
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

    private boolean emailPatternIncorrect(String email) {
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
