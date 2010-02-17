package tablut;

/**
 * Voici l'interface abstraite qu'il suffit d'implanter pour jouer.
 * Ensuite, vous devez utiliser ClientJeu en lui donnant le nom de votre classe
 * pour qu'il la charge et se connecte au serveur.
 * 
 * Vous pouvez utiliser le RandomPlayer pour tester.
 * 
 * @author L. Simon (Univ. Paris-Sud)- 2006
 *
 */

public interface IJoueur {
	
	static final int TAILLE = 9; // Taille du plateau. Pas la peine de la changer !
	static final int NOIR = 2;   // Vous pouvez changer cela en interne si vous le souhaitez
	static final int BLANC = 1;  // Mais pas lors de la conversation avec l'arbitre
	
	/**
	 * L'arbitre vient de lancer votre joueur. Il lui informe par cette méthode
	 * que vous devez jouer dans cette couleur. Vous pouvez utiliser cette méthode
	 * abstraite, ou la méthode constructeur de votre classe, pour initialiser
	 * vos structures.
	 * @param mycolour La couleur dans laquelle vous allez jouer (1=BLANC, 2=NOIR)
	 */
	public void initJoueur(int mycolour);

	/**
	 * C'est ici que vous devez faire appel à votre IA pour trouver le meilleur coup à jouer
	 * sur le plateau courant.
	 * 
	 * @return une chaine décrivant le mouvement. Cette chaine doit être décrite exactement comme sur l'exemple :
	 * String msg = "" + lignePiece + " " + colonnePiece + " " + ligneDestination + " " + colonneDestination + '\0';
	 * System.out.println("Voici mon mouvement : " + msg);
	 */
	public String choixMouvement();	


	
	/**
	 * Méthode appelée par l'arbitre pour désigner le vainqueur. Vous pouvez en profiter pour
	 * imprimer une bannière de joie... Si vous gagnez... 
	 * 
	 * @param colour La couleur du gagnant (BLANC=1, NOIR=2).
	 */
	public void declareLeVainqueur(int colour);
	
	
	/**
	 * On suppose que l'arbitre a vérifié que le mouvement ennemi était bien légal. Il vous informe
	 * là du mouvement ennemi. A vous de répercuter ce mouvement dans vos structures. Comme
	 * par exemple éliminer les pions que ennemi vient de vous prendre par ce mouvement.
	 * Il n'est pas nécessaire de réfléchir déjà à votre prochain coup à jouer : pour cela
	 * l'arbitre appelera ensuite choixMouvement().
	 * 
	 * @param startRow Ligne de départ du mouvement (entre 0 et TAILLE-1), 
	 *                 commençant en haut=0 à bas=(TAILLE-1)
	 * @param startCol Colonne de départ du mouvement (entre 0 et TAILLE-1), 
	 *                 commençant à gauche=0 à droite=(TAILLE-1)
	 * @param finishRow Ligne d'arrivée du mouvement (entre 0 et TAILLE-1),
	 *                  commençant en haut=0 à bas=(TAILLE-1)
	 * @param finishCol Colonne d'arrivée du mouvement (entre 0 et TAILLE-1),
	 * 	                commençant à gauche=0 à droite=(TAILLE-1)
	 */
	public void mouvementEnnemi(int startRow, int startCol, int finishRow, int finishCol);

	/**
	 * @return Le nom de votre quadrinome
	 */
	public String quadriName();

	
}


