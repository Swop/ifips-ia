/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package client;

import java.util.ArrayList;
/**
 *
 * @author swop
 */
public class Plateau {

    private static Plateau instance = null;

    /*public static int VIDE = 0;
    public static int BLANC = 1;
    public static int NOIR = 2;
    public static int ROI = 3;
    public static int MUR = 4;*/

    public static int NB_CASES = 9;

    private Case[][] cases;
    private Poubelle poubelle;
    private ArrayList<Pion> pionsBlanc;
    private ArrayList<Pion> pionsNoir;
    private Pion roi;

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

	//Placement du Roi
	p = new Roi();
	pionsBlanc.add(p);
	cases[4][4].ajouterPion(p);
	roi = p;
    }

    public final synchronized static Plateau getInstance() {
         if (instance == null)
             instance = new Plateau();
         return instance;
     }

    public Case getCase(int x, int y) throws HorsJeuException {
	if(x >= 0 && x < NB_CASES &&  y >= 0 && y < NB_CASES) {
	    return cases[x][y];
	} else {
	    throw new HorsJeuException();
	}
    }

    public Case getPoubelle() {
	return poubelle;
    }

    /*public void setContenuCase(int x, int y, int valeur) throws HorsJeuException {
	if(x >= 0 && x < NB_CASES &&  y >= 0 && y < NB_CASES) {
	    cases[x][y] = valeur;
	} else {
	    throw new HorsJeuException();
	}
    }*/

    public void deplacerPion(Case orig, Case dest) {
	//if(orig.getContenu() != null) {
	    Pion p = orig.retirerPion();
            dest.ajouterPion(p);
	//}
    }

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
		if(case_temp.getType() != Case.TypeCase.MUR && (case_temp.getType() != Case.TypeCase.TRONE || (case_temp.getContenu() == null)) && case_temp.getContenu() == null)
		    if(case_temp.getType() != Case.TypeCase.TRONE)
                        list.add(new Mouvement(c, case_temp));
		else
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
		if(case_temp.getType() != Case.TypeCase.MUR && (case_temp.getType() != Case.TypeCase.TRONE || (case_temp.getContenu() == null)) && case_temp.getContenu() == null)
		    if(case_temp.getType() != Case.TypeCase.TRONE)
                        list.add(new Mouvement(c, case_temp));
		else
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
		if(case_temp.getType() != Case.TypeCase.MUR && (case_temp.getType() != Case.TypeCase.TRONE || (case_temp.getContenu() == null)) && case_temp.getContenu() == null)
		    if(case_temp.getType() != Case.TypeCase.TRONE)
                        list.add(new Mouvement(c, case_temp));
		else
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
		if(case_temp.getType() != Case.TypeCase.MUR && (case_temp.getType() != Case.TypeCase.TRONE || (case_temp.getContenu() == null)) && case_temp.getContenu() == null)
                    if(case_temp.getType() != Case.TypeCase.TRONE)
                        list.add(new Mouvement(c, case_temp));
		else
		    fin = true;
		temp_x--;
	    } catch (HorsJeuException ex) { fin = true; }
	}

	return list;
    }

    public Pion getRoi(){
	return roi;
    }

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

	
