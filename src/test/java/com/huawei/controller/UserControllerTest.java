package com.huawei.controller;

import com.huawei.entity.User;
import com.huawei.global.Result;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserControllerTest {

    @Autowired
    private UserController userController;

    @Test
    public void add() {
    }

    @Test
    public void update() {
    }

    @Test
    public void deleteByIds() {
    }

    @Test
    public void findAll() {
    }

    @Test
    public void findAllByPage() {
    }

    @Test
    public void findById() {
        Result<User> result = userController.findById(1);
        User user = result.getData();
        Assert.assertTrue(user.getAge() == 18);
    }

    @Test
    public void findByNameLike() {
    }

    @Test
    public void findByNameLikeByPage() {
    }

    @Test
    public void updateNameById() {
    }
}