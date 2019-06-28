package com.huawei.dao;

import com.huawei.entity.Domain;
import com.huawei.entity.Issue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DomainDao extends JpaRepository<Domain, Integer> {

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
     * @return domain
     */
    Domain findByName(String name);
}
