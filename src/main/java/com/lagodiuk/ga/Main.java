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
    private JTextField seedField;
    private JLabel chromosome;
    private JLabel iteration;
    private JLabel population1;
    private JLabel generation;
    private JLabel score;

    public int populationSize = 240;
    public double surviveRate = 0.5;
    public int numberOfPairs = 0;
    public int numOfChromosome = 1000;
    public int evolveCount = 5;
    public int coordinateRange = 10;
    public long seed;
    public Random random = new Random();

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
                if(seedField.getText() != null && !seedField.getText().isEmpty()) {
                    seed = Long.parseLong(seedField.getText());
                    random.setSeed(seed);
                }

                Population population = createInitialPopulation(populationSize, numOfChromosome, coordinateRange, numberOfPairs, random);
                Fitness fitnessFunc = new Fitness();
                GeneticAlgorithm ga = new GeneticAlgorithm(population, fitnessFunc, surviveRate, random);
                ga.evolve(evolveCount);

                IterationListener iterationListener = ga.getIterationListener();
                int i = iterationListener.cList.size() - 1;
                chromosome.setText(iterationListener.cList.get(i).toString());
                iteration.setText(String.valueOf(i));
                population1.setText(iterationListener.pList.get(i).toString());
                generation.setText(iterationListener.fList.get(i).toString());
                score.setText(iterationListener.eList.get(i).toString());
                //result.setText(String.format("%s\t%s\t%s\t%s\t%s", i, iterationListener.pList.get(i), iterationListener.fList.get(i), iterationListener.eList.get(i), iterationListener.cList.get(i)));
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

    private static Population createInitialPopulation(int populationSize, int numOfChromosome, int coordinateRange, int numberOfPairs, Random random) {
        Population population = new Population(numOfChromosome);
        //coordinateRange = 10;
        for (int i = 0; i < populationSize; i++) {
            Chromosome base;
            if(numberOfPairs == 0) {
                base = new Chromosome(random);  //random number of pairs
            }else {
                base = new Chromosome(numberOfPairs, random);
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
