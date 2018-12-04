package org.patentminer.service.impl;

import org.junit.Test;
import org.patentminer.model.User;
import org.patentminer.service.UserService;

public class UserServiceImplTest {

    private UserService userService;

    public UserServiceImplTest() {
        userService = new UserServiceImpl();
    }

    @Test
    public void testFindById() {
        User user = userService.findById(1);
        if (user != null) {
            System.out.println(user);
        } else {
            System.out.println("id is not exists.");
        }
    }
}