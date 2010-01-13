package interfacesJeux;

/**
 * Interface pour les plateaux de jeux supportant
 * les algorithmes g�rant les valeurs de hachage.
 * 
 * @author  L. Simon (Univ. Paris-Sud)- 2006
 *
 */
public interface IPlateauHache {

	/**
	 * G�n�ralement, la valeur de hachage va �tre maintenu
	 * au fur et � mesure des coups jou�s, et non pas calcul�
	 * d'apr�s un plateau donn�. Cette m�thode doit donc simplement 
	 * renvoyer un entier et non pas le calculer. Le maintien est assur�
	 * l'interface ICoupHache.
	 * 
	 * @return la valeur de Hachage associ�e au plateau
	 * @see ICoupHache
	 */
	public int getHashValue();
	
	/**
	 * Permet de court-circuiter �ventuellement le maintien 
	 * de la valeur de hachage, comme par exemple lors
	 * de la lecture de plateau depuis un fichier. Cela
	 * permet de modifier la valeur de hachage associ�e � un 
	 * plateau.
	 * 
	 * @param v l'entier de hachage. 
	 */
	public void setHashValue(int v);
	
}
