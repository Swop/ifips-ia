package tablut;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AppletTablut extends JApplet

{
	final static int WHITE = 1;
	final static int BLACK = 2;
	final static int KING = 3;
	final static int RESTRICTED = 4;
	final static int EMPTY = 0;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JList brdList;
	private TaflBoard displayBoard;
	private JScrollPane scrollPane;
	private DefaultListModel listModel;
	private Frame myFrame;
	
	static int cpt = 0;
	
	// 
	public void init(){
		System.out.println("Initialisation BoardApplet" + cpt++);
		buildUI(getContentPane());
	}
	
	public void buildUI(Container container) {
		setBackground(Color.white);
		
		int[][] temp = new int[11][11];
		
		for( int i = 0; i < 9; i++ )
			for( int j = 0; j < 9; j++ )
				temp[i][j] = 0;
		
		// 
		displayBoard = new TaflBoard( "Coups", temp );
		
		listModel = new DefaultListModel();
		listModel.addElement(displayBoard);
		
		brdList = new JList(listModel);
		brdList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		brdList.setSelectedIndex(0);
		scrollPane = new JScrollPane(brdList);
		Dimension d = scrollPane.getSize();
		scrollPane.setPreferredSize(new Dimension( 110, d.height ));
		
		brdList.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				brdList_keyPressed(e);
			}
		});
		brdList.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				brdList_mouseClicked(e);
			}
		});
		container.add(displayBoard, BorderLayout.CENTER);
		container.add(scrollPane, BorderLayout.EAST);	
	}
	
	// d
	public void update(Graphics g, Insets in)
	{
		Insets tempIn = in;
		g.translate( tempIn.left, tempIn.top);
		paint(g);
	}
	
	public void paint(Graphics g)
	{
		displayBoard.paint(g);
	}
	

	public void addBoard( String move, int[][] board ){
		TaflBoard tempTafl = new TaflBoard( move, board );
		listModel.addElement(new TaflBoard( move, board ));
		brdList.setSelectedIndex(listModel.getSize()-1);
		brdList.ensureIndexIsVisible(listModel.getSize()-1);
		displayBoard = tempTafl;
		update(myFrame.getGraphics(), myFrame.getInsets() );
	}
	
	//
	public void setMyFrame( Frame f ){
		myFrame = f;
	}
	
	// 
	void brdList_keyPressed( KeyEvent e ){
		int index = brdList.getSelectedIndex(); 
		if( e.getKeyCode() == KeyEvent.VK_UP && index > 0 )
			displayBoard = (TaflBoard)listModel.getElementAt(index-1);
		
		if( e.getKeyCode() == KeyEvent.VK_DOWN && index < (listModel.getSize()-1) )
			displayBoard = (TaflBoard)listModel.getElementAt(index+1);
		
		update(myFrame.getGraphics(), myFrame.getInsets() );
		
		
	}
	
	// 
	void brdList_mouseClicked( MouseEvent e ){
		displayBoard = (TaflBoard)listModel.getElementAt(brdList.getSelectedIndex());
		update(myFrame.getGraphics(), myFrame.getInsets());
	}
	
//	Sous classe qui dessine le plateau de jeu
	class TaflBoard extends JPanel{
		
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		int[][] boardState;
		String move;
		
		// The string will be the move details
		// and the array the details of the board
		// after the move has been applied.
		public TaflBoard( String mv, int[][] bs ){
			boardState = bs;
			move = mv;
		}
		
		
		public void drawBoard( Graphics g ){
			// First draw the lines
			g.setColor( new Color( 200, 200, 200 ) );
			g.fillRect( 0, 0, 180, 180 );
			g.setColor( new Color(0, 0, 0) );
			for( int i = 0; i <= 180; i = i + 20 ){
				g.drawLine( i, 0, i, 180);
				g.drawLine( 0, i, 180, i);
			}
			
			// Fill in the reserved squares 
			g.setColor( new Color( 200, 0, 0 ) );
			g.fillRect( 1, 1, 19, 19 );
			g.fillRect( 1, 161, 19, 19 );
			g.fillRect( 161, 1, 19, 19 );
			g.fillRect( 161, 161, 19, 19 );
			// dessin des sorties autorizes
			g.setColor( new Color( 100, 100, 255 ) );
			g.fillRect( 1+20*3, 1+20*0, 19, 19 );
			g.fillRect( 1+20*4, 1+20*0, 19, 19 );
			g.fillRect( 1+20*5, 1+20*0, 19, 19 );
			g.fillRect( 1+20*0, 1+20*3, 19, 19 );
			g.fillRect( 1+20*0, 1+20*4, 19, 19 );
			g.fillRect( 1+20*0, 1+20*5, 19, 19 );
			g.fillRect( 1+20*3, 1+20*8, 19, 19 );
			g.fillRect( 1+20*4, 1+20*8, 19, 19 );
			g.fillRect( 1+20*5, 1+20*8, 19, 19 );
			g.fillRect( 1+20*8, 1+20*3, 19, 19 );
			g.fillRect( 1+20*8, 1+20*4, 19, 19 );
			g.fillRect( 1+20*8, 1+20*5, 19, 19 );
			
			// Dessin du trone
			g.setColor( new Color( 0, 200, 0 ) );
			g.fillRect( 81, 81, 19, 19 );
			g.setColor( new Color(0, 0, 0) );
			g.drawLine(100,81,81,100);
			g.drawLine(81,81,100,100);
//			assert boardState[0][0] > 0;
			// Draw the pieces by referencing boardState array
			for( int i = 0; i < 9; i++ ){
				for( int j = 0; j < 9; j++ ){
					switch( boardState[i][j] ){
					case(WHITE):
						g.setColor( new Color( 255, 255, 255 ) );
					g.fillOval( 20*j+2, 20*i+2, 16, 16 );
					break;
					case(BLACK):
						g.setColor( new Color( 0, 0, 0 ) );
					g.fillOval( 20*j+2, 20*i+2, 16, 16 );
					break;
					
					case(KING):
						g.setColor( new Color( 200, 0 , 0 ) );
					g.fillOval( 20*j+2, 20*i+2, 16, 16 );
					}
				}
			}
		}
		
		public void paint(Graphics g){
			drawBoard( g );
		}
		
		public void update(Graphics g){
			drawBoard(g);
		}
		
		public String toString(){
			return move;
		}
	}
	
}
