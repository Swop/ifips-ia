/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tablut;
import java.lang.String;
import java.util.Vector;
import java.util.Vector;

/**
 *
 * @author HerrChaoul
 */
public class PlateauJoueur{
	
	
	boolean VictoireNoir;
	boolean VictoireBlanc;
	private int[][] Plateau;
    private Vector<String> Lmove;   //contient la liste des modifications apportées au plateau de jeu

        /**
         * Creation du plateau de jeu
         * une case vide vaut 0
         * une case contenant un pion blanc vaut 1
         * une case contenant un pion noir vaut 2
         * une case contenant la Roi vaut 3
         * une case a acces restreint vaut 4
         * la case trone vaut 5
         */
	public PlateauJoueur(){

			this.VictoireBlanc=false;
			this.VictoireNoir=false;
            this.Plateau = new int[9][9];

            //initialisation du plateau, toutes les cases sont vides
            for(int i=0;i<9;i++)
                for(int j=0;j<9;j++)
                    this.Plateau[i][j]=0;

            //creation des 4 coins
            this.Plateau[0][0]=4;
            this.Plateau[0][8]=4;
            this.Plateau[8][0]=4;
            this.Plateau[8][8]=4;

            //Placement des pions noirs
            this.Plateau[0][3]=2;
            this.Plateau[0][4]=2;
            this.Plateau[0][5]=2;
            this.Plateau[1][4]=2;

            this.Plateau[8][3]=2;
            this.Plateau[8][4]=2;
            this.Plateau[8][5]=2;
            this.Plateau[7][4]=2;

            this.Plateau[3][0]=2;
            this.Plateau[4][0]=2;
            this.Plateau[5][0]=2;
            this.Plateau[4][1]=2;

            this.Plateau[3][8]=2;
            this.Plateau[4][8]=2;
            this.Plateau[5][8]=2;
            this.Plateau[4][7]=2;

            //Placement des pions blancs

            this.Plateau[4][2]=2;
            this.Plateau[4][3]=2;
            this.Plateau[4][5]=2;
            this.Plateau[4][6]=2;

            this.Plateau[2][4]=2;
            this.Plateau[3][4]=2;
            this.Plateau[5][4]=2;
            this.Plateau[6][4]=2;

            //Placement du Roi
            this.Plateau[4][4]=3;

	};

        /**
         * applique le mouvement choisit
         * @param move le mouvement a faire
         * @return vrai si le mouvement est valide, faux sinon
         */
        public boolean AppliqueMouvement(String move){
            int x1, y1, x2, y2;
            
            String[] mouvement;
            mouvement=move.split(" ");

            x1=Integer.parseInt(mouvement[0])-1;
            y1=Integer.parseInt(mouvement[1])-1;
            x2=Integer.parseInt(mouvement[2])-1;
            y2=Integer.parseInt(mouvement[3])-1;

            this.Plateau[x2][y2]=this.Plateau[x1][y1];
            this.Plateau[x1][y1]=0;
            Lmove.add(move);
            if(x1==4 && y1==4){
                Lmove.add("trone");
                this.Plateau[4][4]=5;
            }
            this.Consequences(x2,y2);//effectue les suppressions de pions ou Roi si le mouvement effectué le permet
            return true;
        }

        /**
         * annule le dernier coup joué
         * @return vrai si l'annulation est faite
         */
        public boolean AnnuleDernierCoup(){
            if(this.Lmove.size()==0)return false;
            this.Annulation(this.Lmove.get(this.Lmove.size()));
            return true;
        }

