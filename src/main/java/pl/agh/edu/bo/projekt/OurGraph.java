package pl.agh.edu.bo.projekt;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Paint;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import com.ctc.wstx.dtd.ContentSpec;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
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

	public Graph<Vertex, String> generateRandomGraph() {

		dg = new UndirectedSparseGraph<Vertex, String>();

		for (int i = 0; i < n; i++) {
			dg.addVertex(new Vertex(i));
		}

        List<Vertex> verticles = new ArrayList(dg.getVertices());

        for (int i = 0; i < dg.getVertexCount(); i++) {
            for (int j = i + 1; j < dg.getVertexCount(); j++) {
                if (Math.random() < Constants.EDGE_PROBABILITY) {
                    if(i != j)
                        dg.addEdge(i + "-to-" + j, verticles.get(i), verticles.get(j));
                }
            }
        }

		return dg;
	}

	public void createEdges(Individual indiv) {
		for (int i = 0; i < indiv.path.size() - 1; i++) {
			dg.addEdge(indiv.path.get(i) + " - to - " + indiv.path.get(i + 1),
					indiv.path.get(i), indiv.path.get(i + 1));
		}
	}

	public void printGraph() {

		Transformer<Vertex, Point2D> locationTransformer = new Transformer<Vertex, Point2D>() {
			public Point2D transform(Vertex vertex) {
				return new Point2D.Double((double) vertex.getX(),
						(double) vertex.getY());
			}
		};

		Layout<Vertex, String> layout = new StaticLayout<Vertex, String>(dg,
				locationTransformer);
		layout.setSize(new Dimension(Constants.CANVAS_WIDTH,
				Constants.CANVAS_HEIGHT));
		
		BasicVisualizationServer<Vertex, String> vv = new BasicVisualizationServer<Vertex, String>(
				layout);
		Transformer<Vertex, Paint> vertexColor = new Transformer<Vertex, Paint>() {
			public Paint transform(Vertex v) {
				/*if (v.getId() == 5)
					return Color.GREEN;*/
				return Color.RED;
			}
		};
        vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
		vv.getRenderContext().setVertexFillPaintTransformer(vertexColor);
		vv.setPreferredSize(new Dimension(Constants.CANVAS_WIDTH,
				Constants.CANVAS_HEIGHT)); // Sets the viewing area
		// size
		JFrame frame = new JFrame("Simple Graph View");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(vv);
		frame.pack();
		frame.setVisible(true);
	}
	
}