package cn.bruce.dao;

import com.mongodb.client.MongoCursor;
import com.mongodb.client.gridfs.GridFSFindIterable;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.mongodb.gridfs.GridFSDBFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileInputStream;

import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.gridfs.GridFsCriteria.whereFilename;

@Repository
public class ImageDaoImpl implements ImageDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    GridFsOperations operations;

    @Override
    public String putImage(File file) throws Exception {
        //GridFS gridFS = new GridFS((DB) mongoTemplate.getDb());
        FileInputStream fileInputStream = new FileInputStream(file);
        operations.store(fileInputStream, "testImage");

        // gridFS.createFile(file);
        return null;
    }

    @Override
    public GridFSFile findFilesInGridFs() {
        GridFSFindIterable gridFSFiles = operations.find(query(whereFilename().is("filename.txt")));
        MongoCursor<GridFSFile> iterator = gridFSFiles.iterator();

        GridFSFile testImage = operations.findOne(query(whereFilename().is("testImage")));
        return testImage;
    }

}
