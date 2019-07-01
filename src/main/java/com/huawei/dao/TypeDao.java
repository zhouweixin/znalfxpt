package com.huawei.dao;

import com.huawei.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TypeDao extends JpaRepository<Type, Integer> {

    /**
     * 通过id批量删除
     *
     * @param ids
     */
    void deleteByIdIn(List<Integer> ids);

    /**
     * 通过名称查询
     *
     * @param name 名称
     * @return type
     */
    Type findByName(String name);
}
