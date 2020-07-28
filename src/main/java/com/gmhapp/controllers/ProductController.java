package com.gmhapp.controllers;

import com.gmhapp.entities.ProductEntity;
import com.gmhapp.enums.ProductCategory;
import com.gmhapp.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private final ProductService service;
    @Autowired
    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping("/addProduct/{userId}")
    public ProductEntity addProduct(@RequestBody ProductEntity product
            , @PathVariable int userId){
        return service.saveProduct(product,userId);
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

    @GetMapping("/products")
    public List<ProductEntity> findAllProducts(){
        return service.getProducts();
    }

    @GetMapping("/products/{id}")
    public ProductEntity findProductById(@PathVariable int id){
        return service.getProductById(id);
    }

    @GetMapping("/products/byName")
    public List<ProductEntity> findProductsByName(@RequestParam("name") String name){
        return service.getAllProductsByName(name);
    }

    @GetMapping("/findProductC/{category}")
    public List<ProductEntity> findProductsByCategory(@PathVariable ProductCategory category){
        return service.getAllProductsByCategory(category);
    }

    @PutMapping("/updateProduct")
    public ProductEntity updateProduct(@RequestBody ProductEntity product){
        return service.updateProduct(product);
    }

    @DeleteMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable int id){
        return service.deleteProduct(id);
    }

}
