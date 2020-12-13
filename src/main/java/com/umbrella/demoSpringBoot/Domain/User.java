package com.umbrella.demoSpringBoot.Domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Document(collection = "users")
public class User implements Serializable {
    @Id
    private String id;

    @NotBlank
    @Size(max = 20)
    @Field("username")
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    @Field("email")
    private String email;

    @NotBlank
    @NotNull
    @Field("firstName")
    private String firstName;

    @NotBlank
    @NotNull
    @Field("lastName")
    private String lastName;

    @NotBlank
    @NotNull
    @Field("phoneNumber")
    private String phoneNumber;

    @NotBlank
    @Field("password")
    private String password;

    @Field("restKey")
    private String restKey;

    @Field("creationDate")
    private LocalDateTime creationDate;

    @Field("modificationDate")
    private LocalDateTime modificationDate;

    @DBRef
    @Field("createdBy")
    private User createdBy;

    @DBRef
    @Field("roles")
    private Set<Role> roles = new HashSet<>();

    @Field("activated")
    private boolean activated;

    @Field("address")
    @DBRef
    private Address address;

    @DBRef
    @Field("profileMedia")
    private Media profileMedia;

    public User() {
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRestKey() {
        return restKey;
    }

    public void setRestKey(String restKey) {
        this.restKey = restKey;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(LocalDateTime modificationDate) {
        this.modificationDate = modificationDate;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Media getProfileMedia() {
        return profileMedia;
    }

    public void setProfileMedia(Media profileMedia) {
        this.profileMedia = profileMedia;
    }
}