//imports
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Color;

/**
 * Various recursive methods to be implemented.
 * Given shell file for CS314 assignment.
 */
public class Recursive {
	
	// Int array used for methods that search above, below, left, and right of a cell
	// In each inner array, the first element represents the row change and the second represent the column change
	// Methods such as canFlowOffMap and canEscapeMaze
	final static int[][] ADJ_CELLS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

   /**
    * Problem 1: convert a base 10 int to binary recursively.
    *   <br>pre: n != Integer.MIN_VALUE<br>
    *   <br>post: Returns a String that represents N in binary.
    *   All chars in returned String are '1's or '0's. 
    *   Most significant digit is at position 0
    *   @param n the base 10 int to convert to base 2
    *   @return a String that is a binary representation of the parameter n
    */
   public static String getBinary(int n) {
       if (n == Integer.MIN_VALUE) {
           throw new IllegalArgumentException("Failed precondition: getBinary. "
                           + "n cannot equal Integer.MIN_VALUE. n: " + n);
       }
       // Base Case: the parameter is an integer -1 <= n <= 1 where the will be no
       // other digit to check so the only thing left to do is to return the last digit
       if(Math.abs(n) <= 1) {
    	   return "" + n;
       }
       
       // If remainder is 1, then return a 1 in the digit's place 
       if(Math.abs(n) % 2 == 1 ) {
    	   return getBinary(n/2) + "1"; 
       }
       // Otherwise the remainder is 0 and return a 0 in the digit's place
       return getBinary(n/2) + "0";
   }


   /**
    * Problem 2: reverse a String recursively.<br>
    *   pre: stringToRev != null<br>
    *   post: returns a String that is the reverse of stringToRev
    *   @param stringToRev the String to reverse.
    *   @return a String with the characters in stringToRev in reverse order.
    */
   public static String revString(String stringToRev) {
       if (stringToRev == null) {
           throw new IllegalArgumentException("Failed precondition: revString. parameter may not be null.");
       }
       // Base Case: stringToRev only has one character so return the last character or has no character
       if(stringToRev.length() < 2) {
    	   return stringToRev;
       }  
       // Return a string with the first string in the last place and a method call of the remaining string
       return revString(stringToRev.substring(1)) + stringToRev.substring(0,1);
   }


   /**
    * Problem 3: Returns the number of elements in data
    * that are followed directly by value that is
    * double that element.
    * pre: data != null
    * post: return the number of elements in data that are followed immediately by double the value
    * @param data The array to search.
    * @return The number of elements in data that are followed immediately by a value that
    * is double the element.
    */
   public static int nextIsDouble(int[] data) {
       if (data == null) {
           throw new IllegalArgumentException("Failed precondition: revString. parameter may not be null.");
       }
       // Declare int variables to keep count of the number of elements followed by their double and the index
       int count = 0;
       int index = 0;
       // Return int by calling recursion method
       return numOfDoubles(data, index, count);
   }

   // Return number of elements in data that are followed by a double of itself
   private static int numOfDoubles(int[] data, int index, int count) {
	   // Base Case: the index is at the end of the array, and since the method looks ahead of the index
	   // nothing happens at the end of the array an so we return the final result of count
	   if(index == data.length - 1 || data.length == 0) {
		   return count;
	   }
	   // Check if the next element from the index position is double of current position
	   // Increment count if true
	   if(data[index] * 2 == data[index + 1]) {
		   count++;
	   }
	   index++; // Update to next index
	   // Return the count following the next elements in the array
	   return numOfDoubles(data, index, count);
   }

   /**  Problem 4: Find all combinations of mnemonics for the given number.<br>
    *   pre: number != null, number.length() > 0, all characters in number are digits<br>
    *   post: see tips section of assignment handout
    *   @param number The number to find mnemonics for
    *   @return An ArrayList<String> with all possible mnemonics for the given number
    */
   public static ArrayList<String> listMnemonics(String number) {
       if (number == null ||  number.length() == 0 || !allDigits(number)) {
           throw new IllegalArgumentException("Failed precondition: listMnemonics");
       }

       // Create an ArrayList to hold the possible mnemonics given the number
       ArrayList<String> result = new ArrayList<>();
       recursiveMnemonics(result, "", number); // Call recursive method to alter the ArrayList
       return result;
   }


