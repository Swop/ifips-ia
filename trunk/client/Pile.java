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

    private Vector items;

    public Pile() {
	items = new Vector(10);
    }

    public Object empiler(Object item) {
	items.addElement(item);
	return item;
    }

    public Object depiler() throws EmptyStackException {
	int len = items.size();
	Object item = null;
	if (len == 0) {
	    throw new EmptyStackException();
	}
	item = items.elementAt(len - 1);
	items.removeElementAt(len - 1);
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
	item = items.elementAt(len - 1);
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
		item = items.elementAt(curseur);
		s += "\n"+item.toString();
		curseur--;
	    }
	}
	return s;
    }
}
