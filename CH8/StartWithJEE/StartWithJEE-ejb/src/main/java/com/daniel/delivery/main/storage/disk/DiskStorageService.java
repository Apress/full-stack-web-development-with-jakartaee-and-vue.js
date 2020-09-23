package com.daniel.delivery.main.storage.disk;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import com.daniel.delivery.abstraction.service.file.StorageService;

public class DiskStorageService implements StorageService{
    private final String rootPath;

    public DiskStorageService(String rootPath) {
        this.rootPath = rootPath;
    }

    @Override
    public String save(String fileName, InputStream inputStream) throws IOException{
        String fileKey = UUID.randomUUID().toString() + "-" + fileName;
        
        Path pathLocation = Paths.get(this.rootPath, fileKey);
       
        Files.copy(inputStream, pathLocation);
        
        return fileKey;
    }

    @Override
    public InputStream load(String fileName) throws IOException {
        Path pathLocation = Paths.get(this.rootPath, fileName);
       
        return Files.newInputStream(pathLocation);
    }
    
}
