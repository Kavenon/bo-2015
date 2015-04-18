package pl.agh.edu.bo.projekt;

import static org.junit.Assert.*;

import org.junit.Test;

public class VertexDistanceTest {

	@Test
	public void test() {
		Vertex ver = new Vertex(1);
		Vertex ver2 = new Vertex(2);
		
		ver.setX(0);
		ver.setY(0);
		
		ver2.setX(5);
		ver2.setY(0);
		assertEquals(5.0, Vertex.distance(ver, ver2), 0.00001);
		
		ver2.setX(6);
		ver2.setY(3);
		
		assertEquals(6.7082, Vertex.distance(ver, ver2), 0.0001);
		
		
		
	}

}
