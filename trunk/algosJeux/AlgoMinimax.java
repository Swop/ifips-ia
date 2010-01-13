package algosJeux;

import main.*;
import interfacesJeux.*;

/**
 * 
 * A vous de jouer pour implanter Minimax.
 * Renommez cette classe et lancez vous dans sa programmation !
 * 
 * @author Vous !
 *
 */

public class AlgoMinimax implements IAlgoJeu {
	

	/**
	 * Votre constructeur
	 */
	public AlgoMinimax(){
		// A remplir !!
	}
	
	/* 
	 * ...
	 */
	public String toString() {
		return "MiniMax (a remplir)";
	}
	
	/* 
	 * C'est la m�thode principale que vous devez implanter dans le TP.
	 * C'est bien celle-ci qui fera la base de votre IA.
	 * 
	 * Petites aides :
	 *
	 * - Vous pouvez utiliser Main.joueurs.joueurSuivant(joueur) pour avoir 
	 * le num�ro du joueur suivant (entre 1 et 2 pour deux joueurs).
	 * - Attention, m�me si la m�thode doit renvoyer un ICoup, vous devrez
	 * aussi g�rer la manipulation des valeurs heuristiques qui sont li�es
	 * � chaque ICoup.
	 * 
	 * @see tpIAJeux.IAlgoJeu
	 */
	public ICoup meilleurCoup(IPlateau depart, IHeuristique j, int joueur) {	
		// VOUS DEVEZ FINIR CETTE METHODE !!!
		System.err.println("La m�thode meilleurCoup de algosJeux.AlgoMinimax n'a pas �t� implant� !");
		System.exit(1);
		return null;
	}
	
}
