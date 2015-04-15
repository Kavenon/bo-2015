package pl.agh.edu.bo.projekt;

import java.util.Random;

public class Vertex {
	
	private int id;	

	private int x;
	private int y;
	private boolean visited;	
	
	public Vertex(int id){
		Random random = new Random();
		this.id = id;
		this.x = random.nextInt(Constants.CANVAS_WIDTH);
		this.y = random.nextInt(Constants.CANVAS_HEIGHT);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public static double distance(Vertex v1, Vertex v2){
		return Math.sqrt(Math.pow((v2.getX() - v1.getX()), 2) + Math.pow((v2.getY() - v1.getY()), 2));
	}
	
	
}
