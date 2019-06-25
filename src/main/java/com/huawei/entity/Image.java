package com.huawei.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Arrays;

/**
 * @Author: zhouweixin
 * @Description: 图片
 * @Date: Created in 18:04 2018/6/6
 * @Modified By:
 */
@Entity
@ApiModel(description="图片")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "主键:自增长")
    private Long id;

    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    @ApiModelProperty(value = "数据")
    private byte[] data;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
