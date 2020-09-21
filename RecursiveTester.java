import java.util.ArrayList;
import java.util.Collections;

/**
 * Tester class for the methods in Recursive.java
 * @author scottm
 *
 */
public class RecursiveTester {

    // run the tests
    public static void main(String[] args) {
        studentTests();
    }

    private static void doMazeTests() {        
        int mazeTestNum = 1;
        runMazeTest("$GSGE", 1, 2, mazeTestNum++);
        runMazeTest("$Y$SGE", 1, 1, mazeTestNum++);
        String maze = "GGSGG**GGGGG";
        runMazeTest(maze, 3, 0, mazeTestNum++);
        maze = "GSGGG**GGEGG";
        runMazeTest(maze, 3, 2, mazeTestNum++);
        maze = "*EY****YYYYYY**YYYSY";
        runMazeTest(maze, 5, 0, mazeTestNum++);
        maze = "**$****G**ESGG$**G****$**";
        runMazeTest(maze, 5, 1, mazeTestNum++);
        maze = "**$****G**ESGG$*GGG***$G*";
        runMazeTest(maze, 5, 2, mazeTestNum++);
        maze = "**$****G**ESGG$**G*$**$**";
        runMazeTest(maze, 5, 1, mazeTestNum++);
        maze = "EYSG$";
        runMazeTest(maze, 1, 2, mazeTestNum++);
        maze = "EYYGGGYGSGGG$GG";
        runMazeTest(maze, 3, 2, mazeTestNum++);
        maze = "EGG$$$GGSGG$$$$";
        runMazeTest(maze, 3, 2, mazeTestNum++);
        maze = "EYY$$$YYSG***YYG$$$$";
        runMazeTest(maze, 4, 2, mazeTestNum++);
        maze = "EYY$$$YYSG****YG$$$$";
        runMazeTest(maze, 4, 1, mazeTestNum++);
        maze = "EYY$$$**SGY***YG$$$$";
        runMazeTest(maze, 4, 2, mazeTestNum++);
    }
    
    private static void runMazeTest(String rawMaze, int rows, int expected, int num) {
        char[][] maze = makeMaze(rawMaze, rows);
        System.out.println("Can escape maze test number " + num);
        printMaze(maze);
        int actual = Recursive.canEscapeMaze(maze);
        System.out.println("Expected result: " + expected);
        System.out.println("Actual result:   " + actual);
        if (expected == actual) {
            System.out.println("passed test " + num);
        } else {
            System.out.println("FAILED TEST " + num);
        }
        System.out.println();
    }

    // print the given maze
    // pre: none
    private static void printMaze(char[][] maze) {
        if (maze == null) {
            System.out.println("NO MAZE GIVEN");
        } else {
            for (char[] row : maze) {
                for (char c : row) {
                    System.out.print(c);
                    System.out.print(" ");
                }
                System.out.println();
            }
        }
    }

    // generate a char[][] given the raw string
    // pre: rawMaze != null, rawMaze.length() % rows == 0
    private static char[][] makeMaze(String rawMaze, int rows) {
        if (rawMaze == null || rawMaze.length() % rows != 0) {
            throw new IllegalArgumentException("Violation of precondition in makeMaze."
                            + "Either raw data is null or left over values: ");
        }
        int cols = rawMaze.length() / rows;
        char[][] result = new char[rows][cols];
        int rawIndex = 0;
        for (int r = 0; r < result.length; r++) {
            for (int c = 0; c < result[0].length; c++) {
                result[r][c] = rawMaze.charAt(rawIndex);
                rawIndex++;
            }
        }
        return result;
    }

    private static void  doCarpetTest() {
        Recursive.drawCarpet(729, 4);
        Recursive.drawCarpet(729, 1);
        
    }

