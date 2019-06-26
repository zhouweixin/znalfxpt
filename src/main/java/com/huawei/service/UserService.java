package com.huawei.service;

import com.huawei.dao.UserDao;
import com.huawei.entity.Department;
import com.huawei.entity.User;
import com.huawei.global.ExceptionEnum;
import com.huawei.global.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    /**
     * 新增
     *
     * @param user
     * @return
     */
    public User add(User user) {
        if (user.getId() != null && userDao.findById(user.getId()).isPresent()) {
            throw ExceptionUtil.newInstance(ExceptionEnum.ADD_FAIL_EXISTS);
        }

        return userDao.save(user);
    }

    /**
     * 更新
     *
     * @param user
     * @return
     */
    public User update(User user) {
        if (user.getId() == null || !userDao.findById(user.getId()).isPresent()) {
            throw ExceptionUtil.newInstance(ExceptionEnum.UPDATE_FAIL_NOT_EXISTS);
        }

        return userDao.save(user);
    }

    /**
     * 查询所有
     *
     * @return
     */
    public List<User> findAll() {
        return userDao.findAll();
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
    public Page<User> findAllByPage(Integer page, Integer size, String[] sortFieldNames, Integer asc) {
        return userDao.findAll(PageRequest.of(page, size, asc == 1 ? Sort.Direction.ASC : Sort.Direction.DESC, sortFieldNames));
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    public User findById(int id) {
        return userDao.findById(id).orElse(null);
    }

    /**
     * 通过id更新姓名
     *
     * @param id
     * @param name
     */
    @Transactional
    public void updateNameById(int id, String name) {
        userDao.updateNameById(name, id);
    }

    /**
     * 通过ids删除
     *
     * @param ids
     */
    @Transactional
    public void deleteByIds(Integer[] ids) {
        userDao.deleteByIdIn(Arrays.asList(ids));
    }

    /**
     * 通过姓名模糊查询
     *
     * @param name
     * @return
     */
    public List<User> findByNameLike(String name) {
        return userDao.findByNameLike("%" + name + "%");
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
    public Page<User> findByNameLikeByPage(String name, Integer page, Integer size, String[] sortFieldNames, Integer asc) {
        return userDao.findByNameLike("%" + name + "%", PageRequest.of(page, size, asc == 1 ? Sort.Direction.ASC : Sort.Direction.DESC, sortFieldNames));
    }


    public List<User> findByDepartmentId(int departmentId) {
        Department department = new Department();
        department.setId(departmentId);

        return userDao.findByDepartment(department);
    }
}
