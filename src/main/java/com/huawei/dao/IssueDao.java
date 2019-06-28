package com.huawei.dao;

import com.huawei.entity.Department;
import com.huawei.entity.Issue;
import com.huawei.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IssueDao extends JpaRepository<Issue, Long> {

    /**
     * 通过id批量删除
     *
     * @param ids
     */
    void deleteByIdIn(List<Integer> ids);

//    @Query(value = "", nativeQuery = true)
//    List<Issue> findByDomainIdAndTypeId(Integer domainId, Integer typeId);
}
