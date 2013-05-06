import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class MancalaTest {
	
	public static void main(String [] args){
		final JFrame frame = new JFrame();
		final JRadioButton rectangleButton = new JRadioButton("Rectangles");
		final JRadioButton ellipseButton = new JRadioButton("Ellipses");
		ButtonGroup styleGroup = new ButtonGroup();
		styleGroup.add(rectangleButton);
		styleGroup.add(ellipseButton);
		JPanel radioPanel = new JPanel(new GridLayout(0,1));
		radioPanel.add(ellipseButton);
		radioPanel.add(rectangleButton);
		
		final JTextField beadField = new JTextField("4");
		beadField.setColumns(5);
		
		JButton startButton = new JButton("Start");
		startButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				int numBeads = -1;
				try{
					numBeads = Integer.parseInt(beadField.getText());
				} catch (NumberFormatException f){} //doesn't need to do anything
				
				StyleManager style = null;
				if(ellipseButton.isSelected())
					style = new SimpleEllipseStyle();
				else if(rectangleButton.isSelected())
					style = new SimpleRectangleStyle();
				else
					style = null;
				
				if(numBeads > 0 && style != null){
					frame.setVisible(false);
					start(numBeads, style);
				}
			}
		});
		
		frame.add(BorderLayout.WEST, radioPanel);
		frame.add(BorderLayout.EAST, beadField);
		frame.add(BorderLayout.SOUTH, startButton);
		
		frame.setSize(300, 200);
	    frame.setTitle("Setup");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setVisible(true);
	    
	}

	public static void start(int initialNumberBeads, StyleManager manager){
		JFrame frame = new JFrame();
		final Game game = new Game(initialNumberBeads);
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
