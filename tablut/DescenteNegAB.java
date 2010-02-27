package tablut;

import java.util.ArrayList;

/**
 * Iplementation de la descente NegAlphaBeta
 * @author swop
 */
public class DescenteNegAB implements IDescente {
    /**
     * Pile de mouvement gardant trace de l'historique des mouvements appliques aux pieces du plateau
     */
    private Pile mouvements;
    /**
     * Dernier mouvement applique
     */
    private Mouvement lastMove;

    /**
     * Constructeur de l'algorithme de descente
     */
    public DescenteNegAB() {
	mouvements = new Pile();
	lastMove = null;
    }

    /**
     * Trouve le meilleur coup possible en lancant un NegAlphaBeta sur le plateau passee en parrametre, selon l'heuristique passee en parrametre.
     * @param heuristique
     * L'heuristique a utiliser pour evaluer les feuilles
     * @param p
     * Le plateau courant
     * @param couleur_joueur
     * Couleur du joueur
     * @return
     * Le coup a jouer
     */
    public String trouveMeilleurCoup(IHeuristique heuristique, Plateau p, int couleur_joueur) {
	while(!mouvements.estVide())
	    mouvements.depiler();
	//System.out.println(mouvements);
	int best = Integer.MIN_VALUE;
	int idBest = -1;
	int profondeur = 3;
	ArrayList<Mouvement> mouv_possibles = p.getMouvementsPossibles(couleur_joueur);
	for(int i=0; i<mouv_possibles.size(); i++){
	    //System.out.println("i : "+i);
	    mouvements.empiler(mouv_possibles.get(i));
	    try {
		ArrayList<Mouvement> mouvementsPoubelle = mouv_possibles.get(i).appliquerMouvement(p);
                //System.out.print("Plateau après mouvement :\n"+p.toString());
		// Si le mouvement a entrainee la prise d'autres pieces, on applique leur mouvement vers la poubelle
		for(Mouvement m : mouvementsPoubelle) {
		    m.appliquerMouvement(p);
                    mouvements.empiler(m);
                }
	    } catch (HorsJeuException ex) { /* Mauvais mouvement */ }
	    int negb = -negAB(p, heuristique, getCouleurEnnemi(couleur_joueur), profondeur-1,Integer.MIN_VALUE,Integer.MAX_VALUE);
	    //System.out.println("Retour en haut. Heuristique : "+negb);
	    //System.out.println(mouvements.toString());
	    try {
		// Tant qu'il y a eu des suppression de pieces, on les remet sur le jeu
		while(((Mouvement)mouvements.sommet()).getDest().getType() == Case.TypeCase.POUBELLE) {
                    //System.out.println("Poubelle : "+((Mouvement)(mouvements.sommet())).toString());
                    ((Mouvement)(mouvements.depiler())).appliquerMouvementInverse(p);
                }
                //System.out.println("Pile après poubelle :");
                //System.out.println(mouvements.toString());
		    
		// On effectue ensuite le mouvement inverse
		
		((Mouvement)mouvements.sommet()).appliquerMouvementInverse(p);
              //  System.out.print("Plateau après depilage :\n"+p.toString());
                lastMove = (Mouvement)(mouvements.depiler());
                //System.out.println("Mouvement associé : "+lastMove.toString());
	    } catch (HorsJeuException ex) { /* Mauvais mouvement */ }
	    if(best < negb){
		best = negb;
		idBest = i;
	    }
	}
	if(idBest != -1) {
	    Mouvement mouvement_choisi = mouv_possibles.get(idBest);
            //System.out.println("Meilleur H : "+best+" , Mouvement choisi : "+mouvement_choisi.toString());
	    try {
		ArrayList<Mouvement> mouvementsPoubelle = mouvement_choisi.appliquerMouvement(p);
		for(Mouvement m : mouvementsPoubelle)
		    m.appliquerMouvement(p);

	    } catch (HorsJeuException ex) { /* Mauvais mouvement */ }
	    //System.out.print(p.toString());
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
	//System.out.println("Profondeur : "+profondeur);
	//System.out.println(mouvements.toString());
	int fin = heuristique.evalueFinPartie(p, couleur_joueur);
	if(fin != 0)
	    return fin;
	ArrayList<Mouvement> mouv_possibles = p.getMouvementsPossibles(couleur_joueur);
	if(mouv_possibles.size() == 0 || profondeur == 0){
	    //lastMove = (Mouvement)mouvements.depiler();
	    int heu = heuristique.evalue(p, couleur_joueur);
	    //System.out.println("Heuristique "+heu+", Profondeur : "+profondeur+", Pile : ");
	    //System.out.println(mouvements.toString());
	    return heu;
	}
	for(int i=0; i<mouv_possibles.size(); i++){
	    mouvements.empiler(mouv_possibles.get(i));
	    try {
		ArrayList<Mouvement> mouvementsPoubelle = mouv_possibles.get(i).appliquerMouvement(p);
		// Si le mouvement a entrainee la prise d'autres pieces, on applique leur mouvement vers la poubelle
		for(Mouvement m : mouvementsPoubelle) {
		    m.appliquerMouvement(p);
                    mouvements.empiler(m);
                }
	    } catch (HorsJeuException ex) { /* Mauvais mouvement */ }
	    int val = -negAB(p, heuristique, getCouleurEnnemi(couleur_joueur), profondeur-1,-b,-a);
	    try {
		// Tant qu'il y a eu des suppression de pieces, on les remet sur le jeu
		while(((Mouvement)mouvements.sommet()).getDest().getType() == Case.TypeCase.POUBELLE)
		    ((Mouvement)(mouvements.depiler())).appliquerMouvementInverse(p);
		// On effectue ensuite le mouvement inverse
		lastMove = (Mouvement)(mouvements.depiler());
		lastMove.appliquerMouvementInverse(p);
                //System.out.println("Remonte d'un cran ... Prof : "+profondeur);
		//System.out.println(mouvements);
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
    /**
     * Retourne la couleur opposee a la couleur donnee en paramettre
     * @param couleur Une couleur
     * @return La couleur de l'ennemi
     */
    private int getCouleurEnnemi(int couleur) {
	if(couleur == ClientUltimIA.BLANC)
	    return ClientUltimIA.NOIR;
	else
	    return ClientUltimIA.BLANC;
    }
}
