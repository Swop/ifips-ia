package algosJeux;

import interfacesJeux.*;

import main.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Vector;



/**
 * Classe étendant IAlgoJeu mais limitée à la demande au clavier du meilleur
 * coup à jouer. Pour appeler cette classe, comme l'utilisation d'heuristique
 * n'a plus de sens, vous pouvez l'appeler avec IHeuristique h=null. 
 * 
 * @author L. Simon (Univ. Paris-Sud)- 2006
 * @see IPlateau#coupsPermis(Joueur)
 */
public class AlgoChoixHumain implements IAlgoJeu {

	/* Renvoie le meilleur coup à jouer donné par le clavier.
	 * Doit renvoyer "null" si aucun coup n'est possible.
	 * Elle utilise IPlateau#coupsPermis pour trouver dans la liste
	 * des coups le coup entré au clavier par l'humain.
	 * 
	 * @see tpIAJeux.IAlgoJeu#meilleurCoup(tpIAJeux.IPlateau, tpIAJeux.IHeuristique, tpIAJeux.Joueur)
	 * @see tpIAJeux.IPlateau#coupsPermis(joueur)
	 */
	public ICoup meilleurCoup(IPlateau depart, IHeuristique h, int joueur){
		ICoup retcoup = null;
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		// Il faut demander à l'utilisateur son choix.
		depart.printPlateau(System.out);
		System.out.println("Vous devez choisir votre coup (retour chariot pour voir tous les coups possibles):");
		System.out.flush();
		boolean boucle = true;
		
		// On boucle tant qu'il n'y a pas de coup valide
		while (boucle) {
			boucle = false;
			String couplu = null;
			try {
				couplu = in.readLine();
			}
			catch (Exception e) {
				System.out.println(e);
			}
			System.out.println("LU:" + couplu);
			// On peut vérifier que le coup est bien dans les coups possibles
			// plutôt que d'utiliser ICoup.fromString(), cela permet de ne pas
			// avoir de problèmes de typages, le type instancié étant trouvé
			// en parcourant tous les coups possibles renvoyés par la classe
			// qui étend IPlateau
			Vector<ICoup> coupPossibles = depart.coupsPermis(joueur);
			boolean coupOK = false;
			for(ICoup coup:coupPossibles) {
				if (coup.toString().equals(couplu)) {
					retcoup = coup;
					coupOK=true;
					break;
				}
			}
			if (!coupOK) {
				if (couplu.equals("\n") || (couplu.equals("")) || (couplu==null)) {
					int cpt = 1;
					for(ICoup coup:coupPossibles)
						System.out.println("coup " + (cpt++) + " : " + coup);
				} else 
					System.out.println("Votre coup n'est pas dans les coups possibles. Veuillez recommencer !");
				boucle = true;
				
			}
			
		}
		System.out.println("Vous voulez jouer le coup " + retcoup);
		return retcoup;
	}

	/* Nom de l'algorithme implantant l'interface
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "Humain au clavier";
	}

}
