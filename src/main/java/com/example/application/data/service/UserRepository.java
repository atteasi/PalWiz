package com.example.application.data.service;

import com.example.application.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Interface for the user repository
 */
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    /**
     * Finds users by username
     *
     * @param username Username of the user we want to find
     * @return The user whose username we inputted
     */
    User findByUsername(String username);
}
