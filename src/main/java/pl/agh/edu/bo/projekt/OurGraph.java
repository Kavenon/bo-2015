package pl.agh.edu.bo.projekt;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Paint;
import java.util.Collection;

import javax.swing.JFrame;

import edu.uci.ics.jung.algorithms.layout.KKLayout;
import edu.uci.ics.jung.algorithms.*;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.*;
import edu.uci.ics.jung.visualization.*;


public class OurGraph {

	private int n;
	private double p;
	private UndirectedSparseGraph<Vertex, String> dg;
	
	public Graph<Vertex, String> getGraph() {
		return dg;
	}

	public OurGraph(int n, double p) {
		this.n = n;
		this.p = p;
	}
	
	public Vertex getVertexById(int id){
		Collection<Vertex> vertices = dg.getVertices();
		for(Vertex vertex : vertices){
			if(vertex.getId() == id){
				return vertex;
			}
		}
		
		return null;		
	}

	public Graph<Vertex, String> generateRandomGraph() {

		dg = new UndirectedSparseGraph<Vertex, String>();
		for (int i = 0; i < n; i++) {
			dg.addVertex(new Vertex(i));
		}	

		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				if (Math.random() < p) {
					dg.addEdge(i + "-to-" + j, getVertexById(i), getVertexById(j));
				}
			}
		}
		return dg;
	}

	public static void main(String[] args) {

		OurGraph rgg = new OurGraph(20, 0.5);

		// int maxEdgeCount = (n*(n-1))>>1;
		Graph<Vertex, String> g = rgg.generateRandomGraph();

		// Add some edges. From above we defined these to be of type String
		// Note that the default is for undirected edges.
		Layout<Vertex, String> layout = new KKLayout<Vertex, String>(g);
		layout.setSize(new Dimension(1200, 600)); // sets the initial size of
		// the space
		// The BasicVisualizationServer<V,E> is parameterized by the edge types
		BasicVisualizationServer<Vertex, String> vv = new BasicVisualizationServer<Vertex, String>(
				layout);
		
	   Transformer<Integer,Paint> vertexColor = new Transformer<Integer,Paint>() {
            public Paint transform(Integer i) {
                if(i == 1) return Color.GREEN;
                return Color.RED;
            }
        };
		
		vv.getRenderContext().setVertexDrawPaintTransformer(vertexColor);
		vv.setPreferredSize(new Dimension(1200, 600)); // Sets the viewing area
		// size
		JFrame frame = new JFrame("Simple Graph View");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(vv);
		frame.pack();
		frame.setVisible(true);
	}

}