package com.Kh033Java.travelbook.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileUploadService {

    String saveFile() throws IOException;

    void setFile(MultipartFile file);

}
