package com.lostfinder.backend.global.util;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Component
public class FileUtil {

    private static final String UPLOAD_DIR = System.getProperty("user.dir") + "/uploads/";;

    public String saveImage(MultipartFile file) {
        if (file == null || file.isEmpty()) return null;

        String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
        File dir = new File(UPLOAD_DIR);
        if (!dir.exists()) dir.mkdirs();

        File dest = new File(UPLOAD_DIR + filename);
        try {
            file.transferTo(dest);
            return "/uploads/" + filename;   // 클라이언트가 접근할 URL
        } catch (IOException e) {
            throw new RuntimeException("이미지 저장 실패", e);
        }
    }
}
