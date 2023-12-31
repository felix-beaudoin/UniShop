package Main;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class AcheteurTest {

    @Test
    public void testGetListeAcheteurNotEmpty() {
        // Arrange
        Acheteur acheteur1 = new Acheteur("John", "Doe", "john123", "john@example.com", "Address1", "123456789");
        Acheteur acheteur2 = new Acheteur("Jane", "Smith", "jane456", "jane@example.com", "Address2", "987654321");
        LinkedList<Acheteur> listeAcheteur = new LinkedList<>();
        listeAcheteur.add(acheteur1);
        listeAcheteur.add(acheteur2);

        // Act
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        acheteur1.getListeAcheteur(listeAcheteur);

        // Assert
        String expectedOutput = "Voici la liste des acheteurs\nJohn Doe\nJane Smith\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testGetListeAcheteurEmpty() {
        // Arrange
        LinkedList<Acheteur> listeAcheteur = new LinkedList<>();

        // Act
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        new Acheteur().getListeAcheteur(listeAcheteur);

        // Assert
        String expectedOutput = "La liste des acheteurs est vide.\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testGetListeAcheteurNull() {
        // Arrange
        LinkedList<Acheteur> listeAcheteur = null;

        // Act
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        new Acheteur().getListeAcheteur(listeAcheteur);

        // Assert
        String expectedOutput = "La liste des acheteurs est vide.\n";
        assertEquals(expectedOutput, outContent.toString());
    }
}
