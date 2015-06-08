package pl.agh.edu.bo.projekt;

import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import edu.uci.ics.jung.io.GraphIOException;
import edu.uci.ics.jung.io.graphml.*;
import org.apache.commons.collections15.Transformer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

public class GraphIO {
    public static UndirectedSparseGraph<Vertex, String> readGraphFromFile(File file) {
        Reader reader = null;
        try {
            reader = new FileReader(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Transformer<NodeMetadata, Vertex> vtrans = new Transformer<NodeMetadata,Vertex>(){
            public Vertex transform(NodeMetadata nmd){
                Vertex v = new Vertex(Integer.valueOf(nmd.getId()));
                return v;
            }
        };

        Transformer<EdgeMetadata, String> etrans = new Transformer<EdgeMetadata,String>(){
            public String transform(EdgeMetadata emd){
                return emd.getSource() + "-to-" + emd.getTarget();
            }
        };

        Transformer<HyperEdgeMetadata, String> hetrans = new Transformer<HyperEdgeMetadata,String>(){
            public String transform(HyperEdgeMetadata emd){
                return String.valueOf(emd.getEdge());
            }
        };
        Transformer<GraphMetadata, UndirectedSparseGraph<Vertex,String>> gtrans = new Transformer<GraphMetadata,UndirectedSparseGraph<Vertex,String>>(){
            public UndirectedSparseGraph<Vertex,String> transform( GraphMetadata gmd ){
                return new UndirectedSparseGraph<Vertex,String>();
            }
        };

        GraphMLReader2<UndirectedSparseGraph<Vertex,String> , Vertex , String> gmlr =
                new GraphMLReader2<UndirectedSparseGraph<Vertex,String> ,Vertex, String>(
                        reader,
                        gtrans,
                        vtrans,
                        etrans,
                        hetrans);

        UndirectedSparseGraph<Vertex,String> g = null;
        try {
            g = gmlr.readGraph();
        } catch (GraphIOException e) {
            e.printStackTrace();
        }
        return g;
    }
}
