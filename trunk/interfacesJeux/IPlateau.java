package interfacesJeux;

import java.util.Vector;
import java.io.PrintStream;

import main.*;

/**
 * Interface permettant de repr�senter les plateaux de jeu, de mani�re
 * g�n�rique.
 * 
 * @author L. Simon (Univ. Paris-Sud)- 2006
 *
 */
public interface IPlateau {
	
	/**
	 * Cette m�thode doit imprimer le plateau de jeu.
	 * 
	 * @param out L� o� l'on veut �crire le plateau.
	 */
	public void printPlateau(PrintStream out);
	

	/**
	 * 	L'ensemble des coups permis pour le joueur j depuis
	 * le plateau courant. Il faudra ensuite appeler la m�thode
	 * joue(coup, j) pour chaque coup possible du vecteur.
	 *  
	 * @param j le joueur qui doit jouer
	 * @return un vecteur de ICoup l�gaux pour le joueur j
	 * @see ICoup
	 */
	public Vector<ICoup> coupsPermis(int j);

	
	/**
	 * V�rifie si, �tant donn� le plateau courant et le joueur j,
	 * le jeu n'est pas, par hasard, fini :)
	 * 
	 * @param j le joueur qui doit jouer
	 * @return le num�ro du joueur qui a gagn� la partie
	 */
	public int gagnant(int j);
}
