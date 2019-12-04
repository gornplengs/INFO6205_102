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
		Population<Pattern> population = createInitialPopulation(1000);
		Fitness<Pattern, Long> fitness = new PatternFitness();
		GeneticAlgorithm<Pattern, Long> ga = new GeneticAlgorithm<>(population, fitness);
		addListener(ga);
		ga.evolve(500);
	}

	/**
	 * The simplest strategy for creating initial population <br/>
	 * in real life it could be more complex
	 */
	private static Population<Pattern> createInitialPopulation(int populationSize) {
		Population<Pattern> population = new Population<>();
		Pattern base = new Pattern();
		Random random = new Random();
		int coordinateRange = 20;
		for (int i = 0; i < populationSize; i++) {
			for(int j = 0; j < base.len; j++) {
				base.vector[j][0] = random.nextInt(coordinateRange);
				base.vector[j][1] = random.nextInt(coordinateRange);
			}
			population.addChromosome(base);
		}
		return population;
	}

	/**
	 * After each iteration Genetic algorithm notifies listener
	 */
	private static void addListener(GeneticAlgorithm<Pattern, Long> ga) {
		// just for pretty print
		System.out.println(String.format("%s\t%s\t%s", "iter", "fit", "chromosome"));

		// Lets add listener, which prints best chromosome after each iteration
		ga.addIterationListener(new IterartionListener<Pattern, Long>() {

			private final double threshold = 1e-5;

			@Override
			public void update(GeneticAlgorithm<Pattern, Long> ga) {

				Pattern best = ga.getBest();
				double bestFit = ga.fitness(best);
				int iteration = ga.getIteration();

				// Listener prints best achieved solution
				System.out.println(String.format("%s\t%s\t%s", iteration, bestFit, best));

				// If fitness is satisfying - we can stop Genetic algorithm
				if (bestFit < this.threshold) {
					ga.terminate();
				}
			}
		});
	}

	/**
	 * Chromosome, which represents pattern
	 */
	public static class Pattern implements Chromosome<Pattern>, Cloneable {

		private static final Random random = new Random();
		int len = 9;
		private final int[][] vector = new int[len][2];

		/**
		 * Returns clone of current chromosome, which is mutated a bit
		 */
		@Override
		public Pattern mutate() {
			Pattern result = this.clone();

			// just select random element of vector
			// and increase or decrease it on small value
			int index = random.nextInt(this.vector.length);
			int coordinate = random.nextInt(2);
			int mutationValue = random.nextInt(3) - random.nextInt(3);
			result.vector[index][coordinate] += mutationValue;

			return result;
		}

		/**
		 * Returns list of siblings <br/>
		 * Siblings are actually new chromosomes, <br/>
		 * created using any of crossover strategy
		 */
		@Override
		public List<Pattern> crossover(Pattern other) {
			Pattern thisClone = this.clone();
			Pattern otherClone = other.clone();

			// one point crossover
			int index = random.nextInt(this.vector.length - 1);
			for (int i = index; i < this.vector.length; i++) {
				int tmp = thisClone.vector[i][i];
				thisClone.vector[i][i] = otherClone.vector[i][i];
				otherClone.vector[i][i] = tmp;
			}

			return Arrays.asList(thisClone, otherClone);
		}

		@Override
		protected Pattern clone() {
			Pattern clone = new Pattern();
			System.arraycopy(this.vector, 0, clone.vector, 0, this.vector.length);
			return clone;
		}

		public int[][] getVector() {
			return this.vector;
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			for(int i = 0; i < len; i++) {
				sb.append(this.vector[i][0]);   //x coordinate
				sb.append(" ");
				sb.append(this.vector[i][1]);   //y coordinate
				if(i != len-1)  sb.append(", ");
			}
			return sb.toString();
		}
	}

	/**
	 * Fitness function, which calculates difference between chromosomes vector
	 * and target vector
	 */
	public static class PatternFitness implements Fitness<Pattern, Long> {

//		private final int[] target =  new int[1000];

		@Override
		public Long calculate(Pattern chromosome) {
//			for(int i=0;i<target.length/2;i=i+2){
//                target[i]=1;
//                target[i+1]=0;
//			}
//			double delta = 0;
//			int[] v = chromosome.getVector();
//			for (int i = 0; i < 1000; i++) {
//				delta += this.sqr(v[i] - this.target[i]);
//			}
//			return delta;
			Long generation = Game.run(chromosome.toString());
			return generation;
		}

//		private double sqr(double x) {
//			return x * x;
//		}
	}
}
