package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.business.CourseManager;
import ba.unsa.etf.rpr.business.ExamManager;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Course;
import ba.unsa.etf.rpr.domain.Exam;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exception.DBHandleException;

import org.apache.commons.cli.*;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

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

    private static Date parseDate(String dateString) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        return sdf.parse(dateString);
    }

    public static void main(String[] args) throws Exception {
        User user = null;

        try {
            user = DaoFactory.userDao().getByUsername("admin");
        } catch (DBHandleException e) {
            System.out.println("Admin account somehow non-existant. Please contact a higher-level administrator or fix it yourself.");
        }

        Options options = addOptions();

        CommandLineParser commandLineParser = new DefaultParser();
        CommandLine cl = commandLineParser.parse(options, args);

        if (cl.hasOption(addExam.getOpt()) || cl.hasOption(addExam.getLongOpt())) {
            Course course = null;

            if (cl.getArgList().size() != 3) {
                System.out.println("Must have three arguments: the name of a course, the date of the exam and the answer sheet.");
            }

            try {
                course = DaoFactory.courseDao().searchByName(cl.getArgList().get(0));
            } catch (DBHandleException e) {
                System.out.println("Course not defined. Please try again.");

                System.exit(1);
            }

            Exam exam = new Exam();

            exam.setUser(user);
            exam.setCourse(course);

            try {
                exam.setExamTime(parseDate(cl.getArgList().get(1)));
            } catch (ParseException e) {
                System.out.println("Date format incorrect. Must be dd/MM/yyyy. Please try again.");

                System.exit(1);
            }

            exam.setAnswerSheet(cl.getArgList().get(2));

            DaoFactory.examDao().add(exam);

            System.out.println("Exam successfully added to the database.");

        } else if (cl.hasOption(getExams.getOpt()) || cl.hasOption(getExams.getLongOpt())) {
            ExamManager examManager = new ExamManager();

            examManager.getAll().forEach(q -> System.out.println(q.getAnswerSheet() + "\n"));

        } else if (cl.hasOption(addCourse.getOpt()) || cl.hasOption(addCourse.getLongOpt())) {
            CourseManager courseManager = new CourseManager();

            if (cl.getArgList().size() != 2) {
                System.out.println("Course addition must have two arguments: the name of the course and the name of the professor. Please try again.");

                System.exit(1);
            }

            try {
                courseManager.createCourse(cl.getArgList().get(0), cl.getArgList().get(1));
            } catch (DBHandleException e) {
                System.out.println("Course with the same name already exists. Please try again.");

                System.exit(1);
            }

            System.out.println("Course successfully added to the database.");

        } else {
            printFormattedOptions(options);

            System.exit(-1);
        }
    }

}
