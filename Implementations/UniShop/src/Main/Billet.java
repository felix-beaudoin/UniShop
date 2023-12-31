package Main;

import java.util.Scanner;

public class Billet {
    Acheteur acheteur;
    String  probleme;
    String solution;
    String code;
    String description;

    public Billet(Acheteur acheteur, String probleme, String solution, String code, String description) {
        this.acheteur = acheteur;
        this.probleme = probleme;
        this.solution = solution;
        this.code = code;
        this.description = description;
    }


   

  

    public Acheteur getAcheteyr() {
        return acheteur;
    }

    public void setAcheteyr(Acheteur acheteyr) {
        this.acheteur = acheteyr;
    }

    public String getProbleme() {
        return probleme;
    }

    public void setProbleme(String probleme) {
        this.probleme = probleme;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public String getUID() {
        return code;
    }

    public void setUID(String code) {
        code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