   /*
    * Helper method for listMnemonics
    * mnemonics stores completed mnemonics
    * mneominicSoFar is a partial (possibly complete) mnemonic
    * digitsLeft are the digits that have not been used from the original number
    */
   private static void recursiveMnemonics(ArrayList<String> mnemonics,
           String mnemonicSoFar, String digitsLeft) {

       // Base Case: If the number of digits left to evaluate is 0, return the current mnemonic
	   // Otherwise add to mnemonicSoFar
	   if(digitsLeft.length() > 0) {
		   char firstDigit = digitsLeft.charAt(0); // First digit of the number we need to evaluate
		   String letterSet = digitLetters(firstDigit); // All characters associated with the first digit
		   
		   // Traverse through the characters associated with the first digit
		   for(int i = 0; i < letterSet.length(); i++) {
			   // Create a string to store the character i of letterSet
			   String newMnemonic = mnemonicSoFar + letterSet.charAt(i);
			   
			   // Call the recursive method with the newly made string as mnemonicSoFar and continously
			   // add the string until we can add it to the array
			   recursiveMnemonics(mnemonics, newMnemonic, digitsLeft.substring(1));
		   }
	   } else {
		   // There are no digits left, so add the final result of one possible mnemonic to the list
		   mnemonics.add(mnemonicSoFar);
	   }
   }


   // used by method digitLetters
   private static final String[] letters = {"0", "1", "ABC", "DEF", "GHI", "JKL", "MNO", "PQRS", "TUV", "WXYZ"};


   /* helper method for recursiveMnemonics
    * pre: ch is a digit '0' through '9'
    * post: return the characters associated with this digit on a phone keypad
    */
   private static String digitLetters(char ch) {
       if (ch < '0' || ch > '9') {
           throw new IllegalArgumentException("parameter ch must be a digit, 0 to 9. Given value = " + ch);
       }
       int index = ch - '0';
       return letters[index];
   }


   /* helper method for listMnemonics
    * pre: s != null
    * post: return true if every character in s is a digit ('0' through '9')
    * */
   private static boolean allDigits(String s) {
       if (s == null) {
           throw new IllegalArgumentException("Failed precondition: allDigits. String s cannot be null.");
       }
       boolean allDigits = true;
       int i = 0;
       while (i < s.length() && allDigits) {
           allDigits = s.charAt(i) >= '0' && s.charAt(i) <= '9';
           i++;
       }
       return allDigits;
   }


   /**
    * Problem 5: Draw a Sierpinski Carpet.
    * @param size the size in pixels of the window
    * @param limit the smallest size of a square in the carpet.
    */
   public static void drawCarpet(int size, int limit) {
       DrawingPanel p = new DrawingPanel(size, size);
       Graphics g = p.getGraphics();
       g.setColor(Color.BLACK);
       g.fillRect(0,0,size,size);
       g.setColor(Color.WHITE);
       drawSquares(g, size, limit, 0, 0);
   }


   /* Helper method for drawCarpet
    * Draw the individual squares of the carpet.
    * @param g The Graphics object to use to fill rectangles
    * @param size the size of the current square
    * @param limit the smallest allowable size of squares
    * @param x the x coordinate of the upper left corner of the current square
    * @param y the y coordinate of the upper left corner of the current square
    */
   private static void drawSquares(Graphics g, int size, int limit, double x, double y) {
	   // Base Case: if the size is greater than the limit, create a new 3 x 3 grid 
	   // Otherwise do nothing and end the recursive method calls
	   if(size > limit) {
		   // Variable to pass in for the next recursive call, to help change the 
		   // position of the x and y of the 9 squares, and establishes their size
		   int newSize = size / 3;
		   int newY = (int)y; // Variable for the y-position
		   
		   // Traverse through the rows of the 3 x 3 grid
		   for(int r = 0; r < 3; r++) {
			   int newX = (int)x; // Variable for the x-position that resets after 3 squares
			   // Traverse through the columns of the 3 x 3 grid
			   for(int c = 0; c < 3; c++) {
				   // If the row and column are representing the center, fill in the center
				   // with a white square in the middle
				   if(r == 1 && c == 1) {
					   g.fillRect(newX, newY, newSize, newSize);
				   } else {
					   // Otherwise call the method again and make a new grid in the section of the current grid
					   drawSquares(g, newSize, limit, newX, newY);
				   }
				   newX += newSize; // Change the x - position
			   }
			   newY += newSize; // Change the y - position
		   }
	   }

   }
   
