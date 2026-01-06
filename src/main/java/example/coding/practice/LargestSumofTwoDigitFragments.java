package example.coding.practice;

public class LargestSumofTwoDigitFragments {

	// Method to return largest sum of two two-digit fragments of input string
	public int solution(String S) {
		int largestSum = 0;
		int len = S.length();

		// Iterate over all possible positions of the first two-digit fragments
		for (int i = 0; i < len - 1; i++) {
			int first = Integer.parseInt(S.substring(i, i + 2));

			// Iterate over all possible positions of the second two-digit fragments
			for (int j = 0; j < len - 1; j++) {

				// Ensure that the two two-digits numbers are non-overlapping
				if (j >= i + 2 || i >= j + 2) {
					int second = Integer.parseInt(S.substring(j, j + 2));
					int sum = first + second;

					// update largestSum if new sum is greater
					if (sum > largestSum) {
						largestSum = sum;
					}
				}
			}
		}
		return largestSum;
	}

	public static void main(String[] args) {
		LargestSumofTwoDigitFragments mainObj = new LargestSumofTwoDigitFragments();
		int c = mainObj.solution("00101"); // 0 x and 0 y
		System.out.println("answer: %d\t" + c);
	}

}
