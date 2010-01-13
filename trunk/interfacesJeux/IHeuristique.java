package interfacesJeux;

import main.*;

/**
 * Interface pour la gestion de l'heuristique. Attention, � vous d'ajouter,
 * dans toutes les classes qui l'�tendent, les fonctions de constructeur !
 * Attention, ceci n'est pas for��ment � r�utiliser pour le projet. 
 * Vous devez �tre capable de faire plus efficace.
 * 
 * @author L. Simon (Univ. Paris-Sud)- 2006
 * @see IPlateau
 * @see ICoup
 */
public interface IHeuristique {
	
	/**
	 * Cette fonction doit �valuer le plateau de jeu quand c'est a joueur de jouer
	 * @param joueur
	 * @return
	 */
	public int evalue(IPlateau plateau, int joueur);

	/**
	 * @return Le nom de la fonction heuristique utilis�e
	 */
	public String nomHeuristique();
	
}
