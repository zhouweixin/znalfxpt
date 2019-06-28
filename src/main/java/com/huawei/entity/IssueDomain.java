package com.huawei.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
@Table(name = "issue_domain")
@ApiModel(description = "案例-领域")
public class IssueDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "主键:自增长")
    private Long id;

    @ApiModelProperty(value = "案例外键")
    @ManyToOne(targetEntity = Issue.class)
    @JoinColumn(name = "issue_id", referencedColumnName = "id")
    private Issue issue;

    @ApiModelProperty(value = "领域外键")
    @ManyToOne(targetEntity = Domain.class)
    @JoinColumn(name = "domain_id", referencedColumnName = "id")
    private Domain domain;

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

    public Domain getDomain() {
        return domain;
    }

    public void setDomain(Domain domain) {
        this.domain = domain;
    }
}
