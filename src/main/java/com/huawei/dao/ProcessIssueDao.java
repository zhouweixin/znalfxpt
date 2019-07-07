package com.huawei.dao;

import com.huawei.entity.Process;
import com.huawei.entity.ProcessIssue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProcessIssueDao extends JpaRepository<ProcessIssue, Long> {
    List<ProcessIssue> findByProcess(Process process);
}
