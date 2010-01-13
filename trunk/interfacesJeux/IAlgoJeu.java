package interfacesJeux;

import main.*;

/**
 * Interface permettant d'implanter des interfaces de jeu diff�rentes pour
 * le TP de cours. Attention, ceci n'est pas for��ment � r�utiliser pour 
 * le projet. Vous devez �tre capable de faire plus efficace.
 * 
 * @author L. Simon (Univ. Paris-Sud)- 2006
 *
 */
public interface IAlgoJeu {
	
	/**
	 * M�thode de base pour appeler votre algorithme de recherche
	 * du meilleur coup (Minimax, Alpha-Beta, ID-Alpha-Beta, SSS*...)
	 * 
	 * @param depart Plateau de Jeu sur lequel on doit chercher un coup � jouer
	 * @param joueur C'est le joueur "joueur" qui doit jouer ce coup
	 * @return un "Coup" qui est sens� �tre le meilleur.
	 */
	public ICoup meilleurCoup(IPlateau p, IHeuristique h, int j);
	
	
	/**
	 * Permet de savoir et d'imprimer le nom de l'algorithme utilis�.
	 * 
	 * @return Une cha�ne d�crivant l'algorithme (par ex. "MiniMax")
	 */
	public String toString();
}
