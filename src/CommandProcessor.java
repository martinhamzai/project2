/**
 * The purpose of this class is to parse a text file into its appropriate, line
 * by line commands for the format specified in the project spec.
 * 
 * @author Martin Hamzai and Richmond Southall
 * @version 2025-03-23
 */
public class CommandProcessor
{

    // the database object to manipulate the commands that the command processor
    // feeds to it
    private Database data;

    /**
     * The constructor for the command processor requires a database instance to
     * exist, so the only constructor takes a database class object to feed
     * commands to.
     * 
     * @param database
     *            the database being used
     */
    public CommandProcessor(Database database)
    {
        this.data = database;
    }


    /**
     * This method parses keywords in the line and calls methods in the database
     * as required. Each line command will be specified by one of the keywords
     * to perform the actions. These actions are performed on specified objects
     * and include insert, remove, regionsearch, duplicates, search, and dump.
     * This processor method is called for each line in the file. Note that the
     * methods called will themselves write to the console, this method does
     * not, only calling methods that do.
     * 
     * @param line
     *            a single line from the text file
     */
    public void processor(String line)
    {

        // converts the string of the line into an
        // array of its space (" ") delimited elements
        String[] arr = line.split("\\s{1,}");
        String command = arr[0]; // the command will be the first of these
                                 // elements

        // calls the insert function and passes the correct
        // parameters by converting the string integers into
        // their Integer equivalent, trimming the whitespace
        if (command.equals("insert"))
        {

            // Get x, y from arr into separate Integers
            Integer x = Integer.parseInt(arr[2]);
            Integer y = Integer.parseInt(arr[3]);

            // the point to store in the KVPair as the value
            Point p = new Point(x, y);

            // call insert to store the KVPair in the database
            // arr[1] is the key
            data.insert(new KVPair<String, Point>(arr[1], p));

        }

        // calls the appropriate remove method based on the
        // number of white space delimited strings in the line
        else if (command.equals("remove"))
        {
            // checks the number of white space delimited strings in the line
            int numParam = arr.length - 1;
            if (numParam == 1)
            {
                // Calls remove by name
                data.remove(arr[1]);
            }
            else if (numParam == 2)
            {
                // Calls remove by coordinate, converting string
                // integers into their Integer equivalent minus whitespace
                // Get x, y from arr into separate Integers
                Integer x = Integer.parseInt(arr[1]);
                Integer y = Integer.parseInt(arr[2]);
                Point p = new Point(x, y);
                data.remove(p);
            }
        }

        else if (command.equals("regionsearch"))
        {
            // calls the regionsearch method for a set of coordinates
            // the string integers in the line will be trimmed of whitespace
            // Get x, y, w, h from arr into separate Integers
            Integer x = Integer.parseInt(arr[1]);
            Integer y = Integer.parseInt(arr[2]);
            Integer w = Integer.parseInt(arr[3]);
            Integer h = Integer.parseInt(arr[4]);
            data.regionsearch(x, y, w, h);

        }

        else if (command.equals("duplicates"))
        {
            // calls the duplicates method, no parameters to be passed
            data.duplicates();

        }

        else if (command.equals("search"))
        {
            // calls the search method for a name of object
            data.search(arr[1]);

        }

        else if (command.equals("dump"))
        {
            // calls the dump method for the database, takes no parameters
            // (see the dump() JavaDoc in the Database class for more
            // information)
            data.dump();
        }
    }
}
