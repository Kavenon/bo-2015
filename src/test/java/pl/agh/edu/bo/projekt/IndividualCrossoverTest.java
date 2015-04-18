package pl.agh.edu.bo.projekt;


import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class IndividualCrossoverTest {

	//Sprawdza czy poprawnie skrzyzowalo
	@Test
	public void test() {
		ArrayList <Vertex> l1 = new ArrayList<Vertex>();
		ArrayList <Vertex> l2 = new ArrayList<Vertex>();
		
		l1.add(new Vertex(33));
		l1.add(new Vertex(9));
		l1.add(new Vertex(13));
		l1.add(new Vertex(24));
		
		
		l1.add(new Vertex(14));
		l1.add(new Vertex(11));
		l1.add(new Vertex(18));
		l1.add(new Vertex(21));
		
		
		ArrayList<Vertex> result = Individual.crossover(l1, l2).path;
		
		
		ArrayList<Vertex> verList = new ArrayList<Vertex>();
		verList.add(new Vertex(33));
		verList.add(new Vertex(9));
		verList.add(new Vertex(13));
		verList.add(new Vertex(24));
		verList.add(new Vertex(14));
		verList.add(new Vertex(11));
		verList.add(new Vertex(18));
		verList.add(new Vertex(33));

		
		for(int i=0; i<verList.size(); i++){
			assertEquals(true, verList.get(i).getId()==result.get(i).getId());
		
		}
		
	}

	}

