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

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Chromosome implements Cloneable{
	private static final int DEFAULT_NUMBER_OF_COORDINATE_PAIRS = 10;
	private int[][] pairs = new int[DEFAULT_NUMBER_OF_COORDINATE_PAIRS][2];
	private final Random random = new Random();

	/**
	 * Returns clone of current chromosome, which is mutated a bit
	 */
	public Chromosome mutate() {
		Chromosome result = this.clone();
		// just select random element of vector
		// and increase or decrease it on small value
		int index = random.nextInt(this.pairs.length);
		int coordinate = random.nextInt(2);
		int mutationValue = random.nextInt(3) - random.nextInt(3);
		result.pairs[index][coordinate] += mutationValue;
		return result;
	}

	public void addPairs(int index, int x, int y) {
		pairs[index][0] = x;
		pairs[index][1] = y;
	}

	public int getNumberOfPairs() {
		return DEFAULT_NUMBER_OF_COORDINATE_PAIRS;
	}

	/**
	 * Returns list of siblings <br/>
	 * Siblings are actually new chromosomes, <br/>
	 * created using any of crossover strategy
	 */
//	public List<Demo.Pattern> crossover(Demo.Pattern other) {
//		Demo.Pattern thisClone = this.clone();
//		Demo.Pattern otherClone = other.clone();
//
//		// one point crossover
//		int index = random.nextInt(this.vector.length - 1);
//		for (int i = index; i < this.vector.length; i++) {
//			int tmp = thisClone.vector[i][i];
//			thisClone.vector[i][i] = otherClone.vector[i][i];
//			otherClone.vector[i][i] = tmp;
//		}
//
//		return Arrays.asList(thisClone, otherClone);
//	}

	@Override
	protected Chromosome clone() {
		Chromosome clone = new Chromosome();
		System.arraycopy(this.pairs, 0, clone.pairs, 0, this.pairs.length);
		return clone;
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < DEFAULT_NUMBER_OF_COORDINATE_PAIRS; i++) {
			sb.append(this.pairs[i][0]);   //x coordinate
			sb.append(" ");
			sb.append(this.pairs[i][1]);   //y coordinate
			if(i != DEFAULT_NUMBER_OF_COORDINATE_PAIRS-1)  sb.append(", ");
		}
		return sb.toString();
	}
}
