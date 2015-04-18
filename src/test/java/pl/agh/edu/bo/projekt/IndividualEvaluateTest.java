package pl.agh.edu.bo.projekt;

import static org.junit.Assert.*;

import org.junit.Test;

public class IndividualEvaluateTest {

	//sprawdza czy policzylo dlugosc sciezki
	@Test
	public void test() {
		OurGraph graph = new OurGraph(30);
		graph.generateRandomGraph();
		Individual ind = new Individual();
		ind.create(graph);
		
		ind.evaluate();
		assertEquals(true, ind.length>0);
	}

}
