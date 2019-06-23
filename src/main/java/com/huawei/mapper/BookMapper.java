package com.huawei.mapper;

import com.huawei.entity.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookMapper {
    int add(Book book);
    int update(Book book);
    Book findById(Integer id);

    int deleteByIds(@Param("ids") Integer[] ids);

    List<Book> findAll();

    List<Book> findByNameLike(String name);
}
