package main;


import interfacesJeux.IAlgoJeu;
import interfacesJeux.ICoup;
import interfacesJeux.IHeuristique;
import interfacesJeux.IPlateau;
import algosJeux.*;
import dominos.*;

/**
 * Classe contenant la méthode main. Cette classe va vous permettre
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
        AlgoJoueur[0] = new AlgoMinimaxProf(); // Il faut remplir la méthode !!!
        AlgoJoueur[1] = new AlgoMinimaxProf(); // Il faut remplir la méthode !!!
		IHeuristique Heuristique = new HeuriDominosProf();
		
		System.out.println("TP Jeux");
		System.out.println("Etat Initial du plateau de jeu:");
		
		boolean jeufini = false;
		ICoup coupcourant = null;
	    int jnum;
	    int gagnant=0;
	    
	    // A chaque itération de la boucle, on fait jouer les deux joueurs
	    // tour a tour
		do {
			jnum = joueurs.premierJoueur();
			
			// on itere sur les deux joueurs, tant que le jeu n'est pas fini
			while( !jeufini ) {
				// On écrit le plateau
				plateauCourant.printPlateau(System.out);
				
				// C'est au joueur jnum à jouer...
				// Vérifions maintenant si l'un d'eux a gagné...
				gagnant = plateauCourant.gagnant(jnum);
				if (gagnant > 0) {
					// L'un des deux a gagné
					jeufini = true;
					break;
				}
				
					System.out.println("C'est au joueur " +
							joueurs.nomDeNum(jnum) + " avec " + 
							AlgoJoueur[jnum-1]+" de jouer.");
					
					// Lancement de l'algo de recherche du meilleur coup
					coupcourant = (ICoup)AlgoJoueur[jnum-1].meilleurCoup(plateauCourant, Heuristique, jnum);

					if (coupcourant == null) {
						System.out.println("Le joueur " + AlgoJoueur[jnum-1]+ " a abandonné !");
						jeufini = true;
						break;
					}
					System.out.println("Coup joué : " + coupcourant);
					
					// Calcul du nouveau plateau de jeu courant
					plateauCourant = coupcourant.copieEtJoue(plateauCourant,jnum);
					
				jnum = joueurs.joueurSuivant(jnum);
			}
			
		} while (!jeufini);
		System.out.println("Le jeu est fini, et c'est le joueur " + joueurs.nomDeNum(gagnant) + " qui a gagné la partie.");
	}
}
