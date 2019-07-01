package com.huawei;

import com.huawei.entity.Department;
import com.huawei.entity.User;
import com.huawei.global.ExceptionEnum;
import com.huawei.global.ExceptionUtil;
import junit.framework.TestCase;

import java.util.Optional;

public class MyTest extends TestCase {
    public void testFun1(){

        User user = new User();
        Department d1 = new Department();
        d1.setName("研发部");
        user.setDepartment(d1);

        Optional<User> optional = Optional.ofNullable(user);

        // 1.传统方法
        if(user != null){
            Department department = user.getDepartment();
            if(department != null){
                String name = department.getName();
                if(name != null) {
                    System.out.println(name);
                } else {
                    System.out.println("");
                }
            }
        }

        // 2.java8推荐的optional的写法
        String name = optional.map((u) -> u.getDepartment()).map((d) -> d.getName()).orElse("");
        System.out.println(name);

        // 抛出异常，方式1：
        if(optional.isPresent() == false){
            throw ExceptionUtil.newInstance(ExceptionEnum.UPDATE_FAIL_NOT_EXISTS);
        }

        // 抛出异常，方式2：
        optional.orElseThrow(() -> ExceptionUtil.newInstance(ExceptionEnum.UPDATE_FAIL_NOT_EXISTS));
    }
}
