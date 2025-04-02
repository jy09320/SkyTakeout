package com.sky.controller.admin;

import com.sky.result.Result;
import com.sky.utils.AliyunOSSUtil;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RequestMapping("/admin/common")
@Api(tags = "通用接口")
@RestController
public class CommonController {
    //本地磁盘存储
//    @PostMapping
//    public Result handleFileUpload(String name, Integer age, MultipartFile file) throws IOException {
//        log.info("接收参数:{},{},{}",name,age,file);
//        //保存文件
//        String originalFilename = file.getOriginalFilename();
//        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
//        originalFilename = UUID.randomUUID().toString() + extension;
//        file.transferTo(new File("D:/我的练习/" + originalFilename));
//        return Result.success();
//    }
    @Autowired
    private AliyunOSSUtil aliyunOSSUtil;
    @PostMapping("/upload")
    public Result<String> Upload( MultipartFile file) throws Exception {
        log.info("文件上传:{}",file.getOriginalFilename());
        //将文件交给oss管理
        String url=aliyunOSSUtil.upload(file.getBytes(), file.getOriginalFilename());
        log.info("文件上传oss,url:{}",url);
        return Result.success(url);
    }
}
