/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package client;

/**
 * Notre heuristique de jeu. Principe : TODO
 * @author swop
 */
public class HeuristiqueUltimIA implements IHeuristique {
    /**
     * Evalue le plateau donne en paramettre
     * @param p
     * Plateau de jeu
     * @return
     * La valeur heuristique representative du plateau
     */
    public int evalue(Plateau p, int couleur) {
	// TODO Heuristique

	// ---- Heuristique aleatoire ... En attendant de faire mieux ...

	return (int) (Math.random() * 100);
	// ----
    }

}
