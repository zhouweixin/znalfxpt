package com.huawei.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

@ApiModel(description = "用户")
public class Book {
    @ApiModelProperty(value = "主键:自增长")
    private Integer id;

    @ApiModelProperty(value = "名称")
    @NotNull(message = "书名称不可为空")
    private String name;

    @ApiModelProperty(value = "价格")
    private Double price;

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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
