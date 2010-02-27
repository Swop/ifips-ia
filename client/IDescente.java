package client;

/**
 * Interface generalisant le principe de descente. Les algorithme de descente (Alpha-Beta, Minimax, Interative Deep. etc ...) doivent implementer cette interface.
 * @author swop
 */
public interface IDescente {
    /**
     * Retourne le meilleur coup a jouer.
     * @param heuristique
     * L'heuristique a utiliser pour evaluer les feuilles de l'horizon.
     * @param plateau
     * Le plateau actuel.
     * @param couleurJoueur
     * La couleur du joueur
     * @return
     * La chaine de caractere decrivant le meilleur coup a jouer.
     */
    public String trouveMeilleurCoup(IHeuristique heuristique, Plateau p, int couleurJoueur);
}
