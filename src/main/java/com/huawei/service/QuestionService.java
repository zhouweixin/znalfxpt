package com.huawei.service;

import com.huawei.dao.KeywordDao;
import com.huawei.dao.QuestionDao;
import com.huawei.dao.QuestionKeywordDao;
import com.huawei.entity.Keyword;
import com.huawei.entity.Question;
import com.huawei.entity.QuestionKeyword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionDao questionDao;

    @Autowired
    private KeywordDao keywordDao;

    @Autowired
    private QuestionKeywordDao questionKeywordDao;

    public List<Question> findByKeywordLike(String keyword){
        // 查询所有关键字
        List<Keyword> keywords = keywordDao.findByNameLike("%" + keyword + "%");

        // 关键字查询所有问题-关键字
        List<QuestionKeyword> questionKeywords = questionKeywordDao.findByKeywordIn(keywords);

        // 获取所有问题
        List<Question> questions = new ArrayList<>();
        for (QuestionKeyword questionKeyword : questionKeywords) {
            questions.add(questionKeyword.getQuestion());
        }

        return questions;
    }
}
