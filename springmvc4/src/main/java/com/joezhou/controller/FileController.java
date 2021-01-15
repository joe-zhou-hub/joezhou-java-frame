package com.joezhou.controller;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    @RequestMapping("download")
    public ResponseEntity<byte[]> download(String file) throws IOException {
        byte[] bytes = FileUtils.readFileToByteArray(new File("F:\\upload\\" + file));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", file);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<>(bytes, headers, HttpStatus.CREATED);
    }
}