    private static void doFairTeamsTests() {
//        System.out.println("Stress test for minDifference - may take up to a minute");
//        int[] testerArr = new int[] {5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55, 60, 65, 70, 75, 100000};
//        Stopwatch s = new Stopwatch();
//        s.start();
//        int actualInt = Recursive.minDifference(4, testerArr);
//        s.stop();
//        System.out.println("Time to solve for 16 people on 4 teams: " + s.time() + "\n");
//        System.out.println(actualInt);
        
        
        int[] abilities = {1, 2, 3, 4, 5, 6, 7};
        if(Recursive.minDifference(3, abilities) == 1)
            System.out.println( "Test 1 passed. min difference.");
        else
            System.out.println( "Test 1 failed. min difference.");

        if(Recursive.minDifference(5, abilities) == 2)
            System.out.println( "Test 2 passed. min difference.");
        else
            System.out.println( "Test 2 failed. min difference.");

        if(Recursive.minDifference(6, abilities) == 4)
            System.out.println( "Test 3 passed. min difference.");
        else
            System.out.println( "Test 3 failed. min difference.");

        abilities = new int[]{1, 12, 46, 60, 53, 86, 72, 79, 44, 7};
        if(Recursive.minDifference(3, abilities) == 3)
            System.out.println( "Test 4 passed. min difference.");
        else
            System.out.println( "Test 4 failed. min difference.");

        if(Recursive.minDifference(5, abilities) == 19)
            System.out.println( "Test 5 passed. min difference.");
        else
            System.out.println( "Test 5 failed. min difference.");


        abilities = new int[]{10, 10, 6, 6, 6};
        if(Recursive.minDifference(2, abilities) == 2)
            System.out.println( "Test 6 passed. min difference.");
        else
            System.out.println( "Test 6 failed. min difference.");  
        
        abilities = new int[]{-10, -10, -8, -8, -8};
        if(Recursive.minDifference(2, abilities) == 4)
            System.out.println( "Test 6 passed. min difference.");
        else
            System.out.println( "Test 6 failed. min difference."); 
        
        abilities = new int[]{-5, 5, 10, 5, 10, -15};
        if(Recursive.minDifference(2, abilities) == 0)
            System.out.println( "Test 7 passed. min difference.");
        else
            System.out.println( "Test 7 failed. min difference."); 
    }

    private static void doFlowOffMapTests() {
        int testNum = 1;
        int[][] world = {{5,5,5,5,5,5},
                         {5,5,5,5,5,5},
                         {5,5,5,5,5,5},
                         {5,5,4,4,5,5},
                         {5,5,3,3,5,5},
                         {5,5,2,2,5,5},
                         {5,5,5,1,5,5},
                         {5,5,5,-2,5,5}};

        doOneFlowTest(world, 0, 0, true, testNum++);
        doOneFlowTest(world, 1, 1, false, testNum++);
        doOneFlowTest(world, 3, 3, true, testNum++);
        doOneFlowTest(world, 1, 5, true, testNum++);

        world = new int[][]
           {{10, 10, 10, 10, 10, 10, 10},
            {10, 10, 10,  5, 10, 10, 10},
            {10, 10, 10,  6, 10, 10, 10},
            {10, 10, 10,  7, 10, 10, 10},
            {5,   6,  7,  8,  7,  6, 10},
            {10, 10, 10,  7, 10, 10, 10},
            {10, 10, 10,  6, 10, 10, 10},
            {10, 10, 10,  5, 10, 10, 10},
            {10, 10, 10, 10, 10, 10, 10}};
            
        doOneFlowTest(world, 3, 3, false, testNum++);
        doOneFlowTest(world, 4, 3, true, testNum++);  
        System.out.println();
    }
    
    private static void doOneFlowTest(int[][] world, int r, int c, boolean expected, int testNum) {
        System.out.println("Can Flow Off Map Test Number " + testNum);
        boolean actual = Recursive.canFlowOffMap(world, r, c);
        System.out.println("Start location = " + r + ", " + c);
        System.out.println("Expected result = " + expected + " actual result = " + actual);
        if (expected == actual) {
            System.out.println("passed test " + testNum + " can flow off map.");
        } else {
            System.out.println("FAILED TEST " + testNum + " can flow off map.");
        }
        System.out.println();
    }

