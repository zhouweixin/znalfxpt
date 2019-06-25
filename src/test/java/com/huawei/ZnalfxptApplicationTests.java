package com.huawei;

import com.huawei.entity.Boy;
import com.huawei.entity.BoyExample;
import com.huawei.mapper.BoyMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ZnalfxptApplicationTests {

    @Autowired
    private BoyMapper boyMapper;

    @Test
    public void testFindById() {
        BoyExample boyExample = new BoyExample();

        BoyExample.Criteria criteria = boyExample.createCriteria();
        criteria.andIdEqualTo(2);

        List<Boy> boys = boyMapper.selectByExample(boyExample);
        for (Boy boy : boys) {
            System.out.println(boy);
        }
    }

    @Test
    public void testFindByNameLikeAge() {
        BoyExample boyExample = new BoyExample();

        BoyExample.Criteria criteria = boyExample.createCriteria();

        criteria.andNameLike("%" + "小" + "%");
        criteria.andMyAgeGreaterThan(10);

        List<Boy> boys = boyMapper.selectByExample(boyExample);
        for (Boy boy : boys) {
            System.out.println(boy);
        }
    }

}
