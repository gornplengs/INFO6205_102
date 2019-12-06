package com.lagodiuk.ga;

import org.junit.Test;

import static org.junit.Assert.assertNotEquals;

public class MutationTest {

    @Test
    public void testMutate() {
        Chromosome c1 = new Chromosome(2);
        c1.addPairs(0,0,0);
        c1.addPairs(1,1,0);

        Chromosome c2 = new Chromosome(7);
        c2.addPairs(0,1,3);
        c2.addPairs(1,2,4);
        c2.addPairs(2,3,4);
        c2.addPairs(3,4,3);
        c2.addPairs(4,4,2);
        c2.addPairs(5,3,1);
        c2.addPairs(6,2,2);

        Chromosome c3 = c1.mutate();
        Chromosome c4 = c2.mutate();

        assertNotEquals(c1, c3);
        assertNotEquals(c2, c4);

    }
}
