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
import com.example.userservice.logic.dao.UserDao;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRestPactRunner.class)
@PactFolder("C:\\Users\\Igal\\Desktop\\user-service-consumer\\user-service-consumer\\target\\pacts")
@Provider("UserServiceProviderHttp")
@IgnoreNoPactsToVerify
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        properties = {"server.port=9514"}
)

public class UserServicePactProviderHttpTest {

    @Autowired
    private
    UserDao userDao;


    @TestTarget
    public final Target target = new HttpTarget(9514);

    private List<User> usersToDelete = new ArrayList<>();

    @After
    public void cleanUp() {
        usersToDelete.forEach(user -> userDao.delete(Integer.toString(user.getId())));
    }

    @Before
    public void setUp(){
        this.usersToDelete.clear();

    }


    @State("")
    public void emptyState() {
        System.out.println("preparing nothing");
    }

    @State("user 9876 already exists")
    public void userExists9876() {
        User user = new User(9876, "first", "last");
        userDao.save(user);
        usersToDelete.add(user);
    }

    @State("user 1111 already exists")
    public void userExists1111() {
        User user = new User(1111, "first", "last");
        userDao.save(user);
        this.usersToDelete.add(user);
    }

    @State("user 9999 does NOT  exists")
    public void userNotExists() {

    }


}
