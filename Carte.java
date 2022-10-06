import java.util.Arrays;

public class Carte implements Comparable<Carte> {

    public static final String[] COULEURS = { "Pique", "Coeur", "Trefle", "Carreau" };
    public static final String[] VALEURS = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "Valet", "Damme", "Roi", "As" };

    private String couleur;
    private String valeur;

    // provide the index of the color in the array
    // same for the card value

    public Carte(int couleurId, int valeurId) {
        // make sure each index is within the bounds of the array
        if(couleurId >= COULEURS.length || valeurId >= VALEURS.length) {
            return;
        }
        this.couleur = COULEURS[couleurId];
        this.valeur = VALEURS[valeurId];
    }

    // Used to compare this card to another one
    public int compareTo(Carte o) {
        if(o == null) { return 1; }
        int thisValeurId = Arrays.asList(VALEURS).indexOf(this.valeur);
        int oValeurId = Arrays.asList(VALEURS).indexOf(o.valeur);
        return thisValeurId == oValeurId ? 0 : thisValeurId < oValeurId ? -1 : 1;
    }


    // toString override
    public String toString() {
        return this.valeur + " de " + this.couleur;
    }

}