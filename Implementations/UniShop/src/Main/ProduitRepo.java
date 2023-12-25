package Main;

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


    public LinkedList<Produit> recherche(String nom) {
        LinkedList<Produit> produits = get();

        for (Produit p : produits) {
            if (!p.nom.equals(nom)) {
                produits.remove(p);
            }
        }

        return produits;
    }

    public LinkedList<Produit> recherche(TypeProduit type) {
        LinkedList<Produit> produits = get();

        for (Produit p : produits) {
            if (!p.type.equals(type)) {
                produits.remove(p);
            }
        }

        return produits;
    }

    public LinkedList<Produit> recherche(int prixMin, int prixMax) {
        LinkedList<Produit> produits = get();

        for (Produit p : produits) {
            if (p.prix < prixMin || p.prix > prixMax) {
                produits.remove(p);
            }
        }

        return produits;
    }
}
