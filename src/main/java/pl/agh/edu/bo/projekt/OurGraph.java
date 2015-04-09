package pl.agh.edu.bo.projekt;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Paint;
import java.util.Collection;

import javax.swing.JFrame;

import org.apache.commons.collections15.Transformer;

import edu.uci.ics.jung.algorithms.layout.KKLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;


public class OurGraph {

	private int n;
	private double p;
	private UndirectedSparseGraph<Integer, String> dg;
	
	public Graph<Integer, String> getGraph() {
		return dg;
	}

	public OurGraph(int n, double p) {
		this.n = n;
		this.p = p;
	}
	
	/*public Vertex getVertexById(int id){
		Collection<Integer> vertices = dg.getVertices();
		for(Vertex vertex : vertices){
			if(vertex.getId() == id){
				return vertex;
			}
		}
		
		return null;		
	}*/

	public Graph<Integer, String> generateRandomGraph() {

		dg = new UndirectedSparseGraph<Integer, String>();
		for (int i = 0; i < n; i++) {
			dg.addVertex(i);
		}	

		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				if (Math.random() < p) {
					dg.addEdge(i + "-to-" + j, i, j);
				}
			}
		}
		return dg;
	}

	public void printGraph(){
		
	}
	public static void main(String[] args) {

		OurGraph rgg = new OurGraph(20, 0.5);

		// int maxEdgeCount = (n*(n-1))>>1;
		Graph<Integer, String> g = rgg.generateRandomGraph();

		// Add some edges. From above we defined these to be of type String
		// Note that the default is for undirected edges.
		Layout<Integer, String> layout = new KKLayout<Integer, String>(g);
		layout.setSize(new Dimension(1200, 600)); // sets the initial size of
		// the space
		// The BasicVisualizationServer<V,E> is parameterized by the edge types
		BasicVisualizationServer<Integer, String> vv = new BasicVisualizationServer<Integer, String>(
				layout);
		
	   Transformer<Integer,Paint> vertexColor = new Transformer<Integer,Paint>() {
            public Paint transform(Integer v) {
                if(v == 5) return Color.GREEN;
                return Color.RED;
            }
        };
		
		vv.getRenderContext().setVertexFillPaintTransformer(vertexColor);
		vv.setPreferredSize(new Dimension(1200, 600)); // Sets the viewing area
		// size
		JFrame frame = new JFrame("Simple Graph View");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(vv);
		frame.pack();
		frame.setVisible(true);
	}

}