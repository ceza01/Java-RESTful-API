package me.dio.service.impl;

import me.dio.domain.model.User;
import me.dio.domain.repository.UserRepository;
import me.dio.service.UserService;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll();
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
        if (!userRepository.existsById(userToDelete.getId())) {
            throw new IllegalArgumentException("This account number don't exist");
        }
        userRepository.delete(userToDelete);
    }

    @Override
    public User update(Long id, User userToUpdate) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new IllegalArgumentException("This account don't exist");
        }

        User userUpdate = user.get();
        userUpdate.setName(userToUpdate.getName());
        userUpdate.setAccount(userToUpdate.getAccount());
        userUpdate.setCard(userToUpdate.getCard());
        userUpdate.setFeatures(userToUpdate.getFeatures());
        userUpdate.setNews(userToUpdate.getNews());

        return userRepository.save(userUpdate);
    }
}
