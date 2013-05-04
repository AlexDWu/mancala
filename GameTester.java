import static org.junit.Assert.*;

import org.junit.Test;

public class GameTester {
	
	@Test
	public void test() {
		Game game = new Game(4);
		for(int i = 0; i < 6; ++i){
			assertEquals(4, game.getPitValue(i));
			assertEquals(4, game.getPitValue(7 + i));
		}
		assertEquals(0, game.getPitValue(6));
		assertEquals(0, game.getPitValue(13));
	}
	
	@Test
	public void testMove(){
		Game game = new Game(4);
		game.setPlayer(Game.Player.A);
		
		// Player A makes a move that gives another turn
		game.makeMove(2);
		assertEquals(4,game.getPitValue(0));
		assertEquals(4,game.getPitValue(1));
		assertEquals(0,game.getPitValue(2));
		assertEquals(5,game.getPitValue(3));
		assertEquals(5,game.getPitValue(4));
		assertEquals(5,game.getPitValue(5));
		assertEquals(1,game.getPitValue(6)); // Player A Mancala
		assertEquals(4,game.getPitValue(7));
		assertEquals(4,game.getPitValue(8));
		assertEquals(4,game.getPitValue(9));
		assertEquals(4,game.getPitValue(10));
		assertEquals(4,game.getPitValue(11));
		assertEquals(4,game.getPitValue(12));
		assertEquals(0,game.getPitValue(13)); // Player B Mancala
		assertEquals(Game.Player.A, game.getCurrentPlayer());
		assertFalse(game.gameOver());
		
		//undo
		game.undo();
		assertEquals(4,game.getPitValue(0));
		assertEquals(4,game.getPitValue(1));
		assertEquals(4,game.getPitValue(2));
		assertEquals(4,game.getPitValue(3));
		assertEquals(4,game.getPitValue(4));
		assertEquals(4,game.getPitValue(5));
		assertEquals(0,game.getPitValue(6)); // Player A Mancala
		assertEquals(4,game.getPitValue(7));
		assertEquals(4,game.getPitValue(8));
		assertEquals(4,game.getPitValue(9));
		assertEquals(4,game.getPitValue(10));
		assertEquals(4,game.getPitValue(11));
		assertEquals(4,game.getPitValue(12));
		assertEquals(0,game.getPitValue(13)); // Player B Mancala
		assertEquals(Game.Player.A, game.getCurrentPlayer());
		assertFalse(game.gameOver());
		
		//second undo does nothing
		game.undo();
		assertEquals(4,game.getPitValue(0));
		assertEquals(4,game.getPitValue(1));
		assertEquals(4,game.getPitValue(2));
		assertEquals(4,game.getPitValue(3));
		assertEquals(4,game.getPitValue(4));
		assertEquals(4,game.getPitValue(5));
		assertEquals(0,game.getPitValue(6)); // Player A Mancala
		assertEquals(4,game.getPitValue(7));
		assertEquals(4,game.getPitValue(8));
		assertEquals(4,game.getPitValue(9));
		assertEquals(4,game.getPitValue(10));
		assertEquals(4,game.getPitValue(11));
		assertEquals(4,game.getPitValue(12));
		assertEquals(0,game.getPitValue(13)); // Player B Mancala
		assertEquals(Game.Player.A, game.getCurrentPlayer());
		assertFalse(game.gameOver());
		
		// Player A makes a move that gives another turn
		game.makeMove(2);
		assertEquals(4,game.getPitValue(0));
		assertEquals(4,game.getPitValue(1));
		assertEquals(0,game.getPitValue(2));
		assertEquals(5,game.getPitValue(3));
		assertEquals(5,game.getPitValue(4));
		assertEquals(5,game.getPitValue(5));
		assertEquals(1,game.getPitValue(6)); // Player A Mancala
		assertEquals(4,game.getPitValue(7));
		assertEquals(4,game.getPitValue(8));
		assertEquals(4,game.getPitValue(9));
		assertEquals(4,game.getPitValue(10));
		assertEquals(4,game.getPitValue(11));
		assertEquals(4,game.getPitValue(12));
		assertEquals(0,game.getPitValue(13)); // Player B Mancala
		assertEquals(Game.Player.A, game.getCurrentPlayer());
		assertFalse(game.gameOver());
		
		// Player A makes an invalid move
		try{
			game.makeMove(9);
		}catch(IllegalArgumentException e){
			System.out.println(e.getMessage());
		}
		assertEquals(4,game.getPitValue(0));
		assertEquals(4,game.getPitValue(1));
		assertEquals(0,game.getPitValue(2));
		assertEquals(5,game.getPitValue(3));
		assertEquals(5,game.getPitValue(4));
		assertEquals(5,game.getPitValue(5));
		assertEquals(1,game.getPitValue(6)); // Player A mancala
		assertEquals(4,game.getPitValue(7));
		assertEquals(4,game.getPitValue(8));
		assertEquals(4,game.getPitValue(9));
		assertEquals(4,game.getPitValue(10));
		assertEquals(4,game.getPitValue(11));
		assertEquals(4,game.getPitValue(12));
		assertEquals(0,game.getPitValue(13)); // Player B mancala
		assertEquals(Game.Player.A, game.getCurrentPlayer());
		assertFalse(game.gameOver());
		
		//Player A makes another invalid move
		try{
			game.makeMove(6);
		}catch(IllegalArgumentException e){
			System.out.println(e.getMessage());
		}
		assertEquals(4,game.getPitValue(0));
		assertEquals(4,game.getPitValue(1));
		assertEquals(0,game.getPitValue(2));
		assertEquals(5,game.getPitValue(3));
		assertEquals(5,game.getPitValue(4));
		assertEquals(5,game.getPitValue(5));
		assertEquals(1,game.getPitValue(6)); // Player A mancala
		assertEquals(4,game.getPitValue(7));
		assertEquals(4,game.getPitValue(8));
		assertEquals(4,game.getPitValue(9));
		assertEquals(4,game.getPitValue(10));
		assertEquals(4,game.getPitValue(11));
		assertEquals(4,game.getPitValue(12));
		assertEquals(0,game.getPitValue(13)); // Player B mancala
		assertEquals(Game.Player.A, game.getCurrentPlayer());
		assertFalse(game.gameOver());
		
		//undoes last valid move
		game.undo();
		assertEquals(4,game.getPitValue(0));
		assertEquals(4,game.getPitValue(1));
		assertEquals(4,game.getPitValue(2));
		assertEquals(4,game.getPitValue(3));
		assertEquals(4,game.getPitValue(4));
		assertEquals(4,game.getPitValue(5));
		assertEquals(0,game.getPitValue(6)); // Player A Mancala
		assertEquals(4,game.getPitValue(7));
		assertEquals(4,game.getPitValue(8));
		assertEquals(4,game.getPitValue(9));
		assertEquals(4,game.getPitValue(10));
		assertEquals(4,game.getPitValue(11));
		assertEquals(4,game.getPitValue(12));
		assertEquals(0,game.getPitValue(13)); // Player B Mancala
		assertEquals(Game.Player.A, game.getCurrentPlayer());
		assertFalse(game.gameOver());
		
		// Player A makes a move that gives another turn
		game.makeMove(2);
		assertEquals(4,game.getPitValue(0));
		assertEquals(4,game.getPitValue(1));
		assertEquals(0,game.getPitValue(2));
		assertEquals(5,game.getPitValue(3));
		assertEquals(5,game.getPitValue(4));
		assertEquals(5,game.getPitValue(5));
		assertEquals(1,game.getPitValue(6)); // Player A Mancala
		assertEquals(4,game.getPitValue(7));
		assertEquals(4,game.getPitValue(8));
		assertEquals(4,game.getPitValue(9));
		assertEquals(4,game.getPitValue(10));
		assertEquals(4,game.getPitValue(11));
		assertEquals(4,game.getPitValue(12));
		assertEquals(0,game.getPitValue(13)); // Player B Mancala
		assertEquals(Game.Player.A, game.getCurrentPlayer());
		assertFalse(game.gameOver());
		
		//Player A makes a move
		game.makeMove(5);
		assertEquals(4,game.getPitValue(0));
		assertEquals(4,game.getPitValue(1));
		assertEquals(0,game.getPitValue(2));
		assertEquals(5,game.getPitValue(3));
		assertEquals(5,game.getPitValue(4));
		assertEquals(0,game.getPitValue(5));
		assertEquals(2,game.getPitValue(6)); // Player A mancala
		assertEquals(5,game.getPitValue(7));
		assertEquals(5,game.getPitValue(8));
		assertEquals(5,game.getPitValue(9));
		assertEquals(5,game.getPitValue(10));
		assertEquals(4,game.getPitValue(11));
		assertEquals(4,game.getPitValue(12));
		assertEquals(0,game.getPitValue(13));// Player B mancala
		assertEquals(Game.Player.B, game.getCurrentPlayer());
		assertFalse(game.gameOver());
		
		//Player B makes a move and gets another turn
		game.makeMove(8);
		assertEquals(4,game.getPitValue(0));
		assertEquals(4,game.getPitValue(1));
		assertEquals(0,game.getPitValue(2));
		assertEquals(5,game.getPitValue(3));
		assertEquals(5,game.getPitValue(4));
		assertEquals(0,game.getPitValue(5));
		assertEquals(2,game.getPitValue(6)); // Player A mancala
		assertEquals(5,game.getPitValue(7));
		assertEquals(0,game.getPitValue(8));
		assertEquals(6,game.getPitValue(9));
		assertEquals(6,game.getPitValue(10));
		assertEquals(5,game.getPitValue(11));
		assertEquals(5,game.getPitValue(12));
		assertEquals(1,game.getPitValue(13)); // Player B mancala
		assertEquals(Game.Player.B, game.getCurrentPlayer());
		assertFalse(game.gameOver());
		
		//Player B makes a move
		game.makeMove(12);
		assertEquals(5,game.getPitValue(0));
		assertEquals(5,game.getPitValue(1));
		assertEquals(1,game.getPitValue(2));
		assertEquals(6,game.getPitValue(3));
		assertEquals(5,game.getPitValue(4));
		assertEquals(0,game.getPitValue(5));
		assertEquals(2,game.getPitValue(6)); // Player A mancala
		assertEquals(5,game.getPitValue(7));
		assertEquals(0,game.getPitValue(8));
		assertEquals(6,game.getPitValue(9));
		assertEquals(6,game.getPitValue(10));
		assertEquals(5,game.getPitValue(11));
		assertEquals(0,game.getPitValue(12));
		assertEquals(2,game.getPitValue(13)); // Player B mancala
		assertEquals(Game.Player.A, game.getCurrentPlayer());
		assertFalse(game.gameOver());
		
		//Player A makes a steal
		game.makeMove(0);
		assertEquals(0,game.getPitValue(0));
		assertEquals(6,game.getPitValue(1));
		assertEquals(2,game.getPitValue(2));
		assertEquals(7,game.getPitValue(3));
		assertEquals(6,game.getPitValue(4));
		assertEquals(0,game.getPitValue(5));
		assertEquals(8,game.getPitValue(6)); // Player A mancala
		assertEquals(0,game.getPitValue(7));
		assertEquals(0,game.getPitValue(8));
		assertEquals(6,game.getPitValue(9));
		assertEquals(6,game.getPitValue(10));
		assertEquals(5,game.getPitValue(11));
		assertEquals(0,game.getPitValue(12));
		assertEquals(2,game.getPitValue(13)); // Player B mancala
		assertEquals(Game.Player.B, game.getCurrentPlayer());
		assertFalse(game.gameOver());
		
		//undo
		game.undo();
		assertEquals(5,game.getPitValue(0));
		assertEquals(5,game.getPitValue(1));
		assertEquals(1,game.getPitValue(2));
		assertEquals(6,game.getPitValue(3));
		assertEquals(5,game.getPitValue(4));
		assertEquals(0,game.getPitValue(5));
		assertEquals(2,game.getPitValue(6)); // Player A mancala
		assertEquals(5,game.getPitValue(7));
		assertEquals(0,game.getPitValue(8));
		assertEquals(6,game.getPitValue(9));
		assertEquals(6,game.getPitValue(10));
		assertEquals(5,game.getPitValue(11));
		assertEquals(0,game.getPitValue(12));
		assertEquals(2,game.getPitValue(13)); // Player B mancala
		assertEquals(Game.Player.A, game.getCurrentPlayer());
		assertFalse(game.gameOver());
	}
}
