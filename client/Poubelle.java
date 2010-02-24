/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package client;

/**
 *
 * @author swop
 */
public class Poubelle extends Case {
    private Pile pile;

    public Poubelle() {
	super(Case.TypeCase.POUBELLE, -1, -1);
	pile = new Pile();
    }

    @Override
    public void ajouterPion(Pion p) {
	p.tuer();
	pile.empiler(p);
	p.setPere(this);
    }

    @Override
    public Pion retirerPion() {
	Pion p = (Pion)pile.depiler();
	p.ressuciter();
	return p;
    }
}
