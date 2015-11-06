package N_Queen;

import java.text.NumberFormat;
import java.util.Random;

public class N_Queens {

    private static final int TEST_TIMES = 500;

	public N_Queens() {

	}
	
	public static void main(String[] args){

        for (int NODE_SIZE = 8; NODE_SIZE <= 20; NODE_SIZE++) {

            Node.setSize(NODE_SIZE);

            N_Queens board = new N_Queens();

            System.out.println("Test for " + NODE_SIZE + " Queens");

            double StochasticHillClimbing_MIN_Nodes = Integer.MAX_VALUE;
            double StochasticHillClimbing_MAX_Nodes = 0;
            double StochasticHillClimbing_SUM_Nodes = 0;
            double StochasticHillClimbing_AVE_Nodes = 0;

            double SteepestAscentHillClimbing_MIN_Nodes = Integer.MAX_VALUE;
            double SteepestAscentHillClimbing_MAX_Nodes = 0;
            double SteepestAscentHillClimbing_SUM_Nodes = 0;
            double SteepestAscentHillClimbing_AVE_Nodes = 0;

            double FirstChoiceHillClimbing_MIN_Nodes = Integer.MAX_VALUE;
            double FirstChoiceHillClimbing_MAX_Nodes = 0;
            double FirstChoiceHillClimbing_SUM_Nodes = 0;
            double FirstChoiceHillClimbing_AVE_Nodes = 0;

            double SimulatedAnnealing_MIN_Nodes = Integer.MAX_VALUE;
            double SimulatedAnnealing_MAX_Nodes = 0;
            double SimulatedAnnealing_SUM_Nodes = 0;
            double SimulatedAnnealing_AVE_Nodes = 0;

            double Genetic_MIN_Nodes = Integer.MAX_VALUE;
            double Genetic_MAX_Nodes = 0;
            double Genetic_SUM_Nodes = 0;
            double Genetic_AVE_Nodes = 0;

            double RandomRestartHillClimbing_MIN_Nodes = Integer.MAX_VALUE;
            double RandomRestartHillClimbing_MAX_Nodes = 0;
            double RandomRestartHillClimbing_SUM_Nodes = 0;
            double RandomRestartHillClimbing_AVE_Nodes = 0;

            double StochasticHillClimbing_SUM_Successes = 0;
            double StochasticHillClimbing_AVE_Successes = 0;

            double SteepestAscentHillClimbing_SUM_Successes = 0;
            double SteepestAscentHillClimbing_AVE_Successes = 0;

            double FirstChoiceHillClimbing_SUM_Successes = 0;
            double FirstChoiceHillClimbing_AVE_Successes = 0;

            double SimulatedAnnealing_SUM_Successes = 0;
            double SimulatedAnnealing_AVE_Successes = 0;

            double Genetic_SUM_Successes = 0;
            double Genetic_AVE_Successes = 0;

            double RandomRestartHillClimbing_SUM_Successes = 0;
            double RandomRestartHillClimbing_AVE_Successes = 0;

            System.out.print("Running : ");

            for (int currentTest = 1; currentTest <= TEST_TIMES; currentTest++) {

                System.out.print("|");

                Queen[] startBoard = board.generateBoard();

                StochasticHillClimbing stochasticHillClimber
                        = new StochasticHillClimbing(startBoard);
                RandomRestartHillClimbing randomRestartHillClimber
                        = new RandomRestartHillClimbing(startBoard);
                SimulatedAnnealing simulatedAnnealer
                        = new SimulatedAnnealing(startBoard);
                SteepestAscentHillClimbing steepestAscentHillClimber
                        = new SteepestAscentHillClimbing(startBoard);
                FirstChoiceHillClimbing firstChoiceHillClimber
                        = new FirstChoiceHillClimbing(startBoard);
                Genetic geneticer = new Genetic();

                Node stochasticHillClimbingNode = stochasticHillClimber.hillClimbing();
                Node randomRestartHillClimbingNode = randomRestartHillClimber.randomRestart();
                Node simulatedAnnealNode = simulatedAnnealer.simulatedAnneal(28, 0.0001);
                Node steepestAscentHillClimbingNode = steepestAscentHillClimber.hillClimbing();
                Node firstChoiceHillClimbingNode = firstChoiceHillClimber.hillClimbing();
                Node geneticNode = geneticer.GeneticReproduce(50);

                if(stochasticHillClimbingNode.getHeuristic() == 0){
                    StochasticHillClimbing_SUM_Successes++;
                }
                if(randomRestartHillClimbingNode.getHeuristic() == 0){
                    RandomRestartHillClimbing_SUM_Successes++;
                }
                if(simulatedAnnealNode.getHeuristic() == 0){
                    SimulatedAnnealing_SUM_Successes++;
                }
                if (steepestAscentHillClimbingNode.getHeuristic() == 0) {
                    SteepestAscentHillClimbing_SUM_Successes++;
                }
                if (firstChoiceHillClimbingNode.getHeuristic() == 0) {
                    FirstChoiceHillClimbing_SUM_Successes++;
                }
                if (geneticNode.getHeuristic() == 0) {
                    Genetic_SUM_Successes++;
                }

                StochasticHillClimbing_SUM_Nodes
                        += stochasticHillClimber.getNodesGenerated();
                StochasticHillClimbing_MIN_Nodes =
                        Math.min(StochasticHillClimbing_MIN_Nodes,
                                stochasticHillClimber.getNodesGenerated());
                StochasticHillClimbing_MAX_Nodes =
                        Math.max(StochasticHillClimbing_MAX_Nodes,
                                stochasticHillClimber.getNodesGenerated());

                SteepestAscentHillClimbing_SUM_Nodes
                        += steepestAscentHillClimber.getNodesGenerated();
                SteepestAscentHillClimbing_MIN_Nodes =
                        Math.min(SteepestAscentHillClimbing_MIN_Nodes,
                                steepestAscentHillClimber.getNodesGenerated());
                SteepestAscentHillClimbing_MAX_Nodes =
                        Math.max(SteepestAscentHillClimbing_MAX_Nodes,
                                steepestAscentHillClimber.getNodesGenerated());

                FirstChoiceHillClimbing_SUM_Nodes
                        += firstChoiceHillClimber.getNodesGenerated();
                FirstChoiceHillClimbing_MIN_Nodes =
                        Math.min(FirstChoiceHillClimbing_MIN_Nodes,
                                firstChoiceHillClimber.getNodesGenerated());
                FirstChoiceHillClimbing_MAX_Nodes =
                        Math.max(FirstChoiceHillClimbing_MAX_Nodes,
                                firstChoiceHillClimber.getNodesGenerated());

                SimulatedAnnealing_SUM_Nodes
                        += simulatedAnnealer.getNodesGenerated();
                SimulatedAnnealing_MIN_Nodes =
                        Math.min(SimulatedAnnealing_MIN_Nodes,
                                simulatedAnnealer.getNodesGenerated());
                SimulatedAnnealing_MAX_Nodes =
                        Math.max(SimulatedAnnealing_MAX_Nodes,
                                simulatedAnnealer.getNodesGenerated());

                Genetic_SUM_Nodes
                        += geneticer.getNodesGenerated();
                Genetic_MIN_Nodes =
                        Math.min(Genetic_MIN_Nodes,
                                geneticer.getNodesGenerated());
                Genetic_MAX_Nodes =
                        Math.max(Genetic_MAX_Nodes,
                                geneticer.getNodesGenerated());

                RandomRestartHillClimbing_SUM_Nodes
                        += randomRestartHillClimber.getNodesGenerated();
                RandomRestartHillClimbing_MIN_Nodes =
                        Math.min(RandomRestartHillClimbing_MIN_Nodes,
                                randomRestartHillClimber.getNodesGenerated());
                RandomRestartHillClimbing_MAX_Nodes =
                        Math.max(RandomRestartHillClimbing_MAX_Nodes,
                                randomRestartHillClimber.getNodesGenerated());
            }

            StochasticHillClimbing_AVE_Nodes =
                    StochasticHillClimbing_SUM_Nodes / TEST_TIMES;
            SteepestAscentHillClimbing_AVE_Nodes =
                    SteepestAscentHillClimbing_SUM_Nodes / TEST_TIMES;
            FirstChoiceHillClimbing_AVE_Nodes =
                    FirstChoiceHillClimbing_SUM_Nodes / TEST_TIMES;
            SimulatedAnnealing_AVE_Nodes =
                    SimulatedAnnealing_SUM_Nodes / TEST_TIMES;
            Genetic_AVE_Nodes =
                    Genetic_SUM_Nodes / TEST_TIMES;
            RandomRestartHillClimbing_AVE_Nodes =
                    RandomRestartHillClimbing_SUM_Nodes / TEST_TIMES;

            StochasticHillClimbing_AVE_Successes =
                    StochasticHillClimbing_SUM_Successes / TEST_TIMES;
            SteepestAscentHillClimbing_AVE_Successes =
                    SteepestAscentHillClimbing_SUM_Successes / TEST_TIMES;
            FirstChoiceHillClimbing_AVE_Successes =
                    FirstChoiceHillClimbing_SUM_Successes / TEST_TIMES;
            SimulatedAnnealing_AVE_Successes =
                    SimulatedAnnealing_SUM_Successes / TEST_TIMES;
            Genetic_AVE_Successes =
                    Genetic_SUM_Successes / TEST_TIMES;
            RandomRestartHillClimbing_AVE_Successes =
                    RandomRestartHillClimbing_SUM_Successes / TEST_TIMES;

            NumberFormat fmt = NumberFormat.getPercentInstance();
            fmt.setMinimumFractionDigits(3);
            fmt.setMinimumIntegerDigits(10);

            System.out.println("Finish:");
            System.out.println("StochasticHillClimbing    :"
                    + " MIN_NODES = " + StochasticHillClimbing_MIN_Nodes
                    + " MAX_NODES = " + StochasticHillClimbing_MAX_Nodes
                    + " AVE_NODES = " + StochasticHillClimbing_AVE_Nodes
                    + " SUM_NODES = " + StochasticHillClimbing_SUM_Nodes
                    + " SUCCESSES = " + StochasticHillClimbing_SUM_Successes
                    + " SUCCESSES_RATE = " + StochasticHillClimbing_AVE_Successes);
            System.out.println("SteepestAscentHillClimbing:"
                    + " MIN_NODES = " + SteepestAscentHillClimbing_MIN_Nodes
                    + " MAX_NODES = " + SteepestAscentHillClimbing_MAX_Nodes
                    + " AVE_NODES = " + SteepestAscentHillClimbing_AVE_Nodes
                    + " SUM_NODES = " + SteepestAscentHillClimbing_SUM_Nodes
                    + " SUCCESSES = " + SteepestAscentHillClimbing_SUM_Successes
                    + " SUCCESSES_RATE = " + SteepestAscentHillClimbing_AVE_Successes);
            System.out.println("FirstChoiceHillClimbing   :"
                    + " MIN_NODES = " + FirstChoiceHillClimbing_MIN_Nodes
                    + " MAX_NODES = " + FirstChoiceHillClimbing_MAX_Nodes
                    + " AVE_NODES = " + FirstChoiceHillClimbing_AVE_Nodes
                    + " SUM_NODES = " + FirstChoiceHillClimbing_SUM_Nodes
                    + " SUCCESSES = " + FirstChoiceHillClimbing_SUM_Successes
                    + " SUCCESSES_RATE = " + FirstChoiceHillClimbing_AVE_Successes);
            System.out.println("SimulatedAnnealing        :"
                    + " MIN_NODES = " + SimulatedAnnealing_MIN_Nodes
                    + " MAX_NODES = " + SimulatedAnnealing_MAX_Nodes
                    + " AVE_NODES = " + SimulatedAnnealing_AVE_Nodes
                    + " SUM_NODES = " + SimulatedAnnealing_SUM_Nodes
                    + " SUCCESSES = " + SimulatedAnnealing_SUM_Successes
                    + " SUCCESSES_RATE = " + SimulatedAnnealing_AVE_Successes);
            System.out.println("Genetic                   :"
                    + " MIN_NODES = " + Genetic_MIN_Nodes
                    + " MAX_NODES = " + Genetic_MAX_Nodes
                    + " AVE_NODES = " + Genetic_AVE_Nodes
                    + " SUM_NODES = " + Genetic_SUM_Nodes
                    + " SUCCESSES = " + Genetic_SUM_Successes
                    + " SUCCESSES_RATE = " + Genetic_AVE_Successes);
            System.out.println("RandomRestartHillClimbing :"
                    + " MIN_NODES = " + RandomRestartHillClimbing_MIN_Nodes
                    + " MAX_NODES = " + RandomRestartHillClimbing_MAX_Nodes
                    + " AVE_NODES = " + RandomRestartHillClimbing_AVE_Nodes
                    + " SUM_NODES = " + RandomRestartHillClimbing_SUM_Nodes
                    + " SUCCESSES = " + RandomRestartHillClimbing_SUM_Successes
                    + " SUCCESSES_RATE = " + RandomRestartHillClimbing_AVE_Successes);

        }

	}

	public Queen[] generateBoard(){
		Queen[] start = new Queen[Node.getSize()];
		Random gen = new Random();

		for (int i = 0; i < Node.getSize(); i++) {
			start[i] = new Queen(gen.nextInt(Node.getSize()), i);
		}
		return start;
	}
}
