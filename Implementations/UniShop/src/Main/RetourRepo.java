package Main;

import java.io.*;
import java.util.LinkedList;

public class RetourRepo {

    private LinkedList<Retour> retours;

    public LinkedList<Retour> get() {
        if (retours != null) {
            return retours;
        }

        try {
            FileInputStream fileIn = new FileInputStream("Retours.json");
            ObjectInputStream in = new ObjectInputStream(fileIn);

            try {
                retours = (LinkedList<Retour>) in.readObject();
                return retours;
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
        if (retours == null) {
            retours = new LinkedList<>();
        }
        retours.add(r);

        try {
            FileOutputStream fileOut = new FileOutputStream("Retours.json");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(retours);
            out.close();
            fileOut.close();

        } catch (IOException i) {
            i.printStackTrace();
        }
    }
}