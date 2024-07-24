package me.dio.service;

import me.dio.domain.model.User;

public interface UserService {

    Iterable<User> findAll();

    User findById(Long id);

    User create(User userToCreate);

    void delete(User userToDelete);

    User update(Long id, User userToUpdate);
}