    private static void doListMnemonicsTests() {
        ArrayList<String> mnemonics = Recursive.listMnemonics("1");
        ArrayList<String> expected = new ArrayList<>();
        expected.add("1");
        if (mnemonics.equals(expected))
            System.out.println( "Test 1 passed. Phone mnemonics.");
        else
            System.out.println( "Test1 failed. Phone mnemonics.");

        mnemonics = Recursive.listMnemonics("22");
        Collections.sort(mnemonics);
        expected.clear();
        expected.add("AA");
        expected.add("AB");
        expected.add("AC");
        expected.add("BA");
        expected.add("BB");
        expected.add("BC");
        expected.add("CA");
        expected.add("CB");
        expected.add("CC");
        Collections.sort(expected);
        if (mnemonics.equals(expected))
            System.out.println( "Test 2 passed. Phone mnemonics.");
        else
            System.out.println( "Test 2 failed. Phone mnemonics.");

        mnemonics = Recursive.listMnemonics("110010");
        expected.clear();
        expected.add("110010");
        if (mnemonics.equals(expected))
            System.out.println( "Test 3 passed. Phone mnemonics.");
        else
            System.out.println( "Test 3 failed. Phone mnemonics.");
        System.out.println();

    }

    private static void doNextIsDoubleTests() {
        int[] numsForDouble = {1, 2, 4, 8, 16, 32, 64, 128, 256};
        int actualDouble = Recursive.nextIsDouble(numsForDouble);
        int expectedDouble = 8;
        if (actualDouble == expectedDouble)
            System.out.println( "Test 1 passed. next is double.");
        else
            System.out.println( "Test 1 failed. next is double. expected: " + expectedDouble + ", actual: " + actualDouble);


        numsForDouble = new int[]{1, 3, 4, 2, 32, 8, 128, -5, 6};
        actualDouble = Recursive.nextIsDouble(numsForDouble);
        expectedDouble = 0;
        if (actualDouble == expectedDouble)
            System.out.println( "Test 2 passed. next is double.");
        else
            System.out.println( "Test 2 failed. next is double. expected: " + expectedDouble + ", actual: " + actualDouble);


        numsForDouble = new int[]{1, 0, 0, -5, -10, 32, 64, 128, 2, 9, 18};
        actualDouble = Recursive.nextIsDouble(numsForDouble);
        expectedDouble = 5;
        if (actualDouble == expectedDouble)
            System.out.println( "Test 3 passed. next is double.");
        else
            System.out.println( "Test 3 failed. next is double. expected: " + expectedDouble + ", actual: " + actualDouble);
        
        numsForDouble = new int[]{37};
        actualDouble = Recursive.nextIsDouble(numsForDouble);
        expectedDouble = 0;
        if (actualDouble == expectedDouble)
            System.out.println( "Test 4 passed. next is double.");
        else
            System.out.println( "Test 4 failed. next is double. expected: " + expectedDouble + ", actual: " + actualDouble);
        
        numsForDouble = new int[]{37, 74};
        actualDouble = Recursive.nextIsDouble(numsForDouble);
        expectedDouble = 1;
        if (actualDouble == expectedDouble)
            System.out.println( "Test 5 passed. next is double.");
        else
            System.out.println( "Test 5 failed. next is double. expected: " + expectedDouble + ", actual: " + actualDouble);
        System.out.println();
    }

    private static void doReverseTests() {
        String actualRev = Recursive.revString("target");
        String expectedRev = "tegrat";
        if (actualRev.equals(expectedRev))
            System.out.println( "Test 1 passed. reverse string.");
        else
            System.out.println( "Test 1 failed. reverse string. expected: " + expectedRev + ", actual: " + actualRev);


        actualRev = Recursive.revString("Calvin and Hobbes");
        expectedRev = "sebboH dna nivlaC";
        if (actualRev.equals(expectedRev))
            System.out.println( "Test 2 passed. reverse string.");
        else
            System.out.println( "Test 2 failed. reverse string. expected: " + expectedRev + ", actual: " + actualRev);
        
        actualRev = Recursive.revString("U");
        expectedRev = "U";
        if (actualRev.equals(expectedRev))
            System.out.println( "Test 3 passed. reverse string.");
        else
            System.out.println( "Test 3 failed. reverse string. expected: " + expectedRev + ", actual: " + actualRev);
        System.out.println();
    }

