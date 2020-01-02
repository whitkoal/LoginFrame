package cn.bruce.service;

import java.io.File;
import java.io.FileInputStream;

public interface ImageService {
    public String put(File file) throws Exception;

    public FileInputStream getImage();
}
