package leetcode;

/**
 * Author: 		Long Vu, longvu.cs@outlook.com
 * Date: 		Jul 15, 2016
 * Problem: 	Q6_ZigzagConversion.java
 * Source:		https://leetcode.com/problems/zigzag-conversion/
 *
 * Description:	
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility) 
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * And then read line by line: "PAHNAPLSIIGYIR"
 * Write the code that will take a string and make this conversion given a number of rows: 
 * string convert(string text, int nRows);
 * convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR". 
 * 
 * Solution:	1. Straight forward: use 1 pointer to move back and fort between the lines.
 * 				2. Other: iterate through input string m (inputed rows) times, we can calculate next char by using this
 * 				formular: next = curr + (nextPeak-curr)*2
 * 						
 * Complexity:	O(n)
 * Notes:
 *					
 * Follow up:	(H) Wildcard Matching   (M) Encode and Decode Strings   (E) Reverse Vowels of a String  
 */
public class Q6_ZigzagConversion {
	public String convert(String s, int nRows) {
		StringBuilder arr[] = new StringBuilder[nRows];
		int line = 0;
		boolean isUp = true;

		for (int i = 0; i < s.length(); i++) {
			if (arr[line] == null) {
				arr[line] = new StringBuilder("");
			}
			arr[line].append(s.charAt(i));
			// reach top
			if (line == nRows - 1)
				isUp = false;
			// reach bottom
			else if (line == 0)
				isUp = true;

			if (isUp && line != nRows - 1)
				line++;
			if (!isUp && line != 0)
				line--;
		}
		StringBuilder ret = new StringBuilder("");
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] != null)
				ret.append(arr[i]);
		}
		return ret.toString().trim();
	}

	public static String convert2(String s, int nRows) {
		if (nRows <= 1)
			return s;
		
		int period = (nRows << 1) - 2;
		char[] array = s.toCharArray();

		StringBuilder result = new StringBuilder();
		for (int i = 0; i < nRows; i++) {
			int j = i;
			int index = 0, temp = 0;

			while (j < array.length) {
				result = result.append(array[j]);
				if (i != 0 && i != nRows - 1) {
					temp = (j % period);
					index = ((temp < nRows) ? (nRows - 1 - temp) << 1 : (period - temp) << 1);
					j += index;
				} else {
					j += period;
				}
			}
		}

		return result.toString();
	}
	
	public static String convert3(String s, int nRows) {
		if (nRows <= 1)
			return s;
		
		int len = s.length();
		int nums = (len - 1)/nRows + 3;//ceil the result of len/rows
		char[] array = s.toCharArray();
		StringBuilder result = new StringBuilder();
		
		int[] peaks = new int[nums];
		for(int i = 0; i < nums; i++){
			peaks[i] = nRows*i-i;
		}
		
		for(int k = 0; k < nRows; k++){
			int j = k;
			int i = 1;
			while(j < len && i < nums){
				result.append(array[j]);
				if(peaks[i] - j > 0)
					j = j + (peaks[i]-j)*2;
				else {
					j = j + (peaks[i+1]-j)*2;
				}
				i++;
			}
		}
		
		return result.toString();
	}
	
	public static void main(String[] agr){
		System.out.println(convert2("abcdefghi",5));
		System.out.println(convert3("abcdefghi",5));
	}
}
