package com.huawei.dao;

import com.huawei.entity.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DepartmentDao extends JpaRepository<Department, Integer> {
    /**
     * 通过id更新name
     *
     * @param name
     * @param id
     */
    @Modifying
    @Query(value = "update Department u set u.name=?1 where u.id=?2")
    void updateNameById(String name, int id);

    /**
     * 通过id批量删除
     *
     * @param ids
     */
    void deleteByIdIn(List<Integer> ids);

    /**
     * 通过姓名模糊查询
     *
     * @param name
     * @return
     */
    List<Department> findByNameLike(String name);

    /**
     * 通过姓名查询-分页
     *
     * @param name
     * @param pageable
     * @return
     */
    Page<Department> findByNameLike(String name, Pageable pageable);
}
