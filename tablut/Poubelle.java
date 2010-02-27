package tablut;

/**
 * Poubelle du plateau. Les pions pris vont dans la poubelle
 * @author swop
 */
public class Poubelle extends Case {
    /**
     * Pile stockant les pieces prises
     */
    private Pile pile;
    /**
     * Constructeur
     */
    public Poubelle() {
	super(Case.TypeCase.POUBELLE, -1, -1);
	pile = new Pile();
    }
    /**
     * Ajoute un pion dans la pile
     * @param p Le pion
     */
    @Override
    public void ajouterPion(Pion p) {
	p.tuer();
	pile.empiler(p);
	p.setPere(this);
    }
    /**
     * Retire un pion de la pile
     * @return Le pion retire
     */
    @Override
    public Pion retirerPion() {
	Pion p = (Pion)pile.depiler();
	p.ressuciter();
	return p;
    }
}
