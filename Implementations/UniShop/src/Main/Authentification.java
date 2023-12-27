package Main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
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
        System.out.println("5. Se connecter en tant qu'admin");
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
        AcheteurRepo acheteurRepo = new AcheteurRepo();
        LinkedList<Acheteur> acheteurs = acheteurRepo.get();

        if (acheteur.pseudo == null){
            for (Acheteur a : acheteurs) {
                if (a.pseudo.equals(acheteur.pseudo)) {
                    acheteur = a;
                    break;
                }
            }
        }

        if (acheteur.pseudo == null){
            System.out.println("Acheteur Pseudo non trouvé. Veuillez essayer à nouveau.");
            return;
        }

        System.out.println("Vous êtes connecté en tant qu'acheteur: " + acheteur.pseudo);
        System.out.println("Sélectionnez l'option désirée:");
        System.out.println("1. Afficher le catalogue");
        System.out.println("2. Afficher le panier d'achat");
        System.out.println("3. Passer une commande");
        System.out.println("4. Gestion de commandes");
        System.out.println("5. Rechercher revendeur par pseudo");
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
                    case 5:
                        RechercherAcheteur(acheteur); break;
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
        RevendeurRepo revendeurRepo = new RevendeurRepo();
        LinkedList<Revendeur> revendeurs = revendeurRepo.get();

        if (revendeur.nom == null){
            for (Revendeur r : revendeurs) {
                if (r.nom.equals(revendeur.nom)) {
                    revendeur = r;
                    break;
                }
            }
        }

        if (revendeur.nom == null){
            System.out.println("Revendeur nom non trouvé. Veuillez essayer à nouveau.");
            return;
        }

        System.out.println("Vous êtes connecté en tant que revendeur: " + revendeur.nom);
        System.out.println("Sélectionnez l'option désirée:");
        System.out.println("1. Offrir un produit");
        System.out.println("2. Afficher les metriques");
        System.out.println("3. Confirmer la reception d'un retour");
        System.out.println("4. Afficher les listes des acheteurs");
        System.out.println("5. Afficher le profil d'un acheteur");
        System.out.println("6. Afficher les évaluations d'un produit");
        System.out.println("7. Rechercher revendeur par pseudo");
        System.out.println("8. Rechercher acheteur par pseudo");
        System.out.println("9. Modifier son profil");
        System.out.println("10.Supprimer un produit");
        System.out.println("0. Quitter");

        while (running) {
            try {
                String input = in.nextLine();
                int option = Integer.parseInt(input);

                switch (option) {
                    case 1:
                        OffrirProduit(revendeur, revendeurRepo);
                        break;
                    case 2:
                        AfficherMetriques(revendeur, revendeurRepo);
                        break;
                    case 3:
                        ConfirmerReception(revendeur, revendeurRepo);

                        break;
                    case 4:
                       // Acheteur.getListeAcheteur(null);
                       break;
                    case 5:
                        //Acheteur.getProfilAcheteur(null);  
                        break;
                    case 6:
                        AfficherEvaluationProduit(revendeur, revendeurRepo);
                        break;
                    case 7:
                        RechercherAcheteur(revendeur);
                        break;
                    case 8:
                        RechercherAcheteur(revendeur);
                        break;
                    case 9:
                        modifierProfil(revendeur);
                        break;
                    case 10: 
                        SupprimerProduit(revendeur);
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
        return creerNouveauRevendeur(new Revendeur(nom, adresse, email, telephone, listeProduit));
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

    private void OffrirProduit(Revendeur revendeur, RevendeurRepo revendeurRepo){
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
        revendeur.listeProduit.add(produit);

        // Save the updated Revendeur (listeProduit)
        revendeurRepo.put(revendeur);

        // Save the new Produit
        ProduitRepo produitRepo = new ProduitRepo();
        produitRepo.put(produit);

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

    private void AfficherEvaluationProduit(Revendeur revendeur, RevendeurRepo revendeurRepo){
        ProduitRepo produitRepo = new ProduitRepo();
        LinkedList<Produit> produits = produitRepo.get();
    
        int inputId = -1;
        while (inputId == -1) {
            System.out.println("Veuillez entrer l'ID du produit:");
            Scanner in = new Scanner(System.in);
            String inp = in.nextLine();
    
            try {
                inputId = Integer.parseInt(inp);
                System.out.println("ID du produit: " + inputId);
            } catch (NumberFormatException e) {
                System.out.println("Entrée invalide. Veuillez entrer un nombre entier.");
            }
        }
    
        boolean produitFound = false;
        for (Produit produit : produits) {
            if (produit.id == inputId) {
                System.out.println("Commentaires pour le produit " + produit.id + ":");
                for (Commentaire commentaire : produit.commentaires) {
                    System.out.println("- " + commentaire.texte);
                }
                produitFound = true;
                break;
            }
        }
    
        if (!produitFound) {
            System.out.println("Aucun produit trouvé avec cet ID.");
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

    private void AfficherMetriques(Revendeur revendeur, RevendeurRepo revendeurRepo){
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

    private void RechercherAcheteur(Revendeur r) {
        AcheteurRepo acheteurRepo = new AcheteurRepo();
        LinkedList<Acheteur> acheteurs = acheteurRepo.get();
    
        System.out.println("Veuillez entrer le pseudo de l'acheteur:");
        Scanner in = new Scanner(System.in);
        String pseudo = in.nextLine();
    
        Acheteur acheteurRecherche = null;
        for (Acheteur acheteur : acheteurs) {
            if (acheteur.pseudo.equals(pseudo)) {
                acheteurRecherche = acheteur;
                break;
            }
        }
    
        if (acheteurRecherche != null) {
            System.out.println("Acheteur trouvé: " + acheteurRecherche.pseudo);
            System.out.println(acheteurRecherche.toString());
        } else {
            System.out.println("Aucun acheteur trouvé avec ce pseudo.");
        }
    
        System.out.println();
        System.out.println("Appuyez sur n'importe quelle touche pour revenir au menu revendeur.");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        connexionRevendeur(r);
    }

    private void RechercherAcheteur(Acheteur a) {
        AcheteurRepo acheteurRepo = new AcheteurRepo();
        LinkedList<Acheteur> acheteurs = acheteurRepo.get();
    
        System.out.println("Veuillez entrer le pseudo de l'acheteur:");
        Scanner in = new Scanner(System.in);
        String pseudo = in.nextLine();
    
        Acheteur acheteurRecherche = null;
        for (Acheteur acheteur : acheteurs) {
            if (acheteur.pseudo.equals(pseudo)) {
                acheteurRecherche = acheteur;
                break;
            }
        }
    
        if (acheteurRecherche != null) {
            System.out.println("Acheteur trouvé: " + acheteurRecherche.pseudo);
            System.out.println(acheteurRecherche.toString());
        } else {
            System.out.println("Aucun acheteur trouvé avec ce pseudo.");
        }
    
        System.out.println();
        System.out.println("Appuyez sur n'importe quelle touche pour revenir au menu revendeur.");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        connexionAcheteur(a);
    }

    public void ConfirmerReception(Revendeur revendeur, RevendeurRepo revendeurRepo){
        RetourRepo retourRepo = new RetourRepo();
        LinkedList<Retour> retours = retourRepo.get();
    
        System.out.println("======================================");
        System.out.println("Confirmer la reception d'un retour");
        System.out.println("======================================");
    
        System.out.println("Entrez l'ID du retour:");
        Scanner in = new Scanner(System.in);
        String retourIdStr = in.nextLine();
        int retourId = Integer.parseInt(retourIdStr);
    
        Retour retourToConfirm = null;
        for (Retour retour : retours) {
            if (retour.id == retourId) {
                retourToConfirm = retour;
                break;
            }
        }
    
        if (retourToConfirm != null) {
            retourToConfirm.changerStatus("livré");
            retourRepo.put(retourToConfirm);
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
        AcheteurRepo acheteurRepo = new AcheteurRepo();
        acheteurRepo.put(a);
        return a; 
    }
    
    private Revendeur creerNouveauRevendeur(Revendeur r) {
        RevendeurRepo revendeurRepo = new RevendeurRepo();
        revendeurRepo.put(r);
        return r;
    }
    private void modifierProfil(Revendeur r){
        System.out.println("======================================");
        System.out.println("Modifier son profil");
        System.out.println("======================================");
        System.out.println();
        System.out.println("Que voulez vous modifier");
        System.out.println("1. Nom");
        System.out.println("2. Adresse");
        System.out.println("3. Email");
        System.out.println("4. Telephone");
        System.out.println("0. Retour");

        while (running) {
            try {
                String input = in.nextLine();
                int option = Integer.parseInt(input);

                switch (option) {
                    case 1:
                        System.out.println("Entrez le nouveau nom : ");
                        String nom = in.nextLine();
                        r.setNom(nom);
                        break;
                    case 2:
                        System.out.println("Entrez la nouvelle adresse : ");
                        String adresse = in.nextLine();
                        r.setAdresse(adresse);
                        break;
                    case 3:
                        System.out.println("Entrez le nouvel email : ");
                        String email = in.nextLine();
                        r.setEmail(email);
                        break;
                    case 4:
                        System.out.println("Entrez le nouveau telephone : ");
                        String telephone = in.nextLine();
                        boolean telephoneLoop = true;
                        while(telephoneLoop) {
                            if (telephone.matches("\\d{3}-\\d{3}-\\d{4}")) {
                            telephoneLoop = false;
                        }else {
                            System.out.println("Votre numéro doit être dans le format 514-123-1234, veuillez réessayez.");
                            telephone = in.nextLine();
                        }
                    }
                        r.setTelephone(telephone);
                    break;
                    case 0:
                        running = false;
                        break;
                    default:
                        System.out.println("Option invalide. Veuillez entrer un nombre entre 0 et 4.");
                }
            } catch (NumberFormatException nfe) {
                System.out.println("Entrée invalide. Veuillez entrer un nombre entier.");
            }
        }
    }
    private void SupprimerProduit(Revendeur r){
        ProduitRepo produitRepo = new ProduitRepo();
        LinkedList<Produit> produits = produitRepo.get();
        System.out.println("======================================");
        System.out.println("Supprimer un produit");
        System.out.println("======================================");
        System.out.println();
        System.out.println("Entrez l'ID du produit à supprimer:");
        Scanner in = new Scanner(System.in);
        String inp = in.nextLine();
        int id = Integer.parseInt(inp);
        boolean produitFound = false;
        for (Produit p : produits) {
            if (p.id == id) {
                produitFound = true;
                produits.remove(p);
                System.out.println("Produit supprimé avec succès!");
                break;
            }
        }
        if (!produitFound) {
            System.out.println("Aucun produit trouvé avec cet ID.");
        }
        System.out.println();
        System.out.println("Appuyez sur n'importe quelle touche pour revenir au menu revendeur.");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        connexionRevendeur(r);
    }

    
}
