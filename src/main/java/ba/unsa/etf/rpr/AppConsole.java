package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.domain.Exam;
import org.apache.commons.cli.*;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    private static void printFormattedOptions(Options options) {
        HelpFormatter helpFormatter = new HelpFormatter();
        PrintWriter printWriter = new PrintWriter(System.out);

        helpFormatter.printUsage(printWriter, 150, "java -jar rpr-projekat-zlendo1.jar [option] 'something else if needed' ");
        helpFormatter.printOptions(printWriter, 150, options, 2, 7);

        printWriter.close();
    }

    private static Options addOptions() {
        Options options = new Options();

        options.addOption(addCourse);
        options.addOption(addExam);
        options.addOption(getExams);

        return options;
    }

    private static List<Exam> searchExams(List<Exam> listOfExams, String courseName, Date examDate) {
        List<Exam> exams = new ArrayList<>(listOfExams);

        if (!courseName.isEmpty()) {
            exams = exams.stream().filter(cat -> cat.getCourse().getName().equals(courseName)).toList();
        }

        if (examDate != null) {
            exams = exams.stream().filter(cat -> cat.getExamTime().equals(examDate)).toList();
        }

        return exams;
    }

    public static void main(String[] args) {

    }

}
