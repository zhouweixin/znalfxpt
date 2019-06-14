package com.huawei.controller;

import com.huawei.entity.User;
import com.huawei.global.Result;
import com.huawei.global.ResultUtil;
import com.huawei.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping(value = "/findAll")
    @ApiOperation(value = "查询所有")
    public Result<List<User>> findAll(){
        return ResultUtil.success(userService.findAll());
    }

    @GetMapping(value = "/findById")
    @ApiOperation(value = "通过主键查询")
    public Result<User> findById(@ApiParam(value = "主键") @RequestParam int id){
        return ResultUtil.success(userService.findById(id));
    }
}
