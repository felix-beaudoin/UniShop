package Main;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Revendeur {

	String nom, adresse, email, role, telephone;
	List<Produit> listeProduit = new ArrayList<>();
	Stack<String> Notifications = new Stack<String>();

	public Revendeur(String nom, String adresse, String email, String telephone, List<Produit> listeProduit) {
		this.nom = nom;
		this.adresse = adresse;
		this.email = email;
		this.telephone = telephone;
		this.role = "Revendeur";
		this.listeProduit = listeProduit;
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
	public void afficherNotifications(Revendeur r){
		if(!r.Notifications.isEmpty()){
			System.out.println("Voici vos notifications : ");
			for (String notification : r.Notifications) {
				System.out.println(notification);
			}
		} else {
			System.out.println("Vous n'avez pas de notifications.");
		}
	}
	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Revendeur() {
		this.role = "Revendeur";
	}

}