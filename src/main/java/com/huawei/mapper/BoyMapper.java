package com.huawei.mapper;

import com.huawei.entity.Boy;
import com.huawei.entity.BoyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;

@Repository
public interface BoyMapper {
    long countByExample(BoyExample example);

    int deleteByExample(BoyExample example);

    int insert(Boy record);

    int insertSelective(Boy record);

    List<Boy> selectByExample(BoyExample example);

    int updateByExampleSelective(@Param("record") Boy record, @Param("example") BoyExample example);

    int updateByExample(@Param("record") Boy record, @Param("example") BoyExample example);
}