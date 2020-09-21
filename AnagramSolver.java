import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

public class AnagramSolver {
	
	// Map that will hold the different words and their letter inventory
	private TreeMap<String, LetterInventory> freqList;

    /*
     * pre: list != null
     * @param list Contains the words to form anagrams from.
     */
    public AnagramSolver(Set<String> list) {
    	if (list == null)
            throw new IllegalArgumentException("Failed precondition");
    	
    	freqList = new TreeMap<String, LetterInventory>();
    	for(String x: list) {
    		// We are under the assumption that there are no repeats in the Set list
    		// No need for checking if a key exists
    		freqList.put(x, new LetterInventory(x));
    	}
    }

    /*
     * pre: maxWords >= 0, s != null, s contains at least one 
     * English letter.
     * Return a list of anagrams that can be formed from s with
     * no more than maxWords, unless maxWords is 0 in which case
     * there is no limit on the number of words in the anagram
     */
    public List<List<String>> getAnagrams(String s, int maxWords) {
    	// Check preconditions, a proper string has been passed with characters
    	// maxWords does not exceed the number possible characters
        if(s == null || s.length() == 0 || maxWords < 0) {
        	throw new IllegalArgumentException("Failed preconditions");
        }
    	LetterInventory strInv = new LetterInventory(s);
    	
    	// Check precondition, the passed in String has letters
    	if(strInv.size() == 0) {
    		throw new IllegalArgumentException("Word does not contain an English letter.");
    	}
    	
    	// If maxWords is 0, then change it to the size since the longest anagram a word
    	// or phrase can have consists of 1 letter words with maxWords members 
    	maxWords = maxWords == 0? strInv.size(): maxWords;
    	
    	// Create an List of Lists that will each represent a different anagram
    	ArrayList<List<String>> result = new ArrayList<List<String>>();
    	// An List that will be a place holder for potential anagrams
    	ArrayList<String> ana = new ArrayList<String>();
    	// An List that will hold potential words that can be in anagrams of s
    	ArrayList<String> dict = new ArrayList<String>();
    	for(String x: freqList.keySet()) {
    		if(strInv.subtract(freqList.get(x)) != null)
    			dict.add(x);
    	}
    	
    	// Start recursive call
    	anagramSet(result, strInv, dict, ana, maxWords);
    	// Sort list based on anagram size and context
    	result.sort(new AnagramComparator());
        return result;
    }
    
    // Helper method that handles the recursion
    // Add valid anagrams to the list containing anagrams
    private void anagramSet(ArrayList<List<String>> result, LetterInventory let, ArrayList<String> dict, ArrayList<String> ana, int maxWords) {
    	// Base Case: Either we have no letters left to make anagrams out of or
    	// We have the maximum number of words in an anagram and the we have run out of letters
    	if(let.isEmpty() || (maxWords == ana.size() && let.isEmpty())) {
    		// Get copy of list to avoid adding the same references
        	ArrayList<String> copy = (ArrayList<String>)createCopy(ana);
        	result.add(copy);
    	} else {
    		int index = 0;
    		// Traverse through the current dictionary we have
	    	for(String x: dict) {
	    		// Get the letter inventory we would have if we made the current word from the dictionary
	    		LetterInventory temp = let.subtract(freqList.get(x));
	    		// If the current word does no go over our letter inventory limit
	    		if(temp != null) {
	    			ana.add(x); // Add the word to the anagram 
	    			// Create a new dictionary without the word we have
	    			ArrayList<String> newDict = createDictionary(temp, dict, maxWords, ana, index);
	    			// Add more words to our currently updated anagram to check if a valid anagram is possible
	    			anagramSet(result, temp, newDict, ana, maxWords);
	    			ana.remove(x); // Remove the word to try other possible anagrams
	    		}	
	    		index++;
	    	}
	    	
    	}	
    }
	
    // Returns an ArrayList of a modified dictionary 
	private ArrayList<String> createDictionary(LetterInventory let, ArrayList<String> dict, int maxWords, 
			ArrayList<String> ana, int index) {
		ArrayList<String> newDict = new ArrayList<String>();
		// Go through dictionary if we don't meet the base case for recursive method 
		// If we do, then no purpose of creating new dictionary 
		if(ana.size() != maxWords && !let.isEmpty()) {
			
			// Traverse through current dictionary starting from the last word in the anagram
			for(int i = index; i < dict.size(); i++) {
				// If the string doesn't go over our letter inventory limit, then copy over to the new dictionary
				LetterInventory curLet = freqList.get(dict.get(i));
				if(let.subtract(curLet) != null) {
					newDict.add(dict.get(i));
				}
			}
		}
		return newDict;
	}
	
	// Method to return a hard copy of a valid anagram
	// @param ana is an anagram that meets requirements
	private List<String> createCopy(ArrayList<String> ana){
		List<String> copy =  new ArrayList<String>(); 
		// Traverse through ana and copy the words over to the copy
		for(String string : ana) {
			copy.add(string);
		}
		return copy;
	}

	private class AnagramComparator implements Comparator<List<String>> {
		// Method that helps in comparing anagrams based on size and context
    	public int compare(List<String> a1, List<String> a2) {
    		// Immediately return a 1 if a1 is larger, a -1 if a1 is shorter
            if(a1.size() != a2.size())
            	return a1.size() > a2.size()? 1: -1;
            
            // The anagrams have the same number of words
            int index = 0;
            // Traverse the anagrams
            while(index < a1.size()) {
            	// Get an int of the relationship between the words at position index
            	int temp = a1.get(index).compareTo(a2.get(index));
            	// Enter if-loop the second different words are found
            	if(temp != 0)
            		// Return 1 if the word from the a1 is lexicographically larger,
            		// otherwise, return a -1
            		return temp > 0? 1: -1;
            	index++;
            }
    		return 0; // No differences were found, both anagrams are the same
        }
    }
}
