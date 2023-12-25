import java.io.*;
import java.util.LinkedList;

public class RetourRepo {

    public LinkedList<Retour> get() {

        try {
            FileInputStream fileIn = new FileInputStream("Retours.json");
            ObjectInputStream in = new ObjectInputStream(fileIn);

            try {
                var listeRetours = (LinkedList<Retour>) in.readObject();
                return listeRetours;
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


    public void put(Retour r) {


        try {
            FileOutputStream fileOut = new FileOutputStream("Retours.json");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject( get().add(r) );
            out.close();
            fileOut.close();

        } catch (IOException i) {
            i.printStackTrace();
        }

    }
}