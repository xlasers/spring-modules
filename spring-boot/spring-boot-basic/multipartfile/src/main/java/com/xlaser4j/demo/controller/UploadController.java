package com.xlaser4j.demo.controller;

import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @package: com.xlaser4j.demo.controller
 * @author: Elijah.D
 * @time: 2020/1/19 20:58
 * @description: Upload File
 * @modified: Elijah.D
 */
@RestController
public class UploadController {
    /**
     * 上传文件
     *
     * @param file
     * @param req
     * @return url
     */
    @PostMapping("/file")
    public String uploadFile(MultipartFile file, HttpServletRequest req) throws IOException {

        // 创建文件夹img
        String format = DateUtil.format(new Date(), "/yyyy/MM/dd/");
        String parent = req.getServletContext().getRealPath("/img") + format;

        // 创建唯一文件名
        String newName = UUID.randomUUID() + "." + StrUtil.subAfter(file.getOriginalFilename(), ".", true);

        // 保存文件
        file.transferTo(new File(FileUtil.mkdir(parent), newName));

        // 动态获取http/https
        return req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + "/img" + format + newName;
    }

    /**
     * 上传多个文件2种方法
     * <p>
     * 1.用数组接收多个uploads(MultipartFile[] files)
     * 2.用多个MultipartFile参数,实现多个:uploads(MultipartFile file1,MultipartFile file2 ...)
     *
     * @param files
     * @param req
     * @return urls
     */
    @PostMapping("/files")
    public List<String> uploads(MultipartFile[] files, HttpServletRequest req) {
        List<String> urls = new ArrayList<>();

        String format = DateUtil.format(new Date(), "/yyyy/MM/dd/");
        String parent = req.getServletContext().getRealPath("/img") + format;

        Arrays.stream(files).forEach(file -> {
            String newName = UUID.randomUUID() + "." + StrUtil.subAfter(file.getOriginalFilename(), ".", true);
            try {
                file.transferTo(new File(FileUtil.mkdir(parent), newName));
            } catch (IOException e) {
                e.printStackTrace();
            }
            urls.add(req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + "/img" + format + newName);
        });

        return urls;
    }
}
