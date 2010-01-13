package dominos;

import interfacesJeux.*;

import main.*;


import java.util.Random;




/**
 * @author L. Simon (Univ. Paris-Sud)- 2006
 *
 */
public class CoupDominosHache
       extends CoupDominos
       implements ICoupHache {

	private static boolean tablechargee = false;
	private static int valeurHachage[][][];

	static {
		Random generateur = new Random();
		System.out.println("Chargement statique de la table de hachage... ");
		valeurHachage = new int[2][PlateauDominos.TAILLE][PlateauDominos.TAILLE];
		for(int joueur=0; joueur<2;joueur++)
			for(int i=0;i<PlateauDominos.TAILLE;i++)
				for(int j=0;j<PlateauDominos.TAILLE;j++)
					valeurHachage[joueur][i][j] = generateur.nextInt();		
		tablechargee = true;
	}

	/**
	 * @param joueur
	 * @param ligne
	 * @param colonne
	 * @return
	 */
	public static int getValeurHachage(int joueur, int ligne, int colonne) {
		return valeurHachage[joueur][ligne][colonne];
	}
	
	/**
	 * @param l
	 * @param c
	 */
	public CoupDominosHache(int l, int c) {
		super(l, c);
		if (!tablechargee)
			initvaleurHachage();
	}
	
	/* (non-Javadoc)
	 * @see interfacesJeux.ICoupHache#initvaleurHachage()
	 */
	public void initvaleurHachage() {
		Random generateur = new Random();
		System.out.println("Chargement de la table");
		valeurHachage = new int[2][PlateauDominos.TAILLE][PlateauDominos.TAILLE];
					for(int joueur=0; joueur<2;joueur++)
			for(int i=0;i<PlateauDominos.TAILLE;i++)
				for(int j=0;j<PlateauDominos.TAILLE;j++)
					valeurHachage[joueur][i][j] = generateur.nextInt();		
		tablechargee = true;
	}
	
	
	/* (non-Javadoc)
	 * @see interfacesJeux.ICoupHache#majHashValue(interfacesJeux.IPlateauHache, int)
	 */
	public void majHashValue(IPlateauHache p, int j) {
		p.setHashValue(p.getHashValue()^valeurHachage[j][ligne][colonne]);
			
	}
	
	/**
	 * @param plateau
	 * @param j
	 * @return
	 */
	public IPlateauHache joue(IPlateauHache plateau, int j) {
		PlateauDominosHache retvalue = ((PlateauDominosHache)plateau).Copie();
		
		if (j == 1) {// Blanc joue
			assert colonne < PlateauDominos.TAILLE - 1;
			retvalue.damier[ligne][colonne] = BLANC;
			plateau.setHashValue(plateau.getHashValue()^valeurHachage[j][ligne][colonne]);			
			retvalue.damier[ligne][colonne+1] = BLANC;
			plateau.setHashValue(plateau.getHashValue()^valeurHachage[j][ligne][colonne+1]);			
		} else { // Noir joue
			assert ligne < PlateauDominos.TAILLE - 1;
			retvalue.damier[ligne][colonne] = NOIR;
			plateau.setHashValue(plateau.getHashValue()^valeurHachage[j][ligne][colonne]);			
			retvalue.damier[ligne+1][colonne] = NOIR;
			plateau.setHashValue(plateau.getHashValue()^valeurHachage[j][ligne+1][colonne]);			
		}
		
		return retvalue;
	}

}
