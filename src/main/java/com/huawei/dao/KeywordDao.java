package com.huawei.dao;

import com.huawei.entity.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KeywordDao extends JpaRepository<Keyword, Long> {
    List<Keyword> findByNameLike(String name);
}
