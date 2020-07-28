package com.gmhapp.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue
    private int id;
    private String userName;
    private String password;
    private String fName;
    private String lName;
    private String address;
    private String phoneNum;
    private String email;
    @JsonManagedReference
    @OneToMany(mappedBy = "userEntity" , targetEntity = ProductEntity.class, cascade = CascadeType.ALL)
    private List<ProductEntity> productList;
    @ManyToMany(mappedBy = "users")
    private List<ProductEntity> favoritesList;

    public UserEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String emailAddress) {
        this.email = emailAddress;
    }

    public List<ProductEntity> getProductList() {
        return productList;
    }

    public void setProductList(List<ProductEntity> productList) {
        this.productList = productList;
    }

    public List<ProductEntity> getFavoritesList() {
        return favoritesList;
    }

    public void setFavoritesList(List<ProductEntity> favoritesList) {
        this.favoritesList = favoritesList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity userEntity = (UserEntity) o;
        return id == userEntity.id &&
                Objects.equals(userName, userEntity.userName) &&
                Objects.equals(password, userEntity.password) &&
                Objects.equals(fName, userEntity.fName) &&
                Objects.equals(lName, userEntity.lName) &&
                Objects.equals(address, userEntity.address) &&
                Objects.equals(phoneNum, userEntity.phoneNum) &&
                Objects.equals(email, userEntity.email) &&
                Objects.equals(productList, userEntity.productList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, password, fName, lName, address, phoneNum, email, productList);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", address='" + address + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", emailAddress='" + email + '\'' +
                ", productList=" + productList +
                '}';
    }
}
