package interfacesJeux;

import main.*;

/**
 * Interface permettant, associé à ICoup, de gérer les coups 
 * qui supportent la gestion des valeurs de hachage associés
 * aux plateaux. Pourquoi gérer cela avec ICoup ? Simplement
 * parce que les valeurs de hachage sont maintenues au fur
 * et à mesure des coups joués, plutôt que recalculer tout
 * depuis un plateau donné.
 * 
 * @author  L. Simon (Univ. Paris-Sud)- 2006
 *
 */
public interface ICoupHache {

	/**
	 * Une petite fonction spécialisé dans l'initialisation
	 * de la valeur de hachage.
	 */
	public void initvaleurHachage();
	
	/**
	 * Permet de mettre à jour la valeur de hachage du plateau
	 * c lorsque l'on joue le coup courant, par le joueur j.
	 * Généralement, effectivement, la valeur de hachage est
	 * maintenue au fur et à mesure des coups joués.
	 * 
	 * @param c le plateau de jeu sur lequel on veut jouer
	 * @param j le numéro du joueur qui doit jouer
	 */
	public void majHashValue(IPlateauHache c, int j);
	
}
