package com.huawei.controller;

import com.huawei.entity.Department;
import com.huawei.global.Result;
import com.huawei.global.ResultUtil;
import com.huawei.service.DepartmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/department")
@Api(tags = "部门接口")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping(value = "/add")
    @ApiOperation(value = "新增", notes = "id自增长不需要传参")
    public Result<Department> add(@Valid Department department, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }

        return ResultUtil.success(departmentService.add(department));
    }

    @PostMapping(value = "/update")
    @ApiOperation(value = "更新")
    public Result<Department> update(@Valid Department department, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }

        return ResultUtil.success(departmentService.update(department));
    }

    @GetMapping(value = "/deleteByIds")
    @ApiOperation(value = "通过主键删除(可以多个主键)")
    public Result<Object> deleteByIds(@ApiParam(value = "主键数组") @RequestParam Integer[] ids){
        departmentService.deleteByIds(ids);
        return ResultUtil.success();
    }

    @GetMapping(value = "/findAll")
    @ApiOperation(value = "查询所有")
    public Result<List<Department>> findAll(){
        return ResultUtil.success(departmentService.findAll());
    }

    @GetMapping(value = "/findAllByPage")
    @ApiOperation(value = "查询所有-分页")
    public Result<Page<Department>> findAllByPage(
            @ApiParam(value = "页码(默认为0)", defaultValue = "0") @RequestParam(defaultValue = "0") Integer page,
            @ApiParam(value = "每页记录数(默认为10)", defaultValue = "10") @RequestParam(defaultValue = "10") Integer size,
            @ApiParam(value = "排序字段名(至少传1个字段名)") @RequestParam String[] sortFieldNames,
            @ApiParam(value = "排序方向(0:降序；1升序；默认为1)", defaultValue = "1") @RequestParam(defaultValue = "1") Integer asc) {
        return ResultUtil.success(departmentService.findAllByPage(page, size, sortFieldNames, asc));
    }

    @GetMapping(value = "/findById/{id}")
    @ApiOperation(value = "通过主键查询")
    public Result<Department> findById(@ApiParam(value = "主键") @PathVariable int id){
        return ResultUtil.success(departmentService.findById(id));
    }

    @GetMapping(value = "/findByNameLike")
    @ApiOperation(value = "通过姓名模糊查询")
    public Result<List<Department>> findByNameLike(@ApiParam(value = "姓名") @RequestParam String name){
        return ResultUtil.success(departmentService.findByNameLike(name));
    }

    @GetMapping(value = "/findByNameLikeByPage")
    @ApiOperation(value = "通过姓名模糊查询-分页")
    public Result<Page<Department>> findByNameLikeByPage(
            @ApiParam(value = "姓名", defaultValue = "") @RequestParam(defaultValue = "") String name,
            @ApiParam(value = "页码(默认为0)", defaultValue = "0") @RequestParam(defaultValue = "0") Integer page,
            @ApiParam(value = "每页记录数(默认为10)", defaultValue = "10") @RequestParam(defaultValue = "10") Integer size,
            @ApiParam(value = "排序字段名(至少传1个字段名)") @RequestParam String[] sortFieldNames,
            @ApiParam(value = "排序方向(0:降序；1升序；默认为1)", defaultValue = "1") @RequestParam(defaultValue = "1") Integer asc) {
        return ResultUtil.success(departmentService.findByNameLikeByPage(name, page, size, sortFieldNames, asc));
    }

    /**
     * 前端提出的要求
     *
     * @param id
     * @param name
     * @return
     */
    @PostMapping(value = "/updateNameById")
    @ApiOperation(value = "通过主键更新姓名")
    public Result<Object> updateNameById(@ApiParam(value = "主键") @RequestParam int id,
                                         @ApiParam(value = "姓名") @RequestParam String name){
        departmentService.updateNameById(id, name);
        return ResultUtil.success();
    }
}
