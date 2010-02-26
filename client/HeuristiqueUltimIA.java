/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package client;

/**
 * Notre heuristique de jeu. Principe : Eviter/favoriser l'encerclement du roi
 * donc augmenter/réduire son champ d'action. Dans un second temps, jouer la
 * balance des pions.
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
    	/*On va déterminer si le roi se fait entourer par des pions au but néfaste.
	 * Pour chaque pion/trône autour du roi, on incrémente la variable encercleRoi.
	 * A partir de 2, cela influence l'heuristique à un niveau moyen.
	 */
	int encercleRoi = 0;
    	try{
	    	//Pion/Trône en X+1
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
	    	//Pion/Trône en X-1
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
		//Pion/Trône en Y+1
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
		//Pion/Trône en Y-1
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
    	catch(HorsJeuException e){/*Si le roi est contre un bord, il est imprenable*/}
    	//Si le roi est pris, heuristique maximale/minimale
	if(p.getRoi().isMort()){
	    if(couleur == ClientJeu.BLANC)
		return Integer.MIN_VALUE + 1;
	    else
		return Integer.MAX_VALUE - 1;
	}
	/*
	 * On étudie le degrés de liberté du roi.
	 * Chaque mouvement possible augement l'heuristique blanche, réciproquement
	 * pour celle des noirs.
	 */
    	if(couleur == ClientJeu.BLANC)
    		eval += 1*(p.getMouvementsPossiblesPourUnPoint(p.getRoi().getPere()).size());
    	else
    		eval -= 1*(p.getMouvementsPossiblesPourUnPoint(p.getRoi().getPere()).size());
	/*
	 * On étudie le nombre de pions restant sur le plateau
	 */
    	eval += 5*(1/p.getNbMyPions(couleur));	//Quand le nombre de mes pions diminue, je suis plus défensif
    	eval -= 5*p.getNbYourPions(couleur);	//Quand le nombre de ses pions diminue, je me concentre sur la victoire
	return eval;
    }

    /**
     * Meta-heuristique rapide qui stop le parcours si le roi arrive sur une case gagnante.
     * @param p
     * Plateau étudié
     * @param couleur
     * Couleur du joueur
     * @return
     * Une valeur max/min si la partie est finie, 0 sinon
     */
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
