package com.huawei.service;

import com.huawei.dao.ProcessDao;
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
import java.util.Optional;

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
    public Process addByJson(Process process) {
        if (process.getId() != null && processDao.findById(process.getId()).isPresent()) {
            throw ExceptionUtil.newInstance(ExceptionEnum.ADD_FAIL_EXISTS);
        }

        return processDao.save(process);
    }

    @Transactional
    public Process add(Process process, Integer parentId, Integer sonId) {
        if (process.getId() != null && processDao.findById(process.getId()).isPresent()) {
            throw ExceptionUtil.newInstance(ExceptionEnum.ADD_FAIL_EXISTS);
        }

        if (processDao.findById(parentId).isPresent() == false) {
            throw ExceptionUtil.newInstance(ExceptionEnum.PROCESS_ADD_FAILED_PARENT_NOT_EXISTS);
        }

        // 1.新增 process
        Process pro = processDao.save(process);

        // 2.修改 自己的processId
        processDao.updateProcessId(parentId, pro.getId());

        // 3.修改 儿子的processId
        if (processDao.findById(sonId).isPresent()) {
            processDao.updateProcessId(pro.getId(), sonId);
        }

        return pro;
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
        return processDao.findByParentNull();
    }

    public Process findProcessRoot() {
        return processDao.findProcessRoot();
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

    @Transactional
    public void deleteById(Integer id) {
        Optional<Process> optional = processDao.findById(id);
        if(optional.isPresent() == false){
            throw ExceptionUtil.newInstance(ExceptionEnum.DELETE_FAIL_NOT_EXISTS);
        }

        Process process = optional.get();

        // 更新儿子的父节点
        List<Integer> sonIds = processDao.findIdsByProcessId(process.getId());
        Integer parentId = processDao.findProcessIdById(process.getId());
        processDao.updateProcessIdByIdIn(parentId, sonIds);

        // 删除
        processDao.deleteProcessById(id);
    }

    @Transactional
    public void deleteByIdWithSon(Integer id) {
        if(processDao.findById(id).isPresent() == false){
            throw ExceptionUtil.newInstance(ExceptionEnum.DELETE_FAIL_NOT_EXISTS);
        }

        // 删除
        processDao.deleteById(id);
    }
}
