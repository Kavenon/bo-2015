package pl.agh.edu.bo.projekt;


import static org.junit.Assert.*;

import org.junit.Test;

public class IndividualCreateTest {

	
	//Sprawdza czy utworzona sciezka miesci sie w okreslonym przedziale
	@Test
	public void test() {
		OurGraph graph = new OurGraph(30);
		graph.generateRandomGraph();
		Individual ind = new Individual();
		ind.create(graph);
	
		
		assertEquals(true, ind.path.size()>=Constants.MIN_PATH_LENGTH);
		assertEquals(true, ind.path.size()<=Constants.MAX_PATH_LENGTH);
		
		
	}
	
	//Sprawdza czy pierwszy i ostatni wierzcholek sciezki jest taki sam
	@Test
	public void test2(){
		OurGraph graph = new OurGraph(30);
		graph.generateRandomGraph();
		Individual ind = new Individual();
		ind.create(graph);
		assertEquals(true, ind.path.get(0) == ind.path.get(ind.path.size()-1));
	}

}
