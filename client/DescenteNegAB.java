/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package client;

import java.util.ArrayList;

/**
 * Iplementation de la descente NegAlphaBeta
 * @author swop
 */
public class DescenteNegAB implements IDescente {
    private Pile mouvements;
    private Mouvement lastMove;

    public DescenteNegAB() {
	mouvements = new Pile();
	lastMove = null;
    }

    /**
     * Trouve le meilleur coup possible en lancant un NegAlphaBeta sur le plateau passee en parrametre, selon l'heuristique passee en parrametre.
     * @param heuristique
     * L'heuristique a utiliser pour evaluer les feuilles
     * @param plateau
     * Le plateau courant
     * @return
     * Le coup a jouer
     */
    public String trouveMeilleurCoup(IHeuristique heuristique, Plateau p, int couleur_joueur) {
	while(!mouvements.estVide())
	    mouvements.depiler();
	System.out.println(mouvements);
	int best = Integer.MIN_VALUE;
	int idBest = -1;
	int profondeur = 2;
	ArrayList<Mouvement> mouv_possibles = p.getMouvementsPossibles(couleur_joueur);
	for(int i=0; i<mouv_possibles.size(); i++){
	    mouvements.empiler(mouv_possibles.get(i));
	    try {
		ArrayList<Mouvement> mouvementsPoubelle = mouv_possibles.get(i).appliquerMouvement(p);
		// Si le mouvement a entrainee la prise d'autres pieces, on applique leur mouvement vers la poubelle
		for(Mouvement m : mouvementsPoubelle)
		    m.appliquerMouvement(p);
	    } catch (HorsJeuException ex) { /* Mauvais mouvement */ }
	    int negb = -negAB(p, heuristique, getCouleurEnnemi(couleur_joueur), profondeur-1,Integer.MIN_VALUE,Integer.MAX_VALUE);
	    try {
		// Tant qu'il y a eu des suppression de pieces, on les remet sur le jeu
		while(((Mouvement)mouvements.sommet()).getDest().getType() == Case.TypeCase.POUBELLE)
		    ((Mouvement)(mouvements.depiler())).appliquerMouvementInverse(p);
		// On effectue ensuite le mouvement inverse
		// A VOIR !!!!!!! -- lastMove = (Mouvement)(mouvements.depiler());
		((Mouvement)mouvements.sommet()).appliquerMouvementInverse(p);
	    } catch (HorsJeuException ex) { /* Mauvais mouvement */ }
	    if(best < negb){
		best = negb;
		idBest = i;
	    }
	}
	if(idBest != -1) {
	    Mouvement mouvement_choisi = mouv_possibles.get(idBest);
	    try {
		mouvement_choisi.appliquerMouvement(p);
	    } catch (HorsJeuException ex) { /* Mauvais mouvement */ }
	    return mouvement_choisi.toString();
	} else{
	    // Normalement, ici on ne peut plus jouer. Mais on rentre jamais dans ce else vu que le serveur nous aura deja signale la fin de la partie ...
	    return null;
	}
    }

    /**
     * Algorithme recursif du NegAlphaBeta
     * @param p
     * Plateau de jeu
     * @param heuristique
     * Heuristique a utiliser pour l'evaluation des feuilles
     * @param couleur_joueur
     * Couleur du joueur
     * @param profondeur
     * Distance a l'horizon
     * @param a
     * Alpha
     * @param b
     * Beta
     * @return
     * Alpha ou Beta en fonction du niveau
     */
    private int negAB(Plateau p, IHeuristique heuristique, int couleur_joueur, int profondeur, int a, int b) {
	System.out.println(mouvements.toString());
	ArrayList<Mouvement> mouv_possibles = p.getMouvementsPossibles(couleur_joueur);
	if(mouv_possibles.size() == 0 || profondeur == 0){
	    //lastMove = (Mouvement)mouvements.depiler();
	    return heuristique.evalue(p, couleur_joueur);
	}
	for(int i=0; i<mouv_possibles.size(); i++){
	    mouvements.empiler(mouv_possibles.get(i));
	    try {
		ArrayList<Mouvement> mouvementsPoubelle = mouv_possibles.get(i).appliquerMouvement(p);
		// Si le mouvement a entrainee la prise d'autres pieces, on applique leur mouvement vers la poubelle
		for(Mouvement m : mouvementsPoubelle)
		    m.appliquerMouvement(p);
	    } catch (HorsJeuException ex) { /* Mauvais mouvement */ }
	    int val = -negAB(p, heuristique, getCouleurEnnemi(couleur_joueur), profondeur-1,-b,-a);
	    try {
		// Tant qu'il y a eu des suppression de pieces, on les remet sur le jeu
		while(((Mouvement)mouvements.sommet()).getDest().getType() == Case.TypeCase.POUBELLE)
		    ((Mouvement)(mouvements.depiler())).appliquerMouvementInverse(p);
		// On effectue ensuite le mouvement inverse
		lastMove = (Mouvement)(mouvements.depiler());
		lastMove.appliquerMouvementInverse(p);
		System.out.println(mouvements);
	    } catch (HorsJeuException ex) { /* Mauvais mouvement */ }
	    if(val > a){
		a = val;
	    }
	    if(a > b) {
		// Coupe
		//lastMove = (Mouvement)mouvements.depiler();
		return a;
	    }
	}
	//lastMove = (Mouvement)mouvements.depiler();
	return a;
    }

    private int getCouleurEnnemi(int couleur) {
	if(couleur == ClientJeu.BLANC)
	    return ClientJeu.NOIR;
	else
	    return ClientJeu.BLANC;
    }
}
