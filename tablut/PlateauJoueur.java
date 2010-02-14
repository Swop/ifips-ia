/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tablut;
import java.lang.String;
import java.util.Vector;

/**
 *
 * @author HerrChaoul
 */
public class PlateauJoueur{
	
	
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

            //Placement de la Roi
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

            if(mouvement.length!=4)return false ;//erreur si le format d'écriture du coup n'est pas bon

            x1=Integer.parseInt(mouvement[0])-1;
            y1=Integer.parseInt(mouvement[1])-1;
            x2=Integer.parseInt(mouvement[2])-1;
            y2=Integer.parseInt(mouvement[3])-1;

            if(x1<0 || x1>8 || y1<0 || y1>8 || x2<0 || x2>8 || y2<0 || y2>8) return false; //erreur si les coordonnées ne sont pas dans le plateau
            if(this.Plateau[x2][y2]!=0) return false; //erreur si la position final est déjà occupée
            if(!this.Bougeable(this.Plateau[x1][y1]))return false; //faux si ce n'est pas la bonne couleur
            this.Plateau[x2][y2]=this.Plateau[x1][y1];
            this.Plateau[x1][y1]=0;
            Lmove.add(move);
            this.Consequences(x1,y1);//effectue les suppressions de pions ou Roi si le mouvement effectué le permet
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
         * la disponibilité du trone est aussi mise a jour
         * @param x abscisse du pion
         * @param y ordonnée du pion
         */
        public void Consequences(int x, int y){

            // !!!!!Attention la suppression du roi n'est pas faites !!!!!

            if(x-2>=0 && this.Plateau[x-1][y]*this.Plateau[x][y]==2)if(this.Plateau[x-2][y]==this.Plateau[x][y] || this.Plateau[x-2][y]==5){
                                                                        Lmove.add("%n"+this.Plateau[x-1][y]+" %n"+(x-1)+" %n"+y);
                                                                        this.Plateau[x-1][y]=0;
                                                                    }
            if(x+2<9 && this.Plateau[x+1][y]*this.Plateau[x][y]==2)if(this.Plateau[x+2][y]==this.Plateau[x][y] || this.Plateau[x+2][y]==5){
                                                                        Lmove.add("%n"+this.Plateau[x+1][y]+" %n"+(x+1)+" %n"+y);
                                                                        this.Plateau[x+1][y]=0;                                                                     
                                                                    }
            if(y-2>=0 && this.Plateau[x][y-1]*this.Plateau[x][y]==2)if(this.Plateau[x][y-2]==this.Plateau[x][y] || this.Plateau[x][y-2]==5){
                                                                        Lmove.add("%n"+this.Plateau[x][y-1]+" %n"+x+" %n"+(y-1));
                                                                        this.Plateau[x][y-1]=0;
                                                                    }
            if(y+2<9 && this.Plateau[x][y+1]*this.Plateau[x][y]==2)if(this.Plateau[x][y+2]==this.Plateau[x][y] || this.Plateau[x][y+2]==5){
                                                                        Lmove.add("%n"+this.Plateau[x][y+1]+" %n"+x+" %n"+(y+1));
                                                                        this.Plateau[x][y+1]=0;
                                                                    }
            if(x==4 && y==4){
                Lmove.add("trone");
                this.Plateau[4][4]=5;
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
         * renvoie faux si on n'est pas sensé pouvoir jouer le pion (si ce n'est pas notre couleur)
         * @param pion  la couleur du pion a jouer
         * @return  vrai si on peut jouer, faux sinon
         */
        public boolean Bougeable(int pion){
            
            return false;
        }
}