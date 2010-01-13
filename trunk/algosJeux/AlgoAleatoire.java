/**
 * 
 */
package algosJeux;

import interfacesJeux.*;

import main.*;

import java.util.Vector;
import java.util.Random;


/**
 * Petite classe de rien du tout qui joue aléatoirement au jeu qu'on lui a donné.
 * Comme pour le joueur au clavier, on peut mettre IHeuristique à null.
 * 
 * @author L. Simon (Univ. Paris-Sud)- 2006
 *
 */
public class AlgoAleatoire 
       implements IAlgoJeu {

	private static Random randgen = new Random();
		
	/* Vraiment tout simple, on tire au sort un coup dans toute la liste
	 * des coups possibles... Deux lignes et voilà un joueur aléatoire !
	 *  
	 * @see tpIAJeux.IAlgoJeu#meilleurCoup(tpIAJeux.IPlateau, tpIAJeux.IHeuristique, tpIAJeux.Joueur)
	 */
	public ICoup meilleurCoup(IPlateau depart, IHeuristique h, int j) {
		Vector<ICoup> coupPossibles = depart.coupsPermis(j);
		int size = coupPossibles.size();
		if (size > 0 )
			return coupPossibles.get(randgen.nextInt(size));
		else
			return null;
	}
	
	/* 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "Joueur Aléatoire";
	}

}
