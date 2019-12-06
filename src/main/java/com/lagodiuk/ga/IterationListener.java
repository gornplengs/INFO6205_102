package com.lagodiuk.ga;

import edu.neu.coe.info6205.life.base.Game;

import java.util.ArrayList;
import java.util.List;

public class IterationListener {
    private final double threshold = 1e-5;
    private int iteration;
    List<Chromosome> cList = new ArrayList<>();
    List<Long> fList = new ArrayList<>();
    List<Integer> pList = new ArrayList<>();
    List<Double> eList= new ArrayList<>();
    public IterationListener(int iteration) {
        this.iteration = iteration;
    }

    public IterationListener() {

    }

    public void update(GeneticAlgorithm ga) {

        Chromosome best = ga.getBest();
        Long bestFitness = ga.fitness(best);
        int population = ga.getPopulation();

        double score = expression((double)(ga.fitness(best))/1000);
        cList.add(best);
        fList.add(bestFitness);
        eList.add(score);
        pList.add(population);
    }

    public double expression(double m) {
        double score =  m/1000;
        return score;
    }

    public void printResult() {
        System.out.println(String.format("%s\t%s\t%s\t%s\t%s", "iter", "population", "generation", "score", "chromosome"));
        // Listener prints best achieved solution


        for (int i = 0; i<cList.size(); i++) {
         if(pList.get(i)==1){
             System.out.println(String.format("%s\t%s\t%s\t%s\t%s", i, pList.get(i), fList.get(i), eList.get(i), cList.get(i)));
             break;}

                System.out.println(String.format("%s\t%s\t%s\t%s\t%s", i, pList.get(i), fList.get(i), eList.get(i), cList.get(i)));

        }
        // If fitness is satisfying - we can stop Genetic algorithm
        //if (bestFitness < this.threshold)  ga.terminate();
    }
}
