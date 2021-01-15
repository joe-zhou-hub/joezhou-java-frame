package com.joezhou.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author JoeZhou
 */
@Controller
@RequestMapping("/api/file")
public class FileController {
    
    @ResponseBody
    @RequestMapping("upload")
    public String upload(@RequestParam("avatar") MultipartFile avatar) throws IOException {
        String descPath = "F:" + File.separator + "upload" + File.separator
                + System.currentTimeMillis() + avatar.getOriginalFilename();

        File descFile = new File(descPath);
        if (!descFile.getParentFile().exists()) {
            System.out.println(descFile.getParentFile().mkdirs());
        }
        if (!avatar.isEmpty()) {
            avatar.transferTo(descFile);
        }



        return "ok";
    }
}