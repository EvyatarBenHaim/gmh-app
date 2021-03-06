package com.gmhapp.services;

import com.gmhapp.entities.ProductEntity;
import com.gmhapp.enums.ProductCategory;
import com.gmhapp.entities.UserEntity;
import com.gmhapp.enums.ValidationQuestions;
import com.gmhapp.exception.ApiException;
import com.gmhapp.repositories.ProductRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final UserService userService;
    private final ProductRepository repository;
    private final static Logger logger = Logger.getLogger(ProductService.class.getName());

    @Autowired
    public ProductService(ProductRepository repository,UserService userService)
    {
        this.userService = userService;
        this.repository = repository;
    }

    public ProductEntity saveProduct(ProductEntity product , int uesrId){
        if(Objects.isNull(product.getCategory())||
           Objects.isNull(product.getDescription())||
           Objects.isNull(product.getLocation())||
           Objects.isNull(product.getName())
        )
            throw new ApiException("אנא הזן את כל הפרטים הנדרשים!",HttpStatus.CONFLICT);

        UserEntity userEntity = userService.getUserById(uesrId);
        product.setUserEntity(userEntity);
        product.setDateAdded(Calendar.getInstance().getTime());
        logger.debug("Product ID: "+product.getId()+" added successfully.");
        return repository.save(product);
    }

    public void addToFavorites(int productId ,int userId) {
        UserEntity userEntity = userService.getUserById(userId);
        ProductEntity productEntity = getProductById(productId);
        productEntity.getUsers().add(userEntity);
        logger.debug("Product ID: "+productId+" added successfully to" +
                " user id "+userId+ " favorites list." );
        repository.save(productEntity);
    }

    public void removeFromFavorites(int productId ,int userId) {
        UserEntity userEntity = userService.getUserById(userId);
        ProductEntity productEntity = getProductById(productId);
        productEntity.getUsers().remove(userEntity);
        logger.debug("Product ID: "+productId+" added successfully to" +
                " user id "+userId+ " favorites list." );
        repository.save(productEntity);
    }

    public List<ProductEntity> saveProducts(List<ProductEntity> products){
        return repository.saveAll(products);
    }

    public List<ProductEntity> getProducts(){
          return  repository.findAll();
    }

    public List<String> getAllCategories(){
        List<String> categories = Arrays.stream(ProductCategory.values()).map(ProductCategory::getHebrew).collect(Collectors.toList());
        logger.info("Get products categories successfully: " + categories);
        return categories;
    }


    public ProductEntity getProductById(int id){
        ProductEntity productEntity =  repository.findById(id).orElse(null);
        if(Objects.isNull(productEntity))
            throw new ApiException("Product not found! the id is: "+id, HttpStatus.NOT_FOUND);
        return productEntity;
    }

    public List<ProductEntity> getAllProductsByName(String name){
        if(repository.existsByName(name))
           return repository.findAllByName(name);
        throw new ApiException("name not found.",HttpStatus.NOT_FOUND);
    }

    public List<ProductEntity> getAllProductsByCategory(ProductCategory category){
        if(ProductCategory.getProductCategory(category.toString())==null)
            throw new ApiException("category not found.",HttpStatus.NOT_FOUND);
        return repository.findAllByCategory(category);
    }

    public String deleteProduct(int id){
        repository.deleteById(id);
        logger.debug("Product ID: "+id+" removed successfully.");
        return "Product ID:"+id+" removed successfully.";
    }

    public int getTotalProducts(){
        return repository.countAllByIdIsNotNull();
    }

    public ProductEntity updateProduct(ProductEntity product){
        ProductEntity existingProduct = repository.findById(product.getId()).orElse(null);
        if(Objects.nonNull(existingProduct)) {
            existingProduct.setName(product.getName());
            existingProduct.setCategory(product.getCategory());
            existingProduct.setDescription(product.getDescription());
            existingProduct.setLocation(product.getLocation());
            if(product.getPictureLink() != null) {
                existingProduct.setPictureLink(product.getPictureLink());
            }
            existingProduct.setAdditionalComments(product.getAdditionalComments());
            logger.debug("Product id "+product.getId()+" successfully updated.");
            return repository.save(existingProduct);
        }

        throw new ApiException("The productId not found , the ID is:"+product.getId(), HttpStatus.NOT_FOUND);
    }

    public List<ProductEntity> getUserProducts(int userId) {
        return userService.getUserById(userId).getProductList();
    }

    public List<ProductEntity> getUserFavorites(int userId) {
        return userService.getUserById(userId).getFavoritesList();
    }
}
