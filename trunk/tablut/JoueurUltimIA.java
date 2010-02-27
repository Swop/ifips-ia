package tablut;

import java.util.ArrayList;


/**
 * Notre joueur virtuel (équipe "UltimIA"). Se charge de lancer l'algorithme et de faire evouluer le plateau après un mouvement de l'ennemi
 * @author NorTicUs
 */
public class JoueurUltimIA implements IJoueur {
    /**
     * Couleur du joueur
     */
    private int color;
    /**
     * Heuristique adopte par le joueur
     */
    private IHeuristique heuristique;
    /**
     * Methode de descente adoptee par le joueur
     */
    private IDescente methode_descente;
    /**
     * Plateau de jeu
     */
    private Plateau p;

    /**
     * Initialise le joueur
     * @param mycolour La couleur du joueur
     */
    public void initJoueur(int mycolour) {
	this.color = mycolour;
	// Utilisation de notre heuristique "HeurisqtiqueUltimIA"
	heuristique = new HeuristiqueUltimIA();
	// Utilisation de notre methode de descente "NegAB"
	methode_descente = new DescenteNegAB();
	p = Plateau.getInstance();
    }
    /**
     * Lancement de l'algorithme du choix de meilleur coup
     * @return Le coup choisi
     */
    public String choixMouvement() {
	String mouv =  methode_descente.trouveMeilleurCoup(heuristique, p, color);
	System.out.println("J'ai choisi le mouvement : "+mouv.toString());
	System.out.println("Plateau après mon mouvement :\n"+p.toString());
	return mouv;
    }
    /**
     * Declare le vainqueur de la partie en console
     * @param couleur La couleur du gagnant
     */
    public void declareLeVainqueur(int couleur) {
	System.out.print("Le vainqueur est le joueur "+couleur+" : ");
	if(couleur == ClientUltimIA.BLANC){
	    if(couleur == color)
		System.out.println("Le Roy est sain et sauf ! Vive le Roy !");
	    else
		System.out.println("Je demande une vérification de l'arbitre !");
	}
	else if(couleur == ClientUltimIA.NOIR){
	    if(couleur == color)
		System.out.println("Le peuple a vaincu ! Le Roy à la guillotine !");
	    else
		System.out.println("Je demande une vérification de l'arbitre !");
	}
	else
	    System.out.println("Il n'y a pas de vainqueur ... Match nul !!");
    }
    /**
     * Repercute un mouvement de l'ennemi sur le plateau
     * @param startRow Ligne de depart
     * @param startCol Colonne de depart
     * @param finishRow Ligne d'arrivee
     * @param finishCol Colonne d'arrivee
     */
    public void mouvementEnnemi(int startRow, int startCol, int finishRow, int finishCol) {
	Case orig = null;
	Case dest = null;
	try {
	    orig = p.getCase(startCol, startRow);
	    dest = p.getCase(finishCol, finishRow);
	} catch (HorsJeuException ex) { /* Normalement, la validite du mouvement est controlle par l'arbitre ...*/ }
	Mouvement m = new Mouvement(orig, dest);
	try {

	    ArrayList<Mouvement> mouvementsPoubelle = m.appliquerMouvement(p);
		for(Mouvement m2 : mouvementsPoubelle)
		    m2.appliquerMouvement(p);

	} catch (HorsJeuException ex) {/*Ici encore, d'apres l'arbire, il n'y a pas de soucis ... */}
        System.out.println("Plateau après mouvement ennemi :\n"+p.toString());
    }
    /**
     * Retorune notre nom d'equipe
     * @return Le nom d'equipe
     */
    public String quadriName() {
	return "The UltimIA";
    }
    /**
     * Retourne la couleur du joueur
     * @return La couleur du joueur
     */
    public int getColor() {
	return this.color;
    }

}
