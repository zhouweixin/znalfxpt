package com.huawei.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
@Table(name = "process_issue")
@ApiModel(description = "流程-案例")
public class ProcessIssue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "主键:自增长")
    private Long id;

    @ApiModelProperty(value = "案例外键")
    @ManyToOne(targetEntity = Issue.class)
    @JoinColumn(name = "issue_id", referencedColumnName = "id")
    private Issue issue;

    @ApiModelProperty(value = "领域外键")
    @ManyToOne(targetEntity = Process.class)
    @JoinColumn(name = "process_id", referencedColumnName = "id")
    private Process process;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Issue getIssue() {
        return issue;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }

    public Process getProcess() {
        return process;
    }

    public void setProcess(Process process) {
        this.process = process;
    }
}
