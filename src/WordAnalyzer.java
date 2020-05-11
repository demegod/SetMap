import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
//set is the mathmatical idea of set. Does not have duplicate entries. Hash set and Tree set. Tree set is slower compared to a Hash set.
//either way, we use 'set' as a interface
//a map is pairing one type of item with another type of item. The two things don't have to be the same data types. 
//Tree map and hash map, tree keeps in order and a hash does not.
public class WordAnalyzer {

	public static void main(String[] args) {

		ArrayList<String> words = new ArrayList<String>();

		// opening discussion: getting words from a file		
		try {
			//words = getWords("input.txt");
			words = getWords("book.txt");
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
			return;
		}
		
		// Task 1: print a list of unique words, sorted
		// - ensure to discuss reading the API docs
		Set<String> uniqueWords = new TreeSet<String>(words);
//		for (String word : words){
//			uniqueWords.add(word);
//		}
		for (String word : uniqueWords){
			//System.out.println(word);
		}
		
		// Task 2: print a count for each word
		// - ensure to discuss unexpected null pointer exceptions
		Map<String, Integer> wordCounts = new TreeMap<String, Integer>();
		for (String word : words){
			try{
				int i = wordCounts.get(word);
				wordCounts.put(word, i+1);
			}
			catch (NullPointerException n){
				wordCounts.put(word, 1);
			}
		}
		for (String k : wordCounts.keySet()){
			//System.out.printf("%-20s %4d\n", k, wordCounts.get(k));
		}
		
		// Task 3: print a list of words for each count, along with size of the list
		// - ensure to discuss efficiency of parameter passing
		Map<Integer, TreeSet<String>> countLists = new TreeMap<Integer, TreeSet<String>>();
		
		//go through each unique word from the mapping above
		for (String k : wordCounts.keySet()){
			
			//get the count of that word
			int count = wordCounts.get(k);
			
			//get the list associated with that count
			TreeSet<String> t = countLists.get(count);
			
			//if there is not a list yet, make one
			//and associate with that count
			if (t == null){
				t = new TreeSet<String>();
				countLists.put(count, t);
			}
			
			//add the word to the associated list
			t.add(k);
		}
		
		//prints out the map
		for (Integer count : countLists.keySet()){
			//System.out.println(count + " " + countLists.get(count));
		}
		
		System.out.println("\n--DONE--");
	}
	

	public static ArrayList<String> getWords(String fileName) throws IOException {
		ArrayList<String> words = new ArrayList<String>();

		Scanner in = new Scanner(new File(fileName));
		while (in.hasNext()) {
			words.addAll(convert(in.next()));
		}
		in.close();

		return words;
	}


	// discussion: why this returns a list instead of a String	
	public static ArrayList<String> convert(String s) {
		ArrayList<String> words = new ArrayList<String>();
		String[] possibleWords = s.split("-");
		for (String p : possibleWords) {
			String pConverted = "";
			for (char c : p.toLowerCase().toCharArray()) {
				if (Character.isLowerCase(c)) {
					pConverted += c;
				}
			}
			if (pConverted.length() > 0) {
				words.add(pConverted);
			}
		}

		return words;
	}

}
