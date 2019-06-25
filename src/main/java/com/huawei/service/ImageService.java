package com.huawei.service;

import com.huawei.dao.ImageDao;
import com.huawei.entity.Image;
import com.huawei.global.ExceptionEnum;
import com.huawei.global.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Optional;

/**
 * @Author: zhouweixin
 * @Description:
 * @Date: Created in 18:07 2018/6/6
 * @Modified By:
 */
@Service
public class ImageService {
    @Autowired
    private ImageDao imageDao;

    /**
     * 上传文件
     * 
     * @param file
     */
    public Image fileUpload(MultipartFile file) {
        if (file.isEmpty()) {
            throw ExceptionUtil.newInstance(ExceptionEnum.UPLOAD_FAILED_FILE_EMPTY);
        }

        Image image = new Image();
        try {
            image.setData(file.getBytes());
            image = imageDao.save(image);
        } catch (IOException e) {
            throw ExceptionUtil.newInstance(ExceptionEnum.UNKOWN_ERROR);
        }

        if(image != null){
            image.setData(null);
        }
        
        return image;
    }

    /**
     * 通过主键查询
     *
     * @param id
     * @return
     */
    public void findOne(Long id, HttpServletResponse response){
        Optional<Image> optional = imageDao.findById(id);

        if (!optional.isPresent()) {
            return;
        }

        Image image = optional.get();
        byte[] bytes = image.getData();
        try {
            OutputStream os = response.getOutputStream();
            os.write(bytes, 0, bytes.length);
            os.flush();
        } catch (IOException e) {
            throw ExceptionUtil.newInstance(ExceptionEnum.UNKOWN_ERROR);
        }
    }

    /**
     * 删除
     *
     * @param id
     */
    public void delete(Long id){
        imageDao.deleteById(id);
    }
}
