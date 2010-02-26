/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package client;

/**
 * Interface generalisant le concept de la fonction heuristique.
 * Les differentes fonctions heuristiques proposees devront implementer cette interface.
 * @author swop
 */
public interface IHeuristique {
    /**
     * Evalue le plateau en fonction de l'heuristique.
     * @param p
     * Le plateau
     * @return
     * L'entier representatif de la valeur heuristique calculee.
     */
    public int evalue(Plateau p, int couleur);
    public int evalueFinPartie(Plateau p, int couleur);
}
