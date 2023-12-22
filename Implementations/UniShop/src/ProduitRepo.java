import java.io.*;
import java.util.LinkedList;

public class ProduitRepo {

    public LinkedList<Produit> get() {

        try {
            FileInputStream fileIn = new FileInputStream("Produits.json");
            ObjectInputStream in = new ObjectInputStream(fileIn);

            try {
                var produits = (LinkedList<Produit>) in.readObject();
                return produits;
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


    public void put(Produit p) {


        try {
            FileOutputStream fileOut = new FileOutputStream("Produits.json");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject( get().add(p) );
            out.close();
            fileOut.close();

        } catch (IOException i) {
            i.printStackTrace();
        }

    }




}
