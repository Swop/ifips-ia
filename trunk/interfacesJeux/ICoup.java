package interfacesJeux;

import main.*;

/**
 * Interface permettant de manipuler les coups possibles dans un jeu donn�.
 * Bien entendu, il doit �tre associ� aux classes implantant IPlateau et 
 * IHeuristique correspondants. Attention, ceci n'est pas for��ment � 
 * r�utiliser pour le projet. Vous devez �tre capable de faire plus efficace.
 * 
 * @author L. Simon (Univ. Paris-Sud)- 2006
 *
 */
public interface ICoup {

	/**
	 * Repr�senter un coup par une chaine de caract�re.
	 * Cela permet de l'imprimer et/ou de le manipuler.
	 * @return la chaine repr�sentant le coup
	 */
	public String toString();
	
	/**
	 * V�rifie qu'un coup est bien possible
	 * 
	 * @param plateau
	 * @param j
	 * @return true si le coup est possible pour le joueur j sur le plateau
	 */
	public boolean estValide(IPlateau plateau, int j);
	
	/**
	 * Renvoie un nouveau plateau de jeu apr�s application du coup.
	 * Attention, il s'agit bien d'une copie de l'autre plateau,
	 * ce qui n'est pas forc�ment tr�s efficace au final.
	 * 
	 * @param plateau
	 * @param j
	 * @return le nouveau plateau de jeu apr�s application du coup
	 */
	public IPlateau copieEtJoue(IPlateau plateau, int j);


	/**
	 * permet de lire un Coup depuis une chaine de caractere
	 * ou faux si la chaine n'est pas valide. Cela peut servir
	 * � lire un coup au clavier, mais aussi, �ventuellement,
	 * � transf�rer un coup via le r�seau pour jouer entre deux 
	 * programmes (comme dans le projet).
	 * 
	 * @param lu
	 * @return true si la chaine est correcte (le coup est m�moriser dans this)
	 */
	public boolean fromString(String lu); 
}
