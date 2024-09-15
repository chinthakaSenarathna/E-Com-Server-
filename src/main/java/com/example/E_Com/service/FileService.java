package com.example.E_com.service;

import com.example.E_com.util.CommonFileSavedBinaryDataDto;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    public CommonFileSavedBinaryDataDto create(MultipartFile file, String directory, String bucket);
    public void delete(String fileName, String directory, String bucket);
}
