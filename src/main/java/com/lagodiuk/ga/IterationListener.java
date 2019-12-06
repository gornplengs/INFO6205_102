package com.lagodiuk.ga;

import java.util.ArrayList;
import java.util.List;

public class IterationListener {
    private final double threshold = 1e-5;
    private int iteration;
    List<Chromosome> cList = new ArrayList<>();
    List<Long> fList = new ArrayList<>();
    List<Integer> pList = new ArrayList<>();

    public IterationListener(int iteration) {
        this.iteration = iteration;
    }
    public void update(GeneticAlgorithm ga) {

        Chromosome best = ga.getBest();
        Long bestFitness = ga.fitness(best);
        int population = ga.getPopulation();

        cList.add(best);
        fList.add(bestFitness);
        pList.add(population);
    }

    public void printResult() {
        System.out.println(String.format("%s\t%s\t%s\t%s", "iter", "population", "generation", "chromosome"));
        // Listener prints best achieved solution
        for (int i = 0; i<cList.size(); i++) {
            System.out.println(String.format("%s\t%s\t%s\t%s", i, pList.get(i), fList.get(i), cList.get(i)));
        }

        // If fitness is satisfying - we can stop Genetic algorithm
        //if (bestFitness < this.threshold)  ga.terminate();
    }
}
