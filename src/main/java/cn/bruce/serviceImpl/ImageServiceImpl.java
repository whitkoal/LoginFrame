package cn.bruce.serviceImpl;

import cn.bruce.dao.ImageDao;
import cn.bruce.service.ImageService;
import com.mongodb.client.gridfs.GridFSFindIterable;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.mongodb.gridfs.GridFSDBFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;

@Service("imageService")
public class ImageServiceImpl implements ImageService {

    @Autowired
    ImageDao imageDao;

    @Override
    public String put(File file) throws Exception {
        return imageDao.putImage(file);
    }

    @Override
    public FileInputStream getImage() {
        GridFSDBFile file = imageDao.retrieveFileOne("","");
        System.out.println("file========"+file);
        return (FileInputStream) file.getInputStream();
    }
}
