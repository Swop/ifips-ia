/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package client;

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

    public void declareLeVainqueur(int colour) {
	System.out.print("Le vainqueur est le joueur ");
	if(colour == ClientJeu.BLANC)
	    System.out.println("Le vainqueur est le joueur blanc !");
	else if(colour == ClientJeu.NOIR)
	    System.out.println("Le vainqueur est le joueur noir !");
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
	    m.appliquerMouvement(p);
	} catch (HorsJeuException ex) {/*Ici encore, d'apres l'arbire, il n'y a pas de soucis ... */}
    }

    public String quadriName() {
	return "The UltimIA";
    }

    public int getColor() {
	return this.color;
    }

}