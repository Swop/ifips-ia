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
     * Evalue le plateau donne en paramettre
     * @param p
     * Plateau de jeu
     * @param couleur
     * Couleur du joueur
     * @return
     * La valeur heuristique representative du plateau
     */
    public int evalue(Plateau p, int couleur) {
	int eval = 0;
	eval += this.evalueFinPartie(p, couleur);
    	int nbCoupGagnant = 0;
    	/*for(Mouvement m : p.getMouvementsPossiblesPourUnPoint(p.getRoi().getPere())){
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
    	}*/
    	int encercleRoi = 0;
    	try{
	    	if(p.getCase(p.getRoi().getPere().getX()+1, p.getRoi().getPere().getY()).getType() == Case.TypeCase.TRONE || (p.getCase(p.getRoi().getPere().getX()+1,
	    			p.getRoi().getPere().getY()).getContenu() != null && p.getCase(p.getRoi().getPere().getX()+1,
	    					p.getRoi().getPere().getY()).getContenu().getType() == Pion.TypePion.NOIR)){
	    		encercleRoi++;
			if(encercleRoi > 1){
			    if(couleur == ClientJeu.BLANC)
				    eval -= 3*(encercleRoi-1);
			    else
				    eval += 3*(encercleRoi-1);
			}
	    	}
	    	if(p.getCase(p.getRoi().getPere().getX()-1, p.getRoi().getPere().getY()).getType() == Case.TypeCase.TRONE || (p.getCase(p.getRoi().getPere().getX()-1,
	    			p.getRoi().getPere().getY()).getContenu() != null && p.getCase(p.getRoi().getPere().getX()-1,
	    			p.getRoi().getPere().getY()).getContenu().getType() == Pion.TypePion.NOIR)){
	    		encercleRoi++;
	    		if(encercleRoi > 1){
			    if(couleur == ClientJeu.BLANC)
				    eval -= 3*(encercleRoi-1);
			    else
				    eval += 3*(encercleRoi-1);
			}
	    	}
	    	if(p.getCase(p.getRoi().getPere().getX(), p.getRoi().getPere().getY()+1).getType() == Case.TypeCase.TRONE || (p.getCase(p.getRoi().getPere().getX(),
	    			p.getRoi().getPere().getY()+1).getContenu() != null && p.getCase(p.getRoi().getPere().getX(),
	    			p.getRoi().getPere().getY()+1).getContenu().getType() == Pion.TypePion.NOIR)){
	    		encercleRoi++;
	    		if(encercleRoi > 1){
			    if(couleur == ClientJeu.BLANC)
				    eval -= 3*(encercleRoi-1);
			    else
				    eval += 3*(encercleRoi-1);
			}
	    	}
	    	if(p.getCase(p.getRoi().getPere().getX(), p.getRoi().getPere().getY()-1).getType() == Case.TypeCase.TRONE || (p.getCase(p.getRoi().getPere().getX(),
	    			p.getRoi().getPere().getY()-1).getContenu() != null && p.getCase(p.getRoi().getPere().getX(),
	    			p.getRoi().getPere().getY()-1).getContenu().getType() == Pion.TypePion.NOIR)){
	    		encercleRoi++;
	    		if(encercleRoi > 1){
			    if(couleur == ClientJeu.BLANC)
				    eval -= 3*(encercleRoi-1);
			    else
				    eval += 3*(encercleRoi-1);
			}
	    	}
    	}
    	catch(HorsJeuException e){}
    	if(p.getRoi().isMort()){
	    if(couleur == ClientJeu.BLANC)
		return Integer.MIN_VALUE;
	    else
		return Integer.MAX_VALUE;
	}
    	if(couleur == ClientJeu.BLANC)
    		eval += 3*(p.getMouvementsPossiblesPourUnPoint(p.getRoi().getPere()).size());
    	else
    		eval -= 3*(p.getMouvementsPossiblesPourUnPoint(p.getRoi().getPere()).size());
    	eval += 2*p.getNbMyPions(couleur);
    	eval -= 2*p.getNbYourPions(couleur);
	/*try{
	    if(p.getCase(0,3).getContenu() != null){
		if(couleur == ClientJeu.BLANC)
			eval --;
		else
			eval ++;
	    }
	    if(p.getCase(0,4).getContenu() != null){
		if(couleur == ClientJeu.BLANC)
			eval --;
		else
			eval ++;
	    }
	    if(p.getCase(0,5).getContenu() != null){
		if(couleur == ClientJeu.BLANC)
			eval --;
		else
			eval ++;
	    }
	    if(p.getCase(8,3).getContenu() != null){
		if(couleur == ClientJeu.BLANC)
			eval --;
		else
			eval ++;
	    }
	    if(p.getCase(8,4).getContenu() != null){
		if(couleur == ClientJeu.BLANC)
			eval --;
		else
			eval ++;
	    }
	    if(p.getCase(8,5).getContenu() != null){
		if(couleur == ClientJeu.BLANC)
			eval --;
		else
			eval ++;
	    }
	    if(p.getCase(3,0).getContenu() != null){
		if(couleur == ClientJeu.BLANC)
			eval --;
		else
			eval ++;
	    }if(p.getCase(4,0).getContenu() != null){
		if(couleur == ClientJeu.BLANC)
			eval --;
		else
			eval ++;
	    }if(p.getCase(5,0).getContenu() != null){
		if(couleur == ClientJeu.BLANC)
			eval --;
		else
			eval ++;
	    }if(p.getCase(3,8).getContenu() != null){
		if(couleur == ClientJeu.BLANC)
			eval --;
		else
			eval ++;
	    }if(p.getCase(4,8).getContenu() != null){
		if(couleur == ClientJeu.BLANC)
			eval --;
		else
			eval ++;
	    }if(p.getCase(4,8).getContenu() != null){
		if(couleur == ClientJeu.BLANC)
			eval --;
		else
			eval ++;
	    }
	}
	catch(HorsJeuException e){}*/

    	return eval;
    }

    public int evalueFinPartie(Plateau p, int couleur) {
	int eval = 0;
    	if(p.getRoi().getPere().getX() == 0 && (p.getRoi().getPere().getY() == 3 || p.getRoi().getPere().getY() == 4 ||
    			p.getRoi().getPere().getY() == 5)){
    		if(couleur == ClientJeu.BLANC)
    			return Integer.MAX_VALUE - 1;
    		else
    			return Integer.MIN_VALUE + 1;
    	}
    	if(p.getRoi().getPere().getX() == 8 && (p.getRoi().getPere().getY() == 3 || p.getRoi().getPere().getY() == 4 ||
    			p.getRoi().getPere().getY() == 5)){
    		if(couleur == ClientJeu.BLANC)
    			return Integer.MAX_VALUE - 1;
    		else
    			return Integer.MIN_VALUE + 1;
    	}
    	if(p.getRoi().getPere().getY() == 0 && (p.getRoi().getPere().getX() == 3 || p.getRoi().getPere().getX() == 4 ||
    			p.getRoi().getPere().getX() == 5)){
    		if(couleur == ClientJeu.BLANC)
    			return Integer.MAX_VALUE - 1;
    		else
    			return Integer.MIN_VALUE + 1;
    	}
    	if(p.getRoi().getPere().getY() == 8 && (p.getRoi().getPere().getX() == 3 || p.getRoi().getPere().getX() == 4 ||
    			p.getRoi().getPere().getX() == 5)){
    		if(couleur == ClientJeu.BLANC)
    			return Integer.MAX_VALUE - 1;
    		else
    			return Integer.MIN_VALUE + 1;
    	}
	return eval;
    }
    //return (int) (Math.random() * 100);
}
