package com.gmhapp.controllers;

import com.gmhapp.entities.ProductEntity;
import com.gmhapp.enums.ProductCategory;
import com.gmhapp.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
public class ProductController {

    private final ProductService service;
    @Autowired
    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping("/addProduct/{userId}")
    public ProductEntity addProduct(@RequestBody ProductEntity product
            , @PathVariable int userId){
        try {
            return service.saveProduct(product,userId);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @PostMapping("/addProducts")
    public List<ProductEntity> addProducts(@RequestBody List<ProductEntity> products){
        return service.saveProducts(products);
    }

    @PostMapping("/addToFavorites/{productId}/{userId}")
    public void addToFavorites(@PathVariable int productId,
                               @PathVariable int userId){
        service.addToFavorites(productId,userId);
    }

    @PostMapping("/removeFromFavorites/{productId}/{userId}")
    public void removeFromFavorites(@PathVariable int productId,
                               @PathVariable int userId){
        service.removeFromFavorites(productId,userId);
    }

    @GetMapping("/products")
    public List<ProductEntity> findAllProducts(){
        return service.getProducts();
    }

    @GetMapping("/categories")
    public List<String> getAllCategories(){
        return service.getAllCategories();
    }

    @GetMapping("/products/{id}")
    public ProductEntity findProductById(@PathVariable int id){
        try {
            return service.getProductById(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @GetMapping("/myProducts/{userId}")
    public List<ProductEntity> findMyProductById(@PathVariable int userId){
        return service.getUserProducts(userId);
    }

    @GetMapping("/myFavorites/{userId}")
    public List<ProductEntity> findMyFavoritesById(@PathVariable int userId){
        return service.getUserFavorites(userId);
    }


    @GetMapping("/products/byName")
    public List<ProductEntity> findProductsByName(@RequestParam("name") String name){
        return service.getAllProductsByName(name);
    }

    @GetMapping("/findProductC/{category}")
    public List<ProductEntity> findProductsByCategory(@PathVariable ProductCategory category){
        return service.getAllProductsByCategory(category);
    }

    @GetMapping("/totalProducts")
    public int howMuchProducts(){
        return service.getTotalProducts();
    }

    @PutMapping("/updateProduct")
    public ProductEntity updateProduct(@RequestBody ProductEntity product){
        try {
            return service.updateProduct(product);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }

    @DeleteMapping("/deleteProduct/{id}")
    public void deleteProduct(@PathVariable int id){
        service.deleteProduct(id);
    }

}
