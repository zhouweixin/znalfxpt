package com.huawei.dao;

import com.huawei.entity.Process;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProcessDao extends JpaRepository<Process, Integer> {

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
    List<Process> findByNameLike(String name);

    /**
     * 通过姓名查询-分页
     *
     * @param name
     * @param pageable
     * @return
     */
    Page<Process> findByNameLike(String name, Pageable pageable);


    @Query(value = "select * from process where isnull(process_id)", nativeQuery = true)
    List<Process> findByParentNull();
}
