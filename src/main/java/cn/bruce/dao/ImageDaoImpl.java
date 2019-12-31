package cn.bruce.dao;

import com.mongodb.DB;
import com.mongodb.gridfs.GridFS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.io.File;

@Repository
public class ImageDaoImpl implements ImageDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public String putImage(File file) throws Exception {
        GridFS gridFS = new GridFS((DB) mongoTemplate.getDb());

        gridFS.createFile(file);
        return null;
    }

}
