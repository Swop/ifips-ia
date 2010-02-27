package tablut;

import java.util.*;

/**
 * Implementation de l'interface IPile avec une liste chainee
 * @author swop
 */
public class Pile implements IPile {
    /**
     * Liste des items
     */
    private LinkedList<Object> items;
    /**
     * Constructeur de la pile perso
     */
    public Pile() {
	items = new LinkedList<Object>();
    }
    /**
     * Empile un objet
     * @param item L'objet a empiler
     * @return L'objet qui a ete empile
     */
    public Object empiler(Object item) {
	items.addFirst(item);
	return item;
    }
    /**
     * Depile un objet
     * @return L'objet depile
     * @throws EmptyStackException
     */
    public Object depiler() throws EmptyStackException {
	int len = items.size();
	Object item = null;
	if (len == 0) {
	    throw new EmptyStackException();
	}
	item = items.getFirst();
	items.removeFirst();
	return item;
    }
    /**
     * Reseigne si la pile est vide
     * @return TRUE si la liste est vide, FALSE sinon
     */
    public boolean estVide() {
	return (items.size() == 0);
    }
    /**
     * Retourne l'element en haut de la pile
     * @return L'element en haut de la pile
     * @throws EmptyStackException
     */
    public Object sommet() throws EmptyStackException {
	int len = items.size();
	Object item = null;
	if (len == 0) {
	    throw new EmptyStackException();
	}
	item = items.getFirst();
	return item;
    }

    @Override
    public String toString() {
	String s = "-- Pile --";
	int len = items.size();
	Object item;
	int curseur = len-1;
	if (len != 0) {
	    while(curseur >= 0) {
		item = items.get(curseur);
		s += "\n"+item.toString();
		curseur--;
	    }
	}
	return s;
    }
}
