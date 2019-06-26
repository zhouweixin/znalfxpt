package com.huawei.controller;

import com.huawei.entity.User;
import com.huawei.global.Result;
import com.huawei.global.ResultUtil;
import com.huawei.service.UserService;
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
@RequestMapping(value = "/user")
@Api(tags = "用户接口")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/add")
    @ApiOperation(value = "新增", notes = "id自增长不需要传参")
    public Result<User> add(@Valid User user, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }

        return ResultUtil.success(userService.add(user));
    }

    @PostMapping(value = "/update")
    @ApiOperation(value = "更新")
    public Result<User> update(@Valid User user, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ResultUtil.error(bindingResult.getFieldError().getDefaultMessage());
        }

        return ResultUtil.success(userService.update(user));
    }

    @GetMapping(value = "/deleteByIds")
    @ApiOperation(value = "通过主键删除(可以多个主键)")
    public Result<Object> deleteByIds(@ApiParam(value = "主键数组") @RequestParam Integer[] ids){
        userService.deleteByIds(ids);
        return ResultUtil.success();
    }

    @GetMapping(value = "/findAll")
    @ApiOperation(value = "查询所有")
    public Result<List<User>> findAll(){
        return ResultUtil.success(userService.findAll());
    }

    @GetMapping(value = "/findAllByPage")
    @ApiOperation(value = "查询所有-分页")
    public Result<Page<User>> findAllByPage(
            @ApiParam(value = "页码(默认为0)", defaultValue = "0") @RequestParam(defaultValue = "0") Integer page,
            @ApiParam(value = "每页记录数(默认为10)", defaultValue = "10") @RequestParam(defaultValue = "10") Integer size,
            @ApiParam(value = "排序字段名(至少传1个字段名)") @RequestParam String[] sortFieldNames,
            @ApiParam(value = "排序方向(0:降序；1升序；默认为1)", defaultValue = "1") @RequestParam(defaultValue = "1") Integer asc) {
        return ResultUtil.success(userService.findAllByPage(page, size, sortFieldNames, asc));
    }

    @GetMapping(value = "/findById/{id}")
    @ApiOperation(value = "通过主键查询")
    public Result<User> findById(@ApiParam(value = "主键") @PathVariable int id){
        return ResultUtil.success(userService.findById(id));
    }

    @GetMapping(value = "/findByNameLike")
    @ApiOperation(value = "通过姓名模糊查询")
    public Result<List<User>> findByNameLike(@ApiParam(value = "姓名") @RequestParam String name){
        return ResultUtil.success(userService.findByNameLike(name));
    }

    @GetMapping(value = "/findByNameLikeByPage")
    @ApiOperation(value = "通过姓名模糊查询-分页")
    public Result<Page<User>> findByNameLikeByPage(
            @ApiParam(value = "姓名", defaultValue = "") @RequestParam(defaultValue = "") String name,
            @ApiParam(value = "页码(默认为0)", defaultValue = "0") @RequestParam(defaultValue = "0") Integer page,
            @ApiParam(value = "每页记录数(默认为10)", defaultValue = "10") @RequestParam(defaultValue = "10") Integer size,
            @ApiParam(value = "排序字段名(至少传1个字段名)") @RequestParam String[] sortFieldNames,
            @ApiParam(value = "排序方向(0:降序；1升序；默认为1)", defaultValue = "1") @RequestParam(defaultValue = "1") Integer asc) {
        return ResultUtil.success(userService.findByNameLikeByPage(name, page, size, sortFieldNames, asc));
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
        userService.updateNameById(id, name);
        return ResultUtil.success();
    }

    @PostMapping(value = "/findByDepartmentId")
    @ApiOperation(value = "通过部门id查询所有用户")
     public Result<List<User>> findByDepartmentId(@ApiParam(value = "主键") @RequestParam int departmentId){
        return ResultUtil.success(userService.findByDepartmentId(departmentId));
     }
}
