package pl.agh.edu.bo.projekt;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

public class AlgorithmGUI {

    private static final int PADDING_TOP = 5;
    private static final int PADDING_RIGHT = 5;
    private static final int PADDING_BOTTOM = 5;
    private static final int PADDING_LEFT = 5;

    private static final Insets insets = new Insets(PADDING_TOP, PADDING_RIGHT, PADDING_BOTTOM, PADDING_LEFT);


    static JTextArea logTextArea = new JTextArea();
    static JFreeChart chart;
    static App algorithm = new App();
    static ConfigArea configArea = new ConfigArea();

    static DynamicTimeSeriesCollection dataset;

    static Thread algorithmThread = new Thread(algorithm);

    static LinkedList<Float> bestDatasetArchive;

    public static void main(final String args[]) {


        final JFrame frame = new JFrame("Algorytm genetyczny - najdłuższy cykl prosty");
        MenuCreator.createMenuBar(frame, logTextArea);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());

        addComponent(frame, createChartArea(), 0, 0, 2, 2, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 7, 3);
        addComponent(frame, configArea.create(), 2, 0, 1, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 1, 1);
        addComponent(frame, createButtonArea(), 2, 1, 1, 2, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 1, 1);
        addComponent(frame, createTextArea(), 0, 2, 2, 1, GridBagConstraints.CENTER, GridBagConstraints.BOTH, 1, 1);

        frame.setSize(500,500);
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }

    private static Component createChartArea() {

        Border margin = new EmptyBorder(10, 10, 10, 10);
        Border title = BorderFactory.createTitledBorder("Wykres");

        dataset = new DynamicTimeSeriesCollection(1, 1000, new Second());
        dataset.setTimeBase(new Second(0, 0, 0, 23, 1, 2014));
        dataset.addSeries(new float[1], 0, "best");

        chart = ChartFactory.createTimeSeriesChart("",
                "Czas", "", dataset, false, true,
                false);
        chart.setBackgroundPaint(new Color(0xFF, 0xFF, 0xFF, 0));

        XYPlot plot = chart.getXYPlot();
        plot.getRenderer().setSeriesPaint(0, Color.BLUE);

        DateAxis axis = (DateAxis) plot.getDomainAxis();
        axis.setFixedAutoRange(10000);
        axis.setDateFormatOverride(new SimpleDateFormat(""));

        ChartPanel cp = new ChartPanel(chart);
        cp.setBorder(BorderFactory.createCompoundBorder(title, margin));

        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                Timer timer = new Timer(100, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        update();
                    }
                });
                timer.start();

            }
        });

        return cp;

    }

    private static void update() {
        if (algorithm.running && algorithm.bestIndividual != null && algorithm.bestIndividual.path.size() > 0) {
            float[] newData = new float[1];
            newData[0] = algorithm.bestIndividual.path.size();

            dataset.advanceTime();
            dataset.appendData(newData);
            bestDatasetArchive.add(Float.valueOf(newData[0]));
        }

    }


    private static Component createButtonArea() {
        JPanel p = new JPanel();
        p.setLayout(new FlowLayout());

        JButton startButton = new JButton("Start");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // app.run
                AbstractButton aButton = (AbstractButton) e.getSource();

                ButtonModel aModel = aButton.getModel();

                if (!algorithm.running) {

                    if (MenuCreator.graph == null) {
                        JOptionPane.showMessageDialog(null, "Najpierw otwórz graf z pliku");
                    } else {
                        cleanUpAndStartNewThread();
                    }

                }

            }
        });

        JButton stopButton = new JButton("Stop");
        stopButton.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                AbstractButton aButton = (AbstractButton) e.getSource();

                ButtonModel aModel = aButton.getModel();
                if (aModel.isPressed() && algorithm.running) {
                    algorithm.running = false;

                }

            }
        });

        JButton plotButton = new JButton("Wykres");
        plotButton.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                AbstractButton aButton = (AbstractButton) e.getSource();

                ButtonModel aModel = aButton.getModel();
                if (aModel.isPressed()) {

                    if (bestDatasetArchive != null && bestDatasetArchive.size() > 0) {

                        final JFrame framen = new JFrame("Wykres");

                        TimeSeries bestTimeSeries = new TimeSeries("bestIndividualArchive");
                        TimeSeriesCollection timeSeriesCollection = new TimeSeriesCollection();

                        timeSeriesCollection.addSeries(bestTimeSeries);

                        Day d = new Day(new Date());
                        RegularTimePeriod regularTimePeriod = d.next();
                        for (float item : bestDatasetArchive) {
                            bestTimeSeries.add(regularTimePeriod, item);
                            regularTimePeriod = regularTimePeriod.next();
                        }

                        JFreeChart chart = ChartFactory.createTimeSeriesChart(
                                "", "Czas", "", timeSeriesCollection, false, true, false);
                        chart.setBackgroundPaint(new Color(0xFF, 0xFF, 0xFF, 0));
                        XYPlot plot = chart.getXYPlot();
                        plot.getRenderer().setSeriesPaint(0, Color.BLUE);

                        DateAxis xAxis1 = (DateAxis) plot.getDomainAxis();
                        xAxis1.setAutoRange(true);
                        xAxis1.setDateFormatOverride(new SimpleDateFormat(""));

                        ChartPanel cp = new ChartPanel(chart);
                        framen.add(cp);
                        framen.setSize(500, 500);
                        framen.pack();

                        framen.setVisible(true);
                    }

                }

            }
        });

        p.add(startButton);
        p.add(stopButton);
        p.add(plotButton);
        return p;

    }

    private static void cleanUpAndStartNewThread() {
        bestDatasetArchive = new LinkedList<Float>();

        algorithm.crossoverProbability = Double.valueOf(configArea.crossoverProbability.getText());
        algorithm.mutationProbability = Double.valueOf(configArea.mutationProbability.getText());
        algorithm.maxIterations = Integer.valueOf(configArea.maxIterations.getText());
        algorithm.initPathLength = Integer.valueOf(configArea.initPathLength.getText());
        algorithm.populationSize = Integer.valueOf(configArea.populationSize.getText());
        algorithm.bestIndividual = null;
        algorithm.ourGraph = new OurGraph(MenuCreator.graph);
        algorithm.running = true;
        algorithmThread = new Thread(algorithm);
        algorithmThread.start();
    }

    private static Component createTextArea() {
        logTextArea.setEditable(false);
        JScrollPane scroll = new JScrollPane(logTextArea,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        return scroll;
    }

    private static void addComponent(Container container, Component component, int gridx, int gridy,
                                     int gridwidth, int gridheight, int anchor, int fill, int widthx, int widthy) {
        GridBagConstraints gbc = new GridBagConstraints(gridx, gridy, gridwidth, gridheight, widthx, widthy,
                anchor, fill, insets, 0, 0);
        container.add(component, gbc);
    }
}