        /**
         * effectue les prise de pion s'il y en a
         * les suppresions sont enregistrer de cette façon "numero_pion abscisse ordonnée"
         * @param x abscisse du pion
         * @param y ordonnée du pion
         */
        public void Consequences(int x, int y){

            // !!!!!Attention la suppression du roi n'est pas faites !!!!!

            if(x-2>=0 && this.CompareCouleur(this.Plateau[x-2][y], this.Plateau[x][y])==0 && this.CompareCouleur(this.Plateau[x-1][y], this.Plateau[x][y])==1){
                if(this.Plateau[x-1][y]!=3){
                    Lmove.add("%n"+this.Plateau[x-1][y]+" %n"+(x-1)+" %n"+y);
                    this.Plateau[x-1][y]=0;
                }
                else{
                    if(y-1>=0 && y+1<=8 && this.CompareCouleur(this.Plateau[x-1][y-1], this.Plateau[x][y])==0 && this.CompareCouleur(this.Plateau[x-1][y+1], this.Plateau[x][y])==0){
                        Lmove.add("%n"+this.Plateau[x-1][y]+" %n"+(x-1)+" %n"+y);
                        this.Plateau[x-1][y]=0;
                        this.VictoireNoir=true;
                    }
                }
            }
            if(x+2<9 && this.CompareCouleur(this.Plateau[x+2][y], this.Plateau[x][y])==0 && this.CompareCouleur(this.Plateau[x+1][y], this.Plateau[x][y])==1){
                if(this.Plateau[x+1][y]!=3){
                    Lmove.add("%n"+this.Plateau[x+1][y]+" %n"+(x+1)+" %n"+y);
                    this.Plateau[x+1][y]=0;
                }
                else{
                    if(y-1>=0 && y+1<=8 && this.CompareCouleur(this.Plateau[x+1][y-1], this.Plateau[x][y])==0 && this.CompareCouleur(this.Plateau[x+1][y+1], this.Plateau[x][y])==0){
                        Lmove.add("%n"+this.Plateau[x+1][y]+" %n"+(x+1)+" %n"+y);
                        this.Plateau[x+1][y]=0;
                        this.VictoireNoir=true;
                    }
                }
            }
            if(y-2>=0 && this.CompareCouleur(this.Plateau[x][y-2], this.Plateau[x][y])==0 && this.CompareCouleur(this.Plateau[x][y-1], this.Plateau[x][y])==1){
                if(this.Plateau[x][y-1]!=3){
                    Lmove.add("%n"+this.Plateau[x][y-1]+" %n"+x+" %n"+(y-1));
                    this.Plateau[x][y-1]=0;
                 }
                else{
                    if(x-1>=0 && x+1<=8 && this.CompareCouleur(this.Plateau[x+1][y-1], this.Plateau[x][y])==0 && this.CompareCouleur(this.Plateau[x-1][y-1], this.Plateau[x][y])==0){
                        Lmove.add("%n"+this.Plateau[x][y-1]+" %n"+x+" %n"+(y-1));
                        this.Plateau[x][y-1]=0;
                        this.VictoireNoir=true;
                    }
                }
            }
            if(y+2>=0 && this.CompareCouleur(this.Plateau[x][y+2], this.Plateau[x][y])==0 && this.CompareCouleur(this.Plateau[x][y+1], this.Plateau[x][y])==1){
                if(this.Plateau[x][y+1]!=3){
                    Lmove.add("%n"+this.Plateau[x][y+1]+" %n"+x+" %n"+(y+1));
                    this.Plateau[x][y+1]=0;
                }
                else{
                    if(x-1>=0 && x+1<=8 && this.CompareCouleur(this.Plateau[x+1][y+1], this.Plateau[x][y])==0 && this.CompareCouleur(this.Plateau[x-1][y+1], this.Plateau[x][y])==0){
                        Lmove.add("%n"+this.Plateau[x][y+1]+" %n"+x+" %n"+(y+1));
                        this.Plateau[x][y+1]=0;
                        this.VictoireNoir=true;
                    }
                }
            }
        }


