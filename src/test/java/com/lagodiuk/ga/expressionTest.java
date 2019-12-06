package com.lagodiuk.ga;

import org.junit.Test;

import static org.junit.Assert.*;

public class expressionTest {

    @Test
    public void expression( ) {
        double text = 100.00;
        IterationListener test= new IterationListener();
        double answer= test.expression(text);

        assertEquals(0.1, answer,1e-15);


        double text2 = 50.00;
        double answer2= test.expression(text2);

        assertEquals(0.05, answer2,1e-15);


        double text3 = 20.00;
        double answer3= test.expression(text3);

        assertEquals(0.02, answer3,1e-15);
    }
}