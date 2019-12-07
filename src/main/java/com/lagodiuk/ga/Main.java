package com.lagodiuk.ga;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Main {
    private JPanel InputPanel;
    private JLabel populationLabel;
    private JTextField populationField;
    private JButton submitBTN;
    private JTextField surviveField;
    private JLabel surviveLabel;
    private JTextField pairNumField;
    private JLabel pairNumLabel;
    private JTextField chroNumField;
    private JLabel chroNumLabel;
    private JTextField evolveField;
    private JLabel evolveLabel;
    private JTextField rangeField;
    private JLabel resultHead;
    private JLabel result;
    private JTable table1;

    public int populationSize = 240;
    public double surviveRate = 0.5;
    public int numberOfPairs = 0;
    public int numOfChromosome = 1000;
    public int evolveCount = 5;
    public int coordinateRange = 10;

    public Main() {
        submitBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(populationField.getText() != null && !populationField.getText().isEmpty())
                    populationSize = Integer.parseInt(populationField.getText());
                if(surviveField.getText() != null && !surviveField.getText().isEmpty())
                    surviveRate = Double.parseDouble(surviveField.getText());
                if(chroNumField.getText() != null && !chroNumField.getText().isEmpty())
                    numOfChromosome = Integer.parseInt(chroNumField.getText());
                if(evolveField.getText() != null && !evolveField.getText().isEmpty())
                    evolveCount = Integer.parseInt(evolveField.getText());
                if(pairNumField.getText() != null && !pairNumField.getText().isEmpty())
                    numberOfPairs = Integer.parseInt(pairNumField.getText());
                if(rangeField.getText() != null && !rangeField.getText().isEmpty())
                    coordinateRange = Integer.parseInt(rangeField.getText());

                Population population = createInitialPopulation(populationSize, numOfChromosome, coordinateRange, numberOfPairs);
                Fitness fitnessFunc = new Fitness();
                GeneticAlgorithm ga = new GeneticAlgorithm(population, fitnessFunc, surviveRate);
                ga.evolve(evolveCount);

                IterationListener iterationListener = ga.getIterationListener();
                int i = iterationListener.cList.size() - 1;
                //resultHead.setText("Hello");
                resultHead.setText(String.format("%s\t%s\t%s\t%s\t%s", "iteration", "population", "generation", "score", "chromosome"));
                result.setText(String.format("%s\t%s\t%s\t%s\t%s", i, iterationListener.pList.get(i), iterationListener.fList.get(i), iterationListener.eList.get(i), iterationListener.cList.get(i)));
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Main");
        frame.setContentPane(new Main().InputPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private static Population createInitialPopulation(int populationSize, int numOfChromosome, int coordinateRange, int numberOfPairs) {
        Population population = new Population(numOfChromosome);
        Random random = new Random();
        //coordinateRange = 10;
        for (int i = 0; i < populationSize; i++) {
            Chromosome base;
            if(numberOfPairs == 0) {
                base = new Chromosome();  //random number of pairs
            }else {
                base = new Chromosome(numberOfPairs);
            }
            for(int j = 0; j < base.getNumberOfPairs(); j++) {
                int x = random.nextInt(coordinateRange);
                int y = random.nextInt(coordinateRange);
                base.addPairs(j, x, y);
            }
            population.addChromosome(base);
        }
        return population;
    }
}
