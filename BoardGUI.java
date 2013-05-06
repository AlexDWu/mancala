import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;


public class BoardGUI 
{
	private Pit[] pit;
    private Pit bigPit1;
    private Pit bigPit2;
    private Mancala mancala;
    
    public BoardGUI(Mancala mancala) 
    {
        this.mancala = mancala;
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        JFrame frame = new JFrame("Mancala");
        frame.setSize(800, 320);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation((screen.width - 500) / 2, (screen.height - 300) / 2);
        frame.setVisible(true);
        Container content = frame.getContentPane();
        content.setLayout(new BorderLayout());
        createPits();
        JPanel center = new JPanel(new GridLayout(2, 6));
        center.setOpaque(false);
        MyPanel panel = new MyPanel();
        panel.setLayout(new BorderLayout());
        panel.add(center, BorderLayout.CENTER);
        addPits(center, panel);
    }
    
    private void addPits(JPanel center, MyPanel panel) 
    {
        panel.add(bigPit1, BorderLayout.EAST);
        panel.add(bigPit2, BorderLayout.WEST);
        for (int i = 11; i > 5; i--) 
        {
                center.add(pits[i]);
        }
       
        for (int i = 0; i < 6; i++) 
        {
                center.add(pits[i]);
        }
    }

    private void createPits() 
    {
        bigPit1 = new BigPit();
        bigPit1.setPit(mancala.getPlayerOne().getBigPit());
        bigPit2 = new BigPot();
        bigPit2.setPit(mancala.getPlayerTwo().getBigPit());
        pits = new Pit[12];
        for (int i = 0; i < pots.length; i++) 
        {
        	pits[i] = new Pit();
            if (i < 6) 
            {
            	pits[i].setPit(mancala.getPlayerOne().getSmallPits()[i]);
            } else 
            {
            	pits[i].setPit(mancala.getPlayerTwo().getSmallPits()[i - 6]);
            }
        }
    }
}
