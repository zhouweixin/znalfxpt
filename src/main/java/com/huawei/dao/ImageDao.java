package com.huawei.dao;

import com.huawei.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 18:06 2018/6/6
 * @Modified By:
 */
@Repository
public interface ImageDao extends JpaRepository<Image, Long> {
}
