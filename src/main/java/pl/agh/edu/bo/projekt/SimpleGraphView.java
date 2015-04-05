package pl.agh.edu.bo.projekt;

import java.awt.Dimension;

import javax.swing.JFrame;

import edu.uci.ics.jung.algorithms.layout.KKLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;


public class SimpleGraphView {

	private int n;
	private double p;

	public SimpleGraphView(int n, double p) {
		this.n = n;
		this.p = p;
	}

	public Graph<Integer, String> generateRandomGraph() {

		DirectedSparseGraph<Integer, String> dg = new DirectedSparseGraph<Integer, String>();
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

	public static void main(String[] args) {

		SimpleGraphView rgg = new SimpleGraphView(20, 0.5);

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
		vv.setPreferredSize(new Dimension(1200, 600)); // Sets the viewing area
		// size
		JFrame frame = new JFrame("Simple Graph View");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(vv);
		frame.pack();
		frame.setVisible(true);
	}

}