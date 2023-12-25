public class Commentaire {
    Acheteur a;
    int nbLikes;
    String texte;


    Commentaire (Acheteur a, String texte) {
        this.texte = texte;
        this.a = a;
    }

    public void likeCommentaire(AcheteurRepo acheteurRepo) {
        nbLikes++;
        a.points += 10;
        acheteurRepo.put(a);
    }
    
    public void signaler(AcheteurRepo acheteurRepo) {
        a.points -= 10;
        acheteurRepo.put(a);
    }
    

}
