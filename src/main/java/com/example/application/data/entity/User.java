package com.example.application.data.entity;

import com.example.application.data.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Set;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Table;
import org.springframework.security.crypto.bcrypt.BCrypt;

@Entity
@Table(name = "application_user")
public class User extends AbstractEntity {

    //@OneToMany(mappedBy = "user")
    
	
	private String firstName;
	private String surName;
    private String username;
    @JsonIgnore
    private String hashedPassword;
    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Role> role;


    public User(String fn, String sn, String username, String password, Set<Role> role) {
    	firstName = fn;
    	surName = sn;
    	this.username = username;
    	String hashed = BCrypt.hashpw(password, BCrypt.gensalt(10));
    	System.out.println(hashed);
    	hashedPassword = hashed;
    	this.role = role;
    }
    
    public User() {
    	
    }
    
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getHashedPassword() {
        return hashedPassword;
    }
    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }
    public Set<Role> getRoles() {
        return role;
    }
    public void setRoles(Set<Role> roles) {
        this.role
        = roles;
    }

}
