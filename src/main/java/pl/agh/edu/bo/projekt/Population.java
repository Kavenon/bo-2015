package pl.agh.edu.bo.projekt;

import java.util.ArrayList;

public class Population {

	ArrayList<Individual> individuals;

    public Population(){
        individuals = new ArrayList<Individual>();
    }
	public Population(int populationSize, int initPathLength, OurGraph graph) {
        individuals = new ArrayList<Individual>();

        for (int i = 0; i < populationSize; i++) {
            Individual newIndyvidual = new Individual();
            newIndyvidual.create(graph,initPathLength);
          //  newIndyvidual.debug();
            individuals.add(newIndyvidual);
        }
	}

	Individual getBestIndividual() {
		Individual result = null;

		double bestRating = Double.MIN_VALUE;
		for (Individual i : individuals) {
			if (i.path.size() >= bestRating) {
				bestRating = i.path.size();
				result = i;
			}
		}

		return result;
	}

	Individual tournamentSelection()  {

		Population tournament = new Population();

		// dodaj do populacji turniejowej losowe sciezki
        int populationSize = (int) (individuals.size() * Constants.TOURNAMENT_POPULATION_RATE);

		for (int i = 0; i < populationSize; i++) {
			int randomId = (int) (Math.random() * individuals.size());

			if (!tournament.individuals.contains(individuals.get(randomId)))
				tournament.individuals.add(individuals.get(randomId));
		}


		Individual fittest = tournament.getBestIndividual();
		return fittest;

	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Individual i : individuals) {
			sb.append(i);
			sb.append("\n");
		}
		return sb.toString();

	}

}
