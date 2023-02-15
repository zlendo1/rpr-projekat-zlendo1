package ba.unsa.etf.rpr;

import org.apache.commons.cli.*;

import java.io.PrintWriter;

/**
 * Command line interface for our app.
 * Only available to administrators.
 * Automatically connects to the admin account.
 *
 */
public class AppConsole {

    private static final Option addCourse = new Option("c", "add-course", false, "Adding course to the database");
    private static final Option addExam = new Option("e", "add-exam", false, "Adding exam to the database");
    private static final Option getExams = new Option("getE", "get-exams", false, "Printing all exam solutions from the database");

    public static void printFormattedOptions(Options options) {
        HelpFormatter helpFormatter = new HelpFormatter();
        PrintWriter printWriter = new PrintWriter(System.out);

        helpFormatter.printUsage(printWriter, 150, "java -jar rpr-projekat-zlendo1.jar [option] 'something else if needed' ");
        helpFormatter.printOptions(printWriter, 150, options, 2, 7);

        printWriter.close();
    }

}