package dominos;

import interfacesJeux.ICoup;
import interfacesJeux.IPlateau;

import java.io.PrintStream;
import java.util.Vector;

import main.*;

/**
 * Classe gérant les plateaux de jeu des dominos.
 * 
 * @author L. Simon (Univ. Paris-Sud)- 2006
 *
 */
public class PlateauDominos 
	implements IPlateau {
	/**
	 * Taille du plateau de jeu
	 */
	public final static int TAILLE = 7;

	/**
	 * Damier TAILLExTAILLE qui représente le plateau.
	 * En représentant ainsi, on perd la représentation
	 * des dominos 2x1 en les confondant avec deux pions
	 * 1x1 mis côtes à côtes.
	 */
	protected int damier[][];
	
	protected final static int VIDE = 0;
	@SuppressWarnings("unused")
	protected final static int BLANC = 1;
	@SuppressWarnings("unused")
	protected final static int NOIR = 2;

	
	public PlateauDominos(){
		damier = new int[TAILLE][TAILLE];
		for(int i=0; i < TAILLE; i++)
			for (int j=0; j < TAILLE; j++)
				damier[i][j] = VIDE;
	}
	
	/**
	 * constructeur permettant d'initialiser le damier avec des
	 * dominos donnés dans le tableau d'entiers depuis[][].
	 * @param depuis représente la position des pions.
	 */
	public PlateauDominos(int depuis[][]) {
		damier = new int[TAILLE][TAILLE];
		for(int i=0; i < TAILLE; i++)
			for (int j=0; j < TAILLE; j++)
				damier[i][j] = depuis[i][j];
	}
	
	public PlateauDominos copy() {
		return new PlateauDominos(this.damier);
	}
	
	/* 
	 * Implante les coups permis pour les dominos.
	 * @see interfacesJeux.IPlateau#coupsPermis(int)
	 */
	public Vector<ICoup> coupsPermis(int joueur) {
		Vector<ICoup> vecret = new Vector<ICoup>();
		
		if (joueur == BLANC) { // Blanc
			for(int i=0 ; i < TAILLE ; i++) { // toutes les lignes
				for (int j=0 ; j < TAILLE - 1 ; j++) { // regarde sur une ligne
					if( (damier[i][j]==VIDE) && (damier[i][j+1]==VIDE) ) { // on peut jouer
						vecret.add(new CoupDominos(i,j));
					}
				}
			}
			
		} else { // Noir
			for(int i=0 ; i < PlateauDominos.TAILLE ; i++) { // toutes les lignes
				for (int j=0 ; j < PlateauDominos.TAILLE - 1 ; j++) { // regarde sur une ligne
					if( (damier[j][i]==VIDE) && (damier[j+1][i]==VIDE) ) { // on peut jouer
						vecret.add(new CoupDominos(j,i));
					}
				}
			}
	
		}
		return vecret;
	}

	/* 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String retstr = new String("");
		for(int i=0; i < TAILLE; i++) {
			for (int j=0; j < TAILLE; j++)
				if (damier[i][j]==0)
					retstr += "-";
				else if (damier[i][j]==1)
					retstr += "O";
				else // damier[i][j] == 2
					retstr += "#";
			retstr += "\n";
		}
		return retstr;
	}
	
	/* 
	 * @see interfacesJeux.IPlateau#printPlateau(java.io.PrintStream)
	 */
	public void printPlateau(PrintStream out) {
		out.println(this.toString());		
	}
		
	public int[][] getDamier() {
		return damier;
	}

	/* 
	 * @see interfacesJeux.IPlateau#gagnant(int)
	 */
	public int gagnant(int j) {
		if (coupsPermis(j).size()>0)
			return 0;
		return Main.joueurs.joueurSuivant(j);
	}
}
