package com.huawei.dao;

import com.huawei.entity.IssueDomain;
import com.huawei.entity.Keyword;
import com.huawei.entity.QuestionKeyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssueDomainDao extends JpaRepository<IssueDomain, Long> {

}
