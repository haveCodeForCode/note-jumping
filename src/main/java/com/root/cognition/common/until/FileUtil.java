package com.root.cognition.common.until;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.UUID;

/**
 * 文件工具
 *
 * @author Worry
 * @version 2019/9/14
 */
public class FileUtil {

    private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);

    /**
     * java文件写出
     *
     * @param file
     * @param filePath
     * @param fileName
     * @throws IOException
     */
    public static void uploadFile(byte[] file, String filePath, String fileName) throws IOException {
        File targetFile = new File(filePath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath + fileName);
        out.write(file);
        out.flush();
        out.close();
    }


    /**
     * 按字节读取文件
     *
     * @param filename
     */
    public static void ReadFileByBytes(String filename) {
        File file = new File(filename);
        InputStream is = null;
        try {
            is = new FileInputStream(file);
            int index = 0;
            System.out.println("-----------------读取文件------------------");
            while (-1 != (index = is.read())) {
                System.out.write(index);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }// TODO Auto-generated catch block
        finally {
            try {
                if (null != is) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 删除文件
     *
     * @param fileName
     * @return
     */
    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }


    public static String renameToUUID(String fileName) {
        return UUID.randomUUID() + "." + fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    /**
     * 文件类型
     *
     * @param fileName
     * @return
     */
    public static int fileType(String fileName) {
        if (fileName == null) {
            fileName = "文件名为空！";
            return 500;

        } else {
            // 获取文件后缀名并转化为写，用于后续比较
            String fileType = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()).toLowerCase();
            // 创建图片类型数组0
            String[] img = {"bmp", "jpg", "jpeg", "png", "tiff", "gif", "pcx", "tga", "exif", "fpx", "svg", "psd",
                    "cdr", "pcd", "dxf", "ufo", "eps", "ai", "raw", "wmf"};
            for (String s : img) {
                if (s.equals(fileType)) {
                    return 0;
                }
            }

            // 创建文档类型数组1
            String[] document = {"txt", "doc", "docx", "xls", "htm", "html", "jsp", "rtf", "wpd", "pdf", "ppt"};
            for (String s : document) {
                if (s.equals(fileType)) {
                    return 1;
                }
            }
            // 创建视频类型数组2
            String[] video = {"mp4", "avi", "mov", "wmv", "asf", "navi", "3gp", "mkv", "f4v", "rmvb", "webm"};
            for (String s : video) {
                if (s.equals(fileType)) {
                    return 2;
                }
            }
            // 创建音乐类型数组3
            String[] music = {"mp3", "wma", "wav", "mod", "ra", "cd", "md", "asf", "aac", "vqf", "ape", "mid", "ogg",
                    "m4a", "vqf"};
            for (String s : music) {
                if (s.equals(fileType)) {
                    return 3;
                }
            }

        }
        //4
        return 99;
    }
}
