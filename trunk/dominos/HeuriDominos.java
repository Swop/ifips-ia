package dominos;

import main.*;
import interfacesJeux.*;

/**
 * Heuristique tr�s simple adapt�e au jeu des dominos vu en cours,
 * compte simplement la diff�rence entre le nombre de placements possibles
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
		System.err.println("La m�thode evalue de dominos.HeuriDominos n'a pas �t� implant� !");
		System.exit(1);
		return 0;
	}

	/* 
	 * @see tpIAJeux.IHeuristique#nomHeuristique()
	 */
	public String nomHeuristique() {
		return "Dominos: vous devez remplir les deux m�thodes.";
	}

	
}
