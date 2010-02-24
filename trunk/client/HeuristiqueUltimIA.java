/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package client;

/**
 * Notre heuristique de jeu. Principe : TODO
 * @author swop
 */
public class HeuristiqueUltimIA implements IHeuristique {
    
	/**
	 * valeur de l'heuristique pour ce plateau
	 */	
	private int eval = 0;
	
	/**
     * Evalue le plateau donne en paramettre
     * @param p
     * Plateau de jeu
     * @return
     * La valeur heuristique representative du plateau
     */
    public int evalue(Plateau p, int couleur) {
    	if(p.getPlaceRoi().getX() == 0 && (p.getPlaceRoi().getY() == 3 || p.getPlaceRoi().getY() == 4 ||
    			p.getPlaceRoi().getY() == 5)){
    		if(couleur == ClientJeu.BLANC)
    			return Integer.MAX_VALUE;
    		else
    			return Integer.MIN_VALUE;
    	}
    	if(p.getPlaceRoi().getX() == 8 && (p.getPlaceRoi().getY() == 3 || p.getPlaceRoi().getY() == 4 ||
    			p.getPlaceRoi().getY() == 5)){
    		if(couleur == ClientJeu.BLANC)
    			return Integer.MAX_VALUE;
    		else
    			return Integer.MIN_VALUE;
    	}
    	if(p.getPlaceRoi().getY() == 0 && (p.getPlaceRoi().getX() == 3 || p.getPlaceRoi().getX() == 4 ||
    			p.getPlaceRoi().getX() == 5)){
    		if(couleur == ClientJeu.BLANC)
    			return Integer.MAX_VALUE;
    		else
    			return Integer.MIN_VALUE;
    	}
    	if(p.getPlaceRoi().getY() == 8 && (p.getPlaceRoi().getX() == 3 || p.getPlaceRoi().getX() == 4 ||
    			p.getPlaceRoi().getX() == 5)){
    		if(couleur == ClientJeu.BLANC)
    			return Integer.MAX_VALUE;
    		else
    			return Integer.MIN_VALUE;
    	}
    	int nbCoupGagnant = 0;
    	for(Mouvement m : p.getMouvementsPossiblesPourUnPoint(p.getPlaceRoi())){
    		if(m.getDest().getX() == 0 && (m.getDest().getY() == 3 || m.getDest().getY() == 4 || m.getDest().getY() == 5)){
    			nbCoupGagnant++;
    			if(nbCoupGagnant == 2){
    				if(couleur == ClientJeu.BLANC)
    	    			return Integer.MAX_VALUE;
    	    		else
    	    			return Integer.MIN_VALUE;
    			}
    		}
    		if(m.getDest().getX() == 8 && (m.getDest().getY() == 3 || m.getDest().getY() == 4 || m.getDest().getY() == 5)){
    			nbCoupGagnant++;
    			if(nbCoupGagnant == 2){
    				if(couleur == ClientJeu.BLANC)
    	    			return Integer.MAX_VALUE;
    	    		else
    	    			return Integer.MIN_VALUE;
    			}
    		}
    		if(m.getDest().getY() == 0 && (m.getDest().getX() == 3 || m.getDest().getX() == 4 || m.getDest().getX() == 5)){
    			nbCoupGagnant++;
    			if(nbCoupGagnant == 2){
    				if(couleur == ClientJeu.BLANC)
    	    			return Integer.MAX_VALUE;
    	    		else
    	    			return Integer.MIN_VALUE;
    			}
    		}
    		if(m.getDest().getY() == 8 && (m.getDest().getX() == 3 || m.getDest().getX() == 4 || m.getDest().getX() == 5)){
    			nbCoupGagnant++;
    			if(nbCoupGagnant == 2){
    				if(couleur == ClientJeu.BLANC)
    	    			return Integer.MAX_VALUE;
    	    		else
    	    			return Integer.MIN_VALUE;
    			}
    		}
    	}
    	if(couleur == ClientJeu.BLANC)
    		eval += 3*(p.getMouvementsPossiblesPourUnPoint(p.getPlaceRoi()).size());
    	else
    		eval -= 3*(p.getMouvementsPossiblesPourUnPoint(p.getPlaceRoi()).size());
    	eval += p.getNbMyPions(couleur);
    	eval -= p.getNbYourPions(couleur);
    	
    	return eval;
    }
    //return (int) (Math.random() * 100);
}
