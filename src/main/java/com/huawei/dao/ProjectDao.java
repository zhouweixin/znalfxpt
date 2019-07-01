package com.huawei.dao;

import com.huawei.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectDao extends JpaRepository<Project, Integer> {

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
    Project findByName(String name);
}
