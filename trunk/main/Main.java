package main;


import interfacesJeux.IAlgoJeu;
import interfacesJeux.ICoup;
import interfacesJeux.IHeuristique;
import interfacesJeux.IPlateau;
import algosJeux.*;
import dominos.*;

/**
 * Classe contenant la m�thode main. Cette classe va vous permettre
 * de tester votre algorithme de recherche de meilleur coup.
 * 
 * @author L. Simon (Univ. Paris-Sud)- 2006
 *
 */
public class Main {


	private static IPlateau plateauCourant = new PlateauDominos();

	public static DeuxJoueurs joueurs = null;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		joueurs = new DeuxJoueurs();
		IAlgoJeu AlgoJoueur[] = new IAlgoJeu[2];
        AlgoJoueur[0] = new AlgoMinimaxProf(); // Il faut remplir la m�thode !!!
        AlgoJoueur[1] = new AlgoMinimaxProf(); // Il faut remplir la m�thode !!!
		IHeuristique Heuristique = new HeuriDominosProf();
		
		System.out.println("TP Jeux");
		System.out.println("Etat Initial du plateau de jeu:");
		
		boolean jeufini = false;
		ICoup coupcourant = null;
	    int jnum;
	    int gagnant=0;
	    
	    // A chaque it�ration de la boucle, on fait jouer les deux joueurs
	    // tour a tour
		do {
			jnum = joueurs.premierJoueur();
			
			// on itere sur les deux joueurs, tant que le jeu n'est pas fini
			while( !jeufini ) {
				// On �crit le plateau
				plateauCourant.printPlateau(System.out);
				
				// C'est au joueur jnum � jouer...
				// V�rifions maintenant si l'un d'eux a gagn�...
				gagnant = plateauCourant.gagnant(jnum);
				if (gagnant > 0) {
					// L'un des deux a gagn�
					jeufini = true;
					break;
				}
				
					System.out.println("C'est au joueur " +
							joueurs.nomDeNum(jnum) + " avec " + 
							AlgoJoueur[jnum-1]+" de jouer.");
					
					// Lancement de l'algo de recherche du meilleur coup
					coupcourant = (ICoup)AlgoJoueur[jnum-1].meilleurCoup(plateauCourant, Heuristique, jnum);

					if (coupcourant == null) {
						System.out.println("Le joueur " + AlgoJoueur[jnum-1]+ " a abandonn� !");
						jeufini = true;
						break;
					}
					System.out.println("Coup jou� : " + coupcourant);
					
					// Calcul du nouveau plateau de jeu courant
					plateauCourant = coupcourant.copieEtJoue(plateauCourant,jnum);
					
				jnum = joueurs.joueurSuivant(jnum);
			}
			
		} while (!jeufini);
		System.out.println("Le jeu est fini, et c'est le joueur " + joueurs.nomDeNum(gagnant) + " qui a gagn� la partie.");
	}
}
