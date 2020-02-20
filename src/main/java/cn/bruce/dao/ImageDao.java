package cn.bruce.dao;

import com.mongodb.client.gridfs.GridFSFindIterable;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.mongodb.gridfs.GridFSDBFile;

import java.io.File;

public interface ImageDao {

    public String putImage(File file) throws Exception;

    public GridFSFile findFilesInGridFs();

    public GridFSDBFile retrieveFileOne(String collection, String filename);
}
