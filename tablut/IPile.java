package tablut;

import java.util.EmptyStackException;

/**
 * Interface generalisant le concept d'une pile (LIFO)
 * @author swop
 */
public interface IPile {
    /**
     * Empile un objet
     * @param item L'objet a empiler
     * @return L'objet qui a ete empile
     */
    public Object empiler(Object item);
    /**
     * Depile un objet
     * @return L'objet depile
     * @throws EmptyStackException
     */
    public Object depiler() throws EmptyStackException;
    /**
     * Retourne l'element en haut de la pile
     * @return L'element en haut de la pile
     * @throws EmptyStackException
     */
    public Object sommet() throws EmptyStackException;
    /**
     * Reseigne si la pile est vide
     * @return TRUE si la liste est vide, FALSE sinon
     */
    public boolean estVide();
}