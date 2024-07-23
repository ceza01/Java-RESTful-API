package me.dio.service.impl;

import me.dio.domain.model.User;
import me.dio.domain.repository.UserRepository;
import me.dio.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public User create(User userToCreate) {
        if (userRepository.existsByAccountNumber(userToCreate.getAccount().getNumber())) {
            throw new IllegalArgumentException("This account number already exists.");
        }
        return userRepository.save(userToCreate);
    }

    @Override
    public void delete(User userToDelete) {
        if (!userRepository.existsById(userToDelete.getId())){
            throw new IllegalArgumentException("This account number don't exist");
        }
        userRepository.delete(userToDelete);
    }

    @Override
    public User update(User userToUpdate) {
        if (!userRepository.existsByAccountNumber(userToUpdate.getAccount().getNumber())) {
            throw new IllegalArgumentException("This account number doesn't exist");
        }
        return userRepository.save(userToUpdate);
    }
}
