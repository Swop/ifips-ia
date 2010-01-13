package interfacesJeux;

import java.util.Vector;
import java.io.PrintStream;

import main.*;

/**
 * Interface permettant de représenter les plateaux de jeu, de manière
 * générique.
 * 
 * @author L. Simon (Univ. Paris-Sud)- 2006
 *
 */
public interface IPlateau {
	
	/**
	 * Cette méthode doit imprimer le plateau de jeu.
	 * 
	 * @param out Là où l'on veut écrire le plateau.
	 */
	public void printPlateau(PrintStream out);
	

	/**
	 * 	L'ensemble des coups permis pour le joueur j depuis
	 * le plateau courant. Il faudra ensuite appeler la méthode
	 * joue(coup, j) pour chaque coup possible du vecteur.
	 *  
	 * @param j le joueur qui doit jouer
	 * @return un vecteur de ICoup légaux pour le joueur j
	 * @see ICoup
	 */
	public Vector<ICoup> coupsPermis(int j);

	
	/**
	 * Vérifie si, étant donné le plateau courant et le joueur j,
	 * le jeu n'est pas, par hasard, fini :)
	 * 
	 * @param j le joueur qui doit jouer
	 * @return le numéro du joueur qui a gagné la partie
	 */
	public int gagnant(int j);
}
