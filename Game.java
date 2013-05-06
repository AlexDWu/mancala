import java.util.ArrayList;
import java.util.Random;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Runs the Mancala game.
 * The pits in the board are arranged and marked as so
 *    12 11 10  9  8  7
 * 13                   6
 *     0  1  2  3  4  5
 * @author Alex Wu
 *
 */
public class Game {

	private Player currentPlayer;
	private Pit [] pitList;
	private boolean isGameOver;
	
	private ArrayList<ChangeListener> observers;
	
	//undo memory
	private boolean canUndo;
	private Player prevPlayer;
	private Pit[] prevPitList;
	private boolean prevGameOver;
	
	public static int NUM_PITS = 14;	
	public static int NUM_PITS_PER_SIDE = 6;
	
	/**
	 * Creates a new Game object
	 * @param initPit	initial number of stones in a pit.
	 * @precondition initPit > 0;
	 */
	public Game(int initPit){
		pitList = new Pit[NUM_PITS];
		for (int i = 0; i < NUM_PITS_PER_SIDE; ++i){
			pitList[i] = new Pit(initPit);
			pitList[i + NUM_PITS_PER_SIDE + 1] = new Pit(initPit);
		}
		pitList[NUM_PITS_PER_SIDE] = new GoalPit();
		pitList[2 * NUM_PITS_PER_SIDE + 1] = new GoalPit();
		
		Random rand = new Random();
		if(rand.nextBoolean()){
			currentPlayer = Player.A;
		}
		else{
			currentPlayer = Player.B;
		}
		isGameOver = false;
		
		observers = new ArrayList<ChangeListener>();
		canUndo = false;
		prevPlayer = null;
		prevPitList = null;
	}
	
	/**
	 * Retrieves the number of stones in a the pit
	 * @param pitNum number that represents the pit of interest
	 * @return the number of stone in the pit of interest
	 */
	public int getPitValue(int pitNum){
		return pitList[pitNum].getValue();
	}
	
	/**
	 * Makes a move from the selected pit.
	 * @throws IllegalArgumentException if a pit that does not belong to the
	 * current player, or a mancala is selected.
	 * @param pitNum
	 */
	public void makeMove(int pitNum) 
			throws IllegalArgumentException{
		if(currentPlayer == Player.A){
			if(pitNum < 0 || pitNum >= NUM_PITS_PER_SIDE)
				throw new IllegalArgumentException("Can't make this move");
		}else{
			if(pitNum <= NUM_PITS_PER_SIDE || pitNum > 2 * NUM_PITS_PER_SIDE)
				throw new IllegalArgumentException("Can't make this move");
		}
		
		if(isGameOver)
			return;
		// save state
		prevPitList= new Pit[NUM_PITS];
		for(int i = 0; i < NUM_PITS; i++){
			prevPitList[i] = (Pit) pitList[i].clone();
		}
		canUndo = true;

		int currentGoal;
		int oppositeGoal;
		if (currentPlayer == Player.A){
			currentGoal = NUM_PITS_PER_SIDE;
			oppositeGoal = 2 * NUM_PITS_PER_SIDE + 1;
		}
		else{
			currentGoal = 2 * NUM_PITS_PER_SIDE + 1;
			oppositeGoal = NUM_PITS_PER_SIDE;
		}
		int stoneInHand = pitList[pitNum].remove();
		while(stoneInHand > 0){
			pitNum = (pitNum + 1) % NUM_PITS;
			if(pitNum != oppositeGoal){
				pitList[pitNum].add(1);
				--stoneInHand;
			}
		}

		//Steal condition
		if(pitList[pitNum].getValue() == 1 && 
				((currentPlayer == Player.A && pitNum > 0 && pitNum < NUM_PITS_PER_SIDE)||
				(currentPlayer == Player.B && pitNum > NUM_PITS_PER_SIDE &&
				pitNum <= NUM_PITS_PER_SIDE * 2))){
			int oppositePit = 2* NUM_PITS_PER_SIDE - pitNum;
			pitList[currentGoal].add(pitList[pitNum].remove());
			pitList[currentGoal].add(pitList[oppositePit].remove());
		}
		
		//End game condition
		int start = 0;
		int end = NUM_PITS_PER_SIDE;
		if(currentPlayer == Player.B){
			start += 7;
			end += 7;
		}
		int i = start;
		while(pitList[i].getValue() == 0 && i < end)
			++i;
		if(i == end){ // player's pits are empty;
			for(i = 0; i < NUM_PITS_PER_SIDE; ++i){
				pitList[oppositeGoal].add(pitList[oppositeGoal - i - 1].remove());
			}
			prevGameOver = isGameOver;
			isGameOver = true;
		}
		prevPlayer = currentPlayer;
		//change player condition
		if(pitNum != currentGoal){
			if(currentPlayer == Player.A)
				currentPlayer = Player.B;
			else
				currentPlayer = Player.A;
		}
		notifyListeners();
	}
	
