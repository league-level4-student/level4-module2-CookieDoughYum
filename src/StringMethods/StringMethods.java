package StringMethods;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;

/*
Visit the JavaDocs for the String class to view everything you can do with a String.  


HINT:  Here are some String methods you might find useful 
contains
replace
trim
length
getBytes
endsWith
split 
indexOf
lastIndexOf
compareTo(IgnoreCase)
substring


Here are some Character methods you might find useful:
Character.toLowerCase(char c);
Character.isLetter(char c);
Character.isDigit(char c);
Character.getNumericValue(char c);
 */

public class StringMethods {

	// Given Strings s1 and s2, return the longer String
	public static String longerString(String s1, String s2) {
		if(s1.length()>s2.length()) {
			return s1;
		}
		return s2;
	}

	
	// if String s contains the word "underscores", change all of the spaces to underscores
	public static String formatSpaces(String s) {
		if(s.contains("underscores")) {
			s=s.replace(" ", "_");
		}
		return s;
	}

	
	// Return the name of the person whose LAST name would appear first if they were in alphabetical order
	// You cannot assume there are no extra spaces around the name, but you can
	// assume there is only one space between the first and last name
	public static String lineLeader(String s1, String s2, String s3) {
		//trim 
		s1=s1.trim();
		s2=s2.trim();
		s3=s3.trim();
		
		String OGs1=s1;
		String OGs2=s2;
		String OGs3=s3;
		//separate the last names 
		String[] strArray=s1.split(" ");
		//System.out.println(strArray);
		
		String[] strArray1=s2.split(" ");
		//System.out.println(strArray[1]);
		
		String[] strArray2=s3.split(" ");
		//System.out.println(strArray2.size());
		//use compareTo to figure out the order 
		if(strArray[1].compareTo(strArray1[1])<0 && strArray[1].compareTo(strArray2[1])<0) {
			return OGs1;
		}
		else if(strArray1[1].compareTo(strArray[1])<0 && strArray1[1].compareTo(strArray2[1])<0) {
			return OGs2;
		}
		//return FULL name of the first person 
		return OGs3;
	}
	
	
	// Return the sum of all numerical digits in the String
	public static int numeralSum(String s) {
		
//		int total = 0;
//		for(int i=0; i<s.length(); i++) {
//			if(Character.isDigit(s.charAt(i))) {
//				total += Character.getNumericValue(s.charAt(i));
//			}
//		}
//		
//		return total;
//		
		ArrayList<Character> s1=new ArrayList<Character>(s.length());//s.length doesn't work because it is just creating an array list with the size of s.length.
		ArrayList<Integer> s2=new ArrayList<Integer>(s1.size());
		for(int i=0; i<s.length(); i++) {
			s1.add(s.charAt(i));
		}
		for(int i=0; i<s1.size(); i++) {
			if(Character.isDigit(s1.get(i))) {
		    s2.add(Character.getNumericValue(s1.get(i)));
			}
		}
		int constantSum=0;
		for(int i=0; i<s2.size(); i++) {
			constantSum+=s2.get(i);
		}
		return constantSum;
	}
	
	
	// Return the number of times String substring appears in String s
	
	public static int substringCount(String s, String substring) {
		int times=0;
		for(int i=0; i<=s.length()-substring.length(); i++) {
		if(s.substring(i, i+substring.length()).equals(substring)) {
				times+=1;
		}
		}
		return times;
		
//		int total = 0;
//		int startIndex = 0;
//		while (s.indexOf(substring, startIndex) >= 0) {
//			total++;
//			startIndex = s.indexOf(substring, startIndex) + 1;
//		}
//		return total;
	}

	// Call Utitilities.encrypt to encrypt String s
	public static String encrypt(String s, char key) {
		return Utilities.encrypt(s.getBytes(), (byte) key);
	}

	// Call Utilities.decrypt to decrypt the cyphertext
	public static String decrypt(String s, char key) {
		return Utilities.decrypt(s, (byte)key);
	}


	// Return the number of words in String s that end with String substring
	// You can assume there are no punctuation marks between words
	public static int wordsEndsWithSubstring(String s, String substring) {
		String[] words=s.split(" ");
		int numWords=0;
		for(int i=0; i<words.length; i++) {
			if(words[i].length() >= substring.length()) {
			if(words[i].substring(words[i].length()-substring.length()).equals(substring)){
				numWords+=1;
			}
			}
		}
		return numWords;
	}
	

	// Given String s, return the number of characters between the first occurrence
	// of String substring and the final occurrence
	// You can assume that substring will appear at least twice\
	
	
	/*
	 
	 subaaasub
	 	
	 	substring length = 3 
	 	
	 	indexOf = 0 
	 	
	 	lastIndexOf = 6 
	 	
	 0	   6
	 */
	public static int distance(String s, String substring) {
		int betweenChar=0;
			betweenChar=(s.lastIndexOf(substring)-s.indexOf(substring))-substring.length();
		return betweenChar;
	}


	
	/*
	aba ba
	
	ab aba
	*/
	
	// Return true if String s is a palindrome
	// palindromes are words or phrases are read the same forward as backward.
	// HINT: ignore/remove all punctuation and spaces in the String
	public static boolean palindrome(String s) {
		
		String formatted =  "";

		for(int i=0; i<s.length(); i++) {
			if(Character.isLetter(s.charAt(i))) {
				formatted += s.charAt(i);
			}
		}
		formatted=formatted.toLowerCase();
		int t=0;
		String reversed="";
		//int characters = 0;
		for(int i=formatted.length()-1; i>=0; i--) {
			reversed+=formatted.charAt(i);
		}
		for(int i=0; i<formatted.length(); i++) {
				   if(formatted.charAt(i)==reversed.charAt(i)) {
					  t +=1;
				   }
			   }
		
		
		System.out.println(formatted + " " + reversed);
	if(t==formatted.length()) {
			return true;
		}
		else {
			return false;
		}
}
		
	}
	


class Utilities {
	// This basic encryption scheme is called single-byte xor. It takes a single
	// byte and uses exclusive-or on every character in the String.
	public static String encrypt(byte[] plaintext, byte key) {
		for (int i = 0; i < plaintext.length; i++) {
			plaintext[i] = (byte) (plaintext[i] ^ key);
		}
		return Base64.getEncoder().encodeToString(plaintext);
	}

	public static String decrypt(String cyphertext, byte key) {
		byte[] b = Base64.getDecoder().decode(cyphertext);
		for (int i = 0; i < b.length; i++) {
			b[i] = (byte) (b[i] ^ key);
		}
		return new String(b);
	}
}
