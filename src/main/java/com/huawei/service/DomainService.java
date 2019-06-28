package com.huawei.service;

import com.huawei.dao.DomainDao;
import com.huawei.entity.Domain;
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
public class DomainService {

    @Autowired
    private DomainDao domainDao;

    /**
     * 新增
     *
     * @param domain
     * @return
     */
    public Domain add(Domain domain) {
        if (domain.getId() != null && domainDao.findById(domain.getId()).isPresent()) {
            throw ExceptionUtil.newInstance(ExceptionEnum.ADD_FAIL_EXISTS);
        }

        if(domainDao.findByName(domain.getName()) != null){
            throw ExceptionUtil.newInstance(ExceptionEnum.ADD_FAILED_NAME_DUP);
        }

        return domainDao.save(domain);
    }

    /**
     * 更新
     *
     * @param domain
     * @return
     */
    public Domain update(Domain domain) {
        if (domain.getId() == null || !domainDao.findById(domain.getId()).isPresent()) {
            throw ExceptionUtil.newInstance(ExceptionEnum.UPDATE_FAIL_NOT_EXISTS);
        }

        Domain temp = domainDao.findByName(domain.getName());
        if(temp != null && temp.getId() != domain.getId()){
            throw ExceptionUtil.newInstance(ExceptionEnum.UPDATE_FAILED_NAME_DUP);
        }

        return domainDao.save(domain);
    }

    /**
     * 查询所有
     *
     * @return
     */
    public List<Domain> findAll() {
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
    public Page<Domain> findAllByPage(Integer page, Integer size, String[] sortFieldNames, Integer asc) {
        return domainDao.findAll(PageRequest.of(page, size, asc == 1 ? Sort.Direction.ASC : Sort.Direction.DESC, sortFieldNames));
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    public Domain findById(Integer id) {
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
}
