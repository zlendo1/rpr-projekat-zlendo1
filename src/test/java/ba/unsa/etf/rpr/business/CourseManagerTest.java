package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.CourseDaoSQLImpl;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Course;
import ba.unsa.etf.rpr.exception.DBHandleException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockedStatic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class CourseManagerTest {

    private CourseManager courseManager;
    private Course course;
    private CourseDaoSQLImpl courseDaoSQLMock;
    private List<Course> courses;

    @BeforeEach
    void setUp() {
        courseManager = Mockito.mock(CourseManager.class);

        course = new Course(1, "Blizzard API 101", "Saban Saulic");

        courseDaoSQLMock = Mockito.mock(CourseDaoSQLImpl.class);

        courses = new ArrayList<>();

        courses.addAll(Arrays.asList(
                new Course(1, "Blizzard API 101", "Saban Saulic"),
                new Course(2, "Kore od banana II", "Vojislav Seselj"),
                new Course(3, "Visoki eksplozivi", "Salko Dinamitas")
        ));
    }

    @Test
    void createCourse() throws DBHandleException {
        courseManager.createCourse("Osnove elektrotehnike", "Ramo Legenda");

        Mockito.verify(courseManager).createCourse("Osnove elektrotehnike", "Ramo Legenda");
    }

    @Test
    void createExistingCourse() throws DBHandleException {
        MockedStatic<DaoFactory> daoFactoryMockedStatic = Mockito.mockStatic(DaoFactory.class);

        daoFactoryMockedStatic.when(DaoFactory::courseDao).thenReturn(courseDaoSQLMock);
        when(DaoFactory.courseDao().getAll()).thenReturn(courses);
        Mockito.doCallRealMethod().when(courseManager).createCourse(course.getName(), course.getProfessor());

        assertThrows(DBHandleException.class, () -> {
            courseManager.createCourse(course.getName(), course.getProfessor());
        });

        daoFactoryMockedStatic.verify(DaoFactory::courseDao);
        Mockito.verify(courseManager).createCourse(course.getName(), course.getProfessor());

        daoFactoryMockedStatic.close();
    }
}