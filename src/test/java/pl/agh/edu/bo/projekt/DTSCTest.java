package pl.agh.edu.bo.projekt;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.*;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.LinkedList;
import java.util.Random;

public class DTSCTest extends ApplicationFrame {

    private static final String TITLE = "Dynamic Series";
    private static final String START = "Start";
    private static final String STOP = "Stop";
    private static final float MINMAX = 100;
    private static final int COUNT = 2 * 60;
    private static final int FAST = 100;
    private static final int SLOW = FAST * 5;
    private static final Random random = new Random();
    private Timer timer;
     DynamicTimeSeriesCollection dataset;
    LinkedList<Float> floats = new LinkedList<Float>();

    public DTSCTest(final String title) {
        super(title);
          dataset =
                new DynamicTimeSeriesCollection(1, COUNT, new Second());
        dataset.setTimeBase(new Second());

        dataset.addSeries(new float[1], 0, "Gaussian data");
        gaussianData();
        JFreeChart chart = createChart(dataset);

        final JButton run = new JButton(STOP);
        run.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String cmd = e.getActionCommand();
                if (STOP.equals(cmd)) {
                    timer.stop();
                    run.setText(START);
                    createFull();
                } else {
                    timer.start();
                    run.setText(STOP);
                }
            }
        });



        this.add(new ChartPanel(chart), BorderLayout.CENTER);
        JPanel btnPanel = new JPanel(new FlowLayout());
        btnPanel.add(run);

        this.add(btnPanel, BorderLayout.SOUTH);

        timer = new Timer(FAST, new ActionListener() {

            float[] newData = new float[1];

            @Override
            public void actionPerformed(ActionEvent e) {
                newData[0] = randomValue();
                dataset.advanceTime();
                dataset.appendData(newData);
                floats.add(newData[0]);
            }
        });
    }

    private void createFull() {

        JFrame frame = new JFrame("testing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        TimeSeries timeSeries =  new TimeSeries("timeSeries1");
        TimeSeriesCollection timeSeriesCollection = new TimeSeriesCollection();

        timeSeriesCollection.addSeries(timeSeries );

        Day d = new Day(new Date());
        RegularTimePeriod regularTimePeriod = d.next();
        for (float item : floats) {


            timeSeries.add(regularTimePeriod,item);

            regularTimePeriod = regularTimePeriod.next();
        }

         JFreeChart chart = ChartFactory.createTimeSeriesChart(
                 TITLE, "hh:mm:ss", "milliVolts", timeSeriesCollection, true, true, false);
        XYPlot plot = chart.getXYPlot();
        DateAxis xAxis1 = (DateAxis) plot.getDomainAxis();
        xAxis1.setAutoRange(true);

        long minimum = xAxis1.getMinimumDate().getTime();
        long maximum = xAxis1.getMaximumDate().getTime();

        //DateRange range = new DateRange(minimum,maximum);
        xAxis1.setRange(minimum,maximum);
        ChartPanel cp = new ChartPanel(chart);
        frame.add(cp);
        frame.pack();

        frame.setVisible(true);

    }

    private float randomValue() {
        return (float) (random.nextGaussian() * MINMAX / 3);
    }

    private void gaussianData() {
        float[] d = new float[1];
        d[0] = 0.5f;
        for (int i = 0; i < COUNT; i++) {
            dataset.appendData(d);
            floats.add(Float.valueOf(d[0]));
        }

    }

    private JFreeChart createChart(final XYDataset dataset) {
        final JFreeChart result = ChartFactory.createTimeSeriesChart(
                TITLE, "hh:mm:ss", "milliVolts", dataset, true, true, false);
        final XYPlot plot = result.getXYPlot();
        ValueAxis domain = plot.getDomainAxis();
        domain.setAutoRange(true);
        ValueAxis range = plot.getRangeAxis();
        range.setRange(-MINMAX, MINMAX);
        return result;
    }

    public void start() {
        timer.start();
    }

    public static void main(final String[] args) {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                DTSCTest demo = new DTSCTest(TITLE);
                demo.pack();
                RefineryUtilities.centerFrameOnScreen(demo);
                demo.setVisible(true);
                demo.start();
            }
        });
    }
}