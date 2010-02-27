package client;

/**
 * Une case sur le plateau
 * @author swop
 */
public class Case {
    /**
     * Type de cases possible
     */
    public enum TypeCase { MUR, TRONE, NORMALE, POUBELLE };
    /**
     * Contenu de la case
     */
    private Pion contenu;
    /**
     * Type de la case
     */
    private TypeCase type;

    /**
     * Coordonnee x de la case
     */
    private int x;
    /**
     * Coordonnee y de la case
     */
    private int y;

    /**
     * Constructeur de la case vide
     * @param type Type de Case
     * @param n_x Coordonee x de la case
     * @param n_y Coordonnee y de la case
     */
    public Case(TypeCase type, int n_x, int n_y) {
	contenu = null;
	this.type = type;
	x = n_x;
	y = n_y;
    }

    /**
     * Constructeur de la case non vide
     * @param type Type de Case
     * @param n_x Coordonee x de la case
     * @param n_y Coordonnee y de la case
     * @param p Pion a placer sur la case
     */
    public Case(TypeCase type, int n_x, int n_y, Pion p) {
	this.type = type;
	contenu = p;
	x = n_x;
	y = n_y;
    }
    /**
     * Ajoute un pion sur la case
     * @param p Le pion a placer
     */
    public void ajouterPion(Pion p) {
	contenu = p;
	p.setPere(this);
    }
    /**
     * Retire un pion de la case
     * @return Le pion enlevé
     */
    public Pion retirerPion() {
	Pion c = contenu;
	contenu = null;
	return c;
    }
    /**
     * Retourne le type de la case
     * @return Le type de la case
     */
    public TypeCase getType() {
	return type;
    }
    /**
     * Retourne le contenu de la case
     * @return Le pion sur la case ou null si al case est vide
     */
    public Pion getContenu() {
	return contenu;
    }
    /**
     * Retourne la coordonnee x de la case
     * @return la coordonee x de la case
     */
    public int getX() {
	return x;
    }
    /**
     * Retourne la coordonnee y de la case
     * @return la coordonnee y de la case
     */
    public int getY() {
	return y;
    }
}
