package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.dao.UserDaoSQLImpl;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exception.DBHandleException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserManagerTest {

    private UserManager userManager;
    private User user;
    private UserDaoSQLImpl userDaoSQLMock;
    private List<User> users;
    private MockedStatic<DaoFactory> daoFactoryMockedStatic;

    @BeforeEach
    void setUp() throws DBHandleException {
        userManager = Mockito.mock(UserManager.class);

        user = new User(1, "zlendo1", "haha22", "Zijad", "Lendo");

        userDaoSQLMock = mock(UserDaoSQLImpl.class);

        users = new ArrayList<>();

        users.addAll(Arrays.asList(
                new User(1, "zlendo1", "haha22", "Zijad", "Lendo"),
                new User(2, "mpuzic1", "stacukucitakorano", "Meho", "Puzic")
        ));

        daoFactoryMockedStatic = Mockito.mockStatic(DaoFactory.class);

        daoFactoryMockedStatic.when(DaoFactory::userDao).thenReturn(userDaoSQLMock);

        when(userDaoSQLMock.getByUsername(anyString())).thenAnswer(invocationOnMock -> {
            String username = invocationOnMock.getArgument(0);

            return users.stream().filter(cat -> Objects.equals(cat.getUsername(), anyString())).findAny().orElse(null);
        });

        when(userDaoSQLMock.add(any())).thenAnswer(invocationOnMock -> {
            User newUser = invocationOnMock.getArgument(0);

            users.add(newUser);

            return newUser;
        });
    }

    @Test
    void findUser() throws DBHandleException {
        User newUser = userManager.findUser("zlendo1", "haha22");

        assertEquals(newUser, user);
    }

    @Test
    void existsUser() throws DBHandleException {
        assertTrue(userManager.existsUser("zlendo1"));
    }

    @Test
    void createUser() throws DBHandleException {
        userManager.createUser("tzdravkovic1", "ocigankomoja", "Toma", "Zdravkovic");

        assertEquals(3, users.size());
        Mockito.verify(userManager).createUser("tzdravkovic1", "ocigankomoja", "Toma", "Zdravkovic");
    }

    @AfterEach
    void closeStaticMock() {
        daoFactoryMockedStatic.close();
    }

}