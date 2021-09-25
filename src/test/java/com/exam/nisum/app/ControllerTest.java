package com.exam.nisum.app;

import com.exam.nisum.app.dao.IUserDao;
import com.exam.nisum.app.entity.User;
import com.exam.nisum.app.services.UserServiceImpl;
import com.exam.nisum.app.utils.Util;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ControllerTest {

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @Mock
    private IUserDao userDao;

    @BeforeEach
    public void beforeEachTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveUser() {
        User user = new User();
        Mockito.when(userDao.save(new User())).thenReturn(user);
        assertNotNull(userServiceImpl.save(user));
    }

    @Test
    public void testUserNull() {
        User user = new User();
        user.setNname("Jimmy");
        user.setActive(true);
        user.setCreated(new Date());

        Mockito.when(userDao.save(new User())).thenReturn(null);
        assertNull(userServiceImpl.save(user));
    }


    @Test
    public void testCorrectEmail() {
        User user = new User();
        user.setNname("Jimmy");
        user.setActive(true);
        user.setCreated(new Date());
        user.setEmail("jimmyvaldez@");

        assertFalse(Util.isPasswordCorrect(user.getEmail()));
    }

}
