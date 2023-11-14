import java.util.Scanner;

public class PasserCommande {

    private void passCommande(){
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
                        commande();
                        repeat = false;
                    case 2:
                        infoProfil();
                        repeat = false;
                    case 0:/**accueil();*/
                        repeat = false;
                    default:
                        System.out.println("Option non comprise. Veuillez réessayer.");
                }

            } catch (NumberFormatException nfe) {
                System.out.println("Option non comprise. Veuillez réessayer.");
            }
        }
    }
    private void commande(){
        //todo
    }
    private void infoProfil(){
        //todo
    }
    private void genererUUID(){
        //todo
    }
}
