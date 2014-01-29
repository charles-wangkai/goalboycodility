package PowerFib;

// Omicron 2012
// https://codility.com/demo/results/demoVCS3B6-FM3/

public class Solution {
	static final int F0 = 0;
	static final int F1 = 1;
	static final int MODULO = 10000103;
	static final int PERIOD = computePeriod();

	public int solution(int N, int M) {
		return computeFibonacci(powMod(N, M, PERIOD));
	}

	static int computePeriod() {
		int fprevprev = F0;
		int fprev = F1;
		for (int period = 1;; period++) {
			int f = addMod(fprevprev, fprev);
			fprevprev = fprev;
			fprev = f;
			if (fprevprev == F0 && fprev == F1) {
				return period;
			}
		}
	}

	static int addMod(int a, int b) {
		return (a + b) % MODULO;
	}

	int multiplyMod(int a, int b, int modulo) {
		return (int) ((long) a * b % modulo);
	}

	int powMod(int N, int M, int modulo) {
		int result = 1;
		while (M != 0) {
			if (M % 2 != 0) {
				result = multiplyMod(result, N, modulo);
			}
			N = multiplyMod(N, N, modulo);
			M /= 2;
		}
		return result;
	}

	int computeFibonacci(int n) {
		if (n == 0) {
			return F0;
		}
		return multiply(new int[][] { { F1, F0 } },
				pow(new int[][] { { 1, 1 }, { 1, 0 } }, n - 1))[0][0];
	}

	int[][] pow(int[][] m, int p) {
		int size = m.length;
		int[][] result = new int[size][size];
		for (int i = 0; i < size; i++) {
			result[i][i] = 1;
		}
		while (p != 0) {
			if (p % 2 != 0) {
				result = multiply(result, m);
			}
			m = multiply(m, m);
			p /= 2;
		}
		return result;
	}

	int[][] multiply(int[][] m1, int[][] m2) {
		int r1 = m1.length;
		int c1 = m1[0].length;
		int c2 = m2[0].length;
		int[][] result = new int[r1][c2];
		for (int i = 0; i < r1; i++) {
			for (int j = 0; j < c2; j++) {
				for (int k = 0; k < c1; k++) {
					result[i][j] = addMod(result[i][j],
							multiplyMod(m1[i][k], m2[k][j], MODULO));
				}
			}
		}
		return result;
	}
}
