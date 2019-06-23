package com.huawei.service;

import com.huawei.dao.DepartmentDao;
import com.huawei.entity.Department;
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
public class DepartmentService {

    @Autowired
    private DepartmentDao departmentDao;

    /**
     * 新增
     *
     * @param department
     * @return
     */
    public Department add(Department department) {
        if (department.getId() != null && departmentDao.findById(department.getId()).isPresent()) {
            throw ExceptionUtil.newInstance(ExceptionEnum.ADD_FAIL_EXISTS);
        }

        return departmentDao.save(department);
    }

    /**
     * 更新
     *
     * @param department
     * @return
     */
    public Department update(Department department) {
        if (department.getId() == null || !departmentDao.findById(department.getId()).isPresent()) {
            throw ExceptionUtil.newInstance(ExceptionEnum.UPDATE_FAIL_NOT_EXISTS);
        }

        return departmentDao.save(department);
    }

    /**
     * 查询所有
     *
     * @return
     */
    public List<Department> findAll() {
        return departmentDao.findAll();
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
    public Page<Department> findAllByPage(Integer page, Integer size, String[] sortFieldNames, Integer asc) {
        return departmentDao.findAll(PageRequest.of(page, size, asc == 1 ? Sort.Direction.ASC : Sort.Direction.DESC, sortFieldNames));
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    public Department findById(int id) {
        return departmentDao.findById(id).orElse(null);
    }

    /**
     * 通过id更新姓名
     *
     * @param id
     * @param name
     */
    @Transactional
    public void updateNameById(int id, String name) {
        departmentDao.updateNameById(name, id);
    }

    /**
     * 通过ids删除
     *
     * @param ids
     */
    @Transactional
    public void deleteByIds(Integer[] ids) {
        departmentDao.deleteByIdIn(Arrays.asList(ids));
    }

    /**
     * 通过姓名模糊查询
     *
     * @param name
     * @return
     */
    public List<Department> findByNameLike(String name) {
        return departmentDao.findByNameLike("%" + name + "%");
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
    public Page<Department> findByNameLikeByPage(String name, Integer page, Integer size, String[] sortFieldNames, Integer asc) {
        return departmentDao.findByNameLike("%" + name + "%", PageRequest.of(page, size, asc == 1 ? Sort.Direction.ASC : Sort.Direction.DESC, sortFieldNames));
    }


}
