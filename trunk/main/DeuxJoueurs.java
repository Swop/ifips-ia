package main;

import interfacesJeux.*;

/**
 * Classe très simple implantant l'interface IJoueurs et 
 * permettant de gérer les jeux à deux joueurs. Vous pouvez
 * modifier cette classe pour changer les noms des joueurs (par
 * exemple "Nord" et "Sud" pour l'Awalé plutôt que "Blancs" et 
 * "Noirs").
 * 
 * @author  L. Simon (Univ. Paris-Sud)- 2006
 *
 */
public class DeuxJoueurs 
	implements IJoueurs {

	static String tabNoms[];
	
	static {
		tabNoms = new String[2];
		tabNoms[0] = "Blancs (Horizontal)";
		tabNoms[1] = "Noirs (Vertical)";
	}
	
	/* 
	 * @see interfacesJeux.IJoueurs#joueurSuivant(int)
	 */
	public  int joueurSuivant(int joueur) {
		assert ( (joueur > 0) && (joueur < 3));
		if (joueur == 2)
			return 1;
		assert (joueur == 1);
		return 2;
	}

	/* 
	 * @see interfacesJeux.IJoueurs#nomDeNum(int)
	 */
	public String nomDeNum(int joueur) {
		assert ( (joueur > 0) && (joueur < 3));
		return tabNoms[joueur-1];
	}

	/* 
	 * @see interfacesJeux.IJoueurs#nombreJoueurs()
	 */
	public int nombreJoueurs() {
		return 2;
	}

	/* 
	 * @see interfacesJeux.IJoueurs#numDeNom(java.lang.String)
	 */
	public int numDeNom(String NomJoueur) {
		if (NomJoueur.equals(tabNoms[0]))
			return 1;
		return 2;
	}

	/* 
	 * @see interfacesJeux.IJoueurs#premierJoueur()
	 */
	public int premierJoueur() {
		return 1;
	}

	/* 
	 * @see interfacesJeux.IJoueurs#setNomDeNum(int, java.lang.String)
	 */
	public void setNomDeNum(int num, String nom) {
		if ( (num > 0) && (num < 3))
			tabNoms[num-1] = nom;
	}
}
