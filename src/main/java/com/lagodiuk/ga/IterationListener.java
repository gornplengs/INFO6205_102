package com.lagodiuk.ga;

import java.util.ArrayList;
import java.util.List;

public class IterationListener {
    //private final double threshold = 1e-5;
    private int iteration;
    List<Chromosome> cList;
    List<Long> fList;
    List<Integer> pList;
    List<Double> eList;

    public IterationListener(int iteration) {
        this.iteration = iteration;
        this.cList = new ArrayList<>();
        this.fList = new ArrayList<>();
        this.pList = new ArrayList<>();
        this.eList = new ArrayList<>();
    }

    public IterationListener() {
        this.iteration = 7;
        this.cList = new ArrayList<>();
        this.fList = new ArrayList<>();
        this.pList = new ArrayList<>();
        this.eList = new ArrayList<>();
    }
    public void update(GeneticAlgorithm ga) {
        Chromosome best = ga.getBest();
        Long bestFitness = ga.fitness(best);
        int population = ga.getPopulation();

        double score = expression(ga.fitness(best));
        cList.add(best);
        fList.add(bestFitness);
        eList.add(score);
        pList.add(population);
    }

    public double expression(double m) {
        double score =  m / 1000;
        score = score * 100;
        return  score;

    }

    public void printResult() {
        System.out.println(String.format("%s\t%s\t%s\t%s\t%s", "iter", "population", "generation", "score", "chromosome"));
        // Listener prints best achieved solution


        for (int i = 0; i < cList.size(); i++) {
//         if(pList.get(i) == 1){
//             System.out.println(String.format("%s\t%s\t%s\t%s\t%s", i, pList.get(i), fList.get(i), eList.get(i), cList.get(i)));
//             break;
//         }
         System.out.println(String.format("%s\t%s\t%s\t%s\t%s", i, pList.get(i), fList.get(i), eList.get(i), cList.get(i)));

        }
        // If fitness is satisfying - we can stop Genetic algorithm
        //if (bestFitness < this.threshold)  ga.terminate();
    }
}
