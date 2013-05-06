import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class MancalaTest {

	public static void main(String [] args){
		JFrame frame = new JFrame();
		final Game game = new Game(1);
		StyleManager manager = new SimpleRectangleStyle();
		BoardPanel board = new BoardPanel(game, manager);
		
		final JTextField statusBox = new JTextField(game.getStatus());
		statusBox.enableInputMethods(false);
		statusBox.setEditable(false);
		
		final JButton undoButton = new JButton("undo");
		undoButton.setEnabled(game.canUndo());
		undoButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				game.undo();
			}
		});

		game.addListener(new ChangeListener(){
			@Override
			public void stateChanged(ChangeEvent e) {
				statusBox.setText(game.getStatus());
				undoButton.setEnabled(game.canUndo());
			}
			
		});
		JPanel southPanel = new JPanel();
		southPanel.setLayout(new FlowLayout());
		statusBox.setColumns(15);
		southPanel.add(statusBox);
		southPanel.add(undoButton);
		frame.add(BorderLayout.CENTER, board);
		frame.add(BorderLayout.SOUTH, southPanel);
		
		frame.setSize(500, 400);
	    frame.setTitle("Mancala");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setVisible(true);
	}
}
