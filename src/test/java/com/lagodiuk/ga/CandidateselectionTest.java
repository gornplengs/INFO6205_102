package com.lagodiuk.ga;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class CandidateselectionTest {

    @Test
    public void evolve() {
        Random random = new Random();
        Population population = new Population();
        Fitness fitnessFunc = new Fitness();
        Population parent = population.createInitialPopulation(240);
        Chromosome parentbest = parent.getChromosomeByIndex(0);
        int parentPopulationSize = parent.getSize();
        GeneticAlgorithm ga = new GeneticAlgorithm(parent, fitnessFunc, random);
        Population child = ga.evolve();


        Chromosome childbest = child.getChromosomeByIndex(0);
        Long bestFitness = ga.fitness(parentbest);



        Long childbestFitness = ga.fitness(childbest);


        for(int i=0;i<child.getSize();i++){
            childbestFitness = ga.fitness(childbest);
            if(ga.fitness(child.getChromosomeByIndex(i))>childbestFitness){
                childbestFitness =  ga.fitness(child.getChromosomeByIndex(i));
            }
        }

       int childPopulationSize = child.getSize();
      

        // children's populationSize is half of parent's populationSize
        assertEquals(childPopulationSize,120);
        assertEquals((childbestFitness>=bestFitness), (boolean) true);
    }
}