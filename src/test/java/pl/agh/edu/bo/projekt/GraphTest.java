package pl.agh.edu.bo.projekt;

import org.junit.Test;

import java.util.Random;

public class GraphTest {
    @Test
    public void createRandomGraphTest() throws InterruptedException {
        Random random = new Random();
        OurGraph graph = App.createRandomGraph(random);
        graph.printGraph();

        Thread.sleep(5000);
    }
}
