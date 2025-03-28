import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import student.TestCase;
import student.TestableRandom;

/**
 * Helps test the code on a larger scale by referncing the instructor given
 * input and output files to the output of the program.
 * 
 * @author Richmond Southall & Martin hamzai
 * @version 3-23-2025
 */
public class ProblemSpecTest
    extends TestCase
{
    /**
     * Sets up the tests that follow. In general, used for initialization
     */
    public void setUp()
    {
        // Nothing here
    }


    /**
     * Read contents of a file into a string
     * 
     * @param path
     *            File name
     * @return the string
     * @throws IOException
     */
    static String readFile(String path)
        throws IOException
    {
        System.out.println(Paths.get(path));
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded);
    }


    /**
     * Example 1: This method runs on a command sample IO file You will write
     * similar test cases using different text files
     * 
     * @throws IOException
     */
    public void testPostedSample1()
        throws IOException
    {
        // Setting up all the parameters
        String[] args = new String[1];
        args[0] = "SyntaxTest1.txt";

        // Invoke main method of our Points Project
        PointsProject.main(args);

        // Actual output from your System console
        String output = systemOut().getHistory();

        // Expected output from file
        String referenceOutput = readFile("SyntaxTest1Out.txt");

        assertFuzzyEquals(referenceOutput, output);
    }


    /**
     * Example 2: This method runs on a command sample IO file You will write
     * similar test cases using different text files
     * 
     * @throws IOException
     */
    public void testPostedSample2()
        throws IOException
    {
        // Setting up all the parameters
        String[] args = new String[1];
        args[0] = "SyntaxTest2.txt";

        // Set testableRandom for output
        // Your SkipList from P1 presumably calculates level with booleans
        boolean[] randomBools = new boolean[] { false, // p_p - level 1
            false, // poi - level 1
            true, false, // p_42 - level 2
            true, true, true, false // far - level 4
        };
        TestableRandom.setNextBooleans(randomBools);

        // Set testableRandom for output
        // The reference implementation calculates randomLevel with ints now
        // Don't feel like you have to change your code, this code block should
        // allow the tests to still pass
        // 1 maps to false and 0 maps to true for any future tests you create
        int[] randomInts = new int[] { 1, // p_p - level 1
            1, // poi - level 1
            0, 1, // p_42 - level 2
            0, 0, 0, 1, // far - level 4
        };
        TestableRandom.setNextInts(randomInts);

        // Invoke main method of our Points Project
        PointsProject.main(args);

        // Actual output from your System console
        String output = systemOut().getHistory();

        // Expected output from file
        String referenceOutput = readFile("SyntaxTest2Out.txt");

        // Compare the two outputs
        // once you have implemented your project
        assertEquals(referenceOutput, output);
    }


    /**
     * Example 3: Checks if invalid file or no file given
     * 
     * @throws IOException
     */
    public void testPostedSample3()
        throws IOException
    {
        String[] args = new String[2];
        args[0] = "";
        PointsProject.main(args);
        assertEquals(
            systemOut().getHistory(),
            "Usage: PointsProject <command-file>\n");
        systemOut().clearHistory();

        String[] args2 = new String[1];
        args2[0] = "test";
        PointsProject.main(args2);
        assertEquals(
            systemOut().getHistory(),
            "There is no such input file as |test|\n");
    }
}
