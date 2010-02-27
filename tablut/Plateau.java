package tablut;

import java.util.ArrayList;
/**
 * Gestion du plateau (singleton)
 * @author swop
 */
public class Plateau {
    /**
     * Instance du singleton
     */
    private static Plateau instance = null;
    /**
     * Nombre de cases du plateau
     */
    public static int NB_CASES = 9;
    /**
     * Cases du plateau
     */
    private Case[][] cases;
    /**
     * Case "poubelle" (stocke l'ensemble des pions manges)
     */
    private Poubelle poubelle;
    /**
     * Liste des pieces blanches
     */
    private ArrayList<Pion> pionsBlanc;
    /**
     * Liste des pieces noires
     */
    private ArrayList<Pion> pionsNoir;
    /**
     * Reference vers le roi
     */
    private Pion roi;
    /**
     * Creation du plateau
     */
    private Plateau() {
	pionsBlanc = new ArrayList<Pion>();
	pionsNoir = new ArrayList<Pion>();

	cases = new Case[NB_CASES][NB_CASES];

	//creation des 4 coins
	cases[0][0] = new Case(Case.TypeCase.MUR, 0, 0);
	cases[0][8] = new Case(Case.TypeCase.MUR, 0, 8);
	cases[8][0] = new Case(Case.TypeCase.MUR, 8, 0);
	cases[8][8] = new Case(Case.TypeCase.MUR, 8, 8);

	// Creation du trone
	cases[4][4] = new Case(Case.TypeCase.TRONE, 4, 4);

	// Creation des cases normales
	for(int i = 0; i< NB_CASES; i++) {
	    for(int j = 0; j< NB_CASES; j++) {
		if((i == 0 && j == 0) ||
			(i == 0 && j == 8) ||
			(i == 8 && j == 0) ||
			(i == 8 && j == 8) ||
			(i == 4 && j == 4))
		    continue;
		// Si il ne s'agit pas d'un mur ou du trone, on cree une case normale
		cases[i][j] = new Case(Case.TypeCase.NORMALE, i, j);
	    }
	}

	// Creation de la poubelle a piece
	poubelle = new Poubelle();

	Pion p;
	//Placement du Roi
	p = new Roi();
	pionsBlanc.add(p);
	cases[4][4].ajouterPion(p);
	roi = p;

	//Placement des pions noirs
	p = new Pion(Pion.TypePion.NOIR);
	pionsNoir.add(p);
	cases[0][3].ajouterPion(p);
	p = new Pion(Pion.TypePion.NOIR);
	pionsNoir.add(p);
	cases[0][4].ajouterPion(p);
	p = new Pion(Pion.TypePion.NOIR);
	pionsNoir.add(p);
	cases[0][5].ajouterPion(p);
	p = new Pion(Pion.TypePion.NOIR);
	pionsNoir.add(p);
	cases[1][4].ajouterPion(p);

	p = new Pion(Pion.TypePion.NOIR);
	pionsNoir.add(p);
	cases[8][3].ajouterPion(p);
	p = new Pion(Pion.TypePion.NOIR);
	pionsNoir.add(p);
	cases[8][4].ajouterPion(p);
	p = new Pion(Pion.TypePion.NOIR);
	pionsNoir.add(p);
	cases[8][5].ajouterPion(p);
	p = new Pion(Pion.TypePion.NOIR);
	pionsNoir.add(p);
	cases[7][4].ajouterPion(p);

	p = new Pion(Pion.TypePion.NOIR);
	pionsNoir.add(p);
	cases[3][0].ajouterPion(p);
	p = new Pion(Pion.TypePion.NOIR);
	pionsNoir.add(p);
	cases[4][0].ajouterPion(p);
	p = new Pion(Pion.TypePion.NOIR);
	pionsNoir.add(p);
	cases[5][0].ajouterPion(p);
	p = new Pion(Pion.TypePion.NOIR);
	pionsNoir.add(p);
	cases[4][1].ajouterPion(p);

	p = new Pion(Pion.TypePion.NOIR);
	pionsNoir.add(p);
	cases[3][8].ajouterPion(p);
	p = new Pion(Pion.TypePion.NOIR);
	pionsNoir.add(p);
	cases[4][8].ajouterPion(p);
	p = new Pion(Pion.TypePion.NOIR);
	pionsNoir.add(p);
	cases[5][8].ajouterPion(p);
	p = new Pion(Pion.TypePion.NOIR);
	pionsNoir.add(p);
	cases[4][7].ajouterPion(p);

	//Placement des pions blancs

	p = new Pion(Pion.TypePion.BLANC);
	pionsBlanc.add(p);
	cases[4][2].ajouterPion(p);
	p = new Pion(Pion.TypePion.BLANC);
	pionsBlanc.add(p);
	cases[4][3].ajouterPion(p);
	p = new Pion(Pion.TypePion.BLANC);
	pionsBlanc.add(p);
	cases[4][5].ajouterPion(p);
	p = new Pion(Pion.TypePion.BLANC);
	pionsBlanc.add(p);
	cases[4][6].ajouterPion(p);

	p = new Pion(Pion.TypePion.BLANC);
	pionsBlanc.add(p);
	cases[2][4].ajouterPion(p);
	p = new Pion(Pion.TypePion.BLANC);
	pionsBlanc.add(p);
	cases[3][4].ajouterPion(p);
	p = new Pion(Pion.TypePion.BLANC);
	pionsBlanc.add(p);
	cases[5][4].ajouterPion(p);
	p = new Pion(Pion.TypePion.BLANC);
	pionsBlanc.add(p);
	cases[6][4].ajouterPion(p);
    }
    /**
     * Retourne l'instance du singleton Plateau
     * @return L'instance du sigleton Plateau
     */
    public final synchronized static Plateau getInstance() {
         if (instance == null)
             instance = new Plateau();
         return instance;
     }

