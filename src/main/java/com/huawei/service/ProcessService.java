package com.huawei.service;

import com.huawei.dao.DepartmentDao;
import com.huawei.dao.ProcessDao;
import com.huawei.entity.Department;
import com.huawei.entity.Process;
import com.huawei.global.ExceptionEnum;
import com.huawei.global.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

@Service
public class ProcessService {

    @Autowired
    private ProcessDao processDao;

    /**
     * 新增
     *
     * @param process
     * @return
     */
    public Process add(Process process) {
        if (process.getId() != null && processDao.findById(process.getId()).isPresent()) {
            throw ExceptionUtil.newInstance(ExceptionEnum.ADD_FAIL_EXISTS);
        }

        return processDao.save(process);
    }

    /**
     * 更新
     *
     * @param process
     * @return
     */
    public Process update(Process process) {
        if (process.getId() == null || !processDao.findById(process.getId()).isPresent()) {
            throw ExceptionUtil.newInstance(ExceptionEnum.UPDATE_FAIL_NOT_EXISTS);
        }

        return processDao.save(process);
    }

    /**
     * 查询所有
     *
     * @return
     */
    public List<Process> findAll() {
//        return processDao.findAll();
        return processDao.findByParentNull();
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
    public Page<Process> findAllByPage(Integer page, Integer size, String[] sortFieldNames, Integer asc) {
        return processDao.findAll(PageRequest.of(page, size, asc == 1 ? Sort.Direction.ASC : Sort.Direction.DESC, sortFieldNames));
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    public Process findById(int id) {
        return processDao.findById(id).orElse(null);
    }

    /**
     * 通过ids删除
     *
     * @param ids
     */
    @Transactional
    public void deleteByIds(Integer[] ids) {
        processDao.deleteByIdIn(Arrays.asList(ids));
    }

    /**
     * 通过姓名模糊查询
     *
     * @param name
     * @return
     */
    public List<Process> findByNameLike(String name) {
        return processDao.findByNameLike("%" + name + "%");
    }

    /**
     * 通过姓名模糊查询-分页
     *
     * @param name
     * @param page
     * @param size
     * @param sortFieldNames
     * @param asc
     * @return
     */
    public Page<Process> findByNameLikeByPage(String name, Integer page, Integer size, String[] sortFieldNames, Integer asc) {
        return processDao.findByNameLike("%" + name + "%", PageRequest.of(page, size, asc == 1 ? Sort.Direction.ASC : Sort.Direction.DESC, sortFieldNames));
    }
}