   /**
    * Problem 6: Determine if water at a given point
    * on a map can flow off the map.
    * <br>pre: map != null, map.length > 0,
    * map is a rectangular matrix, 0 <= row < map.length, 0 <= col < map[0].length
    * <br>post: return true if a drop of water starting at the location
    * specified by row, column can reach the edge of the map, false otherwise.
    * @param map The elevations of a section of a map.
    * @param row The starting row of a drop of water.
    * @param col The starting column of a drop of water.
    * @return return true if a drop of water starting at the location
    * specified by row, column can reach the edge of the map, false otherwise.
    */
   public static boolean canFlowOffMap(int[][] map, int row, int col) {
       if( map == null || map.length == 0 || !isRectangular(map) || !inbounds(row, col, map)) {
           throw new IllegalArgumentException("Failed precondition: canFlowOffMap");
       }
       // Base Case: The position is the edge of the map and thus we can flow off, return true 
       if(row == 0 || row == map.length - 1 || col == 0 || col == map[0].length - 1) {
    	   return true;
       }
       // Value of the current spot of the map
       int val = map[row][col];
       boolean flow = false; // Initial boolean stating we cannot flow off the map
       
       // Traverse through ADJ_CELLS to search adjacent cells of map at the current spot
       // Exit loop once we found a way off the map
       for(int i = 0; i < ADJ_CELLS.length && !flow; i++) {
    	   int rowChange = ADJ_CELLS[i][0];
    	   int colChange = ADJ_CELLS[i][1];
    	   
    	   // If the adjacent position is less than the value of the current spot, then 
    	   // move to the adjacent position and find a new pathways
    	   if(map[row + rowChange][col + colChange] < val) {
    		   flow = canFlowOffMap(map, row + rowChange, col + colChange);
    	   }
       }
       return flow;
   }

   /* helper method for canFlowOfMap
    * pre: mat != null,
    */
   private static boolean inbounds(int r, int c, int[][] mat) {
       assert mat != null : "Failed precondition: inbounds";
       return r >= 0 && r < mat.length && mat[r] != null && c >= 0 && c < mat[r].length;
   }

   /*
    * helper method for canFlowOfMap 
    * pre: mat != null, mat.length > 0
    * post: return true if mat is rectangular
    */
   private static boolean isRectangular(int[][] mat) {
       assert (mat != null) && (mat.length > 0) : "Violation of precondition: isRectangular";

       boolean correct = true;
       final int numCols = mat[0].length;
       int row = 0;
       while( correct && row < mat.length) {
           correct = (mat[row] != null) && (mat[row].length == numCols);
           row++;
       }
       return correct;
   }


   /**
    * Problem 7: Find the minimum difference possible between teams
    * based on ability scores. The number of teams may be greater than 2.
    * The goal is to minimize the difference between the team with the
    * maximum total ability and the team with the minimum total ability.
    * <br> pre: numTeams >= 2, abilities != null, abilities.length >= numTeams
    * <br> post: return the minimum possible difference between the team
    * with the maximum total ability and the team with the minimum total
    * ability.
    * @param numTeams the number of teams to form.
    * Every team must have at least one member
    * @param abilities the ability scores of the people to distribute
    * @return return the minimum possible difference between the team
    * with the maximum total ability and the team with the minimum total
    * ability. The return value will be greater than or equal to 0.
    */
   public static int minDifference(int numTeams, int[] abilities) {
	   // Check precondition, abilities is not null, numTeams >= 2, and length of array >= numTeams
	   if(numTeams < 2 || abilities == null || abilities.length < numTeams) {
		   throw new IllegalArgumentException("Failed precondition: abilities is null, numTeams < 2, "
		   		+ "not enough people for teams");
	   }
	   // Create an ArrayList of the different teams
	   // Each ArrayList within will have numbers representing the abilities of members in a particular team
	   ArrayList<ArrayList<Integer>> teamSet = new ArrayList<ArrayList<Integer>>();
	   
	   // Create an ArrayList to contain teams with minimum differences
	   for(int i = 0; i < numTeams; i++) {
		   ArrayList<Integer> indexTeam = new ArrayList<Integer>();
		   teamSet.add(indexTeam);
	   }
	   // Get minimum differences with list for teams and an index
       return getTeamMinimum(numTeams, abilities, 0, teamSet);
   }
   
