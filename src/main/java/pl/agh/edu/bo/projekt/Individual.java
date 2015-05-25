/*
 * 
 */
package pl.agh.edu.bo.projekt;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

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

        ArrayList<Vertex> verticlesFromGraph;
        boolean foundPath = false;


		int maxPathLength = (Constants.MAX_PATH_LENGTH == 0) ? graph
				.getVertexCount() : Constants.MAX_PATH_LENGTH;
		int minPathLength = Constants.MIN_PATH_LENGTH;

        int iterationsToFindOnePath = 0;
        int randomPathLength;

        while(!foundPath) {
            verticlesFromGraph = new ArrayList<Vertex>(graph.getVertices());
            path.clear();

            randomPathLength = random
                    .nextInt((maxPathLength - minPathLength) + 1) + minPathLength;

            if (randomPathLength > 1) {

                addRandomVerticesToPath(verticlesFromGraph, randomPathLength);
                addStartVertexToEnd();

                if (validatePath(path, graph)) {
                    foundPath = true;
                }
                iterationsToFindOnePath++;
            }
        }

        System.out.println("Iterations to find one path: " + iterationsToFindOnePath);

	}

    private void addStartVertexToEnd() {
        if (path.size() >= 2) {
            if (!path.get(path.size() - 1).equals(path.get(0))) {
                path.add(path.get(0));
            }
        }
    }

    private void addRandomVerticesToPath(ArrayList<Vertex> lst, int randomPathLength) {
        Collections.shuffle(lst);
        for (int j = 0; j < randomPathLength; j++) {
            try {
                path.add(lst.get(j));
            }
            catch(IndexOutOfBoundsException e) {
                break;
            }

        }
    }

    public boolean validatePath(List<Vertex> path, Graph<Vertex, String> dg ) {
        ArrayList<Vertex> visited = new ArrayList<Vertex>();

        for (int i = 0; i < path.size()-1; i++){
            if(visited.contains(path.get(i)))
                return false;
            visited.add(path.get(i));
        }


        for (int i = 0; i < path.size()-1; i++) {
            if(!dg.isSuccessor(path.get(i), path.get(i+1)))
                return false;
        }
        return true;
    }

    // obliczanie dlugosci sciezki
	public void evaluate() {
		length = path.size();
		/*for (int i = 0; i < path.size() - 1; i++) {
			length += Vertex.distance(path.get(i), path.get(i + 1));
		}*/
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


        boolean inserted = false;
        int iterations = 0;


        CopyOnWriteArrayList<Vertex> lstFromGraph = new CopyOnWriteArrayList<Vertex>(ourGraph.getGraph()
                .getVertices());

        for (Vertex vertex : lstFromGraph) {
            if (path.contains(vertex)) {
                lstFromGraph.remove(vertex);
            }
        }

        int maxPossibleIterations = lstFromGraph.size() ^ path.size();
        Random random = new Random();
        if(lstFromGraph.size() > 0) {
            while (!inserted && iterations < maxPossibleIterations) {
                int randomId2 = random
                        .nextInt((path.size() - 1) + 1) + 1;
                //int randomId2 = (int) (Math.random() * path.size());
                Collections.shuffle(lstFromGraph);

                path.add(randomId2, lstFromGraph.get(0));
                //addStartVertexToEnd();

                if (!validatePath(path, ourGraph.getGraph())) {
                    path.remove(randomId2);
                    iterations++;
                } else {
                    inserted = true;
                }

            }
        }


        System.out.println("Inserted: " + inserted + " iterations: " + iterations + " max possible" + maxPossibleIterations);

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


    
    public static List<Individual> crossoverNew(ArrayList<Vertex> l1, ArrayList<Vertex> l2) {
        List<Vertex> l1FirstHalf = l1.subList(0, l1.size() / 2);
        List<Vertex> l1SecondHalf = l1.subList(l1FirstHalf.size(), l1.size() );

        List<Vertex> l2FirstHalf = l2.subList(0, l2.size()/2);
        List<Vertex> l2SecondHalf = l2.subList(l2FirstHalf.size(), l2.size());

        List<Individual> result = new ArrayList<Individual>();

        Individual l1Cross = mergeResult(l1FirstHalf, l2FirstHalf);

        l1Cross.addStartVertexToEnd();
        result.add(l1Cross);

        Individual l2Cross = mergeResult(l1SecondHalf, l2SecondHalf);
        l2Cross.addStartVertexToEnd();
        result.add(l2Cross);

        return result;
    }

    private static Individual mergeResult(List<Vertex> l1FirstHalf, List<Vertex> l2FirstHalf) {
        ArrayList<Vertex> l1CrossPath = new ArrayList<Vertex>(l1FirstHalf);
        l1CrossPath.addAll(l2FirstHalf);
        Individual l1Cross = new Individual();
        l1Cross.path = l1CrossPath;
        return l1Cross;
    }
}
