package pl.agh.edu.bo.projekt;

import java.util.ArrayList;

public class Population {

	ArrayList<Individual> individuals;
	
	Population(int populationSize){
		individuals = new ArrayList<Individual>();
		
		for(int i = 0; i < populationSize; i++){
			Individual newIndyvidual = new Individual();
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
