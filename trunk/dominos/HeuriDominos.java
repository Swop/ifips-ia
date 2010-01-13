package dominos;

import main.*;
import interfacesJeux.*;

/**
 * Heuristique très simple adaptée au jeu des dominos vu en cours,
 * compte simplement la différence entre le nombre de placements possibles
 * de joueur avec son ennemi.
 * 
 * @author L. Simon (Univ. Paris-Sud)- 2006
 *
 */
public class HeuriDominos 
       implements IHeuristique{

	/* 
	 * @see tpIAJeux.IHeuristique#evalue(tpIAJeux.IPlateau, tpIAJeux.Joueur)
	 */
	public int evalue(IPlateau p, int joueur){
		// VOUS DEVEZ FINIR CETTE METHODE !!!
		System.err.println("La méthode evalue de dominos.HeuriDominos n'a pas été implanté !");
		System.exit(1);
		return 0;
	}

	/* 
	 * @see tpIAJeux.IHeuristique#nomHeuristique()
	 */
	public String nomHeuristique() {
		return "Dominos: vous devez remplir les deux méthodes.";
	}

	
}
