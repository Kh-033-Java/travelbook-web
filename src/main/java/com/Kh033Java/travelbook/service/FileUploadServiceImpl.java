package com.Kh033Java.travelbook.service;

import com.Kh033Java.travelbook.exception.BadFileException;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Acl;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

@Service
@PropertySource(value = "classpath:application.properties")
public class FileUploadServiceImpl implements FileUploadService {

    @Value("${google.cloud.bucket}")
    private String bucket;

    @Value("${google.cloud.credentials}")
    private String pathToCredentials;

    private MultipartFile file;

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    private Storage setCredentials() throws IOException {
//        GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream(pathToCredentials))
//                .createScoped(Lists.newArrayList("https://www.googleapis.com/auth/cloud-platform"));
//        return StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        return StorageOptions.getDefaultInstance().getService();
    }

    public String saveFile() throws IOException {
        Storage storage = setCredentials();
        String fileName = getFileName(file);
        ByteArrayOutputStream outputStream = getByteArrayOutputStream(file.getInputStream());

        BlobInfo blobInfo = storage.create(BlobInfo.newBuilder(bucket, fileName).setContentType(file.getContentType())
                .setAcl(new ArrayList<>(Collections.singletonList(Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER))))
                .build(), outputStream.toByteArray());
        return "https://storage.googleapis.com/" + bucket + "/" + blobInfo.getName();
    }

    private ByteArrayOutputStream getByteArrayOutputStream(InputStream is) throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        byte[] readBuf = new byte[4096];
        while (is.available() > 0) {
            int bytesRead = is.read(readBuf);
            os.write(readBuf, 0, bytesRead);
        }

        return os;
    }

    private String getFileName(MultipartFile file) {
        String mimetype = file.getContentType();
        String type = mimetype.split("/")[0];
        if (!type.equals("image"))
            throw new BadFileException("File is not an image");

        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

        if (fileName.contains("..")) {
            throw new BadFileException("Sorry! Filename contains invalid path sequence " + fileName);
        }

        return fileName;
    }
}
