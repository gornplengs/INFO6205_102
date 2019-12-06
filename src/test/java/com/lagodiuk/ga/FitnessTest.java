package com.lagodiuk.ga;

import edu.neu.coe.info6205.life.base.Game;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FitnessTest {

    @Test
    public void testCalculate() {
        Chromosome c1 = new Chromosome(2);
        c1.addPairs(0,0,0);
        c1.addPairs(1,1,0);

        Chromosome c2 = new Chromosome();
        c2.addPairs(0,1,3);
        c2.addPairs(1,2,4);
        c2.addPairs(2,3,4);
        c2.addPairs(3,4,3);
        c2.addPairs(4,4,2);
        c2.addPairs(5,3,1);
        c2.addPairs(6,2,2);

        Fitness fitness = new Fitness();
        int g1 = Math.toIntExact(fitness.calculate(c1));
        int g2 = Math.toIntExact(fitness.calculate(c2));

        assertEquals(1, g1);
        assertEquals(1000, g2);
    }
}