    private static void doBinaryTests() {
        String actualBinary = Recursive.getBinary(13);
        String expectedBinary = "1101";
        if (actualBinary.equals(expectedBinary))
            System.out.println( "Test 1 passed. get binary.");
        else
            System.out.println( "Test 1 failed. get binary. expected: " + expectedBinary + ", actual: " + actualBinary);


        actualBinary = Recursive.getBinary(0);
        expectedBinary = "0";
        if (actualBinary.equals(expectedBinary))
            System.out.println( "Test 2 passed. get binary.");
        else
            System.out.println( "Test 2 failed. get binary. expected: " + expectedBinary + ", actual: " + actualBinary);

        actualBinary = Recursive.getBinary(-35);
        expectedBinary = "-100011";
        if (actualBinary.equals(expectedBinary))
            System.out.println( "Test 3 passed. get binary.");
        else
            System.out.println( "Test 3 failed. get binary. expected: " + expectedBinary + ", actual: " + actualBinary);

        actualBinary = Recursive.getBinary(1);
        expectedBinary = "1";
        if (actualBinary.equals(expectedBinary))
            System.out.println( "Test 4 passed. get binary.");
        else
            System.out.println( "Test 4 failed. get binary. expected: " + expectedBinary + ", actual: " + actualBinary);


        actualBinary = Recursive.getBinary(64);
        expectedBinary = "1000000";
        if (actualBinary.equals(expectedBinary))
            System.out.println( "Test 5 passed. get binary");
        else
            System.out.println( "Test 5 failed. get binary. expected: " + expectedBinary + ", actual: " + actualBinary);
        System.out.println();
    }

