package tablut;

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
    /**
     * Meta-heuristique rapide qui stop le parcours si le roi arrive sur une case gagnante.
     * @param p
     * Plateau étudié
     * @param couleur
     * Couleur du joueur
     * @return
     * Une valeur max/min si la partie est finie, 0 sinon
     */
    public int evalueFinPartie(Plateau p, int couleur);
}
