package AbsDistinct;

// http://codility.com/demo/results/demoPFAZPB-76F/

import java.util.HashSet;

public class Solution {
	public int solution(int[] A) {
		HashSet<Integer> absoluteValues = new HashSet<Integer>();
		for (int elem : A) {
			absoluteValues.add(Math.abs(elem));
		}
		return absoluteValues.size();
	}
}
