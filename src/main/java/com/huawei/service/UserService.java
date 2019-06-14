package com.huawei.service;

import com.huawei.dao.UserDao;
import com.huawei.entity.User;
import com.huawei.global.ExceptionEnum;
import com.huawei.global.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public User add(User user) {
        if(user.getId() != null && findById(user.getId()) != null){
            throw ExceptionUtil.newInstance(ExceptionEnum.ADD_FAIL_EXISTS);
        }

        return userDao.save(user);
    }

    public List<User> findAll() {
        return userDao.findAll();
    }

    public User findById(int id) {
        Optional<User> optional = userDao.findById(id);
        return optional.orElse(null);
    }
}
