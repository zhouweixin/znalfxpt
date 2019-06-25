package com.huawei.controller;

import com.huawei.entity.Image;
import com.huawei.global.Result;
import com.huawei.global.ResultUtil;
import com.huawei.service.ImageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 18:25 2018/6/6
 * @Modified By:
 */
@RestController
@RequestMapping(value = "/image")
@Api(tags = "图片接口")
public class ImageController {
    @Autowired
    private ImageService imageService;

    /**
     * 上传图片
     *
     * @param file
     * @return
     */
    @PostMapping(value = "/upload")
    @ApiOperation(value = "上传")
    public Result<Image> fileUpload(MultipartFile file) {
        return ResultUtil.success(imageService.fileUpload(file));
    }

    /**
     * 查询图片
     *
     * @param id
     * @param response
     * @return
     */
    @GetMapping(value = "/{id}")
    @ApiOperation(value = "查询图片(直接写在a标签就可显示)")
    public Result<Object> getImage(@ApiParam(value = "主键") @PathVariable Long id, HttpServletResponse response) {
        imageService.findOne(id, response);
        return ResultUtil.success();
    }

    /**
     * 通过主键删除
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/deleteById")
    @ApiOperation(value = "通过主键删除")
    public Result<Object> deleteById(@ApiParam(value = "主键") @RequestParam Long id) {
        imageService.delete(id);
        return ResultUtil.success();
    }
}
