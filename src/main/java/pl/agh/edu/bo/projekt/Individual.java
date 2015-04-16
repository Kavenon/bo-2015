/*
 * 
 */
package pl.agh.edu.bo.projekt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import edu.uci.ics.jung.graph.Graph;

/**
 * Klasa ktora reprezentuje pojedyncza sciezke w grafie
 */
public class Individual {

	double length;

	ArrayList<Vertex> path;

	public Individual() {
		path = new ArrayList<Vertex>();
	}

	public void create(OurGraph ourGraph) {
		Random random = new Random();
		Graph<Vertex, String> graph = ourGraph.getGraph();

		path = new ArrayList<Vertex>();

		// lista wierzcholkow w grafie
		ArrayList<Vertex> lst = new ArrayList<Vertex>(graph.getVertices());

		int maxPathLength = (Constants.MAX_PATH_LENGTH == 0) ? graph
				.getVertexCount() : Constants.MAX_PATH_LENGTH;
		int minPathLength = Constants.MIN_PATH_LENGTH;

		// sciezka moze byc loswej dlugosci (min,max)
		int randomPathLength = random
				.nextInt((maxPathLength - minPathLength) + 1) + minPathLength;

		if (randomPathLength > 0) {
			// dodajemy losowe wierzcholki
			for (int i = 0; i < randomPathLength; i++) {
				int index = random.nextInt(lst.size());
				Vertex item = lst.get(index);
				path.add(item);
				lst.remove(index);
			}

			// upewniamy sie ze sciezka wraca do tego samego wierzcholka z
			// ktorego wyszla
			if (path.size() >= 2) {
				if (!path.get(path.size() - 1).equals(path.get(0))) {
					path.add(path.get(0));
				}
			}

		}

	}

	// obliczanie dlugosci sciezki
	public void evaluate() {
		length = 0;
		for (int i = 0; i < path.size() - 1; i++) {
			length += Vertex.distance(path.get(i), path.get(i + 1));
		}
	}

	// krzyzowanie
	/**
	 * todo: nie wiem czy sciezki nie powinny byc krotsze na wyjsciu, zbyt
	 * szybko osiagamy sciezke przez wszystkie wierzcholki
	 */
	public static Individual crossover(ArrayList<Vertex> lst1,
			ArrayList<Vertex> lst2) {

		Individual newIndividual = new Individual();

		// ArrayList<Vertex> lst1 = new ArrayList<Vertex>(indiv1.path);
		// ArrayList<Vertex> lst2 = new ArrayList<Vertex>(indiv2.path);

		boolean firstDepleted = false;
		boolean secondDepleted = false;

		Random rnd = new Random();

		int i = 0;
		int j = 0;

		for (Vertex v : lst1) {
			lst2.removeAll(Collections.singleton(v));
		}
		lst1.remove(lst1.size() - 1);

		// when indiv1 is same as indiv2
		if (lst2.size() > 0)
			lst2.remove(lst2.size() - 1);
		else
			secondDepleted = true;

		while (true) {
			try {
				while (i < rnd.nextInt(lst1.size() + i)) {
					if (!newIndividual.path.contains(lst1.get(i)))
						newIndividual.path.add(lst1.get(i));
					i++;
				}
			} catch (IndexOutOfBoundsException e) {
				firstDepleted = true;
			}
			if (lst2.size() == 1) {
				if (!newIndividual.path.contains(lst2.get(0)))
					newIndividual.path.add(lst2.get(0));
				secondDepleted = true;
			} else {
				try {
					while (j < rnd.nextInt(lst2.size() + j)) {
						if (!newIndividual.path.contains(lst2.get(j)))
							newIndividual.path.add(lst2.get(j));
						j++;
					}
				} catch (IndexOutOfBoundsException e) {
					secondDepleted = true;
				} catch (IllegalArgumentException e) {
					secondDepleted = true;
				} // when indiv1 is same as indiv2
			}
			if ((firstDepleted && secondDepleted)) {
				break;
			}

		}

		// upewniamy sie ze sciezka wraca do tego samego wierzcholka z ktorego
		// wyszla
		if (newIndividual.path.size() >= 2) {
			if (!newIndividual.path.get(newIndividual.path.size() - 1).equals(
					newIndividual.path.get(0))) {
				newIndividual.path.add(newIndividual.path.get(0));
			}
		}
		return newIndividual;
	}

	// mutacja
	/**
	 * todo: podmieniac wiecej niz jeden wierzcholek w sytuacji gdy sciezka
	 * przechodzi przez wszystkie wierzcholki zamiast podmieniac mozna je
	 * permutowac
	 */
	public void mutate(OurGraph ourGraph) {

		// zmieniamy losowy wierzcholek na inny
		int randomId = (int) (Math.random() * path.size());
		Vertex randomFromPath = path.get(randomId);

		ArrayList<Vertex> lst = new ArrayList<Vertex>(ourGraph.getGraph()
				.getVertices());

		while (lst.size() > 0) {
			// losowy wierzcholek z grafu na ktory probujemy zmienic
			Vertex randomFromGraph = lst.get((int) Math.random() * lst.size());

			// sprawdzamy, czy taki wierzcholek juz istnieje w sciezce i czy nie
			// jest tym podmienianym
			if (!path.contains(randomFromGraph)
					&& randomFromGraph.getId() != randomFromPath.getId()) {
				path.set(randomId, randomFromGraph);
				break;
			} else {
				lst.remove(randomFromGraph);
			}
		}

		// upewniamy sie ze sciezka wraca do tego samego wierzcholka z ktorego
		// wyszla
		if (path.size() >= 2) {
			if (!path.get(path.size() - 1).equals(path.get(0))) {
				path.add(path.get(0));
			}
		}

	}

	public String toString() {
		StringBuilder sb = new StringBuilder();

		for (Vertex i : path) {
			sb.append(i.getId() + " ");
		}

		if (this.length > 0)
			sb.append("length:" + this.length);

		return sb.toString();
	}

	public void debug() {
		if (Constants.ENV == 0)
			System.out.println(this);
	}
}
