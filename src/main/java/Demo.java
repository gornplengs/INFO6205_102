/*******************************************************************************
 * Copyright 2012 Yuriy Lagodiuk
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.lagodiuk.ga.Chromosome;
import com.lagodiuk.ga.Fitness;
import com.lagodiuk.ga.GeneticAlgorithm;
import com.lagodiuk.ga.IterartionListener;
import com.lagodiuk.ga.Population;
import edu.neu.coe.info6205.life.base.Game;

public class Demo {

	public static void main(String[] args) {
		Population population = createInitialPopulation(5);
		//System.out.println("!!!!!!!!!!!! " + population.);
		Fitness fitnessFunc = new Fitness();
		GeneticAlgorithm ga = new GeneticAlgorithm(population, fitnessFunc);
		addListener(ga);
		//ga.evolve(5);
	}

	/**
	 * The simplest strategy for creating initial population <br/>
	 * in real life it could be more complex
	 */
	private static Population createInitialPopulation(int populationSize) {
		Population population = new Population();
		Random random = new Random();
		int coordinateRange = 10;
		for (int i = 0; i < populationSize; i++) {
			Chromosome base = new Chromosome();
			for(int j = 0; j < base.getNumberOfPairs(); j++) {
				int x = random.nextInt(coordinateRange);
				int y = random.nextInt(coordinateRange);
				base.addPairs(j, x, y);
			}
			population.addChromosome(base);
		}
		return population;
	}

	/**
	 * After each iteration Genetic algorithm notifies listener
	 */
	private static void addListener(GeneticAlgorithm<Chromosome, Long> ga) {
		// just for pretty print
		System.out.println(String.format("%s\t%s\t%s", "iter", "generation", "chromosome"));

		// Lets add listener, which prints best chromosome after each iteration
		ga.addIterationListener(new IterartionListener<Chromosome, Long>() {
			private final double threshold = 1e-5;

			@Override
			public void update(GeneticAlgorithm<Chromosome, Long> ga) {
				Chromosome best = ga.getBest();
				double bestFit = ga.fitness(best);
				int iteration = ga.getIteration();

				// Listener prints best achieved solution
				System.out.println(String.format("%s\t%s\t%s", iteration, bestFit, best));
				// If fitness is satisfying - we can stop Genetic algorithm
				if (bestFit < this.threshold)  ga.terminate();

			}
		}
	}
}




