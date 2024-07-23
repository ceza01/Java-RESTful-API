package me.dio.service;

import me.dio.domain.model.User;
import org.springframework.http.ResponseEntity;

public interface UserService {
    User findById(Long id);

    User create(User userToCreate);

    void delete(User userToDelete);

    User update(User userToUpdate);
}
