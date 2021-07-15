package com.example.userservice.pact;


import au.com.dius.pact.provider.junit.IgnoreNoPactsToVerify;
import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.loader.PactFolder;
import au.com.dius.pact.provider.junit.target.HttpTarget;
import au.com.dius.pact.provider.junit.target.Target;
import au.com.dius.pact.provider.junit.target.TestTarget;
import au.com.dius.pact.provider.spring.SpringRestPactRunner;
import com.example.userservice.domain.User;
import com.example.userservice.logic.dao.UserAlreadyExistsException;
import com.example.userservice.logic.dao.UserDao;
import com.example.userservice.logic.dao.UserNotFoundException;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;

import static org.mockito.Mockito.*;

@RunWith(SpringRestPactRunner.class)
@PactFolder("C:\\Users\\Igal\\Desktop\\user-service-consumer\\user-service-consumer\\target\\pacts")
//@PactBroker(...)
@Provider("UserServiceProviderHttp")
@IgnoreNoPactsToVerify
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        properties = {"server.port=9514"}
)

public class UserServicePactProviderHttpTest {

    @MockBean
    UserDao userDao;


    @TestTarget
    public final Target target = new HttpTarget(9514);


    @After
    public void cleanUp() {
    }

    @Before
    public void setUp() {
    }


    @State("")
    public void emptyState() {
        System.out.println("preparing nothing");
    }

    @State("user 9876 already exists")
    public void userExists9876() {
        mockUser(9876);
    }

    @State("user 1111 already exists")
    public void userExists1111() {
        mockUser(1111);
    }

    @State("user 9999 does NOT  exists")
    public void userNotExists() {
        when(userDao.get("9999")).thenThrow(new UserNotFoundException("9999"));
    }


    private void mockUser(int userId) {
        User user = new User(userId, "first", "last");
        String userIdStr = Integer.toString(userId);
        doThrow(new UserAlreadyExistsException(userIdStr)).when(userDao).save(user);
        doNothing().when(userDao).delete(userIdStr);
        when(userDao.get(userIdStr)).thenReturn(user);
        when(userDao.getAll()).thenReturn(Collections.singletonList(user));
    }


}
