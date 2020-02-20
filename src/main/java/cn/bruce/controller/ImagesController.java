package cn.bruce.controller;

import cn.bruce.service.ImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


@RestController
@RequestMapping("image")
public class ImagesController {
    private static final Logger logger = LoggerFactory.getLogger(ImagesController.class);

    @Autowired
    public ImageService imageService;

    @RequestMapping(value = "upload", method = RequestMethod.GET)
    public String upload(@RequestParam(value = "file" , required = false)MultipartFile file){
//        if(file.isEmpty()) {
//            return "";
//        }
//        // 获取文件名
//        String filename = file.getOriginalFilename();
//        logger.info("上传文件名为：" + filename);
//        // 获取文件后缀
//        String suffixName = filename.substring(filename.lastIndexOf("."));
//        logger.info("文件后缀名： "+ suffixName);

        File f = new File("C:\\Users\\Administrator\\Desktop\\文档\\a.jpg");
        try {
            imageService.put(f);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }


    @GetMapping(produces = MediaType.IMAGE_JPEG_VALUE, value = "get")
    public byte[] getImage() {
        FileInputStream inputStream = imageService.getImage();
        System.out.println("inputstream========" + inputStream);
        byte[] bytes;
        try {
             bytes = new byte[inputStream.available()];
             inputStream.read(bytes, 0, inputStream.available());
             return bytes;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

}
