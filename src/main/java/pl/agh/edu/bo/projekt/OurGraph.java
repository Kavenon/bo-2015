package pl.agh.edu.bo.projekt;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Paint;
import java.awt.geom.Point2D;
import java.util.Collection;
import java.util.Random;

import javax.swing.JFrame;

import org.apache.commons.collections15.Transformer;

import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.algorithms.layout.StaticLayout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;

public class OurGraph {

	private int n;
	private UndirectedSparseGraph<Vertex, String> dg;

	public Graph<Vertex, String> getGraph() {
		return dg;
	}

	public OurGraph(int n) {	
		this.n = n;	
	}

	public Vertex getVertexById(int id) {
		Collection<Vertex> vertices = dg.getVertices();
		for (Vertex vertex : vertices) {
			if (vertex.getId() == id) {
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
		
		return dg;
	}

	public void printGraph() {

	}

	// just for tests
	public static void main(String[] args) {

		// CREATE OWN VERTEX
		// zeby miec jego pozycje randomowa x, y
		// zmienic w generowaniu zeby sie nie dublowaly a reszta tak samo

		OurGraph rgg = new OurGraph(20);

		// int maxEdgeCount = (n*(n-1))>>1;
		Graph<Vertex, String> g = rgg.generateRandomGraph();

		// Add some edges. From above we defined these to be of type String
		// Note that the default is for undirected edges.

		Transformer<Vertex, Point2D> locationTransformer = new Transformer<Vertex, Point2D>() {
			Random random = new Random();

			public Point2D transform(Vertex vertex) {

				return new Point2D.Double((double) random.nextInt(1200),
						(double) random.nextInt(600));
			}
		};

		Layout<Vertex, String> layout = new StaticLayout<Vertex, String>(g,
				locationTransformer);
		layout.setSize(new Dimension(1200, 600)); // sets the initial size of
		// the space
		// The BasicVisualizationServer<V,E> is parameterized by the edge types
		BasicVisualizationServer<Vertex, String> vv = new BasicVisualizationServer<Vertex, String>(
				layout);

		Transformer<Vertex, Paint> vertexColor = new Transformer<Vertex, Paint>() {
			public Paint transform(Vertex v) {
				if (v.getId() == 5)
					return Color.GREEN;
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