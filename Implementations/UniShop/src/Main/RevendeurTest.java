package Main;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.LinkedList;

public class RevendeurTest {

    @Test
    public void testGetListeRevendeurNotEmpty() {
        // Arrange
        Revendeur r1 = new Revendeur("John","john123", "john@example.com", "Address1", null);
        r1.Notifications.add("n1");


        // Act
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        r1.afficherNotifications(r1);


        // Assert
        String expectedOutput = "Voici vos notifications : \nn1";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void afficherRevendeur() {
        // Arrange
        Revendeur r1 = new Revendeur("John","john123", "john@example.com", "Address1", null);


        // Act
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        r1.afficherRevendeur();


        // Assert
        String expectedOutput = "Revendeur John\nAdresse: john123\nEmail: john@example.com\nTelephone: Addresse1\nProduits:\n";
        assertEquals(expectedOutput, outContent.toString());
    }




}
