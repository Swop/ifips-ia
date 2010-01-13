package dominos;

import interfacesJeux.*;

import main.*;

import java.util.StringTokenizer;
import java.util.Random;



/**
 * Une classe très simple qui étend ICoup pour gérer les coups du jeu des dominos.
 * 
 * @author L. Simon (Univ. Paris-Sud)- 2006
 *
 */
public class CoupDominos 
	implements ICoup {

	/**
	 * La ligne et la colonne du coup interne.
	 */
	protected int ligne, colonne;

	/**
	 * Représente une case vide
	 */
	protected final static int VIDE = PlateauDominos.VIDE;
	/**
	 * Une case avec un domino blanc
	 */
	protected final static int BLANC = PlateauDominos.BLANC;
	/**
	 * Une case avec un domino noir
	 */
	protected final static int NOIR = PlateauDominos.NOIR;
	
	/* Ne sert pas directement dans le TP pour l'instant. Cela
	 * doit permettre de lire un coup depuis une chaine de caractères.
	 * Dans l'idéal, ce sera pour jouer en réseau ou éventuellement lire
	 * directement un coup au clavier (non utilisé dans algosJeux.AlgoChoixHumain).
	 * 
	 * @see interfacesJeux.ICoup#fromString(java.lang.String)
	 */
	public boolean fromString(String lu) {
		 StringTokenizer coupTokenizer = new StringTokenizer(lu, "(),\n\0");
		 try {
			 ligne	= Integer.parseInt(coupTokenizer.nextToken());
			 colonne = Integer.parseInt(coupTokenizer.nextToken());
		 }
		 catch (Exception NumberFormatException) {
			 ligne = 0; colonne = 0;
			 return false;
		 }
		 if ((ligne < 0) || (ligne >= PlateauDominos.TAILLE) ||
				 (colonne < 0) || (colonne>= PlateauDominos.TAILLE))
			 return false;
		 
		 return true;
	}

	
	/**
	 * Constructeur de base de la classe
	 * @param l la ligne du coup
	 * @param c la colonne du coup
	 */
	public CoupDominos(int l, int c) {
		ligne = l;
		colonne = c;
	}

	/* 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "("+ligne+","+colonne+")";
	}
	
	
	/* 
	 * @see interfacesJeux.ICoup#joue(interfacesJeux.IPlateau, int)
	 */
	public IPlateau copieEtJoue(IPlateau plateau, int j) {
		PlateauDominos retvalue = ((PlateauDominos)plateau).copy();
		
		if (j == BLANC) {// Blanc joue
			assert colonne < PlateauDominos.TAILLE - 1;
			retvalue.damier[ligne][colonne] = BLANC;
			retvalue.damier[ligne][colonne+1] = BLANC;
		} else { // Noir joue
			assert ligne < PlateauDominos.TAILLE - 1;
			retvalue.damier[ligne][colonne] = NOIR;
			retvalue.damier[ligne+1][colonne] = NOIR;
		}
		
		return retvalue;
	}
	
    /* 
     * @see interfacesJeux.ICoup#estValide(interfacesJeux.IPlateau, int)
     */
    public boolean estValide(IPlateau plateau, int j){
 		if (j == BLANC) {// Blanc joue
 			return ((colonne < PlateauDominos.TAILLE) && 
 					(((PlateauDominos)plateau).damier[ligne][colonne] == VIDE) &&
 					(((PlateauDominos)plateau).damier[ligne][colonne+1] == VIDE));
 		} else { // Noir joue
			return ((ligne < PlateauDominos.TAILLE) && 
 					(((PlateauDominos)plateau).damier[ligne][colonne] == VIDE) &&
 					(((PlateauDominos)plateau).damier[ligne+1][colonne] == VIDE));
		}
    }
     
}
