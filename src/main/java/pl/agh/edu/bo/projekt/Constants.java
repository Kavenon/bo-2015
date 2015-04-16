package pl.agh.edu.bo.projekt;

public class Constants {
	public static int ENV = 0; // 0 - debug, 1 - production

	public static int MAX_PATH_LENGTH = 20; // 0 - for verticles count
	public static int MIN_PATH_LENGTH = 8;

	public static int MAX_GRAPH_VERTICLES = 50;
	public static int MIN_GRAPH_VERTICLES = 20;

	public static int CANVAS_WIDTH = 1200;
	public static int CANVAS_HEIGHT = 600;

	public static int MAX_INIT_PATH = 5;
	public static int MIN_INIT_PATH = 3;

	public static int MAX_ITERATIONS = 10;

	public static int TOURNAMENT_POPULATION_SIZE = 2; // have to be < MIN_INIT_PATH

	public static double CROSSOVER_RATE = 0.3;

	public static double MUTATION_RATE = 1;

}
