package pl.agh.edu.bo.projekt;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;
import edu.uci.ics.jung.io.GraphMLWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;

public class GraphCreator extends JPanel {
    JTextField verticesCount;
    JTextField edgeProbablity;
    String filePath;

    public GraphCreator() {
        super(new BorderLayout());

        DefaultFormBuilder builder = new DefaultFormBuilder(new FormLayout(""));
        builder.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        builder.appendColumn("right:pref");
        builder.appendColumn("3dlu");
        builder.appendColumn("fill:max(pref; 100px)");


        verticesCount = new JTextField();
        builder.append("Ilość wierzchołków:", verticesCount);


        edgeProbablity = new JTextField();
        builder.append("Prawdopodobieństwo krawędzi:", edgeProbablity);


        builder.append(new JButton(new GenerateGraphAction()));
        add(builder.getPanel());

    }

    private boolean validateFeed() {
        boolean valid = true;
        if (validateVerticlesCount()) {
            JOptionPane.showMessageDialog(null, "Uzupełnij poprawnie (> 0) ilość wierzchołków.", "Error", JOptionPane.ERROR_MESSAGE);
            valid = false;
        } else if (validateEdgePropability()) {
            JOptionPane.showMessageDialog(null, "Uzupełnij poprawnie <0,1> prawd. krawędzi. ", "Error", JOptionPane.ERROR_MESSAGE);
            valid = false;
        }

        return valid;
    }

    private boolean validateEdgePropability() {
        return this.edgeProbablity.getText().length() < 1 || Double.valueOf(this.edgeProbablity.getText()) > 1 || Double.valueOf(this.edgeProbablity.getText()) < 0;
    }

    private boolean validateVerticlesCount() {
        return this.verticesCount.getText().length() < 1 || Integer.valueOf(this.verticesCount.getText()) < 0;
    }


    private class GenerateGraphAction extends AbstractAction {
        public GenerateGraphAction() {
            super("Generuj graf");
        }

        public void actionPerformed(ActionEvent event) {

            if (validateFeed()) {

                JFileChooser fileChooser = new JFileChooser();
                if (fileChooser.showSaveDialog(GraphCreator.this) == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();

                    GraphMLWriter<Vertex, String> graphWriter =
                            new GraphMLWriter<Vertex, String>();


                    OurGraph graph = generateGraph();

                    PrintWriter out = null;
                    try {
                        out = new PrintWriter(
                                new BufferedWriter(
                                        new FileWriter(file)));
                        graphWriter.save(graph.getGraph(), out);

                        JOptionPane.showMessageDialog(null, "Zapisano do " + file.getAbsolutePath(), "Info", JOptionPane.INFORMATION_MESSAGE);

                        edgeProbablity.setText("");
                        verticesCount.setText("");
                        filePath = file.getAbsolutePath();

                    } catch (IOException e) {
                        JOptionPane.showMessageDialog(null, "Wystąpił błąd podczas zapisu", "Error", JOptionPane.ERROR_MESSAGE);
                        e.printStackTrace();
                    }
                }

            }


        }


    }

    private OurGraph generateGraph() {
        Integer vertCount = Integer.valueOf(verticesCount.getText());
        Double edgeProb = Double.valueOf(edgeProbablity.getText());

        OurGraph graph = new OurGraph(vertCount, edgeProb);
        graph.generateRandomGraph();
        return graph;
    }


    public static void main(String[] a) {
        JFrame f = new JFrame("Graph creator");
        f.setDefaultCloseOperation(2);
        f.add(new GraphCreator());
        f.pack();
        f.setVisible(true);
    }
}