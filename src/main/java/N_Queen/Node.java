package N_Queen;

import java.util.ArrayList;
import java.util.Random;

public class Node implements Comparable<Node>{

	// the default size of Queen puzzle is 8
	static private int SIZE = 10;
	// the state of node
	private Queen[] state;
	private ArrayList<Node> neighbours;
	// heuristic score
	// remember that the heuristic here means the number of conflict pairs of queens
	// so when heuristic is 0, we get the answer
	private int heuristic;

	public Node() {
		state = new Queen[SIZE];
		neighbours = new ArrayList<>();
		heuristic = 0;
	}

	public Node(Node n) {
		state = new Queen[SIZE];
		neighbours = new ArrayList<>();
		for (int i = 0; i < SIZE; i++) {
			state[i] = new Queen(n.state[i].getRow(), n.state[i].getCol());
		}
		heuristic = 0;
	}

	public Node(Queen[] queens) {
		state = new Queen[SIZE];
		for (int i = 0; i < SIZE; i++) {
			state[i] = queens[i];
		}
		neighbours = new ArrayList<>();
		heuristic = 0;
	}

	static public int getSize() {
		return SIZE;
	}

	static public void setSize(int Size) {
		SIZE = Size;
	}

	public ArrayList<Node> generateNeighbours(Node startState){

		int count=0;
		
		for (int i = 0; i < SIZE; i++) {
			for (int j = 1; j < SIZE; j++) {
				neighbours.add(count, new Node(startState));
				neighbours.get(count).state[i].moveDown(j);
				neighbours.get(count).computeHeuristic();
				count++;
			}
		}
		
		return neighbours;
	}

	public Node getRandomNeighbour(Node startState){
		Random gen = new Random();
		
		int col = gen.nextInt(SIZE);
		int d = gen.nextInt(SIZE - 1) + 1;
		
		Node neighbour = new Node(startState);
		neighbour.state[col].moveDown(d);
		neighbour.computeHeuristic();
		
		return neighbour;
	}

	public int computeHeuristic(){
	
		for (int i = 0; i < SIZE - 1; i++){
			for (int j = i + 1; j < SIZE; j++){
				if (state[i].canAttack(state[j])){
					heuristic++;
				}
			}
		}

		return heuristic;
	}

	public int getHeuristic(){
		return heuristic;
	}

	public int compareTo(Node n){
		if (this.heuristic < n.getHeuristic()) {
			return -1;
		} else if (this.heuristic > n.getHeuristic()) {
			return 1;
		} else {
			return 0;
		}
	}

	public void setState(Queen[] s) {
		for(int i = 0; i < SIZE; i++){
			state[i]= new Queen(s[i].getRow(), s[i].getCol());
		}
	}

	public Queen[] getState(){
		return state;
	}

	public String toString(){

		String result = "";
		String[][] board = new String[SIZE][SIZE];

		for (int i = 0; i < SIZE; i++)
			for(int j = 0; j < SIZE; j++)
				board[i][j] = ".";

		for (int i = 0; i < SIZE; i++){
			board[state[i].getRow()][state[i].getCol()]="Q";
		}

		for (int i = 0; i < SIZE; i++){
			for (int j = 0; j < SIZE; j++){
				result += board[i][j];
			}
			result += "\n";
		}
		
		return result;
	}
}
