package com.huawei.service;

import com.huawei.dao.ProjectDao;
import com.huawei.entity.Project;
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
public class ProjectService {

    @Autowired
    private ProjectDao projectDao;

    /**
     * 新增
     *
     * @param project
     * @return
     */
    public Project add(Project project) {
        if (project.getId() != null && projectDao.findById(project.getId()).isPresent()) {
            throw ExceptionUtil.newInstance(ExceptionEnum.ADD_FAIL_EXISTS);
        }

        if(projectDao.findByName(project.getName()) != null){
            throw ExceptionUtil.newInstance(ExceptionEnum.ADD_FAILED_NAME_DUP);
        }

        return projectDao.save(project);
    }

    /**
     * 更新
     *
     * @param project
     * @return
     */
    public Project update(Project project) {
        if (project.getId() == null || !projectDao.findById(project.getId()).isPresent()) {
            throw ExceptionUtil.newInstance(ExceptionEnum.UPDATE_FAIL_NOT_EXISTS);
        }

        Project temp = projectDao.findByName(project.getName());
        if(temp != null && temp.getId() != project.getId()){
            throw ExceptionUtil.newInstance(ExceptionEnum.UPDATE_FAILED_NAME_DUP);
        }

        return projectDao.save(project);
    }

    /**
     * 查询所有
     *
     * @return
     */
    public List<Project> findAll() {
        return projectDao.findAll();
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
    public Page<Project> findAllByPage(Integer page, Integer size, String[] sortFieldNames, Integer asc) {
        return projectDao.findAll(PageRequest.of(page, size, asc == 1 ? Sort.Direction.ASC : Sort.Direction.DESC, sortFieldNames));
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    public Project findById(Integer id) {
        return projectDao.findById(id).orElse(null);
    }

    /**
     * 通过ids删除
     *
     * @param ids
     */
    @Transactional
    public void deleteByIds(Integer[] ids) {
        projectDao.deleteByIdIn(Arrays.asList(ids));
    }
}
