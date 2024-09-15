package com.example.E_com.service.impl;

import com.example.E_com.service.FileService;
import com.example.E_com.util.CommonFileSavedBinaryDataDto;
import org.springframework.web.multipart.MultipartFile;

public class FileServiceImpl implements FileService {
    @Override
    public CommonFileSavedBinaryDataDto create(MultipartFile file, String directory, String bucket) {
        return null;
    }

    @Override
    public void delete(String fileName, String directory, String bucket) {

    }
}
