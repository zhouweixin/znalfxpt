package com.huawei.controller;

import com.huawei.entity.Issue;
import com.huawei.global.Result;
import com.huawei.global.ResultUtil;
import com.huawei.service.IssueService;
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
@RequestMapping(value = "/issue")
@Api(tags = "案例接口")
public class IssueController {

    @Autowired
    private IssueService issueService;

    @PostMapping(value = "/add")
    @ApiOperation(value = "新增", notes = "id自增长不需要传参")
    public Result<Issue> add(@Valid Issue issue, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }

        return ResultUtil.success(issueService.add(issue));
    }

    @PostMapping(value = "/update")
    @ApiOperation(value = "更新")
    public Result<Issue> update(@Valid Issue issue, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }

        return ResultUtil.success(issueService.update(issue));
    }

    @GetMapping(value = "/deleteByIds")
    @ApiOperation(value = "通过主键删除(可以多个主键)")
    public Result<Object> deleteByIds(@ApiParam(value = "主键数组") @RequestParam Integer[] ids){
        issueService.deleteByIds(ids);
        return ResultUtil.success();
    }

    @GetMapping(value = "/findAll")
    @ApiOperation(value = "查询所有")
    public Result<List<Issue>> findAll(){
        return ResultUtil.success(issueService.findAll());
    }

    @GetMapping(value = "/findAllByPage")
    @ApiOperation(value = "查询所有-分页")
    public Result<Page<Issue>> findAllByPage(
            @ApiParam(value = "页码(默认为0)", defaultValue = "0") @RequestParam(defaultValue = "0") Integer page,
            @ApiParam(value = "每页记录数(默认为10)", defaultValue = "10") @RequestParam(defaultValue = "10") Integer size,
            @ApiParam(value = "排序字段名(至少传1个字段名)") @RequestParam String[] sortFieldNames,
            @ApiParam(value = "排序方向(0:降序；1升序；默认为1)", defaultValue = "1") @RequestParam(defaultValue = "1") Integer asc) {
        return ResultUtil.success(issueService.findAllByPage(page, size, sortFieldNames, asc));
    }

    @GetMapping(value = "/findById/{id}")
    @ApiOperation(value = "通过主键查询")
    public Result<Issue> findById(@ApiParam(value = "主键") @PathVariable Long id){
        return ResultUtil.success(issueService.findById(id));
    }

    @GetMapping(value = "/findByDomainIdAndTypeId")
    @ApiOperation(value = "通过领域和种类查询")
    public Result<List<Issue>> findByDomainIdAndTypeId(@ApiParam(value = "领域主键, -1表示所有", defaultValue = "-1") @RequestParam(defaultValue = "-1") Integer domainId,
                                                 @ApiParam(value = "种类主键, -1表示所有", defaultValue = "-1") @RequestParam(defaultValue = "-1") Integer typeId){
        return ResultUtil.success(issueService.findByDomainIdAndTypeId(domainId, typeId));
    }

    @GetMapping(value = "/findByProcessId")
    @ApiOperation(value = "通过流程id查询")
    public Result<List<Issue>> findByProcessId(@ApiParam(value = "流程主键") @RequestParam Integer processId){
        return ResultUtil.success(issueService.findByProcessId(processId));
    }
}
