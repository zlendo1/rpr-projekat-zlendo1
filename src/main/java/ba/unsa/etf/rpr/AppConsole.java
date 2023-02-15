package ba.unsa.etf.rpr;

import org.apache.commons.cli.*;

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

}