        /**
         * annule un coup enregistré préhalablement
         * @param coup le coup a annuler (deplacement de pion ou prise de pion)
         */
        public void Annulation(String move){
            String[] mouvement;
            mouvement=move.split(" ");
            int taille = mouvement.length;
            
            if(taille==4){
                int x1, y1, x2, y2;
                x1=Integer.parseInt(mouvement[0])-1;
                y1=Integer.parseInt(mouvement[1])-1;
                x2=Integer.parseInt(mouvement[2])-1;
                y2=Integer.parseInt(mouvement[3])-1;
                if(x1>=0 && x1<=8 && y1>=0 && y1<=8 && x2>=0 && x2<=8 && y2>=0 && y2<=8){
                    this.Plateau[x1][y1]=this.Plateau[x2][y2];
                    this.Plateau[x2][y2]=0;
                    this.Lmove.removeElementAt(this.Lmove.size()-1);
                }
            }
            else if(taille==3){
                int pion,x,y;
                pion=Integer.parseInt(mouvement[0]);
                x=Integer.parseInt(mouvement[1])-1;
                y=Integer.parseInt(mouvement[2])-1;

                if(x<=8 && x>=0 && y<=8 && y>=0){
                    this.Plateau[x][y]=pion;
                    this.Lmove.removeElementAt(this.Lmove.size()-1);
                    if(pion==3) this.VictoireNoir=false;
                    this.AnnuleDernierCoup();   //ce n'est pas un coup mais une suppression de pion qu'on a annulé!! il faut annuler le mouvement du pion responsable de cette suppression maintenant
                }
            }
            else if(taille==1){
                if(move.compareTo("trone")==0){
                    this.AnnuleDernierCoup();    //ce n'est pas un coup mais un changement d'etat pour le trone !! il faut annuler le mouvement du roi responsable de ce changement
                }

            }
        }


        /**
         * compare les valeurs de deux cases afin de voir si un pion peux etre mangé entre les deux
         * @param   case1 la valeur de la case1
         * @param   case2 la valeur de la case2
         * @return  0 si les deux pions sur ces cases permettent de manger un pion entre elles
         * @return  1 si les deux pions sont de couleur differentes et ne permettent pas de manger un pion entre elles
         * @return  2 si les deux cases ne permettent pas de manger un pion entre elles
         */
        public int CompareCouleur(int case1, int case2){
            if(case1*case2==0 || case1==4 || case2==4) return 2;
            else if(case1==case2 || case1*case2==3 || case1==5 || case2==5) return 0;
            else if(case1*case2==2 || case1*case2==6) return 1;
            else return 404;
        }

