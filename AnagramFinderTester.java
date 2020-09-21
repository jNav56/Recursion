import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AnagramFinderTester {

    private static final String testCaseFileName = "testCaseAnagrams.txt";
    private static final String dictionaryFileName = "d3.txt";

    public static void main(String[] args) {
        //letterInventoryTests();
    	
    	// Student tests
    	cs314StudentTestsForLetterInventory();
        
        // tests on the anagram solver itself
        boolean displayAnagrams = getChoiceToDisplayAnagrams();
        AnagramSolver solver = new AnagramSolver(AnagramMain.readWords(dictionaryFileName));
        runAnagramTests(solver, displayAnagrams);
    }
    
    private static void letterInventoryTests() {
        LetterInventory lets1 = new LetterInventory("");
        Object expected = 0;
        Object actual = lets1.size();
        showTestResults(expected, actual, 1, " size on empty LetterInventory");

        expected = "";
        actual = lets1.toString();
        showTestResults(expected, actual, 2, " toString on empty LetterInventory");

        expected = 0;
        actual = lets1.get('A');
        showTestResults(expected, actual, 3, " get on empty LetterInventory");

        expected = 0;
        actual = lets1.get('z');
        showTestResults(expected, actual, 4, " get on empty LetterInventory");

        expected = true;
        actual = lets1.isEmpty();
        showTestResults(expected, actual, 5, " isEmpty on empty LetterInventory");

        expected = "";
        actual = lets1.toString();
        showTestResults(expected, actual, 6, " LetterInventory toString on empty LetterInventory");


        lets1 = new LetterInventory("mmmmm");
        expected = "mmmmm";
        actual = lets1.toString();
        showTestResults(expected, actual, 7, " LetterInventory toString");


        LetterInventory lets2 = new LetterInventory("\"Stanford University\"!! Jr<>(())G");
        expected = "adefgiijnnorrrssttuvy";
        actual = lets2.toString();
        showTestResults(expected, actual, 8, " LetterInventory constructor and toString");

        expected = 21;
        actual = lets2.size();
        showTestResults(expected, actual, 9, " LetterInventory size");

        expected = false;
        actual = lets2.isEmpty();
        showTestResults(expected, actual, 10, " LetterInventory isEmpty");

        expected = null;
        actual = lets2.subtract(lets1);
        showTestResults(expected, actual, 11, "LetterInventory subtract");

        lets1 = new LetterInventory("stand ---- ton");
        expected = "efgiijrrrsuvy";
        actual = lets2.subtract(lets1).toString();
        showTestResults(expected, actual, 12, "LetterInventory subtract");

        expected = 13;
        actual = lets2.subtract(lets1).size();
        showTestResults(expected, actual, 13, "LetterInventory size after subtract");

        expected = false;
        actual = lets2.isEmpty();
        showTestResults(expected, actual, 14, "LetterInventory isEmpty after subtract");

        expected = null;
        actual = lets1.subtract(lets2);
        showTestResults(expected, actual, 15, "LetterInventory subtract");

        expected = false;
        actual = lets1.equals(lets2);
        showTestResults(expected, actual, 16, "LetterInventory equals");

        lets1 = new LetterInventory("Ol33vIA33");
        expected = "aadefgiiijlnnoorrrssttuvvy";
        LetterInventory lets3 = lets1.add(lets2);
        actual = lets3.toString();
        showTestResults(expected, actual, 17, "LetterInventory add");

        expected = 26;
        actual = lets3.size();
        showTestResults(expected, actual, 18, "LetterInventory size after add");

        expected = false;
        actual = lets3.isEmpty();
        showTestResults(expected, actual, 19, "LetterInventory isEmpty after add");

        lets3 = lets1.add(lets2);
        LetterInventory lets4 = lets2.add(lets1);
        showTestResults(lets3, lets4, 20, "LetterInventory add and equals");

        expected = false;
        StringBuilder foo = new StringBuilder();
        actual = lets3.equals(foo);
        showTestResults(expected, actual, 21, "LetterInventory equals");

        expected = false;
        String str = "abeeills";
        lets3 = new LetterInventory("ISAbelle!!");
        actual = lets3.equals(str);
        showTestResults(expected, actual, 22, "LetterInventory equals");

        expected = true;
        lets2 = new LetterInventory("\\abeei\"ll0212s");
        lets3 = new LetterInventory("ISAbelle!!");
        actual = lets3.equals(lets2);
        showTestResults(expected, actual, 23, "LetterInventory equals");
    }
    
    private static void cs314StudentTestsForLetterInventory() {
    	LetterInventory l1 = new LetterInventory("");
        String actual = "";
        String expected = "";
        int i = 1;
        
        System.out.println("");
        // Testing Constructor
        System.out.println("-----Testing Constructor-----\n");
        
        System.out.println("Passing in a null String");
        actual = "String is null";        
        try {
        	String nulledStr = null;
            l1 = new LetterInventory(nulledStr);
        } catch(IllegalArgumentException e) {
        	expected = "String is null";
        }
        status(actual, expected, i++);
        
        System.out.println("Passing in a String with non-English letter characters");
        l1 = new LetterInventory("a.b.c.d.e.f.g");
        actual = "abcdefg";
        expected = l1.toString();
        status(actual, expected, i++);
        
        // Testing get() method
        System.out.println("-----Testing get() method-----\n");
        
        System.out.println("Getting a English letter character");
        actual = "1";
        expected = "" + l1.get('g');
        status(actual, expected, i++);
        
        System.out.println("Getting a non-English letter character");
        actual = "Character is not an English letter";
        try {
        	l1.get('.');
        } catch(IllegalArgumentException e) {
        	expected = "Character is not an English letter";
        }
        status(actual, expected, i++);
        
        // Testing size() method
        System.out.println("-----Testing size() method-----\n");
        
        System.out.println("Getting the size of a non-empty LetterInventory");
        actual = "7";
        expected = "" + l1.size();
        status(actual, expected, i++);
        
        System.out.println("Getting the size of an empty LetterInventory");
        l1 = new LetterInventory("");
        actual = "0";
        expected = "" + l1.size();
        status(actual, expected, i++);
        
        // Testing isEmpty() method
        System.out.println("-----Testing isEmpty() method-----\n");
        
        System.out.println("Getting boolean result of an empty LetterInventory");
        actual = "true";
        expected = "" + l1.isEmpty();
        status(actual, expected, i++);
        
        System.out.println("Getting boolean result of a non-empty LetterInventory");
        l1 = new LetterInventory("abcdefg");
        actual = "false";
        expected = "" + l1.isEmpty();
        status(actual, expected, i++);
        
        // Testing add() method
        System.out.println("-----Testing add() method-----\n");
        
        LetterInventory l2 = new LetterInventory("hijklmnop");
        LetterInventory l3;
        
        System.out.println("Adding LetterInvetorys of \"abcdefg\" and \"hijklmnop\"");
        l3 = l1.add(l2);
        actual = "abcdefghijklmnop";
        expected = "" + l3.toString();
        status(actual, expected, i++);
        
        System.out.println("Adding when passed in LetterInventory is null");
        l3 = null;
        actual = "Passed in object is null";
        try {
        	l1.add(l3);
        } catch(IllegalArgumentException e) {
        	expected = "Passed in object is null";
        }
        status(actual, expected, i++);
        
        // Testing subtract() method
        System.out.println("-----Testing subtract() method-----\n");
        
        System.out.println("Subtracting LetterInvetorys of \"aabbccdde\" and \"acbd\"");
        l1 = new LetterInventory("aabbccdde");
        l2 = new LetterInventory("acbd");
        l3 = l1.subtract(l2);
        actual = "abcde";
        expected = "" + l3.toString();
        status(actual, expected, i++);
        
        System.out.println("Subtracting LetterInvetorys of \"abcde\" and \"g\"");
        l1 = new LetterInventory("abcde");
        l2 = new LetterInventory("g");
        l3 = l1.subtract(l2);
        actual = "LetterInventory is null";
        if(l3 == null)
        	expected = actual;
        status(actual, expected, i++);
        
        // Testing equals() method
        System.out.println("-----Testing equals() method-----\n");
        
        System.out.println("Calling method with null Object object");
        actual = "Object obj is null";
        try {
        	l1.equals(l3);
        } catch(IllegalArgumentException e) {
        	expected = "Object obj is null";
        }
        status(actual, expected, i++);
        
        System.out.println("Calling method with LetterInventorys \"adgjl\" and \"adgjl\"");
        l1 = new LetterInventory("adgjl");
        l2 = new LetterInventory("adgjl");
        actual = "true";
        expected = "" + l1.equals(l2);
        status(actual, expected, i++);
        
        // Testing toString() method
        System.out.println("-----Testing toString() method-----\n");
        
        System.out.println("Calling method with LetterInventory \"PrindleGotArrested\"");
        l1 = new LetterInventory("PrindleGotArrested");
        actual = "addeeegilnoprrrstt";
        expected = l1.toString();
        status(actual, expected, i++);
        
        System.out.println("Calling method with an empty LetterInventory");
        l1 = new LetterInventory("");
        actual = "";
        expected = "" + l1.toString();
        status(actual, expected, i++);
    }
    
    private static void status(String act, String exp, int test) {
    	String res = exp.equals(act)? "Passed": "Failed";
    	System.out.println("Expected: " + exp);
        System.out.println("Actual: " + act);
        System.out.println("Test " + test + ": " + res);
        System.out.println();
    }

    private static boolean getChoiceToDisplayAnagrams() {
        Scanner console = new Scanner(System.in);
        System.out.print("Enter y or Y to display anagrams during tests: ");
        String response = console.nextLine();
        return response.length() > 0 && response.toLowerCase().charAt(0) == 'y';
    }

    public static boolean showTestResults(Object expected, Object actual, int testNum, String featureTested) {
        System.out.println("Test Number " + testNum + " testing " + featureTested);
        System.out.println("Expected result: " + expected);
        System.out.println("Actual result: " + actual);
        boolean passed = (actual == null && expected == null) || actual.equals(expected);
        if(passed)
            System.out.println("Passed test " + testNum);
        else
            System.out.println("!!! FAILED TEST !!! " + testNum);
        System.out.println();
        return passed;
    }

    /**
     * Method to run tests on Anagram solver itself.
     * pre: the files d3.txt and testCaseAnagrams.txt are in the local directory
     * 
     * assumed format for file is
     * <NUM_TESTS>
     * <TEST_NUM>
     * <MAX_WORDS>
     * <PHRASE>
     * <NUMBER OF ANAGRAMS>
     * <ANAGRAMS>
     */
    private static void runAnagramTests(AnagramSolver solver, boolean displayAnagrams) {
        int solverTestCases = 0;
        int solverTestCasesPassed = 0;
        Stopwatch st = new Stopwatch();
        try {
            Scanner sc = new Scanner(new File(testCaseFileName));
            final int NUM_TEST_CASES = Integer.parseInt(sc.nextLine().trim());
            System.out.println(NUM_TEST_CASES);
            for (int i = 0; i < NUM_TEST_CASES; i++) {
                // expected results
                TestCase currentTest = new TestCase(sc);
                solverTestCases++;
                st.start();
                // actual results
                List<List<String>> actualAnagrams = solver.getAnagrams(currentTest.phrase, currentTest.maxWords);
                st.stop();
                if (checkPassOrFailTest(currentTest, actualAnagrams)) {
                    solverTestCasesPassed++;
                }
                System.out.println();
                System.out.println("Time to find anagrams: " + st.time());
                if (displayAnagrams) {
                    displayAnagrams("actual anagrams", actualAnagrams);
                    System.out.println();
                    displayAnagrams("expected anagrams", currentTest.anagrams);
                }
                System.out.println("\n******************************************\n");
                // System.out.println("Number of calls to recursive helper method: " + NumberFormat.getNumberInstance(Locale.US).format(AnagramSolver.callsCount));
            }
            sc.close();
        } catch(IOException e) {
            System.out.println("\nProblem while running test cases on AnagramSolver. Check" +
                            " that file testCaseAnagrams.txt is in the correct location.");
            System.out.println(e);
            System.out.println("AnagramSolver test cases run: " + solverTestCases);
            System.out.println("AnagramSolver test cases failed: " + (solverTestCases - solverTestCasesPassed));
        }
        System.out.println("\nAnagramSolver test cases run: " + solverTestCases);
        System.out.println("AnagramSolver test cases failed: " + (solverTestCases - solverTestCasesPassed));
    }


    // print out all of the anagrams in a list of anagram
    private static void displayAnagrams(String type,
                    List<List<String>> anagrams) {

        System.out.println("Results for " + type);
        System.out.println("num anagrams: " + anagrams.size());
        System.out.println("anagrams: ");
        for(List<String> singleAnagram : anagrams)
            System.out.println(singleAnagram);
    }


    // determine if the test passed or failed
    private static boolean checkPassOrFailTest(TestCase currentTest,
                    List<List<String>> actualAnagrams) {

        boolean passed = true;
        System.out.println();
        System.out.println("Test number: " + currentTest.testCaseNumber);
        System.out.println("Phrase: " + currentTest.phrase);
        System.out.println("Word limit: " + currentTest.maxWords);
        System.out.println("Expected Number of Anagrams: " + currentTest.anagrams.size());
        if(actualAnagrams.equals(currentTest.anagrams)) {
            System.out.println("Passed Test");
        } else {
            System.out.println("\n!!! FAILED TEST CASE !!!");
            System.out.println("Recall MAXWORDS = 0 means no limit.");
            System.out.println("Expected number of anagrams: " + currentTest.anagrams.size());
            System.out.println("Actual number of anagrams:   " + actualAnagrams.size());
            if(currentTest.anagrams.size() == actualAnagrams.size()) {
                System.out.println("Sizes the same, but either a difference in anagrams or anagrams not in correct order.");
            }
            System.out.println();
            passed = false;
        }  
        return passed;
    }

    // class to handle the parameters for an anagram test 
    // and the expected result
    private static class TestCase {

        private int testCaseNumber;
        private String phrase;
        private int maxWords;
        private List<List<String>> anagrams;

        // pre: sc is positioned at the start of a test case
        private TestCase(Scanner sc) {
            testCaseNumber = Integer.parseInt(sc.nextLine().trim());
            maxWords = Integer.parseInt(sc.nextLine().trim());
            phrase = sc.nextLine().trim();
            anagrams = new ArrayList<>();
            readAndStoreAnagrams(sc);
        }

        // pre: sc is positioned at the start of the resulting anagrams
        // read in the number of anagrams and then for each anagram:
        //  - read in the line
        //  - break the line up into words
        //  - create a new list of Strings for the anagram
        //  - add each word to the anagram
        //  - add the anagram to the list of anagrams
        private void readAndStoreAnagrams(Scanner sc) {
            int numAnagrams = Integer.parseInt(sc.nextLine().trim());
            for (int j = 0; j < numAnagrams; j++) {
                String[] words = sc.nextLine().split("\\s+");
                ArrayList<String> anagram = new ArrayList<String>();
                for (String st : words) {
                    anagram.add(st);
                }
                anagrams.add(anagram);
            }
            assert anagrams.size() == numAnagrams : "Wrong number of angrams read or expected";
        }
    }
}