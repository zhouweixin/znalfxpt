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

    @Query(value = "select * from process where isnull(process_id) limit 1", nativeQuery = true)
    Process findProcessRoot();

    @Modifying
    @Query(value = "update process set process_id=?1 where id=?2", nativeQuery = true)
    void updateProcessId(Integer processId, Integer id);

    @Query(value = "select id from process where process_id=?1", nativeQuery = true)
    List<Integer> findIdsByProcessId(Integer processId);

    @Query(value = "select process_id from process where id=?1", nativeQuery = true)
    Integer findProcessIdById(Integer id);

    @Modifying
    @Query(value = "update process set process_id=?1 where id in ?2", nativeQuery = true)
    void updateProcessIdByIdIn(Integer parentId, List<Integer> sonIds);

    @Modifying
    @Query(value = "delete from process where id=?1", nativeQuery = true)
    void deleteProcessById(Integer id);
}
