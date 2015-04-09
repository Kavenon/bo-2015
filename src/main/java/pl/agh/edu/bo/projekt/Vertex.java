package pl.agh.edu.bo.projekt;

public class Vertex {
	
	private int id;	

	private boolean visited;	
	
	public Vertex(int id){
		this.id = id;
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

	
}
