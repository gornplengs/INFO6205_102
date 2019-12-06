package com.lagodiuk.ga;

import org.junit.Test;

import static org.junit.Assert.*;


public class ChromosomeTest {
    @Test
    public void testMutate() {
        Chromosome c1 = new Chromosome(2);
        //System.out.println(c1);
        Chromosome c2=c1.mutate();
       // System.out.println(c2);
        boolean answer = (c1==c2);
        System.out.println(c1);
       assertEquals(false, answer);

    }
}
