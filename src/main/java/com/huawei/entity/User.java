package com.huawei.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "user")
@ApiModel(description = "用户")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "主键:自增长")
    private Integer id;

    @NotNull(message = "姓名不能为空")
    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "年龄")
    @NotNull(message = "年龄不能为空")
    private Integer age;

    @ApiModelProperty(value = "性别")
    private Integer sex;

    @ManyToOne(targetEntity = Department.class)
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    private Department department;

    @ApiModelProperty(value = "图片id")
    private Long imageId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }
}
