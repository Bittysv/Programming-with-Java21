package example.coding.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StringOperations {

	public String reverse(String input) {
		char[] charsInput = input.toCharArray();
		int i = 0, j = charsInput.length - 1;
		while (i < j) {
			char temp = charsInput[i];
			charsInput[i] = charsInput[j];
			charsInput[j] = temp;
			i++;
			j--;
		}

		return new String(charsInput);

	}

	public static void main(String[] args) throws IOException{
		StringOperations obj = new StringOperations();
		System.out.println("Input: ");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String input = reader.readLine();
		System.out.println("Reverse is: " + obj.reverse(input));
	}

}
