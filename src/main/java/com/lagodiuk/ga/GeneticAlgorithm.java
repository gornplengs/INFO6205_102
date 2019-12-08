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
package com.lagodiuk.ga;

import java.util.*;

public class GeneticAlgorithm {
	private Fitness fitnessFunc;
	private Population population;
	private IterationListener iterationListener;
	private Random random;
	private double SURVIVE_RATE = 0.5;
	//private int iteration;

	private class ChromosomesComparator implements Comparator<Chromosome> {
		private final Map<Chromosome, Long> cache = new WeakHashMap<>();

		@Override
		public int compare(Chromosome chr1, Chromosome chr2) {
			Long fit1 = this.fit(chr1);
			Long fit2 = this.fit(chr2);
			int ret = fit1.compareTo(fit2);
			return ret;
		}

		public Long fit(Chromosome chr) {
			Long fit = this.cache.get(chr);
			if (fit == null) {
				fit = GeneticAlgorithm.this.fitnessFunc.calculate(chr);
				this.cache.put(chr, fit);
			}
			return fit;
		};

		public void clearCache() {
			this.cache.clear();
		}
	}
	private final ChromosomesComparator chromosomesComparator = new ChromosomesComparator();;

	public GeneticAlgorithm(Population population, Fitness fitnessFunc, Random random) {
		this.population = population;
		this.fitnessFunc = fitnessFunc;
		this.random = random;
		//this.population.sortPopulationByFitness(this.chromosomesComparator);
	}

	public GeneticAlgorithm(Population population, Fitness fitnessFunc, double surviveRate, Random random) {
		this.population = population;
		this.fitnessFunc = fitnessFunc;
		this.SURVIVE_RATE = surviveRate;
		this.random = random;
		//this.population.sortPopulationByFitness(this.chromosomesComparator);
	}

	public void evolve() {
		int parentPopulationSize = this.population.getSize();

		if(parentPopulationSize > 2) {
			int newPopulationSize = (int) (parentPopulationSize * SURVIVE_RATE);
			Population newPopulation = new Population(newPopulationSize);


			//1.sort old population by fitness
			this.population.sortPopulationByFitness(this.chromosomesComparator);

			//2.add survive population to new population
			for (int i = 0; i < newPopulationSize; i++) {  //&& i < parentPopulationSize
				newPopulation.addChromosome(this.population.getChromosomeByIndex(i));
			}

			//3.mutate
			int range = (int) (newPopulationSize * 0.01);   //possibility to mutate : 1%
			for(int i = 0; i < range; i++) {
				int mutateIndex = random.nextInt(newPopulationSize);
				Chromosome chromosome = this.population.getChromosomeByIndex(mutateIndex);
				Chromosome mutated = chromosome.mutate();
				newPopulation.setChromosomeByIndex(mutateIndex, mutated);
			}

			//4.replace old population
			newPopulation.sortPopulationByFitness(this.chromosomesComparator);
			this.population = newPopulation;

//			return newPopulation;
		}
//		else {
//		Population wu = new Population();
//		return wu;
//	}

	}

	public void evolve(int count) {
		this.iterationListener = new IterationListener(count);
		for (int i = 0; i < count; i++) {
			if(this.population.getSize() <= 1) break;
			this.evolve();
			//this.iteration = i;
			iterationListener.update(this);
//			if(this.population.getSize() <= 1) {
//				break;
//			}
		}
		iterationListener.printResult();
	}

	public Population getNewPopulation() {
		return population;
	}
	public int getPopulation() {
		return this.population.getSize();
	}

	public ChromosomesComparator getChromosomesComparator() {
		return chromosomesComparator;
	}

	public Chromosome getBest() {
		this.population.sortPopulationByFitness(chromosomesComparator);
		return this.population.getChromosomeByIndex(0);
	}

	public IterationListener getIterationListener() {
		return iterationListener;
	}

	public Long fitness(Chromosome chromosome) {
		return this.chromosomesComparator.fit(chromosome);
	}

//	public void clearCache() {
//		this.chromosomesComparator.clearCache();
//	}
}
