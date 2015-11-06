package N_Queen;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by 伟平 on 2015/11/6.
 */
public class FirstChoiceHillClimbing {

    private Queen[] startState;
    // start state
    private Node start;
    private int nodesGenerated;

    public FirstChoiceHillClimbing() {
        start = new Node();
        startState = new Queen[Node.getSize()];
        startState();
        nodesGenerated=0;
    }

    public FirstChoiceHillClimbing(Queen[] s) {
        start = new Node();
        startState = new Queen[Node.getSize()];
        for (int i = 0; i < s.length; i++){
            startState[i] = new Queen(s[i].getRow(), s[i].getCol());
        }
        start.setState(startState);
        start.computeHeuristic();

        nodesGenerated = 0;
    }

    public void startState() {
        Random random = new Random();
        for (int i = 0; i < Node.getSize(); i++){
            startState[i] = new Queen(random.nextInt(Node.getSize()), i);
        }
        start.setState(startState);
        start.computeHeuristic();
    }

    public Node hillClimbing() {

        Node currentNode = start;

        while (true) {
            ArrayList<Node> successors = currentNode.generateNeighbours(currentNode);
            nodesGenerated += successors.size();

            boolean existBetter = false;

            for (int i = 0; i < successors.size(); i++) {
                if (successors.get(i).compareTo(currentNode) < 0) {
                    currentNode = successors.get(i);
                    existBetter = true;
                    break;
                }
            }

            if(!existBetter) {
                return currentNode;
            }

        }
    }

    public Node getStartNode(){
        return start;
    }

    public int getNodesGenerated(){
        return nodesGenerated;
    }

}
