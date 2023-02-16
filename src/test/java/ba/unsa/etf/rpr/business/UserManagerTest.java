package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exception.DBHandleException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class UserManagerTest {

    private UserManager userManager;
    private User user;
    private List<User> users;

    @BeforeEach
    void setUp() {
        userManager = Mockito.mock(UserManager.class);

        user = new User(1, "zlendo1", "haha22", "Zijad", "Lendo");

        users = new ArrayList<>();

        users.addAll(Arrays.asList(
                new User(1, "zlendo1", "haha22", "Zijad", "Lendo"),
                new User(2, "mpuzic1", "stacukucitakorano", "Meho", "Puzic")
        ));
    }

    @Test
    void findUser() throws DBHandleException {
        when(DaoFactory.userDao().getByUsername(anyString())).thenReturn(
                users.stream().filter(cat -> Objects.equals(cat.getUsername(), anyString())).findAny().orElse(null)
        );

        User newUser = userManager.findUser("zlendo1", "haha22");

        assertEquals(newUser, user);
    }

    @Test
    void existsUser() throws DBHandleException {
        when(DaoFactory.userDao().getByUsername(anyString())).thenReturn(
                users.stream().filter(cat -> Objects.equals(cat.getUsername(), anyString())).findAny().orElse(null)
        );

        assertTrue(userManager.existsUser("zlendo1"));
    }

    @Test
    void createUser() throws DBHandleException {
        when(DaoFactory.userDao().add(any())).thenReturn(
                users.add(any()) ? any() : null
        );

        userManager.createUser("tzdravkovic1", "ocigankomoja", "Toma", "Zdravkovic");

        assertEquals(3, users.size());
        Mockito.verify(userManager).createUser("tzdravkovic1", "ocigankomoja", "Toma", "Zdravkovic");
    }
}