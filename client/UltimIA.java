/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package client;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author NorTicUs
 */
public class UltimIA implements IJoueur {
    private int color;
    private IHeuristique heuristique;
    private IDescente methode_descente;
    private Plateau p;


    public void initJoueur(int mycolour) {
	this.color = mycolour;
	heuristique = new HeuristiqueUltimIA();
	methode_descente = new DescenteNegAB();
	p = Plateau.getInstance();
    }

    public String choixMouvement() {
	return methode_descente.trouveMeilleurCoup(heuristique, p, color);
    }

    public void declareLeVainqueur(int couleur) {
	System.out.print("Le vainqueur est le joueur "+couleur+" : ");
	if(couleur == ClientJeu.BLANC){
	    if(couleur == color)
		System.out.println("Le Roy est sain et sauf ! Vive le Roy !");
	    else
		System.out.println("Je demande une vérification de l'arbitre !");
	}
	else if(couleur == ClientJeu.NOIR){
	    if(couleur == color)
		System.out.println("Le peuple a vaincu ! Le Roy à la guillotine !");
	    else
		System.out.println("Je demande une vérification de l'arbitre !");
	}
	else
	    System.out.println("Il n'y a pas de vainqueur ... Match nul !!");
    }

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
        //System.out.println("Plateau après mouvement ennemi :\n"+p.toString());
    }

    public String quadriName() {
	return "The UltimIA";
    }

    public int getColor() {
	return this.color;
    }

}
