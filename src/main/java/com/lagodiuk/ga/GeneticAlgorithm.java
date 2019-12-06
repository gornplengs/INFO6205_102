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

	// private static final int ALL_PARENTAL_CHROMOSOMES = Integer.MAX_VALUE;
	private Double SURVIVE_RATE = 0.5;

	private final Fitness fitnessFunc;
	private Population population;
	private boolean terminate = false;
	private int iteration = 0;
	// listeners of genetic algorithm iterations (handle callback afterwards)
	// private final List<IterartionListener<C, T>> iterationListeners = new LinkedList<IterartionListener<C, T>>();
	// number of parental chromosomes, which survive (and move to new population)


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
	private final ChromosomesComparator chromosomesComparator;

	public GeneticAlgorithm(Population population, Fitness fitnessFunc) {
		this.population = population;
		this.fitnessFunc = fitnessFunc;
		this.chromosomesComparator = new ChromosomesComparator();
		//this.population.sortPopulationByFitness(this.chromosomesComparator);
	}

	public void evolve() {
		int parentPopulationSize = this.population.getSize();
		if(parentPopulationSize > 1){
			Population newPopulation = new Population();
			int newPopulationSize = (int) (parentPopulationSize*this.SURVIVE_RATE);

			//1.sort old population by fitness
			this.population.sortPopulationByFitness(this.chromosomesComparator);
			//2.add survive population to new population
			for (int i = 0; i < newPopulationSize; i++) {  //&& i < parentPopulationSize
				newPopulation.addChromosome(this.population.getChromosomeByIndex(i));
			}

			//3.mutate
			Random random = new Random();
			int range = random.nextInt(Math.max(newPopulationSize, 1));
			for(int i = 0; i < range; i++) {
				int mutateIndex = random.nextInt(parentPopulationSize);
				Chromosome chromosome = this.population.getChromosomeByIndex(mutateIndex);
				Chromosome mutated = chromosome.mutate();
				newPopulation.setChromosomeByIndex(mutateIndex, mutated);
			}

			//4.replace old population
			this.population = newPopulation;
		}

	}

	public void evolve(int count) {
		this.terminate = false;

		for (int i = 0; i < count; i++) {
			if (this.terminate) {
				break;
			}
			this.evolve();
			this.iteration = i;
			for (IterartionListener<C, T> l : this.iterationListeners) {
				l.update(this);
			}
		}
	}

	public int getIteration() {
		return this.iteration;
	}

	public void terminate() {
		this.terminate = true;
	}

//	public Population<C> getPopulation() {
//		return this.population;
//	}

	public Chromosome getBest() {
		return this.population.getChromosomeByIndex(0);
	}

	public Chromosome getWorst() {
		return this.population.getChromosomeByIndex(this.population.getSize() - 1);
	}

	public int getParentChromosomesSurviveCount() {
		return this.parentChromosomesSurviveCount;
	}

	public void addIterationListener(IterartionListener<C, T> listener) {
		this.iterationListeners.add(listener);
	}

	public void removeIterationListener(IterartionListener<C, T> listener) {
		this.iterationListeners.remove(listener);
	}

	public Long fitness(Chromosome chromosome) {
		return this.chromosomesComparator.fit(chromosome);
	}

	public void clearCache() {
		this.chromosomesComparator.clearCache();
	}
}
