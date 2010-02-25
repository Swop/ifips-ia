/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package client;

/**
 *
 * @author swop
 */
public class Pion {
    private boolean mort;

    public enum TypePion { BLANC, NOIR, ROI };
    private TypePion type;
    private Case pere;

    public Pion(TypePion t) {
	type = t;
	mort = false;
	pere = null;
    }

    public TypePion getType() {
	return type;
    }

    public void tuer() {
	mort = true;
    }

    public void ressuciter() {
	mort = false;
    }

    public boolean isMort() {
	return mort;
    }

    public void setPere(Case p) {
	pere = p;
    }

    public Case getPere() {
	return pere;
    }

    public static int getCouleurPion(TypePion type) {
	if(type == TypePion.BLANC || type == TypePion.ROI)
	    return ClientJeu.BLANC;
	else
	    return ClientJeu.NOIR;
    }

     public static int getCouleurPionAdverse(TypePion type) {
	 int couleur = getCouleurPion(type);
	 if(couleur == ClientJeu.BLANC)
	     return ClientJeu.NOIR;
	 else
	     return ClientJeu.BLANC;
    }
    @Override
    public String toString() {
	if(this.getType()==Pion.TypePion.BLANC) return "2";
	if(this.getType()==Pion.TypePion.NOIR) return "1";
	if(this.getType()==Pion.TypePion.ROI) return "3";
	return "dummy";
    }
}
