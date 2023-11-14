import java.util.HashMap;
import java.util.Scanner;

public class AfficherProduit {

    Produit p;
    Acheteur a;
    AfficherProduit(Produit p){
        this.p = p;

        p.afficher();

        System.out.println("Que voulez-vous faire?");
        System.out.println("1- Mettre un commentaire");
        System.out.println("2- Voir les commentaires");
        System.out.println("3- Acheter le produit");

        Scanner in = new Scanner(System.in);
        String inp = in.nextLine();

        boolean displayLoop = true;

        while (displayLoop) {
            try {
                int num = Integer.parseInt(inp);
                switch (num) {
                    case 1: ajouterCommentaire(p);
                    case 2: afficherCommentaires(p);
                    case 3: acheterProduit(); // on ne l'implémente pas dans ce prototype
                }
            } catch (NumberFormatException nfe) {
                System.out.println("Choix non-compris, veuillez réessayer.");
            }
        }
    }

    public void afficherCommentaires(Produit p) {
        HashMap<Integer, Commentaire> commentaireHashMap = new HashMap<>();
        for (int i = 0; i < p.commentaires.size(); i++) {
            commentaireHashMap.put((i+1), p.commentaires.get(i));
            System.out.println((i+1) + "- " + p.commentaires.get(i).texte);
        }
        System.out.println("Choisissez un commentaire pour le liker ou le signaler.");

        Scanner in = new Scanner(System.in);
        String inp = in.nextLine();

        boolean displayCommentLoop = true;
        while (displayCommentLoop) {

            try {
                int num = Integer.parseInt(inp);

                Commentaire c = commentaireHashMap.get(num);
                if (c == null) {
                    System.out.println("Choix pas compris. Réessaye.");
                } else {
                    System.out.println("L- Liker");
                    System.out.println("S- Signaler");
                    System.out.println("N- Ne rien faire");

                    boolean actionComment = true;

                    while (actionComment) {

                        inp = in.nextLine();
                        switch (inp) {

                            case "L": {
                                c.likeCommentaire();
                                break;
                            }

                            case "S": {
                                c.signaler();
                                break;
                            }

                            case "N": break;

                            default:
                                System.out.println("Choix pas compris. Réessaye.");
                        }

                    }


                }

            } catch (NumberFormatException nfe) {
                System.out.println("Choix pas compris. Réessaye.");
            }

        }
    }

    public void ajouterCommentaire(Produit p) {
        System.out.println("Votre commentaire: ");
        Scanner in = new Scanner(System.in);
        String commentaire = in.nextLine();
        p.commentaires.add(new Commentaire(a, commentaire));
    }

    public void acheterProduit() {
        //rien
    }


}
