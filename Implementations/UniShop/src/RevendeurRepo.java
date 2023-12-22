import java.io.*;
import java.util.LinkedList;

public class RevendeurRepo {

    public LinkedList<Revendeur> get() {

        try {
            FileInputStream fileIn = new FileInputStream("Revendeurs.json");
            ObjectInputStream in = new ObjectInputStream(fileIn);

            try {
                var listeRevendeurs = (LinkedList<Revendeur>) in.readObject();
                return listeRevendeurs;
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


    public void put(Revendeur r) {


        try {
            FileOutputStream fileOut = new FileOutputStream("Revendeurs.json");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject( get().add(r) );
            out.close();
            fileOut.close();

        } catch (IOException i) {
            i.printStackTrace();
        }

    }




}
