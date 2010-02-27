package client;

/**
 * Un pion sur le plateau
 * @author swop
 */
public class Pion {
    /**
     * Reseigne sur l'etat du pion (pris/non pris)
     */
    private boolean mort;
    /**
     * Type de pions
     */
    public enum TypePion { BLANC, NOIR, ROI };
    /**
     * Le type du pion
     */
    private TypePion type;
    /**
     * La case ou se trouve le pion
     */
    private Case pere;
    /**
     * Constructeur
     * @param t Le type du pion
     */
    public Pion(TypePion t) {
	type = t;
	mort = false;
	pere = null;
    }
    /**
     * Retourne le type du pion
     * @return le type du pion
     */
    public TypePion getType() {
	return type;
    }
    /**
     * Place le statut du pion a "pris"
     */
    public void tuer() {
	mort = true;
    }
    /**
     * Place le statut du pion a "non pris"
     */
    public void ressuciter() {
	mort = false;
    }
    /**
     * Retourne le statut du pion (pris/non pris)
     * @return Le statut du pion. TRUE : pris, FALSE : non pris
     */
    public boolean isMort() {
	return mort;
    }
    /**
     * Change la case mere du pion
     * @param p La nouvelle case ou se trouve le pion
     */
    public void setPere(Case p) {
	pere = p;
    }
    /**
     * Retourne la case ou se trouve le pion
     * @return La case ou se trouve le pion
     */
    public Case getPere() {
	return pere;
    }
    /**
     * Retourne l'entier representatif de la couleur du pion d'apres son type
     * @param type Le type du pion
     * @return La couleur du pion
     */
    public static int getCouleurPion(TypePion type) {
	if(type == TypePion.BLANC || type == TypePion.ROI)
	    return ClientJeu.BLANC;
	else
	    return ClientJeu.NOIR;
    }
    /**
     * Retourne l'entier representatif de la couleur ennemie de la couleur du pion d'apres son type
     * @param type Le type du pion
     * @return La couleur du joueur ennemi du pion
     */
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