   // Recursive method to get the minimum difference of possible teams from the array of abilities
   // @param numTeams is the number of teams
   // @param abil is the array of abilities from people represented by ints
   // @param index is the int for the team we are altering 
   private static int getTeamMinimum(int numTeams, int[] abil, int index, ArrayList<ArrayList<Integer>> teamSet) {
	   // -1 will represent a default value where we are just starting or where there is one team without a player
	   int minimum = -1;
	   
	   // Reach base case where index is past the last person with abilities; in other words, everyone is in a team already
	   if(index == abil.length) {
		   // Check every team has a least one person
		   for(int i = 0; i < numTeams; i++)
			   if(teamSet.get(i).size() == 0)
				   return -1;
		   
		   // Get an ArrayList of the sum of each person's abilities in each team and get the difference of the max and min of that ArrayList
		   return getDifference(getSum(numTeams, teamSet));
	   }
	   
	   // Traverse through the number of teams
	   for(int i = 0; i < numTeams; i++) {
		   ArrayList<Integer> cur = teamSet.get(i); // Access all the teams in teamSet
		   cur.add(abil[index]); // Put the person at index into the current team we are viewing
		   
		   // Check the minimum difference that is possible if the player at index were placed in a certain team
		   int possibleDiff = getTeamMinimum(numTeams, abil, index + 1, teamSet);
		   
		   // if minimum has not been set to a meaningful value yet, not -1, and the possibleDiff
		   // is less than the current minimum, then change the value of the minimum
		   minimum = possibleDiff != -1 && (possibleDiff < minimum || minimum == -1)? possibleDiff: minimum;
		   
		   // Remove the person at index so the next scenario is not disrupted; we just want the difference, we don't care how the list results in the end
		   cur.remove(cur.size() - 1);
	   }
	   return minimum;
   }
   
   // Returns an ArrayList that has the sum of every team
   private static ArrayList<Integer> getSum(int numTeams, ArrayList<ArrayList<Integer>> teamSet) {
	   ArrayList<Integer> pointSum = new ArrayList<Integer>();
	   
	   // Traverse through the number of teams
	   for(int i = 0; i < numTeams; i++) {
		   ArrayList<Integer> cur = teamSet.get(i);
		   int sum = 0; // Variable to contain all points of a teams
		   
		   // Traverse through individual teams and get the cumulative sum of the team mates
		   for(int j = 0; j < cur.size(); j++) {
			   sum += cur.get(j);
		   }
		   pointSum.add(sum); // Add sum to ArrayList containing sums of other teams
	   }
	   return pointSum;
   }
   
   // Get the difference of the highest and lowest team points 
   private static int getDifference(ArrayList<Integer> pointSum) {
	   // Establish a max and min initially with the first team
	   int max = pointSum.get(0);
	   int min = pointSum.get(0);
	   
	   // Traverse through the rest of the teams 
	   for(int i = 1; i < pointSum.size(); i++) {
		   // Change max and min accordingly with each new index
		   max = Math.max(max, pointSum.get(i));
		   min = Math.min(min, pointSum.get(i));
	   }
	   return max - min; // Get the difference
   }
   
   /**
    * Problem 8: Maze solver. Return 2 if it is possible to escape the maze after
    * collecting all the coins. Return 1 if it is possible to escape the maze 
    * but without collecting all the coins. Return 0 if it is not possible
    * to escape the maze. More details in the assignment handout.
    * <br>pre: board != null
    * <br>pre: board is a rectangular matrix
    * <br>pre: board only contains characters 'S', 'E', '$', 'G', 'Y', and '*'
    * <br>pre: there is a single 'S' character present
    * <br>post: rawMaze is not altered as a result of this method.
    * Return 2 if it is possible to escape the maze after
    * collecting all the coins. Return 1 if it is possible to escape the maze 
    * but without collecting all the coins. Return 0 if it is not possible
    * to escape the maze. More details in the assignment handout.
    * @param rawMaze represents the maze we want to escape. 
    * rawMaze is not altered as a result of this method.
    * @return per the post condition
    */
   public static int canEscapeMaze(char[][] rawMaze) {
	   // Checks precondition, as stated in method description
	   if(rawMaze == null || !characterCheck(rawMaze)) {
		   throw new IllegalArgumentException("Failed precondition; board == null, the board"
		   		+ "is not rectangular, board contains unrecognizable characters, or there are"
		   		+ "more than one 'S' present");
	   }
	   // Create variables to find how many Gold spots there are and get the starting position
	   int numGold = 0;
	   int posRow = 0;
	   int posCol = 0;
	   
	   // Traverse through the rows of the maze
	   for(int r = 0; r < rawMaze.length; r++) {
		   // Traverse through the columns of the maze
		   for(int c = 0; c < rawMaze[0].length; c++) {
			   // If a Gold spot is found, increment the count of gold
			   if(rawMaze[r][c] == '$') {
				   numGold++;
				   
			   // If the starting spot is found, save the row and column
			   } else if(rawMaze[r][c] == 'S') {
				   posRow = r;
				   posCol = c;
			   }
		   }
	   }
       return travelMaze(rawMaze, posRow, posCol, numGold, 0);
   }
   
