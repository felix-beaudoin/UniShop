import java.util.Scanner;
import java.util.regex.Pattern;

public class Authentification {
    public void accueil() {
        boolean accueilLoop = true;

        System.out.println("Sélectionnez l'option désirée:");
        System.out.println("1. S'inscrire");
        System.out.println("2. Se connecter");
        System.out.println("0. Quitter");

       while (accueilLoop) {


            Scanner in = new Scanner(System.in);
            String inp = in.nextLine();

            try {

                int option = Integer.parseInt(inp);

                switch (option) {
                    case 1: inscription();
                        accueilLoop = false;
                    case 2: connection();
                        accueilLoop = false;
                    case 0: accueilLoop = false;
                    default: System.out.println("Option non comprise. Veuillez réessayer.");
                }

            } catch (NumberFormatException nfe) {
                System.out.println("Option non comprise. Veuillez réessayer.");
            }
        }
    }

    private void connection() {
        //todo
    }

    private void inscription() {
        boolean inscriptionLoop = true;
        Scanner in = new Scanner(System.in);

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

        creerNouveauAcheteur(new Acheteur(nom, prenom, pseudo, email, adresse, telephone));

    }
    private boolean emailPatternIncorrect(String email) {
        return Pattern.compile("^(.+)@(\\S+)$").matcher(email).matches();
    }
    private boolean pseudoDisponible(String pseudo) {
        return true;
    }

    private void creerNouveauAcheteur(Acheteur a) {
        return; // pour quand nous aurons une base de données
    }
}