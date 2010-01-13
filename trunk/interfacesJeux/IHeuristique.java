package interfacesJeux;

import main.*;

/**
 * Interface pour la gestion de l'heuristique. Attention, à vous d'ajouter,
 * dans toutes les classes qui l'étendent, les fonctions de constructeur !
 * Attention, ceci n'est pas forçément à réutiliser pour le projet. 
 * Vous devez être capable de faire plus efficace.
 * 
 * @author L. Simon (Univ. Paris-Sud)- 2006
 * @see IPlateau
 * @see ICoup
 */
public interface IHeuristique {
	
	/**
	 * Cette fonction doit évaluer le plateau de jeu quand c'est a joueur de jouer
	 * @param joueur
	 * @return
	 */
	public int evalue(IPlateau plateau, int joueur);

	/**
	 * @return Le nom de la fonction heuristique utilisée
	 */
	public String nomHeuristique();
	
}
