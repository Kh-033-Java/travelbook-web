package com.Kh033Java.travelbook.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {

    String saveFile() throws IOException;

    void setFile(MultipartFile fileStream);
}

