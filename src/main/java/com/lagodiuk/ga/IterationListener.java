package com.lagodiuk.ga;

public class IterationListener {
    private final double threshold = 1e-5;

    public void update(GeneticAlgorithm ga) {
        int iteration = ga.getIteration();
        Chromosome best = ga.getBest();
        Long bestFitness = ga.fitness(best);


        // Listener prints best achieved solution
        System.out.println(String.format("%s\t%s\t%s", iteration, bestFitness, best));
        // If fitness is satisfying - we can stop Genetic algorithm
        if (bestFitness < this.threshold)  ga.terminate();
    }
}
