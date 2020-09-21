public class LetterInventory {

	// Variable to store the frequencies of each letter of a passed in String with a length of 26
	// Every index will associate with a letter in the alphabet
	// For example, letterTrack[9] will hold the frequency of 'j' because 'j' is the 10th letter in the alphabet
	private int[] letterTrack;
	// variable to represent the total number of letters of a passed in String
	private int numChar;
	
	/* pre: s != null
	 * Constructor stores amount of English letters into an array of length 26 for each letter
     * @param s contains the letters to form the letter inventory. */
	public LetterInventory(String s) {
		// Check precondition, String must not be a null object
		if(s == null) {
			throw new IllegalArgumentException("String is null");
		}
		letterTrack = new int[26];
		numChar = 0;
		s = s.toLowerCase(); // Avoid any upper-case letters to more easily count the frequencies
		
		// Traverse through the String
		for(int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			// Check if the character at index i is an English letter
			if('a' <= ch && ch <= 'z') {
				// Increment the frequency of the letter of the array container
				// Subtracting 97 from the character will result in a value of 0 - 25
				// because the lexicographical value of 'a' == 97 and 'z' == 122
				letterTrack[ch - 97]++;
				numChar++;
			}
		}
	}
	
	/* pre: c is an English letter, lower-case or upper-case
	 * Return the frequency of the letter */
	public int get(char c) {
		// If the character is upper-case, turn it into a lower-case version of itself
		c = ("" + c).toLowerCase().charAt(0);
		
		// Check precondition, the character is an upper-case or lower-case version of an English letter
		if(c < 'a' || c > 'z') {
			throw new IllegalArgumentException("Character is not an English letter");
		}
		return letterTrack[c - 97];
	}

	// Return the number of total characters in the letter inventory
	public int size() {
		return numChar;
	}
	
	// Return true if there is no characters in the letter inventory
	public boolean isEmpty() {
		return numChar == 0;
	}
	
	
	/* pre: obj != null
	 * Return a new LetterInventory object of the combination String representation 
	 * of the calling LetterInventory and the passed in LetterInventory */
	public LetterInventory add(LetterInventory obj) {
		// Check precondition, obj may not be null
		if(obj == null) {
			throw new IllegalArgumentException("Passed in object is null");
		}
		return new LetterInventory(toString() + obj.toString());
	}
	
	/* pre: obj != null
	 * Return a new LetterInventory object of the combination String representation 
	 * of the calling LetterInventory and the passed in LetterInventory */
	public LetterInventory subtract(LetterInventory obj) {
		// Check precondition, obj may not be null
		if(obj == null) {
			throw new IllegalArgumentException("Passed in object is null");
		}
		// Automatically return null if the size of the passed in object is larger
		if(size() - obj.size() < 0)
			return null;
		
		// We know the size of both is equal or the calling object is larger
		String sub = "";
		
		// Traverse through the container array of the calling object
		for(int i = 0; i < letterTrack.length; i++) {
			// Get the frequency number difference of a letter at index i
			int count = letterTrack[i] - obj.letterTrack[i];
			// Automatically return null if the calling object has more of one letter
			if(count < 0) {
				return null;
			} 
			// Concatenate the appropriate amount of letters to string based on difference
			for(int j = 0; j < count; j++) {
				sub += (char)(i + 97);
			}
		}
		return new LetterInventory(sub);//result;
	}
	
	/* pre: obj != null
	 * Return true if the calling object and the passed in object have the same frequency
	 * for all letters */
	public boolean equals(Object obj) {
		// Check precondition, obj must not be null
		if(obj == null) {
			throw new IllegalArgumentException("Object obj is null");
		}
		// Return false if the object is a LetterInventory object
		if(!(obj instanceof LetterInventory))
			return false;
		
		// Traverse through the array container of the letter frequencies
		for(int i = 0; i < letterTrack.length; i++)
			// Return false the second a difference is found in any letter's frequency
			if(letterTrack[i] != ((LetterInventory)obj).letterTrack[i])
				return false;
		return true; // No differences were found
	}
	
	// Return a String form of the LetterInventory object listed in alphabetical order
	public String toString() {
		String result = "";
		// Traverse through the array container of this LetterInventory
		for(int i = 0; i < letterTrack.length; i++) {
			// Add the letter at index i to result the number of times the letter is present
			for(int j = 0; j < letterTrack[i]; j++) {
				result += (char)(i + 97);
			}
		}
		return result;
	}
}
