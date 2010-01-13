/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tablut;

/**
 *
 * @author NorTicUs
 */
public class UltimIA implements IJoueur{

    private int color;

    public void initJoueur(int mycolour) {
	this.color = mycolour;
    }

    public String choixMouvement() {
	throw new UnsupportedOperationException("Not supported yet.");
    }

    public void declareLeVainqueur(int colour) {
	throw new UnsupportedOperationException("Not supported yet.");
    }

    public void mouvementEnnemi(int startRow, int startCol, int finishRow, int finishCol) {
	throw new UnsupportedOperationException("Not supported yet.");
    }

    public String quadriName() {
	return "The UltimIA";
    }

}
