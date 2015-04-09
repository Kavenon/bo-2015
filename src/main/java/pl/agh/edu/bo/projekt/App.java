package pl.agh.edu.bo.projekt;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Paint;
import java.util.Random;

import javax.swing.JFrame;

import org.apache.commons.collections15.Transformer;

import edu.uci.ics.jung.algorithms.layout.KKLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {    	
    	// make it random
        Random random = new Random();        
     
        int verticles = random.nextInt(20)+5;
        
        float probability = 0.5f;
       
    	// CREATE GRAPH
    	OurGraph graph = new OurGraph(verticles, probability);
    	graph.generateRandomGraph();
    	
    	//Initialization
    	int initPaths = random.nextInt(5)+2;
    	
    	
    	
    	Population population = new Population(initPaths, graph);
    	
    	
    	
    	
    }
}
