package example.coding.practice;


public class ValidSplitCount {
	public int solution(String S) {
	       int len = S.length(); // get length of input string

	       // Arrays to store cumulative counts of 'x' and 'y' from left 
	       int [] leftX = new int[len];
	       int [] leftY = new int[len];
	       
	       // Arrays to store cumulative counts of 'x' and 'y' from right 
	       int [] rightX = new int[len];
	       int [] rightY = new int[len];
	       
	       // Count occurrences of 'x' and 'y' from left-side
	       int xCount = 0, yCount = 0;
	       for(int i = 0; i<len; i++) {
	           if(S.charAt(i) == 'x') xCount++;
	           if(S.charAt(i) == 'y') yCount++;

	           leftX[i] = xCount;
	           leftY[i] = yCount;
	       }

	       // Count occurrences of 'x' and 'y' from right-side
	       xCount = 0;
	       yCount = 0;
	       for(int i = len -1; i>= 0; i--) {
	           if(S.charAt(i) == 'x') xCount++;
	           if(S.charAt(i) == 'y') yCount++;

	           rightX[i] = xCount;
	           rightY[i] = yCount;
	       }

	       // Variable to store possible count of valid splits
	       int possSplit = 0;

	       // Check  each possible valid splits between i and i+1
	       for( int i = 0; i<len -1; i++) {
	           boolean leftSplit = leftX[i] == leftY[i]; // To check if the left part has equal 'x' and 'y'
	           boolean rightSplit = rightX[i+1] == rightY[i+1]; // To check if the right part has equal 'x' and 'y'

	            if(leftSplit || rightSplit) {
	                possSplit++; // Count the split if either part is valid
	            }
	       }
	       return possSplit;
	    }
	
	public static void main(String[] args) {
		ValidSplitCount s = new ValidSplitCount();
		int c = s.solution("apple"); //0 x and 0 y
		
		System.out.println("answer: \t"+c);
	}

}
