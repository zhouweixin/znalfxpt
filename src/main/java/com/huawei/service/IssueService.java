package com.huawei.service;

import com.huawei.dao.IssueDao;
import com.huawei.entity.Issue;
import com.huawei.entity.User;
import com.huawei.global.ExceptionEnum;
import com.huawei.global.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class IssueService {

    @Autowired
    private IssueDao issueDao;

    /**
     * 新增
     *
     * @param issue
     * @return
     */
    public Issue add(Issue issue) {
        if (issue.getId() != null && issueDao.findById(issue.getId()).isPresent()) {
            throw ExceptionUtil.newInstance(ExceptionEnum.ADD_FAIL_EXISTS);
        }
        issue.setCreateTime(new Date());

        return issueDao.save(issue);
    }

    /**
     * 更新
     *
     * @param issue
     * @return
     */
    public Issue update(Issue issue) {
        Optional<Issue> optional = null;
        if (issue.getId() == null || !(optional=issueDao.findById(issue.getId())).isPresent()) {
            throw ExceptionUtil.newInstance(ExceptionEnum.UPDATE_FAIL_NOT_EXISTS);
        }

        issue.setCreateTime(optional.get().getCreateTime());
        issue.setUpdateTime(new Date());
        return issueDao.save(issue);
    }

    /**
     * 查询所有
     *
     * @return
     */
    public List<Issue> findAll() {
        return issueDao.findAll();
    }

    /**
     * 查询所有-分页
     *
     * @param page
     * @param size
     * @param sortFieldNames
     * @param asc
     * @return
     */
    public Page<Issue> findAllByPage(Integer page, Integer size, String[] sortFieldNames, Integer asc) {
        return issueDao.findAll(PageRequest.of(page, size, asc == 1 ? Sort.Direction.ASC : Sort.Direction.DESC, sortFieldNames));
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    public Issue findById(Long id) {
        return issueDao.findById(id).orElse(null);
    }

    /**
     * 通过ids删除
     *
     * @param ids
     */
    @Transactional
    public void deleteByIds(Integer[] ids) {
        issueDao.deleteByIdIn(Arrays.asList(ids));
    }

    public List<Issue> findByDomainIdAndTypeId(Integer issueId, Integer typeId) {
        if(issueId == -1 && typeId == -1){

        } else if(issueId == -1 && typeId != -1){

        } else if(issueId != -1 && typeId == -1){

        } else{
//            return issueDao.findByDomainIdAndTypeId(issueId, typeId);
        }
        return null;
    }
}
