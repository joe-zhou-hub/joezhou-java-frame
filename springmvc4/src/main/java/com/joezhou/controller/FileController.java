package com.joezhou.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
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
    public String upload(@NotNull @RequestParam("avatar") MultipartFile avatar) throws IOException {
        File desc = new File("F:\\upload" + System.currentTimeMillis() + avatar.getOriginalFilename());
        if (!desc.getParentFile().exists()) {
            System.out.println(desc.getParentFile().mkdirs());
        }
        avatar.transferTo(desc);
        return "ok";
    }
}