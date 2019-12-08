//package com.lagodiuk.ga;
//
//import org.junit.Test;
//
//import java.util.Random;
//
//import static org.junit.Assert.*;
//
//public class CandidateselectionTest {
//
//    @Test
//    public void evolve() {
//        Random random = new Random();
//        Fitness fitnessFunc = new Fitness();
//        Population parent = createInitialPopulation(240);
//        GeneticAlgorithm ga = new GeneticAlgorithm(parent, fitnessFunc, random);
//        parent.sortPopulationByFitness(ga.getChromosomesComparator());
//        Chromosome parentbest = parent.getChromosomeByIndex(0);
//        Population child = ga.evolve();
//        child.sortPopulationByFitness(ga.getChromosomesComparator());
//        Chromosome childbest = child.getChromosomeByIndex(0);
//        long bestFitness = ga.fitness(parentbest);
//        long childbestFitness = ga.fitness(childbest);
//        int childPopulationSize = child.getSize();
////        for(int i = 0;i < child.getSize() ; i++){
////            childbestFitness = ga.fitness(childbest);
////            if(ga.fitness(child.getChromosomeByIndex(i))>childbestFitness){
////                childbestFitness =  ga.fitness(child.getChromosomeByIndex(i));
////            }
////        }
//
//        // children's populationSize is half of parent's populationSize
//        assertEquals(childPopulationSize,120);
//        assertEquals((childbestFitness >= bestFitness), (boolean) true);
//    }
//
//    public static Population createInitialPopulation(int populationSize) {
//        Population population = new Population();
//        Random random = new Random();
//        int coordinateRange = 10;
//        for (int i = 0; i < populationSize; i++) {
//            Chromosome base = new Chromosome(10, random);
//            for(int j = 0; j < base.getNumberOfPairs(); j++) {
//                int x = random.nextInt(coordinateRange);
//                int y = random.nextInt(coordinateRange);
//                base.addPairs(j, x, y);
//            }
//            population.addChromosome(base);
//        }
//        return population;
//    }
//}
