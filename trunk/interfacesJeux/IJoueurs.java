package interfacesJeux;

/**
 * Classe donn�e permettant d'implanter un jeu � plusieurs joueurs,
 * leurs noms, etc... C'est un peu artificiel, mais �a permet
 * de rendre le tout un peu g�n�rique. Pour tout simplifier, 
 * la classe est implant� dans le package main pour deux joueurs.
 * 
 * Cela permet aussi, tout simplement, de repr�senter un joueur par
 * un num�ro... Tout le reste est cach� / g�r� gr�ce � cette interface
 * 
 * @author L. Simon (Univ. Paris-Sud)- 2006
 *
 */
public interface IJoueurs {

	/**
	 * Donne le num�ro (>0) du joueur correspondant au nom donn�.
	 * Non d�fini si la cha�ne donn�e ne correspond pas � un nom
	 * d'un joueur.
	 * 
	 * @param NomJoueur la chaine du joueur (par exemple "Blancs")
	 * @return le num�ro du joueur correspondant au nom.
	 * @see nomDeNum(int num)
	 */
	public int numDeNom(String NomJoueur);
	
	/**
	 * Donne le nom du joueur correspondant au num�ro donn�.
	 * Non d�fini si le num�ro n'est pas >0 et <=nbjoueurs
	 * @param num le Num�ro du joueur
	 * @return le nom du joueur correspondant (par exemple "Blancs")
	 */
	public String nomDeNum(int num);
	
	/**
	 * Permet de changer dynamiquement le nom des joueurs, 
	 * pour r�cup�rer une classe d�j� �crite.
	 * 
	 * @param num
	 * @param nom
	 */
	public void setNomDeNum(int num, String nom);
	
	/**
	 * @return le premier joueur qui joue dans le jeu donn�
	 */
	public int premierJoueur();
	
	/**
	 * @param joueur
	 * @return le num�ro du joueur suivant
	 */
	public int joueurSuivant(int joueur);
	
	/**
	 * @return nombre de joueur total pour le jeu
	 */
	public int nombreJoueurs();
}
