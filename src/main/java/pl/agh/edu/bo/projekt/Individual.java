/*
 * 
 */
package pl.agh.edu.bo.projekt;

import edu.uci.ics.jung.graph.Graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Klasa ktora reprezentuje pojedyncza sciezke w grafie
 */
public class Individual {

	double length;

	ArrayList<Vertex> path;

	public Individual() {
		path = new ArrayList<Vertex>();
	}

	public void create(OurGraph ourGraph, int initPathLength) {

		Graph<Vertex, String> graph = ourGraph.getGraph();

        ArrayList<Vertex> verticlesFromGraph;
        boolean foundPath = false;

        int iterationsToFindOnePath = 0;

        while(!foundPath) {
            verticlesFromGraph = new ArrayList<Vertex>(graph.getVertices());
            path.clear();

            if (initPathLength > 1) {

                addRandomVerticesToPath(verticlesFromGraph, initPathLength);
                addStartVertexToEnd();

                if (validatePath(path, graph)) {
                    foundPath = true;
                }
                iterationsToFindOnePath++;
            }
        }

        AlgorithmGUI.logTextArea.append("Iterations to find one path: " + iterationsToFindOnePath + "\n");

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

        if (hasVisitedMoreThanOnce(path))
            return false;

        if (verticesAreNotConnected(path, dg))
            return false;

        return true;
    }

    private boolean verticesAreNotConnected(List<Vertex> path, Graph<Vertex, String> dg) {
        for (int i = 0; i < path.size()-1; i++) {
            if(!dg.isSuccessor(path.get(i), path.get(i+1)))
                return true;
        }
        return false;
    }

    private boolean hasVisitedMoreThanOnce(List<Vertex> path) {
        ArrayList<Vertex> visited = new ArrayList<Vertex>();
        for (int i = 0; i < path.size()-1; i++){
            if(visited.contains(path.get(i)))
                return true;
            visited.add(path.get(i));
        }
        return false;
    }

    public void evaluate() {
		length = path.size();
	}

	public void mutate(OurGraph ourGraph) {

        Random random = new Random();
        boolean inserted = false;
        int iterations = 0;

        CopyOnWriteArrayList<Vertex> lstFromGraph = getNotVisitedVertices(ourGraph);

        int maxPossibleIterations = lstFromGraph.size() ^ path.size();

        if(lstFromGraph.size() > 0) {
            while (!inserted && iterations < maxPossibleIterations) {
                // random not first, not last, not more than path.size()
                int randomId2 = random
                        .nextInt(((path.size()-1) - 1) + 1) + 1;

                Collections.shuffle(lstFromGraph);
                path.add(randomId2, lstFromGraph.get(0));

                //rollback
                if (!validatePath(path, ourGraph.getGraph())) {
                    path.remove(randomId2);
                    iterations++;
                } else {
                    inserted = true;
                }

            }
        }
	}

    private CopyOnWriteArrayList<Vertex> getNotVisitedVertices(OurGraph ourGraph) {
        CopyOnWriteArrayList<Vertex> lstFromGraph = new CopyOnWriteArrayList<Vertex>(ourGraph.getGraph()
                .getVertices());

        for (Vertex vertex : lstFromGraph) {
            if (path.contains(vertex)) {
                lstFromGraph.remove(vertex);
            }
        }
        return lstFromGraph;
    }


    
    public static List<Individual> crossoverNew(ArrayList<Vertex> l1, ArrayList<Vertex> l2) {
        //split to halves
        List<Vertex> l1FirstHalf = l1.subList(0, l1.size() / 2);
        List<Vertex> l1SecondHalf = l1.subList(l1FirstHalf.size(), l1.size() );

        List<Vertex> l2FirstHalf = l2.subList(0, l2.size()/2);
        List<Vertex> l2SecondHalf = l2.subList(l2FirstHalf.size(), l2.size());

        List<Individual> result = new ArrayList<Individual>();

        //prepare first path
        Individual l1Cross = mergeResult(l1FirstHalf, l2FirstHalf);
        l1Cross.addStartVertexToEnd();
        result.add(l1Cross);

        //prepare second path
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