    // pre: r != null
    // post: run student test
    private static void studentTests() {
    	String actual = "";
        String expected = "";
        int testNum = 1;
    	
    	// Testing Binary method
        System.out.println("-----Testing Binary method-----\n");
        System.out.println("Parameter n = -100");
        actual = "" + Recursive.getBinary(-100);
        expected = "-1100100";
        status(actual, expected, testNum++);
        
        System.out.println("Parameter n = 2^10");
        actual = "" + Recursive.getBinary(1024);
        expected = "10000000000";
        status(actual, expected, testNum++);
        
        // Testing Binary method
        System.out.println("-----Testing Reverse String method-----\n");
        System.out.println("Parameter stringToRev = \"ROTAator\"");
        actual = Recursive.revString("ROTAator");
        expected = "rotaATOR";
        status(actual, expected, testNum++);
        
        System.out.println("Parameter stringToRev = \"\"");
        actual = Recursive.revString("");
        expected = "";
        status(actual, expected, testNum++);
        
        // Testing Next is Double method
        System.out.println("-----Testing Next is Double method-----\n");
        System.out.println("Parameter data = {0, 0, 0, 0, 0}");
        actual = "" + Recursive.nextIsDouble(new int[]{0, 0, 0, 0, 0});
        expected = "4";
        status(actual, expected, testNum++);
        
        System.out.println("Parameter data = {}");
        actual = "" + Recursive.nextIsDouble(new int[]{});
        expected = "0";
        status(actual, expected, testNum++);
        
        // Testing Phone Mnemonics method
        System.out.println("-----Testing Phone Mnemonics method-----\n");
        System.out.println("Parameter number = 1010101010");
        actual = "" + Recursive.listMnemonics("1010101010");
        expected = "[1010101010]";
        status(actual, expected, testNum++);
        
        System.out.println("Parameter number = 1213");
        actual = "" + Recursive.listMnemonics("1213");
        expected = "[1A1D, 1A1E, 1A1F, 1B1D, 1B1E, 1B1F, 1C1D, 1C1E, 1C1F]";
        status(actual, expected, testNum++);
        
        // Testing Water Flow method
        System.out.println("-----Testing Water Flow method-----\n");
        
        int[][] map = {{3,3,3,3,3},
                	   {3,4,4,4,3},
                	   {3,4,5,4,3},
                	   {3,4,4,4,3},
                	   {3,3,3,3,3}}; 
        System.out.println("Parameter map = {3,3,3,3,3}\n"
        		+ "		{3,4,4,4,3}\n" + 
        		"row = 2		{3,4,5,4,3}\n" + 
        		"col = 2		{3,4,4,4,3}\n" + 
        		"		{3,3,3,3,3}");
        actual = "" + Recursive.canFlowOffMap(map, 2, 2);
        expected = "true";
        status(actual, expected, testNum++);
        
        map = new int[][] {{9,9,9,9,9},
        				   {9,8,1,2,9},
        				   {9,7,0,3,9},
        				   {9,6,5,4,9},
        				   {9,9,9,9,9}}; 
        System.out.println("Parameter map = {9,9,9,9,9}\n"
        		+ "		{9,8,1,2,9}\n" + 
        		"row = 1		{9,7,0,3,9}\n" + 
        		"col = 1		{9,6,5,4,9}\n" + 
        		"		{9,9,9,9,9}");
        actual = "" + Recursive.canFlowOffMap(map, 1, 1);
        expected = "false";
        status(actual, expected, testNum++);
        
        // Testing Fair Teams method
        System.out.println("-----Testing Fair Teams method-----\n");
        System.out.println("Parameter numTeams = 3 and abilities = {1,2,3,4,5,10}");
        actual = "" + Recursive.minDifference(3, new int[]{1,2,3,4,5,10});
        expected = "3";
        status(actual, expected, testNum++);
        
        System.out.println("Parameter numTeams = 2 and abilities = {1,1,1,1,1}");
        actual = "" + Recursive.minDifference(2, new int[]{1,1,1,1,1});
        expected = "1";
        status(actual, expected, testNum++);
        
        // Testing Raw Maze method
        System.out.println("-----Testing Raw Maze method-----\n");
        
        char[][] rawMaze = {{'$','G','G','G','$'},
        					{'G','*','$','*','G'},
        					{'G','$','S','$','G'},
        					{'G','*','$','*','G'},
        					{'$','G','G','G','$'}}; 
        System.out.println("Parameter rawMaze = {'$','G','G','G','$'}\n"
        		+ "		    {'G','*','$','*','G'}\n" + 
        		"		    {'G','$','S','$','G'}\n" + 
        		"		    {'G','*','$','*','G'}\n" + 
        		"		    {'$','G','G','G','$'}");
        actual = "" + Recursive.canEscapeMaze(rawMaze);
        expected = "0";
        status(actual, expected, testNum++);
        
        rawMaze = new char[][] {{'$','G','G','G','$'},
								{'G','*','$','*','G'},
								{'G','$','S','$','G'},
								{'E','*','$','*','G'},
								{'$','G','G','G','$'}}; 
		System.out.println("Parameter rawMaze = {'$','G','G','G','$'}\n"
				+ "		    {'G','*','$','*','G'}\n" + 
				"		    {'G','$','S','$','G'}\n" + 
				"		    {'E','*','$','*','G'}\n" + 
				"		    {'$','G','G','G','$'}");
		actual = "" + Recursive.canEscapeMaze(rawMaze);
		expected = "2";
		status(actual, expected, testNum++);
    }
    
    private static void status(String act, String exp, int test) {
    	String res = exp.equals(act)? "Passed": "Failed";
    	System.out.println("Actual: " + act);
    	System.out.println("Expected: " + exp);
        System.out.println("Test " + test + ": " + res);
        System.out.println();
    }
}