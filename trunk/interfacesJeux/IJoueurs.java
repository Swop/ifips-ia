package interfacesJeux;

/**
 * Classe donnée permettant d'implanter un jeu à plusieurs joueurs,
 * leurs noms, etc... C'est un peu artificiel, mais ça permet
 * de rendre le tout un peu générique. Pour tout simplifier, 
 * la classe est implanté dans le package main pour deux joueurs.
 * 
 * Cela permet aussi, tout simplement, de représenter un joueur par
 * un numéro... Tout le reste est caché / géré grâce à cette interface
 * 
 * @author L. Simon (Univ. Paris-Sud)- 2006
 *
 */
public interface IJoueurs {

	/**
	 * Donne le numéro (>0) du joueur correspondant au nom donné.
	 * Non défini si la chaîne donnée ne correspond pas à un nom
	 * d'un joueur.
	 * 
	 * @param NomJoueur la chaine du joueur (par exemple "Blancs")
	 * @return le numéro du joueur correspondant au nom.
	 * @see nomDeNum(int num)
	 */
	public int numDeNom(String NomJoueur);
	
	/**
	 * Donne le nom du joueur correspondant au numéro donné.
	 * Non défini si le numéro n'est pas >0 et <=nbjoueurs
	 * @param num le Numéro du joueur
	 * @return le nom du joueur correspondant (par exemple "Blancs")
	 */
	public String nomDeNum(int num);
	
	/**
	 * Permet de changer dynamiquement le nom des joueurs, 
	 * pour récupérer une classe déjà écrite.
	 * 
	 * @param num
	 * @param nom
	 */
	public void setNomDeNum(int num, String nom);
	
	/**
	 * @return le premier joueur qui joue dans le jeu donné
	 */
	public int premierJoueur();
	
	/**
	 * @param joueur
	 * @return le numéro du joueur suivant
	 */
	public int joueurSuivant(int joueur);
	
	/**
	 * @return nombre de joueur total pour le jeu
	 */
	public int nombreJoueurs();
}
