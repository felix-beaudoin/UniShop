import java.io.*;
import java.util.LinkedList;

public class AcheteurRepo {

    public LinkedList<Acheteur> get() {

        try {
            FileInputStream fileIn = new FileInputStream("Acheteurs.json");
            ObjectInputStream in = new ObjectInputStream(fileIn);

            try {
                var listeAcheteurs = (LinkedList<Acheteur>) in.readObject();
                return listeAcheteurs;
            } catch (ClassNotFoundException c) {
                c.printStackTrace();
            }

            in.close();
            fileIn.close();

        } catch (IOException i) {
            i.printStackTrace();
        }

        return null;
    }


    public void put(Acheteur a) {


        try {
            FileOutputStream fileOut = new FileOutputStream("Acheteurs.json");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject( get().add(a) );
            out.close();
            fileOut.close();

        } catch (IOException i) {
            i.printStackTrace();
        }

    }




}
