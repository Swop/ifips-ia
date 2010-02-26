/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.util.*;

/**
 *
 * @author swop
 */
public class Pile implements IPile {

    private LinkedList<Object> items;

    public Pile() {
	items = new LinkedList<Object>();
    }

    public Object empiler(Object item) {
	items.addFirst(item);
	return item;
    }

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

    public boolean estVide() {
	return (items.size() == 0);
    }

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
