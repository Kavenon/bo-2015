package pl.agh.edu.bo.projekt;

import java.util.ArrayList;

public class Population {

	ArrayList<Individual> individuals;
	
	Population(int populationSize, OurGraph graph){
		individuals = new ArrayList<Individual>();

		for(int i = 0; i < populationSize; i++){
			Individual newIndyvidual = new Individual();
			newIndyvidual.create(graph);
			individuals.add(newIndyvidual);
		}
	}
	
	Individual getBestIndividual(){
		Individual result = null;
		
		int bestRating = Integer.MIN_VALUE;
		for(Individual i : individuals){
			if(i.getRating() >= bestRating){
				result = i;
			}
		}
		
		return result;
	}
	
}
