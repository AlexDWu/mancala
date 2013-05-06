import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;


public class SimpleRectangleStyle implements StyleManager {
	
	Shape [] pits;

	/**
	 * return pit locations for pit identification on mouse clicks events
	 */
	@Override
	public Shape[] getPits(int containerWidth, int containerHeight) {
		final int BUFFER = 5;
		int pitWidth = containerWidth / 8 - 2 * BUFFER;
		int pitHeight = containerHeight / 2 - 2 * BUFFER;
		int mancalaHeight = containerHeight - 2 * BUFFER;
		
		pits = new Shape [14];
		
		// the pits
		for(int i = 0; i < 6; i++){
			pits[i] = new Rectangle2D.Double((i +1) * (containerWidth / 8) + BUFFER, 
					containerHeight / 2 + BUFFER, pitWidth, pitHeight);
			pits[i + 7] = new Rectangle2D.Double((6 - i) * (containerWidth / 8) + BUFFER,
					BUFFER, pitWidth, pitHeight);
		}
		// the Mancalas
		pits[6] = new Rectangle2D.Double(7 * containerWidth / 8 + BUFFER, BUFFER,
				pitWidth, mancalaHeight);
		pits[13] = new Rectangle2D.Double(BUFFER, BUFFER, pitWidth, mancalaHeight);
		
		return pits;
	}

	@Override
	public void invalidSelection() {
		//simple style does nothing for an invalid selection
	}

	@Override
	public void drawBoard(Graphics2D g2, Game game) {
		if(pits == null)
			return;
		for(int i = 0; i < pits.length; ++i){
			g2.draw(pits[i]);
			g2.drawString(Integer.toString(game.getPitValue(i)),
					(float) pits[i].getBounds2D().getCenterX(), 
					(float) pits[i].getBounds2D().getCenterY());
		}
	}

}
