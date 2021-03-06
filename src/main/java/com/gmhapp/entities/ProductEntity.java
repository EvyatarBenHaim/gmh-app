package com.gmhapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gmhapp.enums.ProductCategory;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "product")
public class ProductEntity {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String category;
    private String description;
    private String location;
    private String pictureLink;
    private String additionalComments;
    private Date dateAdded;
    @JsonIgnoreProperties(value={"productList","favoritesList"})
    @ManyToOne
    @JoinColumn(name = "u_fk", referencedColumnName = "id" )
    private UserEntity userEntity;
    @ManyToMany
    @JoinTable(name = "favorites_list",
               joinColumns = {@JoinColumn (name = "fk_prod")},
               inverseJoinColumns = {@JoinColumn(name = "fk_user")})
    @JsonIgnore
    private List<UserEntity> users;

    public ProductEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPictureLink() {
        return pictureLink;
    }

    public void setPictureLink(String pictureLink) {
        this.pictureLink = pictureLink;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date date) {
        this.dateAdded= date;
    }

    public String getAdditionalComments() {
        return additionalComments;
    }

    public void setAdditionalComments(String additionalComments) {
        this.additionalComments = additionalComments;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductEntity product = (ProductEntity) o;
        return id == product.id &&
                Objects.equals(name, product.name) &&
                category.equals(product.category)  &&
                Objects.equals(description, product.description) &&
                Objects.equals(location, product.location) &&
                Objects.equals(pictureLink, product.pictureLink) &&
                Objects.equals(additionalComments, product.additionalComments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, category, description, location, pictureLink, additionalComments);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category=" + category +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", pictureLink='" + pictureLink + '\'' +
                ", additionalComments='" + additionalComments + '\'' +
                '}';
    }
}
