package FloodedIsland;

// Nitrogenium 2013
// http://codility.com/cert/view/cert48QB3A-3JAX387ADYNNSPS6

import java.util.ArrayList;

public class Solution {
	@SuppressWarnings("unchecked")
	public int[] solution(int[] A, int[] B) {
		int maxA = getMax(A);
		int maxB = getMax(B);
		ArrayList<Integer>[] indices = new ArrayList[Math.max(maxA, maxB) + 1];
		for (int i = 0; i < indices.length; i++) {
			indices[i] = new ArrayList<Integer>();
		}

		for (int i = 0; i < A.length; i++) {
			indices[A[i]].add(i);
		}

		boolean[] underWaters = new boolean[A.length];
		int[] islandNums = new int[maxB + 1];
		int currentIslandNum = 1;
		for (int i = 0; i < islandNums.length; i++) {
			ArrayList<Integer> currentIndices = indices[i];
			int rangeBegin = -1;
			int rangeEnd = -1;
			for (int j = 0; j <= currentIndices.size(); j++) {
				int index = -1;
				if (j < currentIndices.size()) {
					index = currentIndices.get(j);
				}
				if (j < currentIndices.size() && rangeEnd >= 0
						&& index == rangeEnd + 1) {
					rangeEnd++;
				} else {
					if (rangeBegin >= 0) {
						int delta = -1;
						if (rangeBegin > 0 && !underWaters[rangeBegin - 1]) {
							delta++;
						}
						if (rangeEnd < A.length - 1
								&& !underWaters[rangeEnd + 1]) {
							delta++;
						}
						currentIslandNum += delta;

						for (int k = rangeBegin; k <= rangeEnd; k++) {
							underWaters[k] = true;
						}
					}

					rangeBegin = index;
					rangeEnd = index;
				}
			}
			islandNums[i] = currentIslandNum;
		}

		int[] results = new int[B.length];
		for (int i = 0; i < results.length; i++) {
			results[i] = islandNums[B[i]];
		}

		return results;
	}

	int getMax(int[] a) {
		int max = Integer.MIN_VALUE;
		for (int num : a) {
			max = Math.max(max, num);
		}
		return max;
	}
}
