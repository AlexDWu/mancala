import java.awt.Graphics2D;
import java.awt.Shape;


public interface StyleManager {

	Shape[] getPits(int containerWidth, int containerHeight);

	void invalidSelection();

	void drawBoard(Graphics2D g2, Game game);
}
