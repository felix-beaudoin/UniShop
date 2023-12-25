package Main;

import java.util.ArrayList;
import java.util.List;

public class Revendeur {

	String nom, adresse, email, role, telephone;
	List<Produit> listeProduit = new ArrayList<>();

	public Revendeur(String nom, String adresse, String email, String telephone, List<Produit> listeProduit) {
		this.nom = nom;
		this.adresse = adresse;
		this.email = email;
		this.telephone = telephone;
		this.role = "Revendeur";
		this.listeProduit = listeProduit;
	}

	public Revendeur() {
		this.role = "Revendeur";
	}


	public void afficherRevendeur() {
		System.out.println("Revendeur " + nom);
		System.out.println("Adresse: " + adresse);
		System.out.println("Email: " + email);
		System.out.println("Telephone: " + telephone);
		System.out.println("Produits:");

		for (Produit p : listeProduit) {
			p.afficher();
		}
	}

}