   // Determine if maze breaks precondition; board only contains characters 
   // 'S', 'E', '$', 'G', 'Y' and '*' and there is a single 'S' character present
   private static boolean characterCheck(char[][] rawMaze) {
	   int countS = 0; // Variable keeps count of number of 'S' characters present
	   
	   // Traverses through the rows of the char maze
	   for(char[] x: rawMaze) {
		   // Traverses through the columns of the char maze
		   for(char y: x) {
			   // If an 'S' is found increment count
			   if(y == 'S') {
				   countS++;
				   // If countS goes above 1, return false
				   if(countS > 1) {
					   return false;
				   }
			   // If an unknown character is found, return false
			   } else if(y != 'S' && y !='E' && y != '$' 
					   && y != 'G' && y != 'Y' && y != '*') {
				   return false;
			   }
		   }
	   }
	   return true; // No conditions were violated, return trueS
   }
   
   // Recursive method to determine if it is possible to escape a maze, and if so, with all coins as well
   // @param rawMaze char matrix of the map we are escaping
   // @param row and col are the current position we are at 
   // @param numGold is the total number of gold present in the map
   // @param goldHave is the number of gold we have currently found
   private static int travelMaze(char[][] rawMaze, int row, int col, int numGold, int goldHave) {
	   // Base Cases: If an escape spot is found, return a 2 if we have all the gold or a 1 if we can just escape
	   if(rawMaze[row][col] == 'E') {
		   return goldHave == numGold? 2: 1;
	   // IF we are stuck then return a 0, where we do not have an escape route possible
	   } else if(stuck(rawMaze, row, col)) {
		   return 0;
	   }
	   
	   goldHave += rawMaze[row][col] == '$'? 1: 0; // Increment goldHave if the spot we are on is a Money spot
	   
	   char change = changeMaze(rawMaze, row, col); // Get the character that would replace the current spot based on the rules
	   char undoChar = rawMaze[row][col]; // The current character of the spot we are in
	   int result = 0; // An int to represent the status of the pathway we take from this point on
	   
	   // Traverse through ADJ_CELLS to search adjacent cells of map at the current spot
       // Exit loop once we found an escape with all coins possible
	   for(int i = 0; i < ADJ_CELLS.length && result != 2; i++) {
    	   int r = ADJ_CELLS[i][0]; // The row change 
    	   int c = ADJ_CELLS[i][1]; // The column change
    	   
    	   // If the adjacent position is in bounds and is pass-able, then check for possible pathways
    	   if(inBounds(rawMaze, row + r, col + c) && rawMaze[row + r][col + c] != '*') {
    		   rawMaze[row][col] = change; // Change the current spot to the character it would change to based on rule
			   
			   int temp = travelMaze(rawMaze, row + r, col + c, numGold, goldHave); // Check the pathway with a change to the spot
			   
			   rawMaze[row][col] = undoChar; // Undo change to current spot so the status of the next check is not disturbed
			   result = temp > result? temp: result; // If the int from the pathway is greater than the current result, then change result
    	   }
	   }
	   
	   return result;
   }
   
   // Returns the character change that happens on the spot based on what it is initially
   private static char changeMaze(char[][] rawMaze, int row, int col) {
	   char result;
       if(rawMaze[row][col] == 'G' || rawMaze[row][col] == '$') { // Return a 'Y' on a Green and Money spot
    	   result = 'Y';
	   } else if(rawMaze[row][col] == 'Y') { // Return a '*' on Yellow spot
		   result = '*';
	   } else { // Return a 'G' on a Starting spot
    	   result = 'G';
	   }
	   return result;
   }

   // Method returns true if movements are no longer possible in the current spot 
   private static boolean stuck(char[][] rawMaze, int row, int col) {
	   int walls = 0; // int to represent the number of directions that are not pass-able
	   
	   // Traverse through ADJ_CELLS to check adjacent cells
	   for(int i = 0; i < ADJ_CELLS.length; i++) {
		   int r = row + ADJ_CELLS[i][0]; // Change row 
		   int c = col + ADJ_CELLS[i][1]; // Change col
		   // If the adjacent cell is a * or out of bounds, then increment wall count
		   walls += !inBounds(rawMaze, r, c) || rawMaze[r][c] == '*' ? 1: 0;
	   }
	   return walls == 4;
	}
   
   // Determines if a position is out of bounds of the map
   private static boolean inBounds(char[][] rawMaze, int row, int col) {
	   return row >= 0 && row < rawMaze.length && col >= 0 && col < rawMaze[0].length;
   }
}