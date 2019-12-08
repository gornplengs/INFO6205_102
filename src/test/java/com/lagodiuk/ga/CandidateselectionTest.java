package com.lagodiuk.ga;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class CandidateselectionTest {

    @Test
    public void evolve() {
        Random random = new Random();
        Fitness fitnessFunc = new Fitness();

        Population parent = createInitialPopulation(240);
        GeneticAlgorithm ga = new GeneticAlgorithm(parent, fitnessFunc, random);
        parent.sortPopulationByFitness(ga.getChromosomesComparator());
        Chromosome parentBest = parent.getChromosomeByIndex(0);
        long bestFitness = ga.fitness(parentBest);

        ga.evolve();
        Population p1 = ga.getNewPopulation();
        p1.sortPopulationByFitness(ga.getChromosomesComparator());
        Chromosome p1Best = p1.getChromosomeByIndex(0);
        long p1BestFitness = ga.fitness(p1Best);
        int p1PopulationSize = p1.getSize();

        ga.evolve();
        Population p2 = ga.getNewPopulation();
        p2.sortPopulationByFitness(ga.getChromosomesComparator());
        Chromosome p2Best = p2.getChromosomeByIndex(0);
        long p2BestFitness = ga.fitness(p2Best);
        int p2PopulationSize = p2.getSize();

        // children's populationSize is half of parent's populationSize
        assertEquals(p1PopulationSize,120);
        assertEquals(p2PopulationSize,60);
        assertEquals((p1BestFitness >= bestFitness), (boolean) true);
        assertEquals((p2BestFitness >= bestFitness) && (p2BestFitness >= p1BestFitness), (boolean) true);
    }

    public static Population createInitialPopulation(int populationSize) {
        Population population = new Population();
        Random random = new Random();
        int coordinateRange = 10;
        for (int i = 0; i < populationSize; i++) {
            Chromosome base = new Chromosome(10, random);
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