    /**
     * Recupere une case du plateau a partir de ses coordonnees
     * @param x La coordonee x de la case
     * @param y La coordonee y de la case
     * @return La case correspondante
     * @throws HorsJeuException
     */
    public Case getCase(int x, int y) throws HorsJeuException {
	if(x >= 0 && x < NB_CASES &&  y >= 0 && y < NB_CASES) {
	    return cases[x][y];
	} else {
	    throw new HorsJeuException();
	}
    }
    /**
     * Retourne la poubelle
     * @return La poubelle
     */
    public Case getPoubelle() {
	return poubelle;
    }
    /**
     * Deplace le pion d'un endoit du plateau a un autre
     * @param orig Case d'origine
     * @param dest Case de destination
     */
    public void deplacerPion(Case orig, Case dest) {
	Pion p = orig.retirerPion();
	dest.ajouterPion(p);
    }
    /**
     * Retourne les mouvements possibles pour un joueur donne
     * @param couleur Couleur du joueur
     * @return La liste des mouvements possibles pour tous ces pions
     */
    public ArrayList<Mouvement> getMouvementsPossibles(int couleur) {
	ArrayList<Mouvement> list = new ArrayList<Mouvement>();

	ArrayList<Pion> listePions;
	if(couleur == ClientJeu.BLANC)
	    listePions = pionsBlanc;
	else
	    listePions = pionsNoir;

	for(Pion p : listePions) {
	    if(!p.isMort()) {
		list.addAll(getMouvementsPossiblesPourUnPoint(p.getPere()));
	    }
	}
	return list;
    }
    /**
     * Recuperer tous les mouvements possibles pour un pion en particulier
     * @param c La case du pion
     * @return La liste des mouvements du pion
     */
    public ArrayList<Mouvement> getMouvementsPossiblesPourUnPoint(Case c) {
	ArrayList<Mouvement> list = new ArrayList<Mouvement>();
	int x = c.getX();
	int y = c.getY();

	boolean fin;
	int temp_x;
	int temp_y;
	Case case_temp;

	// Mouvements vers le haut
	fin = false;
	temp_x = x;
	temp_y = y - 1;
	while(!fin) {
	    try {
		case_temp = this.getCase(temp_x, temp_y);
		if(case_temp.getType() != Case.TypeCase.MUR && (case_temp.getType() != Case.TypeCase.TRONE || (case_temp.getContenu() == null)) && case_temp.getContenu() == null) {
		    if(case_temp.getType() != Case.TypeCase.TRONE)
                        list.add(new Mouvement(c, case_temp));
                } else
		    fin = true;
		temp_y--;
	    } catch (HorsJeuException ex) { fin = true; }
	}

	// Mouvement vers le bas
	fin = false;
	temp_x = x;
	temp_y = y + 1;
	while(!fin) {
	    try {
		case_temp = this.getCase(temp_x, temp_y);
		if(case_temp.getType() != Case.TypeCase.MUR && (case_temp.getType() != Case.TypeCase.TRONE || (case_temp.getContenu() == null)) && case_temp.getContenu() == null) {
		    if(case_temp.getType() != Case.TypeCase.TRONE)
                        list.add(new Mouvement(c, case_temp));
                } else
		    fin = true;
		temp_y++;
	    } catch (HorsJeuException ex) { fin = true; }
	}

	// Mouvement vers la droite
	fin = false;
	temp_x = x + 1;
	temp_y = y;
	while(!fin) {
	    try {
		case_temp = this.getCase(temp_x, temp_y);
		if(case_temp.getType() != Case.TypeCase.MUR && (case_temp.getType() != Case.TypeCase.TRONE || (case_temp.getContenu() == null)) && case_temp.getContenu() == null) {
		    if(case_temp.getType() != Case.TypeCase.TRONE)
                        list.add(new Mouvement(c, case_temp));
                } else
		    fin = true;
		temp_x++;
	    } catch (HorsJeuException ex) { fin = true; }
	}

	// Mouvement vers la gauche
	fin = false;
	temp_x = x - 1;
	temp_y = y;
	while(!fin) {
	    try {
		case_temp = this.getCase(temp_x, temp_y);
		if(case_temp.getType() != Case.TypeCase.MUR && (case_temp.getType() != Case.TypeCase.TRONE || (case_temp.getContenu() == null)) && case_temp.getContenu() == null) {
                    if(case_temp.getType() != Case.TypeCase.TRONE)
                        list.add(new Mouvement(c, case_temp));
                } else
		    fin = true;
		temp_x--;
	    } catch (HorsJeuException ex) { fin = true; }
	}

	return list;
    }
    /**
     * Retourne une réference vers le roi
     * @return Le roi
     */
    public Pion getRoi(){
	return roi;
    }
    /**
     * Renseigne sur le nombre de pions encore sur le plateau pour un joueur
     * @param couleur La couleur du joueur
     * @return Le nombre de pions restants du joueur
     */
    public int getNbMyPions(int couleur){
    	int pionsVivant = 0;
	if(couleur == ClientJeu.BLANC){
	    for(Pion p : pionsBlanc){
		if(!p.isMort())
		    pionsVivant++;
	    }
	    return pionsVivant;
	}
	else{
	    for(Pion p : pionsNoir){
		if(!p.isMort())
		    pionsVivant++;
	    }
	    return pionsVivant;
	}
    }
    /**
     * Renseigne sur le nombre de pions encore sur le plateau pour l'adevrsaire d'un joueur
     * @param couleur La couleur du joueur
     * @return Le nombre de pions restants de l'aversaire du joueur sont la couleur est fournie en paramètre
     */
    public int getNbYourPions(int couleur){
    	int pionsVivant = 0;
	if(couleur == ClientJeu.BLANC){
	    for(Pion p : pionsNoir){
		if(!p.isMort())
		    pionsVivant++;
	    }
	    return pionsVivant;
	}
	else{
	    for(Pion p : pionsBlanc){
		if(!p.isMort())
		    pionsVivant++;
	    }
	    return pionsVivant;
	}
    }

    @Override
    public String toString() {
	String ret="Plateau\n";
	for(int i=0; i<9; i++){
	    for(int j=0; j<9; j++){
	    if(this.cases[j][i].getContenu()==null) ret+=".";
	    else ret+=this.cases[j][i].getContenu().toString();
	    }
	ret+="\n";
	}
	return ret;
    }
}

	
