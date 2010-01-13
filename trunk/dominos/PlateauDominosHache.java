package dominos;

import interfacesJeux.IPlateauHache;

/**
 * @author L. Simon (Univ. Paris-Sud)- 2006
 *
 */
public class PlateauDominosHache 
       extends PlateauDominos
       implements IPlateauHache {

	private int hashValue = 0;
	
	/**
	 * 
	 */
	public PlateauDominosHache(){
		super();
	}
	
	/**
	 * @param depuis
	 */
	public PlateauDominosHache(int depuis[][]){
		super(depuis);
		// Il faut impérativement que les valeurs de hachage soient chargées
		for(int i=0; i < TAILLE; i++)
			for (int j=0; j < TAILLE; j++)
				hashValue = (hashValue^CoupDominosHache.getValeurHachage(depuis[i][j]-1,i,j));			
	}
	
	/* (non-Javadoc)
	 * @see interfacesJeux.IPlateauHache#getHashValue()
	 */
	public int getHashValue() {
		return hashValue;
	}

	/* (non-Javadoc)
	 * @see interfacesJeux.IPlateauHache#setHashValue(int)
	 */
	public void setHashValue(int v) {
		hashValue = v;
		
	}

	/**
	 * @return
	 */
	public PlateauDominosHache Copie() {
		return new PlateauDominosHache(this.damier);
	}

	
}
