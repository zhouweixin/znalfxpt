package com.huawei.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
@Table(name = "question_keyword")
@ApiModel(description = "问题-关键词")
public class QuestionKeyword {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "主键:自增长")
    private Long id;

    @ApiModelProperty(value = "问题外键")
    @ManyToOne(targetEntity = Question.class)
    @JoinColumn(name = "question_id", referencedColumnName = "id")
    private Question question;

    @ApiModelProperty(value = "关键字外键")
    @ManyToOne(targetEntity = Keyword.class)
    @JoinColumn(name = "keyword_id", referencedColumnName = "id")
    private Keyword keyword;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Keyword getKeyword() {
        return keyword;
    }

    public void setKeyword(Keyword keyword) {
        this.keyword = keyword;
    }
}
