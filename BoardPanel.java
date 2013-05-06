import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class BoardPanel extends JPanel implements ChangeListener{

	StyleManager manager;
	Game game;
	Shape[] pits;
	
	public BoardPanel(Game g, StyleManager m){
		this.manager = m;
		this.game = g;
		pits = manager.getPits(this.getWidth(), this.getHeight());
		game.addListener(this);
		this.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e){
				int i = 0;
				while(!pits[i].contains(e.getPoint()))
						++i;
				try{
					game.makeMove(i);
				}catch (IllegalArgumentException f){
					manager.invalidSelection();
				}
			}
		});
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		pits = manager.getPits(this.getWidth(), this.getHeight());
		manager.drawBoard(g2, game);

	}

	@Override
	public void stateChanged(ChangeEvent e) {
		repaint();
	}
}
