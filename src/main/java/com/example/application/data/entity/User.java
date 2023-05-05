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


    private String firstName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    private String surName;

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    private String username;
    @JsonIgnore
    private String hashedPassword;
    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Role> role;


    /**
     * Constructs a new User with the specified first name, last name, username, password, and role.
     *
     * @param fn       the first name of the user
     * @param sn       the last name of the user
     * @param username the username of the user
     * @param password the password of the user
     * @param role     the set of roles assigned to the user
     */
    public User(String fn, String sn, String username, String password, Set<Role> role) {
        firstName = fn;
        surName = sn;
        this.username = username;
        String hashed = BCrypt.hashpw(password, BCrypt.gensalt(10));
        System.out.println(hashed);
        hashedPassword = hashed;
        this.role = role;
    }

    /**
     * Constructs a new User object.
     */
    public User() {
    }

    /**
     * Returns the username of this User.
     *
     * @return the username of this User
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of this User to the specified value.
     *
     * @param username the new username to be set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns the hashed password of this User.
     *
     * @return the hashed password of this User
     */
    public String getHashedPassword() {
        return hashedPassword;
    }

    /**
     * Sets the hashed password of this User to the specified value.
     *
     * @param hashedPassword the new hashed password to be set
     */
    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    /**
     * Returns the set of roles assigned to this User.
     *
     * @return the set of roles assigned to this User
     */
    public Set<Role> getRoles() {
        return role;
    }

    /**
     * Sets the set of roles assigned to this User to the specified value.
     *
     * @param roles the new set of roles to be assigned to this User
     */
    public void setRoles(Set<Role> roles) {
        this.role = roles;
    }
}
