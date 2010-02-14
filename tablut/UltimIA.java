/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tablut;


/**
 *
 * @author NorTicUs
 */
public class UltimIA implements IJoueur{

    private int color;

    public void initJoueur(int mycolour) {
	this.color = mycolour;
    }

    public String choixMouvement() {
	throw new UnsupportedOperationException("Not supported yet.");
    }

    
    /**
     * calcul l heuristique d un plateau donne
     * @return la valeur de l heuristique
     */
    public int Heuristique(){
    	
    	int army;
    	army= nombre_pion(color)-nombre_pion(color%2);
    	
    	return 0;
    }
    
    public int nombre_pion(int joueur){
    	
    	int nb_pion=0;
    	
    	for (int i=0;i<9;i++)
    		for(int j=0;j<9;j++)
    			if(tablut.ClientJeu.==joueur)nb_pion++;
    	return nb_pion;
    }
    
    public void declareLeVainqueur(int colour) {
	throw new UnsupportedOperationException("Not supported yet.");
    }

    public void mouvementEnnemi(int startRow, int startCol, int finishRow, int finishCol) {
	throw new UnsupportedOperationException("Not supported yet.");
    }

    public String quadriName() {
	return "The UltimIA";
    }

}
