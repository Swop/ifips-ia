/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package client;

import client.Case.TypeCase;
import client.Pion.TypePion;
import java.util.ArrayList;

/**
 *
 * @author swop
 */
public class Mouvement {
    /*private int x_orig;
    private int y_orig;
    private int x_dest;
    private int y_dest;*/
    private Case orig;
    private Case dest;

    /*public Mouvement(int x_orig, int y_orig, int x_dest, int y_dest) {
	this.x_orig = x_orig;
	this.y_orig = y_orig;
	this.x_dest = x_dest;
	this.y_dest = y_dest;
    }*/

    public Mouvement(Case n_orig, Case n_dest) {
	orig = n_orig;
	dest = n_dest;
    }

    /*public int getXOrig() {
	return this.x_orig;
    }

    public int getYOrig() {
	return this.y_orig;
    }

    public int getXDest() {
	return this.x_dest;
    }

    public int getYDest() {
	return this.y_dest;
    }*/

    public Case getOrig() {
	return orig;
    }

    public Case getDest() {
	return dest;
    }

    public ArrayList<Mouvement> appliquerMouvement(Plateau p) throws HorsJeuException {
		ArrayList<Mouvement> listeMouvementPoubelle = new ArrayList<Mouvement>();
	
		if(orig.getContenu() == null) {
		    int a = 1;
		   //System.out.println("Pas de pion han!");
		    //System.out.println(p.toString());
		    return listeMouvementPoubelle;
		}
	    //System.out.println("Ca marche");
		TypePion type = orig.getContenu().getType();
		p.deplacerPion(orig, dest);
	
		if(dest.getType() != TypeCase.POUBELLE) {
		    /* Si il ne s'agit pas d'un mouvement d'une piece vers la poubelle, on cherche a savoir
		    si ce mouvement entraine la prise de pions adverses */
	
		    Mouvement pieceMangee;
		    // ----- Detection des pieces mangees
		    // Detection a droite
		    pieceMangee = mangerPieceEventuelleSurUnCote(type, dest, 1, 0);
		    if(pieceMangee != null)
			listeMouvementPoubelle.add(pieceMangee);
		    // Detection a gauche
		    pieceMangee = mangerPieceEventuelleSurUnCote(type, dest, -1, 0);
		    if(pieceMangee != null)
			listeMouvementPoubelle.add(pieceMangee);
		    // Detection en haut
		    pieceMangee = mangerPieceEventuelleSurUnCote(type, dest, 0, -1);
		    if(pieceMangee != null)
			listeMouvementPoubelle.add(pieceMangee);
		    // Detection en bas
		    pieceMangee = mangerPieceEventuelleSurUnCote(type, dest, 0, 1);
		    if(pieceMangee != null)
			listeMouvementPoubelle.add(pieceMangee);
		}
		return listeMouvementPoubelle;
    }

    private Mouvement mangerPieceEventuelleSurUnCote(TypePion type, Case dest, int diff_x, int diff_y) {
	Case a_cote;
	Case deux_cases_plus_loin;

	Mouvement pieceMangee = null;

	try{
	    a_cote = Plateau.getInstance().getCase(dest.getX() + diff_x, dest.getY() + diff_y);
	    deux_cases_plus_loin = Plateau.getInstance().getCase(dest.getX() + diff_x * 2, dest.getY() + diff_y * 2);

	    if(a_cote.getType() != Case.TypeCase.MUR && a_cote.getType() != Case.TypeCase.TRONE &&
		    a_cote.getContenu() != null &&
		    Pion.getCouleurPion(a_cote.getContenu().getType()) == Pion.getCouleurPionAdverse(type)) {
		// Une piece ennemie est a cote ...
		switch(a_cote.getContenu().getType()) {
		    case ROI:
			// Le pion ennemi est un roi ... Il faut etre a 4 pour le manger, ou le coincer a trois sur son trone
			Case case1 = null;
			Case case2 = null;
			if(diff_x != 0) {
			    // Si le roi est a droite ou a gauche, on considere les cases supperieures et inferieurs entourant le roi
			    case1 = Plateau.getInstance().getCase(dest.getX(), dest.getY() + diff_y);
			    case2 = Plateau.getInstance().getCase(dest.getX(), dest.getY() - diff_y);
			} else {
			    // Sinon on considere les cases les cases de droite et gauche entourant le roi
			    case1 = Plateau.getInstance().getCase(dest.getX() + diff_x, dest.getY());
			    case2 = Plateau.getInstance().getCase(dest.getX() - diff_x, dest.getY());
			}
			// A ce stade, les 4 cases entourant le roi existent, sinon on serait dans le catch ;-)
			// On va compter le nombre de pions amis entourant le roi et le "nombre" de trone

			int casesAmis = 1; // Nous sommes deja sur une case a cote du roi !!
			int casesTrone = 0;

			switch(case1.getType()) {
			    case TRONE:
				casesTrone++;
				break;
			    default:
				if(case1.getType() != TypeCase.MUR &&
					case1.getContenu() != null &&
					Pion.getCouleurPion(case1.getContenu().getType()) == Pion.getCouleurPion(type))
				    casesAmis++;
			}
			switch(case2.getType()) {
			    case TRONE:
				casesTrone++;
				break;
			    default:
				if(case2.getType() != TypeCase.MUR &&
					case2.getContenu() != null &&
					Pion.getCouleurPion(case2.getContenu().getType()) == Pion.getCouleurPion(type))
				    casesAmis++;
			}
			switch(deux_cases_plus_loin.getType()) {
			    case TRONE:
				casesTrone++;
				break;
			    default:
				if(deux_cases_plus_loin.getType() != TypeCase.MUR &&
					deux_cases_plus_loin.getContenu() != null &&
					Pion.getCouleurPion(deux_cases_plus_loin.getContenu().getType()) == Pion.getCouleurPion(type))
				    casesAmis++;
			}

			// Si on a 4 pions noirs entourant le roi, ou 3 pions et le trone, le roi est pris ...
			if(casesAmis == 4 || (casesAmis == 3 && casesTrone == 1))
			    pieceMangee = new Mouvement(a_cote, Plateau.getInstance().getPoubelle());


			break;
		    default:
			// Le pion ennemi est un pion classique. Il faut un pion ami encore une case plus loin, ou le trone, pour le manger
			if(deux_cases_plus_loin.getType() == TypeCase.TRONE) {
			    // On mange le pion
			    pieceMangee = new Mouvement(a_cote, Plateau.getInstance().getPoubelle());
			} else {
			    if(deux_cases_plus_loin.getType() != Case.TypeCase.MUR &&
				    deux_cases_plus_loin.getContenu() != null &&
				    Pion.getCouleurPion(deux_cases_plus_loin.getContenu().getType()) == Pion.getCouleurPion(type)) {
				pieceMangee = new Mouvement(a_cote, Plateau.getInstance().getPoubelle());
			    }
			}

			break;
		}
	    }
	} catch (HorsJeuException ex) { /* S'il y a moins de deux cases sur ce cote, aucune piece sera mangee ! */ }

	return pieceMangee;
    }

    public void appliquerMouvementInverse(Plateau p) throws HorsJeuException {
	p.deplacerPion(dest, orig);
	/*p.setContenuCase(x_orig, y_orig, p.getContenuCase(x_dest, y_dest));
	p.setContenuCase(x_dest, y_dest, Plateau.VIDE);*/
    }

    @Override
    public String toString() {
	return ""+orig.getY()+" "+orig.getX()+" "+dest.getY()+" "+dest.getX()+'\0';
    }
}
