package N_Queen;

import java.util.Random;

public class SimulatedAnnealing {
	int nodesGenerated;
	private Queen[] startState;
	private Node start;

	public SimulatedAnnealing(Queen[] s) {
		nodesGenerated = 0;
		start = new Node();
		startState = new Queen[Node.getSize()];
		
		for (int i = 0; i < Node.getSize(); i++) {
			startState[i] = new Queen(s[i].getRow(), s[i].getCol());
		}
		start.setState(startState);
		start.computeHeuristic();
	}

	public void startState(){
		start = new Node();
		startState = new Queen[Node.getSize()];
		Random gen = new Random();
		
		for (int i = 0; i < Node.getSize(); i++){
			startState[i] = new Queen(gen.nextInt(Node.getSize()), i);
		}
		start.setState(startState);
		start.computeHeuristic();
	}

	public Node simulatedAnneal(double initialTemp, double step) {
		Node currentNode = start;
		double temperature = initialTemp;
		double val = step;
		double probability;
		int delta;
		double determine;
		
		Node nextNode = new Node();
		
		while (currentNode.getHeuristic() != 0 && temperature > 0){
			//select a random neighbour from currentNode
			nextNode = currentNode.getRandomNeighbour(currentNode);
			nodesGenerated++;
			
			if (nextNode.getHeuristic() == 0)
				return nextNode;
			
			delta = currentNode.getHeuristic() - nextNode.getHeuristic();
			
			if (delta > 0) { //currentNode has a higher heuristic
				currentNode = nextNode;
			} else {
				probability = Math.exp(delta/temperature);
				//Do we want to choose nextNode or stick with currentNode?
				determine = Math.random();
				
				if (determine <= probability) { //choose nextNode
					currentNode = nextNode;
				}
			}
			temperature = temperature - val;
		}
		
		return currentNode;
	}

	public int getNodesGenerated(){
		return nodesGenerated;
	}

	public Node getStartNode(){
		return start;
	}
}
