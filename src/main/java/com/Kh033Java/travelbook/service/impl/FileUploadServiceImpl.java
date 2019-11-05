package com.Kh033Java.travelbook.service;

import com.Kh033Java.travelbook.exception.BadFileException;
import com.google.api.gax.paging.Page;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;
import com.google.common.collect.Lists;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    private static Logger logger = LogManager.getLogger(FileUploadServiceImpl.class);

    @Value("${google.cloud.bucket}")
    private String bucket;

    private MultipartFile file;

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    private Storage setCredentials() {
        logger.debug("Setting credentials");
        Storage storage;
        try {
            GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream("/credentials.json"))
                        .createScoped(Lists.newArrayList("https://www.googleapis.com/auth/cloud-platform"));
            storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        } catch (IOException e) {
            logger.error("/credentials.json is not found");
            storage = StorageOptions.getDefaultInstance().getService();
        }

        logger.debug("All buckets ");
        Page<Bucket> buckets = storage.list();
        for (Bucket b : buckets.iterateAll()) {
            logger.debug(b.toString());
        }
        return storage;
    }

    public String saveFile() throws IOException {
        logger.debug("Saving file is start");
        Storage storage = setCredentials();
        String fileName = getFileName(file);
        logger.debug(file.getName());
        logger.debug(file.getContentType());
        logger.debug(fileName);
        ByteArrayOutputStream outputStream = getByteArrayOutputStream(file.getInputStream());

        BlobInfo blobInfo = storage.create(BlobInfo.newBuilder(bucket, fileName).setContentType(file.getContentType())
                .setAcl(new ArrayList<>(Collections.singletonList(Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER))))
                .build(), outputStream.toByteArray());
        logger.debug("Saving file is end");
        return "https://storage.googleapis.com/" + bucket + "/" + blobInfo.getName();
    }

    private ByteArrayOutputStream getByteArrayOutputStream(InputStream inputStream) throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        byte[] readBuf = new byte[4096];
        while (inputStream.available() > 0) {
            int bytesRead = inputStream.read(readBuf);
            os.write(readBuf, 0, bytesRead);
        }

        return os;
    }

    private String getFileName(MultipartFile file) {
        String mimetype = file.getContentType();
        String type = mimetype.split("/")[0];
        if (!type.equals("image")) {
            logger.error("File is not an image");
            throw new BadFileException("File is not an image");
        }

        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

        if (fileName.contains("..")) {
            throw new BadFileException("Sorry! Filename contains invalid path sequence " + fileName);
        }

        return fileName;
    }
}
