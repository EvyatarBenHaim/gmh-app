package com.gmhapp.services;


import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;


import com.gmhapp.entities.ProductEntity;
import com.gmhapp.repositories.ProductRepository;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FilesStorageServiceImpl implements FilesStorageService{

    private final Path root = Paths.get("uploads/product");

    public final ProductService service;

    public FilesStorageServiceImpl(ProductService service){
        this.service = service;
    }

    @Override
    public void init(){
        if(Files.notExists(root)){
            try {
                Files.createDirectories(root);
            } catch (IOException e){
                throw new RuntimeException("Could not initialize folder for upload!");
            }
        }
    }

    public void save(MultipartFile file, int pid){
       ProductEntity productEntity = service.getProductById(pid);
       String mimeType = file.getContentType();
       if (mimeType.equals("image/jpg")||
           mimeType.equals("image/jpeg")||
           mimeType.equals("image/png")){
           try {
               Files.copy(file.getInputStream(), this.root.resolve(productEntity.getId()
                       +file.getContentType()));
           } catch (Exception e) {
               throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
           }
       }
       else throw new RuntimeException();
    }

    @Override
    public Resource load(String filename) {
        try {
            Path file = root.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    public void deleteAll() {
        FileSystemUtils.deleteRecursively(root.toFile());
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
        } catch (IOException e) {
            throw new RuntimeException("Could not load the files!");
        }
    }
}