	/**
	 * undoes the last valid move
	 */
	public void undo(){
		if(canUndo){
			currentPlayer = prevPlayer;
			pitList = prevPitList;
			canUndo = false;
			isGameOver = prevGameOver;
		}
		notifyListeners();
	}
	
	/**
	 * Finds out if game is over
	 * @return true if the game is over, false if it is not
	 */
	public boolean gameOver(){
		return isGameOver;
	}
	
	/**
	 * Sets the current player.
	 * @param p
	 */
	public void setPlayer(Player p){
		currentPlayer = p;
		canUndo = false;
	}
	
	/**
	 * Retrieves who the current player
	 * @return
	 */
	public Player getCurrentPlayer(){
		return currentPlayer;
	}
	
	/**
	 * adds a change listener
	 * @param l
	 */
	public void addListener(ChangeListener l){
		observers.add(l);
	}
	
	private void notifyListeners(){
		for(ChangeListener l : observers)
			l.stateChanged(new ChangeEvent(this));
	}
	
	public enum Player {
		A,B;
	}
	
	/**
	 * A pit in the board
	 */
	private class Pit implements Cloneable{
		private int value;
		
		/**
		 * Creates a pit
		 * @param init initial number of stones in the pit
		 * @param owner the owner of the pit
		 * @throws IllegalArgumentException
		 */
		protected Pit(int init) throws IllegalArgumentException{
			if(init < 0)
				throw new IllegalArgumentException();
			value = init;
		}
		
		/**
		 * Removes all the stone in the pit and returns the number of stones removed
		 * @return the number of stones removed
		 */
		protected int remove(){
			int temp = value;
			value = 0;
			return temp;
		}
		
		/**
		 * adds amount of stones to the pit
		 */
		protected void add(int amount){
			value += amount;
		}
		
		/**
		 * returns the number of stones in the pit
		 * @return the number of stones in the pit
		 */
		public int getValue(){
			return value;
		}
		
		@Override
		public Pit clone(){
			Pit cloned = null;
			try {
				cloned = (Pit) super.clone();
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
			return cloned;
		}
	}
	
	/**
	 * a Mancala Pit
	 */
	private class GoalPit extends Pit{
		protected GoalPit(){
			super(0);
		}
		
		@Override
		/**
		 * can't remove from a GoalPit
		 */
		protected int remove() throws UnsupportedOperationException{
			throw new UnsupportedOperationException("can't remove from Mancala");
		}
		
		@Override
		public GoalPit clone(){
			GoalPit cloned = null;
			cloned = (GoalPit) super.clone();
			return cloned;
		}
	}

	/**
	 * A string that indicates game status;
	 * @return
	 */
	public String getStatus() {
		if(isGameOver){
			String out = "Game Over: ";
			if(pitList[13].getValue() > pitList[6].getValue())
				out += "Player B wins";
			else if(pitList[6].getValue() > pitList[13].getValue())
				out += "Player A wins";
			else
				out += "Tie game";
			return out;
		}
		if(currentPlayer == Player.A)
			return "Player A's turn";
		if(currentPlayer == Player.B)
			return "Player B's turn";
		return "";
	}
	
	/**
	 * Returns whether or not the game can undo
	 * @return true if the game can undo, false if it can't
	 */
	public boolean canUndo(){
		return canUndo;
	}
}
