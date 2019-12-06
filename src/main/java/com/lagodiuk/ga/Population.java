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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Population {

	private static final int DEFAULT_NUMBER_OF_CHROMOSOMES = 1000;
	private List<Chromosome> chromosomes;
	private final Random random = new Random();

	public Population() {
		this.chromosomes = new ArrayList<>(DEFAULT_NUMBER_OF_CHROMOSOMES);
	}

	public Population(int chromosomeNum) {
		this.chromosomes = new ArrayList<>(chromosomeNum);
	}

	public void addChromosome(Chromosome chromosome) {
		this.chromosomes.add(chromosome);
	}

	public Chromosome getChromosomeByIndex(int index) {
		return this.chromosomes.get(index);
	}

	public void setChromosomeByIndex(int index, Chromosome chromosome) {
		this.chromosomes.set(index, chromosome);
	}

	public int getSize() {
		return this.chromosomes.size();
	}

	public void sortPopulationByFitness(Comparator<Chromosome> chromosomesComparator) {  //??
		Collections.shuffle(this.chromosomes);
		Collections.sort(this.chromosomes, chromosomesComparator);
	}

	/**
	 * shortening population till specific number
	 */
//	public void trim(int len) {
//		this.chromosomes = this.chromosomes.subList(0, len);
//	}

//	@Override
//	public Iterator<String> iterator() {
//		return this.chromosomes.iterator();
//	}

}
