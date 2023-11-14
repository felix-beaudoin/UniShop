public class Commentaire {
    Acheteur a;
    int nbLikes;
    String texte;


    Commentaire (Acheteur a, String texte) {
        this.texte = texte;
        this.a = a;
    }

    public void likeCommentaire() {
        nbLikes++;
        a.points += 10;
    }

    public void signaler() {
        a.points -= 10;
    }

}
