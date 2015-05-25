package pl.agh.edu.bo.projekt;

import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


public class LearningTests {
    @Test
    public void testSuccessor(){
        int n = 5;
        double p = 0.5;
        UndirectedSparseGraph<Vertex,String> g = new UndirectedSparseGraph<Vertex, String>();

        for (int i = 0; i < n; i++) {
            g.addVertex(new Vertex(i));
        }

        List<Vertex> verticles = new ArrayList(g.getVertices());


        for (int i = 0; i < g.getVertexCount(); i++) {
            for (int j = i + 1; j < g.getVertexCount(); j++) {
                if (Math.random() < p) {
                    g.addEdge(i + "-to-" + j, verticles.get(i), verticles.get(j));
                }
            }
        }

        //Assert.assertTrue(g.isSuccessor(verticles.get(0), verticles.get(1)));

        //System.out.println(g.toString());


    }
    @Test
    public void testaddremove(){
        ArrayList<Integer> test = new ArrayList<Integer>();
        test.add(1);
        test.add(2);
        test.add(3);
        test.add(4);

        System.out.println(test);

        test.add(1,0);

        System.out.println(test);

        test.remove(1);

        System.out.println(test);
    }
}