        /**
         * renvoit les mouvements possibles pour amener un pion d une couleur donnee a une case
         * @param myColor	la couleur du joueur
         * @param xf		abscisse de la case d arrivee (comprise entre 0 et 8)
         * @param yf		ordonnee de la case d arrivee (comprise entre 0 et 8)
         * @return mesMouv	l ensemble des mouvements possibles 
         */
        public Vector<String> GenereMouvements(int myColor,int xf,int yf){
        	
        	Vector<String> mesMouv = new Vector<String>();
        	
        	if(this.Plateau[xf][yf]!=0) return mesMouv;
        	
        	if(xf<8){
	        	for(int i=xf+1;i<=8;i++){
	        		if(this.Plateau[i][yf]!=0 && this.Plateau[i][yf]!=5){
	        			if(this.CompareCouleur(this.Plateau[i][yf],myColor)!=0) break;
	        			else{
	        				mesMouv.add(i+" "+yf+" "+xf+" "+yf);
	        				break;
	        			}
	        		}
	        	}
        	}
        	
        	if(xf>0){
	        	for(int i=xf-1;i>=0;i--){
	        		if(this.Plateau[i][yf]!=0 && this.Plateau[i][yf]!=5){
	        			if(this.CompareCouleur(this.Plateau[i][yf],myColor)!=0) break;
	        			else{
	        				mesMouv.add((i+1)+" "+(yf+1)+" "+(xf+1)+" "+(yf+1));
	        				break;
	        			}
	        		}
	        	}
        	}
        	
        	if(yf<8){
	        	for(int i=yf+1;i<=8;i++){
	        		if(this.Plateau[xf][i]!=0 && this.Plateau[xf][i]!=5){
	        			if(this.CompareCouleur(this.Plateau[xf][i],myColor)!=0) break;
	        			else{
	        				mesMouv.add((xf+1)+" "+(i+1)+" "+(xf+1)+" "+(yf+1));
	        				break;
	        			}
	        		}
	        	}
        	}
        	
        	if(yf>0){
	        	for(int i=yf-1;i>=0;i--){
	        		if(this.Plateau[xf][i]!=0 && this.Plateau[xf][i]!=5){
	        			if(this.CompareCouleur(this.Plateau[xf][i],myColor)!=0) break;
	        			else{
	        				mesMouv.add((xf+1)+" "+(i+1)+" "+(xf+1)+" "+(yf+1));
	        				break;
	        			}
	        		}
	        	}
        	}
        	
        	return mesMouv;
        	
        }
        
        
        public int Heuristique(int myColor){
        	
        	if(this.VictoireBlanc){
        		if(myColor==1)return Integer.MAX_VALUE;
        		else return Integer.MIN_VALUE;
        	}
        	if(this.VictoireNoir){
        		if(myColor==2)return Integer.MAX_VALUE;
        		else return Integer.MIN_VALUE;
        	}
        	
        	int HeuristiqueNoir=0;
        	int HeuristiqueBlanc=0;
        	
        	for(int i=0;i<9;i++){
        		for(int j=0;j<9;j++){
        			if(this.Color(this.Plateau[i][j])==1){
	        			if(this.Plateau[i][j]==1){
	        				HeuristiqueBlanc+=this.Liberte(i,j);
	        				HeuristiqueBlanc+=this.Mangeable(i,j);
	        				HeuristiqueBlanc+=this.Mangeur(myColor,i,j);
	        			}
	        			else{
	        				HeuristiqueBlanc+=this.Liberte(i,j);
	        				HeuristiqueBlanc+=this.Mangeable(i,j);
	        				HeuristiqueBlanc+=this.Mangeur(i,j);

	        			}
        			}
        			else if(this.Color(this.Plateau[i][j])==2){
        				HeuristiqueNoir+=this.Liberte(i,j);
        				HeuristiqueNoir+=this.Mangeable(i,j);
        				HeuristiqueNoir+=this.Mangeur(i,j);
        			}
        		}
        	}
        	
        	return 0;
        }

        
        public int Mangeur(int xi, int yi){
        	
        	return
        }
        
