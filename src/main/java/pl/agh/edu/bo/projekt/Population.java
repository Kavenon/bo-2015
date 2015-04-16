package pl.agh.edu.bo.projekt;

import java.util.ArrayList;

public class Population {

	ArrayList<Individual> individuals;

	Population(int populationSize, OurGraph graph) {
		individuals = new ArrayList<Individual>();

		for (int i = 0; i < populationSize; i++) {
			Individual newIndyvidual = new Individual();
			newIndyvidual.create(graph);
			newIndyvidual.debug();
			individuals.add(newIndyvidual);
		}
	}

	public Population() {
		individuals = new ArrayList<Individual>();
	}

	Individual getBestIndividual() {
		Individual result = null;

		double bestRating = Double.MIN_VALUE;
		for (Individual i : individuals) {
			if (i.length >= bestRating) {
				bestRating = i.length;
				result = i;
			}
		}

		return result;
	}

	Individual tournamentSelection() throws Exception {

		if (Constants.TOURNAMENT_POPULATION_SIZE > Constants.MIN_INIT_PATH)
			throw new Exception();

		// populacja turniejowa
		Population tournament = new Population();

		// dodaj do populacji turniejowej losowe sciezki
		for (int i = 0; i < Constants.TOURNAMENT_POPULATION_SIZE; i++) {
			int randomId = (int) (Math.random() * individuals.size());

			if (!tournament.individuals.contains(individuals.get(randomId)))
				tournament.individuals.add(individuals.get(randomId));
		}

		// wybierz najlepsza i zwroc (moze zwrocic takie same za kazdym
		// wywolaniem)
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
