package me.dio.service;

import me.dio.domain.model.User;

public interface UserService {
    User findById(Long id);

    User create(User userToCreate);

    User delete(User userToDelete);

    User update(User userToUpdate);
}
