package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.CourseDaoSQLImpl;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Course;
import ba.unsa.etf.rpr.exception.DBHandleException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockedStatic;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class CourseManagerTest {

    private CourseManager courseManager;
    private Course course;
    private CourseDaoSQLImpl courseDaoSQLMock;
    private List<Course> courses;
    MockedStatic<DaoFactory> daoFactoryMockedStatic;

    @BeforeEach
    void setUp() throws DBHandleException {
        courseManager = Mockito.mock(CourseManager.class);

        course = new Course(1, "Blizzard API 101", "Saban Saulic");

        courseDaoSQLMock = Mockito.mock(CourseDaoSQLImpl.class);

        courses = new ArrayList<>();

        courses.addAll(Arrays.asList(
                new Course(1, "Blizzard API 101", "Saban Saulic"),
                new Course(2, "Kore od banana II", "Vojislav Seselj"),
                new Course(3, "Visoki eksplozivi", "Salko Dinamitas")
        ));

        daoFactoryMockedStatic = Mockito.mockStatic(DaoFactory.class);

        daoFactoryMockedStatic.when(DaoFactory::courseDao).thenReturn(courseDaoSQLMock);

        when(courseDaoSQLMock.getAll()).thenReturn(courses);

        when(courseDaoSQLMock.searchByName(anyString())).thenAnswer(invocationOnMock -> {
            String name = invocationOnMock.getArgument(0);

            return courses.stream().filter(cat -> Objects.equals(cat.getName(), name)).findAny().orElse(null);
        });

        when(courseDaoSQLMock.add(any())).thenAnswer(invocationOnMock ->  {
            Course course = invocationOnMock.getArgument(0);

            courses.add(course);

            return course;
        });
    }

    @Test
    void createCourse() throws DBHandleException {
        courseManager.createCourse("Osnove elektrotehnike", "Ramo Legenda");

        assertEquals(4, courses.size());
        Mockito.verify(courseManager).createCourse("Osnove elektrotehnike", "Ramo Legenda");
    }

    @Test
    void createExistingCourse() throws DBHandleException {
        assertThrows(DBHandleException.class, () -> {
            courseManager.createCourse(course.getName(), course.getProfessor());
        });

        daoFactoryMockedStatic.verify(DaoFactory::courseDao);
        Mockito.verify(courseManager).createCourse(course.getName(), course.getProfessor());

        daoFactoryMockedStatic.close();
    }

    @AfterEach
    void closeStaticMock() {
        daoFactoryMockedStatic.close();
    }

}