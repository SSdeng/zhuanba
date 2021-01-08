package com.zb.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * 处理文件上传工具类
 * 
 * @author shenmanjie 2021/1/7 18:55
 */
public class FileUtil {

    // 上传文件的目录
    private static final String FileDirectoryPATH = "";

    public static String uploadFile(MultipartFile multipartFile) throws IOException {

        //文件夹目录
        File dir = new File(FileDirectoryPATH);

        if (!dir.exists()) {// 文件夹不存在则创建
            dir.mkdir();
        }

        // 文件原名
        String filename = multipartFile.getOriginalFilename();
        // 文件后缀 123.jpg
        String suffix = filename.substring(filename.lastIndexOf("."));
        // 存储的文件名
        String newName = UUID.randomUUID() + suffix;

        File file = new File(FileDirectoryPATH + "/" + newName);

        // 存储文件
        multipartFile.transferTo(file);
        return newName;
    }

    public static String getFileDirectoryPATH(){
        return FileDirectoryPATH;
    }
}
