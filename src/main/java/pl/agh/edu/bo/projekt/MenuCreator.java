package pl.agh.edu.bo.projekt;

import edu.uci.ics.jung.graph.UndirectedSparseGraph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class MenuCreator {

    public static UndirectedSparseGraph<Vertex, String> graph;

    public static void createMenuBar(JFrame mainFrame, JTextArea logTextArea) {

        JMenuBar menubar = new JMenuBar();

        JMenu file = new JMenu("Plik");

        JMenuItem generateGraphItem = createGenerateGraphMenuItem(mainFrame,logTextArea);

        JMenuItem openGraphItem = createOpenGraphMenuItem(mainFrame,logTextArea);

        file.add(generateGraphItem);
        file.add(openGraphItem);
        menubar.add(file);

        mainFrame.setJMenuBar(menubar);

    }

    private static JMenuItem createOpenGraphMenuItem(final JFrame mainFrame, final JTextArea logTextArea) {
        JMenuItem openGraphItem = new JMenuItem("Wczytaj graf");
        openGraphItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {

                JFileChooser fileChooser = new JFileChooser();
                if (fileChooser.showOpenDialog(mainFrame) == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();

                    graph = GraphIO.readGraphFromFile(file);
                    logTextArea.append("Wczytano graf: " + file.getAbsolutePath() + "\n");
                    logTextArea.append("Wierzchołki: " + graph.getVertexCount() + "\n");

                }


            }
        });
        return openGraphItem;
    }

    private static JMenuItem createGenerateGraphMenuItem(Component mainFrame, final JTextArea logTextArea) {
        JMenuItem generateGraphItem = new JMenuItem("Generuj graf");

        generateGraphItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {

                GraphCreator graphCreator = new GraphCreator();
                JOptionPane.showConfirmDialog(null, graphCreator, "Test", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);

                if(graphCreator.filePath != null && graphCreator.filePath.length() > 0 )
                    logTextArea.append("Utworzono graf: " + graphCreator.filePath + "\n");
            }
        });
        return generateGraphItem;
    }
}
