package com.huawei.service;

import com.huawei.entity.Book;
import com.huawei.global.ExceptionEnum;
import com.huawei.global.ExceptionUtil;
import com.huawei.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookMapper bookMapper;

    public int add(Book book) {
        if (book.getId() != null && bookMapper.findById(book.getId()) != null) {
            throw ExceptionUtil.newInstance(ExceptionEnum.ADD_FAIL_EXISTS);
        }
        return bookMapper.add(book);
    }

    public int update(Book book) {
        if (book.getId() == null || bookMapper.findById(book.getId()) == null) {
            throw ExceptionUtil.newInstance(ExceptionEnum.UPDATE_FAIL_NOT_EXISTS);
        }
        return bookMapper.update(book);
    }

    public Book findById(Integer id){
        return bookMapper.findById(id);
    }

    public int deleteByIds(Integer[] ids) {
        return bookMapper.deleteByIds(ids);
    }

    public List<Book> findAll() {
        return bookMapper.findAll();
    }

    public List<Book> findByNameLike(String name) {
        return bookMapper.findByNameLike(name);
    }
}
