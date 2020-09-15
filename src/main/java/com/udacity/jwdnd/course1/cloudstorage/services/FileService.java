package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class FileService {
    private final FileMapper fileMapper;

    public FileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    public File uploadFile(MultipartFile multipartFile, Integer userId) throws IOException {
        File newFile = new File(null,
                multipartFile.getOriginalFilename(),
                multipartFile.getContentType(),
                Long.toString(multipartFile.getSize()),
                userId,
                multipartFile.getBytes()
                );
        try {
            fileMapper.uploadFile(newFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newFile;
    }

    public File getFile(int fileId) {
        return fileMapper.getFileById(fileId);
    }

    public List<File> getAllFiles(int userId) {
        return fileMapper.getFileByUser(userId);
    }

    public Set<String> getAllFileName(int userId) {
        Set<String> res = new HashSet<>();
        List<File> files = getAllFiles(userId);
        for (File file : files) res.add(file.getFileName());
        return res;
    }

    public boolean deleteFile(int fileId) {
        return fileMapper.deleteFileById(fileId);
    }
}
