package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.dao.ExamDaoSQLImpl;
import ba.unsa.etf.rpr.domain.Course;
import ba.unsa.etf.rpr.domain.Exam;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exception.DBHandleException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ExamManagerTest {

    private ExamManager examManager;
    private Exam exam;
    private ExamDaoSQLImpl examDaoSQLMock;
    private List<Exam> exams;
    private User user;
    private Course course;
    private Date date;

    private Date localDateToDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    private LocalDate dateToLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    @BeforeEach
    void setUp() {
        course = new Course(1, "Resavanje I", "Baka Prase");
        user = new User(1, "admin", "admin", "admin", "admin");
        date = localDateToDate(LocalDate.of(2022, 2, 15));

        examManager = Mockito.mock(ExamManager.class);

        exam = new Exam(1, user, course, date, "225883 nazovi moj broj");

        examDaoSQLMock = Mockito.mock(ExamDaoSQLImpl.class);

        exams = new ArrayList<>();

        exams.addAll(Arrays.asList(
                new Exam(1, user, course, date, "225883 nazovi moj broj"),
                new Exam(2, user, course, localDateToDate(LocalDate.of(2022, 2, 16)), "okreni moj broj ti zivote moj")
        ));
    }

    @Test
    void searchExamExistantNone() throws DBHandleException {
        when(DaoFactory.examDao().getAll()).thenReturn(exams);

        List<Exam> examList = examManager.searchExam("", null);

        assertEquals(exams, examList);
    }

    @Test
    void searchExamExistantCourse() throws DBHandleException {
        when(DaoFactory.examDao().getAll()).thenReturn(exams);

        List<Exam> examList = examManager.searchExam("Resavanje I", null);

        assertEquals(exams, examList);
    }

    @Test
    void searchExamExistantDate() throws DBHandleException {
        when(DaoFactory.examDao().getAll()).thenReturn(exams);

        List<Exam> examList = examManager.searchExam("", dateToLocalDate(date));

        assertEquals(exam, examList.listIterator().next());
    }

    @Test
    void searchExamExistantBoth() throws DBHandleException {
        when(DaoFactory.examDao().getAll()).thenReturn(exams);

        List<Exam> examList = examManager.searchExam("Resavanje I", dateToLocalDate(date));

        assertEquals(exam, examList.listIterator().next());
    }

    @Test
    void searchExamNonExistant() throws DBHandleException {
        when(DaoFactory.examDao().getAll()).thenReturn(exams);

        List<Exam> examList = examManager.searchExam("Resavanje II", null);

        assertTrue(examList.isEmpty());
    }

    @Test
    void createExam() throws DBHandleException {
        examManager.createExam("Resavanje I", user, dateToLocalDate(date), "Ti me dizes, ti me dizes iz kome");

        Mockito.verify(examManager).createExam("Resavanje I", user, dateToLocalDate(date), "Ti me dizes, ti me dizes iz kome");
    }
    
}