        /**
         * renvoit le nombre de pion qui peuvent manger un pion placer sur une case
         * @param xf	abscisse de la case
         * @param yf	ordonnee de la case
         * @return		nombre de pions mangeur
         */
        public int Mangeable(int xf, int yf){
        	
        	int nb_mangeur=0; //nombre de pions qui peuvent manger le pion de la case xf, yf
			int couladv = this.ColorAdverse(this.Plateau[xf][yf]);
			int coul = this.Color(this.Plateau[xf][yf]);
        	if(this.Plateau[xf][yf]!=3){
		        	if(xf<8 && xf>0){
		        		if(this.Plateau[xf+1][yf]==5 || this.Color(this.Plateau[xf+1][yf])==couladv) nb_mangeur=this.GenereMouvements(couladv, xf-1, yf).size();
		        		else if(this.Plateau[xf-1][yf]==5 || this.Color(this.Plateau[xf-1][yf])==couladv) nb_mangeur=this.GenereMouvements(couladv, xf+1, yf).size();
		        	}
		        	if(yf<8 && yf>0){
		        		if(this.Plateau[xf][yf+1]==5 || this.Color(this.Plateau[xf][yf+1])==couladv) nb_mangeur=this.GenereMouvements(couladv, xf, yf-1).size();
		        		else if(this.Plateau[xf][yf-1]==5 || this.Color(this.Plateau[xf][yf-1])==couladv) nb_mangeur=this.GenereMouvements(couladv, xf, yf+1).size();
		        	}
        	}
        	else{
        		if(xf<8 && xf>0 && yf<8 && yf>0){
        			if(this.Plateau[xf-1][yf]==0){
        				if((this.Color(this.Plateau[xf+1][yf])==couladv || this.Color(this.Plateau[xf+1][yf])==5) && (this.Color(this.Plateau[xf][yf+1])==couladv || this.Color(this.Plateau[xf][yf+1])==5) && (this.Color(this.Plateau[xf][yf-1])==couladv || this.Color(this.Plateau[xf][yf-1])==5)){
        					nb_mangeur=this.GenereMouvements(couladv, xf-1, yf).size();
        				}
        			}
        			else if(this.Plateau[xf+1][yf]==0){
        				if((this.Color(this.Plateau[xf-1][yf])==couladv || this.Color(this.Plateau[xf-1][yf])==5) && (this.Color(this.Plateau[xf][yf+1])==couladv || this.Color(this.Plateau[xf][yf+1])==5) && (this.Color(this.Plateau[xf][yf-1])==couladv || this.Color(this.Plateau[xf][yf-1])==5)){
        					nb_mangeur=this.GenereMouvements(couladv, xf+1, yf).size();
        				}
        			}
        			else if(this.Plateau[xf][yf+1]==0){
        				if((this.Color(this.Plateau[xf-1][yf])==couladv || this.Color(this.Plateau[xf-1][yf])==5) && (this.Color(this.Plateau[xf+1][yf])==couladv || this.Color(this.Plateau[xf+1][yf])==5) && (this.Color(this.Plateau[xf][yf-1])==couladv || this.Color(this.Plateau[xf][yf-1])==5)){
        					nb_mangeur=this.GenereMouvements(couladv, xf, yf+1).size();
        				}
        			}
        			else if(this.Plateau[xf][yf-1]==0){
        				if((this.Color(this.Plateau[xf-1][yf])==couladv || this.Color(this.Plateau[xf-1][yf])==5) && (this.Color(this.Plateau[xf+1][yf])==couladv || this.Color(this.Plateau[xf+1][yf])==5) && (this.Color(this.Plateau[xf][yf+1])==couladv || this.Color(this.Plateau[xf][yf+1])==5)){
        					nb_mangeur=this.GenereMouvements(couladv, xf, yf-1).size();
        				}
        			}
        		}
        	}
        	return nb_mangeur;
        }        
        
        /**
         * renvoit le degre de liberte d'un pion, c'est a dire le nombre de case ou il peut se deplacer
         * @param xf	abscisse du pion
         * @param yf	ordonnee du pion
         * @return		nombre de cases accessibles
         */
        public int Liberte(int xf, int yf){
        	
        	int lib=0;
        	
        	if(xf<8){
	        	for(int i=xf+1;i<=8;i++){
	        		if(this.Plateau[i][yf]==0) lib++;
	        		else if(this.Plateau[i][yf]!=5) break;
	        	}
        	}
        	
        	if(xf>0){
	        	for(int i=xf-1;i>=0;i--){
	        		if(this.Plateau[i][yf]==0) lib++;
	        		else if(this.Plateau[i][yf]!=5) break;
	        	}
        	}
        	
        	if(yf<8){
	        	for(int i=yf+1;i<=8;i++){
	        		if(this.Plateau[xf][i]==0) lib++;
	        		else if(this.Plateau[xf][i]!=5)break;
	        	}
        	}
        	
        	if(yf>0){
	        	for(int i=yf-1;i>=0;i--){
	        		if(this.Plateau[xf][i]==0) lib++;
	        		else if(this.Plateau[xf][i]!=5)break;
	        	}
        	}
        	
        	return lib;
        }
        
        /**
         * renvoit la couleur du pion
         * @param pion
         * @return	0 si le pion est 'null', 1 si il est blanc, 2 si il est noir
         */
        public int Color(int pion){
        	if(pion==1 || pion==3)return 1;
        	else if(pion==2)return 2;
        	else return 0;
        	
        }
        
        
        /**
         * renvoit la couleur oppose du pion
         * @param pion
         * @return	0 si le pion est 'null', 1 si il est noir, 2 si il est blanc
         */
        public int ColorAdverse(int pion){
        	if(pion==1 || pion==3)return 2;
        	else if(pion==2)return 1;
        	else return 0;
        	
        }
}