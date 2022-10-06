import java.util.ArrayList;

public class Joueur {
    
    private ArrayList<Carte> deck;
    private int score;

    public Joueur() {
        this.score = 0;
        this.deck = new ArrayList<Carte>();
    }

    public void ajouterCarte(Carte c) {
        this.deck.add(0, c);
    }

    public Carte tirerCarte() {
        if(this.deck.size() == 0) { return null; }
        return this.deck.remove(this.deck.size() - 1);
    }
}
