package com.huawei.service;

import com.huawei.dao.DepartmentDao;
import com.huawei.dao.IssueDao;
import com.huawei.entity.Department;
import com.huawei.entity.Issue;
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

@Service
public class IssueService {

    @Autowired
    private IssueDao domainDao;

    /**
     * 新增
     *
     * @param domain
     * @return
     */
    public Issue add(Issue domain) {
        if (domain.getId() != null && domainDao.findById(domain.getId()).isPresent()) {
            throw ExceptionUtil.newInstance(ExceptionEnum.ADD_FAIL_EXISTS);
        }

        return domainDao.save(domain);
    }

    /**
     * 更新
     *
     * @param domain
     * @return
     */
    public Issue update(Issue domain) {
        if (domain.getId() == null || !domainDao.findById(domain.getId()).isPresent()) {
            throw ExceptionUtil.newInstance(ExceptionEnum.UPDATE_FAIL_NOT_EXISTS);
        }

        domain.setUpdateTime(new Date());
        return domainDao.save(domain);
    }

    /**
     * 查询所有
     *
     * @return
     */
    public List<Issue> findAll() {
        return domainDao.findAll();
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
        return domainDao.findAll(PageRequest.of(page, size, asc == 1 ? Sort.Direction.ASC : Sort.Direction.DESC, sortFieldNames));
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    public Issue findById(Long id) {
        return domainDao.findById(id).orElse(null);
    }

    /**
     * 通过ids删除
     *
     * @param ids
     */
    @Transactional
    public void deleteByIds(Integer[] ids) {
        domainDao.deleteByIdIn(Arrays.asList(ids));
    }

    public List<Issue> findByDomainIdAndTypeId(Integer domainId, Integer typeId) {
        if(domainId == -1 && typeId == -1){

        } else if(domainId == -1 && typeId != -1){

        } else if(domainId != -1 && typeId == -1){

        } else{
//            return domainDao.findByDomainIdAndTypeId(domainId, typeId);
        }
        return null;
    }
}
