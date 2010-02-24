/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package client;

/**
 *
 * @author swop
 */
public class Case {
    public enum TypeCase { MUR, TRONE, NORMALE, POUBELLE };
    private Pion contenu;
    private TypeCase type;

    private int x;
    private int y;

    public Case(TypeCase type, int n_x, int n_y) {
	contenu = null;
	this.type = type;
	x = n_x;
	y = n_y;
    }

    public Case(TypeCase type, int n_x, int n_y, Pion p) {
	this.type = type;
	contenu = p;
	x = n_x;
	y = n_y;
    }

    public void ajouterPion(Pion p) {
	contenu = p;
	p.setPere(this);
    }

    public Pion retirerPion() {
	Pion c = contenu;
	contenu = null;
	return c;
    }

    public TypeCase getType() {
	return type;
    }

    public Pion getContenu() {
	return contenu;
    }

    public int getX() {
	return x;
    }

    public int getY() {
	return y;
    }
}
