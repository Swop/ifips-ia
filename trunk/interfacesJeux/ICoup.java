package interfacesJeux;

import main.*;

/**
 * Interface permettant de manipuler les coups possibles dans un jeu donné.
 * Bien entendu, il doit être associé aux classes implantant IPlateau et 
 * IHeuristique correspondants. Attention, ceci n'est pas forçément à 
 * réutiliser pour le projet. Vous devez être capable de faire plus efficace.
 * 
 * @author L. Simon (Univ. Paris-Sud)- 2006
 *
 */
public interface ICoup {

	/**
	 * Représenter un coup par une chaine de caractère.
	 * Cela permet de l'imprimer et/ou de le manipuler.
	 * @return la chaine représentant le coup
	 */
	public String toString();
	
	/**
	 * Vérifie qu'un coup est bien possible
	 * 
	 * @param plateau
	 * @param j
	 * @return true si le coup est possible pour le joueur j sur le plateau
	 */
	public boolean estValide(IPlateau plateau, int j);
	
	/**
	 * Renvoie un nouveau plateau de jeu après application du coup.
	 * Attention, il s'agit bien d'une copie de l'autre plateau,
	 * ce qui n'est pas forcément très efficace au final.
	 * 
	 * @param plateau
	 * @param j
	 * @return le nouveau plateau de jeu après application du coup
	 */
	public IPlateau copieEtJoue(IPlateau plateau, int j);


	/**
	 * permet de lire un Coup depuis une chaine de caractere
	 * ou faux si la chaine n'est pas valide. Cela peut servir
	 * à lire un coup au clavier, mais aussi, éventuellement,
	 * à transférer un coup via le réseau pour jouer entre deux 
	 * programmes (comme dans le projet).
	 * 
	 * @param lu
	 * @return true si la chaine est correcte (le coup est mémoriser dans this)
	 */
	public boolean fromString(String lu); 
}
