package interfacesJeux;

import main.*;

/**
 * Interface permettant, associ� � ICoup, de g�rer les coups 
 * qui supportent la gestion des valeurs de hachage associ�s
 * aux plateaux. Pourquoi g�rer cela avec ICoup ? Simplement
 * parce que les valeurs de hachage sont maintenues au fur
 * et � mesure des coups jou�s, plut�t que recalculer tout
 * depuis un plateau donn�.
 * 
 * @author  L. Simon (Univ. Paris-Sud)- 2006
 *
 */
public interface ICoupHache {

	/**
	 * Une petite fonction sp�cialis� dans l'initialisation
	 * de la valeur de hachage.
	 */
	public void initvaleurHachage();
	
	/**
	 * Permet de mettre � jour la valeur de hachage du plateau
	 * c lorsque l'on joue le coup courant, par le joueur j.
	 * G�n�ralement, effectivement, la valeur de hachage est
	 * maintenue au fur et � mesure des coups jou�s.
	 * 
	 * @param c le plateau de jeu sur lequel on veut jouer
	 * @param j le num�ro du joueur qui doit jouer
	 */
	public void majHashValue(IPlateauHache c, int j);
	
}
