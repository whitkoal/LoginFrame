package cn.bruce.service;

import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public interface ImagesService {
    /**
     * put images
     * @param image
     * @return
     */
    String put(Base64 image);
}
