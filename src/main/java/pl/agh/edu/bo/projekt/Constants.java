package pl.agh.edu.bo.projekt;

public class Constants {
    public static final double EDGE_PROBABILITY = 0.7;
    public static final int ENV = 0; // 0 - debug, 1 - production

	public static final int MAX_PATH_LENGTH = 20; // 0 - for verticles count
	public static final int MIN_PATH_LENGTH = 10;

	public static final int MAX_GRAPH_VERTICLES = 50;
	public static final int MIN_GRAPH_VERTICLES = 30; // wiecej niz min_path_length

	public static final int CANVAS_WIDTH = 1200;
	public static final int CANVAS_HEIGHT = 600;

	public static final int MAX_INIT_PATH = 5;
	public static final int MIN_INIT_PATH = 3;

	public static final int MAX_ITERATIONS = 10;

	public static final int TOURNAMENT_POPULATION_SIZE = 2; // have to be < MIN_INIT_PATH

	public static final double CROSSOVER_RATE = 0.5;

	public static final double MUTATION_RATE = 0.5;

}
