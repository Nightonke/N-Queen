package N_Queen;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

/**
 * Created by 伟平 on 2015/11/6.
 * Notice we use a "bleeding" genetic algorithm by 2 methods:
 *
 * First, I will choose a part of individuals from each population
 * which I call them elitists. These elitists can have a more bigger
 * probability to reproduce. By this method, I can choose "the one"
 * more faster than the original genetic algorithm
 *
 * Second, in each reproduction, we do in this way
 * If some queens are in the same position in the boards of their father
 * and mother, I keep this. For we know, their parents are elitists probability,
 * so the queens in the same position are really good "gene".
 * After the above, we create the other random but not conflicting queens
 */

public class Genetic {

    int nodesGenerated;
    private Queen[] startState;
    private Node start;

    // the probability of mutating
    static private int MUTATE_RANDOM = 1000;
    // the reproducing time
    static private int REPRODUCE_TIME = 10000;
    // we choose 20 elitists from each population
    static private int ELITIST_NUMBER = 20;
    // and increase their probability of reproducing
    static private int ELITIST_WEIGHT = 10;

    public Genetic() {
        nodesGenerated = 0;
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

    public Node GeneticReproduce(int populationSize) {

        ArrayList<Node> population = getAncestors(populationSize);

        nodesGenerated = populationSize;

        for (int reproduceTime = 0; reproduceTime < REPRODUCE_TIME; reproduceTime++) {

            ArrayList<Node> newPopulation  = new ArrayList<>();
            int[] weight = new int[populationSize];
            int sum = 0;
            Random random = new Random();
            // prepare for random select father or mother
            Collections.sort(population, new Comparator<Node>() {
                @Override
                public int compare(Node o1, Node o2) {
                    if (o1.getHeuristic() < o2.getHeuristic()) {
                        return -1;
                    } else if (o1.getHeuristic() > o2.getHeuristic()) {
                        return 1;
                    } else {
                        return 0;
                    }
                }
            });
            for (int i = 0; i < populationSize; i++) {
                weight[i] =  Node.getSize() * Node.getSize() - population.get(i).computeHeuristic();
                sum += weight[i];
            }
            for (int i = 0; i < ELITIST_NUMBER; i++) {
                weight[i] *= ELITIST_WEIGHT;
                sum += weight[i] * (ELITIST_WEIGHT - 1);
            }

            for (int i = 0; i < populationSize; i++) {

                int fp = random.nextInt(populationSize);
                int mp = random.nextInt(populationSize);

                Node father = population.get(fp);
                Node mother = population.get(mp);


                int randInt = random.nextInt(sum);
                int subSum = 0;
                for (int j = 0; j < populationSize; j++) {
                    subSum += weight[j];
                    if (randInt <= subSum) {
                        father = population.get(j);
                        fp = j;
                        break;
                    }
                }
                do {
                    randInt = random.nextInt(sum);
                    subSum = 0;
                    for (int j = 0; j < populationSize; j++) {
                        subSum += weight[j];
                        if (randInt <= subSum) {
                            mother = population.get(j);
                            mp = j;
                            break;
                        }
                    }
                } while (fp == mp);

                Node son = reproduce(father, mother);

                nodesGenerated++;

                if (son.computeHeuristic() == 0) {
                    return son;
                }

                if (random.nextInt(MUTATE_RANDOM) == 0) {
                    mutate(son);
                }

                newPopulation.add(son);
            }

            population = newPopulation;

        }

        return population.get(0);
    }

    private ArrayList<Node> getAncestors(int ancestorsSize) {
        Random random = new Random();
        ArrayList<Node> ancestors = new ArrayList<>();
        for (int j = 0; j < ancestorsSize; j++) {
            Queen[] ancestor = new Queen[Node.getSize()];
            for (int i = 0; i < Node.getSize(); i++) {
                ancestor[i] = new Queen(random.nextInt(Node.getSize()), i);
            }
            ancestors.add(new Node(ancestor));
        }
        return ancestors;
    }

    private Node reproduce(Node father, Node mother) {

        Queen[] queen = new Queen[father.getState().length];
        boolean[] alreadyHas = new boolean[queen.length];
        boolean[] alreadyInCol = new boolean[queen.length];
        boolean[] alreadyInRow = new boolean[queen.length];
        for (int i = 0; i < queen.length; i++) {
            alreadyHas[i] = false;
            alreadyInCol[i] = false;
            alreadyInRow[i] = false;
        }
        for (int i = 0; i < queen.length; i++) {
            if (father.getState()[i].getRow() == mother.getState()[i].getRow() &&
                    father.getState()[i].getCol() == mother.getState()[i].getCol()) {
                queen[i] = father.getState()[i];
                alreadyHas[i] = true;
                alreadyInCol[queen[i].getCol()] = true;
                alreadyInRow[queen[i].getRow()] = true;
            }
        }
        for (int i = 0; i < queen.length; i++) {
            if (!alreadyHas[i]) {
                Random random = new Random();
                int row = 0;
                int col = 0;
                do {
                    row = random.nextInt(queen.length);
                } while (alreadyInRow[row]);
                do {
                    col = random.nextInt(queen.length);
                } while (alreadyInCol[col]);
                alreadyInRow[row] = true;
                alreadyInCol[col] = true;
                queen[i] = new Queen(row, col);
            }
        }
        Node son = new Node(queen);
        return son;

        /* the original reproduction method in genetic algorithm

        Random random = new Random();
        int c = random.nextInt(father.getState().length);
        Queen[] queen1 = new Queen[father.getState().length];
        Queen[] queen2 = new Queen[father.getState().length];
        for (int i = 0; i <= c; i++) {
            queen1[i] = father.getState()[i];
            queen2[i] = mother.getState()[i];
        }
        for (int i = c + 1; i < mother.getState().length; i++) {
            queen1[i] = mother.getState()[i];
            queen2[i] = mother.getState()[i];
        }
        Node son1 = new Node(queen1);
        Node son2 = new Node(queen2);
        if (son1.getHeuristic() < son2.getHeuristic()) {
            return son1;
        } else {
            return son2;
        }

        */
    }

    private void mutate(Node son) {
        Random random = new Random();
        int p = random.nextInt(Node.getSize());
        int r = random.nextInt(Node.getSize());
        son.getState()[p].moveDown(r);
    }

    public int getNodesGenerated(){
        return nodesGenerated;
    }

}
