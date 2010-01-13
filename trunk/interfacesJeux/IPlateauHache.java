package interfacesJeux;

/**
 * Interface pour les plateaux de jeux supportant
 * les algorithmes gérant les valeurs de hachage.
 * 
 * @author  L. Simon (Univ. Paris-Sud)- 2006
 *
 */
public interface IPlateauHache {

	/**
	 * Généralement, la valeur de hachage va être maintenu
	 * au fur et à mesure des coups joués, et non pas calculé
	 * d'après un plateau donné. Cette méthode doit donc simplement 
	 * renvoyer un entier et non pas le calculer. Le maintien est assuré
	 * l'interface ICoupHache.
	 * 
	 * @return la valeur de Hachage associée au plateau
	 * @see ICoupHache
	 */
	public int getHashValue();
	
	/**
	 * Permet de court-circuiter éventuellement le maintien 
	 * de la valeur de hachage, comme par exemple lors
	 * de la lecture de plateau depuis un fichier. Cela
	 * permet de modifier la valeur de hachage associée à un 
	 * plateau.
	 * 
	 * @param v l'entier de hachage. 
	 */
	public void setHashValue(int v);
	
}
