package pl.agh.edu.bo.projekt;

/**
 * Class that represents single path in graph.
 */
public class Individual {

	/**
	 * path length
	 */
	int rating;

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}
	
	public void create(){
		//TODO: take vertex, edges and create random path that starts and ends at the same vertex (avoiding visited vertexes)
	}
}
