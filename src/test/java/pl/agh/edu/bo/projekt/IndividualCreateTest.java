package pl.agh.edu.bo.projekt;


import static org.junit.Assert.*;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class IndividualCreateTest {

	
	//Sprawdza czy utworzona sciezka miesci sie w okreslonym przedziale
/*	@Test
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
	}*/

    @Test
    public void individualCreateTest() throws InterruptedException {
        Random random = new Random();
        OurGraph graph = App.createRandomGraph(random);

        /*ArrayList<Vertex> lst = new ArrayList<Vertex>(graph.getGraph().getVertices());
        System.out.println(lst);
        Collections.shuffle(lst);

        // adding defined amount of numbers to target list
        ArrayList<Vertex> path = new ArrayList<Vertex>();
        for (int j = 0; j < 5; j++) {
            path.add(lst.get(j));
        }
        if (path.size() >= 2) {
            if (!path.get(path.size() - 1).equals(path.get(0))) {
                path.add(path.get(0));
            }
        }
        System.out.println(path);*/

        Individual individual = new Individual();
        individual.create(graph);
        System.out.println("ind"+ individual.toString());

        /*individual.mutate(graph);
        System.out.println("ind"+ individual.toString());
        graph.printGraph();*/

        Thread.sleep(99995000);
    }

}
