package com.huawei.controller;

import com.huawei.entity.Project;
import com.huawei.global.Result;
import com.huawei.global.ResultUtil;
import com.huawei.service.ProjectService;
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
@RequestMapping(value = "/domain")
@Api(tags = "领域接口")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping(value = "/add")
    @ApiOperation(value = "新增", notes = "id自增长不需要传参")
    public Result<Project> add(@Valid Project project, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }

        return ResultUtil.success(projectService.add(project));
    }

    @PostMapping(value = "/update")
    @ApiOperation(value = "更新")
    public Result<Project> update(@Valid Project project, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }

        return ResultUtil.success(projectService.update(project));
    }

    @GetMapping(value = "/deleteByIds")
    @ApiOperation(value = "通过主键删除(可以多个主键)")
    public Result<Object> deleteByIds(@ApiParam(value = "主键数组") @RequestParam Integer[] ids){
        projectService.deleteByIds(ids);
        return ResultUtil.success();
    }

    @GetMapping(value = "/findAll")
    @ApiOperation(value = "查询所有")
    public Result<List<Project>> findAll(){
        return ResultUtil.success(projectService.findAll());
    }

    @GetMapping(value = "/findAllByPage")
    @ApiOperation(value = "查询所有-分页")
    public Result<Page<Project>> findAllByPage(
            @ApiParam(value = "页码(默认为0)", defaultValue = "0") @RequestParam(defaultValue = "0") Integer page,
            @ApiParam(value = "每页记录数(默认为10)", defaultValue = "10") @RequestParam(defaultValue = "10") Integer size,
            @ApiParam(value = "排序字段名(至少传1个字段名)") @RequestParam String[] sortFieldNames,
            @ApiParam(value = "排序方向(0:降序；1升序；默认为1)", defaultValue = "1") @RequestParam(defaultValue = "1") Integer asc) {
        return ResultUtil.success(projectService.findAllByPage(page, size, sortFieldNames, asc));
    }

    @GetMapping(value = "/findById/{id}")
    @ApiOperation(value = "通过主键查询")
    public Result<Project> findById(@ApiParam(value = "主键") @PathVariable Integer id){
        return ResultUtil.success(projectService.findById(id));
    }
}
