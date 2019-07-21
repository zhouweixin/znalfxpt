package com.huawei.controller;

import com.huawei.entity.Process;
import com.huawei.global.Result;
import com.huawei.global.ResultUtil;
import com.huawei.service.ProcessService;
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
@RequestMapping(value = "/process")
@Api(tags = "流程接口")
public class ProcessController {

    @Autowired
    private ProcessService processService;

    @PostMapping(value = "/addByJson")
    @ApiOperation(value = "新增", notes = "id自增长不需要传参")
    public Result<Process> addByJson(@RequestBody Process process){
        return ResultUtil.success(processService.addByJson(process));
    }

    @PostMapping(value = "/add")
    @ApiOperation(value = "新增", notes = "id自增长不需要传参")
    public Result<Process> add(Integer parentId, Integer sonId, Process process, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }

        return ResultUtil.success(processService.add(process, parentId, sonId));
    }

    @GetMapping(value = "/deleteById")
    @ApiOperation(value = "通过主键删除")
    public Result<Object> deleteById(Integer id){
        processService.deleteById(id);
        return ResultUtil.success();
    }

    @GetMapping(value = "/deleteByIdWithSon")
    @ApiOperation(value = "通过主键删除--级联删除")
    public Result<Object> deleteByIdWithSon(Integer id){
        processService.deleteByIdWithSon(id);
        return ResultUtil.success();
    }

    @PostMapping(value = "/update")
    @ApiOperation(value = "更新")
    public Result<Process> update(@Valid Process process, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }

        return ResultUtil.success(processService.update(process));
    }

    @GetMapping(value = "/deleteByIds")
    @ApiOperation(value = "通过主键删除(可以多个主键)")
    public Result<Object> deleteByIds(@ApiParam(value = "主键数组") @RequestParam Integer[] ids){
        processService.deleteByIds(ids);
        return ResultUtil.success();
    }

    @GetMapping(value = "/findProcessRoot")
    @ApiOperation(value = "查询流程根(只会返回第一个流程)")
    public Result<Process> findProcessRoot(){
        return ResultUtil.success(processService.findProcessRoot());
    }

    @GetMapping(value = "/findAll")
    @ApiOperation(value = "查询所有")
    public Result<List<Process>> findAll(){
        return ResultUtil.success(processService.findAll());
    }

    @GetMapping(value = "/findAllByPage")
    @ApiOperation(value = "查询所有-分页")
    public Result<Page<Process>> findAllByPage(
            @ApiParam(value = "页码(默认为0)", defaultValue = "0") @RequestParam(defaultValue = "0") Integer page,
            @ApiParam(value = "每页记录数(默认为10)", defaultValue = "10") @RequestParam(defaultValue = "10") Integer size,
            @ApiParam(value = "排序字段名(至少传1个字段名)") @RequestParam String[] sortFieldNames,
            @ApiParam(value = "排序方向(0:降序；1升序；默认为1)", defaultValue = "1") @RequestParam(defaultValue = "1") Integer asc) {
        return ResultUtil.success(processService.findAllByPage(page, size, sortFieldNames, asc));
    }

    @GetMapping(value = "/findById/{id}")
    @ApiOperation(value = "通过主键查询")
    public Result<Process> findById(@ApiParam(value = "主键") @PathVariable int id){
        return ResultUtil.success(processService.findById(id));
    }

    @GetMapping(value = "/findByNameLike")
    @ApiOperation(value = "通过姓名模糊查询")
    public Result<List<Process>> findByNameLike(@ApiParam(value = "姓名") @RequestParam String name){
        return ResultUtil.success(processService.findByNameLike(name));
    }

    @GetMapping(value = "/findByNameLikeByPage")
    @ApiOperation(value = "通过姓名模糊查询-分页")
    public Result<Page<Process>> findByNameLikeByPage(
            @ApiParam(value = "姓名", defaultValue = "") @RequestParam(defaultValue = "") String name,
            @ApiParam(value = "页码(默认为0)", defaultValue = "0") @RequestParam(defaultValue = "0") Integer page,
            @ApiParam(value = "每页记录数(默认为10)", defaultValue = "10") @RequestParam(defaultValue = "10") Integer size,
            @ApiParam(value = "排序字段名(至少传1个字段名)") @RequestParam String[] sortFieldNames,
            @ApiParam(value = "排序方向(0:降序；1升序；默认为1)", defaultValue = "1") @RequestParam(defaultValue = "1") Integer asc) {
        return ResultUtil.success(processService.findByNameLikeByPage(name, page, size, sortFieldNames, asc));
    }

}
