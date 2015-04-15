package pl.agh.edu.bo.projekt;

public class Constants {
	public static int ENV = 0; // 0 - debug, 1 - production
	
	public static int MAX_PATH_LENGTH = 0; // 0 - for verticles count
	public static int MIN_PATH_LENGTH = 2;
	
	public static int MAX_GRAPH_VERTICLES= 20; 
	public static int MIN_GRAPH_VERTICLES = 5;
	
	public static int CANVAS_WIDTH= 1200; 
	public static int CANVAS_HEIGHT = 600;
	
	
	public static int MAX_INIT_PATH= 10; 
	public static int MIN_INIT_PATH = 3;
	
	public static int MAX_ITERATIONS = 5;
	
	public static int TOURNAMENT_POPULATION_SIZE = 2; // have to be > MIN_INIT_PATH
	
	public static double CROSSOVER_RATE = 0.5;
	
	
}
