package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.dao.ExamDaoSQLImpl;
import ba.unsa.etf.rpr.domain.Course;
import ba.unsa.etf.rpr.domain.Exam;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exception.DBHandleException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ExamManagerTest {

    private ExamManager examManager;
    private Exam exam;
    private ExamDaoSQLImpl examDaoSQLMock;
    private List<Exam> exams;
    private MockedStatic<DaoFactory> daoFactoryMockedStatic;
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
    void setUp() throws DBHandleException {
        course = new Course(1, "Resavanje I", "Baka Prase");
        user = new User(1, "admin", "admin", "admin", "admin");
        date = localDateToDate(LocalDate.of(2022, 2, 15));

        examManager = Mockito.mock(ExamManager.class);

        exam = new Exam(1, user, course, date, "225883 nazovi moj broj");

        examDaoSQLMock = mock(ExamDaoSQLImpl.class);

        exams = new ArrayList<>();

        exams.addAll(Arrays.asList(
                new Exam(1, user, course, date, "225883 nazovi moj broj"),
                new Exam(2, user, course, localDateToDate(LocalDate.of(2022, 2, 16)), "okreni moj broj ti zivote moj")
        ));

        daoFactoryMockedStatic = Mockito.mockStatic(DaoFactory.class);

        daoFactoryMockedStatic.when(DaoFactory::examDao).thenReturn(examDaoSQLMock);

        when(examDaoSQLMock.getAll()).thenReturn(exams);

        when(examDaoSQLMock.add(any())).thenAnswer(invocationOnMock -> {
            Exam newExam = invocationOnMock.getArgument(0);

            exams.add(newExam);

            return newExam;
        });
    }

    @Test
    void searchExamExistantNone() throws DBHandleException {
        List<Exam> examList = examManager.searchExam("", null);

        assertEquals(exams, examList);
    }

    @Test
    void searchExamExistantCourse() throws DBHandleException {
        List<Exam> examList = examManager.searchExam("Resavanje I", null);

        assertEquals(exams, examList);
    }

    @Test
    void searchExamExistantDate() throws DBHandleException {
        List<Exam> examList = examManager.searchExam("", dateToLocalDate(date));

        assertEquals(exam, examList.get(0));
    }

    @Test
    void searchExamExistantBoth() throws DBHandleException {
        List<Exam> examList = examManager.searchExam("Resavanje I", dateToLocalDate(date));

        assertEquals(exam, examList.get(0));
    }

    @Test
    void searchExamNonExistant() throws DBHandleException {
        List<Exam> examList = examManager.searchExam("Resavanje II", null);

        assertTrue(examList.isEmpty());
    }

    @Test
    void createExam() throws DBHandleException {
        examManager.createExam("Resavanje I", user, dateToLocalDate(date), "Ti me dizes, ti me dizes iz kome");

        assertEquals(3, exams.size());
        Mockito.verify(examManager).createExam("Resavanje I", user, dateToLocalDate(date), "Ti me dizes, ti me dizes iz kome");
    }

    @AfterEach
    void closeStaticMock() {
        daoFactoryMockedStatic.close();
    }
    
}