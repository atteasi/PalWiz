/**
 * Service class for managing user data.
 *
 * @author YourName
 * @version 1.0
 * @since 1.0
 */
package com.example.application.data.service;

import com.example.application.data.entity.User;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

/**
 * Service used to communicate between the User and the database
 */
@Service
public class UserService {

    private final UserRepository repository;

    /**
     * Constructs a UserService instance with the specified UserRepository.
     *
     * @param repository the UserRepository to be used for managing user data
     */
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    /**
     * Retrieves a user by ID.
     *
     * @param id the ID of the user to be retrieved
     * @return an Optional containing the User if found, or an empty Optional if not
     */
    public Optional<User> get(Long id) {
        return repository.findById(id);
    }

    /**
     * Retrieves a user by username.
     *
     * @param username the username of the user to be retrieved
     * @return the User with the specified username, or null if not found
     */
    public User getByUsername(String username) {
        return repository.findByUsername(username);
    }

    /**
     * Updates the user data in the repository.
     *
     * @param entity the User object containing the updated data
     * @return the updated User object
     */
    public User update(User entity) {
        return repository.save(entity);
    }

    /**
     * Deletes a user by ID.
     *
     * @param id the ID of the user to be deleted
     */
    public void delete(Long id) {
        repository.deleteById(id);
    }

    /**
     * Returns a paginated list of all users.
     *
     * @param pageable the pagination information
     * @return a Page object containing the paginated list of users
     */
    public Page<User> list(Pageable pageable) {
        return repository.findAll(pageable);
    }

    /**
     * Returns a paginated list of users matching the provided filter.
     *
     * @param pageable the pagination information
     * @param filter the Specification to filter the users
     * @return a Page object containing the paginated list of filtered users
     */
    public Page<User> list(Pageable pageable, Specification<User> filter) {
        return repository.findAll(filter, pageable);
    }

    /**
     * Returns the total number of users in the repository.
     *
     * @return the total number of users
     */
    public int count() {
        return (int) repository.count();
    }

}
