package com.huawei.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
@ApiModel(description = "案例-种类")
public class IssueType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "主键:自增长")
    private Long id;

    @ApiModelProperty(value = "案例外键")
    @ManyToOne(targetEntity = Issue.class)
    @JoinColumn(name = "issue_id", referencedColumnName = "id")
    private Issue issue;

    @ApiModelProperty(value = "各类外键")
    @ManyToOne(targetEntity = Type.class)
    @JoinColumn(name = "type_id", referencedColumnName = "id")
    private Type type;

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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
