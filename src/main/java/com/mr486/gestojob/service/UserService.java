package com.mr486.gestojob.service;

import com.mr486.gestojob.model.User;
import com.mr486.gestojob.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  public Boolean existe(Long id) {
    return userRepository.existsById(id);
  }

  public User userById(Long id) {
    return userRepository.findById(id).orElse(null);
  }

  public List<User> allUser() {
    List<User> users;
    users = userRepository.findAll();
    return users;
  }

  public User saveUser(User user) {
    return userRepository.save(user);
  }

  public User updateUser(Long id, User user) {
    user.setId(id);
    return userRepository.save(user);
  }

  public void deleteUserById(Long id) {
    this.userRepository.deleteById(id);
  }

  public Long count() {
    return userRepository.count();
  }

}
