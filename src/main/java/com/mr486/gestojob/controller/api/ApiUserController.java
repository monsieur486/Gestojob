package com.mr486.gestojob.controller.api;

import com.mr486.gestojob.model.User;
import com.mr486.gestojob.model.Message;
import com.mr486.gestojob.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class ApiUserController {

  private final UserService userService;

  public ApiUserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping(value = "/users")
  public ResponseEntity<Object> allUser() {
    try {
      List<User> result = userService.allUser();
      return Message.generateResponse(null, HttpStatus.OK, result);
    } catch (Exception e) {
      return Message.generateResponse(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE, null);
    }
  }

  @GetMapping("/users/{id}")
  public ResponseEntity<Object> getUser(@PathVariable Long id) {
    try {
      Optional<User> result = Optional.ofNullable(userService.userById(id));

      if(result.isPresent()){
        return Message.generateResponse(null, HttpStatus.OK, result);
      } else {
        return Message.generateResponse("Entity not found with id: " + id.toString(), HttpStatus.NOT_FOUND, null);
      }
    } catch (Exception e) {
      return Message.generateResponse(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE, null);
    }
  }

  @PostMapping("/users")
  public ResponseEntity<Object> save(@RequestBody User user) {
    try {
      User result = userService.saveUser(user);
      return Message.generateResponse("Entity successfully created.", HttpStatus.CREATED, result);
    } catch (Exception e) {
      return Message.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
    }
  }

  @PutMapping("/users/{id}")
  public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody User user) {
    try {
      User result = userService.updateUser(id, user);
      return Message.generateResponse("Successfully updated", HttpStatus.CREATED, result);
    } catch (Exception e) {
      return Message.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
    }
  }

  @DeleteMapping("/users/{id}")
  public ResponseEntity<Object> delete(@PathVariable Long id) {
    if(Boolean.TRUE.equals(userService.existe(id))){
      try {
        userService.deleteUserById(id);
        return Message.generateResponse("Entity deleted successfully", HttpStatus.ACCEPTED, null);
      } catch (Exception e) {
        return Message.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
      }
    } else {
      return Message.generateResponse("Entity not found with id: " + id.toString(), HttpStatus.NOT_FOUND, null);
    }
  }
}
