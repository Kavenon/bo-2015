package pl.agh.edu.bo.projekt;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class ConfigArea {

    JTextField crossoverProbability;
    JTextField mutationProbability;
    JTextField maxIterations;
    JTextField initPathLength;
    JTextField populationSize;

    private static final float crossProbDefault = 0.5f;
    private static final float mutProbDefault = 0.5f;
    private static final int maxIterDefault = 100;
    private static final int initPathDefault = 3;
    private static final int popsizeDefault = 20;


    public ConfigArea() {
        crossoverProbability = new JTextField(crossProbDefault + "", 3);
        mutationProbability = new JTextField(mutProbDefault + "", 3);
        maxIterations = new JTextField(maxIterDefault + "", 3);
        initPathLength = new JTextField(initPathDefault + "", 3);
        populationSize = new JTextField(popsizeDefault + "", 3);
    }

    public Component create() {
        JPanel p = new JPanel();
        p.setLayout(new GridBagLayout());

        Border margin = new EmptyBorder(10, 10, 10, 10);
        Border title = BorderFactory.createTitledBorder("Konfiguracja");

        p.setBorder(BorderFactory.createCompoundBorder(title, margin));

        JPanel populationSizePanel = populationSizeSlider();
        JPanel initPathLengthPanel = initPathLengh();
        JPanel maxIterationsPanel = maxIterations();
        JPanel mutationProbabilityPanel = mutationProbability();
        JPanel crossoverProbabilityPanel = crossoverProbability();


        p.add(populationSizePanel, new GBC(0, 1).setAnchor(GridBagConstraints.NORTH));
        p.add(initPathLengthPanel, new GBC(0, 2).setAnchor(GridBagConstraints.NORTH));
        p.add(maxIterationsPanel, new GBC(0, 3).setAnchor(GridBagConstraints.NORTH));
        p.add(mutationProbabilityPanel, new GBC(0, 4).setAnchor(GridBagConstraints.NORTH));
        p.add(crossoverProbabilityPanel, new GBC(0, 5).setAnchor(GridBagConstraints.NORTH));

        return p;

    }

    private JPanel crossoverProbability() {

        JPanel container = new JPanel(new BorderLayout());

        JPanel inputPanel = new JPanel();

        JSlider slider = new JSlider(0, 100, 50);
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();
                Float value = (float) source.getValue() / 100;
                crossoverProbability.setText(String.valueOf(value));
            }
        });

        inputPanel.add(slider);
        inputPanel.add(crossoverProbability);

        container.add(new JLabel("Prawdopodobieństwo krzyżowania"), BorderLayout.NORTH);
        container.add(inputPanel, BorderLayout.CENTER);
        return container;
    }

    private JPanel mutationProbability() {

        JPanel container = new JPanel(new BorderLayout());
        JPanel inputPanel = new JPanel();

        JSlider slider = new JSlider(0, 100, 50);
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();
                Float value = (float) source.getValue() / 100;
                mutationProbability.setText(String.valueOf(value));
            }
        });

        inputPanel.add(slider);
        inputPanel.add(mutationProbability);

        container.add(new JLabel("Prawdopodobieństwo mutacji"), BorderLayout.NORTH);
        container.add(inputPanel, BorderLayout.CENTER);
        return container;

    }

    private JPanel maxIterations() {

        JPanel container = new JPanel(new BorderLayout());
        JPanel inputPanel = new JPanel();

        JSlider slider = new JSlider(0, 1000, maxIterDefault);
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();
                maxIterations.setText(String.valueOf(source.getValue()));
            }
        });

        inputPanel.add(slider);
        inputPanel.add(maxIterations);

        container.add(new JLabel("Ilość iteracji"), BorderLayout.NORTH);
        container.add(inputPanel, BorderLayout.CENTER);
        return container;

    }

    private JPanel initPathLengh() {


        JPanel container = new JPanel(new BorderLayout());
        JPanel inputPanel = new JPanel();

        JSlider slider = new JSlider(0, 100, initPathDefault);
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();
                initPathLength.setText(String.valueOf(source.getValue()));
            }
        });
        inputPanel.add(slider);
        inputPanel.add(initPathLength);

        container.add(new JLabel("Początkowa długość scieżki"), BorderLayout.NORTH);
        container.add(inputPanel, BorderLayout.CENTER);
        return container;
    }

    private JPanel populationSizeSlider() {

        JPanel container = new JPanel(new BorderLayout());
        JPanel inputPanel = new JPanel();

        JSlider slider = new JSlider(0, 100, popsizeDefault);
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider) e.getSource();
                populationSize.setText(String.valueOf(source.getValue()));
            }
        });

        inputPanel.add(slider);
        inputPanel.add(populationSize);

        container.add(new JLabel("Liczebność populacji"), BorderLayout.NORTH);
        container.add(inputPanel, BorderLayout.CENTER);
        return container;
    }

}
