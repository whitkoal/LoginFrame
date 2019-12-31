package cn.bruce.controller;

import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSInputFile;
import org.apache.tomcat.util.http.fileupload.UploadContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("image")
public class ImagesController {
    private static final Logger logger = LoggerFactory.getLogger(ImagesController.class);

    @RequestMapping(value = "upload", method = RequestMethod.POST)
    public String upload(@RequestParam(value = "file")MultipartFile file){
        if(file.isEmpty()) {
            return "";
        }
        // 获取文件名
        String filename = file.getOriginalFilename();
        logger.info("上传文件名为：" + filename);
        // 获取文件后缀
        String suffixName = filename.substring(filename.lastIndexOf("."));
        logger.info("文件后缀名： "+ suffixName);

        return "";
    }

}
