import java.io.File;
import java.io.IOException;
import java.util.Scanner;
//On my honor:
//- I have not used source code obtained from another student,
//or any other unauthorized source, either modified or unmodified.
//- All source code and documentation used in my program is
//either my original work, or was derived by me from the
//source code published in the textbook for this course.
//- I have not discussed coding details about this project with
//anyone other than the instructor, ACM/UPE tutors or the TAs assigned
//to this course. I understand that I may discuss the concepts
//of this program with other students, and that another student
//may help me debug my program so long as neither of us writes
//anything during the discussion or modifies any computer file
//during the discussion. I have violated neither the spirit nor
//letter of this restriction.
// Richmond Southall, Martin Hamzai
/**
 * Main for CS3114 Quadtree/SkipList Point project (CS3114 Spring 2016 Project
 * 2). Usage: java PointsProject <command-file>
 *
 * @author CS Staff
 * @version February, 2016
 */
public class PointsProject
{
    /**
     * Main: Process input parameters and invoke command file processor
     *
     * @param args
     *            The command line parameters
     * @throws IOException
     */
    public static void main(String[] args)
        throws IOException
    {
        if (args.length != 1)
        {
            System.out.println("Usage: PointsProject <command-file>");
            return;
        }

        String commandFile = args[0].trim();
        // System.out.println("Working on file " + commandFile);
        File theFile = new File(commandFile);
        if (!theFile.exists())
        {
            System.out.println(
                "There is no such input file as |" + commandFile + "|");
            return;
        }
        Scanner scanner = new Scanner(theFile);
        Database myWorld = new Database();
        CommandProcessor processor = new CommandProcessor(myWorld);

        while (scanner.hasNextLine())
        {
            String line = scanner.nextLine();
            // determines if the file has more lines to read
            if (!line.trim().isEmpty())
            {
                processor.processor(line.trim());
            }
        }
        //scanner.close();

    }
}
