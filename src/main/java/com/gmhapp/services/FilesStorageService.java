package com.gmhapp.services;

import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.Stream;

import com.gmhapp.entities.ProductEntity;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FilesStorageService {

    void init() throws IOException;

    void save(MultipartFile file, int productEntity);

    Resource load(String filename);

    void deleteAll();

    Stream<Path> loadAll();

}
